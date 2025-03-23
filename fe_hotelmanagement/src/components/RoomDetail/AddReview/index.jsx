import { useState } from "react";

const AddReview = ({ onAddReview }) => {


    const [review, setReview] = useState({
        name: "",
        email: "",
        rating: 5,
        comment: ""
    });


    const handleChange = (e) => {
        setReview({
            ...review,
            [e.target.name]: e.target.value
        });
    };


    const handleRating = (value) => {
        setReview({ ...review, rating: value });
    };

    const handleSubmit = (e) => {
        e.preventDefault();
        if (!review.name || !review.email || !review.comment) {
            alert("Vui lòng điền đầy đủ thông tin!");
            return;
        }
        onAddReview(review);
        setReview({ name: "", email: "", rating: 5, comment: "" }); 
    };

    return (
        <div className="review-add">
            <h4>Add Review</h4>
            <form className="ra-form" onSubmit={handleSubmit}>
                <div className="row">
                    <div className="col-lg-6">
                        <input
                            type="text"
                            name="name"
                            placeholder="Name*"
                            value={review.name}
                            onChange={handleChange}
                        />
                    </div>
                    <div className="col-lg-6">
                        <input
                            type="text"
                            name="email"
                            placeholder="Email*"
                            value={review.email}
                            onChange={handleChange}
                        />
                    </div>
                    <div className="col-lg-12">
                        <div>
                            <h5>Your Rating:</h5>
                            <div className="rating">
                                {[1, 2, 3, 4, 5].map((star) => (
                                    <i
                                        key={star}
                                        className={star <= review.rating ? "icon_star" : "icon_star_alt"}
                                        onClick={() => handleRating(star)}
                                        style={{ cursor: "pointer" }}
                                    ></i>
                                ))}
                            </div>
                        </div>
                        <textarea
                            name="comment"
                            placeholder="Your Review"
                            value={review.comment}
                            onChange={handleChange}
                        ></textarea>
                        <button type="submit">Submit Now</button>
                    </div>
                </div>
            </form>
        </div>
    );
};

export default AddReview;
