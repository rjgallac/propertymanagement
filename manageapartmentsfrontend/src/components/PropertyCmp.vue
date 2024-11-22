<script setup>
import { ref } from 'vue';

import { usePropertyStore } from '@/stores/propertyStore';
import { useLandlordStore } from '@/stores/landlordStore';

const propertyStore = usePropertyStore();
const landlordStore = useLandlordStore();
propertyStore.fetchProperties();
landlordStore.fetchLandlords();

let addPropertyAddress = ref("")
let landlordid = ref(null)
function addProperty(){
  propertyStore.addProperty({ "landlordDto": { "id": landlordid.value }, "address": addPropertyAddress.value })
}

function deleteProperty(id) {
    propertyStore.deleteProperty(id);
}

</script>

<template>
  <div id="properties">
  <h1>Properties</h1>
  <v-table>
    <thead>
      <tr><th>address</th><th>landlord name</th><th>Actions</th></tr>
    </thead>
    <tbody>
      <tr v-for="property in propertyStore.properties">
      <td>{{ property.address }}</td> 
      <td> {{ property.landlordDto.name }} </td>
      <td><v-btn @click="deleteProperty(property.id)">del</v-btn></td>
    </tr>
    </tbody>
  </v-table>
    
  <h2>Add Property</h2>

  <v-form @submit.prevent>
      <v-select v-model="landlordid" 
        label="Landlord"
        :items="landlordStore.landlords"
        item-title="name"
        item-value="id">
      </v-select>
      <v-text-field label="Address" v-model="addPropertyAddress" type="text"></v-text-field>
    <v-btn @click="addProperty()"> submit</v-btn>
  </v-form>
</div>
</template>


<style scoped>
#properties {
    padding: 20px;
}
</style>