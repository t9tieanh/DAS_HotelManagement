import { useState } from "react";
import "./style.scss";
import room1 from "../../assets/img/room/room-1.jpg";
import room2 from "../../assets/img/room/room-2.jpg";
import room3 from "../../assets/img/room/room-3.jpg";
import ImageComponent from "../../components/HotelDetailPage/ImageComponent/index.jsx";
import map from '../../assets/img/background/map.png'
import RoomSection from "../../components/HotelDetailPage/RoomCard/index.jsx";
import ArrowButton from "../../components/common/button/button-arrow/index.jsx";

const hotelInfo = {
  name: "Khách sạn Gold",
  description: "Gold Hotel Đà Nẵng by HAVILAND",
  rating: 8.4,
  reviewsCount: 756,
  price: "369,100 VND",
  location: "24 Núi Thành, Hồ Thuận Đông, Quận Hải Châu, Đà Nẵng, Việt Nam, 550000",
  distances: [
    "Khoảng cách đến trung tâm Đà Nẵng - 6 km",
    "Khu vực trung tâm Đà Nẵng - 5 km",
    "Sân bay quốc tế Đà Nẵng - 2.48 km",
  ],
  amenities: ["Máy lạnh", "Lễ tân 24h", "Chỗ đỗ xe", "WiFi"],
  reviews: [
    { name: "NGUYEN T.M.N.", rating: 7.7, comment: "Dịch vụ tốt, sạch sẽ, lễ tân hỗ trợ nhiệt tình." },
    { name: "THUY N.", rating: 10, comment: "WONDERFUL! Ăn sáng đa dạng, giá cả hợp lý, giường ngủ êm ái." },
  ],
};

const HotelDetail = () => {

  return (
    <>

        <div className="hotel-detail-container container ">

        {/* Hình ảnh khách sạn */}
        <ImageComponent />

        <div className="main-info-hotel">

        <div className="container mt-4 name-hotel">
            <div className="row">
            <div className="col-md-8 infomation-section">
                <h1 className="hotel-header">{hotelInfo.name}</h1>
                <p className="text-muted">{hotelInfo.description}</p>
                <p><span className="rating">{hotelInfo.rating}</span> Rất tốt | {hotelInfo.reviewsCount} đánh giá</p>
            </div>

                {/* Giá và tiện ích */}
                <div className="col-md-4 booking-section">
                    <h3 className="price">{hotelInfo.price}</h3>
                    <button className="btn btn-warning w-100 mb-3">Chọn phòng</button>
                </div>
            </div>
        </div>

        <div className="container info-section" >
            <div className="row p-2 g-3">

                <div className="col-md-4">
                    <div className="service-section p-3 section-detail">
                        <h5>Khoảng cách từ khách sạn</h5>
                        <ul>
                            {hotelInfo.distances.map((distance, index) => (
                                <div key={index}>
                                    <i className="fa-solid fa-location-dot"></i>
                                    <span>{distance}</span>
                                </div>
                            ))}
                        </ul>
                    </div>
                </div>

                <div className="col-md-4">
                    <div className="service-section p-3 section-detail">
                        <h5>Khoảng cách từ khách sạn</h5>
                        <ul>
                            {hotelInfo.distances.map((distance, index) => (
                                <div key={index}>
                                    <i className="fa-solid fa-location-dot"></i>
                                    <span>{distance}</span>
                                </div>
                            ))}
                        </ul>
                    </div>
                </div>

                <div className="col-md-4">
                    <div className="service-section p-3 section-detail">
                        <h5>Khoảng cách từ khách sạn</h5>
                        <ul>
                            {hotelInfo.distances.map((distance, index) => (
                                <div key={index}>
                                    <i className="fa-solid fa-location-dot"></i>
                                    <span>{distance}</span>
                                </div>
                            ))}
                        </ul>
                    </div>
                </div>


            </div>

            <div className="p-2 g-3">
                <div className="section-detail p-3">
                    Lưu trú tại Khách sạn Gold là một lựa chọn đúng đắn khi quý khách đến thăm Hòa Thuận Đông. Quầy tiếp tân 24 giờ luôn sẵn sàng phục vụ quý khách từ thủ tục nhận phòng đến trả phòng hay bất kỳ yêu cầu nào.
                    Nếu cần giúp đỡ xin hãy liên hệ đội ngũ tiếp tân, chúng tôi luôn sẵn sàng hỗ trợ quý khách. Sóng WiFi phủ khắp các khu vực chung của khách sạn cho phép quý khách luôn kết nối với gia đình và bè bạn.
                    <ArrowButton text={'Xem thêm'}/>
                </div>
            </div>

        </div>


        </div>

        <div className="card room-list-container">
            <div className="card-body">
                <h5 className="card-title room-header">Những phòng còn trống tại Khách sạn Gold</h5>
                
                <div className="warning-title">
                    Phải đặt phòng trong thời điểm không chắc chắn này? Hãy chọn phòng có thể hủy miễn phí!
                </div>



                <RoomSection/>

                <RoomSection/>

                



            </div>
        </div>

        </div>

    
    
    
    
    
    
    </>
  );
};

export default HotelDetail;
