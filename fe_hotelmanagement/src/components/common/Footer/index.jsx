import logo from '../../../assets/img/logo1.png';

const Footer = () => {
    return (
        <>
            <footer className="footer-section">
                <div className="container">
                    <div className="footer-text">
                        <div className="row">
                            <div className="col-lg-4">
                                <div className="ft-about">
                                    <div className="logo">
                                        <a href="#">
                                            <img src={logo} alt="Hotelas Logo" />
                                        </a>
                                    </div>
                                    <p>
                                        Hệ thống đặt phòng khách sạn toàn quốc, 
                                        tập trung vào ba thành phố lớn: Hà Nội, Đà Nẵng và TP. Hồ Chí Minh.
                                    </p>
                                    <div className="fa-social">
                                        <a href="#"><i className="fa fa-facebook"></i></a>
                                        <a href="#"><i className="fa fa-twitter"></i></a>
                                        <a href="#"><i className="fa fa-instagram"></i></a>
                                        <a href="#"><i className="fa fa-youtube-play"></i></a>
                                    </div>
                                </div>
                            </div>

                            <div className="col-lg-3 offset-lg-1">
                                <div className="ft-contact">
                                    <h6>Liên hệ</h6>
                                    <ul>
                                        <li>Điện thoại: (+84) 345 6789</li>
                                        <li>Email: hotelas@gmail.com</li>
                                        <li>Phát triển bởi: Phạm Tiến Anh & Nguyễn Đức Sang</li>
                                    </ul>
                                </div>
                            </div>

                            <div className="col-lg-3 offset-lg-1">
                                <div className="ft-newslatter">
                                    <h6>Hỗ trợ khách hàng</h6>
                                    <p>
                                        Gửi email cho chúng tôi nếu bạn cần hỗ trợ hoặc gặp sự cố trong quá trình đặt phòng.
                                    </p>
                                    <form action="#" className="fn-form">
                                        <input type="text" placeholder="Nhập email của bạn" />
                                        <button type="submit"><i className="fa fa-send"></i></button>
                                    </form>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </footer>
        </>
    );
};

export default Footer;
