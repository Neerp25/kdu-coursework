const express = require('express');
const cors = require('cors');
const http = require('http');
const socketIo = require('socket.io');
const path = require('path');
const bodyParser = require('body-parser');

const app = express();

app.use(cors());
app.use(express.json());

// Serve static files from the 'public' directory
app.use(express.static(path.join(__dirname, 'public')));

const server = http.createServer(app);
const io = new socketIo.Server(server, {
    cors: {
        origin: '*'
    }
});

// Array to store multiple hardcoded user details
const hardcodedUsers = [
    { username: 'user1', password: 'password1' },
    { username: 'user2', password: 'password2' },
    // Add more users as needed
];

app.post("/login", (req, res) => {
    const { username, password } = req.body;

    // Check if the provided credentials match any user in the array
    const isValidUser = hardcodedUsers.some(user => user.username === username && user.password === password);

    if (isValidUser) {
        res.json({ success: true, message: "Login successful" });
    } else {
        res.json({ success: false, message: "Invalid credentials" });
    }
});



app.use('/Assignment-2', express.static(path.join(__dirname, 'public')));




app.get("/", (req, res) => {
    res.json({
        msg: "Hello--Connected"
    });
});


let posts = [
    { id: 1, text: 'Post 1' },
    { id: 2, text: 'Post 2' },
    { id :3, text: 'post 3'},
    {id :4 , text: 'post 4'},
    {id:5  ,text: 'post 5'}
];

app.get('/api/posts', (req, res) => {
    const { pageNumber, pageSize } = req.query;
    const startIndex = (pageNumber - 1) * pageSize;
    const endIndex = startIndex + pageSize;
    const paginatedPosts = posts.slice(startIndex, endIndex);

    res.json({ posts: paginatedPosts });
});

app.post('/api/posts', (req, res) => {
    const newPost = req.body;
    newPost.id = posts.length + 1;
    posts.unshift(newPost); // Add the new post at the beginning of the array

    res.json(newPost);
    
});


io.on('connection',(socket)=>{
    console.log('Connected');

    socket.on("message",(payload)=>{
        console.log("Payload",payload);
        io.except(socket.id).emit("new-message",payload)
    })

});

server.listen(8000, () => {
    console.log("Running................");
});

