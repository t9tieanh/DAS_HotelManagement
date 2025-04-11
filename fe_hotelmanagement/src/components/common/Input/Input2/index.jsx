import './style.scss'
import React from "react";

const TextInput = React.forwardRef(
    ({ value, setValue, placeholder, name, onChangeFunc, overlayElement, className, inputType, idInput, height}, ref) => {

    const onChange = (event) => {
        if (onChangeFunc) {
            onChangeFunc(event);
            return;
        }

        else setValue(event.target.value);
    }

      return (
        <div className={`text-input-2 ${className}`} ref={ref}>
          <label htmlFor={idInput}>{name}</label>
          <input
            className="date-input"
            type={inputType ? inputType : 'text'}
            id={idInput}
            value={value}
            placeholder={placeholder}
            onChange={(e) => onChange(e)}
            style={{ height: height }} 
            />
          {overlayElement}
        </div>
      );
    }
  );

export default TextInput