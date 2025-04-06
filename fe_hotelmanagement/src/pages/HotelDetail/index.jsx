import { useEffect, useState } from "react";
import "./style.scss";
import ImageComponent from "../../components/HotelDetailPage/ImageComponent/index.jsx";
import RoomSection from "../../components/HotelDetailPage/RoomCard/index.jsx";
import HotelName from "../../components/HotelDetailPage/NameComponent/index.jsx";
import HotelDescription from "../../components/HotelDetailPage/DescriptionComponent/index.jsx";
import { getHotelDetail } from "../../services/HotelService/hotelService.js";
import { useParams, useLocation } from "react-router-dom";


const HotelDetail = () => {

  // const { id } = useParams(); 

  const location = useLocation();
  const { hotelId, adults, rooms, checkIn, checkOut } = location.state || {};

  const [hotelDetail, setHotelDetail] = useState({})

  useEffect(() => {
    fetchHotelDetail()
  }, []);

  const fetchHotelDetail = async () => {
    const data = await getHotelDetail(hotelId, adults, rooms, checkIn, checkOut);

    if (data && data.code && data.code === 200 && data.result) {
      setHotelDetail(data.result)
      console.log(data.result)
    }
  }

  return (
    <>

      <div className="hotel-detail-container container shadow-3">

        <ImageComponent imgs={hotelDetail?.imgs} avatar={hotelDetail?.avatar} hotelId={hotelId} />

        <div className="main-info-hotel pt-0">

          <HotelName name={hotelDetail?.name} subname={hotelDetail?.subName} />

          <HotelDescription address={hotelDetail.address} description={hotelDetail?.description} facilities={hotelDetail?.facilities} />

        </div>

        <div className="card room-list-container">
          <div className="card-body">
            <h5 className="card-title room-header">Những phòng còn trống tại Khách sạn Gold</h5>

            <div className="warning-title">
              Phải đặt phòng trong thời điểm không chắc chắn này? Hãy chọn phòng có thể hủy miễn phí!
            </div>

            <RoomSection rooms={hotelDetail?.rooms || []} />

          </div>
        </div>
      </div>
    </>
  );
};

export default HotelDetail;
