import React, {useState, useEffect} from 'react';
import AddEditPerson from './AddEditPerson';
import AllPersons from './AllPersons';

function App({apiFacade}) {
	const emptyPerson = {id: '', age: '', name: '', email: '', gender: ''};
	const [personToAddEdit, setPersonToAddEdit] = useState(emptyPerson);
	const [persons, setPersons] = useState([]);
	const [triggerUpdate, setTriggerUpdate] = useState(false);

	useEffect(() => {
		//This would be a great place to fetch data (all persons) from the backend
		apiFacade
			.getPersons()
			.then(result => setPersons(result))
			.catch(err => console.log(err));
	}, [triggerUpdate]);

	const storeAddEditPerson = async person => {
		//Call this from the AddEditPerson control with the person to Add or Edit and Add/Edit via the apiFacade
		await apiFacade.addEditPerson(person);
		// setPersons([...persons]);
		// setPersonToAddEdit(emptyPerson);
		setPersonToAddEdit(emptyPerson);
		setTriggerUpdate(!triggerUpdate); //force useEffect, datafetch
	};

	const deletePerson = async id => {
		//Call this from the AllPerson control with the id for the person to delete
		await apiFacade.deletePerson(id);
		setTriggerUpdate(!triggerUpdate);
	};

	const editPerson = async person => {
		//Call this from the AllPerson control with the  person to edit
		//Set the state variable personToAddEdit with this person (a clone)
		//to make the new value flow down via props
		setPersonToAddEdit(person);
	};

	return (
		<div className="container">
			<div className="row">
				<h3>CRUD Demo </h3>
				<div className="col-md-7">
					<h3>All Persons</h3>
					<AllPersons
						persons={persons}
						editPerson={editPerson}
						deletePerson={deletePerson}
					/>
				</div>
				<div className="col-md-5">
					<h3 style={{textAlign: 'center'}}>Add Persons</h3>
					<AddEditPerson
						newPerson={personToAddEdit}
						//  Next two lines, are if you decide to use the pattern introduced in the day-2 exercises
						addEditPerson={storeAddEditPerson}
						key={personToAddEdit.id}
					/>
				</div>
			</div>
		</div>
	);
}
export default App;
