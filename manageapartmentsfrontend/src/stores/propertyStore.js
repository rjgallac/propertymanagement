
import { ref } from 'vue';
import router from '@/router/index.ts'

import { defineStore } from 'pinia';

export const usePropertyStore = defineStore('property', () => {
  let properties = ref([]);
  let token = JSON.parse(localStorage.getItem('auth'));
  let jwt = token.token;
  const requestOptions = {
    method: 'GET',
    headers: { 
      'Content-Type': 'application/json',
      'Authorization' : 'Bearer ' + jwt
    },
};
  function fetchProperties(){
    fetch('http://localhost:8080/property', requestOptions)
      .then(response => response.json())
      .then(data => {
        properties.value = data
      }).catch(error => {
        router.push({path:'/login'});

      });
  }

  function addProperty(property) {
    const requestOptions = {
      method: 'POST',
      headers: { 
        'Content-Type': 'application/json',
        'Authorization' : 'Bearer ' + JSON.parse(localStorage.getItem('auth')).token
      },
      body: JSON.stringify(property)
    };
    fetch('http://localhost:8080/property', requestOptions)
      .then(response => response.json())
      .then(data => {
        properties.value.push(data);
      });

  }

  function deleteProperty(id) {
    const requestOptions = {
      method: 'DELETE',
      headers: { 
        'Content-Type': 'application/json',
        'Authorization' : 'Bearer ' + JSON.parse(localStorage.getItem('auth')).token
      },
    };
    fetch('http://localhost:8080/property/' + id, requestOptions)
      .then(response => {
        let propertyIndex = properties.value.findIndex(property => property.id === id)
        properties.value.splice(propertyIndex, 1);
      })
  
  }

  return {properties, addProperty, fetchProperties, deleteProperty}
});
