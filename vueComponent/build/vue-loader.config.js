const {plugins} = require('../postcss.config')


module.exports = {
    extractCSS: process.env.NODE_ENV === 'production',
    preserveWhitespace: false,
    postcss: plugins
}
