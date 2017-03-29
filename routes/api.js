/**
 *   Name: api.js
 *   Last Modified: 2017, March 29
 *   Last Modified By: Mohammed Juned Ahmed
 */
// Dependencies
let express = require('express');
let router = express.Router();

// Get Models
let Feedback = require('../models/feedback');
let Notification = require('../models/notification');

// Routes
Feedback.methods(['get','post', 'put', 'delete']);
Feedback.register(router, '/feedback');
Notification.methods(['get']);
Notification.register(router, '/notification');

// Return Router
module.exports = router;