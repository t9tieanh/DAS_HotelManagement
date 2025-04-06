import { useNavigate } from "react-router-dom";
import { BASE_URL } from "../../../conf/baseUrl";
import { IoLocationSharp } from "react-icons/io5";
import { getHotelDetail } from "../../../services/HotelService/hotelService";

const HotelCard = ({ hotel, adults, rooms, dateRange }) => {

    const fileUrl = 'files/image'
    const navigate = useNavigate()
    const handleSubmit = async (hotelId) => {
        const response = await getHotelDetail(hotelId, adults, rooms, dateRange[0].toISOString().split('T')[0], dateRange[1].toISOString().split('T')[0]);
        if (response && response.code && response.code === 200 && response.result) {
            navigate(`/hotel-detail/${hotelId}`, {
                state: {
                    hotelId: hotelId,
                    adults: adults,
                    rooms: rooms,
                    checkIn: dateRange[0].toISOString().split('T')[0],
                    checkOut: dateRange[1].toISOString().split('T')[0]
                }
            });
        }
    }

    return (
        <>

            <div className="hotel-card" key={hotel?.id}>
                <div className="hotel-image">
                    <img
                        src={`${BASE_URL}/${fileUrl}/${hotel?.avatar}`}
                        alt={hotel.name}
                        style={{
                            width: "fit-content",
                            height: "100%",
                            objectFit: "cover"
                        }}
                    />
                </div>
                <div className="hotel-info">
                    <div className="hotel-title">
                        <h2>{hotel.name}</h2>
                        <p>
                            Khách sạn <span className="hotel-stars">
                                {"★".repeat(hotel?.rating)}
                            </span>
                        </p>
                        <p>
                            <IoLocationSharp />{hotel?.address.concrete}, {hotel?.address.district}, {hotel?.address.city}
                        </p>
                    </div>
                    <div className="hotel-rating">
                        <span className="rating-score">
                            {hotel?.rating} sao
                        </span>
                    </div>
                </div>
                <div className="hotel-price">

                    <p className="original-price">{hotel?.originalPrice} VND</p>
                    <p className="sale-price">{hotel?.minRoomPrice} VND</p>
                    <p className="price-note">
                        Chỉ còn {hotel.roomType ? hotel.roomType.length : 0} phòng có giá này! <br />
                    </p>
                    <button onClick={() => handleSubmit(hotel?.id)} className="select-room-button">Chọn phòng</button>
                </div>

            </div>


        </>
    )
}

export default HotelCard