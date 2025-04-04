import './style.scss'

const TextInput = ({name, text, className, onChangeFunc}) => {
    return <> 
        <div onChange={onChangeFunc} class={`form-outline text-input ${className}`} data-mdb-input-init>
            <input value={text} type="text" id="form12" class="form-control" />
            <label class="form-label" for="form12">{<>{name}</>}</label>
        </div>
    </>
}

export default TextInput