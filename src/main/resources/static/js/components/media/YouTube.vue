<template>
    <iframe
        width="560"
        height="315"
        :src="targetSrc"
        frameborder="0"
        allow="accelerometer; autoplay; encrypted-media; gyroscope; picture-in-picture"
        allowfullscreen>
    </iframe>
</template>

<script>
    export default {
        name: 'YouTube',
        props: ['src'],
        computed: {
            targetSrc() {
                let parts = ['']
                if (this.src.includes('v=')) {
                    parts = this.src.split('v=')
                    parts[parts.length - 1] = parts[parts.length - 1].replace('&', '?')
                }
                else {
                    parts = this.src.split('/')
                }
                return 'https://www.youtube.com/embed/' + parts[parts.length - 1]
            }
        }
    }
</script>

<style>

</style>