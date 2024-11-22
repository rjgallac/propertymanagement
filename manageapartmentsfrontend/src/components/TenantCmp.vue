<script setup>
import { ref } from 'vue';
import { useTenantStore } from '@/stores/tenantStore';

const tenantStore = useTenantStore();
tenantStore.fetchTenants();

let tenantEmailAddress = ref("")
let tenantAddress = ref("")
let tenantName = ref("")
function addTenant(){
    tenantStore.addTenant({ "name": tenantName.value, "address": tenantAddress.value, "emailAddress": tenantEmailAddress.value })
}

function deleteTenant(id) {
    tenantStore.deleteTenant(id)
}

</script>

<template>
  <h1>Tenants</h1>
  <li v-for="tenant in tenantStore.tenants">
    {{ tenant.name }} - {{tenant.address}}  <button @click="deleteTenant(tenant.id)">del</button>
  </li>
  <h2>Add Tenant</h2>
  <form @submit.prevent="onSubmit">
    Tenant: 
    Name: <input v-model="tenantName" type="text"></input>
    Address: <input v-model="tenantAddress" type="text"></input>

    Email Address: <input v-model="tenantEmailAddress" type="text"></input>

    <button @click="addTenant()"> submit</button>
  </form>
</template>