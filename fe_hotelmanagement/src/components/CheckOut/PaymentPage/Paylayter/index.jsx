
import { use } from "react";
import PrimaryButton from "../../../common/button/btn-primary"
import './style.scss'
import Form from 'react-bootstrap/Form';
import { FaLocationArrow } from "react-icons/fa";
import { payAtHotel } from "../../../../services/PaymentService/paymentService";
import { toast } from "react-toastify";
import { useSelector } from "react-redux";

const PayLater = ({handleNextStep}) => {

    const reservationId = useSelector(state => state.reservation.reservationId)

    // xử lý thanh toán tại khách sạn
    const handlePayAtHotel = async () => {
        const data = await payAtHotel(reservationId)
            
        if (data && data.code && data.code === 200 && data?.result) {
            handleNextStep()
        } else if (data.response && data.response.data) {
            toast.error(data.response.data.message) // thông báo lôi từ server
        }
        else {
            toast.error(data?.message)
        } 
    }

    return (
        <>

        {/* chính sách thanh toán khi nhận phòng */}

            <blockquote class="blockquote mb-3 mt-3 paylayter-container">
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

            <PrimaryButton onClickFunc={handlePayAtHotel} icon={<FaLocationArrow />} className={'mt-3 btn-confirm-booking'} text={'Xác nhận đặt phòng'} />
        </>
    )
}

export default PayLater