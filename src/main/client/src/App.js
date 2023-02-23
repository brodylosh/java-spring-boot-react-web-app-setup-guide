import logo from './logo.svg';
import './App.css';
import { useState, useEffect } from 'react';

function App() {
    const [response, setResponse] = useState(null);

    useEffect(() => {
        fetch('/hello')
            .then((resp) => resp.json())
            .then((data) => {
                setResponse(data);
            });
      }, []);

  return (
    <div className="App">
      <header className="App-header">
        <img src={logo} className="App-logo" alt="logo" />
        <p>
          {response}
        </p>
      </header>
    </div>
  );
}

export default App;
