import ArrowButton from "../../common/button/button-arrow";
import CustomCard from "../../common/Card";
import Card from "../../common/Card";
import DescriptionComponent from "./DescriptionComponent";
import HotelFacility from "./FacilityComponent";
import HotelLocation from "./LocationComponent";

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


const HotelDescription = ({description, address}) => {

    const addressStr = `${address?.concrete}, ${address?.commune}, ${address?.district}, ${address?.city}`;

    return (
        <>
        
        <div className="container info-section shadow-3" >
            <div className="row p-2 g-3">
                <HotelFacility />
                <HotelFacility />
                <HotelLocation location = {addressStr} />
            </div>

            <DescriptionComponent text={description} />

        </div>
        
        
        </>
    )

}

export default HotelDescription