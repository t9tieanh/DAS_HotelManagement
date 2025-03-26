import { useEffect, useState } from "react";
import { BASE_URL } from "../../../conf/baseUrl";
import noPhoto from "../../../assets/img/hotel/no-hotel.jpg"
import './style.scss'
import { MdManageSearch } from "react-icons/md";
import Badge from 'react-bootstrap/Badge';
import { FaCamera } from "react-icons/fa6";
import ImageGallery from "./ImageGalleryComponent";
import { getImageCategory } from "../../../services/HotelService/hotelService";

const ImageComponent = ({imgs, avatar, hotelId}) => {

    const fileUrl = 'files/image'

    const [show, setShow] = useState(false)
    const [imgCategorys, setImgCategorys] = useState() 
    const [hasFetched, setHasFetched] = useState(false);

    const handleOpenImageGallery = () => {
        setShow(true)
    }

    const fetchImageCategory = async() => {
        const data = await getImageCategory(hotelId)

        if (data && data.code && data.code === 200 && data.result) {
            setImgCategorys(data.result)
        }
    }

    useEffect(
        () => {
            if (show && !hasFetched && hotelId) {
                fetchImageCategory();
            }
        }
        , [show]
    )

    return (

        <>

        <ImageGallery show={show} setShow={setShow} imgCategorys = {imgCategorys} hotelId = {hotelId} />
        
        <div className="container mt-4 gallery">
            <div className="row">
            <div className="col-md-6">
                {avatar && <img src={`${BASE_URL}/${fileUrl}/${avatar}`} className="main-img" alt="Hình lớn" />}
                {!avatar && <img src={noPhoto} className="main-img" alt="Hình lớn" />}
                <Badge onClick={handleOpenImageGallery} className="image-tag d-flex align-items-center gap-1" bg="light" text="primary"><FaCamera /> Xem tất cả hình ảnh</Badge>
            </div>
            <div className="col-md-6">
                <div className="row">
                {imgs?.map((img, index) => (
                    <div key={index} className="col-6 small-img-container img" onClick={handleOpenImageGallery}>
                    <img
                        src={`${BASE_URL}/${fileUrl}/${img}`}
                        className="small-img"
                        alt={`Hình nhỏ ${index + 1}`}
                    />
                    <div className="view-all">
                    <MdManageSearch size={20} />Xem tất cả hình ảnh</div>
                    </div>
                ))}
                </div>
            </div>
            </div>
        </div>
        
        
        </>

    )
}

export default ImageComponent