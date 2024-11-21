
import { ref } from 'vue';

import { defineStore } from 'pinia';

export const useTenantStore = defineStore('tenant', () => {
  let tenants = ref([]);
  const requestOptions = {
    method: 'GET',
    headers: { 'Content-Type': 'application/json' },
};
  function fetchTenants(){
    fetch('http://localhost:8081/tenant', requestOptions)
    .then(response => response.json())
    .then(data => {
      tenants.value = data;
    });
  }

  function addTenant(tenant){
    const requestOptions = {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(tenant)
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

  return {tenants, addTenant, fetchTenants, deleteTenant}
});
