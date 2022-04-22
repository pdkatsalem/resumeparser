import Vue from 'vue'
import Vuex from 'vuex'
import createPersistedState from 'vuex-persistedstate'

Vue.use(Vuex)

const state = {
  isLoading: false,
  hostname: '',
  usertoken: null
}

const isLoading = state => state.isLoading
const currentHost = state => 'http://' + state.hostname

const mutations = {
  setUserToken: function (state, token) {
    state.usertoken = token
  },
  setLoading: function (state, loading) {
    state.isLoading = loading
  }
}

const store = new Vuex.Store({
  plugins: [createPersistedState({ storage: window.sessionStorage })],
  getters: {
    isLoading,
    currentHost
  },
  state,
  mutations
})

export default store
