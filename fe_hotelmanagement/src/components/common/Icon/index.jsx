const Icon = ({logo,size}) => {
    return (
        <><img src={logo} className='mr-2' width={size ? size : 35 } /></>
    )
}

export default Icon