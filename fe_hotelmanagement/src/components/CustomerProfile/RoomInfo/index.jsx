import React from "react";
import "./style.scss";
import { BASE_URL } from "../../../conf/baseUrl";


const RoomInfo = (data) => {
    const fileUrl = 'files/image'
    return (
        <div className="room-info d-flex align-items-start gap-3">
            <img
                src={`${BASE_URL}/${fileUrl}/${data.data?.img}`}
                alt="Property"
                className="img-fluid property-image"
            />

            <div className="flex-grow-1">
                <h5 className="fw-bold mt-2 mb-1">{data.data.hotelName}</h5>
                <div className="text-description mb-2">
                    <p className="text-muted mb-1"><strong>Địa chỉ:</strong> {data.data.address.concrete + "," +
                        data.data.address.commune + "," + data.data.address.district + "," + data.data.address.city}</p>

                    <div className="d-flex flex-wrap align-items-center mb-2">
                        <strong>Dịch vụ:</strong>
                        <span className="badge bg-info text-white mx-2">Ăn sáng</span>
                        <span className="badge bg-success text-white">Đưa đón sân bay</span>
                    </div>

                    <p className="text-muted mb-2"><strong>Loại phòng:</strong> {data.data.roomTypeName}</p>
                    <p className="text-muted mb-2"><strong>Tổng tiền:</strong> {data.data.totalPrice} VNĐ</p>
                    <p className="text-muted mb-2"><strong>Ngày đặt:</strong> {data.data.reservationDate}</p>
                    <p className="text-muted mb-2"><strong>Ngày nhận phòng:</strong> {data.data.checkInDate}</p>
                    <p className="text-muted mb-2"><strong>Ngày trả phòng:</strong> {data.data.checkOutDate}</p>
                </div>
            </div>
        </div>
    );
};

export default RoomInfo;