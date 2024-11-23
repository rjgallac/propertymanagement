
import { ref } from 'vue';

import { defineStore } from 'pinia';

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
    .catch(error => console.error(error));;
  }

  function addBooking(booking){
    let token = JSON.parse(localStorage.getItem('auth'));
    let jwt = token.token;
    const requestOptions = {
      method: 'POST',
      headers: { 
        'Content-Type': 'application/json',
        'Authorization': 'Bearer ' + jwt
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
        headers: { 'Content-Type': 'application/json' },
      };
      fetch('http://localhost:8081/booking/' + id, requestOptions)
        .then(response => {
          let bookingIndex = bookings.value.findIndex(booking => booking.id === id)
          bookings.value.splice(bookingIndex, 1);
        })
  }

  return {bookings, addBooking, fetchBookings, deleteBooking}
});
