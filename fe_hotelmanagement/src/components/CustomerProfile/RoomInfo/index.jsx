import React, { useState } from "react";
import "./style.scss";


const RoomInfo = () => {
    return (
        <>
            <div className="d-flex align-items-start">
                <img
                    src="https://acihome.vn/uploads/15/thiet-ke-khach-san-kieu-phap-anh-bia-danh-muc.jpg"
                    alt="Property"
                    className="img-fluid me-3"
                    style={{ width: "auto", height: "150px", borderRadius: "4px" }}
                />

                <div className="flex-grow-1">

                    <h5 className="fw-bold mt-2 mb-1">
                        Khách sạn Franxe
                    </h5>

                    <p className="text-muted mb-1">
                        <strong>Địa chỉ </strong>: 208 Nguyen Huu Canh Street, 22 Ward, Bình Thạnh District
                    </p>

                    <div className="d-flex flex-column mb-2">
                        <div className="d-flex align-items-center">
                            <strong>Dịch vụ:</strong>
                            <span className="badge bg-info text-white me-2 ms-2">Ăn sáng</span>
                            <span className="badge bg-success text-white">Đưa đón sân bay</span>
                        </div>
                    </div>
                    <p className="text-muted mb-2"><strong>Loại phòng:</strong> Standard</p>

                    <p className="text-muted mb-2"><strong>Tổng tiền:</strong> 1.500.000 VNĐ</p>

                </div>
            </div>
        </>
    )
}

export default RoomInfo