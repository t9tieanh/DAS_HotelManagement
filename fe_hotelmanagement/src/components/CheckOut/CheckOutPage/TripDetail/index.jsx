import React, { useState } from "react";
import "./style.scss";
import Container from 'react-bootstrap/Container';
import Row from 'react-bootstrap/Row';
import Col from 'react-bootstrap/Col';
import Badge from 'react-bootstrap/Badge';

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

export default TripDetail