<template>
  <div>
    <!-- GBN -->

    <div id="titlebar" class="single title_container">
      <nav class="menu">
        <ul>
          <li>
            <router-link to="/mainHome" active-class="current">HOME</router-link>
          </li>
          <li>
            <router-link to="/board/LEVEL_1" active-class="current">LEVEL 1</router-link>
          </li>
          <li>
            <router-link to="/board/LEVEL_2" active-class="current">LEVEL 2</router-link>
          </li>
          <li>
            <router-link to="/board/LEVEL_3" active-class="current">LEVEL 3</router-link>
          </li>
          <li>
            <router-link to="/board/LEVEL_4" active-class="current">LEVEL 4</router-link>
          </li>
          <li>
            <router-link to="/board/LEVEL_5" active-class="current">LEVEL 5</router-link>
          </li>
          <li>
            <router-link to="/board/BLOCK" active-class="current">BLOCK LECTURE</router-link>
          </li>
        </ul>
      </nav>
    </div>

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
  data() {
    return {
      activeClass: 'current'
    }
  },
  mounted() {
  },
  methods: {}
}
</script>

<style scoped>

</style>