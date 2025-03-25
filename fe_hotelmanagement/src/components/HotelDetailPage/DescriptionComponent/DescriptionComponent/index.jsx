import CustomCard from "../../../common/Card"
import ArrowButton from "../../../common/button/button-arrow"
import { useState } from "react";
import CustomModal from "../../../common/Modal";

const DescriptionBody = ({ text, maxLength = 400 }) => {
    const [isExpanded, setIsExpanded] = useState(false);

    const toggleExpand = () => setIsExpanded(!isExpanded);

    const [show, setShow] = useState(false)

    const handleShowDescription = () => {
        setShow(true)
    }

    return (
        <div className="g-3">
            {isExpanded ? text : `${text?.slice(0, maxLength)}${text?.length > maxLength ? "..." : ""}`}
            {text?.length > maxLength && (
                <ArrowButton onClickFunc={handleShowDescription} text={isExpanded ? "Thu gọn" : "Xem thêm"} />
            )}
            <CustomModal show={show} setShow={setShow} title={'Mô tả về khách sạn này'} content={text}  />
        </div>
    );
};

const DescriptionComponent = ({text}) => {
    return <>
        <CustomCard className={'p-2'} children={<DescriptionBody text={text} />} />
    </>
}

export default DescriptionComponent