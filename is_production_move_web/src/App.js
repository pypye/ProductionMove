import React from 'react';
import { Table } from './components/PrimaryComponents';
import { Login } from './pages';

function App() {
    const tableData = [
        { id: 1, name: 'John', age: 19 },
        { id: 2, name: 'Jane', age: 20 },
        { id: 3, name: 'Joe', age: 21 },
        { id: 4, name: 'Jack', age: 22 },
        { id: 5, name: 'Jill', age: 23 },
        { id: 6, name: 'Jen', age: 24 },
        { id: 7, name: 'Jenny', age: 25 },
        { id: 8, name: 'Jenny', age: 26 },
        { id: 9, name: 'Jenny', age: 27 },
        { id: 10, name: 'Jenny', age: 28 },
        { id: 11, name: 'Jenny', age: 29 },
        { id: 12, name: 'Jenny', age: 30 },
        { id: 13, name: 'Jenny', age: 31 },
    ];
    return (
        <div className="App">
            <Table width='80rem' height='35rem' data={tableData} />
            {/* <Login /> */}
        </div>
    );
}

export default App;
