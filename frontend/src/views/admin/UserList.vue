<template>
  <div class="sixteen columns">
    <!-- search box -->
    <div class="list-search">
      <table>
        <tbody>
        <tr>
          <td>
            <input v-model.trim="searchData.name" @keyup.enter="search" type="text" placeholder="이름 또는 이메일을 검색하세요."
                   style="width:200px">
          </td>
          <td>
            <button @click.stop="search"><i class="fa fa-search"></i></button>
          </td>
        </tr>
        </tbody>
      </table>
    </div>
    <!--// search box -->

    <!-- info -->
    <div class="info_box">
      <span class="total_count">
        총
        <strong>{{ totalCount }}</strong>
        개
      </span>
      <button class="button" @click="goWrite">등록</button>
    </div>
    <!-- //info -->

    <!--table-->
    <div class="table_container">
      <table class="manage-table resumes responsive-table">
        <colgroup>
          <col style="width :100px"/>
          <col/>
          <col style="width :250px"/>
          <col/>
          <col style="width :150px"/>
        </colgroup>
        <thead>
        <tr>
          <th>No.</th>
          <th>사용자 ID</th>
          <th>이름</th>
          <th>이메일</th>
          <th>활성화 여부</th>
        </tr>
        </thead>
        <tbody>
        <tr v-for="(user,index) in userList" v-bind:key="user.userId" @click.stop="goView(user.userId)">
          <td>{{ (searchData.page - 1) * searchData.size + index + 1 }}</td>
          <td class="title"><a>{{ user.userId }}</a></td>
          <td>{{ user.name }}</td>
          <td>{{ user.email }}</td>
          <td>{{ user.activeYn }}</td>
        </tr>
        </tbody>
      </table>
    </div>
    <!--//table-->

    <!-- Pagination-->
    <pagination-component
        :perPage="searchData.size"
        :total="totalCount"
        :value="searchData.page"
        @input="changePage"
    >
    </pagination-component>


  </div>


</template>

<script>

import PaginationComponent from "@/components/common/PaginationComponent.vue";
import {mapGetters} from "vuex";
import UserService from "@/services/UserService";

export default {
  name: 'AdminUserList',
  components: {
    PaginationComponent
  },
  data() {
    return {
      totalCount: 0,
      userList: [],
      defaultSearchData: {},
      searchData: {
        name: '',
        page: 1,
        size: 10
      }
    };
  },
  computed: {
    ...mapGetters({
      isAdmin: 'isAdmin',
      getUser: 'getUser'
    })
  },
  mounted() {
    this.defaultSearchData = {...this.searchData};
    this.init();
  },
  methods: {
    init() {
      const query = this.$route.query ? this.$route.query : {};
      this.searchData.name = this.getValue(query.name, '');
      this.loadPage();
    },
    loadPage() {
      const params = {...this.searchData};
      params.page = params.page - 1;

      // console.log('loadPage', params);
      UserService.getList(params).then(({data}) => {
        this.userList = data.content;
        this.totalCount = data.totalElements;
      });

    },
    makeQueryString(page = 1) {
      const query = {...this.searchData, ...{page}};
      return query;
    },
    search() {
      this.$router.push({query: this.makeQueryString()});
    },
    changePage(page) {
      const query = this.makeQueryString(page);
      this.$router.push({query: query});
    },
    goView() {
      //this.$router.push('/board/' + this.type + '/' + this.semesterId + '/view/' + boardId);
    },
    goWrite() {
      // this.$router.push('/board/' + this.type + '/' + this.semesterId + '/write');
    }
  },
  watch: {
    $route() {
      this.init();
    }
  }
}
</script>

<style scoped>


</style>