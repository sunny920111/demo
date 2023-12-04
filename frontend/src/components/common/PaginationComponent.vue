<template>
  <div>
    <a></a>
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

  }
}
</script>

<style scoped>

</style>