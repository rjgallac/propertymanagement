

<script setup>
import { ref } from 'vue';
import { usePropertyStore } from '@/stores/propertyStore';

defineProps({
  msg: {
    type: String,
    required: true,
  },
})

let landlords = ref([])

let properties = ref([])
const propertyStore = usePropertyStore();

let apartments = ref([])

let bookings = ref([])

let tenants = ref([])
let tenantEmailAddress = ref("")
let tenantAddress = ref("")
let tenantName = ref("")

let addPropertyAddress = ref("")
let landlordid = ref(null)
let selectedPropertyId = ref(null)
let selectedApartment = ref(null)

let selectedTenant = ref(null)

let landlordName = ref("")

let addApartmentDto = ref({
  propertyDto:{ id: 1},
  rent: 123
})

const requestOptions = {
    method: 'GET',
    headers: { 'Content-Type': 'application/json' },
};

fetch('http://localhost:8080/landlord', requestOptions)
    .then(response => response.json())
    .then(data => {
      landlords.value = data;
    });

fetch('http://localhost:8080/property', requestOptions)
    .then(response => response.json())
    .then(data => {
      properties.value = data;
      propertyStore.properties = data
    });

fetch('http://localhost:8080/apartment', requestOptions)
    .then(response => response.json())
    .then(data => {
      apartments.value = data;
    });

fetch('http://localhost:8081/booking', requestOptions)
    .then(response => response.json())
    .then(data => {
      bookings.value = data;
    });

fetch('http://localhost:8081/tenant', requestOptions)
    .then(response => response.json())
    .then(data => {
      tenants.value = data;
    });

function addLandlord(){
  const requestOptions = {
    method: 'POST',
    headers: { 'Content-Type': 'application/json' },
    body: JSON.stringify({ "name": landlordName.value })
  };
  fetch('http://localhost:8080/landlord', requestOptions)
    .then(response => response.json())
    .then(data => {
      landlords.value.push(data);
    });;
}

function deleteLandlord(id) {
  const requestOptions = {
    method: 'DELETE',
    headers: { 'Content-Type': 'application/json' },
  };
  fetch('http://localhost:8080/landlord/' + id, requestOptions)
    .then(response => {
      let landlordIndex = landlords.value.findIndex(landlord => landlord.id === id)
      landlords.value.splice(landlordIndex, 1);
    })
}

function addProperty(){
  const requestOptions = {
    method: 'POST',
    headers: { 'Content-Type': 'application/json' },
    body: JSON.stringify({ "landlordDto": { "id": landlordid.value }, "address": addPropertyAddress.value })
  };
  fetch('http://localhost:8080/property', requestOptions)
    .then(response => response.json())
    .then(data => {
      propertyStore.addProperty(data);
    });;;
}

function deleteProperty(id) {
  const requestOptions = {
    method: 'DELETE',
    headers: { 'Content-Type': 'application/json' },
  };
  fetch('http://localhost:8080/property/' + id, requestOptions)
    .then(response => {
      let propertyIndex = properties.value.findIndex(property => property.id === id)
      properties.value.splice(propertyIndex, 1);
    })

}

function addApartment(){
  const requestOptions = {
    method: 'POST',
    headers: { 'Content-Type': 'application/json' },
    body: JSON.stringify(addApartmentDto.value)
  };
  fetch('http://localhost:8080/apartment', requestOptions);
}

function deleteApartment(id) {
  const requestOptions = {
    method: 'DELETE',
    headers: { 'Content-Type': 'application/json' },
  };
  fetch('http://localhost:8080/apartment/' + id, requestOptions)
    .then(response => {
      let apartmentIndex = apartments.value.findIndex(apartment => apartment.id === id)
      apartments.value.splice(apartmentIndex, 1);
    })

}

function addTenant(){
  const requestOptions = {
    method: 'POST',
    headers: { 'Content-Type': 'application/json' },
    body: JSON.stringify({ "name": tenantName.value, "address": tenantAddress.value, "emailAddress": tenantEmailAddress.value })
  };
  fetch('http://localhost:8081/tenant', requestOptions);
}

