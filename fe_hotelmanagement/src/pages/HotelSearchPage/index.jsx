import React from "react";
import "./style.scss";
import SearchBar from "../../components/HotelSearchPage/SearchBarComponent";
import FilterBar from "../../components/HotelSearchPage/FilterBarComponent";
import HotelComponent from "../../components/HotelSearchPage/HotelComponent";
import HeaderComponent from "../../components/HotelSearchPage/HeaderComponent"

const HotelSearchPage = () => {
  return (
    <div className="booking-page">
      <SearchBar />
      <HeaderComponent />

      <div className="main-content">
        <FilterBar />
        <HotelComponent />
      </div>
    </div>
  );
};

export default HotelSearchPage;