import Card from 'react-bootstrap/Card';
import './style.scss'

const CustomCard = ({ name, children, className, subTitle }) => { 
    return <>
        <Card className={`shadow-0 ${className}`}>
            <Card.Body>
                <Card.Title><h5>{name}</h5></Card.Title>
                {subTitle && <Card.Subtitle><h6 className='text-muted'>{subTitle}</h6></Card.Subtitle>}
                <hr/>
                <Card.Text>
                    {children}
                </Card.Text>
            </Card.Body>
        </Card>
    </>
}

export default CustomCard;