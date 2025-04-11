import React, { useState, useEffect } from "react";
import "./style.scss";
import { toast } from "react-toastify";
import "react-toastify/dist/ReactToastify.css";
import VerifyOTP from "../../../pages/VerifyOTP";
import ImageComponent from "../ImageComponent";
import { registerService, sendOtp } from "../../../services/RegisterService/registerService";
import { useNavigate } from "react-router-dom";
import TextInput from "../../common/Input/Input2";
import PrimaryButton from "../../common/button/btn-primary";

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

            <div className="info-card-body p-md-5">

                <h3 className="mb-4 pb-2 pb-md-0 mb-md-5 px-md-2 text-center">Thông tin đăng ký</h3>

                <form className="px-md-2" onSubmit={handleSubmit}>
                    <TextInput
                        name="Tên của bạn"
                        placeholder="Nhập tên của bạn"
                        value={formData.name}
                        setValue={(value) => setFormData({ ...formData, name: value })}
                        idInput="form3Example1q"
                        inputType="text"
                    />

                    <TextInput
                        name="Email của bạn"
                        placeholder="Nhập email của bạn"
                        value={formData.email}
                        setValue={(value) => setFormData({ ...formData, email: value })}
                        idInput="form3Example2q"
                        inputType="email"
                    />

                    <TextInput
                        name="Số điện thoại của bạn"
                        placeholder="Nhập số điện thoại của bạn"
                        value={formData.phone}
                        setValue={(value) => setFormData({ ...formData, phone: value })}
                        idInput="form3Example3q"
                        inputType="tel"
                    />

                    <TextInput
                        name="Tên đăng nhập"
                        placeholder="Nhập tên đăng nhập"
                        value={formData.username}
                        setValue={(value) => setFormData({ ...formData, username: value })}
                        idInput="form3Example4q"
                        inputType="text"
                    />

                    <TextInput
                        name="Mật khẩu"
                        placeholder="Nhập mật khẩu"
                        value={formData.password}
                        setValue={(value) => setFormData({ ...formData, password: value })}
                        idInput="form3Example5q"
                        inputType="password"
                    />

                    <TextInput
                        name="Nhập lại mật khẩu"
                        placeholder="Nhập lại mật khẩu"
                        value={formData.confirmPassword}
                        setValue={(value) => setFormData({ ...formData, confirmPassword: value })}
                        idInput="form3Example6q"
                        inputType="password"
                    />

                    <PrimaryButton text={'Đăng ký'} className={'btn-lg mt-4'} />
                </form>
            </div>
        </>
    );
};

export default InfoComponent;