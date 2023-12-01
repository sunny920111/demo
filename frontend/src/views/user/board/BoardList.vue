<template>
  <div>
    <h2>게시판</h2>

    <!-- search box -->
    <div>
      <table>
        <tbody>
        <tr>
          <th>제목</th>
          <td>
            <input v-model.trim="searchData.title" @keyup.enter="search" type="text" placeholder="입력해주세요.">
          </td>
          <th>작성자</th>
          <td>
            <input v-model.trim="searchData.regName" @keyup.enter="search" type="text" placeholder="입력해주세요.">
            <a @click="search">검색</a>
          </td>
        </tr>
        </tbody>
      </table>
    </div>
    <!--// search box -->

    <!-- info -->
    <div>
      <span>
        총
        <strong>{{ totalCount }}</strong>
        개
      </span>

    </div>
    <!-- //info -->

    <!--table-->
    <div>
      <table>
        <caption>게시판</caption>
        <colgroup>
          <col style="width :60px"/>
          <col/>
          <col style="width :150px"/>
          <col style="width :200px"/>
        </colgroup>
        <thead>
        <tr>
          <th>No</th>
          <th>제목</th>
          <th>작성자</th>
          <th>작성일</th>
        </tr>
        </thead>
        <tbody>
        <tr v-for="(board,index) in boardList" v-bind:key="board.boardId">
          <td>{{ (searchData.page - 1) * searchData.size + index + 1 }}</td>
          <td>{{ board.title }}</td>
          <td>{{ board.regUser.name }}</td>
          <td>{{ board.regDatetime }}</td>
        </tr>
        </tbody>
      </table>
    </div>
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
  methods: {
    search() {
      const params = {...this.searchData};
      params.page = params.page - 1;

      BoardService.get(params).then(({data}) => {
        console.log(data);
        this.boardList = data.content;
        this.totalCount = data.totalElements;
      });

    }
  }
}
</script>

<style scoped>

</style>