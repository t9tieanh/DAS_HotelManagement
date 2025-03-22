import React from "react";
import './style.scss'
import ArrowButton from '../../common/button/button-arrow/index.jsx'
import Tag from "../../common/tag/index.jsx";

const RoomSection = () => {
    return (
        <div className="room-section m-2 shadow-5">
            <div className="row g-0">
                <div className="col-md-4">
                    <div className="room-image">
                        <img 
                            src="https://dyf.vn/wp-content/uploads/2021/10/goc-kham-pha-3-khach-san-co-tuoi-doi-gia-nhat-sai-gon_6172680405e4f.jpeg" 
                            alt="Superior City View Room" 
                        />
                        <ArrowButton text = {'Xem chi tiết phòng'} style={{ fontSize: "15px" ,marginTop: "5px" }}  />
                    </div>
                </div>
                <div className="col-md-8">
                    <div className="room-details">
                        <h5>Superior City View</h5>
                        <table>
                            <tbody>
                                <tr>
                                    <td>
                                        <strong>Superior Room City View – Special Rate</strong><br />
                                        <span className="text-muted">Không gồm bữa sáng</span><br />
                                        <i className="fas fa-bed"></i> 1 giường cỡ queen<br />
                                        <span className="text-success">Áp dụng chính sách hủy phòng</span>
                                    </td>
                                    <td className="text-center">
                                        <i className="fas fa-user"></i> x2
                                    </td>
                                    <td className="text-end">
                                        <span className="price-old">492,133 VND</span><br />
                                        <span className="price-new">369,100 VND</span><br />
                                        <small className="text-muted">Chưa bao gồm thuế và phí</small><br />
                                        <Tag text = {'Đang có khuyến mãi'} />
                                    </td>
                                    <td className="text-end">
                                        <button className="btn btn-warning select-btn">Chọn</button><br />
                                        <small className="text-muted">Chỉ còn 1 phòng</small>
                                    </td>
                                </tr>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </div>
    );
};

export default RoomSection;
