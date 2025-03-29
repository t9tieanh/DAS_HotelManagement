import ArrowButton from "../../common/button/button-arrow";
import CustomCard from "../../common/Card";
import Card from "../../common/Card";
import DescriptionComponent from "./DescriptionComponent";
import HotelFacility from "./FacilityComponent";
import HotelLocation from "./LocationComponent";


const HotelDescription = ({description, address, facilities}) => {

    const addressStr = `${address?.concrete}, ${address?.commune}, ${address?.district}, ${address?.city}`;

    return (
        <>
        
        <div className="container info-section shadow-3" >
            <div className="row p-2 g-3">
                <HotelFacility facilities = {facilities} />
                <HotelLocation location = {addressStr} />
            </div>

            <DescriptionComponent text={description} />

        </div>
        
        
        </>
    )

}

export default HotelDescription