import React from "react";
import './style.scss';
import NavigationMenuComponent from "../../components/CustomerProfile/NavigationMenuComponent";
import CustomerInfo from "../../components/CustomerProfile/CustomerInfo";

function CustomerProfile() {
    return (
        <div className="customer-profile">
            
            {/* Sidebar bên trái */}
            <NavigationMenuComponent />

            {/* Nội dung chính */}
            <CustomerInfo />
        </div>
    );
}

export default CustomerProfile;