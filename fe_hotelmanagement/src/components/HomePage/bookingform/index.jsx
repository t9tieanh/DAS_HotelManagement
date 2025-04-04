import { useState, useEffect } from "react";
import { useNavigate } from "react-router-dom";
import hero4 from "../../../assets/img/hero/hero-4.jpeg";
import hero5 from "../../../assets/img/hero/hero-5.jpeg";
import hero6 from "../../../assets/img/hero/hero-6.jpg";
import { GoPaperAirplane } from "react-icons/go";
import DropdownButton from 'react-bootstrap/DropdownButton';
import Dropdown from 'react-bootstrap/Dropdown';
import PrimaryButton from "../../common/button/btn-primary";
import './style.scss'

const images = [hero5, hero4, hero6];

const BookingForm = () => {
    const defaultCheckIn = new Date();
    defaultCheckIn.setDate(defaultCheckIn.getDate() + 1);

    const defaultCheckOut = new Date();
    defaultCheckOut.setDate(defaultCheckIn.getDate() + 1);

    const [currentImage, setCurrentImage] = useState(0);
    const [checkIn, setCheckIn] = useState(defaultCheckIn.toISOString().split("T")[0]);
    const [checkOut, setCheckOut] = useState(defaultCheckOut.toISOString().split("T")[0]);
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
                                <h1>Hệ thống đặt phòng Hotelas</h1>
                                <p>
                                    Hotelas là hệ thống đặt phòng khách sạn trực tuyến, cho phép khách hàng dễ dàng tìm kiếm, lựa chọn và đặt phòng tại các khách sạn trên khắp Việt Nam.
                                </p>
                                <a href="#" className="primary-btn">
                                    Khám phá ngay
                                </a>
                            </div>
                        </div>
                        <div className="col-xl-5 col-lg-5 offset-xl-1 offset-lg-1">
                            <div className="booking-form">
                                <h3>Đặt phòng khách sạn</h3>
                                <form onSubmit={handleSubmit}>
                                    <div className="check-date">
                                        <label htmlFor="date-in">Nhận phòng:</label>
                                        <input type="date" className="date-input" id="date-in" value={checkIn}
                                            onChange={(e) => setCheckIn(e.target.value)} />
                                    </div>
                                    <div className="check-date">
                                        <label htmlFor="date-out">Trả phòng:</label>
                                        <input type="date" className="date-input" id="date-out" value={checkOut}
                                            onChange={(e) => setCheckOut(e.target.value)} />
                                    </div>
                                    <div className="select-option">
                                        <label htmlFor="guest">Số người:</label>
                                        <DropdownButton variant="dark" id="dropdown-basic-button" className="custom-dropdown" title={`${numAdults} guest${numAdults > 1 ? "s" : ""}`}>
                                            {Array.from({ length: 5 }, (_, i) => i + 1).map((num) => (
                                                <Dropdown.Item key={num} onClick={() => { setNumAdults(num) }}>
                                                    {num} Người
                                                </Dropdown.Item>
                                            ))}
                                        </DropdownButton>
                                    </div>
                                    <div className="select-option">
                                        <label htmlFor="room">Số phòng:</label>
                                        <DropdownButton variant="dark" id="dropdown-basic-button" className="custom-dropdown" title={`${numRooms} room${numAdults > 1 ? "s" : ""}`}>
                                            {Array.from({ length: 5 }, (_, i) => i + 1).map((num) => (
                                                <Dropdown.Item key={num} onClick={() => { setNumRooms(num) }}>
                                                    {num} Phòng
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
