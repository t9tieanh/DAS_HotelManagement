import Overlay from 'react-bootstrap/Overlay';
import Popover from 'react-bootstrap/Popover';
import './style.scss';

const CustomOverlay = ({header, children, show, target, ref}) => {
    return (   
        <Overlay
            show={show}
            target={target}
            placement="bottom"
            container={ref}
            className="custom-overlay-container"
            containerPadding={0}
        >
            <Popover id="popover-contained">
            <Popover.Header as="h5"><h6 className="fw-bold">{header}</h6></Popover.Header>
            <Popover.Body>
                {children}
            </Popover.Body>
            </Popover>
        </Overlay>
    )
}

export default CustomOverlay