import React, { useState, useEffect, useRef } from "react";
import Countdown from "react-countdown";
import "./style.scss";
import OtpInput from 'react-otp-input';
import { register, verifyOtp, sendOtp, getTTL } from "../../services/RegisterService/registerService";
import { toast } from "react-toastify";
import "react-toastify/dist/ReactToastify.css";
import { useNavigate, useLocation } from "react-router-dom";
import OtpCountDown from "../../components/VerifyOTP/OtpCountDown";

const VerifyOTP = () => {

  const [otp, setOtp] = useState("");
  const navigate = useNavigate();
  const location = useLocation();
  const formData = location.state?.formData;
  
  const handleSubmit = async () => {
    try {
      const verifyResponse = await verifyOtp(formData.email, otp);

      if (verifyResponse && verifyResponse.code && verifyResponse.code == 200) {
        toast.success(verifyResponse.result);

        const registerResponse = await register(formData);

        if (registerResponse && registerResponse.code && registerResponse.code == 200) {
          toast.success("Đăng ký tài khoản thành công!");
          navigate("/login");
        } else {
          toast.error(registerResponse?.result || "Đăng ký thất bại!");
        }
      } else {
        toast.error(verifyResponse?.result || "Xác thực OTP thất bại!");
      }
    } catch (error) {
      console.error("Lỗi khi xử lý OTP:", error);
      toast.error("Đã xảy ra lỗi, vui lòng thử lại!");
    }
  };

  const handleResend = async () => {
    try {
      const data = await sendOtp(formData.email)
      if (data && data.code === 200) {
        toast.success(data.result)
      }
      else {
        toast.error(data.result)
      }
    }
    catch (error) {
      console.error("Lỗi khi gửi mail", error)
      toast.error("Đã xảy ra lỗi, vui lòng thử lại!");
    }
  }


  return (
    <div className="verify-otp-container">
      <div className="card-otp">
        <div className="card text-center form-otp hover-shadow">
          <div className="card-header">Xác nhận mã OTP</div>
          <i className="fa-solid fa-shield-halved"></i>
          <div className="card-body">
            <OtpCountDown email={formData?.email} />
            <div className="verify-otp-input">
              <OtpInput
                value={otp}
                numInputs={6}
                onChange={setOtp}
                renderSeparator={<span></span>}
                renderInput={(props) => <input {...props} />}
              />
            </div>
            <button onClick={handleSubmit} className="btn btn-primary" data-mdb-ripple-init>
              Xác nhận
            </button>
            <p className="card-text">Chưa nhận được mã OTP? <span className="high-ligt-text" onClick={handleResend}>Gửi lại tại đây</span></p>
          </div>
          <div className="card-footer text-muted">OTP sẽ hết hạn sau 5 phút</div>
        </div>
      </div>
    </div>
  );
};

export default VerifyOTP;
