import React, {useState} from 'react';

const NameForm = () => {
	const [name, setName] = useState('');
	function handleChange(event) {
		setName(event.target.value);
	}
	function handleSubmit(event) {
		event.preventDefault();
		alert(name);
	}

	return (
		<div>
			<form>
				<label>Name:</label>
				<input type="text" value={name} onChange={handleChange} />
				<input type="submit" onClick={handleSubmit} value="Submit" />
			</form>
			{name}
		</div>
	);
};

export default function FormDemo() {
	return (
		<div style={{marginTop: 25}}>
			<NameForm />
		</div>
	);
}
