import  "./style.scss";
import { toast } from 'react-toastify';
import { useDispatch } from "react-redux";
import { useNavigate } from "react-router-dom";
import { useState, useEffect } from "react";
import { activeGGAccount } from "../../../../services/GGAuth/authService";

const RegisterForm = ({ name, email, imgUrl , setImgUrl }) => {

    const [formData, setFormData] = useState({
        username:"",
        password: "",
        confirmPassword: "",
        file: null,
    });

    const navigator = useNavigate()

    const [isLoadingVerify,setIsLoadingVerify] = useState(false)
    const [imageSrc, setImageSrc] = useState(imgUrl);

    const handleChange = (e) => {
        const { name, value } = e.target;
        setFormData({ ...formData, [name]: value });
    };


    const convertImageUrlToFile = async (url) => {
        try {
            const response = await fetch(url);
            const blob = await response.blob();
            const file = new File([blob], "profile-image.jpg", { type: blob.type });
            setFormData((prev) => ({ ...prev, file }));
        } catch (error) {
            console.error("Lỗi khi chuyển đổi ảnh:", error);
        }
    };

    useEffect(() => {
        if (imgUrl) {
            convertImageUrlToFile(imgUrl);
            setImageSrc(imgUrl);
        }
    }, [imgUrl]);



    const handleSubmit = async(e) => {
        e.preventDefault();
        
        if (formData.password !== formData.confirmPassword){
            toast.error("Mật khẩu không khớp !");
            return
        }

        setIsLoadingVerify(true)

        const data = await activeGGAccount(formData.file, email, name, formData.username, formData.password)

        setIsLoadingVerify(false)

        if (data && data.code && data.code == 200 && data.result === true) {
            toast.success(data?.message)
            toast.success("Please log in again !")
            navigator("/login")
        } else if (data.response && data.response.data) {
            console.log(data)
            toast.error(data.response.data.message)
            return
        }
        else toast.error(data?.message)
    };

    return (
        <>
        
        <form id="registrationForm shadow-3" className="register-form" onSubmit={handleSubmit}>
            <div className="row m-4 text-center">
                <div className="text-welcome">
                    <h2 class="mb-4">Tiếp tục Đăng ký</h2>
                    <p class="mb-0" >Xin chào @{name} ! Nhập username và password để tiếp tục.</p>
                </div>

                <div className="w-100"></div>
                <div className="col-4 mx-auto " style={{ marginTop: "15px", marginBottom: "15px" }}>
                    <img
                        src={imageSrc}
                        className="img-responsive product-img" alt="Image" 
                    />
                </div>
                <h3 className="mb-1 text-center ">{name}</h3>
            </div>

            <input
                name="username"
                type="username"
                className="form-control mb-4"
                placeholder="Username"
                value={formData.username}
                onChange={handleChange}
            />

            <div className="row">
                <div className="col-md-6 mb-4">
                    <input
                        className="form-control"
                        name="password"
                        type="password"
                        placeholder="Password"
                        value={formData.password}
                        onChange={handleChange}
                    />
                </div>
                <div className="col-md-6">
                    <input
                        name="confirmPassword"
                        type="password"
                        className="form-control"
                        placeholder="Confirm Password"
                        value={formData.confirmPassword}
                        onChange={handleChange}
                    />
                </div>
            </div>

            <button type="submit" className="btn btn-primary btn-block mb-4">
                {isLoadingVerify && <i class="fa-solid fa-spinner loaderIcon" style={{marginRight:"10px"}}> </i>} 
                Sign up
            </button>
        </form>
        
        
        </>
    )
}

export default RegisterForm