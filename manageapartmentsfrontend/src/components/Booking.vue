<script setup>
import { ref } from 'vue';

import { useBookingStore } from '@/stores/bookingStore';
import { useApartmentStore } from '@/stores/apartmentStore';
import { useTenantStore } from '@/stores/tenantStore';

const bookingStore = useBookingStore();
const tenantStore = useTenantStore();
const apartmentStore = useApartmentStore();


let selectedApartment = ref(null)

let selectedTenant = ref(null)

function addBooking() {
    bookingStore.addBooking({ "tenantDto": { "id": selectedTenant.value }, "apartmentDto": { "id": selectedApartment.value } })
}

function deleteBooking(id) {
    bookingStore.deleteBooking(id);
}
</script>

<template>
    
  <h1>Bookings</h1>

  <li v-for="booking in bookingStore.bookings">
    {{ booking.tenantDto.name }} - {{booking.apartmentDto.propertyDto.address}} <button @click="deleteBooking(booking.id)">del</button>
  </li>

  <h2>Add Booking</h2>
  <form @submit.prevent="onSubmit">
    Apartment: 
      <select v-model="selectedApartment">
        <option v-for="apartment in apartmentStore.apartments" 
                :value="apartment.id">{{ apartment.rent }}</option>
      </select>
    Tenant: 
      <select v-model="selectedTenant">
        <option v-for="tenant in tenantStore.tenants" 
                :value="tenant.id">{{ tenant.name }}</option>
      </select>
      <button @click="addBooking()">make booking</button>
  </form>
</template>