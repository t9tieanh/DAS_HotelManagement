import room1 from "../../../assets/img/room/room-b1.jpg";
import room2 from "../../../assets/img/room/room-b2.jpg";
import room3 from "../../../assets/img/room/room-b3.jpg";
import room4 from "../../../assets/img/room/room-b4.jpg";

const HomeRoom = () => {
    const rooms = [
        { img: room1, title: "Double Room", price: 199 },
        { img: room2, title: "Premium King Room", price: 159 },
        { img: room3, title: "Deluxe Room", price: 198 },
        { img: room4, title: "Family Room", price: 299 },
    ];

    return (
        <section className="hp-room-section">
            <div className="container-fluid">
                <div className="hp-room-items">
                    <div className="row">
                        {rooms.map((room, index) => (
                            <div className="col-lg-3 col-md-6" key={index}>
                                <div
                                    className="hp-room-item"
                                    style={{
                                        backgroundImage: `url(${room.img})`,
                                        backgroundSize: "cover",
                                        backgroundPosition: "center",
                                    }}
                                >
                                    <div className="hr-text">
                                        <h3>{room.title}</h3>
                                        <h2>{room.price}$<span>/Pernight</span></h2>
                                        <table>
                                            <tbody>
                                                <tr>
                                                    <td className="r-o">Size:</td>
                                                    <td>30 ft</td>
                                                </tr>
                                                <tr>
                                                    <td className="r-o">Capacity:</td>
                                                    <td>Max person 5</td>
                                                </tr>
                                                <tr>
                                                    <td className="r-o">Bed:</td>
                                                    <td>King Beds</td>
                                                </tr>
                                                <tr>
                                                    <td className="r-o">Services:</td>
                                                    <td>Wifi, Television, Bathroom,...</td>
                                                </tr>
                                            </tbody>
                                        </table>
                                        <a href="#" className="primary-btn">More Details</a>
                                    </div>
                                </div>
                            </div>
                        ))}
                    </div>
                </div>
            </div>
        </section>
    );
}

export default HomeRoom;
