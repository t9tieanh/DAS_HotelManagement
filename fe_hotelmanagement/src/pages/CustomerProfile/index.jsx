import React from "react";
import './style.scss';
import NavigationMenuComponent from "../../components/CustomerProfile/NavigationMenuComponent";
import CustomerInfo from "../../components/CustomerProfile/CustomerInfo";
import { ProfileProvider } from "../../context/ProfileContext";

function CustomerProfile() {
    return (
        <ProfileProvider>
            <div className="customer-profile">
                {/* Sidebar bên trái */}
                <NavigationMenuComponent />
                {/* Nội dung chính */}
                <CustomerInfo />
            </div>
        </ProfileProvider>
    );
}

export default CustomerProfile;