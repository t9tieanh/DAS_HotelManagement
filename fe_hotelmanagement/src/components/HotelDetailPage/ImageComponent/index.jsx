import { useState } from "react";


const ImageComponent = () => {


    const [mainImage, setMainImage] = useState("https://res.cloudinary.com/simpleview/image/upload/v1686072977/clients/milwaukee/VM_Hilton_Plaza_Suite_King_Room_9b5d673a-95c6-445e-ad6b-5ae85e260f18.jpg");

    const images = [
        "https://res.cloudinary.com/simpleview/image/upload/v1686072977/clients/milwaukee/VM_Hilton_Plaza_Suite_King_Room_9b5d673a-95c6-445e-ad6b-5ae85e260f18.jpg",
        "https://res.cloudinary.com/simpleview/image/upload/v1686072977/clients/milwaukee/VM_Hilton_Plaza_Suite_King_Room_9b5d673a-95c6-445e-ad6b-5ae85e260f18.jpg",
        "https://res.cloudinary.com/simpleview/image/upload/v1686072977/clients/milwaukee/VM_Hilton_Plaza_Suite_King_Room_9b5d673a-95c6-445e-ad6b-5ae85e260f18.jpg",
        "https://res.cloudinary.com/simpleview/image/upload/v1686072977/clients/milwaukee/VM_Hilton_Plaza_Suite_King_Room_9b5d673a-95c6-445e-ad6b-5ae85e260f18.jpg",
    ]

    return (

        <>
        
        <div className="container mt-4 gallery">
            <div className="row">
            <div className="col-md-6">
                <img src={mainImage} className="main-img" alt="Hình lớn" />
            </div>
            <div className="col-md-6">
                <div className="row">
                {images.map((img, index) => (
                    <div key={index} className="col-6 small-img-container">
                    <img
                        src={img}
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