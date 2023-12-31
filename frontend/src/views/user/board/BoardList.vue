<template>
  <div class="sixteen columns">
    <!-- search box -->
    <div class="list-search">
      <table>
        <tbody>
        <tr>
          <td style="vertical-align: top; ">
            <div class="chosen-container-single" style="width:140px;">
              <select v-model="searchData.semesterId" class="chosen-single" @change="movePathBySemester">
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
      totalCount: 0,
      semesterList: [],
      boardList: [],
      defaultSearchData: {},
      searchData: {
        type: this.$route.params.type,
        semesterId: 0,
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
    type() {
      return this.searchData.type;
    },
    semesterId() {
      return this.searchData.semesterId;
    }
  },
  mounted() {
    //console.log('BoardList mounted')
    this.defaultSearchData = {...this.searchData};
    this.getSemesterInfo();
  },
  methods: {
    getSemesterInfo() {
      const params = {userId: this.getUser.userId, type: this.type};
      UserService.getSemesterInfo(params).then(({data}) => {
        this.semesterList = data;
        if (this.semesterList.length > 0) {
          this.searchData.semesterId = this.semesterList[0].id;
        }

        if (this.$route.params.semesterId) {
          this.init();
        } else {
          this.movePathBySemester();
        }

      });
    },
    movePathBySemester() {

      this.$router.push({
        path: '/board/' + this.type + '/' + this.semesterId,
        query: this.$route.query
      });

    },
    search() {
      this.$router.push({query: this.makeQueryString()});
    },
    init() {
      const query = this.$route.query ? this.$route.query : {};
      const params = this.$route.params ? this.$route.params : {};

      // console.log('init', query)
      this.searchData.type = this.getValue(params.type, this.defaultSearchData.type);
      this.searchData.semesterId = this.getNumber(params.semesterId, this.defaultSearchData.semesterId);
      this.searchData.page = this.getNumber(query.page, 1, 1);
      this.searchData.title = this.getValue(query.title, '');
      this.searchData.regName = this.getValue(query.regName, '');


      this.loadPage();
    },
    loadPage() {
      const params = {...this.searchData};
      params.page = params.page - 1;

      // console.log('loadPage', params);
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
      this.$router.push('/board/' + this.type + '/' + this.semesterId + '/view/' + boardId);
    },
    goWrite() {
      this.$router.push('/board/' + this.type + '/' + this.semesterId + '/write');
    }
  },
  watch: {
    '$route.params.semesterId'(oldValue, newValue) {
      if (oldValue && oldValue !== newValue)
        this.init();
    },
    $route() {
      if (JSON.stringify(this.$route.query) !== '{}') {
        this.init();
      }
    }

  }
}
</script>

<style scoped>


</style>