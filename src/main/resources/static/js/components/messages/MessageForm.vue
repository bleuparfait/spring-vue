<template>
    <v-container>
        <v-text-field label="New message" v-model="text" @keydown.enter="save" />
        <v-btn @click="save">Save</v-btn>
    </v-container>
</template>

<script>
    import { mapActions } from 'vuex'
    import * as Sentry from "@sentry/browser"

    export default {
        props: ['messageAttr'],
            data() {
                return {
                    text: '',
                    id: null
                }
            },
            watch: {
                messageAttr(newVal, oldVal) {
                    this.text = newVal.text
                    this.id = newVal.id
                }
            },
            methods: {
                ...mapActions(['addMessageAction', 'updateMessageAction']),
                save() {
                    const message = {
                        id: this.id,
                        text: this.text
                    }
                    if (this.id) {
                        this.updateMessageAction({ id: this.id, text: this.text })
                    } else {
                        this.addMessageAction({ text: this.text})
                    }
                    this.text = ''
                    this.id = null
                }
            }
        }
</script>

<style>

</style>