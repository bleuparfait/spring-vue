import Vue from 'vue'
import Vuetify from 'vuetify'
import 'vuetify/dist/vuetify.min.css'

Vue.use(Vuetify)

export default new Vuetify({
    theme: {
        dark: true,
        themes: {
            light: {
                primary: '#3f51b5',
                secondary: '#e91e63',
                accent: '#ffc107',
                error: '#f44336',
                warning: '#ff9800',
                info: '#00bcd4',
                success: '#8bc34a'
            },
            dark: {
                primary: '#795548',
                secondary: '#ff9800',
                accent: '#cddc39',
                error: '#f44336',
                warning: '#ff5722',
                info: '#9c27b0',
                success: '#03a9f4'
            }
        }
    }
})