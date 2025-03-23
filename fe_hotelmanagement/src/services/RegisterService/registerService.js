import axios from "../../utils/CustomAxios";

const register = async (userData) => {
    return await axios.post(`auth/sign-up`, userData, {
        headers: {
            "Content-Type": "multipart/form-data"
        }
    })
}

export {register}