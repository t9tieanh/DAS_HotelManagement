import './style.scss'
import Button from 'react-bootstrap/Button';

const PrimaryButton = ({className, onClickFunc, type, text, icon}) => {
    return (
        <>
            <Button className={className} type= {type} variant="primary" onClick = {onClickFunc}> {icon} {text}</Button>       
        </>
    )
}

export default PrimaryButton