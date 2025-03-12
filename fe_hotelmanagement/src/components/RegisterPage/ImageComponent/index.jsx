import React from 'react';
import './style.scss';

const ImageComponent = () => {
    return (
        <>
            <img
                src="https://mdbcdn.b-cdn.net/img/Photos/new-templates/bootstrap-registration/img3.webp"
                className="w-100"
                style={{ borderTopLeftRadius: ".3rem", borderTopRightRadius: ".3rem" }}
                alt="Sample photo"
            />
        </>
    );
}

export default ImageComponent;