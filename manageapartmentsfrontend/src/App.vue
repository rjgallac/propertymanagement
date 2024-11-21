<script setup>
import ManageProperties from './components/ManageProperties.vue'
import ManageTenants from './components/ManageTenants.vue'

import { useApartmentStore } from '@/stores/apartmentStore';
import { usePropertyStore } from '@/stores/propertyStore';
import { useBookingStore } from '@/stores/bookingStore';
import { useTenantStore } from '@/stores/tenantStore';
import { useLandlordStore } from '@/stores/landlordStore';

const bookingStore = useBookingStore();
const tenantStore = useTenantStore();
const apartmentStore = useApartmentStore();
const propertyStore = usePropertyStore();
const landlordStore = useLandlordStore();

bookingStore.fetchBookings();
apartmentStore.fetchApartments();
tenantStore.fetchTenants();
landlordStore.fetchLandlords();
propertyStore.fetchProperties();



</script>

<template>
  <header>
    <div class="wrapper">
      <ManageProperties />
    </div>
  </header>

  <main>
    <ManageTenants />
  </main>
</template>

<style scoped>
header {
  line-height: 1.5;
}

</style>
