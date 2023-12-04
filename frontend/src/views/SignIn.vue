<template>
  <div id="login_container" class="login_container">
    <!-- FORM SECTION -->
    <div class="row">
      <div class="col align-items-center flex-col"></div>
      <!-- SIGN IN -->
      <div class="col align-items-center flex-col sign-in">
        <div class="form-wrapper align-items-center">
          <div class="form sign-in">
            <div class="input-group">
              <i class='bx bxs-user'></i>
              <input v-model="email" type="text" placeholder="Username">
            </div>
            <div class="input-group">
              <i class='bx bxs-lock-alt'></i>
              <input v-model="password" type="password" placeholder="Password">
            </div>
            <button @click="signIn">
              Sign in
            </button>
            <p>
              <b>
                Forgot password?
              </b>
            </p>
            <p>
							<span>
								Don't have an account?
							</span>
              <b @click="move('signUp')" class="pointer">
                Sign up here
              </b>
            </p>
          </div>
        </div>
        <div class="form-wrapper">

        </div>
      </div>
      <!-- END SIGN IN -->
    </div>
    <!-- END FORM SECTION -->

    <!-- CONTENT SECTION -->
    <div class="row content-row">
      <!-- SIGN IN CONTENT -->
      <div class="col align-items-center flex-col">
        <div class="text sign-in">
          <h2>
            Welcome~!
          </h2>

        </div>
        <div class="img sign-in">

        </div>
      </div>
      <!-- END SIGN IN CONTENT -->
      <div class="col align-items-center flex-col"></div>
    </div>
    <!-- END CONTENT SECTION -->
  </div>
</template>

<script>
import {required} from 'vuelidate/lib/validators';
import AuthService from "@/services/AuthService";

export default {
  name: 'SignIn',
  data() {
    return {
      email: 'sunny920111@naver.com',
      password: '1234'
    }
  },
  validations: {
    email: {required},
    password: {required},
  },
  mounted() {
    let container = document.getElementById('login_container');
    setTimeout(() => {
      container.classList.add('sign-in')
    }, 200);
  },
  methods: {
    move(link) {
      this.$router.push({path: '/' + link});
    },
    signIn(evt) {
      evt.preventDefault();
      /*
            this.$v.touch();

            if (this.$v.$invalid) {
              alert("입력값을 확인해주세요");
              return;
            }*/

      const params = {
        email: this.email,
        password: this.password
      };
      AuthService.signIn(params).then(({data}) => {
        console.log(data);
        localStorage.accessToken = data.accessToken;
        this.$store.dispatch('USER_INIT').then(() => {
          this.$router.push('/board');
        });

      }).catch((e) => {
        console.log(e)
        alert("로그인에 실패했습니다.");
      });
    }
  }

}
</script>

<style scoped>

</style>