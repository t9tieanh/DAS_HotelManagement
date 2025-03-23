import React from "react";
import './style.scss';

const SearchBarComponent = () => {

    

    return (
        <>
            <div className="search-bar">
                <div className="search-bar-item">
                    <i className="fas fa-map-marker-alt"></i>
                    <span>Đà Nẵng</span>
                </div>
                <div className="search-bar-item">
                    <i className="fas fa-calendar-alt"></i>
                    <span>13 thg 3 - 14 thg 3, 1 đêm</span>
                </div>
                <div className="search-bar-item">
                    <i className="fas fa-user"></i>
                    <span>1 người lớn, 0 Trẻ em, 1 phòng</span>
                </div>
                <button className="search-bar-button">
                    <i className="fas fa-search"></i>
                    Tìm khách sạn
                </button>
            </div>
        </>
    );
};

export default SearchBarComponent;