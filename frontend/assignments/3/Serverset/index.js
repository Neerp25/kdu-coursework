// server.js

const express = require("express");
const cors = require("cors");
const socketio = require("socket.io");
const http = require("http");

const port = 5000;
const app = express();
const server = http.createServer(app);

app.use(cors());
app.use(express.json());

const io = new socketio.Server(server, {
    cors: {
        origin: "http://127.0.0.1:5500"
    }
});

io.on("connection", (socket) => {
    console.log("New user connected to the stock room");

    // Emit the initial price
    const initialPrice = Math.floor((Math.random() * 500) + 1);
    socket.emit("priceUpdate", initialPrice);

    // Set up an interval to emit a new price every 5 seconds
    const intervalId = setInterval(() => {
        const newPrice = Math.floor((Math.random() * 500) + 1);
        socket.emit("priceUpdate", newPrice);
        console.log("New price sent: " + newPrice);
    }, 5000);

    // Cleanup on disconnect
    socket.on("disconnect", () => {
        clearInterval(intervalId);
    });
});

server.listen(port, () => {
    console.log(`Server is connected: http://localhost:${port}`);
});


app.get('/statup/data', (req, res) => {
    res.send([[145, 129, 298, 298, 232], [{amount: "121", time: "Fri, 7 Feb 1999 21:04:05 GMT", type: "Buy"},{amount: "28", time: "Mon, 18 Feb 2001 21:04:05 GMT", type: "Sell"}]]);
});

app.post('/transaction/add', (req, res) => {
    
    console.log("Transaction added:", req.body);
    res.send({msg: "Successfully added"});
});




