import './style.scss'
import { useState } from 'react';
import vnpayLogo from '../../../../assets/img/common/vnpay.png'
import momoLogo from '../../../../assets/img/common/momo.png'
import { Dropdown, DropdownButton } from 'react-bootstrap';
import Icon from "../../../common/Icon";
import { FaCreditCard } from 'react-icons/fa';

const OnlinePaymentComponent = () => {

    const [paymentMethod, setPaymentMethod] = useState(0)

    const handleChangePaymentMethod = () => {
        setPaymentMethod(!paymentMethod)
    }

    return (
        <>
            <DropdownButton variant="light" id="dropdown-basic-button" className="custom-dropdown" title={<><FaCreditCard className="mr-2" /> Chọn phương thức thanh toán</>}>
                    <Dropdown.Item>
                        <Icon logo={vnpayLogo} /> Thanh toán bằng VN PAY
                    </Dropdown.Item>
                    <Dropdown.Item>
                        <Icon logo={momoLogo} /> Thanh toán bằng MOMO
                    </Dropdown.Item>
            </DropdownButton>  
            <blockquote class="blockquote mb-4 mt-3">
                <p>
                <i class="fas fa-quote-left fa-lg text-warning me-2"></i>
                <span class="font-italic">Thanh toán dễ dàng và an toàn với ví điện tử – không cần tiền mặt, xử lý nhanh chóng, bảo mật cao, giúp bạn mua sắm thuận tiện mọi lúc, mọi nơi!.</span>
                </p>
            </blockquote>
        </>
    )

}

export default OnlinePaymentComponent