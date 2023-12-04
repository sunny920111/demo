<template>
  <div class="write_container">
    <div class="input_container">
      <input v-model.trim="model.title" type="text" placeholder="제목">
    </div>
    <editor-component v-model:content="model.content">
    </editor-component>
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
      mode: 'new', // new, edit,
      model: {
        type: 'LEVEL_1',
        title: '',
        content: ''
      }
    }
  },
  methods: {
    goBoard() {
      this.$router.push('/board');
    },
    write() {
      BoardService.save(this.model).then(({data}) => {
        console.log(data);
        this.goBoard();
      });
    }
  }
}
</script>
