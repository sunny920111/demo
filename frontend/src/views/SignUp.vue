<template>
  <div id="login_container" class="login_container">
    <!-- FORM SECTION -->
    <div class="row">
      <!-- SIGN UP -->
      <div class="col align-items-center flex-col sign-up">
        <div class="form-wrapper align-items-center">
          <div class="form sign-up">
            <div class="input-group">
              <i class='bx bxs-user'></i>
              <input type="text" placeholder="Username" v-model="name">
            </div>
            <div class="input-group">
              <i class='bx bx-mail-send'></i>
              <input type="email" placeholder="Email" v-model="email">
            </div>
            <div class="input-group">
              <i class='bx bxs-lock-alt'></i>
              <input type="password" placeholder="Password" v-model="password">
            </div>
            <div class="input-group">
              <i class='bx bxs-lock-alt'></i>
              <input type="password" placeholder="Confirm password" v-model="confirmPassword">
            </div>
            <button @click="signUp">
              Sign up
            </button>
            <p>
							<span>
								Already have an account?
							</span>
              <b @click="move('signIn')" class="pointer">
                Sign in here
              </b>
            </p>
          </div>
        </div>

      </div>
      <!-- END SIGN UP -->
      <!-- SIGN IN -->
      <div class="col align-items-center flex-col sign-in">
      </div>
      <!-- END SIGN IN -->
    </div>
    <!-- END FORM SECTION -->
    <!-- CONTENT SECTION -->
    <div class="row content-row">
      <div class="col align-items-center flex-col"></div>
      <!-- SIGN UP CONTENT -->
      <div class="col align-items-center flex-col">
        <div class="img sign-up">

        </div>
        <div class="text sign-up">
          <h2>
            Join with us
          </h2>
        </div>
      </div>
      <!-- END SIGN UP CONTENT -->
    </div>
    <!-- END CONTENT SECTION -->
  </div>
</template>

<script>
import AuthService from "@/services/AuthService";
import {required} from "vuelidate/lib/validators";

export default {
  name: 'SignUp',
  data() {
    return {
      name: '이효선2',
      email: 'sunny@naver.com',
      password: '1234',
      confirmPassword: '1234'
    }
  },
  validations: {
    name: {required},
    email: {required},
    password: {required},
    confirmPassword: {required},
  },
  mounted() {
    let container = document.getElementById('login_container');
    setTimeout(() => {
      container.classList.add('sign-up')
    }, 200);
  },
  methods: {
    move(link) {
      this.$router.push({path: '/' + link});
    },
    signUp() {
      const params = {
        name: this.name,
        email: this.email,
        password: this.password,
        confirmPassword: this.confirmPassword
      };
      AuthService.signUp(params).then(({data}) => {
        alert(data.message);

        if (data.success) {
          this.$router.push('/signIn');
        }
      });
    }
  }
}
</script>

<style scoped>

</style>