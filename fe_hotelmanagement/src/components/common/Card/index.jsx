import Card from 'react-bootstrap/Card';
import './style.scss'
import ArrowButton from '../button/button-arrow';

const CustomCard = ({ name, icon, children, className, subTitle, buttonFunc }) => { 
    return <>
        <Card className={`shadow-0 ${className}`}>
            <Card.Body>
                {name && (<>
                
                <Card.Title>
                    <h5 style={{ display: "flex", alignItems: "center", gap: "8px" }}>
                        {icon}
                        {name}
                    </h5>
                    </Card.Title>
                    {subTitle && <Card.Subtitle><h6 className='text-muted'>{subTitle}</h6></Card.Subtitle>}
                    {buttonFunc && <ArrowButton text={"Xem thêm"} onClickFunc={buttonFunc} style={{ fontSize: "12px"}} />}
                <hr/>
                
                </>)}
                <Card.Text>
                    {children}
                </Card.Text>
            </Card.Body>
        </Card>
    </>
}


export default CustomCard;