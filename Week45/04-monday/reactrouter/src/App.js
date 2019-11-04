import React from 'react';
import {
	BrowserRouter as Router,
	Route,
	Link,
	Switch,
	useRouteMatch
} from 'react-router-dom';
import data from './data/data';

import './App.css';

export default function App() {
	return (
		<div className="App">
			<Router>
				<ul>
					<li>
						<Link to="/">Home</Link>
					</li>
				</ul>
				<Switch>
					<Route path="/all" render={() => <All users={data.users} />} />
					<Route exact path="/" component={Welcome} />
					<Route
						path={`/details/:index`}
						render={() => <Details users={data.users} />}
					/>
					<Route component={Error} />
				</Switch>
			</Router>
		</div>
	);
}

function Welcome() {
	return (
		<>
			<h1>Welcome to our friends site</h1>
			<ul>
				<li>
					<Link to="/all">See all users</Link>
				</li>
			</ul>
		</>
	);
}

function All({users}) {
	//Could have done some state here
	return (
		<>
			<h1>All Users</h1>
			<table className="table">
				<thead>
					<tr>
						<th>Picture</th>

						<th>Name</th>

						<th>Details</th>
					</tr>
				</thead>
				<tbody>
					{users.map((e, index) => (
						<tr key={index}>
							<td>
								<img src={e.picture.thumbnail} alt="go away warning" />
							</td>
							<td>{e.first + ' ' + e.last}</td>
							<td>
								<Link to={`/details/${index}`}>Details</Link>
							</td>
						</tr>
					))}
				</tbody>
			</table>
		</>
	);
}

function Details({users}) {
	let match = useRouteMatch();
	let user = users[match.params.index];
	// console.log('users', users);
	// console.log('user', user);
	// console.log('index', useRouteMatch().params.index);

	// console.log('match', match);

	if (user !== null && user !== undefined) {
		return (
			<div>
				<h5>success, your user:</h5>
				<TableGen user={user} />
			</div>
		);
	}

	return <Error />;
}

function TableGen({user}) {
	let keys = Object.keys(user);
	let values = [];
	for (const key in user) {
		if (user.hasOwnProperty(key)) {
			const element = user[key];
			values.push(element);
		}
	}

	return (
		<table className="table table-bordered">
			<thead className="thead-dark">
				<tr>
					{keys.map((element, index) => {
						return (
							<th key={index} scope="col">
								{element}
							</th>
						);
					})}
				</tr>
			</thead>
			<tbody>
				<tr>
					{values.map((element, index) => {
						if (typeof element === 'object')
							//only picture
							return (
								<td key={index} scope="row">
									{<img src={element.large} alt="go away warning" />}
								</td>
							);
						else {
							return <td key={index}>{element}</td>;
						}
					})}
				</tr>
			</tbody>
		</table>
	);
}

function Error() {
	return <p>Du har gjort noget galt</p>;
}
