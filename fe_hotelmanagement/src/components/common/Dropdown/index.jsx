import Dropdown from 'react-bootstrap/Dropdown';
import DropdownButton from 'react-bootstrap/DropdownButton';

const Dropdown = ({logo, selectedItem,setSelectedItem , items, className}) => {

    const [selectedItem, setSelectedItem] = useState("");

    return <>
        <DropdownButton id="dropdown-item-button" className={className} title={<>{logo} {selectedItem}</>}>
            {items.map((item, index) => (
                <Dropdown.Item as="button" key={index} onClick={() => setSelectedItem(item)}>
                    {item}
                </Dropdown.Item>
            ))}
        </DropdownButton>
    </>
}