<template>
    <v-container>
        <v-row justify="space-around">
            <v-col sm="5">
                <div class="title mb-3">User profile</div>
                <v-row justify="space-between">
                    <v-col sm="3" class="px-1">
                        <v-img :src="profile.userpic"></v-img>
                    </v-col>
                    <v-col class="px-1">
                        <v-col>
                            <v-row>{{profile.name}}</v-row>
                            <v-row>{{profile.locale}}</v-row>
                            <v-row>{{profile.gender}}</v-row>
                            <v-row>{{profile.lastVisit}}</v-row>
                            <v-row>{{profile.subscriptions && profile.subscriptions.length}} subscriptions</v-row>
                            <router-link v-if="isMyProfile" :to="`/subscriptions/${profile.id}`">
                                {{profile.subscribers && profile.subscribers.length}} subscribers
                            </router-link>
                            <v-row v-else>{{profile.subscribers && profile.subscribers.length}} subscribers</v-row>
                            </v-col>
                    </v-col>
                    <div v-if="!isMyProfile">
                        <v-btn @click="changeSubscription">
                            {{isSubscribed ? 'Unsubscribe' : 'Subscribe'}}
                        </v-btn>
                        <v-btn @click="changeContact">
                            {{isInContacts ? 'Remove from contacts' : 'Add contact'}}
                        </v-btn>
                    </div>
                </v-row>
            </v-col>
        </v-row>
    </v-container>
</template>

<script>
    import profileApi from 'api/profile'
    export default {
        name: 'Profile',
        data() {
            return {
                profile: {}
            }
        },
        computed: {
            isMyProfile() {
                return !this.$route.params.id || this.$route.params.id === this.$store.state.profile.id
            },
            isSubscribed() {
                return this.profile.subscribers && this.profile.subscribers.find(subscription => {
                    return subscription.subscriber === this.$store.state.profile.id
                })
            },
            isInContacts() {
                return this.$store.state.profile.contacts &&
                    this.$store.state.profile.contacts.filter(contact => contact.receiver === this.profile.id).length > 0
            }
        },
        watch: {
            '$route'() {
                this.updateProfile()
            }
        },
        methods: {
            async changeSubscription() {
                const data = await profileApi.changeSubscription(this.profile.id)
                this.profile = await data.json()
            },
            async changeContact() {
                const data = await profileApi.changeContact(this.profile.id)
                this.$store.state.profile = await data.json()
            },
            async updateProfile() {
                const id = this.$route.params.id || this.$store.state.profile.id
                const data = await profileApi.get(id)
                this.profile = await data.json()

                this.$forceUpdate()
            }
        },
        beforeMount() {
            this.updateProfile()
        }
    }
</script>

<style scoped>
    img {
        max-width: 100%;
        height: auto;
    }
</style>