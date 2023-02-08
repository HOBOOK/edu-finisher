import { createApp } from 'vue'
import App from './App.vue'
import vuetify from './plugins/vuetify'
import axios from 'axios'
import { loadFonts } from './plugins/webfontloader'

loadFonts()



const app = createApp(App)
app.use(vuetify)
app.config.globalProperties.$axios = axios

app.mount('#app')

