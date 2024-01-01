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

    <!-- 답변 -->
    <div class="comment_container">
      <div class="comment">
        <div class="content">
          <div style="height : 250px;">
            <editor-component v-model:content="comment.content" :height="200">
            </editor-component>
          </div>
        </div>
        <div class="btn-area">
          <a @click="saveComment()">
            <i class="fa fa-save"></i>
            저장
          </a>
        </div>
      </div>
    </div>
    <!-- //답변 -->

    <!-- Comment -->
    <div class="comment_container" v-if="commentList.length > 0">
      <div class="comment" v-for="comment in commentList" :key="comment.id">
        <div class="title"><span> {{ comment.modUser.name }} | {{ comment.modDatetime }}</span></div>

        <div class="content">
          <div class="editor" v-if="comment.editYn ==='Y'">
            <editor-component v-model:content="comment.content" :height="250">
            </editor-component>
          </div>
          <div v-else class="ql-snow">
            <div class="ql-editor" v-html="comment.content">
            </div>
          </div>
        </div>

        <div class="btn-area">
          <a class="edit" @click="removeComment(comment.id)" v-if="hasRole(model.regUser.userId)">
            <i class="fa fa-remove"></i>
            삭제
          </a>
          <a class="delete" @click="editComment(comment)"
             v-if="hasRole(model.regUser.userId)">
            <i class="fa fa-pencil"></i>
            수정
          </a>
          <a v-if="comment.editYn ==='Y'" @click="cancelCommentEditMode(comment)">
            <i class="fa fa-close"></i>
            취소
          </a>
        </div>
      </div>
    </div>
    <!--// Comment -->

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
import EditorComponent from "@/components/common/EditorComponent.vue";
import CommentService from "@/services/CommentService";

export default {
  name: 'boardView',
  components: {EditorComponent},
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
      },
      commentList: [],
      comment: {
        content: '',
        boardId: this.$route.params.boardId,
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
    this.init();
  },
  methods: {
    init() {
      const params = this.$route.params ? this.$route.params : {};

      this.model.type = params.type;
      this.model.semesterId = params.semesterId;
      this.boardId = params.boardId;

      this.getBoard();
      this.getCommentList();
    },
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
    },
    editComment(comment) {
      if (!comment.editYn || comment.editYn == 'N') {
        comment.editYn = 'Y';
      } else {
        this.updateComment(comment);
      }

    },
    cancelCommentEditMode(comment) {
      comment.editYn = 'N';
    },
    getCommentList() {
      CommentService.getList(this.boardId, {}).then(({data}) => {
        console.log(data);
        this.commentList = data;
      });
    },
    saveComment() {
      CommentService.save(this.comment).then(({data}) => {
        console.log(data);
        this.comment.content = '';
        this.getCommentList();
      });
    },
    updateComment(comment) {
      CommentService.update(comment.id, comment).then(({data}) => {
        console.log(data);
        this.getCommentList();
      });
    },
    removeComment(commentId) {
      CommentService.remove(commentId).then(({data}) => {
        alert(data);
        this.getCommentList();
      });
    },
  },
  watch: {
    $route() {
      console.log('BoardView->', this.$route.params);
      if (this.$route.params.boardId) {
        this.init();
      }
    }
  }
};
</script>

<style scoped>

</style>