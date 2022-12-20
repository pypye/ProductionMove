import React from 'react';
import { Router } from './routes';
import { BrowserRouter } from "react-router-dom";
import { UseAuth } from './utils';

function App() {
    const [sessions, setSessions] = React.useState(false);

    React.useEffect(() => {
        const auth = UseAuth.get();
        if (auth) setSessions(true);
    }, [])


    return (
        <div className="App">
            <BrowserRouter>
                <Router auth={sessions} />
            </BrowserRouter>

        </div>
    );
}

export default App;
