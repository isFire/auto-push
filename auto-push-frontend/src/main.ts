import { createApp } from 'vue'
import App from './App.vue'
import Antd from 'ant-design-vue'
import 'ant-design-vue/dist/antd.dark.css'
import './style.css'
import './assets/styles/index.css'
import router from './router'

const app = createApp(App)
app.use(Antd)
app.use(router)
app.mount('#app')
