import room0 from "../../../assets/img/room/room-b4.webp";
import room1 from "../../../assets/img/room/room-b1.jpg";
import room2 from "../../../assets/img/room/room-b2.jpg";
import room3 from "../../../assets/img/room/room-b3.jpg";
import "./style.scss";
import { useNavigate } from "react-router-dom";

const HomeRoom = () => {

    const navigotor = useNavigate();

    const rooms = [
        {
            img: room0,
            title: "Việt Nam",
            description: "Khám phá nhiều điểm đến hấp dẫn khác trên khắp Việt Nam.",
            info: {
                hotels: "10.000+ khách sạn trên toàn quốc",
                roomTypes: "Từ bình dân đến cao cấp",
                priceRange: "Linh hoạt theo vùng",
                highlights: "Đa dạng điểm đến, hỗ trợ tìm phòng nhanh chóng"
            }
        },
        {
            img: room1,
            title: "Hà Nội",
            description: "Thủ đô nghìn năm văn hiến, nơi lưu giữ bản sắc văn hóa Việt.",
            info: {
                hotels: "Hơn 2.000 khách sạn",
                roomTypes: "Khách sạn 3-5 sao, homestay, căn hộ dịch vụ",
                priceRange: "Từ 300.000đ/đêm",
                highlights: "Gần phố cổ, view hồ Hoàn Kiếm, tiện di chuyển"
            }
        },
        {
            img: room2,
            title: "TP. Hồ Chí Minh",
            description: "Thành phố năng động, trung tâm kinh tế lớn nhất cả nước.",
            info: {
                hotels: "Khoảng 3.000+ khách sạn",
                roomTypes: "Khách sạn sang trọng, căn hộ cao cấp, hostel trẻ trung",
                priceRange: "Từ 250.000đ/đêm",
                highlights: "Vị trí trung tâm, gần chợ Bến Thành, phố Tây Bùi Viện"
            }
        },
        {
            img: room3,
            title: "Đà Nẵng",
            description: "Thành phố đáng sống với biển xanh, núi đẹp và nhịp sống hiện đại.",
            info: {
                hotels: "Hơn 1.500 khách sạn",
                roomTypes: "Resort ven biển, villa, khách sạn view sông Hàn",
                priceRange: "Từ 400.000đ/đêm",
                highlights: "Gần biển Mỹ Khê, cầu Rồng, bán đảo Sơn Trà"
            }
        },
    ];

    return (
        <section className="hp-room-section mb-10">
            <div className="container-fluid">
                <div className="hp-room-items">
                    <div className="row">
                        {rooms.map((room, index) => (
                            <div className="col-lg-3 col-md-6" key={index}>
                                <div
                                    className="hp-room-item"
                                    style={{
                                        backgroundImage: `url(${room.img})`,
                                        backgroundSize: "cover",
                                        backgroundPosition: "center",
                                    }}
                                >
                                    <div className="hr-text">
                                        <h3>{room.title}</h3>
                                        <h2>{room.description}</h2>
                                        <table>
                                            <tbody>
                                                <tr>
                                                    <td className="r-o">Số khách sạn:</td>
                                                    <td>{room.info.hotels}</td>
                                                </tr>
                                                <tr>
                                                    <td className="r-o">Loại phòng:</td>
                                                    <td>{room.info.roomTypes}</td>
                                                </tr>
                                                <tr>
                                                    <td className="r-o">Mức giá:</td>
                                                    <td>{room.info.priceRange}</td>
                                                </tr>
                                                <tr>
                                                    <td className="r-o">Nổi bật:</td>
                                                    <td>{room.info.highlights}</td>
                                                </tr>
                                            </tbody>
                                        </table>
                                    </div>
                                </div>
                            </div>
                        ))}
                    </div>
                </div>
            </div>
            <div className="text-center mt-3">
            <a href="#" onClick={() => { navigator('/') }} className="primary-btn about-btn text-dark">
                Khám phá thêm
            </a>
            </div>

        </section>
    );
};

export default HomeRoom;
