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
    <div id="landlords">
        <h1>Landlords</h1>
            <v-table>
                <thead>
                    <tr>
                        <th>Landlord Name</th> 
                        <th>Action</th>
                    </tr>
                </thead>
                <tbody>
                    <tr v-for="landlord in landlordStore.landlords">
                        <td>{{ landlord.name }}</td> 
                        <td><v-btn @click="deleteLandlord(landlord.id)">del</v-btn></td>
                    </tr>
                </tbody>
            </v-table>
            <h2>Add Landlord</h2>
            <v-form @submit.prevent>
                <v-text-field label="Name" v-model="landlordName" type="text"></v-text-field>
                <v-btn @click="addLandlord()">Submit</v-btn>
            </v-form>
    </div>
</template>
<style scoped>
#landlords {
    padding: 20px;
}
</style>