import './style.css'

function Navigation(props) {
    return (
        <div className='navigation'>
            {props.children}
        </div>
    )
}

Navigation.Category = function NavigationCategory(props) {
    return (
        <div className='navigation-category'>
            {props.label}
            {props.children}
        </div>
    )
}

Navigation.Item = function NavigationItem(props) {
    return (
        <a className='navigation-item' href={props.link}>
            {props.icon}
            {props.label}
        </a>
    )
}

export { Navigation }