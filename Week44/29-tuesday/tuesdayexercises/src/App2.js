import React, {useState, useEffect} from 'react';
import './App.css';

export default function App() {
	let [time, setTime] = useState(new Date().toLocaleTimeString());

	useEffect(() => {
		let trigger = setInterval(() => {
			setTime(new Date().toLocaleTimeString());
		}, 1000);
		//Cleanup interval
		return () => clearInterval(trigger);
	});

	return (
		<div className="App">
			<h1>The current time is:</h1>
			<h2>{time}</h2>
		</div>
	);
}
