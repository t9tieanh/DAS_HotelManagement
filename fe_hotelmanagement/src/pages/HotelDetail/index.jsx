import { useEffect, useState } from "react";
import "./style.scss";
import ImageComponent from "../../components/HotelDetailPage/ImageComponent/index.jsx";
import RoomSection from "../../components/HotelDetailPage/RoomCard/index.jsx";
import HotelName from "../../components/HotelDetailPage/NameComponent/index.jsx";
import HotelDescription from "../../components/HotelDetailPage/DescriptionComponent/index.jsx";
import { getHotelDetail } from "../../services/HotelService/hotelService.js";
import { useLocation, useNavigate } from "react-router-dom";
import { toast } from "react-toastify";


const HotelDetail = () => {
  const location = useLocation();
  const navigator = useNavigate()
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
    } else if (data.response && data.response.data) {
        toast.error(data.response.data.message) // trường hợp giao dịch hết thời gian
        navigator('/')
        return
    }
    else toast.error(data?.message) 
  }

  return (
    <>
      <div className="hotel-detail-container container shadow-3">
        <ImageComponent imgs={hotelDetail?.imgs} avatar={hotelDetail?.avatar} hotelId={hotelId} />
        <div className="main-info-hotel pt-0">
          <HotelName name={hotelDetail?.name} subname={hotelDetail?.subName} originalPrice={hotelDetail?.originalPrice} />
          <HotelDescription address={hotelDetail.address} description={hotelDetail?.description} facilities={hotelDetail?.facilities} />
        </div>
        <div className="card room-list-container">
          <div className="card-body">
            <h5 className="card-title room-header">Những phòng còn trống tại Khách sạn {hotelDetail?.name}</h5>
            <div className="warning-title">
              Phải đặt phòng trong thời điểm không chắc chắn này? Hãy chọn phòng có thể hủy miễn phí!
            </div>
            <RoomSection checkIn={checkIn} checkOut={checkOut} rooms={hotelDetail?.rooms || []} />
          </div>
        </div>
      </div>
    </>
  );
};

export default HotelDetail;
