
import { ref } from 'vue';

import { defineStore } from 'pinia';

export const useBookingStore = defineStore('booking', () => {
  let bookings = ref([]);
  const requestOptions = {
    method: 'GET',
    headers: { 'Content-Type': 'application/json' },
    };
  function fetchBookings(){
    fetch('http://localhost:8081/booking', requestOptions)
    .then(response => response.json())
    .then(data => {
      bookings.value = data;
    });
  }

  function addBooking(booking){
    const requestOptions = {
      method: 'POST',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify(booking)
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

  return {bookings, addBooking, fetchBookings, deleteBooking}
});
