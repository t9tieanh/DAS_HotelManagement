import React from "react";
import { useState } from "react";
import './style.scss';
import NavigationMenuComponent from "../../components/CustomerProfile/NavigationMenuComponent";
import CustomerInfo from "../../components/CustomerProfile/CustomerInfo";
import { ProfileProvider } from "../../context/ProfileContext";
import ReservationHistory from "../../components/CustomerProfile/ReservationHistory";

const CustomerProfile = () => {

    const [activeMenu, setActiveMenu] = useState("info"); 

    const renderContent = () => {
        switch (activeMenu) {
            case "info":
                return <CustomerInfo />;
            case "history":
                return <ReservationHistory />;
        }
    }
    return (
        <ProfileProvider>
            <div className="customer-profile">
                <NavigationMenuComponent
                    activeMenu={activeMenu}
                    setActiveMenu={setActiveMenu}
                />
                {renderContent()}
            </div>
        </ProfileProvider>
    );
}

export default CustomerProfile;