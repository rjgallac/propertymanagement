
import { ref } from 'vue';

import { defineStore } from 'pinia';

export const useLandlordStore = defineStore('landlord', () => {
  let landlords = ref([]);
  const requestOptions = {
    method: 'GET',
    headers: { 'Content-Type': 'application/json' },
};
  function fetchLandlords(){
    fetch('http://localhost:8080/landlord', requestOptions)
    .then(response => response.json())
    .then(data => {
      landlords.value = data;
    });
  }

  function addLandlord(landlord){
    const requestOptions = {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(landlord)
      };
      fetch('http://localhost:8080/landlord', requestOptions)
        .then(response => response.json())
        .then(data => {
            landlords.value.push(landlord);
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

  return {landlords, addLandlord, fetchLandlords, deleteLandlord}
});
