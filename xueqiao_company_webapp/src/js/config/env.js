// ljq 20181015
let baseUrl = ''
if (process.env.NODE_ENV === 'development') {
  baseUrl = 'https://devwww.xueqiao.cn/'
  // baseUrl = 'http://localhost:9000/'
} else if (process.env.NODE_ENV === 'production') {
  baseUrl = '/'
}

export default {
  baseUrl
}
