import axios from "../../utils/CustomAxios";

const getHotelDetail = async (id) => {
    return await axios.get(`hotel/detail/${id}`)
}

export {getHotelDetail}