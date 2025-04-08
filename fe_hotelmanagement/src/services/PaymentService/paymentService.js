import axios from "../../utils/CustomAxios";

export const paymentWithVnPay = async (amount) => {
    const response = await axios.get(`api/v1/payment/vn-pay?amount=${amount}`);
    const paymentUrl = response.result?.paymentUrl;
    // window.location.href = paymentUrl;
    window.open(paymentUrl, '_blank')
}

export const callBackVNPay = async (callbackUrl) => {
    alert(callbackUrl)
    const response = await axios.get('/api/v1/payment/vn-pay-callback', {
        params: { vnp_ResponseCode: '00' }
    });
    return response;
}