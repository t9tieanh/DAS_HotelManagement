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

const PaymentOptionComponent = ({ handleNextStep, room }) => {

    console.log("room2", room)
    const [activeKey, setActiveKey] = useState("0");

    const handleChangePaymentMethod = () => {
        setActiveKey(prevKey => prevKey === "0" ? "1" : "0");
    }

    return (
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
                            label="Thanh toán bằng ví điện tử"
                        />

                        <div class="ml-2 text-white">
                            <img src={vnpayLogo} className='mr-2' width={35} />
                            <img src={momoLogo} width={35} />
                        </div>

                    </Accordion.Header>
                    <Accordion.Body>

                        {/* oline payment section  */}
                        <OnlinePaymentComponent room={room} />

                    </Accordion.Body>
                </Accordion.Item>

                {/* thanh toán tại hotel */}
                <Accordion.Item eventKey={`1`}>
                    <Accordion.Header>

                        <Form.Check // prettier-ignore
                            type="switch"
                            id="custom-switch"
                            checked={activeKey === "1"}
                            onChange={() => setActiveKey("1")}
                            label="Thanh toán khi nhận phòng"
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
    );

}


const PaymentPage = ({ handleNextStep, room }) => {

    return (<>
        <CustomCard name={'Chọn phương thức thanh toán'} icon={<MdPayment />}
            children={<PaymentOptionComponent handleNextStep={handleNextStep} room={room} />}
        />
    </>)
}

export default PaymentPage