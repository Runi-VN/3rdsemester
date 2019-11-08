import React, {useState, useEffect} from 'react';
import './App.css';
import {
	BrowserRouter as Router,
	Switch,
	Route,
	NavLink,
	useLocation,
	Link,
	useRouteMatch,
	useParams,
	Prompt
} from 'react-router-dom';

function App({bookFactory}) {
	let match = useRouteMatch();
	console.log(match);
	return (
		<div className="App">
			<Router>
				<Header />
				<Switch>
					<Route exact path="/">
						<Home />
					</Route>
					<Route path="/products">
						<Products bookFactory={bookFactory} />
					</Route>
					<Route path="/find-book">
						<Find bookFactory={bookFactory} />
					</Route>
					<Route path="/add-book">
						<AddBook bookFactory={bookFactory} />
					</Route>
					<Route path="*">
						<NoMatch />
					</Route>
				</Switch>
			</Router>
		</div>
	);
}

const Header = () => (
	<div>
		<ul className="header">
			<li>
				<NavLink exact activeClassName="active" to="/">
					Home
				</NavLink>
			</li>
			<li>
				<NavLink activeClassName="active" to="/products">
					Products
				</NavLink>
			</li>
			<li>
				<NavLink activeClassName="active" to="/add-book">
					Add Book
				</NavLink>
			</li>
			<li>
				<NavLink activeClassName="active" to="/find-book">
					Find Book
				</NavLink>
			</li>
		</ul>
	</div>
);
const Home = () => <div>Home</div>;
const Products = ({bookFactory}) => {
	let getBooks = bookFactory.getBooks;

	let match = useRouteMatch();
	console.log(match);
	return (
		<div>
			<table className="table table-bordered">
				<thead>
					<tr key={'warninggoaway'}>
						<th>ID</th>
						<th>Title</th>
						<th>Info</th>
						{/* {getBooks().map((element, index) => {
              return (
                <>
                  <th>{element.id}</th> <th>{element.title}</th>
                </>
              );
            })} */}
					</tr>
				</thead>
				<tbody>
					{getBooks().map(element => {
						return (
							<>
								<tr key={element.id}>
									<td>
										{
											<Link to={`${match.url}/${element.id}`}>
												{element.id}
											</Link>
										}
									</td>
									<td>{element.title}</td>
									<td>{element.info}</td>
								</tr>
							</>
						);
					})}
				</tbody>
			</table>
			<Route path={`${match.path}/:id`}>
				<Details bookFactory={bookFactory} />
			</Route>
		</div>
	);
};
const Find = ({bookFactory}) => {
	let [idVal, setIdVal] = useState();
	let initialValue = {id: '', title: '', info: ''};
	let [book, setBook] = useState(initialValue);
	let [msg, setMsg] = useState('');
	const handleSubmit = evt => {
		if (idVal && idVal !== '') {
			evt.preventDefault();
			let book = bookFactory.findBook(idVal);
			if (book) {
				setBook(book);
				setMsg();
				//setIdVal(); //doesn't work
			} else setMsg('No such book');
		} else alert('Missing input');
	};

	const handleChange = event => {
		const target = event.target;
		const value = target.value;
		setIdVal(value);
	};

	return (
		<div>
			<h1>u r finding the books</h1>
			<form>
				<label>Book ID</label> <br />
				<input
					id="id"
					placeholder="still book id m8"
					onChange={handleChange}></input>
				<br />
				<button onClick={handleSubmit}>Find book</button>
				<br />
				{msg}
				<EditBook book={book} setBook={setBook} bookFactory={bookFactory} />
				<DeleteBook book={book} setBook={setBook} bookFactory={bookFactory} />
			</form>
		</div>
	);
};

const DeleteBook = ({book, setBook, bookFactory}) => {
	let handleSubmit = evt => {
		evt.preventDefault();
		setBook(bookFactory.deleteBook(book.id));
	};
	if (book && book.id) {
		return (
			<>
				<button onClick={handleSubmit}>Delete book</button>
			</>
		);
	}
	return null;
};

const EditBook = ({book, setBook, bookFactory}) => {
	console.log(book);
	let initialValue = {...book};
	console.log('init', initialValue);
	let [newBook, setNewBook] = useState({...initialValue});
	console.log(newBook);
	const handleChange = event => {
		//console.log({newBook});
		const target = event.target;
		const id = target.id;
		const value = target.value;
		console.log('target ', target);
		console.log('id ', id);
		console.log('value ', value);
		setNewBook({...newBook, [id]: value});
	};
	//console.log(bookFactory.editBook)
	console.log('book ', book);
	console.log('newBook ', newBook);

	let handleSubmit = evt => {
		evt.preventDefault();
		let returnBook = bookFactory.editBook(newBook);
		if (returnBook === newBook) alert('edited');
		else alert('error');
	};

	if (book && book.id) {
		return (
			<div id="editBookContainer">
				<p>Current book ---> {JSON.stringify(book)}</p>
				<p>New book -------> {JSON.stringify(newBook)}</p>
				<h4>Success</h4>
				<label>Book ID</label>
				<br />
				<input id="id" disabled value={newBook.id}></input>
				<br />
				<label>Book Title</label>
				<br />

				<input
					type="text"
					id="title"
					value={newBook.title}
					onChange={handleChange}></input>
				<br />
				<label>Book Info</label>
				<br />
				<input
					type="text"
					id="info"
					value={newBook.info}
					onChange={handleChange}></input>
				<br />
				<button onClick={handleSubmit}>Edit Book</button>
			</div>
		);
	}
	return null;
};

const Details = ({bookFactory}) => {
	let {id} = useParams();
	let book = bookFactory.findBook(id);

	if (book) {
		return (
			<div>
				<h3>success</h3>
				{book.id} {' | '}
				{book.title}
				{' | '}
				{book.info}
			</div>
		);
	} else {
		return <h1>Du er dum</h1>;
	}
};

function AddBook({bookFactory}) {
	let initialValue = {id: '', title: '', info: ''};
	let [book, setBook] = useState(initialValue);
	let [msg, setMsg] = useState(); //status message
	let [isBlocking, setIsBlocking] = useState(false);

	const handleSubmit = evt => {
		if (book && book.title !== '' && book.info !== '') {
			evt.preventDefault();
			bookFactory.addBook(book);
			setMsg(`Added book ${JSON.stringify(book)}`);
			setBook({...initialValue});
			//evt.target.reset(); //not targeting form
			setIsBlocking(false);
		} else alert('Missing input');
	};

	const handleChange = event => {
		setIsBlocking(event.target.value.length > 0); //Doesn't work with two inputs
		const target = event.target;
		const id = target.id;
		const value = target.value;
		setBook({...book, [id]: value});
	};

	return (
		<div>
			<h1>u r adding book</h1>
			<Prompt
				when={isBlocking}
				message={location =>
					`Are you sure you want to go to ${location.pathname}? You have unsaved changes!`
				}
			/>

			<p>
				Blocking? {isBlocking ? 'Yes, click a link or the back button' : 'Nope'}
			</p>
			<p>{JSON.stringify(book)}</p>
			<form>
				<label>title</label>
				<br />
				<input id="title" value={book.title} onChange={handleChange}></input>
				<br />
				<label>info</label>
				<br />
				<input id="info" value={book.info} onChange={handleChange}></input>
				<br />
				<button onClick={handleSubmit}>add</button>
				<br />
				{msg}
			</form>
		</div>
	);
}

function NoMatch() {
	let location = useLocation();

	return (
		<div>
			<h3>
				No match for <code>{location.pathname}</code>
			</h3>
		</div>
	);
}

export default App;
