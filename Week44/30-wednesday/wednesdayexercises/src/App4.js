import React, {useState} from 'react';
import './App.css';
import 'bootstrap/dist/css/bootstrap.min.css';
import PersonList from './PersonList';
import NewPerson from './NewPerson';
import uuid from 'uuid/v1';

function App() {
	const initialData = [
		{id: uuid(), name: 'Peter'},
		{id: uuid(), name: 'Ole'},
		{id: uuid(), name: 'Jan'}
	];
	const [persons, setPersons] = useState(initialData);
	const [newPerson, setNewPerson] = useState({id: '', name: ''});
	console.log(persons);

	const addPerson = person => {
		if (person.id === '') {
			// id=-1 Indicates a new object
			person.id = uuid();
			persons.push(person);
		} else {
			//if id != "", it's an existing person. Find it and add changes
			let personToEdit = persons.find(t => t.id === person.id);
			personToEdit.name = person.name;
		}
		setPersons([...persons]);
		setNewPerson({id: '', name: ''});
	};

	const deletePerson = index => {
		let personToDelete = persons.find(t => t.id === index);
		let newPersons = persons.filter(element => element !== personToDelete);
		setPersons([...newPersons]);
	};
	const editPerson = index => {
		let personToEdit = persons.find(t => t.id === index);
		setNewPerson(personToEdit);
	};

	return (
		<div className="container outer">
			<h2 style={{textAlign: 'center', marginBottom: 25}}>Persons</h2>

			<div className="row">
				<div className="col-6 allTodos">
					<PersonList
						persons={persons}
						editPerson={editPerson}
						deletePerson={deletePerson}
					/>
				</div>{' '}
				{/*Re-use CSS*/}
				<div className="col-5 new-todo">
					<NewPerson addPerson={addPerson} nextPerson={newPerson} />
				</div>{' '}
				{/*Re-use CSS*/}
			</div>
		</div>
	);
}
export default App;
