import React from "react";
import "./style.scss";
import { IoMdSearch } from "react-icons/io";

const HeaderComponent = ({count}) => {
    return (
        <>
            {/* Header */}
            <div className="booking-header">
                <div className="header-left">
                    <h1>DAS Hotel</h1>
                    <p><IoMdSearch /><span className="hotel-count">{count}</span> nơi lưu trú được tìm thấy</p>
                </div>

                <div className="header-filters">
                    <label>
                        Xếp theo:
                        <select>
                            <option>Giá thấp đến cao</option>
                            <option>Giá cao đến thấp</option>
                        </select>
                    </label>
                </div>
            </div>
        </>
    )
}

export default HeaderComponent;