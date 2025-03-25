import { useEffect, useState } from "react";
import "./style.scss";
import room1 from "../../assets/img/room/room-1.jpg";
import room2 from "../../assets/img/room/room-2.jpg";
import room3 from "../../assets/img/room/room-3.jpg";
import ImageComponent from "../../components/HotelDetailPage/ImageComponent/index.jsx";
import map from '../../assets/img/background/map.png'
import RoomSection from "../../components/HotelDetailPage/RoomCard/index.jsx";
import HotelName from "../../components/HotelDetailPage/NameComponent/index.jsx";
import HotelDescription from "../../components/HotelDetailPage/DescriptionComponent/index.jsx";
import { getHotelDetail } from "../../services/HotelService/hotelService.js";
import MapComponent from "../../components/common/MapComponent/index.jsx";
import GoogleMapIframe from "../../components/common/MapComponent/index.jsx";

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

  const id = 'adaaa110-e548-460e-bb08-79858487c89f'

  const [hotelDetail,setHotelDetail] = useState({})

  useEffect(() => {
      fetchHotelDetail(id)
  }, []);

  const fetchHotelDetail = async () => {
      const data = await getHotelDetail(id)

      if (data && data.code && data.code === 200 && data.result) {
        setHotelDetail(data.result)
        console.log(data.result)
        console.log(hotelDetail)
      }
  } 




  return (
    <>

        <div className="hotel-detail-container container ">
        Hình ảnh khách sạn
        <ImageComponent imgs={hotelDetail?.imgs} avatar={hotelDetail?.avartar} />

        <div className="main-info-hotel">

        <HotelName name = {hotelDetail?.name} subname = {hotelDetail?.subName} />

        <HotelDescription address = {hotelDetail.address} description={hotelDetail?.description} />


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
