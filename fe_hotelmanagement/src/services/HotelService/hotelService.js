import axios from "../../utils/CustomAxios";

const getHotelDetail = async (id, adults, rooms, checkIn, checkOut) => {
    return await axios.get(`hotel/detail/${id}`, {
        params: {
            adults: adults,
            rooms: rooms,
            checkIn: checkIn,
            checkOut: checkOut
        }
    });
}


const getImageCategory = async (id) => {
    return await axios.get(`hotel/image-category/${id}`)
}

const getHotelImages = async (hotelId, imageType) => {
    return await axios.get('hotel/image-category', {
        params: {
            hotelId: hotelId,
            imageType: imageType
        }
    });
}

export { getHotelDetail, getImageCategory, getHotelImages }