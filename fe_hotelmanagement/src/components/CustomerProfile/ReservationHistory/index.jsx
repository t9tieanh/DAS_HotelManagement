import React from "react";
import './style.scss';
import { Container } from "react-bootstrap";
import { FaHotel } from "react-icons/fa";
import CustomCard from "../../common/Card";
import RoomInfo from "../RoomInfo";
import { getReservationHistory } from "../../../services/ReservationService/reservationService";
import { useEffect, useState } from "react";

const ReservationHistory = () => {

    const [reservation, setReservation] = useState([]);

    useEffect(() => {
        const fetchData = async () => {
            const response = await getReservationHistory();
            setReservation(response.result);
        }

        fetchData();
    }, []);


    return (
        <div className="reservation-history">
            <CustomCard
                name={'Lịch sử đặt phòng'}
                icon={''} children={''} >
                {reservation.map((res, index) => (
                    <CustomCard
                        key={index}
                        className="shadow-3 mt-0 property-card border p-2 mb-2 rounded"
                        icon={<FaHotel />}
                        children={<RoomInfo data={res} />}
                    />
                ))}

            </CustomCard>
        </div >
    )
}

export default ReservationHistory;