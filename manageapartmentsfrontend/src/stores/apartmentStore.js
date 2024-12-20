
import { ref } from 'vue';

import { defineStore } from 'pinia';

export const useApartmentStore = defineStore('apartment', () => {
  let apartments = ref([]);
  let token = JSON.parse(localStorage.getItem('auth'));
  let jwt = token.token;
  const requestOptions = {
    method: 'GET',
    headers: { 
      'Content-Type': 'application/json',
      'Authorization' : 'Bearer ' + JSON.parse(localStorage.getItem('auth')).token
    },
};
  function fetchApartments(){
    fetch('http://localhost:8080/apartment', requestOptions)
    .then(response => response.json())
    .then(data => {
      apartments.value = data;
    });
  }

  function addApartment(apartment){
    const requestOptions = {
        method: 'POST',
        headers: { 
          'Content-Type': 'application/json',
          'Authorization' : 'Bearer ' + JSON.parse(localStorage.getItem('auth')).token
        },
        body: JSON.stringify(apartment)
      };
      fetch('http://localhost:8080/apartment', requestOptions)
        .then(response => response.json())
        .then(data => {
            apartments.value.push(data);
        });

  }

  function deleteApartment(id) {
    const requestOptions = {
        method: 'DELETE',
        headers: { 
          'Content-Type': 'application/json',
          'Authorization' : 'Bearer ' + JSON.parse(localStorage.getItem('auth')).token
        },
      };
      fetch('http://localhost:8080/apartment/' + id, requestOptions)
        .then(response => {
          let apartmentIndex = apartments.value.findIndex(apartment => apartment.id === id)
          apartments.value.splice(apartmentIndex, 1);
        })
  }

  return {apartments, addApartment, fetchApartments, deleteApartment}
});
