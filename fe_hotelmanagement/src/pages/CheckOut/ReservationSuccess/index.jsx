import { useEffect } from 'react';
import Button from 'react-bootstrap/Button';
import Card from 'react-bootstrap/Card';
import { useNavigate } from 'react-router-dom';
import './style.scss'; // Import file CSS tùy chỉnh

const PaymentSuccess = () => {
    const navigate = useNavigate();

    // useEffect(() => {
    //     const timer = setTimeout(() => {
    //         navigate('/');
    //     }, 5000);
    //     return () => clearTimeout(timer); 
    // }, [navigate]);

    return (
        <div className="payment-success-container">
            <Card className="payment-success-card">
                <Card.Img
                    variant="top"
                    src='https://media3.giphy.com/media/v1.Y2lkPTc5MGI3NjExaWRsOG1qN3M3NHkwM3RyYWs0OWhkanJpbGhqMm9lOTBwbWxxbnV6bCZlcD12MV9pbnRlcm5hbF9naWZfYnlfaWQmY3Q9Zw/tf9jjMcO77YzV4YPwE/giphy.gif'
                    className="payment-success-image"
                />
                <Card.Body>
                    <Card.Title className="payment-success-title">Đặt phòng thành công</Card.Title>
                    <Card.Text className="payment-success-text">
                        Cảm ơn bạn đã sử dụng dịch vụ của chúng tôi! Bạn sẽ được chuyển về trang chủ sau 5 giây.
                    </Card.Text>
                    <Button
                        variant="primary"
                        className="payment-success-button"
                        onClick={() => navigate('/')}
                    >
                        Trở về trang chủ ngay
                    </Button>
                </Card.Body>
            </Card>
        </div>
    );
};

export default PaymentSuccess;