

<script setup>
import { ref } from 'vue';

import Property from '@/components/Property.vue';
import Landlord from '@/components/Landlord.vue';
import Apartment from '@/components/Apartment.vue';
import Tenant from '@/components/Tenant.vue';
import Booking from '@/components/Booking.vue';

defineProps({
  msg: {
    type: String,
    required: true,
  },
})

</script>

<template>
  <Landlord></Landlord>
  <Property></Property>
  <Apartment></Apartment>
  <Tenant></Tenant>
  <Booking></Booking>
</template>

<style scoped>
h1 {
  font-weight: 500;
  font-size: 2.6rem;
  position: relative;
  top: -10px;
}

</style>
