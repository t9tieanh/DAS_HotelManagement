import React from "react";
import './style.scss';
import { Container } from "react-bootstrap";
import { FaHotel } from "react-icons/fa";
import CustomCard from "../../common/Card";
import RoomInfo from "../RoomInfo";

const ReservationHistory = () => {
    return (

        <div className="reservation-history">
            <CustomCard
                // className={}
                name={'Lịch sử đặt phòng'}
                icon={''} children={''} >

                <CustomCard className={'shadow-3 mt-0 property-card border p-2 mb-2 rounded'}
                    icon={<FaHotel />} children={<RoomInfo />} />

                <CustomCard className={'shadow-3 mt-0 property-card border p-2 mb-2 rounded'}
                    icon={<FaHotel />} children={<RoomInfo />} />

                <CustomCard className={'shadow-3 mt-0 property-card border p-2 mb-2 rounded'}
                    icon={<FaHotel />} children={<RoomInfo />} />
            </CustomCard>
        </div >
    )
}

export default ReservationHistory;