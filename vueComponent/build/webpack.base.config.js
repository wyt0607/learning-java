const path = require('path')
const ExtractTextPlugin = require("extract-text-webpack-plugin");
const isProd = process.env.NODE_ENV === 'production'


const config = {
    devtool: '#source-map',
    entry: {
        index: './src/client-entry.js',
        index2: ['vue', 'vue-router', 'vuex', 'whatwg-fetch', "es6-promise"]
    },
    output: {
        path: path.resolve(__dirname, '../dist'),
        publicPath: '/dist/',
        filename: 'client/client-bundle.js'
    },
    module: {
        noParse: /es6-promise\.js$/, // avoid webpack shimming process
        rules: [
            {
                test: /\.css$/,
                loader: ExtractTextPlugin.extract({
                    fallback: "style-loader",
                    use: "css-loader!postcss-loader"
                })
            },
            {
                test: /\.scss$/,
                loader: ExtractTextPlugin.extract({
                    fallback: "style-loader",
                    use: "css-loader!postcss-loader!sass-loader"
                })
            },
            {
                test: /\.js$/,
                loader: 'babel-loader',
                exclude: /node_modules/
            },
            {
                test: /\.(png|jpg|gif|jpeg)$/,
                loader: 'url-loader',
                query: {
                    limit: 10000,
                    name: 'images/[name].[ext]?[hash]'
                }
            },
            {
                test: /\.(eot|svg|ttf|woff|woff2|otf)$/,
                loader: 'url-loader',
                query: {
                    limit: 10000,
                    name: 'fonts/[name].[ext]?[hash]'
                }
            }
        ]
    },
    plugins: [
        new ExtractTextPlugin("css/style.css")
    ],
    resolve: {
        alias: {
            'vue$': 'vue/dist/vue.common.js'
        }
    },
    performance: {
        hints: process.env.NODE_ENV === 'production' ? 'warning' : false
    },
    devServer: {
        historyApiFallback: true,
        noInfo: true
    },
}


if (isProd) {
    config.module.rules.push({
        test: /\.vue$/,
        loader: 'vue-loader',
        options: {
            preserveWhitespace: false,
            loaders: {
                sass: ExtractTextPlugin.extract({
                    use: 'css-loader!postcss-loader!sass-loader',
                    fallback: 'vue-style-loader'
                })
            }
        }
    })
} else {
    config.module.rules.push({
        test: /\.vue$/,
        loader: 'vue-loader'
    })
}

module.exports = config