const path = require('path')
const webpack = require('webpack')

function resolve (dir) {
  return path.join(__dirname, '.', dir)
}

module.exports = {
  lintOnSave: false,

  pages: {
    index: {
      // entry for the page
      entry: 'src/main.js',
      // the source template
      template: 'index.html',
      // output as dist/index.html
      filename: 'index.html',
      // when using title option,
      // template title tag needs to be <title><%= htmlWebpackPlugin.options.title %></title>
      title: 'Resume Parser',
      // chunks to include on this page, by default includes
      // extracted common chunks and vendor chunks.
      chunks: ['chunk-vendors', 'chunk-common', 'index'],
    },
  },
  configureWebpack: config => {
    if (process.env.NODE_ENV === 'production') {
      return {
        resolve: {
          alias: {
            'vue$': 'vue/dist/vue.min.js',
            '@': resolve('src'),
            'src': resolve('src'),
            'assets': resolve('src/assets'),
            'components': resolve('src/components'),
            'vuex-store': resolve('src/store')
          }
        },
        plugins:[
          new webpack.DefinePlugin({
            'process.env': {
              NODE_ENV: '"production"'
            }
          })
        ]
        //optimization: {
          //splitChunks: {
            //minSize: 10000,
            //maxSize: 250000
          //}
        //}
      }
    } else {
      return {
        resolve: {
          alias: {
            'vue$': 'vue/dist/vue.esm.js',
            '@': resolve('src'),
            'src': resolve('src'),
            'assets': resolve('src/assets'),
            'components': resolve('src/components'),
            'vuex-store': resolve('src/store')
          }
        }
      }
    }
  },
  assetsDir: 'assets',
  productionSourceMap: false
}
