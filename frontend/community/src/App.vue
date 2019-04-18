<template>
  <v-app>
    <v-toolbar app>
      <v-toolbar-title class="headline text-uppercase">
        <span>광일공방</span>
      </v-toolbar-title>
    </v-toolbar>
    <v-content>
      <v-container>
        <v-layout row wrap justify-center>
          <v-flex xs4>
            <v-text-field
              label="email"
              outline
              v-model="email"
            >
            </v-text-field>
            <v-text-field
              label="비밀번호"
              outline
              :type="'password'"
              v-model="password"
            >
            </v-text-field>
            <v-text-field
                    label="name"
                    outline
                    v-model="name"
            >
            </v-text-field>
          </v-flex>
        </v-layout>
        <v-layout justify-center>
          <v-btn @click="login">로그인</v-btn>
          <v-btn @click="join">회원가입</v-btn>
          <v-btn @click="getMyInfo">내정보</v-btn>
        </v-layout>
      </v-container>
    </v-content>
  </v-app>
</template>

<script>
  export default {
    name: 'App',
    data() {
      return {
        email: null,
        name: null,
        password: null,
      }
    },
    methods: {
      getMyInfo () {
        this.$http.get("/api/users/me"
        ).then(res => {
          if (res.status === 200) {
            console.log(`my info: ${res.data}`);
            console.log(res.data)
          }
        })
      },
      login () {
        this.$http.post("/api/users/login", {
          email: this.email,
          password: this.password,
        }).then(res => {
          if (res.status === 200) {
            alert("login 성공")
          }
        })
      },
      join () {
        this.$http.post("/api/users", {
          email: this.email,
          password: this.password,
          name: this.name
        }).then(res => {
          if (res.status === 200) {
            alert(res.status + ":회원가입 성공.")
          }
        })
      },
    }
  }
</script>
