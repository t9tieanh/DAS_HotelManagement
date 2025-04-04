import Accordion from 'react-bootstrap/Accordion';
import './style.scss'
import Form from 'react-bootstrap/Form';
import { useState } from 'react';
import vnpayLogo from '../../../assets/img/common/vnpay.png'
import momoLogo from '../../../assets/img/common/momo.png'
import { Dropdown, DropdownButton } from 'react-bootstrap';
import PrimaryButton from '../button/btn-primary';
import { FaLocationArrow } from "react-icons/fa";

const Icon = ({logo,size}) => {
    return (
        <><img src={logo} className='mr-2' width={size ? size : 35 } /></>
    )
}

const OnlinePaymentComponent = () => {

    const [paymentMethod, setPaymentMethod] = useState(0)

    const handleChangePaymentMethod = () => {
        setPaymentMethod(!paymentMethod)
    }

    return (
        <>
            <DropdownButton variant="light" id="dropdown-basic-button" className="custom-dropdown" title='Chọn phương thức thanh toán'>
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



const AccordionComponent = ({}) => {

    const [paymentMethod, setPaymentMethod] = useState(true)

    const handleChangePaymentMethod = () => {
        setPaymentMethod(!paymentMethod)
    }

    return (
        <div className="accordion-container">
            <Accordion defaultActiveKey="0" flush>

                {/* thanh toán bằng vn pay */}
                <Accordion.Item eventKey={`0`}>
                    <Accordion.Header onClick={handleChangePaymentMethod}>
                        
                    <Form.Check // prettier-ignore
                        type="switch"
                        id="custom-switch"
                        checked={paymentMethod} 
                        onChange = {handleChangePaymentMethod}
                        label= "Thanh toán bằng ví điện tử"
                    />

                    <div class="ml-2 text-white">
                        <img src={vnpayLogo} className='mr-2' width={35} />
                        <img src={momoLogo} width={35} />
                    </div>
                    
                    </Accordion.Header>
                    <Accordion.Body>
                        
                        <OnlinePaymentComponent />


                    </Accordion.Body>
                </Accordion.Item>

                {/* thanh toán tại hotel */}
                <Accordion.Item eventKey={`1`}>
                    <Accordion.Header onClick={handleChangePaymentMethod}>
                        
                    <Form.Check // prettier-ignore
                        type="switch"
                        id="custom-switch"
                        checked={!paymentMethod} 
                        onChange = {handleChangePaymentMethod}
                        label= "Thanh toán khi nhận phòng"
                    />
                    
                    </Accordion.Header>
                    <Accordion.Body>

                        {/* chính sách thanh toán khi nhận phòng */}

                        <blockquote class="blockquote mb-3 mt-3">
                            <p>
                                <i class="fas fa-quote-left fa-lg text-primary me-2"></i>
                                <span class="font-italic">Chú ý đến chính sách trả sau như sau: </span>
                            </p>
                            <p>
                                <li><strong>Hạn thanh toán:</strong> Thanh toán đầy đủ phải được thực hiện ngay khi nhận phòng. Nếu không hoàn tất thanh toán, khách sạn có quyền từ chối cung cấp dịch vụ.</li>
                                <li><strong>Chính sách hủy và thay đổi:</strong> Việc hủy hoặc thay đổi đặt phòng có thể bị áp dụng phí theo chính sách của khách sạn. Nếu khách không đến mà không thông báo trước, khách sạn có quyền hủy phòng và tính phí theo quy định.</li>
                                <li><strong>Xác nhận đặt phòng:</strong> Khách hàng cần cung cấp thông tin cá nhân chính xác và có thể được yêu cầu thanh toán một khoản đặt cọc để đảm bảo giữ phòng.</li>
                            </p>
                        </blockquote>

                        <Form.Check 
                            type='checkbox'
                            label={`Đồng ý với chính sách`}
                        />

                        <PrimaryButton icon={<FaLocationArrow />} className={'mt-3'} text={'Xác nhận đặt phòng'} />

                    </Accordion.Body>
                </Accordion.Item>
            </Accordion>
        </div>
    );
};

export default AccordionComponent;
