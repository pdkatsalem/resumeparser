'use strict'
const merge = require('webpack-merge')
const prodEnv = require('./prod.env')

module.exports = merge(prodEnv, {
  mode: 'development',
  NODE_ENV: '"production"'
})
