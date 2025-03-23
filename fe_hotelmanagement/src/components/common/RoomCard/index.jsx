import { useState } from "react";
import { useNavigate } from "react-router-dom";

const RoomCard = ({ room }) => {
    const [roomData, setRoomData] = useState(room);

    const navigator = useNavigate();

    return (
        <div className="col-lg-4 col-md-6">
            <div className="room-item">
                <img src={roomData.image || "img/room/default-room.jpg"} alt={roomData.name} />
                <div className="ri-text">
                    <h4>{roomData.name}</h4>
                    <h3>{roomData.price}$<span>/Per night</span></h3>
                    <table>
                        <tbody>
                            <tr>
                                <td className="r-o">Size:</td>
                                <td>{roomData.size} ft</td>
                            </tr>
                            <tr>
                                <td className="r-o">Capacity:</td>
                                <td>Max {roomData.capacity} persons</td>
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
                    <a href="#" className="primary-btn" onClick={() => {navigator('/room/12')}}>More Details</a>
                </div>
            </div>
        </div>
    );
};

export default RoomCard;
