import { useEffect } from "react";
import { useLocation } from "react-router-dom";

const VNPayCallback = () => {
    const location = useLocation();

    useEffect(() => {
        if (window.location.href.includes("vn-pay-callback")) {
            console.log("Here")

            const urlParams = new URLSearchParams(window.location.search);
            const code = urlParams.get("code");
            const message = urlParams.get("message");

            if (code === "00") {
                alert("Thanh toán thành công")
            }
            else {
                alert("Thanh toán thất bại")
            }
        }
    }, [location]);

}
export default VNPayCallback;