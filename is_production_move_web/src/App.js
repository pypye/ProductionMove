import React from 'react';
import { Router } from './routes';
import { BrowserRouter } from "react-router-dom";

function App() {

    return (
        <div className="App">
            <BrowserRouter>
                <Router />
            </BrowserRouter>
        </div>
    );
}

export default App;
