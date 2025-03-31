import React from "react";
import './style.scss'
import ArrowButton from '../../common/button/button-arrow/index.jsx'
import Tag from "../../common/tag/index.jsx";
import { BASE_URL } from "../../../conf/baseUrl.js";


const RoomSection = ({ rooms }) => {
    const fileUrl = 'files/image'

    return (
        <div className="room-section-container">
            {rooms.length > 0 ? (
                rooms.map((room) => (
                    <div key={room.id} className="room-section m-2 shadow-5">
                        <div className="row g-0">
                            <div className="col-md-4">
                                <div className="room-image">
                                    <img
                                        src={`${BASE_URL}/${fileUrl}/${room.avatar}`}
                                        alt={room.name}
                                    />
                                    <ArrowButton text={'Xem chi tiết phòng'} style={{ fontSize: "15px", marginTop: "5px" }} />
                                </div>
                            </div>
                            <div className="col-md-8">
                                <div className="room-details">
                                    <h5>{room.name}</h5>
                                    <table>
                                        <tbody>
                                            <tr>
                                                <td>
                                                    <strong>{room.description}</strong><br />
                                                    <span className="text-muted">
                                                        {room.roomStatus === 1 ? "Có sẵn" : "Hết phòng"}
                                                    </span><br />
                                                    <i className="fas fa-bed"></i> {room.maxOccupation} người<br />
                                                    <span className="text-success">
                                                        {room.freeChildren > 0 ? `Miễn phí ${room.freeChildren} trẻ em` : "Không miễn phí trẻ em"}
                                                    </span>
                                                </td>
                                                <td className="text-center">
                                                    <i className="fas fa-user"></i> x{room.maxOccupation}
                                                </td>
                                                <td className="text-end">
                                                    <span className="price-new">{room.price.toLocaleString()} VND</span><br />
                                                    <small className="text-muted">Chưa bao gồm thuế và phí</small><br />
                                                    {room.roomStatus === 1 && <Tag text={'Đang có khuyến mãi'} />}
                                                </td>
                                                <td className="text-end">
                                                    {room.roomStatus === 1 ? (
                                                        <>
                                                            <button className="btn btn-warning select-btn">Chọn</button><br />
                                                            <small className="text-muted">Còn phòng</small>
                                                        </>
                                                    ) : (
                                                        <small className="text-danger">Hết phòng</small>
                                                    )}
                                                </td>
                                            </tr>
                                        </tbody>
                                    </table>
                                </div>
                            </div>
                        </div>
                    </div>
                ))
            ) : (
                <p>Không có phòng nào.</p>
            )}
        </div>
    );
};

export default RoomSection;
