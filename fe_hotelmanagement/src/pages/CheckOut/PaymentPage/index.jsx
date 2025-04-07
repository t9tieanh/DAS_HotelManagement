import CustomCard from "../../../components/common/Card"
import { MdPayment } from "react-icons/md";

import Accordion from 'react-bootstrap/Accordion';
import './style.scss'
import Form from 'react-bootstrap/Form';
import { useState } from 'react';
import vnpayLogo from '../../../assets/img/common/vnpay.png'
import momoLogo from '../../../assets/img/common/momo.png'
import paylayterLogo from '../../../assets/img/common/paylayter.png'
import Icon from "../../../components/common/Icon";

import OnlinePaymentComponent from "../../../components/CheckOut/PaymentPage/OnlinePaymentComponent";
import PayLater from "../../../components/CheckOut/PaymentPage/Paylayter";
import { Button } from "react-bootstrap";
import { MdCancelScheduleSend } from "react-icons/md";

const PaymentOptionComponent = ({handleNextStep, handleCancelReservation}) => {
    
    const [activeKey, setActiveKey] = useState("0");

    const handleChangePaymentMethod = () => {
        setActiveKey(prevKey => prevKey === "0" ? "1" : "0");
    }

    return (
        <>
        <div className="payment-option-container">
            <Accordion activeKey={activeKey} onSelect={() => handleChangePaymentMethod()} flush>

                {/* thanh toán bằng vn pay */}
                <Accordion.Item eventKey={`0`}>
                    <Accordion.Header>
                        
                    <Form.Check // prettier-ignore
                        type="switch"
                        id="custom-switch"
                        checked={activeKey === "0"} 
                        onChange={() => setActiveKey("0")}
                        label= "Thanh toán bằng ví điện tử"
                    />

                    <div class="ml-2 text-white">
                        <img src={vnpayLogo} className='mr-2' width={35} />
                        <img src={momoLogo} width={35} />
                    </div>
                    
                    </Accordion.Header>
                    <Accordion.Body>

                        {/* oline payment section  */}
                        <OnlinePaymentComponent />

                    </Accordion.Body>
                </Accordion.Item>

                {/* thanh toán tại hotel */}
                <Accordion.Item eventKey={`1`}>
                    <Accordion.Header>
                        
                    <Form.Check // prettier-ignore
                        type="switch"
                        id="custom-switch"
                        checked= {activeKey === "1"} 
                        onChange={() => setActiveKey("1")}
                        label= "Thanh toán khi nhận phòng"
                        className="mr-2"
                    />

                    <Icon logo={paylayterLogo} size={30} />
                    
                    </Accordion.Header>
                    <Accordion.Body>

                        {/* paylayter section */}
                        <PayLater handleNextStep={handleNextStep} />

                    </Accordion.Body>
                </Accordion.Item>
            </Accordion>
        </div>
        <Button style={{width : '90%'}} onClick={handleCancelReservation}
                className="d-block bg-light text-dark mt-2 mx-auto d-flex align-items-center justify-content-center gap-2 button-continue" >
                <MdCancelScheduleSend size={20} /> Hủy đặt phòng này 
        </Button>
        </>
    );

}


const PaymentPage = ({handleNextStep}) => {

    return (<>
        <CustomCard name={'Chọn phương thức thanh toán'} icon={<MdPayment />} 
            children={<PaymentOptionComponent handleNextStep = {handleNextStep} />}
        />
    </>)
}

export default PaymentPage