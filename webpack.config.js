const path = require('path');

module.exports = {
    entry: './src/main/js/Parser.js',
    output: {
        path: path.resolve(__dirname, 'target/classes'),
        filename: 'ecmascript-parser-bundle.js',
        libraryTarget: 'module'
    },
    experiments: {
        outputModule: true
    }
};
