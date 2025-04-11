import { useNavigate } from "react-router-dom";
import about1 from "../../../assets/img/about/about-1.jpg";
import about2 from "../../../assets/img/about/about-2.jpg";
import './style.scss'

export default () => {

    const navigotor = useNavigate();

    return <>
        <section className="aboutus-section spad">
        <div className="container">
            <div className="row">
            <div className="col-lg-6">
                <div className="about-text">
                <div className="section-title">
                    <span><span className="text-danger">H</span>otelAS</span>
                    <h2>Đặt phòng dễ dàng<br />mọi nơi trên khắp Việt Nam</h2>
                </div>
                <p className="f-para">
                    HotelAS là nền tảng đặt phòng trực tuyến giúp bạn dễ dàng tìm kiếm, so sánh và đặt chỗ nghỉ phù hợp chỉ trong vài bước. Với hệ thống hàng ngàn khách sạn, resort, homestay trên khắp cả nước, chúng tôi mang đến sự linh hoạt và tiện lợi cho mọi hành trình.
                </p>
                <p className="s-para">
                    Dù bạn đi công tác, nghỉ dưỡng hay khám phá địa phương mới, HotelAS luôn sẵn sàng đồng hành cùng bạn với giao diện thân thiện, giá tốt và trải nghiệm đặt phòng nhanh chóng.
                </p>
                <a href="#" onClick={() => {navigotor('/')}} className="primary-btn about-btn">Khám phá thêm</a>
                </div>
            </div>
            <div className="col-lg-6">
                <div className="about-pic">
                <div className="row">
                    <div className="col-sm-6">
                    <img src={about1} alt="Ảnh khách sạn" />
                    </div>
                    <div className="col-sm-6">
                    <img src={about2} alt="Ảnh phòng nghỉ" />
                    </div>
                </div>
                </div>
            </div>
            </div>
        </div>
        </section>
    </>
}