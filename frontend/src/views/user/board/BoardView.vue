<template>
  <div class="view_container">
    <h3>{{ model.title }}</h3>
    <div class="meta-tags">
      <span>{{ model.regDatetime }}</span>
      <span>{{ model.regUser.name }}</span>
    </div>
    <div class="post-content view_content ql-snow">
      <div class="ql-editor" v-html="model.content">
      </div>
    </div>
    <div class="button_container">
      <button class="button" @click="remove" v-if="hasRole(model.regUser.userId)">삭제</button>
      <button class="button" @click="goBoard">목록</button>
      <button class="button" @click="goEdit" v-if="hasRole(model.regUser.userId)">수정</button>
    </div>
  </div>
</template>

<script>
import BoardService from "@/services/BoardService";
import {mapGetters} from "vuex";

export default {
  name: 'boardView',
  data() {
    return {
      boardId: this.$route.params.boardId,
      model: {
        type: this.$route.params.type,
        semesterId: this.$route.params.semesterId,
        title: '',
        content: '',
        regUser: {},
        regDatetime: ''
      }
    };
  },
  computed: {
    ...mapGetters({
      isAdmin: 'isAdmin',
      getUser: 'getUser'
    }),
    type() {
      return this.model.type;
    },
    semesterId() {
      return this.model.semesterId;
    }
  },
  mounted() {
    this.getBoard();
  },
  methods: {
    getBoard() {
      BoardService.getOne(this.boardId).then(({data}) => {
        this.model = data;
      });
    },
    goBoard() {
      this.$router.push({path: '/board/' + this.type + '/' + this.semesterId});
    },
    goEdit() {
      this.$router.push('/board/' + this.type + '/' + this.semesterId + '/write/' + this.boardId);
    },
    remove() {
      BoardService.remove(this.boardId).then(({data}) => {
        console.log(data);
        this.goBoard();
      });
    },
    hasRole(userId) {
      return this.isAdmin || this.getUser.userId === userId;
    }
  },
  watch: {
    $route() {
      console.log('View->', this.$route.params)
      if (this.$route.params) {
        this.model.type = this.$route.params.type;
        this.model.semsterId = this.$route.params.semesterId;
        this.boardId = this.$route.params.boardId;
      }
    }
  }
};
</script>

<style scoped>

</style>