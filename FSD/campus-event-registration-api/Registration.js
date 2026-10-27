const mongoose = require('mongoose');

const EVENT_TYPES = ['CODING_HACKATHON', 'AI_WORKSHOP', 'ROBOTICS', 'GAMING'];
const STATUSES = ['PENDING', 'CONFIRMED', 'CANCELLED'];
const DISCOUNT_CODES = ['CAMPUS50', 'EARLYBIRD'];

const registrationSchema = new mongoose.Schema({
  studentName: {
    type: String,
    required: [true, 'Student name is required'],
    trim: true,
    minlength: [3, 'Student name must be at least 3 characters long'],
    maxlength: [50, 'Student name cannot be longer than 50 characters']
  },
  collegeEmail: {
    type: String,
    required: [true, 'College email is required'],
    unique: true,
    trim: true,
    lowercase: true,
    match: [/^[^\s@]+@[^\s@]+\.(edu|ac\.in)$/, 'Must be a valid college email ending in .edu or .ac.in']
  },
  rollNumber: {
    type: String,
    required: [true, 'Roll number is required'],
    trim: true,
    uppercase: true,
    match: [/^[A-Z0-9]+$/, 'Roll number can only contain letters and digits'],
    minlength: [8, 'Roll number must be 8 to 10 characters long'],
    maxlength: [10, 'Roll number must be 8 to 10 characters long']
  },
  eventType: {
    type: String,
    required: [true, 'Event type is required'],
    enum: {
      values: EVENT_TYPES,
      message: 'Event type must be one of: ' + EVENT_TYPES.join(', ')
    }
  },
  teamSize: {
    type: Number,
    required: [true, 'Team size is required'],
    min: [1, 'Team size must be at least 1'],
    max: [4, 'Team size cannot be more than 4'],
    validate: {
      validator: Number.isInteger,
      message: 'Team size must be a whole number'
    }
  },
  ticketPrice: {
    type: Number,
    required: [true, 'Ticket price is required'],
    min: [0, 'Ticket price cannot be negative']
  },
  discountCode: {
    type: String,
    trim: true,
    uppercase: true,
    validate: {
      validator: (code) => !code || DISCOUNT_CODES.includes(code),
      message: 'Invalid discount code'
    }
  },
  finalAmount: {
    type: Number
  },
  status: {
    type: String,
    enum: {
      values: STATUSES,
      message: 'Status must be one of: ' + STATUSES.join(', ')
    },
    default: 'PENDING'
  },
  registrationDate: {
    type: Date,
    default: Date.now,
    validate: {
      validator: (date) => date.getTime() <= Date.now(),
      message: 'Registration date cannot be in the future'
    }
  }
});

function calculateFinalAmount(price, code) {
  if (code === 'CAMPUS50') return Math.round(price * 0.5 * 100) / 100;
  if (code === 'EARLYBIRD') return Math.max(price - 100, 0);
  return price;
}

registrationSchema.pre('validate', function () {
  if (this.eventType === 'AI_WORKSHOP' && this.teamSize > 1) {
    this.invalidate('teamSize', 'AI Workshop only permits individual registrations', this.teamSize);
  }
  if (typeof this.ticketPrice === 'number' && !Number.isNaN(this.ticketPrice)) {
    this.finalAmount = calculateFinalAmount(this.ticketPrice, this.discountCode);
  }
});

const Registration = mongoose.model('Registration', registrationSchema);

module.exports = { Registration, EVENT_TYPES, STATUSES, DISCOUNT_CODES };
