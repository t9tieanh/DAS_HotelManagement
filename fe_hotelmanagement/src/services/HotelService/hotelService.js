import axios from "../../utils/CustomAxios";

const getHotelDetail = async (id) => {
    return await axios.get(`hotel/detail/${id}`)
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

export {getHotelDetail, getImageCategory, getHotelImages}