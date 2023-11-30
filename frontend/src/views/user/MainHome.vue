<template>
  <div>
    Main Home
    <button @click="goBoard">goBoard</button>
  </div>
</template>

<script>
export default {
  name: 'MainHome',
  methods: {
    goBoard() {
      this.$router.push('/board');
    }
  }
}
</script>
<style scoped>

</style>