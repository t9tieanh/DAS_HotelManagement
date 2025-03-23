import React from "react";
import './style.scss';

const HotelComponent = () => {
    return (
        <>
            {/* Danh sách khách sạn bên phải */}
            <section className="hotel-list">
                {/* Ví dụ 1 */}
                <div className="hotel-card">
                    <div className="hotel-image">
                        {/* Thay ảnh thật nếu muốn */}
                        <img
                            src="https://bluejaypms.com/data_news/art-0688.jpg"
                            alt="Orchide'es Ocean Hotel"
                        />
                    </div>
                    <div className="hotel-info">
                        <div className="hotel-title">
                            <h2>Orchide'es Ocean Hotel</h2>
                            <p>
                                Khách sạn <span className="hotel-stars">★ ★ ★</span>
                            </p>
                            <p>Phước Mỹ, Đà Nẵng • Quầy bar</p>
                        </div>
                        <div className="hotel-rating">
                            <span className="rating-score">168 đánh giá</span>
                        </div>
                    </div>
                    <div className="hotel-price">
                        <p className="original-price">4.056.437 VND</p>
                        <p className="sale-price">486.772 VND</p>
                        <p className="price-note">
                            Chỉ còn 1 phòng có giá này! <br /> Chưa bao gồm thuế và phí
                        </p>
                        <button className="select-room-button">Chọn phòng</button>
                    </div>
                </div>

                {/* Ví dụ 2 */}
                <div className="hotel-card">
                    <div className="hotel-image">
                        <img
                            src="https://bluejaypms.com/data_news/art-0688.jpg"
                            alt="Muong Thanh Grand Da Nang Hotel"
                        />
                    </div>
                    <div className="hotel-info">
                        <div className="hotel-title">
                            <h2>Muong Thanh Grand Da Nang Hotel</h2>
                            <p>
                                Khách sạn <span className="hotel-stars">★ ★ ★ ★</span>
                            </p>
                            <p>An Hải Tây, Đà Nẵng • Quầy bar, Máy sấy quần áo, Sân thượng...</p>
                        </div>
                        <div className="hotel-rating">
                            <span className="rating-score">168 đánh giá</span>
                        </div>
                    </div>
                    <div className="hotel-price">
                        <p className="coupon">+COUPON 500K</p>
                        <p className="original-price">1.349.206 VND</p>
                        <p className="sale-price">1.011.905 VND</p>
                        <p className="price-note">Chưa bao gồm thuế và phí</p>
                        <button className="select-room-button">Chọn phòng</button>
                    </div>
                </div>

                {/* Ví dụ 3 */}
                <div className="hotel-card">
                    <div className="hotel-image">
                        <img
                            src="https://bluejaypms.com/data_news/art-0688.jpg"
                            alt="Khách sạn Alibaba Đà Nẵng"
                        />
                    </div>
                    <div className="hotel-info">
                        <div className="hotel-title">
                            <h2>Khách sạn Alibaba Đà Nẵng</h2>
                            <p>
                                Khách sạn <span className="hotel-stars">★ ★ ★</span>
                            </p>
                            <p>Phước Mỹ, Đà Nẵng</p>
                        </div>
                        <div className="hotel-rating">
                            <span className="rating-score">168 đánh giá</span>
                        </div>
                    </div>
                    <div className="hotel-price">
                        <p className="original-price">590.637 VND</p>
                        <p className="price-note">Chưa bao gồm thuế và phí</p>
                        <button className="select-room-button">Chọn phòng</button>
                    </div>
                </div>
            </section>
        </>
    )
}

export default HotelComponent;
