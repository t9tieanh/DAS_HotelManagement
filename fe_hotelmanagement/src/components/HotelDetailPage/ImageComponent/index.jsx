import { useEffect, useState } from "react";
import { BASE_URL } from "../../../conf/baseUrl";
import noPhoto from "../../../assets/img/hotel/no-hotel.jpg"

const ImageComponent = ({imgs, avatar}) => {

    const fileUrl = 'files/image'

    const [mainImage, setMainImage] = useState("https://res.cloudinary.com/simpleview/image/upload/v1686072977/clients/milwaukee/VM_Hilton_Plaza_Suite_King_Room_9b5d673a-95c6-445e-ad6b-5ae85e260f18.jpg");

    const images = [
        "https://res.cloudinary.com/simpleview/image/upload/v1686072977/clients/milwaukee/VM_Hilton_Plaza_Suite_King_Room_9b5d673a-95c6-445e-ad6b-5ae85e260f18.jpg",
        "https://res.cloudinary.com/simpleview/image/upload/v1686072977/clients/milwaukee/VM_Hilton_Plaza_Suite_King_Room_9b5d673a-95c6-445e-ad6b-5ae85e260f18.jpg",
        "https://res.cloudinary.com/simpleview/image/upload/v1686072977/clients/milwaukee/VM_Hilton_Plaza_Suite_King_Room_9b5d673a-95c6-445e-ad6b-5ae85e260f18.jpg",
        "https://res.cloudinary.com/simpleview/image/upload/v1686072977/clients/milwaukee/VM_Hilton_Plaza_Suite_King_Room_9b5d673a-95c6-445e-ad6b-5ae85e260f18.jpg",
    ]

    console.log(avatar,"avatar")

    return (

        <>
        
        <div className="container mt-4 gallery">
            <div className="row">
            <div className="col-md-6">
                {avatar && <img src={`${BASE_URL}/${fileUrl}/${avatar}`} className="main-img" alt="Hình lớn" />}
                {!avatar && <img src={noPhoto} className="main-img" alt="Hình lớn" />}
            </div>
            <div className="col-md-6">
                <div className="row">
                {imgs?.map((img, index) => (
                    <div key={index} className="col-6 small-img-container">
                    <img
                        src={`${BASE_URL}/${fileUrl}/${img}`}
                        className="small-img"
                        alt={`Hình nhỏ ${index + 1}`}
                        onClick={() => setMainImage(img)}
                    />
                    </div>
                ))}
                <div className="col-6 small-img-container">
                    <div className="view-all">Xem tất cả hình ảnh</div>
                </div>
                </div>
            </div>
            </div>
        </div>
        
        
        </>

    )
}

export default ImageComponent