const path = require('path');

module.exports = {
    entry: './src/main/js/Parser.js',
    output: {
        path: path.resolve(__dirname, 'target/dist'),
        filename: 'bundle.js',
        libraryTarget: 'module'
    },
    experiments: {
        outputModule: true
    }
};