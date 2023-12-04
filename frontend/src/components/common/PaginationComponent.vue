<template>
  <div class="pagination-container">
    <nav class="pagination">
      <ul>
        <li v-for="item in range(minPage,maxPage)" :key="item">
          <a @click="changePage(item)" :class="{'current-page':value ===item }">{{ item }}</a>
        </li>
      </ul>
    </nav>
    <nav class="pagination-next-prev">
      <ul>
        <li><a class="prev" :class="{disabled:value ===1}" @click="prevPage">Previous</a></li>
        <li><a class="next" :class="{disabled:value ===totalPages}" @click="nextPage">Next</a></li>
      </ul>
    </nav>
  </div>
</template>

<script>
export default {
  name: 'PaginationComponent',
  props: {
    pageCount: {
      type: Number,
      default: 0
    },
    perPage: {
      type: Number,
      default: 10
    },
    total: {
      type: Number,
      default: 0
    },
    value: {
      type: Number,
      default: 1
    },
    size: {
      type: String,
      default: ""
    },
    align: {
      type: String,
      default: ""
    }
  },
  computed: {
    totalPages() {
      if (this.pageCount > 0) return this.pageCount;
      if (this.total > 0) {
        return Math.ceil(this.total / this.perPage);
      }
      return 1;
    },
    pagesForDisplay() {
      if (this.totalPages > 0 && this.totalPages < this.defaultPagesForDisplay) {
        return this.totalPages;
      }
      return this.defaultPagesForDisplay;
    },
    minPage() {
      if (this.value >= this.pagesForDisplay) {
        const pageToAdd = Math.floor(this.pagesForDisplay / 2);
        const newMaxPage = pageToAdd + this.value;
        if (newMaxPage > this.totalPages) {
          return this.totalPages - this.pagesForDisplay + 1;
        }
        return this.value - pageToAdd;
      }
      return 1;
    },
    maxPage() {
      if (this.value >= this.pagesForDisplay) {
        const pageToAdd = Math.floor(this.pagesForDisplay / 2);
        const newMaxPage = pageToAdd + this.value;
        if (newMaxPage < this.totalPages) {
          return newMaxPage;
        }
        return this.totalPages;
      }
      return this.pagesForDisplay;
    }

  },
  data() {
    return {
      defaultPagesForDisplay: 5
    };
  },
  methods: {
    range(min, max) {
      let arr = [];
      for (let i = min; i <= max; i++) {
        arr.push(i);
      }
      return arr;
    },
    changePage(page) {
      this.$emit('input', page);
    },
    prevPage() {
      if (this.value > 1) {
        this.$emit('input', this.value - 1);
      }
    },
    nextPage() {
      if (this.value < this.totalPages) {
        this.$emit('input', this.value + 1);
      }
    }
  }
}
</script>

<style scoped>

</style>