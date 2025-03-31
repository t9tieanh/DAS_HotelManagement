import React, { useEffect, useState } from "react";
import { BASE_URL } from "../../../conf/baseUrl";
import './style.scss';
import { useLocation } from "react-router-dom";
import { findRoomInHotel } from "../../../services/HotelService/findHotelService";

const HotelComponent = () => {


    const fileUrl = 'files/image'
    const location = useLocation();
    const searchParams = new URLSearchParams(location.search);

    const checkIn = searchParams.get("checkIn");
    const checkOut = searchParams.get("checkOut");
    const numAdults = searchParams.get("numAdults");
    const numRooms = searchParams.get("numRooms");

    const [hotels, setHotels] = useState([]);
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState(null);

    useEffect(() => {
        const fetchHotels = async () => {
            try {
                const result = await findRoomInHotel(checkIn, checkOut, numAdults, numRooms);
                setHotels(result);
            } catch (error) {
                setError("Có lỗi xảy ra khi tải dữ liệu khách sạn.");
            } finally {
                setLoading(false);
            }
        }
        fetchHotels();
    }, [checkIn, checkOut, numAdults, numRooms]);

    if (loading) {
        return <p>Đang tải danh sách khách sạn...</p>;
    }

    if (error) {
        return <p>{error}</p>;
    }

    return (
        <>
            <section className="hotel-list">
                {hotels && hotels.length > 0 ? (
                    hotels.map((hotel) => (

                        <div className="hotel-card" key={hotel.id}>
                            <div className="hotel-image">
                                <img
                                    src={`${BASE_URL}/${fileUrl}/${hotel.avatar}`}
                                    alt={hotel.name}
                                    style={{
                                        width: "300px",
                                        height: "200px",
                                        objectFit: "cover"
                                    }}
                                />
                            </div>
                            <div className="hotel-info">
                                <div className="hotel-title">
                                    <h2>{hotel.name}</h2>
                                    <p>
                                        Khách sạn <span className="hotel-stars">
                                            {"★".repeat(hotel.rating)}
                                        </span>
                                    </p>
                                    <p>
                                    📍{hotel.address.concrete}, {hotel.address.district}, {hotel.address.city}
                                    </p>
                                </div>
                                <div className="hotel-rating">
                                    {/* Nếu có số lượng đánh giá, hiển thị ở đây */}
                                    <span className="rating-score">
                                        {hotel.rating} sao
                                    </span>
                                </div>
                                <div>
                                    co so luu tru nay co
                                </div>
                            </div>
                            <div className="hotel-price">
                                {/* Nếu API có thông tin giá, hiển thị ở đây */}

                                <p className="original-price">{hotel.minRoomPrice} VND</p>
                                <p className="sale-price">{hotel.minRoomPrice} VND</p>
                                <p className="price-note">
                                    Chỉ còn {hotel.roomType ? hotel.roomType.length : 0} phòng có giá này! <br />
                                </p>
                                <button className="select-room-button">Chọn phòng</button>
                            </div>
                        </div>
                    ))
                ) : (
                    <p>Không tìm thấy khách sạn phù hợp.</p>
                )}
            </section>
        </>
    );
};

export default HotelComponent;
