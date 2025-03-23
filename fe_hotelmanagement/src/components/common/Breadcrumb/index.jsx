import { Link } from "react-router-dom";

const Breadcrumb = ({ title, paths }) => {
    return (
        <div className="breadcrumb-section">
            <div className="container">
                <div className="row">
                    <div className="col-lg-12">
                        <div className="breadcrumb-text">
                            <h2>{title}</h2>
                            <div className="bt-option">
                                {paths.map((path, index) => (
                                    <span key={index}>
                                        {path.url ? (
                                            <Link to={path.url}>{path.label}</Link>
                                        ) : (
                                            <span>{path.label}</span>
                                        )}
                                        {index < paths.length - 1 && " / "}
                                    </span>
                                ))}
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    );
};

export default Breadcrumb;
