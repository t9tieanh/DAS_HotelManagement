import React, { useState } from "react";
import "./style.scss";
import Container from 'react-bootstrap/Container';
import Badge from 'react-bootstrap/Badge';
import { FaInfo } from "react-icons/fa6";
import { calculateNightsAndDays } from "../../../../utils/DateUtils";
import { formatCurrency } from "../../../../utils/Format/CurrencyFormat";


const BillContainer = ({reservationDetails, checkIn, checkOut, totalPrice}) => {

    const { days } = calculateNightsAndDays(checkIn, checkOut);

    return (
        <div className="bill-container">
            <Container className="mt-4 p-3 detail-section" style={{backgroundColor : "rgb(248, 243, 243)"}}>
                <h5 className="mt-1"><Badge bg="primary">Hóa đơn đã tính toán</Badge></h5>

                {reservationDetails?.map(
                    (reservationDetail) => {
                        return (
                            <div className="d-flex justify-content-between mt-3 mb-3">
                                <h6 className="text-start mb-0">Giá gốc ({reservationDetail.quantity} phòng {reservationDetail.name} x {days} đêm)</h6>
                                <h6 className="text-end mb-0">{formatCurrency(reservationDetail.quantity * reservationDetail.price * days)}₫</h6>
                            </div>
                        )
                    }
                )}


                {/* <div className="d-flex justify-content-between mb-3 discount-section">
                    <h6 className="text-start mb-0 title">Giá gốc (1 phòng x {days} đêm)</h6>
                    <h6 className="text-end mb-0">36.434.307 ₫</h6>
                </div> */}

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
                    <h5 className="text-end mb-0 fw-semibold">{formatCurrency(totalPrice)} ₫</h5>
                </div>
                <hr style={{border: "none", borderTop: "1px dashed rgb(248, 243, 243)"}}></hr>
                <h6 className="text-start mb-0 fw-light">Giá đã bao gồm: <span className="fw-light">Phí dịch vụ 5%</span></h6>
            </Container>
        
        </div>
    )
}

export default BillContainer