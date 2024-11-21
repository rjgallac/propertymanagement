<script setup>
import { ref } from 'vue';

import { usePropertyStore } from '@/stores/propertyStore';
import { useLandlordStore } from '@/stores/landlordStore';


const propertyStore = usePropertyStore();
const landlordStore = useLandlordStore();


propertyStore.fetchProperties();

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
  <h1>Properties</h1>
    <li v-for="property in propertyStore.properties">
      {{ property.address }} - {{ property.landlordDto.name }} <button @click="deleteProperty(property.id)">del</button>
    </li>
  <h2>Add Property</h2>

  <form @submit.prevent="onSubmit">
    Landlord: 
      <select v-model="landlordid">
        <option v-for="landlord in landlordStore.landlords" 
                :value="landlord.id">{{ landlord.name }}</option>
      </select>

    address: <input v-model="addPropertyAddress" type="text"></input>
    <button @click="addProperty()"> submit</button>
  </form>
</template>