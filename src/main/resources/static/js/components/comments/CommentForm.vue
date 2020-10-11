<template>
        <v-col cols="3">
        <v-text-field label="Add comment" v-model="text" @keydown.enter="save" />
        <v-btn @click="save">Add</v-btn>
        </v-col>
</template>

<script>
    import { mapActions } from 'vuex'

    export default {
        name: 'CommentForm',
        props: ['messageId'],
        data() {
            return {
                text: ''
            }
        },
        methods: {
            ...mapActions(['addCommentAction']),
            async save() {
                await this.addCommentAction({
                    text: this.text,
                    message: {
                        id: this.messageId
                    }
                })
                this.text = ''
            }
        }
    }
</script>