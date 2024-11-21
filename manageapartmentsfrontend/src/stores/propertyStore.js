
import { ref } from 'vue';

import { defineStore } from 'pinia';

export const usePropertyStore = defineStore('property', () => {
  let properties = ref([]);
  const requestOptions = {
    method: 'GET',
    headers: { 'Content-Type': 'application/json' },
};
  function fetchProperties(){
    fetch('http://localhost:8080/property', requestOptions)
      .then(response => response.json())
      .then(data => {
        properties.value = data
      });
  }

  function addProperty(property) {
    const requestOptions = {
      method: 'POST',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify(property)
    };
    fetch('http://localhost:8080/property', requestOptions)
      .then(response => response.json())
      .then(data => {
        this.properties.push(property);
      });

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

  return {properties, addProperty, fetchProperties, deleteProperty}
});
