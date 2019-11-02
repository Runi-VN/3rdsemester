import React from 'react';
import PropTypes from 'prop-types';

const PersonList = ({persons, deletePerson, editPerson}) => {
	return (
		<>
			<h2>All Persons</h2>
			<ul>
				{persons.map(person => (
					<li key={person.id}>
						{person.name}&nbsp;
						<a
							href="#/"
							onClick={e => {
								e.preventDefault();
								deletePerson(person.id);
							}}>
							(delete
						</a>
						{', '}
						<a href="#/" onClick={() => editPerson(person.id)}>
							edit)
						</a>
					</li>
				))}
			</ul>
		</>
	);
};
export default PersonList;

PersonList.propTypes = {
	persons: PropTypes.array
};
