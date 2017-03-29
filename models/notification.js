/**
 *   Name: notification.js
 *   Last Modified: 2017, March 29
 *   Last Modified By: Mohammed Juned Ahmed
 */
// Dependencies
let restful = require('node-restful');
let mongoose = require('mongoose');

// Schema
let notificationSchema = new mongoose.Schema({
    subject: String,
    notification: String
});

// Return Models
module.exports = restful.model('tblNotification', notificationSchema);
