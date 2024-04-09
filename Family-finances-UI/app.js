var express = require('express');
var app = express();

app.use(express.static(__dirname + '/'));
app.use(express.static(__dirname + '/css'));

app.get('/', function(req, res) {
    res.redirect('/src/pages/index.html');
});

app.get('/login', function(req, res) {
    res.redirect('/src/pages/index.html');
});

app.get('/registration', function(req, res) {
    res.redirect('/src/pages/registration.html');
});

app.get('/room', function(req, res) {
    res.redirect('/src/pages/rooms.html');
});

app.get('/finance', function(req, res) {
    res.redirect('/src/pages/finance.html');
});

app.listen(8080);
console.log('SERVER UP!'); 