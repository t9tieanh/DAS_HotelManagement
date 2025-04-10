import React, { useEffect, useState } from "react";
import "./style.scss";
import CustomCard from "../../../components/common/Card";
import { Button } from "react-bootstrap";
import { MdPayment } from "react-icons/md";
import { FaUserCircle } from "react-icons/fa";
import { MdDiscount } from "react-icons/md";
import CustomerInfomation from "../../../components/CheckOut/ConfirmInfomationPage/CustomerInfomation";
import Discount from "../../../components/CheckOut/ConfirmInfomationPage/Discount";
import { useSelector } from "react-redux";
import { updateReservationInfo } from "../../../services/ReservationService/reservationService";
import { toast } from "react-toastify";
import { validateEmail, validatePhoneNumber } from "../../../utils/Validate";
import { MdCancelScheduleSend } from "react-icons/md";
import { getProfile1 } from "../../../services/CustomerProfile/profileService";

const ConfirmInfomationPage = ({handleNextStep, handleCancelReservation, discounts, setDiscounts})  => {

    const reservationId = useSelector(state => state.reservation.reservationId)
    const [name, setName] = useState("");
    const [phone, setPhone] = useState("");
    const [email, setEmail] = useState("");

    const validate = () => {
        if (!name || !phone || !email ||!name.trim() || !phone.trim() || !email.trim()) {
            toast.error("Vui lòng điền đầy đủ thông tin trước khi xác nhận!");
            return false;
        }
    
        if (!validateEmail(email)) {
            toast.error("Email không hợp lệ!");
            return false;
        }
    
        if (!validatePhoneNumber(phone)) {
            toast.error("Số điện thoại không hợp lệ!");
            return false;
        }
    
        return true;
    }
    

    const handleConfirmInfomation = async () => {

        // Kiểm tra chuỗi rỗng
        if (!validate()) return;

        const data = await updateReservationInfo(reservationId, name, phone, email)

        console.log(data)

        if (data && data.code && data.code == 200 && data.result) {
            handleNextStep()
            toast.success(data.message)
        } else if (data.response && data.response.data) {
            console.log(data)
            toast.error(data.response.data.message)
            return
        }
        else toast.error(data?.message)
    }

    const fetchUserProfile = async() => {
        const data = await getProfile1()
        if (data && data.code && data.code == 200 && data.result) {
            setName(data.result.name)
            setEmail(data.result.email)
            setPhone(data.result.phone)
        }
    }

    useEffect (
        () => {fetchUserProfile()},[]
    )

    return (
        <>
            <CustomCard icon={<FaUserCircle />}  className={'shadow-3'} name={'Thông tin khách hàng'} subTitle = {"*Mục bắt buộc"} children={<CustomerInfomation
                name={name} setName={setName} phone={phone} setPhone={setPhone} email={email} setEmail={setEmail} />} />

            <CustomCard className={'shadow-3 mt-3 mb-3'} name={'Phiếu giảm giá'} 
                subTitle={'Hãy áp dụng mã giảm giá'} icon={<MdDiscount />}
                children={<Discount appliedDiscounts = {discounts} setAppliedDiscounts = {setDiscounts}/>}
            />

            <CustomCard className={'shadow-3 mt-3 mb-3'} children=
                {<>
                <Button style={{width : '90%'}} onClick={handleConfirmInfomation} 
                    className="d-block mx-auto d-flex align-items-center justify-content-center gap-2 button-continue" >
                    <MdPayment size={'20'} />Kế tiếp: Bước thanh toán
                </Button>

                <Button style={{width : '90%'}} onClick={handleCancelReservation} 
                    className="d-block bg-light text-dark mt-2 mx-auto d-flex align-items-center justify-content-center gap-2 button-continue" >
                    <MdCancelScheduleSend size={20} /> Hủy đặt phòng này 
                </Button>
                
                
                </>}
            />
        </>
    );
}

export default ConfirmInfomationPage;
