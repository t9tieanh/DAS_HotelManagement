import './style.scss'

const HorizontalCard = ({img, name, subName, children}) => {
    return (
        <>

            <div className="card mb-3">
            <div className="card-body">
                <div className="d-flex justify-content-between">
                <div className="d-flex flex-row align-items-center">
                    <div>
                    <img
                        src={img}
                        className="img-fluid rounded-3" alt="Shopping item" style={{width : '65px'}} />
                    </div>
                    <div className="ms-3">
                    <h5>{name}</h5>
                    <p className="small mb-0">{subName}</p>
                    </div>
                </div>
                <div className="d-flex flex-row align-items-center">
                    {children}
                </div>
                </div>
            </div>
            </div>
        </>
    )
}

export default HorizontalCard