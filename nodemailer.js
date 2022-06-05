const nodemailer = require('nodemailer');
  
  
let mailTransporter = nodemailer.createTransport({
    service: 'gmail',
    auth: {
        user: 'ayurvedicburnol@gmail.com',
        pass: 'gauravsinghmer'
    }
});
  
let mailDetails = {
    from: 'ayurvedicburnol@gmail.com',
    to: 'gauravsinghmer@gmail.com',
    subject: 'Test mail',
    text: 'Node.js testing mail',
    attachments: [
        {
            filename: 'emailable-report.html',
            path: __dirname + '/test-output/emailable-report.html'
        }
    ]
};
  
mailTransporter.sendMail(mailDetails, function(err, data) {
    if(err) {
        console.log('Error Occurs');
    } else {
        console.log('Email sent successfully');
    }
});