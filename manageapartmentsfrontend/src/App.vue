<script setup>
import ManageProperties from './components/ManageProperties.vue'
import ManageTenants from './components/ManageTenants.vue'

</script>

<template>
  <header>
    <div class="wrapper">
      <ManageProperties />
    </div>
  </header>

  <main>
    <ManageTenants />
  </main>
</template>

<style scoped>
header {
  line-height: 1.5;
}

</style>
