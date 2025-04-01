import axios from "../../utils/CustomAxios";

export const findRoomInHotel = async (checkIn, checkOut, numAdults, numRooms, page, size) => {
    return await axios.get(`hotel/search-hotel`, {
        params: { checkIn, checkOut, numAdults, numRooms, page, size }
    });
};