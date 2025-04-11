import { useState, useEffect } from "react";
import { useNavigate } from "react-router-dom";
import hero4 from "../../../assets/img/hero/hero-4.jpeg";
import hero5 from "../../../assets/img/hero/hero-5.jpeg";
import hero6 from "../../../assets/img/hero/hero-6.jpg";
import { GoPaperAirplane } from "react-icons/go";
import PrimaryButton from "../../common/button/btn-primary";
import './style.scss'
import LocationSearch from "../../HotelSearchPage/SearchBarComponent/LocationSearch";
import Overlay from "../../common/Overlay";
import { useRef } from "react";
import { MdTipsAndUpdates } from "react-icons/md";
import './style.scss';
import {FaUser } from 'react-icons/fa'; // Icons từ react-icons
import { CapacitySearch } from "../../HotelSearchPage/SearchBarComponent";
import TextInput from "../../common/Input/Input2";

const images = [hero5, hero4, hero6];

const BookingForm = () => {
    const defaultCheckIn = new Date();
    defaultCheckIn.setDate(defaultCheckIn.getDate() + 1);

    const defaultCheckOut = new Date();
    defaultCheckOut.setDate(defaultCheckIn.getDate() + 1);

    // state để search phòng
    const [location, setLocation] = useState("");
    const [currentImage, setCurrentImage] = useState(0);
    const [checkIn, setCheckIn] = useState(defaultCheckIn.toISOString().split("T")[0]);
    const [checkOut, setCheckOut] = useState(defaultCheckOut.toISOString().split("T")[0]);
    const [numAdults, setNumAdults] = useState(1);
    const [numRooms, setNumRooms] = useState(1);
    const navigate = useNavigate();

    // dùng cho search location 
    const [show, setShow] = useState(false);
    const [target, setTarget] = useState(null);
    const ref = useRef(null);
    // người dùng tiến hành chọn location 
    const handleSelectLocation = (location) => {
        setLocation(location)
        setShow(false);
    }

    // xử lý cho sự kiện search location
    const handleLocationSearch = (event) => {
        setShow(true);
        const value = event.target.value;
        setLocation(value);

        setTarget(event.target);
    };


    // cho phần chọn người lớn, trẻ em, phòng
    const capacityRef = useRef(null);
    const [capacityTarget, setCapacityTarget] = useState(null);
    const [capacityShow, setCapacityShow] = useState(false);

    const handleClickCapacity = (event) => { 
        setCapacityShow(!capacityShow);
        setCapacityTarget(event.target);
    };


    // nhấn nút tìm phòng 
    const handleSubmit = async (e) => {
        e.preventDefault();

        try {
            navigate(`/hotel-result?checkIn=${checkIn}&checkOut=${checkOut}&numAdults=${numAdults}&numRooms=${numRooms}&location=${location}`);
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

                                    {/* nhập location */}
                                    {/* <div className="check-date" ref={ref}>
                                        <label htmlFor="date-in">Chọn địa điểm:</label>
                                        <input type="text" className="date-input" id="date-in" value={location} placeholder="Nhập địa chỉ"
                                            onChange={handleLocationSearch} />
                                        <Overlay show={show} target={target} ref={ref} 
                                            children={<LocationSearch handleSelectLocation = {handleSelectLocation} textSearch = {location} />}
                                            header={<><MdTipsAndUpdates />&nbsp;Nhập thêm ký tự để tìm vị trí chính xác hơn</>}
                                        />
                                    </div> */}
                                    <TextInput
                                        ref={ref}
                                        value={location}
                                        setValue={setLocation}  // ← dùng setValue để xử lý mặc định
                                        onChangeFunc={handleLocationSearch}
                                        placeholder="Nhập địa điểm"
                                        name="Địa điểm"
                                        idInput="location-input"
                                        className="check-date"
                                        overlayElement={
                                            <Overlay show={show} target={target} ref={ref} 
                                                children={<LocationSearch handleSelectLocation = {handleSelectLocation} textSearch = {location} />}
                                                header={<><MdTipsAndUpdates />&nbsp;Nhập thêm ký tự để tìm vị trí chính xác hơn</>}
                                            />
                                        }
                                    />

                                    {/* nhập ngày check in check out */}
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

                                    {/* nhập capacity */}
                                    <div className="select-option">
                                        <label htmlFor="guest">Số người:</label>
                                        <div className="check-date shadow-2 p-2 custom-dropdown" id="guest">
                                            <div ref={capacityRef} className="bg-light" onClick={(e) => {handleClickCapacity(e)}}>
                                            <FaUser />&nbsp;
                                            <span>{numAdults} người lớn, {numRooms} phòng</span>
                                            </div>
                                
                                            <Overlay show={capacityShow} target={capacityTarget} ref={capacityRef} 
                                                children={<CapacitySearch adults = {numAdults} setAdults = {setNumAdults} 
                                                    rooms = {numRooms} setRooms = {setNumRooms} setShow={setCapacityShow}  />}
                                                header={<><MdTipsAndUpdates />&nbsp;Điền thông tin khách và phòng</>}
                                            />
                                        </div>
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
