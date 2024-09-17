const http = require('http');
const fs = require('fs');
const os = require('os');

// Function to get system information
function getSystemInfo() {
    return {
        HostName: os.hostname(),
        OperatingSystem: os.platform(),
        Architecture: os.arch(),
        OSRelease: os.release(),
        Uptime: os.uptime(),
        NumCpuCores: os.cpus().length,
        TotalMemory: os.totalmem() / (1024 ** 3),
        FreeMemory: os.freemem() / (1024 ** 3),
        CurrentWorkingDirectory: process.cwd(),
    };
}

// Function to write system information to a JSON file
// data-->Json formate convert to String then add to filename
function writeJsonToFile(data, filename) {
    fs.writeFileSync(filename, JSON.stringify(data, null, 4));
}

// Create and write system info to json file
const systemInfo = getSystemInfo();
writeJsonToFile(systemInfo, 'system_info.json');

const port = 8000;

// Create an HTTP server
const server = http.createServer((req, res) => {
    if (req.url === '/') {
        try {
            const data = fs.readFileSync('system_info.json', 'utf8');
            // created Js object that store system_info-content
            const systemInfo = JSON.parse(data);
            // Then we will use JSON.stringfy to covert this object data to string

            const response = `
                Hello, my name is Neer!\n\n
                Here is my system information:\n\n
                ${JSON.stringify(systemInfo, null, 4)}
            `;

            res.writeHead(200, { 'Content-Type': 'text/plain' });
            res.end(response);
        } catch (err) {
            res.writeHead(404, { 'Content-Type': 'text/plain' });
            res.end('File not found.');
        }
    } else {
        res.writeHead(404, { 'Content-Type': 'text/plain' });
        res.end('Endpoint not found.');
    }
});

// Start the server
server.listen(port, () => {
    console.log(`Server is running on http://localhost:${port}/`);
});
