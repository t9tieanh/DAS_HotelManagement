import axios from "axios";
import { BASE_URL } from "../../conf/baseUrl";

export const findRoomInHotel = async (checkIn, checkOut, numAdults, numRooms) => {
    try {
        const response = await axios.get(`${BASE_URL}/hotel/result`, {
            params: { checkIn, checkOut, numAdults, numRooms }
        });

        console.log(response.data.result)
        return response.data.result
    }
    catch (error) {
        console.error('Error', error)
        throw error;
    }
};