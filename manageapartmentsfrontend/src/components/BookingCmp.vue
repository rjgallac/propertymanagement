<script setup>
import { ref } from 'vue';

import { useBookingStore } from '@/stores/bookingStore';
import { useApartmentStore } from '@/stores/apartmentStore';

const bookingStore = useBookingStore();
const apartmentStore = useApartmentStore();
bookingStore.fetchBookings();
apartmentStore.fetchApartments();


let selectedApartment = ref(null)

let selectedTenant = ref(null)

function addBooking() {
    bookingStore.addBooking({ "apartmentDto": { "id": selectedApartment.value } })
}

function deleteBooking(id) {
    bookingStore.deleteBooking(id);
}
</script>

<template>
    <div id="bookings">
  <h1>Bookings</h1>
  <v-table>
    <thead>
      <tr><th>Tenant Name</th><th>Address</th><th>Actions</th></tr>
    </thead>
    <tbody>
      <tr v-for="booking in bookingStore.bookings">
        <!-- <td>{{ booking.tenantDto.name }}</td> -->
        <td> {{booking.apartmentDto.propertyDto.address}}</td>
        <td> <v-btn @click="deleteBooking(booking.id)">del</v-btn></td>
      </tr>

    </tbody>
  </v-table>
 

  <h2>Add Booking</h2>
  <form @submit.prevent="onSubmit">


      <v-select v-model="selectedApartment" 
        label="Apartment"
        :items="apartmentStore.apartments"
        item-title="rent"
        item-value="id">
      </v-select>
     
      <v-btn @click="addBooking()">make booking</v-btn>
  </form>
</div>
</template>

<style scoped>
#bookings {
    padding: 20px;
}
</style>