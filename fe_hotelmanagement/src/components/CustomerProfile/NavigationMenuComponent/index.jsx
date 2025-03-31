import React, { useContext } from "react";
import './style.scss';
import { ProfileContext } from "../../../context/ProfileContext";

const NavigationMenuComponent = () => {

    const { profile } = useContext(ProfileContext);

    return (
        <>
            {/* Sidebar bên trái */}
            <aside className="customer-profile__sidebar">
                <div className="user-info">
                    <div className="user-avatar">DS</div>
                    <div className="user-name">{profile.name}</div>
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