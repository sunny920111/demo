<template>
  <div class="sixteen columns">
    <!-- search box -->
    <div class="list-search">
      <table>
        <tbody>
        <tr>
          <td>
            <input v-model.trim="searchData.title" @keyup.enter="search" type="text" placeholder="제목">
          </td>
          <td>
            <input v-model.trim="searchData.regName" @keyup.enter="search" type="text" placeholder="작성자">
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
      <span>
        총
        <strong>{{ totalCount }}</strong>
        개
      </span>

    </div>
    <!-- //info -->

    <!--table-->
    <div class="table_container">
      <table class="manage-table resumes responsive-table">
        <colgroup>
          <col style="width :130px"/>
          <col/>
          <col style="width :230px"/>
          <col style="width :230px"/>
        </colgroup>
        <thead>
        <tr>
          <th>No.</th>
          <th>제목</th>
          <th>작성자</th>
          <th>작성일</th>
        </tr>
        </thead>
        <tbody>
        <tr v-for="(board,index) in boardList" v-bind:key="board.boardId">
          <td>{{ (searchData.page - 1) * searchData.size + index + 1 }}</td>
          <td class="title"><a @click.stop="goView(board.boardId)">{{ board.title }}</a></td>
          <td>{{ board.regUser.name }}</td>
          <td>{{ board.regDatetime }}</td>
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

import BoardService from "@/services/BoardService";
import PaginationComponent from "@/components/common/PaginationComponent.vue";

export default {
  name: 'BoardList',
  components: {
    PaginationComponent
  },
  data() {
    return {
      totalCount: 0,
      boardList: [],
      defaultSearchData: {},
      searchData: {
        type: 'LEVEL_1',
        title: '',
        regName: '',
        page: 1,
        size: 10
      }
    };
  },
  mounted() {
    this.defaultSearchData = {...this.searchData};
    this.init();
  },
  methods: {
    init() {
      const query = this.$route.query;

      this.searchData.type = this.getValue(query.type, this.defaultSearchData.type);
      this.searchData.page = this.getNumber(query.page, 1, 1);
      this.searchData.title = this.getValue(query.title, '');
      this.loadPage();
    },
    search() {
      this.$router.push({query: this.makeQueryString()});
    },
    loadPage() {
      const params = {...this.searchData};
      params.page = params.page - 1;
      BoardService.get(params).then(({data}) => {
        this.boardList = data.content;
        this.totalCount = data.totalElements;
      });

    },
    changePage(page) {
      const query = this.makeQueryString(page);
      this.$router.push({query: query});
    },
    makeQueryString(page = 1) {
      const query = {...this.searchData, ...{page}};
      return query;
    },
    goView(boardId) {
      console.log('goView->' + boardId);
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