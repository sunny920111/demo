<template>
  <div>
    <!-- GBN -->

    <div id="titlebar" class="single title_container">
      <nav class="menu">
        <ul>
          <li>
            <router-link to="/mainHome" active-class="current">HOME</router-link>
          </li>
          <li :hidden="openMenu('LEVEL_1')">
            <router-link to="/board/LEVEL_1" :class="{ 'current': $route.path.startsWith('/board/LEVEL_1') }">LEVEL 1
            </router-link>
          </li>
          <li :hidden="openMenu('LEVEL_2')">
            <router-link to="/board/LEVEL_2" :class="{ 'current': $route.path.startsWith('/board/LEVEL_2') }">LEVEL 2
            </router-link>
          </li>
          <li :hidden="openMenu('LEVEL_3')">
            <router-link to="/board/LEVEL_3" :class="{ 'current': $route.path.startsWith('/board/LEVEL_3') }">LEVEL 3
            </router-link>
          </li>
          <li :hidden="openMenu('LEVEL_4')">
            <router-link to="/board/LEVEL_4" :class="{ 'current': $route.path.startsWith('/board/LEVEL_4') }">LEVEL 4
            </router-link>
          </li>
          <li :hidden="openMenu('LEVEL_5')">
            <router-link to="/board/LEVEL_5" :class="{ 'current': $route.path.startsWith('/board/LEVEL_5') }">LEVEL 5
            </router-link>
          </li>
          <li>
            <router-link to="/board/BLOCK" :class="{ 'current': $route.path.startsWith('/board/BLOCK') }">BLOCK
              LECTURE
            </router-link>
          </li>
          <li>
            <router-link to="/admin" :class="{ 'current': $route.path.startsWith('/myProfile') }">My Profile
            </router-link>
          </li>
          <li :hidden="openAdminMenu()">
            <router-link to="/admin/users" :class="{ 'current': $route.path.startsWith('/admin') }">ADMIN
            </router-link>
          </li>
        </ul>
      </nav>
    </div>

    <!-- 권한별 게시판 목록 -->
    <div style="overflow-y: scroll; height: 790px;">
      <div class="container">
        <router-view :key="$route.params ? $route.params.type : ''"></router-view>
      </div>
    </div>
  </div>
</template>

<script>


import {mapGetters} from "vuex";

export default {
  name: 'UserLayout',
  computed: {
    ...mapGetters({
      getIsAuth: 'getIsAuth',
      getUser: 'getUser'
    })
  },
  mounted() {
    console.log('UserLayout')
    console.log(this.$route)
  },
  methods: {
    openMenu(menu_id) {

      for (let i = 0; i < this.getUser.authorities.length; i++) {
        let auth = this.getUser.authorities[i];
        if (auth.authority.indexOf(menu_id) > -1
            || auth.authority.indexOf('SYSTEM_ADMIN') > -1) {
          return false;
        }
      }

      return true;
    },
    openAdminMenu() {
      for (let i = 0; i < this.getUser.authorities.length; i++) {
        let auth = this.getUser.authorities[i];
        if (auth.authority.indexOf('SYSTEM_ADMIN') > -1) {
          return false;
        }
      }
      return true;
    }

  },

}
</script>

<style scoped>

</style>