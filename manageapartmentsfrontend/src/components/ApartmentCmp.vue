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
    <div id="apartments">
        <h1>Apartments</h1>
        <v-table>
            <thead>
                <tr><th>Rent</th><th>address</th><th>Actions</th></tr>
            </thead>
            <tbody>
                <tr v-for="apartment in apartmentStore.apartments">
                    <td>{{ apartment.rent }}</td>
                    <td>{{apartment.propertyDto.address }}</td>
                    <td>  <v-btn @click="deleteApartment(apartment.id)">del</v-btn></td>
                </tr>
            </tbody>
        </v-table>

        
        <h2>Add Apartment</h2>
        <form @submit.prevent="onSubmit">
            <v-select v-model="selectedPropertyId" 
                label="Property"
                :items="propertyStore.properties"
                item-title="address"
                item-value="id">
            </v-select>

            <v-text-field label="Rent" v-model="addApartmentDto.rent" type="text"></v-text-field>
            <v-btn @click="addApartment()"> submit</v-btn>
        </form>
    </div>
</template>
<style scoped>
#apartments {
    padding: 20px;
}
</style>