import React, {useState, useEffect} from 'react';
import './App.css';
import 'bootstrap/dist/css/bootstrap.min.css';
import Table from 'react-bootstrap/Table';

const URL = 'http://localhost:8080/cool/api/binance';
function handleHttpErrors(res) {
	if (!res.ok) {
		return Promise.reject({status: res.status, fullError: res.json()});
	}
	return res.json();
}

function makeOptions(method) {
	var opts = {
		method: method,
		headers: {
			Accept: 'application/json',
			'Content-Type': 'application/json'
		}
	};

	return opts;
}

/**
 *  WOULD USE IF I WERENT BANNED
 * @param {*} setState
 */
function fetchData(setState) {
	fetch(URL, makeOptions('GET'))
		.then(handleHttpErrors)
		.then(data => setState(data))
		.catch(err => {
			if (err.status) {
				err.fullError.then(e => console.log(e.detail));
			} else {
				console.log('Network error');
			}
		});
}

function App() {
	let initialValue = {BTCUSD: 9000, BTCSOME: 1000, BTCOTHER: 3000};
	console.log('init', Object.keys(initialValue));
	let [state, setState] = useState(initialValue);
	setTimeout(() => {
		setState({...state, ['BTCUSD']: state['BTCUSD'] + 1});
	}, 5000);

	return (
		<div className="App">
			<h1>Binance Cryptocurrencies</h1>
			<GenerateTable data={state} />
			{/* {JSON.stringify(state)} */}
		</div>
	);
}

function GenerateTable({data}) {
	let keys = Object.keys(data);
	let values = Object.values(data);
	return (
		<Table striped bordered hover size="sm" variant="dark">
			<caption>{new Date().toLocaleString()}</caption>
			<thead>
				<tr>
					{keys.map((element, index) => (
						<th key={index}>{element}</th>
					))}
				</tr>
			</thead>
			<tbody>
				<tr>
					{values.map((element, index) => (
						<td key={index}>{element}</td>
					))}
				</tr>
			</tbody>
		</Table>
	);
}

export default App;
