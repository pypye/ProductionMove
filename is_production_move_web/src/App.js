import React from 'react';
import { Router } from './routes';
import { BrowserRouter } from "react-router-dom";
import { getAuth } from './sessions';

function App() {
    const [sessions, setSessions] = React.useState(false);

    React.useEffect(() => {
        const auth = getAuth();
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
