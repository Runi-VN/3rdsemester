import React, {useState, useEffect} from 'react';

export default function AddEditPerson(props) {
	const newPerson = props.newPerson;
	const [person, setPerson] = useState({...newPerson});
	let addEditPerson = props.addEditPerson; //Method to be used
	const emptyPerson = {id: '', age: '', name: '', email: '', gender: ''};

	useEffect(
		() =>
			setPerson({
				...newPerson
			}),
		[newPerson]
	);

	/* Add the required changes to use Reacts "Controlled Component Pattern" 
     to handle inputs related to a person */
	const handleChange = evt => {
		const target = evt.target;
		const value = target.value;
		const name = target.id;
		setPerson({...person, [name]: value});
	};
	const handleSubmit = async evt => {
		evt.preventDefault();
		await addEditPerson(person);
		setPerson({...emptyPerson});
	};

	return (
		<div>
			<p>{JSON.stringify(person)}</p>
			<form
				className="form-horizontal"
				onSubmit={handleSubmit}
				onChange={handleChange}>
				<div className="form-group">
					<label className="control-label col-sm-3">Id:</label>
					<div className="col-sm-9">
						<input
							className="form-control"
							readOnly
							id="id"
							value={person.id}
						/>
					</div>
				</div>
				<div className="form-group">
					<label className="control-label col-sm-3" htmlFor="name">
						Name:
					</label>
					<div className="col-sm-9">
						<input
							value={person.name}
							className="form-control"
							id="name"
							placeholder="Enter Name"
						/>
					</div>
				</div>
				<div className="form-group">
					<label className="control-label col-sm-3" htmlFor="age">
						Age:
					</label>
					<div className="col-sm-9">
						<input
							value={person.age}
							type="number"
							className="form-control"
							id="age"
							placeholder="Enter age"
						/>
					</div>
				</div>
				<div className="form-group">
					<label className="control-label col-sm-3" htmlFor="email">
						Email:
					</label>
					<div className="col-sm-9">
						<input
							value={person.email}
							type="email"
							className="form-control"
							id="email"
							placeholder="Enter email"
						/>
					</div>
				</div>
				<div className="form-group">
					<label className="control-label col-sm-3" htmlFor="pwd">
						Gender:
					</label>
					<div className="col-sm-9">
						<input
							value={person.gender}
							className="form-control"
							id="gender"
							placeholder="Enter Gender"
						/>
					</div>
				</div>
				<div className="form-group">
					<div className="col-sm-offset-3 col-sm-9">
						<button type="submit" className="btn btn-primary">
							Submit
						</button>
						<button
							onClick={() => setPerson({...emptyPerson})}
							style={{marginLeft: 5}}
							type="button"
							className="btn btn-dark">
							Cancel
						</button>
					</div>
				</div>
			</form>
			<s>Please provide me with the ability to create new persons</s>
			<s>And update the backend when submitted</s>
		</div>
	);
}
