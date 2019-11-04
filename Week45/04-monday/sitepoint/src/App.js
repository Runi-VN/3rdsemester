import React, {Component} from 'react';
import {Link, Route, Switch} from 'react-router-dom';
import Category from './Category';

class App extends Component {
	render() {
		return (
			<div>
				<nav className="navbar navbar-light">
					<ul className="nav navbar-nav">
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

				<Switch>
					<Route exact path="/" component={Home} />
					<Route path="/category" component={Category} />
					<Route path="/products" component={Products} />
				</Switch>
			</div>
		);
	}
}
export default App;

/* Code for Home and Products component omitted for brevity */
/* Home component (OLD REUSED) */
const Home = () => (
	<div>
		<h2>Home</h2>
	</div>
);

/* Category is imported */

/* Products component (OLD REUSED) */
const Products = () => (
	<div>
		<h2>Products</h2>
	</div>
);