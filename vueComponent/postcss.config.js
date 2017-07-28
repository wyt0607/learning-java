module.exports = {
    plugins: [
        require('postcss-cssnext')({
            browsers: 'last 3 versions',
            warnForDuplicates: false
        }),
        require('postcss-pxtorem')({
            rootValue: 16,
            propList: ['*'],
            mediaQuery: true,
            replace: true,
        })
    ]
}