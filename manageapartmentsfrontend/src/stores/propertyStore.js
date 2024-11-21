
import { ref } from 'vue';

import { defineStore } from 'pinia';

export const usePropertyStore = defineStore('property', () => {
  const properties = ref([]);

  function addProperty(property){
    this.properties.push(property);

  }
  return {properties, addProperty}
});
