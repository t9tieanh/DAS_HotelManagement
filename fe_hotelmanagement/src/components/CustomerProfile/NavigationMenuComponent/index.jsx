import React from "react";
import './style.scss';

const NavigationMenuComponent = () => {
    return (
        <>
            {/* Sidebar bên trái */}
            <aside className="customer-profile__sidebar">
                <div className="user-info">
                    <div className="user-avatar">DS</div>
                    <div className="user-name">Dē Shuāng Ruǎn (Google)</div>
                    <div className="user-status">
                        Bạn là thành viên <b>Bronze Priority</b>
                    </div>
                </div>

                <ul>
                    <li className="active">Thông tin cá nhân</li>
                    <li>Lịch sử đặt phòng</li>
                    <li>Thống kê chi tiêu</li>
                    <li>Chức năng A</li>
                    <li>Chức năng A2</li>
                </ul>
            </aside>
        </>
    )
}

export default NavigationMenuComponent;