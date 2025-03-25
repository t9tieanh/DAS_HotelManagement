import React from "react";
import "./style.scss";
import logo from "../../assets/img/logo.png";

function ReservationCheckOut() {
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
                        <p>
                            Thanh toán vào ngày <strong>7 Tháng 4, 2025</strong>
                        </p>

                        <div className="form-check my-2">
                            <input
                                className="form-check-input"
                                type="checkbox"
                                id="riskFree"
                            />
                            <label className="form-check-label" htmlFor="riskFree">
                                <strong>KHÔNG SỢ RỦI RO!</strong> Đặt phòng hôm nay và thanh toán vào 7 Tháng 4, 2025
                            </label>
                        </div>
                        <small className="text-muted">
                            Không thanh toán hôm nay;{" "}
                            <a href="#learn-more">Tìm hiểu thêm</a>
                        </small>

                        <hr />

                        <div className="form-check my-2">
                            <input
                                className="form-check-input"
                                type="radio"
                                name="paymentOption"
                                id="payNow"
                            />
                            <label className="form-check-label" htmlFor="payNow">
                                Thanh toán ngay
                            </label>
                        </div>
                        <div className="payment-icons mt-2">
                            <img
                                src="https://upload.wikimedia.org/wikipedia/commons/a/a4/Visa_Logo.png"
                                alt="Visa"
                                className="payment-icon"
                            />
                            <img
                                src="https://upload.wikimedia.org/wikipedia/commons/b/b5/PayPal.svg"
                                alt="PayPal"
                                className="payment-icon"
                            />
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
                    <div className="trip-summary border p-3 mb-3 rounded d-flex">
                        <div className="check-in">
                            <h6>CN, tháng 4 6</h6>
                            <span className="text-muted">Nhận phòng</span>
                        </div>
                        <div className="arrow">
                            <i className="bi bi-arrow-right"></i>
                        </div>
                        <div>→</div>
                        <div className="check-out">
                            <h6>T2, tháng 4 7</h6>
                            <span className="text-muted">Trả phòng</span>
                        </div>
                        <div className="nights">
                            <h6>1</h6>
                            <span className="text-muted">đêm</span>
                        </div>
                    </div>
                    {/* Property Card #1 */}
                    <div className="property-card border p-3 mb-3 rounded">
                        <span className="badge bg-warning text-dark mb-2">Bán chạy nhất</span>
                        <span className="badge bg-success text-white mb-2">Wi-Fi miễn phí</span>
                        <h5 className="mt-2">
                            Căn hộ dịch vụ CeLaVie - The Vinhomes and Landmark
                        </h5>
                        <p>7.9 Rất tốt (621 bài đánh giá)</p>
                        <p className="text-muted">
                            Địa chỉ: 208 Nguyen Huu Canh Street, 22 Ward, Bình Thạnh District
                        </p>
                    </div>

                    {/* Property Card #2 */}
                    <div className="property-card border p-3 rounded">
                        <h5>1 x Căn Hộ Loại Sang 1 Phòng Ngủ Có Ban Công</h5>
                        <p>52 m² | Tối đa: 1 Người lớn</p>
                    </div>
                </div>
            </div>
        </div>
    );
}

export default ReservationCheckOut;
