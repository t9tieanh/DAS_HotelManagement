import Button from 'react-bootstrap/Button'
import Card from 'react-bootstrap/Card';

const HotelName = ({name, subname}) => {
    return (
        <>
            <div className="container mt-1 name-hotel">
                <div className="row">
                <div className="col-md-8 infomation-section">
                    <h1 className="hotel-header">{name}</h1>
                    <p className="text-muted">{subname}</p>
                    <hr/>
                    <p><span className="rating">{8.4}</span> Rất tốt | {756} đánh giá</p>
                </div>

                    {/* Giá và tiện ích */}
                    <div className="col-md-4 booking-section text-right mt-4 d-flex align-items-center justify-content-end g-0">
                        <div>
                            <h6>Tổng giá từ</h6>
                            <h3 className="price mb-0">{123000} VND</h3>
                        </div>
                        <Button variant="primary" className="ms-2">Chọn phòng</Button>
                    </div>
                </div>
            </div>
        </>
    )
}

export default HotelName