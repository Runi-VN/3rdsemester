import React, {useState, useEffect} from 'react';
import './App.css';

export default function App() {
	function handleHttpErrors(res) {
		if (!res.ok) {
			return Promise.reject({status: res.status, fullError: res.json()});
		}
		return res.json();
	}

	let [chuck, setChuck] = useState();
	let [dad, setDad] = useState();
	let [fetchStatus, setFetchStatus] = useState(false);

	/*Chuck jokes*/
	useEffect(() => {
		/*Runs once at start :( */
		fetch('https://api.chucknorris.io/jokes/random')
			.then(handleHttpErrors)
			.then(data => setChuck(data.value))
			.catch(err => {
				if (err.status) {
					err.fullError.then(e => console.log(e.detail));
				} else {
					console.log(err);
				}
			});
	}, [fetchStatus]);

	/* Dad jokes */
	useEffect(() => {
		let trigger = setInterval(() => {
			fetch('https://icanhazdadjoke.com', {
				headers: {
					Accept: 'text/plain'
				}
			})
				.then(res => res.text())
				//.then(handleHttpErrors) //only handles JSON lol
				.then(data => setDad(data))
				.catch(err => {
					if (err.status) {
						err.fullError.then(e => console.log(e.detail));
					} else {
						console.log(err);
					}
				});
		}, 10000);
		return () => clearInterval(trigger);
	}, []);

	return (
		<div id="App">
			<h1>Jokes</h1>
			<button onClick={() => setFetchStatus(!fetchStatus)}>Get Chuck</button>
			<h3>
				Chuck: <br />
				{chuck}
			</h3>
			<h3>
				Dad: <br />
				{dad}
			</h3>
		</div>
	);
}
