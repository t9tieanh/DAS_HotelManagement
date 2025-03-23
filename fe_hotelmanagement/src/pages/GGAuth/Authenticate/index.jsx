import { useNavigate, useSearchParams } from "react-router-dom";
import LoadingOverLay from "../../../components/common/LoadingOverlay";
import { useDispatch } from "react-redux";
import { useEffect, useState } from "react";
import { exchangeToken } from "../../../services/GGAuth/authService";
import RegisterForm from "../../../components/GgAuth/AuthenticationPage/RegisterForm";
import './style.scss'
import { doDeleteUser, doUpdateUser } from "../../../redux/action/updateUserAction";
import { toast } from "react-toastify";
import { doUpdateAccessToken } from "../../../redux/action/updateTokenAction";

const AuthenticatePage = () => {

    const [searchParams] = useSearchParams();
    const code = searchParams.get("code"); 
    const [isLoading, setIsLoading] = useState(true);
    const dispatch = useDispatch();

    const [name, setName] = useState("");
    const [email, setEmail] = useState("");
    const [imageUrl, setImageUrl] = useState("");
    const navigator = useNavigate()

    useEffect(() => {
        const fetchData = async () => {
            if (code) {
                let data = await exchangeToken(code);
    
                
                if (data && data.code && data.code === 200 && data?.result) {
                    setIsLoading(false);
                    if (data.result.active) { // xử lý khi đây là khách hàng cũ 
                        dispatch(doUpdateUser(data.result))
                        navigator('/')
                        return
                    }

                    dispatch(doDeleteUser())
                    dispatch(doUpdateAccessToken(data.result.accessToken)) // nhận token đặc biệt 

                    setName(data.result.name);
                    setEmail(data.result.email);
                    setImageUrl(data.result.imageUrl);

                    console.log("data sau khi exchance code ",data)
                } else {
                    toast.error(data.message)
                }
            }
        };
    
        fetchData();
    }, [code]);

    console.log(code)

    return (
        <>
            <LoadingOverLay isLoading={isLoading} text={`Đang xác thực với Google, xin vui lòng chờ !`} icon = {<GGIcon/>} />

            <section className="register-content">
                <div className="px-4 py-5 px-md-5 text-center text-lg-start">
                    <div className="container">
                        <div className="row gx-lg-5 align-items-center">
                            <div className="col-lg-6 mb-5 mb-lg-0">
                                <h1 className="my-5 display-3 fw-bold ls-tight">
                                    DAS <br />
                                    <span className="text-primary">Hotel</span>
                                </h1>
                                <p style={{ color: "hsl(217, 10%, 50.8%)" }}>
                                    Trải nghiệm dịch vụ khách sạn tuyệt vời
                                </p>
                            </div>

                            <div className="col-lg-6 mb-5 mb-lg-0">
                                <div className="card">
                                    <div className="card-body py-5 px-md-5">
                                        { !isLoading && <RegisterForm name = {name} email = {email} imgUrl = {imageUrl} setImgUrl={setImageUrl} /> }
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </section>
        </>
    )
}

export default AuthenticatePage


export const GGIcon = () => {
    return <>
    <a
        href="#"
        className="btn-floating mx-1"
    > <i className="fab fa-google"></i></a>
    </>
}
