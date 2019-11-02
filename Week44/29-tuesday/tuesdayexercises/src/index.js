import React from 'react';
import ReactDOM from 'react-dom';
import './index.css';
import App from './App';
import App1 from './App1';
import App2 from './App2';
import App3 from './App3';
import App4 from './App4';
import App5 from './App5';

let app = <App />;

const DontUseMeForReal = () => {
	return (
		<div className="App" onClick={handleSelect}>
			<a href="/" className="x" id="app">
				Home
			</a>
			&nbsp; | &nbsp; State &nbsp;| &nbsp;
			<a href="/" className="x" id="app1">
				Exercise 1
			</a>
			&nbsp; | &nbsp;
			<a href="/" className="x" id="app2">
				Exercise 2
			</a>
			&nbsp;| &nbsp;
			<a href="/" className="x" id="app3">
				Exercise 3
			</a>
			&nbsp; | Lists&Keys | &nbsp;
			<a href="/" className="x" id="app4">
				Exercise 1
			</a>
			&nbsp; | &nbsp;
			<a href="/" className="x" id="app5">
				Exercise 2
			</a>
			{app}
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
			app = <App1 initial={2} incremental={1} />;
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
		case 'app5':
			app = <App5 />;
			break;
		default:
			app = <App />;
	}
	ReactDOM.render(<DontUseMeForReal />, document.getElementById('root'));
}

ReactDOM.render(<DontUseMeForReal />, document.getElementById('root'));
