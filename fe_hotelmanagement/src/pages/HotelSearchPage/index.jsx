import React, { useState } from "react";
import "./style.scss";
import SearchBar from "../../components/HotelSearchPage/SearchBarComponent";
import FilterBar from "../../components/HotelSearchPage/FilterBarComponent";
import HotelComponent from "../../components/HotelSearchPage/HotelComponent";
import HeaderComponent from "../../components/HotelSearchPage/HeaderComponent"

const HotelSearchPage = () => {


  const [hotelCount, setHotelCount] = useState(0)

  return (
    <div className="booking-page">
      <SearchBar />
      <HeaderComponent count = {hotelCount} />

      <div className="main-content">
        <FilterBar />
        <HotelComponent setHotelCount = {setHotelCount} />
      </div>
    </div>
  );
};

export default HotelSearchPage;