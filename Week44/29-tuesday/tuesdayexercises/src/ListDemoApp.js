import React, {useState, useEffect} from 'react';

function ListItem({item}) {
	return <li>{item}</li>;
}

function NumberList({numbers}) {
	console.log('--NUMBERS -->', numbers);
	const listItems = numbers.map((n, index) => (
		<ListItem key={index} item={n} />
	));
	return <ul>{listItems}</ul>;
}

function RowItem({item}) {
	return (
		<tr>
			<td>{item}</td>
		</tr>
	);
}

function NumberTable({numbers}) {
	return (
		<table>
			<thead>
				<tr>
					<th>Numbers</th>
				</tr>
			</thead>
			<tbody>
				{numbers.map((n, index) => (
					<RowItem key={index} item={n} />
				))}
			</tbody>
		</table>
	);
}

function ListDemo(props) {
	console.log(props.numbers);
	return (
		<div>
			<h2>All numbers passed in via props</h2>
			<p>Unordered List</p>
			<NumberList numbers={props.numbers} />
			<p>Table</p>
			<NumberTable numbers={props.numbers} />
		</div>
	);
}
export default function App() {
	const [numbers, setNumbers] = useState([1, 2, 3, 4]);
	useEffect(() => {
		let trigger = setInterval(() => {
			let rnd = Math.floor(Math.random() * 10 + 1);
			console.log(rnd);
			setNumbers([...numbers, rnd]);
		}, 5000);
		return () => {
			clearInterval(trigger);
		};
	});
	return <ListDemo numbers={numbers} />;
}
