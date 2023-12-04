<template>
  <div class="editor_container">
    <ck-editor v-model="editorContent" :editor="editor" :config="editorConfig"/>
  </div>
</template>

<script>
import {defineComponent} from "vue";
import CKEditor from '@ckeditor/ckeditor5-vue';
import ClassicEditor from '@ckeditor/ckeditor5-build-classic';
import UploadImageAdapter from "@/components/common/UploadImageAdapter.vue";

//  컴포넌트 등록
export default defineComponent({
      components: {'ck-editor': CKEditor.component},
      props: {
        content: {
          type: String,
          default: '<h1>테스트</h1>'
        }
      },
      computed: {
        editorContent: {
          get() {
            return this.content;
          },
          set(val) {
            this.$emit("update:content", val);
          }
        }
      },
      data() {
        return {
          editor: ClassicEditor,
          editorConfig: {
            toolbar: ['heading', '|', 'fontBackgroundColor', 'fontColor', 'fontSize', 'bold', 'italic', '|', 'alignment', 'bulletedList', 'numberedList', 'indent', 'outdent', '|', 'imageUpload', 'insertTable', 'link', '|', 'undo', 'redo'],
            table: {
              contentToolbar: ['tableColumn', 'tableRow', 'mergeTableCells', 'tableProperties', 'tableCellProperties'],
            },
            image: {
              resize: true,
              toolbar: ['imageStyle:alignLeft', 'imageStyle:alignRight', 'imageStyle:inline', 'imageStyle:side']
            },
            extraPlugins: [function (editor) {
              editor.plugins.get('FileRepository').createUploadAdapter = (loader) => {
                return new UploadImageAdapter(loader);
              }
            }]
          }
        }
      }
    }
)

</script>
<style>
.ck-content {
  height: 550px;
}
</style>