<template>
    <v-container>
        <v-row justify="space-around">
            <v-col>
                <v-list>
                    <v-list-item v-for="item in contacts">
                    <div v-if="profile.id === item.sender.id">
                        <user-link :user="item.receiver" size="24"></user-link>
                    </div>
                    <div v-if="profile.id === item.receiver.id">
                        <user-link :user="item.sender" size="24"></user-link>
                        <v-btn @click="changeContactStatus(item.sender.id)">{{item.active ? "Dismiss" : "Approve"}}</v-btn>
                    </div>
                    </v-list-item>
                </v-list>
            </v-col>
        </v-row>
    </v-container>
</template>

<script>
    import profileApi from 'api/profile'
    import UserLink from 'components/UserLink.vue'

    export default {
        name: 'Contacts',
        components: { UserLink },
        data() {
            return {
            contacts: [],
            profile: this.$store.state.profile
            }
        },
        methods: {
            async changeContactStatus(senderId) {
                await profileApi.changeContactStatus(senderId)
                const contactIndex = this.contacts.findIndex(item => item.sender.id === senderId)
                const contact = this.contacts[contactIndex]
                this.contacts = [
                    ...this.contacts.slice(0, contactIndex),
                    {
                        ...contact,
                        active: !contact.active
                    },
                    ...this.contacts.slice(contactIndex + 1)
                ]
            }
        },
        async beforeMount() {
            const resp = await profileApi.contactList(this.$store.state.profile.id)
            this.contacts = await resp.json()
        }
    }
</script>