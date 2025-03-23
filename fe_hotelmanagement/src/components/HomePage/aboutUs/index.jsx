import { useState, useEffect } from "react";
import about1 from "../../../assets/img/about/about-1.jpg";
import about2 from "../../../assets/img/about/about-2.jpg";

export default () => {
    return <>
        <section className="aboutus-section spad">
            <div className="container">
                <div className="row">
                    <div className="col-lg-6">
                        <div className="about-text">
                            <div className="section-title">
                                <span>About Us</span>
                                <h2>Intercontinental LA <br />Westlake Hotel</h2>
                            </div>
                            <p className="f-para">Sona.com is a leading online accommodation site. We’re passionate about
                                travel. Every day, we inspire and reach millions of travelers across 90 local websites in 41
                                languages.</p>
                            <p className="s-para">So when it comes to booking the perfect hotel, vacation rental, resort,
                                apartment, guest house, or tree house, we’ve got you covered.</p>
                            <a href="#" className="primary-btn about-btn">Read More</a>
                        </div>
                    </div>
                    <div className="col-lg-6">
                        <div className="about-pic">
                            <div className="row">
                                <div className="col-sm-6">
                                    <img src={about1} alt="" />
                                </div>
                                <div className="col-sm-6">
                                    <img src={about2} alt="" />
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </section>
    </>
}