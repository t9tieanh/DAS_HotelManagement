import { useEffect } from 'react';
import Button from 'react-bootstrap/Button';
import Card from 'react-bootstrap/Card';
import { useNavigate } from 'react-router-dom';
import './style.scss'; // Import file CSS tùy chỉnh
import { Container } from 'react-bootstrap';
import Icon from '../../../components/common/Icon';
import successLogo from '../../../assets/img/common/success_icon.gif'
import PrimaryButton from '../../../components/common/button/btn-primary';
import { MdHome } from "react-icons/md";
import { useSelector } from 'react-redux';


const PaymentSuccess = () => {
    const navigate = useNavigate();
    const reservationId = useSelector(state => state.reservation.reservationId)

    // useEffect(() => {
    //     const timer = setTimeout(() => {
    //         navigate('/');
    //     }, 5000);
    //     return () => clearTimeout(timer); 
    // }, [navigate]);

    return (
        <div className="payment-success-container">
            <Container className="justify-content-center vh-100">
                <Card className="text-center shadow-2">
                    <Card.Body>
                        <Icon logo={successLogo} size={50} />
                        <Card.Title className="mb-4">Thanh toán thành công!</Card.Title>
                        <p className='text-muted fs-15'>Mã đặt phòng của quý khách là: {reservationId}</p>
                        <Card.Text className="mb-4">
                            Cảm ơn bạn đã đặt phòng với <span className="text-primary"><span className="text-warning">@H</span>otelas</span>
                            <br></br>! Chúng tôi đã nhận được thanh toán của bạn.
                        </Card.Text>
                        <PrimaryButton className={'mb-3 bg-primary'} icon={<MdHome size={19} />} text={"Quay về trang chủ"} />
                    </Card.Body>
                </Card>
            </Container>
        </div>
    );
};

export default PaymentSuccess;