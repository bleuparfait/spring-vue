<template>
    <v-row>
    <v-col>
        <v-card>
            <v-card-title>
                <user-link :user="message.author" size="48"></user-link>
            </v-card-title>
            <v-card-text>{{ message.text }} </v-card-text>
            <media v-if="message.link" :message="message"></media>
            <v-card-actions>
                <v-btn @click="edit" text>Edit</v-btn>
                <v-btn @click="del" text>Delete</v-btn>
            </v-card-actions>
            <comment-list :comments="message.comments" :message-id="message.id"></comment-list>
        </v-card>
    </v-col>
    </v-row>
</template>

<script>
    import { mapActions } from 'vuex'
    import Media from 'components/media/Media.vue'
    import CommentList from '../comments/CommentList.vue'
    import UserLink from 'components/UserLink.vue'
    export default {
        props: ['message', 'editMessage'],
        components: { Media, CommentList, UserLink },
        methods: {
            ...mapActions(['removeMessageAction']),
            edit() {
                this.editMessage(this.message)
            },
            del() {
                this.removeMessageAction(this.message)
            }
        }
    }
</script>

<style>

</style>