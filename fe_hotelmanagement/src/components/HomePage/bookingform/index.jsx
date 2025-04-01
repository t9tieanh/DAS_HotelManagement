import { useState, useEffect } from "react";
import { useNavigate } from "react-router-dom";
import hero1 from "../../../assets/img/hero/hero-1.jpg";
import hero2 from "../../../assets/img/hero/hero-2.jpg";
import hero3 from "../../../assets/img/hero/hero-3.jpg";
import { GoPaperAirplane } from "react-icons/go";
import DropdownButton from 'react-bootstrap/DropdownButton';
import Dropdown from 'react-bootstrap/Dropdown';
import PrimaryButton from "../../common/button/btn-primary";
import './style.scss'

const images = [hero1, hero2, hero3];

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
                                        <DropdownButton variant="dark" id="dropdown-basic-button" className="custom-dropdown" title={`${numAdults} guest${numAdults > 1 ? "s" : ""}`}>
                                            {Array.from({ length: 5 }, (_, i) => i + 1).map((num) => (
                                                <Dropdown.Item key={num} onClick={() => {setNumAdults(num)}}>
                                                    {num} guest{num > 1 ? "s" : ""}
                                                </Dropdown.Item>
                                            ))}
                                        </DropdownButton>
                                    </div>
                                    <div className="select-option">
                                        <label htmlFor="room">Room:</label>
                                        <DropdownButton variant="dark" id="dropdown-basic-button" className="custom-dropdown" title={`${numRooms} room${numAdults > 1 ? "s" : ""}`}>
                                            {Array.from({ length: 5 }, (_, i) => i + 1).map((num) => (
                                                <Dropdown.Item key={num} onClick={() => {setNumRooms(num)}}>
                                                    {num} guest{num > 1 ? "s" : ""}
                                                </Dropdown.Item>
                                            ))}
                                        </DropdownButton>
                                    </div>
                                    
                                    <PrimaryButton className={'search-button'} type="submit" text={"Tìm phòng ngay"} icon={<GoPaperAirplane />} />
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
