import RoomCard from "../../components/common/RoomCard";
import room1 from "../../assets/img/room/room-1.jpg";
import room2 from "../../assets/img/room/room-2.jpg";
import room3 from "../../assets/img/room/room-3.jpg";
import Breadcrumb from "../../components/common/Breadcrumb";


const RoomList = () => {

    const rooms = [
        {
            name: "Premium King Room",
            image: room1,
            price: 159,
            size: 30,
            capacity: 3,
            bed: "King Beds",
            services: ["Wifi", "Television", "Bathroom"]
        },
        {
            name: "Deluxe Queen Room",
            image: room2,
            price: 129,
            size: 25,
            capacity: 2,
            bed: "Queen Bed",
            services: ["Wifi", "Air Conditioner", "Mini Bar"]
        },
        {
            name: "Family Suite",
            image: room3,
            price: 199,
            size: 40,
            capacity: 4,
            bed: "2 King Beds",
            services: ["Wifi", "Television", "Jacuzzi", "Kitchen"]
        }
    ];



    return (
        <>
        
        <Breadcrumb 
            title="Rooms" 
            paths={[
                { label: "Home", url: "/" },
                { label: "Rooms" }
            ]}
        />


        <section className="rooms-section spad">
            <div className="container">
                <div className="row">
                    {rooms.map((room, index) => (
                        <RoomCard key={index} room={room} />
                    ))}
                </div>
            </div> 
        </section>
        
        </>
    )
}

export default RoomList;