import axios from "../../utils/CustomAxios";

export const findRoomInHotel = async (checkIn, checkOut, numAdults, numRooms, page, size, location) => {
    const params = {
        checkIn,
        checkOut,
        numAdults,
        numRooms,
        page,
        size
    };

    if (location) {
        params.location = location;
    }

    return await axios.get(`hotel/search-hotel`, { params });
};