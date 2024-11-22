<template>
  <v-app id="inspire">
    <v-navigation-drawer v-model="drawer">
      <!--  -->
      <v-list-item title="My Application" subtitle="Vuetify"></v-list-item>
      <v-divider></v-divider>
      <RouterLink to="/property"><v-list-item link title="Properties"></v-list-item></RouterLink>
      <RouterLink to="/apartment"><v-list-item link title="Apartments"></v-list-item></RouterLink>

      <RouterLink to="/booking"><v-list-item link title="Bookings"></v-list-item></RouterLink>
      <RouterLink to="/tenant"><v-list-item link title="Tenants"></v-list-item></RouterLink>
      <RouterLink to="/landlord"><v-list-item link title="Landlords"></v-list-item></RouterLink>
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

  const drawer = ref(null)
</script>

<script>
  export default {
    data: () => ({ drawer: null }),
  }
</script>
