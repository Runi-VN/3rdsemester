import React from 'react';
import ReactDOM from 'react-dom';
import './index.css';
import App from './App';
import App2 from './App2'
import App3 from './App3'

let app = <App/>

/*
https://docs.google.com/document/d/17iG0I2cpgdfmOIW9J-L8kNaO47DILFIzEc9Yi8yW6-o/
Exercises:
ES6 and React functional Components and props
1 app per exercise
*/

const DontUseMeForReal = () => {
    return (
        <div className="App" onClick={handleSelect}>
         <a href="/"  className="x" id="app1">Exercise 1</a> &nbsp;
         <a href="/"  className="x" id="app2">Exercise 2</a> &nbsp;
         <a href="/"  className="x" id="app3">Exercise 3</a> &nbsp;
         {/* Add as many as you have exercises, but remember className="x" */}
         {app}
        </div>
    )
}

function handleSelect(event) {
    event.preventDefault();
    if(event.target.className!=="x"){
      return
    }  
    const id = event.target.id;
    switch (id) {
        case "app1": app = <App />; break;
        case "app2": app = <App2 />; break;
        case "app3": app = <App3 />; break;
    }
    ReactDOM.render(<DontUseMeForReal />, document.getElementById('root'));
}

ReactDOM.render(<DontUseMeForReal />, document.getElementById('root'));