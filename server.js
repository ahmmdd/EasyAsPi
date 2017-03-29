/**
 *   Name: server.js
 *   Last Modified: 2017, March 29
 *   Last Modified By: Mohammed Juned Ahmed
 */
// Dependencies
let express = require('express');
let mongoose = require('mongoose');
let bodyParser = require('body-parser');

// Connect to MongoDB
let URI = "mongodb://root:admin@ds143340.mlab.com:43340/easyaspi";
mongoose.connect(URI);

// Express
let app = express();
app.use(bodyParser.urlencoded({extended:true }));
app.use(bodyParser.json());

// Routes
app.use('/api', require('./routes/api'));

// Start the server
let port = process.env.PORT || 3000;
app.listen(port);
if(port == 3000){
    console.log(`Server Started at http://localhost:${port}`);
}else{
    console.log('Server Started...');
}
