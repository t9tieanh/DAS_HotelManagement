import axios from "../../utils/CustomAxios";

export const getDiscountAvailable = async () => {
    return await axios.get(`/promotion/available`)
};