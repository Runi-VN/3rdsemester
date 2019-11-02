import React from 'react';

const CountryTable = ({labels, countries}) => {
	// console.log('CTLabels', labels);
	// console.log('CTCountries', countries);

	let lengthHelper = array => {
		if (array.length <= 1) return;
		else {
			return ' (+' + (array.length - 1) + ')';
		}
	};

	return (
		<div>
			<p>
				Replace the thead section with a row generated from the Labels endpoint
			</p>
			<p>
				Replace the tbody section with rows generated from the countries
				endpoint
			</p>
			<table className="table">
				<thead>
					<tr>
						{labels.map((element, index) => (
							<th key={index}>{element}</th>
						))}
					</tr>
				</thead>
				<tbody>
					{countries.map(element => (
						<tr key={element.topLevelDomain}>
							<td>{element.name}</td>
							<td>{element.capital}</td>
							<td>{element.region}</td>
							<td>{element.population}</td>
							<td>{element.area}</td>
							<td>
								{element.timezones[0]}
								{lengthHelper(element.timezones)}
							</td>
							<td>
								{element.borders[0]}
								{lengthHelper(element.borders)}
							</td>
							<td>
								{element.topLevelDomain[0]}
								{lengthHelper(element.topLevelDomain)}
							</td>
							<td>
								{element.currencies[0]}
								{lengthHelper(element.currencies)}
							</td>
							<td>
								{element.languages[0]}
								{lengthHelper(element.languages)}
							</td>
						</tr>
					))}
				</tbody>
			</table>
		</div>
	);
};
export default CountryTable;
