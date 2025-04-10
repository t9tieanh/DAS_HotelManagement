import React, { useEffect, useState } from "react";
import "./style.scss";
import {Form, Alert} from 'react-bootstrap';
import HorizontalCard from "../../../../common/HorizontalCard";
import { getDiscountAvailable } from "../../../../../services/DiscountService/discountService";
import { toast } from "react-toastify";
import { formatCurrency } from "../../../../../utils/Format/CurrencyFormat";
import { MdError } from "react-icons/md";
import { useSelector } from "react-redux";
import PrimaryButton from "../../../../common/button/btn-primary";
import { FaLocationArrow } from "react-icons/fa";
import { applyDiscount } from "../../../../../services/ReservationService/reservationService";
import { getTotalPrice } from "../../../../../services/ReservationService/reservationService";

const DiscountBox = ({appliedDiscounts, setAppliedDiscounts, setTotalPrice}) => {
    // lấy mã giảm giá 
    const [discounts, setDiscounts] = useState([])
    const [appliedDiscountsSet, setAppliedDiscountsSet] = useState(new Set());
    const reservationId = useSelector(state => state.reservation.reservationId)
    // const [appliedDiscount, setAppliedDiscount] = useState(new Set())

    // handle checkbox change
    const handleDiscountChange = (e, code) => {
        const updatedSet = new Set(appliedDiscountsSet); // clone để tránh mutation trực tiếp
        if (e.target.checked) {
          updatedSet.add(code); 
        } else {
          updatedSet.delete(code);
        }
        setAppliedDiscountsSet(updatedSet); 
        console.log(appliedDiscountsSet)
    };
    
    useEffect ( () => {
        const fetchDiscountAvailable = async() => {
            const data = await getDiscountAvailable()
    
            if (data && data.code && data.code === 200 && data?.result) {
                setDiscounts(data.result)
            } else if (data.response && data.response.data) {
                toast.error(data.response.data.message) // trường hợp giao dịch hết thời gian
            }
            else {
                toast.error(data?.message)
            } 
        }
        fetchDiscountAvailable()} , []
    )

    useEffect(() => {
        if (appliedDiscounts && appliedDiscounts.length > 0) {
            setAppliedDiscountsSet(new Set(appliedDiscounts.map(d => d.code)));
        }
    }, [appliedDiscounts]); 


    // fetch update discounts
    const handleApplyDiscount = async () => {
        const data = await applyDiscount(reservationId, Array.from(appliedDiscountsSet))
        if (data && data.code && data.code === 200) {
            toast.success(data.message)
            setAppliedDiscounts(data.result.discounts) // set lại mã giảm giá đã áp dụng

            // tiến hành cập nhật lại giá tiền 
            const priceRes = await getTotalPrice(reservationId)
            if (priceRes && priceRes.code && priceRes.code === 200 && priceRes.result) 
                setTotalPrice(priceRes.result)

        } else if (data.response && data.response.data) {
            toast.error(data.response.data.message) 
        }
        else {
            toast.error(data?.message)
        }
    }

    return (
        <>
          {/* Voucher Section Title */}
          <h6>Mã Miễn Phí Vận Chuyển</h6>
          <p className="text-muted small">Có thể chọn 1 Voucher</p>

          {discounts?.length > 0 ? (
            discounts.map((discount) => (
                <HorizontalCard
                key={discount.code}
                img="https://www.shutterstock.com/image-illustration/red-price-tag-label-percentage-600nw-1947684382.jpg"
                name={discount.name}
                subName={
                    <>
                    <div>Giảm {discount.discountPrecentage}% áp dụng cho hóa đơn từ {formatCurrency(discount.minBookingAmount)} VND</div>
                    <div>Giảm tối đa {discount.maxDiscountAmount} VND</div>
                    </>
                }
                >
                <Form.Check
                    type="checkbox"
                    onChange={(e) => handleDiscountChange(e,discount.code)}
                    checked={[...appliedDiscountsSet].includes(discount.code)}
                />
                </HorizontalCard>
            ))
            ) : 
        (
            <div className="text-center text-danger mb-2"><MdError />Hiện tại không có mã giảm giá nào phù hợp với bạn !</div>
        )}

          {/* Warning Message */}
          <Alert variant="warning" className="small">
          <h6 className="text-muted">Hệ thống đặt phòng <span className="text-primary"><span className="text-warning">@H</span>otelas</span></h6>
          </Alert>

          <div className="d-flex justify-content-between align-items-center mt-3">
            <p className="text-muted small mb-0">
                (*) Hãy chọn nhanh, đừng chậm chân!
            </p>

            <PrimaryButton
                text="Áp dụng ngay"
                icon={<FaLocationArrow />}
                className="bg-warning"
                onClickFunc={handleApplyDiscount}
            />
        </div>
        </>
    )
}

export default DiscountBox