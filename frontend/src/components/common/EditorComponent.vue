<template>
  <div class="editor_container">
    <vue-editor
        class="vue_editor_box"
        v-model="contentProp"
        @image-added="handleImageAdded"
    ></vue-editor>
  </div>
</template>

<script>
import {VueEditor} from 'vue3-editor';

export default {
  props: {
    content: {
      type: String,
      Default: ''
    }
  },
  components: {
    VueEditor
  },
  data() {
    return {}
  },
  computed: {
    contentProp: {
      get() {
        return this.content
      },
      set(val) {
        this.$emit('update:content', val);
      }
    }
  },
  methods: {
    handleImageAdded(file, editor, cursorLocation) {
      if (!file) return;

      const downloadType = 'VueEditorImages';

      Promise.all([this.uploadImage(file)]).then((result) => {
        const fileId = result[0].data.fileId;
        const url = `${this.axios.defaults.baseURL}/file/download/${downloadType}/${fileId}`;
        editor.insertEmbed(cursorLocation, 'image', url);

      });

    },
    uploadImage(file) {
      const downloadType = 'VueEditorImages';

      const formData = new FormData();
      formData.append('file', file);
      formData.append('refId', "temp");
      formData.append('downloadType', downloadType);

      return this.axios({
        url: '/file/upload',
        method: 'POST',
        data: formData,
        headers: {
          'Content-Type': 'multipart/form-data'
        }
      });

    }


  }
}
</script>
<style>
.vue_editor_box {
  height: 590px;
}
</style>