import Vue from 'vue'
import VueRouter from 'vue-router'
import Parser from '../components/Parser'

Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    name: 'Parser',
    component: Parser
  }
]

const router = new VueRouter({
  mode: 'history',
  // base: process.env.BASE_URL,
  base: __dirname,
  routes
})

export default router
