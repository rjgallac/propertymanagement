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
  <div id="tenants">
    <h1>Tenants</h1>
    <v-table>
      <thead>
        <tr><th>Tenant Name</th><th>Tenant Address</th><th>Actions</th></tr>
      </thead>
      <tbody>
        <tr v-for="tenant in tenantStore.tenants">
          <td>{{ tenant.name }}</td> 
          <td>{{tenant.address}}</td>
          <td><v-btn @click="deleteTenant(tenant.id)">del</v-btn></td>
        </tr>
      </tbody>
    </v-table>

    <h2>Add Tenant</h2>
    <form @submit.prevent="onSubmit">
      
      <v-text-field label="Name" v-model="tenantName" type="text"></v-text-field>
      <v-text-field label="Address" v-model="tenantAddress" type="text"></v-text-field>
      <v-text-field label="Email Address" v-model="tenantEmailAddress" type="text"></v-text-field>

      <v-btn @click="addTenant()"> submit</v-btn>
    </form>
  </div>
</template>

<style scoped>
#tenants {
  padding: 20px;
}
</style>