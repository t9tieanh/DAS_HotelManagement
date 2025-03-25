import { MapContainer } from "react-leaflet"
import CustomCard from "../../../common/Card"
import './style.scss'
import MapIframe from "../../../common/MapComponent"

const HotelLocationBody = () => {
    return <>
        <ul>
            <div>
                <i className="fa-solid fa-location-dot"></i>
                <span>Khoảng cách đến trung tâm Đà Nẵng - 6 km</span>
            </div>
            <div>
                <i className="fa-solid fa-location-dot"></i>
                <span>Khoảng cách đến trung tâm Đà Nẵng - 6 km</span>
            </div>
            <div>
                <i className="fa-solid fa-location-dot"></i>
                <span>Khoảng cách đến trung tâm Đà Nẵng - 6 km</span>
            </div>
        </ul>
    </>
}

const HotelFacility = () => {
    return (
        <>
            <CustomCard name={'Khoảng cách từ khách sạn'} children={<HotelLocationBody />} className={'col-4'} />
        </>
    )
}

export default HotelFacility