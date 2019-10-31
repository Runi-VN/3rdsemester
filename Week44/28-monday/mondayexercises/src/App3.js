import React from "react"; //3.props1.a
import PropTypes from "prop-types";
import { names } from "./file2";

//3.props1.b
function Welcome(props) {
  return <h1>Hello, {props.name}</h1>;
}

Welcome.propTypes = {
  name: PropTypes.string.isRequired //3.props1.d
};

//3.props2.b
function WelcomePerson(props) {
  return (
    <p>
      {props.person.firstName} | {props.person.lastName} | {props.person.email}
    </p>
  );
}
//3.props2.d -- I guess it's WelcomePerson, not Welcome
WelcomePerson.propTypes = {
  person: PropTypes.shape({
    firstName: PropTypes.string.isRequired,
    lastName: PropTypes.string.isRequired,
    email: PropTypes.string.isRequired
  })
};

//Extra -- works.
// function WelcomePersonDestructuring({ person }) {
//   return (
//     <p>
//       {person.firstName} | {person.lastName} | {person.email}
//     </p>
//   );
// }

export default function App() {
  return (
    <div>
      <Welcome name="Sara" />
      <Welcome name="Cahal" />
      <Welcome name="Edite" />
      {/*<Welcome /> //Error */}
      {/*<Welcome name={45} /> //Error */}
      {/*3.props2.c*/}
      <WelcomePerson person={names[0]} />
      <WelcomePerson person={names[1]} />
      <WelcomePerson person={names[2]} />
      {/* 3.props2.d */}
      <p>_______3.props2.d_______</p>
      <WelcomePerson
        person={{
          firstName: "Kurt",
          lastName: "Wonnegut",
          email: "k@wonnegut.dk"
        }}
      />
      {/*Works*/}
      <WelcomePerson
        person={{
          firstName: "Kurt",
          lastName: "Wonnegut",
          email: "k@wonnegut.dk",
          friends: ["Kim", "Janne"]
        }}
      />
      {/*Works*/}
      {/* <WelcomePerson
        person={{ firstName: "Jane", email: "j@wonnegut.dk", phone: "12345" }}
      /> */}
      {/* FAILS */}
      {/* <WelcomePerson person={{ firstName: "Jane" }} /> */}
      {/* FAILS */}
      {/* 3.props2.e */}
      <p>_______3.props2.e_______</p>
      {names.map((person, index) => (
        <WelcomePerson person={person} key={index} />
      ))}

      {/* -- Extra -- */}
      {/* <WelcomePersonDestructuring person={names[0]} />
      <WelcomePersonDestructuring person={names[1]} />
      <WelcomePersonDestructuring person={names[2]} /> */}
    </div>
  );
}
