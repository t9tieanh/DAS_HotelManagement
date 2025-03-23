import React from "react";
import "./style.scss";

const HeaderComponent = () => {
    return (
        <>
            {/* Header */}
            <div className="booking-header">
                <div className="header-left">
                    <h1>Đà Nẵng</h1>
                    <p>1420 nơi lưu trú được tìm thấy</p>
                </div>

                <div className="header-filters">
                    <label>
                        Xếp theo:
                        <select>
                            <option>Độ phổ biến</option>
                            <option>Giá thấp đến cao</option>
                            <option>Giá cao đến thấp</option>
                        </select>
                    </label>
                    <label>
                        Hiển thị giá:
                        <select>
                            <option>Mỗi phòng mỗi đêm</option>
                            <option>Tổng giá</option>
                        </select>
                    </label>
                </div>
            </div>
        </>
    )
}

export default HeaderComponent;