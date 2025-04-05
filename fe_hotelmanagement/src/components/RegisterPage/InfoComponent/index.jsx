import React, { useState, useEffect } from "react";
import "./style.scss";
import { toast } from "react-toastify";
import "react-toastify/dist/ReactToastify.css";
import VerifyOTP from "../../../pages/VerifyOTP";
import ImageComponent from "../ImageComponent";
import { registerService, sendOtp } from "../../../services/RegisterService/registerService";
import { useNavigate } from "react-router-dom";

const InfoComponent = () => {

    const navigate = useNavigate();

    const [formData, setFormData] = useState({
        name: "",
        email: "",
        phone: "",
        username: "",
        password: "",
        confirmPassword: "",
    });

    const handleChange = (e) => {
        const { name, value } = e.target;
        setFormData({ ...formData, [name]: value });
    };

    const handleSubmit = async (e) => {
        e.preventDefault();

        if (!formData.name.trim()) {
            toast.error("Tên không được để trống");
            return;
        }
        if (!formData.email.trim()) {
            toast.error("Email không được để trống");
            return;
        }
        if (!formData.phone.trim()) {
            toast.error("Số điện thoại không được để trống");
            return;
        }
        if (!formData.username.trim()) {
            toast.error("Tên đăng nhập không được để trống");
            return;
        }
        if (!formData.password.trim()) {
            toast.error("Mật khẩu không được để trống");
            return;
        }
        if (!formData.confirmPassword.trim()) {
            toast.error("Vui lòng nhập lại mật khẩu");
            return;
        }

        const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
        if (!emailRegex.test(formData.email)) {
            toast.error("Email không hợp lệ");
            return;
        }

        const phoneRegex = /^[0-9]{10,11}$/;
        if (!phoneRegex.test(formData.phone)) {
            toast.error("Số điện thoại không hợp lệ (chỉ chứa 10-11 chữ số)");
            return;
        }

        if (formData.password.length < 0) {
            toast.error("Mật khẩu phải có ít nhất 8 ký tự");
            return;
        }

        if (formData.password !== formData.confirmPassword) {
            toast.error("Mật khẩu không khớp");
            return;
        }

        const data = new FormData();
        data.append("name", formData.name);
        data.append("email", formData.email);
        data.append("phone", formData.phone);
        data.append("username", formData.username);
        data.append("password", formData.password);

        try {
            const data = await sendOtp(formData.email)
            if (data && data.code && data.code == 200) {
                toast.info("Vui lòng xác thực email!");
                navigate('/verify-otp', { state: { formData } });
            }
            else {
                toast.error("Gửi mail thất bại!")
            }
        } catch (error) {
            toast.error("Đăng ký thất bại");
        }
    };

    return (
        <>
            <ImageComponent />

            <div className="info-card-body p-4 p-md-5">

                <h3 className="mb-4 pb-2 pb-md-0 mb-md-5 px-md-2 text-center">Thông tin đăng ký</h3>

                <form className="px-md-2" onSubmit={handleSubmit}>
                    <div className="form-outline mb-4">
                        <input
                            type="text"
                            id="form3Example1q"
                            className="form-control"
                            name="name"
                            value={formData.name}
                            onChange={handleChange}
                        />
                        <label className="form-label" htmlFor="form3Example1q">Tên</label>
                    </div>

                    <div className="form-outline mb-4">
                        <input
                            type="email"
                            id="form3Example2q"
                            className="form-control"
                            name="email"
                            value={formData.email}
                            onChange={handleChange}
                        />
                        <label className="form-label" htmlFor="form3Example2q">Email</label>
                    </div>

                    <div className="form-outline mb-4">
                        <input
                            type="text"
                            id="form3Example3q"
                            className="form-control"
                            name="phone"
                            value={formData.phone}
                            onChange={handleChange}
                        />
                        <label className="form-label" htmlFor="form3Example3q">Số điện thoại</label>
                    </div>

                    <div className="form-outline mb-4">
                        <input
                            type="text"
                            id="form3Example4q"
                            className="form-control"
                            name="username"
                            value={formData.username}
                            onChange={handleChange}
                        />
                        <label className="form-label" htmlFor="form3Example4q">Tên đăng nhập</label>
                    </div>

                    <div className="form-outline mb-4">
                        <input
                            type="password"
                            id="form3Example5q"
                            className="form-control"
                            name="password"
                            value={formData.password}
                            onChange={handleChange}
                        />
                        <label className="form-label" htmlFor="form3Example5q">Mật khẩu</label>
                    </div>

                    <div className="form-outline mb-4">
                        <input
                            type="password"
                            id="form3Example6q"
                            className="form-control"
                            name="confirmPassword"
                            value={formData.confirmPassword}
                            onChange={handleChange}
                        />
                        <label className="form-label" htmlFor="form3Example6q">Nhập lại mật khẩu</label>
                    </div>

                    <div className="text-center">
                        <button type="submit" className="btn btn-primary btn-lg mb-1">Đăng ký</button>
                    </div>
                </form>
            </div>
        </>
    );
};

export default InfoComponent;