import React, {useState, useEffect} from 'react';
import CountryTable from './CountryTable';
import './App.css';

const App = props => {
	const factory = props.factory;
	const getLabels = factory.getLabels;
	const getCountries = factory.getCountries;
	// console.log('fact', factory);
	// console.log('labels', getLabels);
	// console.log('country', getCountries);

	const [labels, setLabels] = useState([]);
	const [countries, setCountries] = useState([]);

	const fetchCountries = () => {
		getCountries()
			.then(result => {
				setCountries(result);
			})
			.catch(err => console.log(err));
	};

	const fetchLabels = () => {
		getLabels()
			.then(result => {
				setLabels(result);
			})
			.catch(err => console.log(err));
	};

	// setInterval(() => {
	// 	fetchLabels();
	// 	fetchCountries();
	// }, 3000);

	useEffect(() => {
		setInterval(() => {
			fetchLabels();
			fetchCountries();
		}, 3000);
	}, []);
	return (
		<div>
			<div className="App-header">
				<h2>React, State, Fetch</h2>
			</div>
			<div className="App-intro">
				<p>
					Your initial task is to fetch data from the server (see exercise for
					how to start it), and create a table below, with these data
				</p>
				<CountryTable labels={labels} countries={countries} />
			</div>
		</div>
	);
};

export default App;
