import logo from "../../../assets/img/logo.png"
import './style.scss'
import Alert from 'react-bootstrap/Alert';
import Countdown from "react-countdown";
import { IoIosTime } from "react-icons/io";
import { useNavigate } from "react-router-dom";

const Header = ({pageState, expireDateTime}) => {

    const navigator = useNavigate()

    return (
        <>
        
            <div className="header d-flex justify-content-center align-items-center p-3">
                    <div className="logo" onClick={() => {navigator('/')}}>
                        <img src={logo} alt="Agoda Logo" className="logo-img" />
                    </div>
                    <div className="steps d-flex align-items-center justify-content-center">
                        <div className={`step ${pageState >= 0 && 'active'}`}>
                            <div className="circle">1</div>
                            <span>Thông tin khách hàng</span>
                        </div>
                        <div className="line"></div>
                        <div className={`step ${pageState >= 1 && 'active'}`}>
                            <div className="circle">2</div>
                            <span>Chi tiết thanh toán</span>
                        </div>
                        <div className="line"></div>
                        <div className={`step ${pageState === 2 && 'active'}`}>
                            <div className="circle">3</div>
                            <span>Đã xác nhận đặt phòng!</span>
                        </div>
                    </div>
                </div>

            <Alert variant="danger" className="text-center">
                Chúng tôi đang giữ phòng cho quý khách <IoIosTime /> <Countdown date={expireDateTime} />
            </Alert>
        
        </>
    )
}

export default Header