import Vue from 'vue'
import VueRouter from 'vue-router'
import MessageList from 'components/messages/MessageList.vue'
import Auth from 'pages/Auth.vue'
import Profile from 'pages/Profile.vue'
import Subscriptions from 'pages/Subscriptions.vue'
import Contacts from 'pages/Contacts.vue'

Vue.use(VueRouter)

const routes = [
    { path: '/', component: MessageList },
    { path: '/auth', component: Auth },
    { path: '/user/:id?', component: Profile },
    { path: '/subscriptions/:id', component: Subscriptions },
    { path: '/contacts/:id?', component: Contacts },
    { path: '*', component: MessageList },
]

export default new VueRouter({
    mode: 'history',
    routes
})