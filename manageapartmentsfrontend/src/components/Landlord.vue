<script setup>
import { ref } from 'vue';

import { useLandlordStore } from '@/stores/landlordStore';

const landlordStore = useLandlordStore();
landlordStore.fetchLandlords();

let landlordName = ref("")

function addLandlord() {
    landlordStore.addLandlord({ "name": landlordName.value });
}

function deleteLandlord(id) {
    landlordStore.deleteLandlord(id);
}

</script>

<template>
  <h1>Landlords</h1>
    <li v-for="landlord in landlordStore.landlords">
        {{ landlord.name }} <button @click="deleteLandlord(landlord.id)">del</button>
    </li>
    <form @submit.prevent="onSubmit">
        Name: <input v-model="landlordName" type="text"></input>
        <button @click="addLandlord()">Submit</button>
    </form>
</template>