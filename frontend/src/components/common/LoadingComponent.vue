<template>
  <div>
    Loading ...
  </div>
</template>

<script>
export default {
  name: 'loadingComponent',
  data() {
    return {
      model: {
        email: '',
        password: ''
      }
    };
  },
  mounted() {
    //console.log('checkIn')
    this.checkIn();
  },
  methods: {
    redirect(isAuth) {
      if (isAuth) {
        if (this.$route.query.returnPath) {
          this.$router.replace({
            path: this.$route.query.returnPath,
            query: this.$route.params.query
          });
        }
      } else {
        if (this.$route.query.returnPath) {
          this.$router.replace({
            name: 'signIn',
            query: {returnPath: this.$route.query.returnPath},
            params: {
              query: this.$route.params.query
            }
          });
        } else {
          this.$router.replace({name: 'signIn'});
        }

      }
    },
    checkIn() {

      this.$store.dispatch('USER_INIT').then(() => {
        this.redirect(this.$store.state.isAuth);
      }).catch((err) => {
        console.log('Loading USER INIT 에러', err);
      });

    }
  }
}
</script>
<style scoped>

</style>