/**
 *   Name: feedback.js
 *   Last Modified: 2017, March 29
 *   Last Modified By: Mohammed Juned Ahmed
 */
// Dependencies
let restful = require('node-restful');
let mongoose = require('mongoose');

// Schema
let feedbackSchema = new mongoose.Schema({
    name: String,
    feedback: String
});

// Return Models
module.exports = restful.model('tblFeedback', feedbackSchema);
