import './style.scss'

const TextInput = ({name, text, className, onChangeFunc, style}) => {
    return <> 
        <div onChange={onChangeFunc} className={`form-outline text-input ${className}`} data-mdb-input-init>
            <input value={text} type="text" id="form12" class="form-control" />
            <label class="form-label" for="form12">{<>{name}</>}</label>
        </div>
    </>
}

export default TextInput