
import blog1 from "../../../assets/img/blog/blog-1.jpg";
import blog2 from "../../../assets/img/blog/blog-2.jpg";
import blog3 from "../../../assets/img/blog/blog-3.jpg";
import blog10 from "../../../assets/img/blog/blog-10.jpg";
import blogWide from "../../../assets/img/blog/blog-wide.jpg";

const blogPosts = [
    { id: 1, category: "Travel Trip", title: "Tremblant In Canada", date: "15th April, 2019", image: blog1, size: "col-lg-4" },
    { id: 2, category: "Camping", title: "Choosing A Static Caravan", date: "15th April, 2019", image: blog2, size: "col-lg-4" },
    { id: 3, category: "Event", title: "Copper Canyon", date: "21th April, 2019", image: blog3, size: "col-lg-4" },
    { id: 4, category: "Event", title: "Trip To Iqaluit In Nunavut A Canadian Arctic City", date: "08th April, 2019", image: blogWide, size: "col-lg-8 small-size" },
    { id: 5, category: "Travel", title: "Traveling To Barcelona", date: "12th April, 2019", image: blog10, size: "col-lg-4 small-size" },
];

const BlogSection = () => {
    return (
        <section className="blog-section spad">
            <div className="container">
                {/* Tiêu đề */}
                <div className="row">
                    <div className="col-lg-12">
                        <div className="section-title">
                            <span>Hotel News</span>
                            <h2>Our Blog & Event</h2>
                        </div>
                    </div>
                </div>

                {/* Danh sách bài viết */}
                <div className="row">
                    {blogPosts.map((post) => (
                        <div key={post.id} className={post.size}>
                            <div
                                className="blog-item set-bg"
                                style={{ backgroundImage: `url(${post.image})` }}
                            >
                                <div className="bi-text">
                                    <span className="b-tag">{post.category}</span>
                                    <h4><a href="#">{post.title}</a></h4>
                                    <div className="b-time"><i className="icon_clock_alt"></i> {post.date}</div>
                                </div>
                            </div>
                        </div>
                    ))}
                </div>
            </div>
        </section>
    );
};

export default BlogSection;
