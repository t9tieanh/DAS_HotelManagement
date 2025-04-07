import React, { useState } from "react";
import "./style.scss";
import { BASE_URL } from "../../../../conf/baseUrl";
import { convertAddressToString } from "../../../../utils/Format/AddressFormat";
import { Badge } from "react-bootstrap";
import Button from 'react-bootstrap/Button';
import Card from 'react-bootstrap/Card';


const RoomInfo = ({room}) => {
    const fileUrl = 'files/image'

    return (
        <div className="p-0 mt-3 room-info">
            {/* Property Card #2 */}
            <div className="property-card border rounded room-info">
                <Card>
                    <Card.Img variant="top" src={`${BASE_URL}/${fileUrl}/${room.hotelImgUrl}`} />
                    <Card.Body>
                        <Card.Title className="text-dark">
                            <h5 className="mt-0 mb-1"><Badge bg="warning text-dark">Thông tin khách sạn của bạn</Badge></h5>
                            {room?.hotelName}
                            {/* <h5 className="price">{room?.price} VND</h5> */}
                        </Card.Title>
                        <Card.Text>
                            <p className="text-muted mb-2">
                                Địa chỉ: {convertAddressToString(room?.address)}
                            </p>
                        </Card.Text>
                    </Card.Body>
                </Card>
            </div>

            <div className="d-flex align-items-start shadow-4 p-0 mt-1 pb-0 hotel-info">
                {/* Hình ảnh */}
                <img
                    src={`${BASE_URL}/${fileUrl}/${room?.imgUrl}`}
                    alt="Property"
                    className="img-fluid me-3"
                    style={{ width: "auto", height: "150px", borderRadius: "4px" }} 
                />

                <div className="flex-grow-1">
                    <div className="d-flex align-items-center mb-2">
                        <span className="badge bg-primary text-white mt-2 me-2">Thông tin phòng bạn đã chọn</span>
                        <span className="badge bg-success text-white mt-2">Đặt phòng Hotelas</span>
                    </div>

                    {/* Tên khách sạn / Căn hộ */}
                    <h5 className="fw-bold mt-2">
                        {room?.name}
                    </h5>

                    <p className="text-muted mb-2">
                        Mô tả: {room.description}
                    </p>
                </div>
            </div>
        
        
        </div>
    )
}

export default RoomInfo