import React, { useState } from "react";
import "./style.scss";
import CustomCard from "../../../components/common/Card";
import Container from 'react-bootstrap/Container';
import Row from 'react-bootstrap/Row';
import Col from 'react-bootstrap/Col';
import { SlCalender } from "react-icons/sl";
import { FaHotel } from "react-icons/fa";
import Badge from 'react-bootstrap/Badge';
import { MdOutlinePayments } from "react-icons/md";
import { FaInfo } from "react-icons/fa6";
import { Outlet } from "react-router-dom";
import Handle from "rc-slider/lib/Handles/Handle";
import ConfirmInfomationPage from "../ConfirmInfomationPage";
import { useContext } from "react";
import PaymentContext from "../../../context/PaymentContext";
import PaymentPage from "../PaymentPage";

const TripDetail = () => {

    return (<>

        <Container>
            <Row>
                <Col xs md="auto">
                <div className="trip-details d-flex text-start">
                    <div className="check-in mx-3">
                        <h5>CN, 6 tháng 4</h5>
                        <h6 className="mt-1"><Badge bg="primary">Nhận phòng</Badge></h6>
                    </div>
                    <div className="mx-2">→</div>
                    <div className="check-out mx-3">
                        <h5>T2, 7 tháng 4</h5>
                        <h6 className="mt-1"><Badge bg="primary">Trả phòng</Badge></h6>
                    </div>
                </div>              
                </Col>
                <Col>
                <div className="text-end">
                    <h5>1</h5>
                    <span className="text-muted">đêm</span>
                </div>            
                </Col>
            </Row>
        </Container>
    </>)
}

const RoomInfo = () => {
    return (
        <>
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
            </div>
        
        
        </>
    )
}

const BillContainer = () => {
    return (
        <div className="bill-container">
            <Container className="mt-4 p-3 detail-section" style={{backgroundColor : "rgb(248, 243, 243)"}}>
                <h5 className="mt-1"><Badge bg="primary">Hóa đơn đã tính toán</Badge></h5>
                <div className="d-flex justify-content-between mt-3 mb-3">
                    <h6 className="text-start mb-0">Giá gốc (1 phòng x 8 đêm)</h6>
                    <h6 className="text-end mb-0">36.434.307 ₫</h6>
                </div>
                <div className="d-flex justify-content-between mb-3">
                    <h6 className="text-start mb-0">Giá gốc (1 phòng x 8 đêm)</h6>
                    <h6 className="text-end mb-0">36.434.307 ₫</h6>
                </div>
                <div className="d-flex justify-content-between mb-3 discount-section">
                    <h6 className="text-start mb-0 title">Giá gốc (1 phòng x 8 đêm)</h6>
                    <h6 className="text-end mb-0">36.434.307 ₫</h6>
                </div>
                <div className="d-flex justify-content-between mb-3 discount-section">
                    <h6 className="text-start mb-0 title">Phí đặt trước</h6>
                    <h6 className="text-end mb-0">MIỄN PHÍ</h6>
                </div>
            </Container>

            <Container className="p-3 summary-section">
                <div className="d-flex justify-content-between">
                    <h5 style={{ display: "flex", alignItems: "center", gap: "2px" }}>
                        Giá tiền <FaInfo size={15} />
                    </h5>
                    <h5 className="text-end mb-0 fw-semibold">18.157.416 ₫</h5>
                </div>
                <hr style={{border: "none", borderTop: "1px dashed rgb(248, 243, 243)"}}></hr>
                <h6 className="text-start mb-0 fw-light">Giá đã bao gồm: <span className="fw-light">Phí dịch vụ 5%</span></h6>
            </Container>
        
        </div>
    )
}

const CheckOutPage = ()  => {

    const { pageState, setPageState } = useContext(PaymentContext);

    const handleNextStep = () => {
        if (pageState !== 2) {
            setPageState(prevState => prevState + 1);
        }
    };

    return (
        <Container className="resevation-container mb-5" >
            <div className="row mt-4">
                <div className="col-md-6">
                    {pageState === 0 && <ConfirmInfomationPage handleNextStep = {handleNextStep} />}
                    {pageState === 1 && <PaymentPage handleNextStep = {handleNextStep} />}
                </div>

                <div className="col-md-6">
                    <CustomCard name={'Lịch đặt phòng'} subTitle={'Lưu ý lịch đặt phòng của bạn !'} icon={<SlCalender />} children={<TripDetail/>} />    

                    <CustomCard className={'shadow-3 mt-3 property-card border p-3 mb-3 rounded'} 
                        subTitle={'Thông tin phòng của bạn !'}
                        name={'Phòng của bạn'} icon={<FaHotel /> } children={<RoomInfo />} />


                    <CustomCard className={'shadow-3 mt-3 property-card border p-3 mb-3 rounded'} 
                                            subTitle={'Xem chi tiết hóa đơn của bạn'}
                                            name={'Hóa đơn của bạn'} icon={<MdOutlinePayments /> } children={<BillContainer />} />
                </div>
            </div>
        </Container>
    );
}

export default CheckOutPage;
