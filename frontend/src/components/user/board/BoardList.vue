<template>
  <div>
    Board List
    <button @click="goMain">goMain</button>
  </div>
</template>

<script>
export default {
  name: 'BoardList',
  methods: {
    goMain() {
      this.$router.push('/mainHome');
    }
  }
}
</script>

<style scoped>

</style>