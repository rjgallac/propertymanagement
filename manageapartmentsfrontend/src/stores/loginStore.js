
import { ref } from 'vue';

import { defineStore } from 'pinia';

export const useLoginStore = defineStore('login', () => {

    let loggedin = ref(localStorage.getItem("auth") ? true : false);

    function logout() {
      localStorage.removeItem('auth');
      loggedin.value = false;
    }

    function login(email, password){

        const requestOptions = {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify({ "email":email, "password":password })
        };
        fetch('http://localhost:8083/api/v1/auth/signin', requestOptions)
            .then(response => response.json())
            .then(data => {
                console.log(data)
                localStorage.setItem('auth', JSON.stringify(data));
                loggedin.value = true
            })
            .catch(error => {
                console.log(error)
            });
    }
    

  return {loggedin, logout, login}
});
