import axios from 'axios'

const HOSTNAME = document.location.host
const SERVER = 'http://' + HOSTNAME
const SERVER_URL = SERVER + '/api/'
const PARSE_URL = SERVER + '/api/rparse/v1/'
const FILES_URL = SERVER + '/api/files/v1/'

const instance = axios.create({
  baseURL: SERVER,
  timeout: false,
  params: {}
})

instance.interceptors.request.use(function (config) {
  config.headers.common['Access-Control-Allow-Origin'] = '*'

  return config
}, function (error) {
  console.lor(error)
  return Promise.reject(error)
})

instance.interceptors.response.use((response) => response, (error) => {
  console.log(error.config)
  return Promise.reject(error)
})

export default {
  postDataParser (action, data) {
    console.log(data)
    let url = `${PARSE_URL}`
    url += action + '.json'
    return instance.post(url, data)
  }
}
