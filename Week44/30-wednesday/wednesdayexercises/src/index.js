import React from 'react';
import ReactDOM from 'react-dom';
import './index.css';
import App from './App';
import App1 from './App1';
import App2 from './App2';
import App3 from './App3';
import App4 from './App4';

let app = <App />;

const DontUseMeForReal = () => {
	return (
		<div className="App">
			<div onClick={handleSelect}>
				<a href="/" className="x" id="app">
					Home
				</a>
				{' | '}
				&nbsp;
				<a href="/" className="x" id="app1">
					Exercise 1
				</a>
				{' | '}
				&nbsp;
				<a href="/" className="x" id="app2">
					Exercise 2
				</a>
				{' |  Lifting State Up | '}
				&nbsp;
				<a href="/" className="x" id="app3">
					Exercise 1
				</a>
				{' | '}
				<a href="/" className="x" id="app4">
					Exercise 2
				</a>
			</div>
			{app}
			{/* Had to move this call outside of main div for assignment #2
			 * Otherwise checkbox wouldnt work-
			 *	This means I have to call event.preventDefault() a lot more
			 */}
		</div>
	);
};

function handleSelect(event) {
	event.preventDefault();
	if (event.target.className !== 'x') {
		return;
	}
	const id = event.target.id;
	switch (id) {
		case 'app':
			app = <App />;
			break;
		case 'app1':
			app = <App1 />;
			break;
		case 'app2':
			app = <App2 />;
			break;
		case 'app3':
			app = <App3 />;
			break;
		case 'app4':
			app = <App4 />;
			break;
	}
	ReactDOM.render(<DontUseMeForReal />, document.getElementById('root'));
}

ReactDOM.render(<DontUseMeForReal />, document.getElementById('root'));
