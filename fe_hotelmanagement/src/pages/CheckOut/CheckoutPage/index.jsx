import React, { useEffect, useState } from "react";
import "./style.scss";
import CustomCard from "../../../components/common/Card";
import Container from 'react-bootstrap/Container';
import { SlCalender } from "react-icons/sl";
import { FaHotel } from "react-icons/fa";
import { MdOutlinePayments } from "react-icons/md";
import ConfirmInfomationPage from "../ConfirmInfomationPage";
import { useContext } from "react";
import PaymentContext from "../../../context/PaymentContext";
import PaymentPage from "../PaymentPage";
import BillContainer from "../../../components/CheckOut/CheckOutPage/Bill";
import TripDetail from "../../../components/CheckOut/CheckOutPage/TripDetail";
import RoomInfo from "../../../components/CheckOut/CheckOutPage/RoomInfo";
import { useSelector } from "react-redux";
import { getCurrentStep } from "../../../services/ReservationService/reservationService";
import { doUpdateExpireDateTime } from "../../../redux/action/reservationAction";
import { useDispatch } from "react-redux";
import { toast } from "react-toastify";
import { doDeleteReservation } from "../../../redux/action/reservationAction";
import { cancelReservation } from "../../../services/ReservationService/reservationService";

const CheckOutPage = () => {
    const reservationId = useSelector(state => state.reservation.reservationId)
    const { pageState, setPageState } = useContext(PaymentContext);

    // thông tin đặt phòng 
    const [checkIn, setCheckIn] = useState()
    const [checkOut, setCheckOut] = useState()
    const [reservationDetail, setReservationDetail] = useState()
    const [discounts, setDiscounts] = useState([])

    const dispatch = useDispatch();

    const handleNextStep = () => {
        if (pageState !== 2) setPageState(prevState => prevState + 1);
    };

    useEffect(
        () => {fetchCurrentStep()} , []
    )

    const fetchCurrentStep = async() => {
        const data = await getCurrentStep(reservationId)
        console.log(data , "step hiện tại") // -> nhớ xóa cái này nhá 

        if (data && data.code && data.code === 200 && data?.result) {

            setPageState(data.result.currentStep)
            dispatch(doUpdateExpireDateTime(data.result.expireDateTime)) // set lại thời gian hết hạn 

            // set thông tin cho reservation 
            setCheckIn(data.result.checkIn)
            setCheckOut(data.result.checkOut)

            //set reservationdetail 
            setReservationDetail(data.result.reservationDetail)
            setDiscounts(data.result.discounts) // chuyển list -> set

        } else if (data.response && data.response.data) {

            toast.error(data.response.data.message) // trường hợp giao dịch hết thời gian
            dispatch(doDeleteReservation())
        }
        else {
            toast.error(data?.message)
            dispatch(doDeleteReservation())
        } 
    }

    const handleCancelReservation = async() => {
        const data = await cancelReservation(reservationId)
        console.log(data , "step hiện tại") // -> nhớ xóa cái này nhá 

        if (data && data.code && data.code === 200 && data?.result && data.result.success === true) {
            dispatch(doDeleteReservation())
            toast('Hủy giao dịch thành công !')

        } else if (data.response && data.response.data) {

            toast.error(data.response.data.message) // trường hợp giao dịch hết thời gian
            dispatch(doDeleteReservation())
            return
        }
        else toast.error(data?.message) 
    }

    return (
        <Container className="resevation-container mb-5" >
            <div className="row mt-4">
                <div className="col-md-6">
                    {pageState === 0 && <ConfirmInfomationPage  handleNextStep={handleNextStep} handleCancelReservation = {handleCancelReservation} discounts = {discounts} setDiscounts = {setDiscounts} />}
                    {pageState === 1 && <PaymentPage handleNextStep={handleNextStep} handleCancelReservation = {handleCancelReservation} />}
                </div>

                <div className="col-md-6">
                    <CustomCard name={'Lịch đặt phòng'} subTitle={'Lưu ý lịch đặt phòng của bạn !'} icon={<SlCalender />} 
                        children={<TripDetail checkIn={checkIn} checkOut={checkOut} />} 
                    />

                    <CustomCard className={'shadow-3 mt-3 property-card border p-3 mb-3 rounded'}
                        subTitle={'Thông tin phòng của bạn !'}
                        name={'Phòng của bạn'} icon={<FaHotel />} children={<>
                                {reservationDetail?.map((room) => {
                                    return <RoomInfo  room={room}/>
                                })}
                            </>} />


                    <CustomCard className={'shadow-3 mt-3 property-card border p-3 mb-3 rounded'}
                        subTitle={'Xem chi tiết hóa đơn của bạn'}
                        name={'Hóa đơn của bạn'} icon={<MdOutlinePayments />} 
                        children={<BillContainer reservationDetails = {reservationDetail} checkIn={checkIn} checkOut={checkOut} />} />
                </div>
            </div>
        </Container>
    );
}

export default CheckOutPage;
