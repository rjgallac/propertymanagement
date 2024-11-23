<script setup>
import { ref } from 'vue';
import { useLoginStore } from '@/stores/loginStore';

const loginStore = useLoginStore();

let email = ref("")
let password = ref(null)

function login(){
    loginStore.login(email.value, password.value);
}
</script>

<template>
  <div id="login">
  <h1>Login</h1>
 

  <v-form @submit.prevent>
     
      <v-text-field label="email" v-model="email" type="text"></v-text-field>
      <v-text-field label="password" v-model="password" type="text"></v-text-field>

    <v-btn @click="login()"> submit</v-btn>
  </v-form>
</div>
</template>


<style scoped>
#properties {
    padding: 20px;
}
</style>