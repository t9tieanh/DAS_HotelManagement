import React, { useState } from "react";
import "./style.scss";


const RoomInfo = () => {
    return (
        <>
            {/* Các badge */}
            <div className="d-flex align-items-start">
                {/* Hình ảnh */}
                <img
                    src="https://nhuminhplazahotel.com/wp-content/uploads/2023/06/khach-san-bai-bien-my-khe-2.jpg" // Đổi link ảnh thật
                    alt="Property"
                    className="img-fluid me-3"
                    style={{ width: "auto", height: "150px", borderRadius: "4px" }}
                />

                <div className="flex-grow-1">
                    <div className="d-flex align-items-center mb-2">
                        <span className="badge bg-warning text-dark me-2">Bán chạy nhất</span>
                        <span className="badge bg-success text-white">Wi-Fi miễn phí</span>
                    </div>

                    {/* Tên khách sạn / Căn hộ */}
                    <h5 className="fw-bold mt-2">
                        Căn hộ dịch vụ CeLaVie - The Vinhomes and Landmark
                    </h5>

                    {/* Điểm đánh giá */}
                    <div className="mb-2">
                        <span>
                            ⭐⭐⭐⭐⭐
                        </span>
                        <small className="text-muted">(621 bài đánh giá)</small>
                    </div>

                    <p className="text-muted mb-2">
                        Địa chỉ: 208 Nguyen Huu Canh Street, 22 Ward, Bình Thạnh District
                    </p>
                </div>
            </div>

            {/* Địa chỉ */}
            <p className="text-muted mb-2">
                Căn hộ sang trọng với view thành phố tuyệt đẹp, đầy đủ tiện nghi và gần trung tâm.
            </p>


            {/* Property Card #2 */}
            <div className="property-card border p-3 rounded">
            </div>
        
        
        </>
    )
}

export default RoomInfo