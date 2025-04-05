// filepath: d:\Term II Third Year\OOAD\fe_hotelmanagement\src\pages\RegisterPage\index.jsx
import React from 'react';
import ImageComponent from "../../components/RegisterPage/ImageComponent";
import InfoComponent from "../../components/RegisterPage/InfoComponent";
import './style.scss'

const RegisterPage = () => {
    return (
        <section className="h-100 h-custom register-page">
            <div className="container py-5 h-100">
                <div className="row d-flex justify-content-center align-items-center h-100">
                    <div className="col-lg-8 col-xl-6">
                        <div className="card rounded-3">
                            <InfoComponent />
                        </div>
                    </div>
                </div>
            </div>
        </section>
    );
};

export default RegisterPage;