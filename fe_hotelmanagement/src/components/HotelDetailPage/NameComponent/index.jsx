import Button from 'react-bootstrap/Button'

const HotelName = ({name, subname}) => {
    return (
        <>
            <div className="container mt-4 name-hotel">
                <div className="row">
                <div className="col-md-8 infomation-section">
                    <h1 className="hotel-header">{name}</h1>
                    <p className="text-muted">{subname}</p>
                    <hr/>
                    <p><span className="rating">{8.4}</span> Rất tốt | {756} đánh giá</p>
                </div>

                    {/* Giá và tiện ích */}
                    <div className="col-md-4 booking-section text-right">
                        <h3 className="price">{123000} VND</h3>
                        <Button variant="warning" className='w-100 mb-3'>Chọn phòng</Button>
                    </div>
                </div>
            </div>
        </>
    )
}

export default HotelName