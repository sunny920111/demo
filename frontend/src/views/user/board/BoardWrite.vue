<template>
  <div class="write_container">
    <div class="input_container">
      <input v-model.trim="model.title" type="text" placeholder="제목">
    </div>
    <div class="editor_container">
      <editor-component v-model:content="model.content">
      </editor-component>
    </div>
    <div class="button_container">
      <button class="button" @click="goBoard">목록</button>
      <button class="button" @click="write">등록</button>
    </div>
  </div>
</template>

<script>
import EditorComponent from "@/components/common/EditorComponent.vue";
import BoardService from "@/services/BoardService";

export default {
  name: 'boardWrite',
  components: {EditorComponent},
  data() {
    return {
      mode: 'NEW', // new, edit,
      boardId: this.$route.params.boardId,
      model: {
        type: 'LEVEL_1',
        title: '',
        content: ''
      }
    }
  },
  mounted() {
    if (this.boardId) this.mode = 'EDIT';
    if (this.mode === 'EDIT') {
      this.getBoard();
    }
  },
  methods: {
    getBoard() {
      const params = {boardId: this.boardId};
      BoardService.getOne(this.boardId, params).then(({data}) => {
        this.model = data;
      });
    },
    write() {
      if (this.mode === 'NEW') {
        this.saveBoard();
      } else {
        this.updateBoard();
      }
    },
    saveBoard() {
      BoardService.save(this.model).then(({data}) => {
        console.log(data);
        this.goView();
      });
    },
    updateBoard() {
      BoardService.update(this.boardId, this.model).then(({data}) => {
        console.log(data);
        this.goView();
      });
    },
    goBoard() {
      this.$router.push('/board');
    },
    goView() {
      this.$router.push('/board/' + this.boardId);
    },
  }
}
</script>
