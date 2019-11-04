/* Import statements */
import React, {Component} from 'react';
import {Link, Route, Switch} from 'react-router-dom';

/* Home component */
const Home = () => (
	<div>
		<h2>Home</h2>
	</div>
);

/* Category component */
const Category = () => (
	<div>
		<h2>Category</h2>
	</div>
);

/* Products component */
const Products = () => (
	<div>
		<h2>Products</h2>
	</div>
);

/* App component */
export default class App extends React.Component {
	render() {
		return (
			<div>
				<nav className="navbar navbar-light">
					<ul className="nav navbar-nav">
						{/* /* Link components are used for linking to other views */}
						<li>
							<Link to="/">Homes</Link>
						</li>
						<li>
							<Link to="/category">Category</Link>
						</li>
						<li>
							<Link to="/products">Products</Link>
						</li>
					</ul>
				</nav>
				{/* /* Route components are rendered if the path prop matches the current
        URL */}
				<Switch>
					<Route exact path="/" component={Home} />
					<Route path="/products" component={Products} />
					<Route path="/category" component={Category} />
					<Route
						path="/:id"
						render={() => (
							<p>
								{' '}
								I want this text to show up for all routes other than '/',
								'/products' and '/category'{' '}
							</p>
						)}
					/>
				</Switch>
			</div>
		);
	}
}
