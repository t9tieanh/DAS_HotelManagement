import React, { useState } from "react";
import "./style.scss";
import CustomCard from "../../../components/common/Card";
import { Button } from "react-bootstrap";
import { MdPayment } from "react-icons/md";
import { FaUserCircle } from "react-icons/fa";
import { MdDiscount } from "react-icons/md";
import CustomerInfomation from "../../../components/CheckOut/ConfirmInfomationPage/CustomerInfomation";
import Discount from "../../../components/CheckOut/ConfirmInfomationPage/Discount";

const ConfirmInfomationPage = ({handleNextStep})  => {
    return (
        <>
            <CustomCard icon={<FaUserCircle />}  className={'shadow-3'} name={'Thông tin khách hàng'} subTitle = {"*Mục bắt buộc"} children={<CustomerInfomation />} />

            <CustomCard className={'shadow-3 mt-3 mb-3'} name={'Phiếu giảm giá'} 
                subTitle={'Hãy áp dụng mã giảm giá'} icon={<MdDiscount />}
                children={<Discount />}
            />

            <CustomCard className={'shadow-3 mt-3 mb-3'} children={<Button style={{width : '90%'}} onClick={handleNextStep} 
                className="d-block mx-auto d-flex align-items-center justify-content-center gap-2 button-continue" >
                <MdPayment size={'20'} />Kế tiếp: Bước thanh toán</Button>}
            />
        </>
    );
}

export default ConfirmInfomationPage;
