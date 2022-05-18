const path = require('path');

module.exports = {
    entry: './build/parser.js',
    output: {
        path: path.resolve(__dirname, 'target/classes'),
        filename: 'parser-bundle.js',
        libraryTarget: 'module'
    },
    resolve: {
        fallback: {
          "fs": false,
          "tls": false,
          "net": false,
          "path": false,
          "zlib": false,
          "http": false,
          "util": false,
          "os": false,
          "https": false,
          "stream": false,
          "crypto": false,
          "crypto-browserify": false,
        }
    },
    mode: "production",
    experiments: {
        outputModule: true
    }
};
