<template>
  <div class="sixteen columns">
    <p class="margin-bottom-5">게시판</p>
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
    <!--//table-->
  </div>
</template>

<script>

import BoardService from "@/services/BoardService";

export default {
  name: 'BoardList',
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
    this.init();
  },
  methods: {
    init() {
      const query = this.$route.query;

      this.searchData.type = this.getValue(query.type, "");
      this.searchData.page = this.getNumber(query.page, 1, 1);

      this.loadPage();
    },
    search() {
      this.searchData.page = 1;
      this.$router.push({query: this.searchData});
    },
    loadPage() {
      const params = {...this.searchData};
      params.page = params.page - 1;

      BoardService.get(params).then(({data}) => {
        console.log(data);
        this.boardList = data.content;
        this.totalCount = data.totalElements;
      });

    },
    changePage(page) {
      const query = JSON.parse(JSON.stringify(this.$route.query));
      query.page = page;
      this.$router.push({query: query});
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