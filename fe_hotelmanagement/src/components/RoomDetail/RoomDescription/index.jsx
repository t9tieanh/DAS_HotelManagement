import { useState, useEffect } from "react";

const RoomDetail = ({ room }) => {
    // State lưu thông tin phòng
    const [roomData, setRoomData] = useState({
        name: "",
        image: "",
        price: 0,
        size: 0,
        capacity: 0,
        bed: "",
        services: [],
        description: "",
    });

    // Cập nhật state khi nhận props
    useEffect(() => {
        if (room) {
            setRoomData(room);
        }
    }, [room]);

    return (
        <div className="room-details-item">
            <img src={roomData.image} alt={roomData.name} />
            <div className="rd-text">
                <div className="rd-title">
                    <h3>{roomData.name}</h3>
                    <div className="rdt-right">
                        <div className="rating">
                            <i className="icon_star"></i>
                            <i className="icon_star"></i>
                            <i className="icon_star"></i>
                            <i className="icon_star"></i>
                            <i className="icon_star-half_alt"></i>
                        </div>
                        <a href="#">Booking Now</a>
                    </div>
                </div>
                <h2>{roomData.price}$<span>/Per night</span></h2>
                <table>
                    <tbody>
                        <tr>
                            <td className="r-o">Size:</td>
                            <td>{roomData.size} ft</td>
                        </tr>
                        <tr>
                            <td className="r-o">Capacity:</td>
                            <td>Max {roomData.capacity} people</td>
                        </tr>
                        <tr>
                            <td className="r-o">Bed:</td>
                            <td>{roomData.bed}</td>
                        </tr>
                        <tr>
                            <td className="r-o">Services:</td>
                            <td>{roomData.services.join(", ")}</td>
                        </tr>
                    </tbody>
                </table>
                <p className="f-para">{roomData.description}</p>
            </div>
        </div>
    );
};

export default RoomDetail;
