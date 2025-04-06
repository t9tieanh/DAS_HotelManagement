import React, { useContext, useEffect } from "react";
import './style.scss';
import { ProfileContext } from "../../../context/ProfileContext";

const NavigationMenuComponent = ({ activeMenu, setActiveMenu }) => {

    const { profile } = useContext(ProfileContext);

    useEffect(() => {
        console.log("Active menu changed:", activeMenu);
    }, [activeMenu]);

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
                    <li
                        className={activeMenu === "info" ? "active" : ""}
                        onClick={() => setActiveMenu("info")}
                    >
                        Thông tin cá nhân
                    </li>
                    <li
                        className={activeMenu === "history" ? "active" : ""}
                        onClick={() => setActiveMenu("history")}
                    >
                        Lịch sử đặt phòng
                    </li>
                </ul>
            </aside>
        </>
    )
}

export default NavigationMenuComponent;