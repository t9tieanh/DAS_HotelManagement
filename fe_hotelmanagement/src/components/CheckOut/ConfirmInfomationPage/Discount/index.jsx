import React, { useState } from "react";
import "./style.scss";
import Container from 'react-bootstrap/Container';
import Row from 'react-bootstrap/Row';
import Col from 'react-bootstrap/Col';
import TextInput from "../../../common/Input";
import CustomModal from "../../../common/Modal";
import { MdDiscount } from "react-icons/md";
import PrimaryButton from "../../../common/button/btn-primary";
import { FaSearch } from "react-icons/fa";
import { FaLocationArrow } from "react-icons/fa";
import DiscountBox from "./DiscountBox";
import HorizontalCard from "../../../common/HorizontalCard";
import { formatCurrency } from "../../../../utils/Format/CurrencyFormat";
import { MdError } from "react-icons/md";
import { IoCloseCircleSharp } from "react-icons/io5";
import { applyDiscount } from "../../../../services/ReservationService/reservationService";
import { toast } from "react-toastify";
import { useSelector } from "react-redux";
import { getTotalPrice } from "../../../../services/ReservationService/reservationService";


const Discount = ({appliedDiscounts, setAppliedDiscounts, setTotalPrice}) => {

    const [isOpenDiscountBox, setIsOpenDiscountBox] = useState(false)
    const reservationId = useSelector(state => state.reservation.reservationId)

    const [privateCode, setPrivateCode] = useState()

    const applyPrivateDiscountCode = async () => {
        if (!privateCode || privateCode.trim() === '') {
            toast.error('Vui lòng nhập mã giảm giá')
            return
        }

        // map về set code discount 
        const updatedAppliedDiscounts = [...appliedDiscounts.map(discount => discount.code), privateCode]

        // tiến hành gọi backend để áp dụng mã giảm giá
        const data = await applyDiscount(reservationId, updatedAppliedDiscounts)
        if (data && data.code && data.code === 200) {
            toast.success(data.message)
            setAppliedDiscounts(data.result.discounts)

            // tiến hành cập nhật lại giá tiền 
            const priceRes = await getTotalPrice(reservationId)
            if (priceRes && priceRes.code && priceRes.code === 200 && priceRes.result) 
                setTotalPrice(priceRes.result)

        } else if (data.response && data.response.data) {
            toast.error(data.response.data.message) // trường hợp giao dịch hết thời gian
        }
        else {
            toast.error(data?.message)
        }
    }

    return (
        <>
            <Container>
                <Row className="mt-3">
                    <Col>
                    {appliedDiscounts?.length > 0 ? (
                        appliedDiscounts.map((discount) => (
                            <HorizontalCard
                            key={discount.code}
                            img="https://www.shutterstock.com/image-illustration/red-price-tag-label-percentage-600nw-1947684382.jpg"
                            name={discount.name}
                            subName={
                                <div>Giảm {discount.discountPrecentage}% Giảm tối đa {formatCurrency(discount.maxDiscountAmount)} VND</div>
                            }
                            >
                            </HorizontalCard>
                        ))
                        ) : 
                    (
                        <div className="text-left text-danger mb-2"><MdError />Hiện tại bạn chưa chọn giảm giá nào !</div>
                    )}
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
                                <TextInput text={privateCode} setText={setPrivateCode} 
                                    className={'form-white'} name={'Mã giảm giá'} 
                                />
                            </form>
                            <PrimaryButton className={'bg-info mt-2 p-2'} text={'Tìm giảm giá đặc biệt'} icon = {<FaSearch />}
                                onClickFunc={applyPrivateDiscountCode} />        
                            <hr className="my-4" />

                            <PrimaryButton className={'bg-info mt-2'} text={'Chọn mã giảm giá'} icon={<FaLocationArrow />} onClickFunc={() => {setIsOpenDiscountBox(true)}}/>
                            </div>
                        </div>
                    </Col>
                </Row>
            </Container>
            <CustomModal  title={'Hãy chọn mã giảm giá'} icon={<MdDiscount />} 
                show={isOpenDiscountBox} setShow = {setIsOpenDiscountBox} size={'lg'} 
                content={<DiscountBox appliedDiscounts = {appliedDiscounts} setAppliedDiscounts={setAppliedDiscounts} setTotalPrice = {setTotalPrice}/>}
                btnClose={<PrimaryButton text={'Đóng'} className={'bg-light text-dark'} icon={<IoCloseCircleSharp />} onClickFunc={() => {setIsOpenDiscountBox(false)}}/>}
            />
        </>
    )
}

export default Discount