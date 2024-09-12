const express = require('express');
const cors = require('cors');
const http = require('http');
const socketIo = require('socket.io');

const app = express();

app.use(cors());
app.use(express.json());

const server = http.createServer(app);
const io = new socketIo.Server({
    cors: {
        origin: '*'
    }
});

const stockData = {
    name: 'Zomato',
    initialPrice: 100,
};

const history = [];

function generateRandomChange() {
    return Math.floor(Math.random() * 100);
}

function sendRealTimeUpdates() {
    const priceChange = generateRandomChange();
    const newPrice = stockData.initialPrice + priceChange;

    io.emit('priceUpdate', {
        timestamp: Date.now(),
        priceChange,
        newPrice,
    });
    stockData.initialPrice = newPrice;

    history.push({
        timestamp: Date.now(),
        priceChange,
        newPrice,
    });
}

io.on('connection', (socket) => {
    console.log('A user connected');

    socket.emit('initialData', stockData);
    socket.emit('historyUpdate', history);

    socket.on('disconnect', () => {
        console.log('User disconnected');
    });
});
setInterval(sendRealTimeUpdates, 5000);


io.attach(server);

app.get("/", (req, res) => {
    res.json({
        msg: "Hello--Connected"
    });
});

server.listen(8000, () => {
    console.log("Running................");
});
