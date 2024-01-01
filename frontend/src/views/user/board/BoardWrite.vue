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
        type: this.$route.params.type,
        semesterId: this.getNumber(this.$route.params.semesterId, 0),
        title: '',
        content: ''
      }
    }
  },
  computed: {
    type() {
      return this.model.type;
    },
    semesterId() {
      return this.model.semesterId;
    }
  },
  mounted() {
    if (this.boardId) this.mode = 'EDIT';
    if (this.mode === 'EDIT') {
      this.init();
    }
  },
  methods: {
    init() {

      const params = this.$route.params ? this.$route.params : {};

      this.model.type = params.type;
      this.model.semesterId = params.semesterId;
      this.boardId = params.boardId;

      this.getBoard();
    },
    getBoard() {
      BoardService.getOne(this.boardId).then(({data}) => {
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
        this.goBoard();
      });
    },
    updateBoard() {
      BoardService.update(this.boardId, this.model).then(({data}) => {
        console.log(data);
        this.goView();
      });
    },
    goBoard() {
      this.$router.push({path: '/board/' + this.type + '/' + this.semesterId});
    },
    goView() {
      this.$router.push('/board/' + this.type + '/' + this.semesterId + '/view/' + this.boardId);
    },
  },
  watch: {
    $route() {
      console.log('BoardWrite->', this.$route.params);
      if (this.$route.params.boardId) {
        this.init();
      }
    }
  }
}
</script>
