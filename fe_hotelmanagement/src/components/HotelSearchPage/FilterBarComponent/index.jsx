import React from "react";
import "./style.scss";

const FilterBarComponent = () => {
    return (
        <aside className="sidebar">
            {/* Khoảng giá */}
            <div className="price-filter">
                <h3>Khoảng giá</h3>
                <p>1 phòng, 1 đêm</p>
                <input
                    type="range"
                    min="0"
                    max="24000000"
                    defaultValue="0"
                    style={{ width: "100%" }}
                />
                <div className="range-labels">
                    <span>VND 0</span>
                    <span>VND 24.000.000</span>
                </div>
            </div>

            {/* Lọc phổ biến */}
            <div className="popular-filters">
                <h3>Lọc phổ biến</h3>
                <label>
                    <input type="checkbox" />
                    Gần biển (123)
                </label>
                <label>
                    <input type="checkbox" />
                    +MÃ GIẢM (155)
                </label>
                <label>
                    <input type="checkbox" />
                    4-5 sao giá tốt (75)
                </label>
                <label>
                    <input type="checkbox" />
                    Vị trí thuận tiện (76)
                </label>
                <label>
                    <input type="checkbox" />
                    Được yêu thích nhất (36)
                </label>
                <a href="#xem-tat-ca">Xem Tất cả</a>
            </div>

            {/* Khuyến mãi & Giảm giá */}
            <div className="promotions">
                <h3>Khuyến mãi & Giảm giá</h3>
                <label>
                    <input type="checkbox" />
                    +MÃ GIẢM (155)
                </label>
                <label>
                    <input type="checkbox" />
                    Gần biển (123)
                </label>
                <label>
                    <input type="checkbox" />
                    Khuyến mãi dành cho bạn (121)
                </label>
                <label>
                    <input type="checkbox" />
                    Phù hợp cho cặp đôi (112)
                </label>
            </div>
        </aside>
    )
}

export default FilterBarComponent;