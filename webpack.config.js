var path = require('path');

module.exports = {
    entry: './src/main/js/main.js',
    devtool: 'sourcemaps',
    cache: true,
    output: {
        path: __dirname,
        filename: './src/main/resources/static/built/bundle.js'
    },
    module: {
        loaders: [
            {
                test: path.join(__dirname, '.'),
                exclude: /(node_modules)/,
                loader: 'babel-loader',
                query: {
                    cacheDirectory: true,
                    presets: ['env', 'react'],
                    plugins: ['transform-object-rest-spread', 'transform-class-properties']
                }
            }
        ]
    }
};