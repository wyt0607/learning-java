const webpack = require('webpack')
const base = require('./webpack.base.config')
const HTMLPlugin = require('html-webpack-plugin')

const config = Object.assign({}, base, {
    plugins: (base.plugins || []).concat([
        // extract vendor chunks for better caching
        new webpack.optimize.CommonsChunkPlugin({
            name: 'index2',
            filename: 'client/client-vendor-bundle.js'
        }),
        // generate output HTML
        new HTMLPlugin({
            template: 'src/index.template.html'
        })
    ])
})

if (process.env.NODE_ENV === 'production') {
    config.plugins.push(
        new webpack.DefinePlugin({
            'process.env': {
                NODE_ENV: '"production"'
            }
        }),
        // this is needed in webpack 2 for minifying CSS
        new webpack.LoaderOptionsPlugin({
            minimize: true
        }),
        // minify JS
        new webpack.optimize.UglifyJsPlugin({
            compress: {
                warnings: false
            }
        })
    )
}

module.exports = config
