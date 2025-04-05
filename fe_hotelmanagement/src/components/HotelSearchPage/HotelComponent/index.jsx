import React, { useEffect, useState } from "react";
import './style.scss';
import { useLocation, useNavigate } from "react-router-dom";
import { findRoomInHotel } from "../../../services/HotelService/findHotelService";
import Paginate from "../../common/paging";
import {LIMIT} from "../../../utils/paging"
import HotelCard from "../HotelCard/index.jsx"

const HotelComponent = ({loading, error, hotels, fetchHotels, pageCount, currentPage}) => {

    if (loading) {
        return <p>Đang tải danh sách khách sạn...</p>;
    }

    if (error) {
        return <p>{error}</p>;
    }

    return (
        <>
            <section className="hotel-list">
                {hotels && hotels.length > 0 ? (
                    hotels.map((hotel) => (
                        <HotelCard hotel={hotel} />
                    ))
                ) : (
                    <p>Không tìm thấy khách sạn phù hợp.</p>
                )}

                <div className="d-flex align-items-center justify-content-center"><Paginate fetchFunction = {fetchHotels}  itemsPerPage = {LIMIT} pageCount = {pageCount} currentPage = {currentPage} /></div>
                
            </section>
        </>
    );
};

export default HotelComponent;
