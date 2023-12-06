<template>
  <div class="view_container">
    <h3>{{ model.title }}</h3>
    <div class="meta-tags">
      <span>{{ model.regDatetime }}</span>
      <span>{{ model.regUser.name }}</span>
    </div>
    <div class="post-content view_content ck ck-editor__main">
      <div class="ck ck-content" v-html="model.content"></div>
    </div>
    <div class="button_container">
      <button class="button" @click="remove">삭제</button>
      <button class="button" @click="goBoard">목록</button>
      <button class="button" @click="goEdit">수정</button>
    </div>
  </div>
</template>

<script>
import BoardService from "@/services/BoardService";

export default {
  name: 'boardView',
  data() {
    return {
      boardId: this.$route.params.boardId,
      model: {
        title: '',
        content: '',
        regUser: {},
        regDatetime: ''
      }
    };
  },
  mounted() {
    this.getBoard();
  },
  methods: {
    getBoard() {
      const params = {boardId: this.boardId};
      BoardService.getOne(this.boardId, params).then(({data}) => {
        this.model = data;
      });
    },
    goBoard() {
      this.$router.push('/board');
    },
    goEdit() {
      this.$router.push('/board/write/' + this.boardId);
    },
    remove() {
      BoardService.remove(this.boardId).then(({data}) => {
        console.log(data);
        this.goBoard();
      });
    }
  }
};
</script>

<style scoped>

</style>