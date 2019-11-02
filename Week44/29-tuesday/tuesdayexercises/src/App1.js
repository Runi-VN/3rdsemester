import React, { useState, useEffect } from "react";
import "./App.css";

export default function App(props) {
  //see index.js
  let { initial, incremental } = props; //extract what we need from passed in props.
  let [count, setCount] = useState(Number(localStorage.getItem("count") || initial)); //prepare count
  let [modifier, setModifier] = useState(incremental); //prepare modifier

  useEffect(() => {
    localStorage.setItem("count", count);
  });

  return (
    <div className='App'>
      <h1>State and Events with React</h1>
      <h2> Create a simple React component using the useState hook</h2>
      <p>Initial Value: {initial}</p>
      <p>Count: {count}</p>
      <label>Chose modifier:</label>
      <br />
      <input placeholder='1' onChange={e => setModifier(e.target.value || 1)}></input>
      <br />
      <button onClick={() => setCount(parseInt(count) + parseInt(modifier))}>Increment</button>
      <button onClick={() => setCount(parseInt(count) - parseInt(modifier))}>Decrement</button>
    </div>
  );
}
