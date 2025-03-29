import { MapContainer } from "react-leaflet"
import CustomCard from "../../../common/Card"
import './style.scss'
import MapIframe from "../../../common/MapComponent"

const HotelLocationBody = ({location}) => {
    return <>
        <MapIframe locationStr={location}  />
    </>
}

const HotelLocation = ({location}) => {
    return (
        <CustomCard subTitle={location} name={'Vị trí của khách sạn'} children={<HotelLocationBody location={location} />} className={'col-4 location-component'} />
    )
}

export default HotelLocation