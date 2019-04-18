module.exports = {
  devServer: {
    port: 8090,
    proxy: {
      '/': {
        target: 'http://localhost:8082',
        changeOrigin: true,
        ws: false
      }
    }
  }
}