import './style.css'

function Layout(props) {
    return (
        <div className="container">
            <nav>
                <div className='info'>
                    <span>Hệ thống ProductionMove</span>
                    <div className='personal-info'>
                        <div>
                            <img src="https://i.stack.imgur.com/dRFs4.png" alt="" width='40' height='40' />
                        </div>
                        <div>
                            <div><strong>Trần Đức</strong></div>
                            <div>Admin</div>
                        </div>
                    </div>
                </div>
                <div className='navigation'>Nagivation</div>
            </nav>
            <main>
                <header>
                    Header
                </header>
                <section>
                    {props.children}
                </section>
            </main>
        </div>
    );
} export default Layout;