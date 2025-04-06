import React, { useState } from "react";
import "./style.scss";
import Container from 'react-bootstrap/Container';
import Row from 'react-bootstrap/Row';
import Col from 'react-bootstrap/Col';
import TextInput from "../../../common/Input";
import { FaUserCircle, FaPhoneAlt } from "react-icons/fa";
import { MdOutlineMail } from "react-icons/md";


const CustomerInfomation = ({name, setName, phone, setPhone, email, setEmail}) => {
    return (
        <>
        <div>

        <Container >
            <Row>
                <TextInput text={name} inputType={'text'} setText={setName} name={<>&nbsp;&nbsp;<FaUserCircle />Họ và tên</>} />
            </Row>
            <Row className="mt-3">
                <Col><TextInput text={phone} inputType={'tel'} setText={setPhone} name={<><FaPhoneAlt />Số điện thoại</>} /></Col>
                <Col><TextInput text={email} inputType={'email'} setText={setEmail} name={<><MdOutlineMail />Email</>} /></Col>
            </Row>
        </Container>
            
        </div> 
        </>
    )
}

export default CustomerInfomation