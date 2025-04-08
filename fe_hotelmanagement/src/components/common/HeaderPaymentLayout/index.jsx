import logo from "../../../assets/img/logo.png"
import './style.scss'
import Alert from 'react-bootstrap/Alert';
import Countdown from "react-countdown";
import { IoIosTime } from "react-icons/io";
import { useNavigate } from "react-router-dom";
import { doDeleteReservation } from "../../../redux/action/reservationAction";
import { Toast } from "react-bootstrap";
import { cancelReservation } from "../../../services/ReservationService/reservationService";
import { useSelector } from "react-redux";
import { toast } from "react-toastify";
import { useDispatch } from "react-redux";

const Header = ({pageState, expireDateTime}) => {

    const reservationId = useSelector(state => state.reservation.reservationId)
    const navigator = useNavigate()
    const dispatch = useDispatch()

    const handleCancelReservation = async () => {
        const data = await cancelReservation(reservationId)
        if (data && data.code && data.code === 200 && data?.result) {
            toast.success("Hủy giao dịch thành công")
        } else if (data.response && data.response.data) {
            toast.error(data.response.data.message) // trường hợp giao dịch hết thời gian
        }
        else {
            toast.error(data?.message)
        } 
        dispatch(doDeleteReservation()) // xóa giao dịch phòng khỏi local storage
    }

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
                Chúng tôi đang giữ phòng cho quý khách <IoIosTime /> <Countdown onComplete={handleCancelReservation} date={expireDateTime} />
            </Alert>
        
        </>
    )
}

export default Header