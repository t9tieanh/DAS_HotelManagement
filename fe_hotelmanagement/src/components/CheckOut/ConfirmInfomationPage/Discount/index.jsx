import React, { useState } from "react";
import "./style.scss";
import Container from 'react-bootstrap/Container';
import Row from 'react-bootstrap/Row';
import Col from 'react-bootstrap/Col';
import TextInput from "../../../common/Input";
import {Badge} from "react-bootstrap";
import CustomModal from "../../../common/Modal";
import { MdDiscount } from "react-icons/md";
import PrimaryButton from "../../../common/button/btn-primary";
import { FaSearch } from "react-icons/fa";
import { FaLocationArrow } from "react-icons/fa";
import DiscountBox from "./DiscountBox";

const Discount = () => {

    const [isOpenDiscountBox, setIsOpenDiscountBox] = useState(false)

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
                            <PrimaryButton className={'bg-info mt-2 p-2'} text={'Tìm giảm giá đặc biệt'} icon = {<FaSearch />}/>        
                            <hr className="my-4" />

                            <PrimaryButton className={'bg-info mt-2'} text={'Chọn mã giảm giá'} icon={<FaLocationArrow />} onClickFunc={() => {setIsOpenDiscountBox(true)}}/>
                            </div>
                        </div>
                    </Col>
                </Row>
            </Container>
            <CustomModal  title={'Hãy chọn mã giảm giá'} icon={<MdDiscount />} 
                show={isOpenDiscountBox} setShow = {setIsOpenDiscountBox} size={'lg'} content={<DiscountBox />}
                btnClose={<PrimaryButton text={'Áp dụng ngay'} icon={<FaLocationArrow />} className={'bg-warning'} />}
            />
        </>
    )
}

export default Discount