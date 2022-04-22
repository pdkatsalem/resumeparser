import Vue from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'
import './registerServiceWorker'
import api from './utils/apis'

Vue.config.productionTip = false

router.beforeEach((to, from, next) => {
  store.commit('setLoading', true)
  next()
})

router.afterEach((to, from) => {
  store.commit('setLoading', false)
})

Vue.prototype.api = api

new Vue({
  router,
  store,
  render: h => h(App)
}).$mount('#app')
