import React from "react";
import "./style.scss";
import SearchBar from "../../components/HotelSearchPage/SearchBarComponent";

const HotelSearchPage = () => {
  return (
    <div className="booking-page">
        <SearchBar />
      {/* Header */}
      <div className="booking-header">
        <div className="header-left">
          <h1>Đà Nẵng</h1>
          <p>1420 nơi lưu trú được tìm thấy</p>
        </div>
        
        <div className="header-filters">
          <label>
            Xếp theo:
            <select>
              <option>Độ phổ biến</option>
              <option>Giá thấp đến cao</option>
              <option>Giá cao đến thấp</option>
            </select>
          </label>
          <label>
            Hiển thị giá:
            <select>
              <option>Mỗi phòng mỗi đêm</option>
              <option>Tổng giá</option>
            </select>
          </label>
        </div>
      </div>
      

      <div className="main-content">
        {/* Sidebar bên trái */}
        <aside className="sidebar">
          {/* Khoảng giá */}
          <div className="price-filter">
            <h3>Khoảng giá</h3>
            <p>1 phòng, 1 đêm</p>
            <input
              type="range"
              min="0"
              max="24000000"
              defaultValue="0"
              style={{ width: "100%" }}
            />
            <div className="range-labels">
              <span>VND 0</span>
              <span>VND 24.000.000</span>
            </div>
          </div>

          {/* Lọc phổ biến */}
          <div className="popular-filters">
            <h3>Lọc phổ biến</h3>
            <label>
              <input type="checkbox" />
              Gần biển (123)
            </label>
            <label>
              <input type="checkbox" />
              +MÃ GIẢM (155)
            </label>
            <label>
              <input type="checkbox" />
              4-5 sao giá tốt (75)
            </label>
            <label>
              <input type="checkbox" />
              Vị trí thuận tiện (76)
            </label>
            <label>
              <input type="checkbox" />
              Được yêu thích nhất (36)
            </label>
            <a href="#xem-tat-ca">Xem Tất cả</a>
          </div>

          {/* Khuyến mãi & Giảm giá */}
          <div className="promotions">
            <h3>Khuyến mãi & Giảm giá</h3>
            <label>
              <input type="checkbox" />
              +MÃ GIẢM (155)
            </label>
            <label>
              <input type="checkbox" />
              Gần biển (123)
            </label>
            <label>
              <input type="checkbox" />
              Khuyến mãi dành cho bạn (121)
            </label>
            <label>
              <input type="checkbox" />
              Phù hợp cho cặp đôi (112)
            </label>
          </div>
        </aside>

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
                <span className="rating-score">9.0</span>
                <span>(168 đánh giá)</span>
                <span className="rating-text">Ấn tượng</span>
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
                <span className="rating-score">8.6</span>
                <span>(1.1N đánh giá)</span>
                <span className="rating-text">Ấn tượng</span>
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
                <span className="rating-score">8.5</span>
                <span>(703 đánh giá)</span>
                <span className="rating-text">Ấn tượng</span>
              </div>
            </div>
            <div className="hotel-price">
              <p className="original-price">590.637 VND</p>
              <p className="price-note">Chưa bao gồm thuế và phí</p>
              <button className="select-room-button">Chọn phòng</button>
            </div>
          </div>
        </section>
      </div>
    </div>
  );
};

export default HotelSearchPage;