function deleteTenant(id) {
  const requestOptions = {
    method: 'DELETE',
    headers: { 'Content-Type': 'application/json' },
  };
  fetch('http://localhost:8081/tenant/' + id, requestOptions)
    .then(response => {
      let tenantIndex = tenants.value.findIndex(tenant => tenant.id === id)
      tenants.value.splice(tenantIndex, 1);
    })
}

function addBooking(){

  const bookingDto = { "tenantDto": { "id": selectedTenant.value }, "apartmentDto": { "id": selectedApartment.value } }
  const requestOptions = {
    method: 'POST',
    headers: { 'Content-Type': 'application/json' },
    body: JSON.stringify(bookingDto)
  };
  fetch('http://localhost:8081/booking', requestOptions);
}

function deleteBooking(id) {
  const requestOptions = {
    method: 'DELETE',
    headers: { 'Content-Type': 'application/json' },
  };
  fetch('http://localhost:8081/booking/' + id, requestOptions)
    .then(response => {
      let bookingIndex = bookings.value.findIndex(booking => booking.id === id)
      bookings.value.splice(bookingIndex, 1);
    })
}

</script>

<template>

  <h1>Landlords</h1>
    <li v-for="landlord in landlords">
      {{ landlord.name }} <button @click="deleteLandlord(landlord.id)">del</button>
    </li>
    <form @submit.prevent="onSubmit">
      Name: <input v-model="landlordName" type="text"></input>
      <button @click="addLandlord()">Submit</button>
    </form>

  <h1>Properties</h1>
    <li v-for="property in propertyStore.properties">
      {{ property.address }} - {{ property.landlordDto.name }} <button @click="deleteProperty(property.id)">del</button>
    </li>
  <h2>Add Property</h2>

  <form @submit.prevent="onSubmit">
    Landlord: 
      <select v-model="landlordid">
        <option v-for="landlord in landlords" 
                :value="landlord.id">{{ landlord.name }}</option>
      </select>

    address: <input v-model="addPropertyAddress" type="text"></input>
    <button @click="addProperty()"> submit</button>
  </form>

  <h1>Apartments</h1>
  <div class="greetings">
    <li v-for="apartment in apartments">
      {{ apartment.rent }} - {{apartment.propertyDto.address }}  <button @click="deleteApartment(apartment.id)">del</button>
    </li>
  </div>
  <h2>Add Apartment</h2>
  <form @submit.prevent="onSubmit">
    Property: 
      <select v-model="selectedPropertyId">
        <option v-for="property in properties" 
                :value="property.id">{{ property.address }}</option>
      </select>
    RENT: <input v-model="addApartmentDto.rent" type="text"></input>
    <button @click="addApartment()"> submit</button>
  </form>

  <h1>Tenants</h1>
  <li v-for="tenant in tenants">
    {{ tenant.name }} - {{tenant.address}}  <button @click="deleteTenant(tenant.id)">del</button>
  </li>
  <h2>Add Tenant</h2>
  <form @submit.prevent="onSubmit">
    Tenant: 
    Name: <input v-model="tenantName" type="text"></input>
    Address: <input v-model="tenantAddress" type="text"></input>

    Email Address: <input v-model="tenantEmailAddress" type="text"></input>

    <button @click="addTenant()"> submit</button>
  </form>

  <h1>Bookings</h1>

  <li v-for="booking in bookings">
    {{ booking.tenantDto.name }} - {{booking.apartmentDto.propertyDto.address}} <button @click="deleteBooking(booking.id)">del</button>
  </li>

  <h2>Add Booking</h2>
  <form @submit.prevent="onSubmit">
    Apartment: 
      <select v-model="selectedApartment">
        <option v-for="apartment in apartments" 
                :value="apartment.id">{{ apartment.rent }}</option>
      </select>
    Tenant: 
      <select v-model="selectedTenant">
        <option v-for="tenant in tenants" 
                :value="tenant.id">{{ tenant.name }}</option>
      </select>
      <button @click="addBooking()">make booking</button>
  </form>
</template>

<style scoped>
h1 {
  font-weight: 500;
  font-size: 2.6rem;
  position: relative;
  top: -10px;
}

</style>
