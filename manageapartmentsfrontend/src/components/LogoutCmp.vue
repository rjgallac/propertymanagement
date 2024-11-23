<script setup>
import { ref } from 'vue';
import { useLoginStore } from '@/stores/loginStore';

const loginStore = useLoginStore();

loginStore.logout();



</script>

<template>
  
</template>


<style scoped>
#properties {
    padding: 20px;
}
</style>

