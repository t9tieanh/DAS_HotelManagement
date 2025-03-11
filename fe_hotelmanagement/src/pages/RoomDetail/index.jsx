import Breadcrumb from '../../components/common/Breadcrumb';
import RoomBooking from '../../components/RoomDetail/RoomBooking';
import room1 from "../../assets/img/room/room-1.jpg";
import RoomDescription from '../../components/RoomDetail/RoomDescription';


import './style.scss';
import Reviews from '../../components/RoomDetail/Reviews';
import AddReview from '../../components/RoomDetail/AddReview';

const RoomDetail = () => {


    const room = {
        name: "Premium King Room",
        image: room1,
        price: 159,
        size: 30,
        capacity: 3,
        bed: "King Beds",
        services: ["Wifi", "Television", "Bathroom"],
        description: "This is a luxurious room with all modern amenities."
    };

    return (
        <>

            <Breadcrumb 
                title="Rooms" 
                paths={[
                    { label: "Home", url: "/" },
                    { label: "Rooms", url: "/rooms" },
                    { label: "OurRoom" }
                ]}
            />

            <section className="room-details-section spad">
                <div className="container">
                    <div className="row">

                        <div className="col-lg-8 test">

                            <RoomDescription room={room} />
                            <Reviews />
                            <AddReview onAddReview={() => {}} />
                            
                        </div>

                        <div className="col-lg-4 test">

                            <RoomBooking/>

                        </div>


                    </div> 
                </div> 
            </section>
        </>
    );

}

export default RoomDetail;