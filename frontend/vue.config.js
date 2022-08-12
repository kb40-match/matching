module.exports = {
  transpileDependencies: [
    'vuetify'
  ],
  devServer: {
    proxy: 'http://matching.169.56.100.104.nip.io/match'
  }
}
