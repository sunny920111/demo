<template>
  <div class="editor_container">
    <vue-editor
        class="vue_editor_box"
        v-model="contentProp"
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
  methods: {}
}
</script>
<style>
.vue_editor_box {
  height: 590px;
}
</style>