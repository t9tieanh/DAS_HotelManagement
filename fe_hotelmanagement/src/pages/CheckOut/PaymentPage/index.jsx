import PrimaryButton from "../../../components/common/button/btn-primary"

const PaymentPage = ({handleNextStep}) => {
    return (<>
        <PrimaryButton text={'Hoàn thành thanh toán'} onClickFunc={handleNextStep} />
    </>)
}

export default PaymentPage