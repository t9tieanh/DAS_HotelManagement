import axios from "../../utils/CustomAxios";

export const paymentWithVnPay = async (amount) => {
    try {
        alert(amount)
        const response = await axios.get(`api/v1/payment/vn-pay?amount=${amount}`);
        const paymentUrl = response.result?.paymentUrl;
        console.log('URL thanh toán VNPay:', paymentUrl);
        window.location.href = paymentUrl;


        // Stop
        window.onload = function () {
            alert("onload")
            if (window.location.pathname.includes("/api/v1/payment/vn-pay-callback")) {
                alert("callback")
                fetch(window.location.href)
                    .then(response => response.json())
                    .then(callbackData => {
                        if (callbackData.code === 200 && callbackData.data && callbackData.data.code === "00") {
                            alert("Payment success");
                        } else {
                            alert("Payment failed!");
                        }
                    })
                    .catch(error => console.log("Error:", error));
            }
        };

    }
    catch (error) {
        console.error('Lỗi khi thanh toán bằng VNPay:', error);
        throw error;
    }
}