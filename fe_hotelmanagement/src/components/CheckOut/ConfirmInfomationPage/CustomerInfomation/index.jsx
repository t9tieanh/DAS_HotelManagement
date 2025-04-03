import React, { useState } from "react";
import "./style.scss";
import Container from 'react-bootstrap/Container';
import Row from 'react-bootstrap/Row';
import Col from 'react-bootstrap/Col';
import TextInput from "../../../common/Input";


const CustomerInfomation = () => {
    return (
        <>
        <div>

        <Container >
            <Row>
                <TextInput name={'_Họ và tên'} />
            </Row>
            <Row className="mt-3">
                <Col><TextInput name={'Họ và tên'} /></Col>
                <Col><TextInput name={'Họ và tên'} /></Col>
            </Row>
            <Row className="mt-3">
                <Col><TextInput name={'Họ và tên'} /></Col>
                <Col><TextInput name={'Họ và tên'} /></Col>
            </Row>
        </Container>
            
        </div> 
        </>
    )
}

export default CustomerInfomation