import '@wojtekmaj/react-daterange-picker/dist/DateRangePicker.css';
import 'react-calendar/dist/Calendar.css';
import DateRangePicker from '@wojtekmaj/react-daterange-picker';
import './style.scss';

const DateRangePickerComponent = ({date, setDate}) => {

    return (
        <>
            <div className="calendar-custom card p-2">
                <DateRangePicker
                onChange={(value) => setDate(value)}
                value={date}
                minDate={new Date('2020-01-01')}
                maxDate={new Date('2030-12-31')}
                clearIcon={null}
                format="dd thg MM yyyy"
                locale="vi-VN"
                />
            </div>
        </>
    )
}

export default DateRangePickerComponent