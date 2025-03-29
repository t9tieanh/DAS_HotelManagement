import React, { useState } from "react";
import "./style.scss";
import logo from "../../assets/img/logo.png";

function ReservationCheckOut() {
    const [selectedOption, setSelectedOption] = useState("riskFree");
    const handleOptionChange = (e) => {
        setSelectedOption(e.target.value);
    };
    return (
        <div className="container1">
            {/* Header with steps & timer */}
            <div className="header d-flex justify-content-center align-items-center p-3">
                <div className="logo">
                    <img src={logo} alt="Agoda Logo" className="logo-img" />
                </div>
                <div className="steps d-flex align-items-center justify-content-center">
                    <div className="step active">
                        <div className="circle">1</div>
                        <span>Thông tin khách hàng</span>
                    </div>
                    <div className="line"></div>
                    <div className="step">
                        <div className="circle">2</div>
                        <span>Chi tiết thanh toán</span>
                    </div>
                    <div className="line"></div>
                    <div className="step">
                        <div className="circle">3</div>
                        <span>Đã xác nhận đặt phòng!</span>
                    </div>
                </div>
            </div>

            {/* Timer bar */}
            <div className="timer-bar text-danger fw-bold text-center p-2">
                Chúng tôi đang giữ phòng cho quý khách... ⌛00:19:55
            </div>

            {/* Main content area */}
            <div className="row mt-4">
                {/* Left Column */}
                <div className="col-md-6">
                    {/* Payment Options Card */}
                    <div className="payment-options border p-3 mb-3 rounded">
                        <h5>Chọn cách thanh toán</h5>

                        {/* Lựa chọn 1 */}
                        <div
                            className={`option-wrapper ${selectedOption === "riskFree" ? "option-selected" : ""
                                }`}
                        >
                            <div className="form-check">
                                <input
                                    className="form-check-input"
                                    type="radio"
                                    name="paymentOption"
                                    id="riskFree"
                                    value="riskFree"
                                    checked={selectedOption === "riskFree"}
                                    onChange={handleOptionChange}
                                />
                                <label className="form-check-label" htmlFor="riskFree">
                                    Thanh toán vào ngày <strong>2 Tháng 4, 2025</strong>
                                </label>
                            </div>

                            {/* Khối mô tả lợi ích */}
                            <div className="riskfree-details">
                                <strong>🛡️ KHÔNG SỢ RỦI RO!</strong> Đặt phòng hôm nay và thanh toán vào
                                2 Tháng 4, 2025
                                <br />
                                <small className="text-muted">
                                    Hủy không tốn phí trước 2 Tháng 4, 2025 (giờ địa phương).
                                    <br />
                                </small>
                            </div>
                            <p className="text-note">Không thanh toán hôm nay; <a href="#learn-more">
                                Tìm hiểu thêm
                            </a> về ảnh hưởng đến giá</p>
                        </div>

                        <hr />

                        {/* Lựa chọn 2 */}
                        <div
                            className={`option-wrapper ${selectedOption === "payNow" ? "option-selected" : ""
                                }`}
                        >
                            <div className="form-check">
                                <input
                                    className="form-check-input"
                                    type="radio"
                                    name="paymentOption"
                                    id="payNow"
                                    value="payNow"
                                    checked={selectedOption === "payNow"}
                                    onChange={handleOptionChange}
                                />
                                <label className="form-check-label" htmlFor="payNow">
                                    Thanh toán ngay
                                </label>
                                <p className="text-note">Bạn có thể chọn thanh toán ngay bằng MoMo hoặc VNPay</p>
                            </div>

                            {/* Biểu tượng thanh toán */}
                            <div className="payment-icons mt-2">
                                <img
                                    src="https://upload.wikimedia.org/wikipedia/vi/f/fe/MoMo_Logo.png"
                                    alt="MoMo"
                                    className="payment-icon"
                                />
                                <img
                                    src="https://vinadesign.vn/uploads/images/2023/05/vnpay-logo-vinadesign-25-12-57-55.jpg"
                                    alt="VNPay"
                                    className="payment-icon"
                                />
                            </div>
                        </div>
                    </div>

                    {/* Guest Info Card */}
                    <div className="guest-info border p-3 rounded">
                        <h5>Ai là khách chính?</h5>
                        <p>Vui lòng nhập thông tin khách hàng chính.</p>
                        <div className="form-check">
                            <input
                                className="form-check-input"
                                type="radio"
                                id="guest1"
                                name="guest"
                            />
                            <label className="form-check-label" htmlFor="guest1">
                                Đức Sang
                            </label>
                        </div>
                    </div>
                </div>

                {/* Right Column */}
                <div className="col-md-6">
                    <div className="trip-summary border p-3 mb-3 rounded d-flex justify-content-between">
                        <div className="trip-details d-flex">
                            <div className="check-in mx-3">
                                <h6>CN, 6 tháng 4</h6>
                                <span className="text-muted">Nhận phòng</span>
                            </div>
                            <div className="mx-2">→</div>
                            <div className="check-out mx-3">
                                <h6>T2, 7 tháng 4</h6>
                                <span className="text-muted">Trả phòng</span>
                            </div>
                        </div>
                        <div className="nights text-end">
                            <h6>1</h6>
                            <span className="text-muted">đêm</span>
                        </div>
                    </div>
                    {/* Property Card #1 */}
                    <div>
                        {/* Property Card #1 */}
                        <div className="property-card border p-3 mb-3 rounded">
                            {/* Các badge */}
                            <div className="d-flex align-items-start">
                                {/* Hình ảnh */}
                                <img
                                    src="https://nhuminhplazahotel.com/wp-content/uploads/2023/06/khach-san-bai-bien-my-khe-2.jpg" // Đổi link ảnh thật
                                    alt="Property"
                                    className="img-fluid me-3"
                                    style={{ width: "auto", height: "150px", borderRadius: "4px" }}
                                />

                                <div className="flex-grow-1">
                                    <div className="d-flex align-items-center mb-2">
                                        <span className="badge bg-warning text-dark me-2">Bán chạy nhất</span>
                                        <span className="badge bg-success text-white">Wi-Fi miễn phí</span>
                                    </div>

                                    {/* Tên khách sạn / Căn hộ */}
                                    <h5 className="fw-bold mt-2">
                                        Căn hộ dịch vụ CeLaVie - The Vinhomes and Landmark
                                    </h5>

                                    {/* Điểm đánh giá */}
                                    <div className="mb-2">
                                        <span>
                                            ⭐⭐⭐⭐⭐
                                        </span>
                                        <small className="text-muted">(621 bài đánh giá)</small>
                                    </div>

                                    <p className="text-muted mb-2">
                                        Địa chỉ: 208 Nguyen Huu Canh Street, 22 Ward, Bình Thạnh District
                                    </p>
                                </div>
                            </div>

                            {/* Địa chỉ */}
                            <p className="text-muted mb-2">
                                Căn hộ sang trọng với view thành phố tuyệt đẹp, đầy đủ tiện nghi và gần trung tâm.
                            </p>


                            {/* Property Card #2 */}
                            <div className="property-card border p-3 rounded">
                                <h5>1 x Căn Hộ Loại Sang 1 Phòng Ngủ Có Ban Công</h5>
                                <p>52 m² | Tối đa: 1 Người lớn</p>

                                {/* Danh sách tiện ích */}
                                <ul className="list-unstyled text-muted">
                                    <li>
                                        <i className="bi bi-check-circle-fill text-success me-1"></i>
                                        Có nơi giữ hành lý
                                    </li>
                                    <li>
                                        <i className="bi bi-check-circle-fill text-success me-1"></i>
                                        Nhanh lên! Phòng cuối cùng cho khung giờ này
                                    </li>
                                    <li>
                                        <i className="bi bi-check-circle-fill text-success me-1"></i>
                                        Tiện ích A, Tiện ích B, ...
                                    </li>
                                </ul>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    );
}

export default ReservationCheckOut;
