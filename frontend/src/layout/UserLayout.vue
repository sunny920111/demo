<template>
  <div>
    <!-- GBN -->
    <div id="titlebar" class="single"> User Layout</div>
    <!-- 권한별 게시판 목록 -->
    <div style="overflow-y: scroll; height: 790px;">
      <div class="container">
        <router-view></router-view>
      </div>
    </div>
  </div>
</template>

<script>


export default {
  name: 'UserLayout',
}
</script>

<style scoped>

</style>