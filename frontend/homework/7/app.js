const express = require("express");
const cors = require("cors");
const http = require('http')
const socketIo = require("socket.io");
const { Console } = require("console");

const app = express();

app.use(cors());
app.use(express.json());
const server = http.createServer(app);
const io = new socketIo.Server(server,{
    cors:{
        origin:'*'
    }
});


app.get("/",(req,res)=>{
    res.json({
        msg:"Hello--Connected"
    })
})

io.on('connection',(socket)=>{
    console.log('Connected');

    socket.on("message",(payload)=>{
        console.log("Payload",payload);
        io.except(socket.id).emit("new-message",payload)
    })

});

server.listen(3000,()=>{
console.log("Running................")
})