import React from 'react';
import { Router } from './routes';
import { BrowserRouter } from "react-router-dom";

/*
* @description: Main app
*/
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
