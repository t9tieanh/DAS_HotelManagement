import React, { useState } from "react";
import "./style.scss";
import Container from 'react-bootstrap/Container';
import Row from 'react-bootstrap/Row';
import Col from 'react-bootstrap/Col';
import TextInput from "../../../common/Input/Input2";
import { FaUserCircle, FaPhoneAlt } from "react-icons/fa";
import { MdOutlineMail } from "react-icons/md";


const CustomerInfomation = ({name, setName, phone, setPhone, email, setEmail}) => {
    return (
        <>
        <div>

        <Container >
            <Row>
                <TextInput value={name} setValue={setName} inputType={'text'} 
                  idInput = {'input-customer-name'}  name={<>&nbsp;&nbsp;<FaUserCircle />Họ và tên</>}
                  placeholder={'Nhập họ và tên'} className={'p-1'} height={'40px'}
                />
            </Row>
            <Row className="mt-3 p-0">
                <Col>
                    <TextInput placeholder={'Nhập số điện thoại'} value={phone} 
                        inputType={'tel'} setValue={setPhone} name={<><FaPhoneAlt />Số điện thoại</>}
                        height={'40px'} 
                        idInput = {'input-customer-phone'}
                    />
                </Col>
                <Col>
                    <TextInput placeholder={'Nhập email'} value={email}
                        setValue={setEmail} name={<><MdOutlineMail />Email</>} height={'40px'}
                        idInput = {'input-customer-email'}
                        inputType={'email'}
                    />
                </Col>
            </Row>
        </Container>
            
        </div> 
        </>
    )
}

export default CustomerInfomation