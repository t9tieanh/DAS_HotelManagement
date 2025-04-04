import React, { useState } from "react";
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


const CheckOutPage = ()  => {

    const { pageState, setPageState } = useContext(PaymentContext);

    const handleNextStep = () => {
        if (pageState !== 2) {
            setPageState(prevState => prevState + 1);
        }
    };

    return (
        <Container className="resevation-container mb-5" >
            <div className="row mt-4">
                <div className="col-md-6">
                    {pageState === 0 && <ConfirmInfomationPage handleNextStep = {handleNextStep} />}
                    {pageState === 1 && <PaymentPage handleNextStep = {handleNextStep} />}
                </div>

                <div className="col-md-6">
                    <CustomCard name={'Lịch đặt phòng'} subTitle={'Lưu ý lịch đặt phòng của bạn !'} icon={<SlCalender />} children={<TripDetail/>} />    

                    <CustomCard className={'shadow-3 mt-3 property-card border p-3 mb-3 rounded'} 
                        subTitle={'Thông tin phòng của bạn !'}
                        name={'Phòng của bạn'} icon={<FaHotel /> } children={<RoomInfo />} />


                    <CustomCard className={'shadow-3 mt-3 property-card border p-3 mb-3 rounded'} 
                                            subTitle={'Xem chi tiết hóa đơn của bạn'}
                                            name={'Hóa đơn của bạn'} icon={<MdOutlinePayments /> } children={<BillContainer />} />
                </div>
            </div>
        </Container>
    );
}

export default CheckOutPage;
