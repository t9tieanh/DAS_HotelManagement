import PrimaryButton from "../../../components/common/button/btn-primary"
import CustomCard from "../../../components/common/Card"
import { MdPayment } from "react-icons/md";

const PaymentPage = ({handleNextStep}) => {
    return (<>

        <CustomCard name={'Chọn phương thức thanh toán'} icon={<MdPayment />} />

        <PrimaryButton text={'Hoàn thành thanh toán'} onClickFunc={handleNextStep} />
    </>)
}

export default PaymentPage