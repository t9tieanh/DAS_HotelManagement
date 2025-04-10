import axios from "../../utils/CustomAxios";

// tạo đặt phòng -> bước 1
export const createReservation = async (checkIn, checkOut, reservationDetails) => {
    
  const requestData = {
      checkIn,
      checkOut,
      reservationDetails,
    };

    return await axios.post('/reservation', requestData);
};


// cập nhật thông tin đặt phòng -> bước 2 
export const updateReservationInfo = async (reservationId, name, phone, email) => {

  const request = {
    reservationId,
    name,
    phone,
    email
  };

  return  await axios.post('/reservation/update-info', request);
} 


// hủy đặt phòng
export const cancelReservation = async (id) => {
    return await axios.delete(`/reservation/${id}`);
};


// tạo request lấy những thông tin cần thiết cho việc thanh toán tử server 
export const getCurrentStep = async (id) => {
  return await axios.get('/reservation/current-step', {
    params: {
      id: id,
    },
  });
}


// apply discount
export const applyDiscount = async (reservationId, discountCodes) => {
  return await axios.post('/reservation/apply-discount', {
    reservationId,
    discountCodes
  });
};

