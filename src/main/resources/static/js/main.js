import Vue from 'vue'
import vuetify from 'plugins/vuetify'
import '@babel/polyfill'
import 'api/resource'
import router from 'router/router'
import App from 'pages/App.vue'
import store from 'store/store'
import { connect } from './util/ws'
import * as Sentry from "@sentry/browser"
import { Vue as VueIntegration } from "@sentry/integrations"
import { Integrations } from '@sentry/tracing'

Sentry.init({
  dsn: "https://b252fe7ed1334b56889189de0de8675d@o440786.ingest.sentry.io/5410388",
  integrations: [
    new VueIntegration({
      Vue,
      tracing: true
    }),
    new Integrations.BrowserTracing()
  ],
  tracesSampleRate: 1
})

Sentry.configureScope(scope =>
    scope.setUser({
        id: profile && profile.id,
        username: profile && profile.name
    })
)

if (profile) {
    connect()
}

Vue.use(vuetify)

new Vue({
    el: '#app',
    vuetify,
    store,
    router,
    render: a => a(App),
})
