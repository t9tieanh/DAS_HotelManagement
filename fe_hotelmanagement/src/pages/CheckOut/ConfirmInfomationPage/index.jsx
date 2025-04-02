import React, { useState } from "react";
import "./style.scss";
import CustomCard from "../../../components/common/Card";
import Container from 'react-bootstrap/Container';
import Row from 'react-bootstrap/Row';
import Col from 'react-bootstrap/Col';
import TextInput from "../../../components/common/Input";
import { Button } from "react-bootstrap";
import { MdPayment } from "react-icons/md";
import { FaUserCircle } from "react-icons/fa";
import { MdDiscount } from "react-icons/md";
import {Badge} from "react-bootstrap";


const PaymentOption = () => {
    const [selectedOption, setSelectedOption] = useState("riskFree");
    const handleOptionChange = (e) => {
        setSelectedOption(e.target.value);
    };

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

const Discount = () => {
    return (
        <>
            <Container>
                <Row className="mt-3">
                    <Col>

                    <div className="card mb-3">
                    <div className="card-body">
                        <div className="d-flex justify-content-between">
                        <div className="d-flex flex-row align-items-center">
                            <div>
                            <img
                                src="https://w7.pngwing.com/pngs/110/471/png-transparent-discounts-and-allowances-advertising-sales-promotion-computer-icons-marketing-logo-discount-sticker.png"
                                className="img-fluid rounded-3" alt="Shopping item" width={"50px"} />
                            </div>
                            <div className="ms-3">
                            <h5>Giảm giá mùa đông</h5>
                            <p className="small mb-0"><Badge bg="primary">Giảm 5%</Badge></p>
                            </div>
                        </div>
                        </div>
                    </div>
                    </div>
                    
                    </Col>
                    <Col>
                    
                    <div className="card bg-primary text-white rounded-3">
                        <div className="card-body">
                          <div className="d-flex justify-content-between align-items-center mb-4">
                            <h5 className="mb-0">Thêm mã giảm giá</h5>
                            <img src="https://png.pngtree.com/png-vector/20230408/ourmid/pngtree-price-tag-with-the-discount-icon-vector-png-image_6686659.png"
                              className="img-fluid rounded-3"  alt="Avatar" width={'70px'}/>
                          </div>
    
      
                          <form className="mt-4">
                            <TextInput className={'form-white'} name={'Mã giảm giá'} />
                          </form>
      
                          <hr className="my-4" />
    
      
                          <button  type="button" data-mdb-button-init data-mdb-ripple-init className="btn btn-info btn-block btn-lg">
                            <div className="d-flex justify-content-between">
                              <span>Save code <i className="fas fa-long-arrow-alt-right ms-2"></i></span>
                            </div>
                          </button>
      
                        </div>
                      </div>
                    
                    
                    
                    </Col>
                </Row>
            </Container>
        </>
    )
}



const ConfirmInfomationPage = ({handleNextStep})  => {
    return (
        <>
            <CustomCard icon={<FaUserCircle />}  className={'shadow-3'} name={'Thông tin khách hàng'} subTitle = {"*Mục bắt buộc"} children={<PaymentOption/>} />

            <CustomCard className={'shadow-3 mt-3 mb-3'} name={'Phiếu giảm giá'} 
                subTitle={'Hãy áp dụng mã giảm giá'} icon={<MdDiscount />}
                children={<Discount />}
            />

            <CustomCard className={'shadow-3 mt-3 mb-3'} children={<Button style={{width : '90%'}} onClick={handleNextStep} 
                className="d-block mx-auto d-flex align-items-center justify-content-center gap-2 button-continue" >
                <MdPayment size={'20'} />Kế tiếp: Bước thanh toán</Button>}
            />
        </>
    );
}

export default ConfirmInfomationPage;
