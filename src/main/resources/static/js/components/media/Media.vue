<template>
    <v-card outlined="true" rounded="0">
        <v-col v-if="type === 'href'" sm="3">
            <v-img v-if="message.linkCover" :src="message.linkCover" aspect-ratio="2.75">
            </v-img>
            <v-card-title><a :href="message.link">{{message.linkTitle || message.link}}</a></v-card-title>
            <v-card-text v-if="message.linkDescription">
                {{message.linkDescription}}
                <a :href="message.link">{{message.link || message.linkTitle}}</a>
            </v-card-text>
        </v-col>
        <v-col v-if="type === 'image'" sm="3">
            <a :href="message.link">
                <v-img v-if="message.linkCover" :src="message.linkCover" aspect-ratio="2.75"></v-img>
                {{message.link}}
            </a>
        </v-col>
        <v-col v-if="type === 'youtube'" sm="3">
            <you-tube :src="message.link"></you-tube>
        </v-col>
    </v-card>
</template>

<script>
    import YouTube from './YouTube.vue'
    export default {
        name: 'Media',
        components: { YouTube },
        props: ['message'],
        data() {
            return {
                type: 'href'
            }
        },
        beforeMount() {
            if (this.message.link.indexOf('youtu') > -1) {
                this.type = 'youtube'
            } else if (this.message.link.match(/\.(jpeg|jpg|gif|png)$/) !== null) {
                this.type = 'image'
            } else {
                this.type = 'href'
            }
        }
    }
</script>

<style>

</style>