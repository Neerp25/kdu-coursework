const path = require('path');

// Function to extract file information
function extractFileInfo(filePath) {
    const extension = path.extname(filePath);
    const baseName = path.basename(filePath);
    const directory = path.dirname(filePath);

    return {
        extension,
        baseName,
        directory,
    };
}

// Function to process an array of file paths
// Iterate over each filepath apply extractFileInfo Function
function processFilePaths(filePaths) {
    return filePaths.map(filePath => extractFileInfo(filePath));
}

const filePaths = [
    'dir1/dir2/file1.txt',
    'dir1/dir3/file2.txt',
    'dir1/dir3/file3.md',
    'dir4/file4.jpg',
    'dir4/file5.pdf',
];

const result = processFilePaths(filePaths);
console.log(result);