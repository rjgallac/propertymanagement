
import { ref } from 'vue';
import router from '@/router/index.ts'

import { defineStore } from 'pinia';
import { useLoginStore } from '@/stores/loginStore';

const loginStore = useLoginStore();

export const useBookingStore = defineStore('booking', () => {
  let bookings = ref([]);
  let token = JSON.parse(localStorage.getItem('auth'));
  let jwt = token.token;
  const requestOptions = {
    method: 'GET',
    withCredentials: true,
    headers: { 
      'Content-Type' : 'application/json',
      'Authorization' : 'Bearer ' + jwt
    },
  };
  function fetchBookings(){
    fetch('http://localhost:8081/booking', requestOptions)
    .then(response => response.json())
    .then(data => {
      bookings.value = data;
    })
    .catch(error => {
      console.error(error);
      loginStore.logout();
      router.push({path:'/'});
    });
  }

  function addBooking(booking){
    let token = JSON.parse(localStorage.getItem('auth'));
    let jwt = token.token;
    const requestOptions = {
      method: 'POST',
      headers: { 
        'Content-Type': 'application/json',
        'Authorization' : 'Bearer ' + JSON.parse(localStorage.getItem('auth')).token
      },
      body: JSON.stringify(booking)
    };
    fetch('http://localhost:8081/booking', requestOptions)
      .then(response => response.json())
      .then(data => {
          bookings.value.push(data);
      });

  }

  function deleteBooking(id) {
    const requestOptions = {
        method: 'DELETE',
        headers: { 
          'Content-Type': 'application/json',
          'Authorization' : 'Bearer ' + JSON.parse(localStorage.getItem('auth')).token
        },
      };
      fetch('http://localhost:8081/booking/' + id, requestOptions)
        .then(response => {
          let bookingIndex = bookings.value.findIndex(booking => booking.id === id)
          bookings.value.splice(bookingIndex, 1);
        })
  }

  return {bookings, addBooking, fetchBookings, deleteBooking}
});
