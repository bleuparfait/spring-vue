<template>
    <v-app>
        <v-navigation-drawer
              v-model="drawer"
              app
              clipped
            >
              <v-list dense>
                <v-list-item link v-if="profile"
                                  :disabled="$route.path === '/user'"
                                  @click="showProfile">
                  <v-list-item-action>
                    <v-icon>mdi-account</v-icon>
                  </v-list-item-action>
                  <v-list-item-content>
                    <v-list-item-title>Account</v-list-item-title>
                  </v-list-item-content>
                </v-list-item>
                <v-list-item link v-if="profile"
                                  :disabled="$route.path === '/'"
                                  @click="showMessages">
                  <v-list-item-action>
                    <v-icon>mdi-message</v-icon>
                  </v-list-item-action>
                  <v-list-item-content>
                    <v-list-item-title>Messages</v-list-item-title>
                  </v-list-item-content>
                </v-list-item>
                <v-list-item link v-if="profile"
                                  :disabled="$route.path === `/contacts/${this.$store.state.profile.id}`"
                                  @click="showContacts">
                  <v-list-item-action>
                    <v-icon>mdi-contacts</v-icon>
                  </v-list-item-action>
                  <v-list-item-content>
                    <v-list-item-title>Contacts</v-list-item-title>
                  </v-list-item-content>
                </v-list-item>
                <v-list-item link>
                  <v-list-item-action>
                    <v-icon>mdi-cog</v-icon>
                  </v-list-item-action>
                  <v-list-item-content>
                    <v-list-item-title>Settings</v-list-item-title>
                  </v-list-item-content>
                </v-list-item>
              </v-list>
        </v-navigation-drawer>

        <v-app-bar app clipped-left :collapse="false" :collapse-on-scroll="false">
        <v-app-bar-nav-icon @click.stop="drawer = !drawer"></v-app-bar-nav-icon>
            <v-toolbar-title>App</v-toolbar-title>
            <v-spacer></v-spacer>
            <v-btn href="/logout">
                <v-icon>mdi-exit-to-app</v-icon>
                Logout
            </v-btn>
        </v-app-bar>
        <v-main>

            <router-view></router-view>
        </v-main>
    </v-app>
</template>

<script>
    import { mapState, mapMutations } from 'vuex'
    import { addHandler } from 'util/ws'
    export default {
        data() {
            return { drawer: null }
        },
        computed: mapState(['profile']),
        methods: {
            ...mapMutations([
                        'addMessageMutation',
                        'updateMessageMutation',
                        'removeMessageMutation',
                        'addCommentMutation']),
            showMessages() {
                this.$router.push('/')
            },
            showProfile() {
                this.$router.push('/user')
            },
            showContacts() {
                this.$router.push(`/contacts/${this.$store.state.profile.id}`)
            }
        },
        created() {
            addHandler(data => {
                if (data.objectType === 'MESSAGE') {
                    switch (data.eventType) {
                        case 'CREATE':
                            this.addMessageMutation(data.body)
                            break
                        case 'UPDATE':
                            this.updateMessageMutation(data.body)
                            break
                        case 'REMOVE':
                            this.removeMessageMutation(data.body)
                            break
                        default:
                            console.error('java EventType error: "${data.eventType}"')
                    }
                } else if (data.objectType === 'COMMENT') {
                    switch (data.eventType) {
                        case 'CREATE':
                            this.addCommentMutation(data.body)
                            break
                        default:
                            console.error('java EventType error: "${data.eventType}"')
                    }
                } else {
                    console.error('java ObjectType error: "${data.objectType}"')
                }
            })
        },
        beforeMount() {
            if (!this.profile) {
                this.$router.replace('/auth')
            }
        },
    }
</script>

<style>
</style>