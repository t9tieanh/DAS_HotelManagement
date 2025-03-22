import './style.scss'

const ArrowButton = ({text, onClickFunc, style}) => {
    return (
        <>
            <div className="btn-arrow shadow-3" style={style} onClick={onClickFunc}>
                {text} &nbsp;
                <i className="fa-solid fa-arrow-right"></i>
            </div>
        </>
    )
}

export default ArrowButton