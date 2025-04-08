import React, { useEffect, useState } from "react";
import "./style.scss";
import {Form, Alert} from 'react-bootstrap';
import './style.scss'; 
import HorizontalCard from "../../../../common/HorizontalCard";
import { getDiscountAvailable } from "../../../../../services/DiscountService/discountService";
import { toast } from "react-toastify";
import { formatCurrency } from "../../../../../utils/Format/CurrencyFormat";

const DiscountBox = () => {

    const [discounts, setDiscounts] = useState([])
    const [appliedDiscount, setAppliedDiscount] = useState()
    
    useEffect ( () => {
        const fetchDiscountAvailable = async() => {
            const data = await getDiscountAvailable()
    
            if (data && data.code && data.code === 200 && data?.result) {
                setDiscounts(data.result)
                console.log(data)
            } else if (data.response && data.response.data) {
                toast.error(data.response.data.message) // trường hợp giao dịch hết thời gian
            }
            else {
                toast.error(data?.message)
            } 
        }
        fetchDiscountAvailable()} , []
    )

    return (
        <>
          {/* Voucher Section Title */}
          <h6>Mã Miễn Phí Vận Chuyển</h6>
          <p className="text-muted small">Có thể chọn 1 Voucher</p>

          {discounts?.map(
            (discount) => {
                return (
                    <HorizontalCard 
                        img={"https://www.shutterstock.com/image-illustration/red-price-tag-label-percentage-600nw-1947684382.jpg"}
                        name={discount.name} 
                        subName={<><div>Giảm {discount.discountPrecentage} %, áp dụng cho hóa đơn từ {formatCurrency(discount.minBookingAmount)} VND</div>
                                    <div>Giảm tối đa {discount.maxDiscountAmount} VND</div>
                                </>} 
                        children={
                            <Form.Check
                            type="radio"
                            name="voucher"
                            label=""
                            onChange={(e) => {if (e.target.checked === true) setAppliedDiscount(discount.code)}}
                            checked={appliedDiscount === discount.code}
                        />
                        }
                    />
                )
            }
          )}

          {/* Warning Message */}
          <Alert variant="warning" className="small">
          <h6 className="text-muted">Hệ thống đặt phòng <span className="text-primary"><span className="text-warning">@H</span>otelas</span></h6>
          </Alert>

          {/* Note */}
          <p className="text-muted small">
            (*) Hãy chọn nhanh, đừng chậm chân!
          </p>
        </>
    )
}

export default DiscountBox