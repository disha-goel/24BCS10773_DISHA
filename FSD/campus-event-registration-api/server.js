require('dotenv').config();
const express = require('express');
const mongoose = require('mongoose');
const { Registration, EVENT_TYPES, STATUSES } = require('./Registration');

const app = express();
app.use(express.json());

const PORT = process.env.PORT || 5000;
const MONGO_URI = process.env.MONGO_URI || 'mongodb://127.0.0.1:27017/campus_symposium';

const ALLOWED_FIELDS = ['studentName', 'collegeEmail', 'rollNumber', 'eventType', 'teamSize', 'ticketPrice', 'discountCode', 'status', 'registrationDate'];

function pick(body) {
  var data = {};
  ALLOWED_FIELDS.forEach(function (f) {
    if (body[f] !== undefined) data[f] = body[f];
  });
  if ('discountCode' in data && (data.discountCode === null || String(data.discountCode).trim() === '')) {
    data.discountCode = undefined;
  }
  return data;
}

app.get('/', (req, res) => {
  res.json({ success: true, message: 'Campus Event Registration API is running' });
});

app.post('/api/registrations', async (req, res, next) => {
  try {
    var reg = await Registration.create(pick(req.body || {}));
    res.status(201).json({ success: true, message: 'Registration successful', data: reg });
  } catch (err) {
    next(err);
  }
});

app.get('/api/registrations', async (req, res, next) => {
  try {
    var filter = {};
    if (req.query.eventType) {
      var et = String(req.query.eventType).toUpperCase();
      if (!EVENT_TYPES.includes(et)) {
        return res.status(400).json({ success: false, message: 'Invalid eventType filter', errors: ['eventType must be one of: ' + EVENT_TYPES.join(', ')] });
      }
      filter.eventType = et;
    }
    if (req.query.status) {
      var st = String(req.query.status).toUpperCase();
      if (!STATUSES.includes(st)) {
        return res.status(400).json({ success: false, message: 'Invalid status filter', errors: ['status must be one of: ' + STATUSES.join(', ')] });
      }
      filter.status = st;
    }
    var regs = await Registration.find(filter).sort({ registrationDate: -1 });
    res.status(200).json({ success: true, count: regs.length, data: regs });
  } catch (err) {
    next(err);
  }
});

function checkId(req, res, next) {
  if (!/^[0-9a-fA-F]{24}$/.test(req.params.id)) {
    return res.status(400).json({ success: false, message: 'Invalid registration ID format', errors: ['Invalid registration ID format'] });
  }
  next();
}

app.get('/api/registrations/:id', checkId, async (req, res, next) => {
  try {
    var reg = await Registration.findById(req.params.id);
    if (!reg) return res.status(404).json({ success: false, message: 'Registration not found', errors: ['Registration not found'] });
    res.status(200).json({ success: true, data: reg });
  } catch (err) {
    next(err);
  }
});

app.put('/api/registrations/:id', checkId, async (req, res, next) => {
  try {
    var changes = pick(req.body || {});
    if (Object.keys(changes).length === 0) {
      return res.status(400).json({ success: false, message: 'No valid fields provided to update', errors: ['Send at least one updatable field in the request body'] });
    }
    var reg = await Registration.findById(req.params.id);
    if (!reg) return res.status(404).json({ success: false, message: 'Registration not found', errors: ['Registration not found'] });
    reg.set(changes);
    await reg.save();
    res.status(200).json({ success: true, message: 'Registration updated successfully', data: reg });
  } catch (err) {
    next(err);
  }
});

app.delete('/api/registrations/:id', checkId, async (req, res, next) => {
  try {
    var reg = await Registration.findByIdAndDelete(req.params.id);
    if (!reg) return res.status(404).json({ success: false, message: 'Registration not found', errors: ['Registration not found'] });
    res.status(200).json({ success: true, message: 'Registration deleted successfully', data: reg });
  } catch (err) {
    next(err);
  }
});

app.use((req, res) => {
  res.status(404).json({ success: false, message: 'Route ' + req.method + ' ' + req.originalUrl + ' not found', errors: ['Route ' + req.method + ' ' + req.originalUrl + ' not found'] });
});

app.use((err, req, res, next) => {
  if (err.name === 'ValidationError') {
    var msgs = Object.values(err.errors).map(function (e) {
      if (e.name === 'CastError') return e.path + ' must be a valid ' + String(e.kind).toLowerCase();
      return e.message;
    });
    return res.status(400).json({ success: false, message: 'Validation failed', errors: msgs });
  }
  if (err.code === 11000) {
    return res.status(409).json({ success: false, message: 'This email is already registered for the event', errors: ['This email is already registered for the event'] });
  }
  if (err.name === 'CastError') {
    if (err.path === '_id') return res.status(400).json({ success: false, message: 'Invalid registration ID format', errors: ['Invalid registration ID format'] });
    return res.status(400).json({ success: false, message: 'Invalid value for ' + err.path, errors: ['Invalid value for ' + err.path] });
  }
  if (err.type === 'entity.parse.failed') {
    return res.status(400).json({ success: false, message: 'Request body is not valid JSON', errors: ['Request body is not valid JSON'] });
  }
  console.error(err);
  res.status(500).json({ success: false, message: 'Something went wrong on the server', errors: ['Something went wrong on the server'] });
});

mongoose.connect(MONGO_URI).then(() => {
  console.log('MongoDB connected');
  app.listen(PORT, () => {
    console.log('Server listening on port ' + PORT);
  });
}).catch((err) => {
  console.error('Could not connect to MongoDB:', err.message);
  process.exit(1);
});
