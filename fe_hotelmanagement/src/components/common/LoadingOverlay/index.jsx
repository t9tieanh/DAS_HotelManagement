import './style.scss';

const LoadingOverLay = ({ isLoading, text, icon }) => { 
    return ( <>
        { isLoading && (
            <div id="loadingOverlay" className="loading-overlay">
                <div className="loading-box">
                    <div className="spinner-border text-primary" role="status">
                        <span className="visually-hidden">Đang xác thực...</span>
                    </div>
                    <p className="mt-3">
                    {text}</p>
                    <div>{icon}</div>
                </div>
            </div>
        )} </>
    )
}

export default LoadingOverLay