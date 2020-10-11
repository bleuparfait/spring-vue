<template>
    <v-list-item>
        <v-list-item-content>
            <v-list-item-title>
                <user-link :user="comment.author"></user-link>
            </v-list-item-title>
            <v-list-item-subtitle>{{comment.text}}</v-list-item-subtitle>
        </v-list-item-content>
    </v-list-item>
</template>

<script>
    import UserLink from 'components/UserLink.vue'
    export default {
        name: 'CommentItem',
        props: ['comment'],
        components: { UserLink }
    }
</script>