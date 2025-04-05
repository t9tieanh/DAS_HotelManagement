import axios from "../../utils/CustomAxios";
import qs from "qs";

export const register = async (userData) => {
    return await axios.post(`auth/sign-up`, userData, {
        headers: {
            "Content-Type": "multipart/form-data"
        }
    })
}


export const sendOtp = async (email) => {
    return await axios.post(`otp/send`, qs.stringify({ email }), {
        headers: {
            "Content-Type": "application/x-www-form-urlencoded"
        }
    });
};

export const verifyOtp = async (email, otpInput) => {
    return await axios.post(`otp/verify`, qs.stringify({ email, otpInput }), {
        headers: {
            "Content-Type": "application/x-www-form-urlencoded"
        }
    });
};

export const getTTL = async (email) => {
    return await axios.get(`otp/get-ttl`, { params: { email } })
}