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
      type: this.$route.params.type,
      model: {
        type: this.$route.params.type,
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
      this.$router.push('/board/' + this.type);
    },
    goView() {
      this.$router.push('/board/' + this.type + '/' + this.boardId);
    },
  },
  watch: {
    $route() {
      console.log('Write->', this.$route.params)
      if (this.$route.params) {
        this.model.type = this.$route.params.type;
        this.type = this.$route.params.type;
        this.boardId = this.$route.params.boardId;
      }
    }
  }
}
</script>
