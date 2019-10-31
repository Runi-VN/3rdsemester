import React from "react";
import "./App.css";
import person, { males, females } from "./file2"; //2.2 + 2.6

//Exercise 2
function App() {
  let { firstName, email } = person; //2.3
  console.log('6.1: SHOULD RETURN ["Peter", "Jan", "Janne", "Sarah"]');
  console.log([...males, ...females]); //2.6
  console.log(
    '6.2: SHOULD RETURN "Peter", "Jan", "Kurt", "Helle", "Janne", "Sarah", "Tina"'
  );
  console.log([...males, firstName, "Helle", ...females, "Tina"]); //2.6
  console.log("2.7:");
  console.log(PersonV2);
  return (
    <div className="App">
      <p>
        {firstName}, {email}
      </p>{" "}
      {/*2.4*/}
    </div>
  );
}

let PersonV2 = { ...person }; //2.7
PersonV2.phone = 123456;
PersonV2.friends = [...males, ...females];

export default App;
