import React from 'react';
import { Table } from './components';
import { Layout } from './layouts';


function App() {
    const fakeData = [
        {id: 1,name: 'Duc', age: 20,},
        {id: 2,name: 'Tran', age: 21,},
        {id: 3,name: 'Duc', age: 22,},
        {id: 4,name: 'Tran', age: 23,},
        {id: 5,name: 'Duc', age: 24,},
        {id: 6,name: 'Tran', age: 25,},
        {id: 7,name: 'Duc', age: 26,},
        {id: 8,name: 'Tran', age: 27,},
    ]
    return (
        <div className="App">
            <Layout>
                <Table data={fakeData}/>
            </Layout>
        </div>
    );
}

export default App;
