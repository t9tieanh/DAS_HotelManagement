import axios from "../../utils/CustomAxios";

export const createReservation = async (checkIn, checkOut, reservationDetails) => {

  const requestData = {
    checkIn,
    checkOut,
    reservationDetails,
  };

  return await axios.post('/reservation', requestData);
};

export const updateReservationInfo = async (reservationId, name, phone, email, appliedDiscounts) => {

  const request = {
    reservationId,
    name,
    phone,
    email,
    appliedDiscounts
  };

  return await axios.post('/reservation/update-info', request);
}


export const cancelReservation = async (id) => {
  return await axios.delete(`/reservation/${id}`);
};


export const getCurrentStep = async (id) => {
  return await axios.get('/reservation/current-step', {
    params: {
      id: id,
    },
  });
}

export const reservationSuccess = async (reservationId) => {

}



