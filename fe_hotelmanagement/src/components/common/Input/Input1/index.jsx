import './style.scss'

const TextInput = ({name, text, inputType, setText, className, onChangeFunc, style}) => {
    return <> 
        <div onChange={onChangeFunc} className={`form-outline text-input ${className}`} data-mdb-input-init>
            <input onChange={(e) => {setText(e.target.value)}} value={text} type={inputType ? inputType : 'text'} id="form12" className="form-control" />
            <label class="form-label" for="form12">{<>{name}</>}</label>
        </div>
    </>
}

export default TextInput