<script setup>
import { ref } from 'vue';

import { useApartmentStore } from '@/stores/apartmentStore';
import { usePropertyStore } from '@/stores/propertyStore';

const apartmentStore = useApartmentStore();
const propertyStore = usePropertyStore();
apartmentStore.fetchApartments();
propertyStore.fetchProperties();

let selectedPropertyId = ref(null)

let addApartmentDto = ref({
  propertyDto:{ id: 1},
  rent: 123
})

function addApartment(){
    apartmentStore.addApartment(addApartmentDto.value)
}

function deleteApartment(id) {
    apartmentStore.deleteApartment(id);
}


</script>

<template>
    <h1>Apartments</h1>
    <div class="greetings">
    <li v-for="apartment in apartmentStore.apartments">
        {{ apartment.rent }} - {{apartment.propertyDto.address }}  <button @click="deleteApartment(apartment.id)">del</button>
    </li>
    </div>
    <h2>Add Apartment</h2>
    <form @submit.prevent="onSubmit">
    Property: 
        <select v-model="selectedPropertyId">
        <option v-for="property in propertyStore.properties" 
                :value="property.id">{{ property.address }}</option>
        </select>
    RENT: <input v-model="addApartmentDto.rent" type="text"></input>
    <button @click="addApartment()"> submit</button>
    </form>
</template>