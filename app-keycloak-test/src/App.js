import React, { useState } from 'react';
import logo from './logo.svg';
import './App.css';

import axios from 'axios';

function App() {
  const [ responses, setResponses ] = useState([]);

  const renderResponses = () => {
    return (
      <ul>
        {responses.map((response, index) => (<li key={index}>{response}</li>))}
      </ul>
    );
  }

  const handlerClickRequestApi = () => {
    const token = localStorage.getItem('react-token');

    axios.get('http://localhost:8085/api/v1/users/username', {
        headers: { 'Authorization': `Bearer ${token}` }
      })
      .then(response => {
        console.log(response.data);
        setResponses([...responses, response.data]);
      });
  };

  return (
    <div className="App">
      <header className="App-header">
        <img src={logo} className="App-logo" alt="logo" />
        <p>
          Edit <code>src/App.js</code> and save to reload.
        </p>
        <button onClick={handlerClickRequestApi} >Request API</button>
        {renderResponses()}
      </header>
    </div>
  );
}

export default App;
