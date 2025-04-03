import { useState, useEffect } from "react";
import { useNavigate } from "react-router-dom";
import hero1 from "../../../assets/img/hero/hero-1.jpg";
import hero5 from "../../../assets/img/hero/hero-5.jpeg";
import hero6 from "../../../assets/img/hero/hero-6.jpg";
import hero4 from "../../../assets/img/hero/hero-4.jpeg";

import { findRoomInHotel } from "../../../services/HotelService/findHotelService";

const images = [hero5, hero4, hero6];

const BookingForm = () => {
    const [currentImage, setCurrentImage] = useState(0);
    const [checkIn, setCheckIn] = useState("");
    const [checkOut, setCheckOut] = useState("");
    const [numAdults, setNumAdults] = useState(1);
    const [numRooms, setNumRooms] = useState(1);
    const navigate = useNavigate();

    const handleSubmit = async (e) => {
        e.preventDefault();

        try {
            navigate(`/hotel-result?checkIn=${checkIn}&checkOut=${checkOut}&numAdults=${numAdults}&numRooms=${numRooms}`);
        } catch (error) {
            console.error("Lỗi khi tìm phòng:", error);
            alert("Đã xảy ra lỗi khi tìm phòng.");
        }
    };


    useEffect(() => {
        const interval = setInterval(() => {
            setCurrentImage((prev) => (prev + 1) % images.length);
        }, 3000);

        return () => clearInterval(interval);
    }, []);

    return (
        <>
            <section className="hero-section">
                <div className="container">
                    <div className="row">
                        <div className="col-lg-6">
                            <div className="hero-text">
                                <h1>Sona A Luxury Hotel</h1>
                                <p>
                                    Here are the best hotel booking sites, including recommendations for international
                                    travel and for finding low-priced hotel rooms.
                                </p>
                                <a href="#" className="primary-btn">
                                    Discover Now
                                </a>
                            </div>
                        </div>
                        <div className="col-xl-4 col-lg-5 offset-xl-2 offset-lg-1">
                            <div className="booking-form">
                                <h3>Booking Your Hotel</h3>
                                <form onSubmit={handleSubmit}>
                                    <div className="check-date">
                                        <label htmlFor="date-in">Check In:</label>
                                        <input type="date" className="date-input" id="date-in" value={checkIn}
                                            onChange={(e) => setCheckIn(e.target.value)} />
                                    </div>
                                    <div className="check-date">
                                        <label htmlFor="date-out">Check Out:</label>
                                        <input type="date" className="date-input" id="date-out" value={checkOut}
                                            onChange={(e) => setCheckOut(e.target.value)} />
                                    </div>
                                    <div className="select-option">
                                        <label htmlFor="guest">Guests:</label>
                                        <select id="guest" value={numAdults} onChange={(e) => setNumAdults(e.target.value)}>
                                            {[...Array(5)].map((_, i) => (
                                                <option key={i + 1} value={i + 1}>{i + 1} Adults</option>
                                            ))}
                                        </select>
                                    </div>
                                    <div className="select-option">
                                        <label htmlFor="room">Room:</label>
                                        <select id="room" value={numRooms} onChange={(e) => setNumRooms(e.target.value)}>
                                            {[...Array(5)].map((_, i) => (
                                                <option key={i + 1} value={i + 1}>{i + 1} Room</option>
                                            ))}
                                        </select>
                                    </div>
                                    <button type="submit">Check Availability</button>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>

                <div className="hero-slider">
                    <div
                        className="hs-item set-bg"
                        style={{
                            backgroundImage: `url(${images[currentImage]})`,
                            transition: "background-image 1s ease-in-out",
                            height: "100%",
                            backgroundSize: "cover",
                            backgroundPosition: "center"
                        }}
                    ></div>
                </div>
            </section>
        </>
    );
};

export default BookingForm;
