<template>
  <v-app id="inspire">
    <v-navigation-drawer v-model="drawer">
      <!--  -->
      <v-list-item title="My Application" subtitle="Vuetify"></v-list-item>
      <v-divider></v-divider>
      <RouterLink to="/landlord" v-if="loginStore.loggedin && !loginStore.role == 'USER'"><v-list-item link title="Landlords"></v-list-item></RouterLink>
      <RouterLink to="/property" v-if="loginStore.loggedin && !loginStore.role == 'USER'"><v-list-item link title="Properties"></v-list-item></RouterLink>
      <RouterLink to="/apartment" v-if="loginStore.loggedin && !loginStore.role == 'USER'"><v-list-item link title="Apartments"></v-list-item></RouterLink>
      <v-divider></v-divider>

      <RouterLink to="/booking" v-if="loginStore.loggedin"><v-list-item link title="Bookings"></v-list-item></RouterLink>
      <RouterLink to="/tenant" v-if="loginStore.loggedin && !loginStore.role == 'USER'"><v-list-item link title="Tenants"></v-list-item></RouterLink>
      <v-divider></v-divider>
      <RouterLink to="/login" v-if="!loginStore.loggedin"><v-list-item link title="Login"></v-list-item></RouterLink>
      <RouterLink to="/logout" v-if="loginStore.loggedin"><v-list-item link title="Logout"></v-list-item></RouterLink>
      <!-- {{ loginStore.role }} -->
    </v-navigation-drawer>

    <v-app-bar>
      <v-app-bar-nav-icon @click="drawer = !drawer"></v-app-bar-nav-icon>
      <v-app-bar-title>Application</v-app-bar-title>
    </v-app-bar>

    <v-main>
      <!--  -->
      <RouterView />
    </v-main>
  </v-app>
</template>

<script setup>
  import { ref } from 'vue'
  import { useLoginStore } from '@/stores/loginStore';

  const loginStore = useLoginStore();

  const drawer = ref(null)
  let out =  ref("test")
</script>

<script>
  export default {
    data: () => ({ drawer: null }),
  }
</script>
