import { useState } from "react";
import avatar1 from "../../../assets/img/room/avatar/avatar-1.jpg";
import avatar2 from "../../../assets/img/room/avatar/avatar-2.jpg";


const Reviews = () => {
    const [reviews, setReviews] = useState([
        {
            id: 1,
            name: "Brandon Kelley",
            date: "27 Aug 2019",
            avatar: avatar1,
            rating: 4.5,
            comment: "Neque porro qui squam est, qui dolorem ipsum quia dolor sit amet, consectetur, adipisci velit, sed quia non numquam eius modi tempora. incidunt ut labore et dolore magnam."
        },
        {
            id: 2,
            name: "Alice Johnson",
            date: "15 Sep 2020",
            avatar: avatar2,
            rating: 4.5,
            comment: "Neque porro qui squam est, qui dolorem ipsum quia dolor sit amet, consectetur, adipisci velit, sed quia non numquam eius modi tempora. incidunt ut labore et dolore magnam."
        }
    ]);

    
    const renderStars = (rating) => {
        const fullStars = Math.floor(rating);
        const halfStar = rating % 1 !== 0;
        return (
            <>
                {[...Array(fullStars)].map((_, index) => (
                    <i key={index} className="icon_star"></i>
                ))}
                {halfStar && <i className="icon_star-half_alt"></i>}
            </>
        );
    };

    return (
        <div className="rd-reviews">
            <h4>Reviews</h4>
            {reviews.map((review) => (
                <div className="review-item" key={review.id}>
                    <div className="ri-pic">
                        <img src={review.avatar} alt={review.name} />
                    </div>
                    <div className="ri-text">
                        <span>{review.date}</span>
                        <div className="rating">{renderStars(review.rating)}</div>
                        <h5>{review.name}</h5>
                        <p>{review.comment}</p>
                    </div>
                </div>
            ))}
        </div>
    );
};

export default Reviews;
