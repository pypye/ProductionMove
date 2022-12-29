import './style.css'

/*
* @description: Icon custom component
*/
function IconWrapper(props) {
    return (
        <div className="icon-wrapper">
            <svg className="icon" focusable="false" aria-hidden="true" viewBox="0 0 24 24">
                {props.children}
            </svg>
        </div>
    )
}

function IconWrapperRaw(props) {
    return (
        <div className="icon-wrapper-raw">
            <svg className="icon" focusable="false" aria-hidden="true" viewBox="0 0 24 24">
                {props.children}
            </svg>
        </div>
    )
}

const Icon = {}

Icon.Navigation = function IconNavigation(props) {
    return (
        <IconWrapper>
            <circle cx="4" cy="12" r="1" fill="currentColor"></circle><rect width="14" height="2" x="7" y="11" fill="currentColor" rx=".94" ry=".94"></rect><rect width="18" height="2" x="3" y="16" fill="currentColor" rx=".94" ry=".94"></rect><rect width="18" height="2" x="3" y="6" fill="currentColor" rx=".94" ry=".94"></rect>
        </IconWrapper>
    )
}


Icon.Notification = function IconNotification(props) {
    return (
        <IconWrapper>
            <path fill="currentColor" d="m20.52 15.21l-1.8-1.81V8.94a6.86 6.86 0 0 0-5.82-6.88a6.74 6.74 0 0 0-7.62 6.67v4.67l-1.8 1.81A1.64 1.64 0 0 0 4.64 18H8v.34A3.84 3.84 0 0 0 12 22a3.84 3.84 0 0 0 4-3.66V18h3.36a1.64 1.64 0 0 0 1.16-2.79ZM14 18.34A1.88 1.88 0 0 1 12 20a1.88 1.88 0 0 1-2-1.66V18h4Z"></path>
        </IconWrapper>
    )
}

Icon.People = function IconPeople(props) {
    return (
        <IconWrapper>
            <path fill="currentColor" d="M9 11a4 4 0 1 0-4-4a4 4 0 0 0 4 4Zm8 2a3 3 0 1 0-3-3a3 3 0 0 0 3 3Zm4 7a1 1 0 0 0 1-1a5 5 0 0 0-8.06-3.95A7 7 0 0 0 2 20a1 1 0 0 0 1 1h12a1 1 0 0 0 1-1"></path>
        </IconWrapper>
    )
}

Icon.AvatarBox = function IconAvatarBox(props) {
    return (
        <div className='avatar-box'>
            {props.children}
        </div>
    )
}

Icon.NotificationRaw = function IconNotificationRaw(props) {
    return (
        <IconWrapperRaw>
            <path fill="currentColor" d="m20.52 15.21l-1.8-1.81V8.94a6.86 6.86 0 0 0-5.82-6.88a6.74 6.74 0 0 0-7.62 6.67v4.67l-1.8 1.81A1.64 1.64 0 0 0 4.64 18H8v.34A3.84 3.84 0 0 0 12 22a3.84 3.84 0 0 0 4-3.66V18h3.36a1.64 1.64 0 0 0 1.16-2.79ZM14 18.34A1.88 1.88 0 0 1 12 20a1.88 1.88 0 0 1-2-1.66V18h4Z"></path>
        </IconWrapperRaw>
    )
}

export { Icon }