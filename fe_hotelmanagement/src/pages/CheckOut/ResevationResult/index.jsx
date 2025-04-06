import React, { useEffect, useState } from "react";
import { Container, Row, Col, Spinner } from "react-bootstrap";
import { CheckCircleFill } from "react-bootstrap-icons";

const ResevationResult = () => {
    return <>
    <Container className="text-center mt-5">
      <CheckCircleFill color="green" size={80} className="mb-4" />
      <h5>Đơn hàng của bạn đã thanh toán thành công.</h5>
      <p>
        Quý khách vui lòng{" "}
        <span style={{ color: "red", fontWeight: "bold" }}>KHÔNG</span> tắt trình duyệt
      </p>
      <p className="text-danger mt-4">
        Trở lại trang mua hàng trong {seconds} giây<br />
        <span className="text-muted">Xin vui lòng chờ trong giây lát...</span>
      </p>
    </Container>
    </>
}

export default ResevationResult