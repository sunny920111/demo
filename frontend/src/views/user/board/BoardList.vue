<template>
  <div class="sixteen columns">
    <!-- search box -->
    <div class="list-search">
      <table>
        <tbody>
        <tr>
          <td style="vertical-align: top; ">
            <div class="chosen-container-single" style="width:140px;">
              <select v-model="searchData.semesterId" class="chosen-single" @change="search">
                <option v-for="item in semesterList" :value="item.id" :key="item.id">{{
                    `[ ` + item.lectureYear + ` - ` + item.lectureSemester + ` ]`
                  }}
                </option>
              </select>
            </div>
          </td>
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
        <tr v-for="(board,index) in boardList" v-bind:key="board.boardId" @click.stop="goView(board.boardId)">
          <td>{{ (searchData.page - 1) * searchData.size + index + 1 }}</td>
          <td class="title"><a>{{ board.title }}</a></td>
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
import UserService from "@/services/UserService";
import {mapGetters} from "vuex";

export default {
  name: 'BoardList',
  components: {
    PaginationComponent
  },
  data() {
    return {
      type: this.$route.params.type,
      totalCount: 0,
      semesterList: [],
      boardList: [],
      defaultSearchData: {},
      searchData: {
        type: this.$route.params.type,
        semesterId: 1,
        title: '',
        regName: '',
        page: 1,
        size: 10
      }
    };
  },
  computed: {
    ...mapGetters({
      isAdmin: 'isAdmin',
      getUser: 'getUser'
    }),
    semesterId() {
      return this.searchData.semesterId;
    }
  },
  mounted() {
    this.defaultSearchData = {...this.searchData};
    this.getSemesterInfo()
  },
  methods: {
    getSemesterInfo() {
      const params = {userId: this.getUser.userId, type: this.type};
      UserService.getSemesterInfo(params).then(({data}) => {
        this.semesterList = data;
        this.searchData.semesterId = this.semesterList[0].id;
        this.init();
      });
    },
    init() {
      const query = this.$route.query;

      this.searchData.type = this.getValue(query.type, this.defaultSearchData.type);
      this.searchData.semesterId = this.getNumber(query.semesterId, this.searchData.semesterId);

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
      console.log(params)
      BoardService.getList(params).then(({data}) => {
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
      this.$router.push('/board/' + this.type + '/' + this.semesterId + '/' + boardId);
    },
    goWrite() {
      this.$router.push('/board/' + this.type + '/' + this.semesterId + '/write');
    }
  },
  watch: {
    $route() {
      if (this.$route.params) {
        this.type = this.$route.params.type;
        this.semesterId = this.getNumber(this.$route.params.semesterId, 0);

        this.searchData.type = this.$route.params.type;
        this.searchData.semesterId = this.getNumber(this.$route.params.semesterId, 0);

        this.defaultSearchData = {...this.searchData};
      }

      this.init();
    }
  }
}
</script>

<style scoped>
</style>