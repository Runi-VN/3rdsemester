import 'bootstrap/dist/css/bootstrap.css'


let output = document.getElementById("output");
let url = 'http://localhost:3333/api/';

function fetchWithErrorCheck(res) {
    if (!res.ok) {
        return Promise.reject({ status: res.status, fullError: res.json() })
    }
    return res.json();
}

// fetch('https://swapi.co/api/people/999999999999')
// .then(res => fetchWithErrorCheck(res))
// .then(data => console.log(data.name))
// .catch(err => {
//     if (err.status) {
//     err.fullError.then(e => {
//         output.innerHTML = "Error:<br><br>" + generateTable(e);
//         console.log(e.detail)
//     })
// }
//     else { console.log("Network error"); }
// });


// Show all users (in a table)
document.getElementById("btnAllUsers").addEventListener("click",
    function getAllUsers() {
        fetch(url + 'users')
            .then(res => fetchWithErrorCheck(res))
            .then(data => {
                console.log('getAllUsers: ' + data);
                output.innerHTML = data.map((e, i, a) => generateTable(e, i, a)).join("");
            })
            .catch(err => {
                if (err.status) {
                    err.fullError.then(e => {
                        output.innerHTML = "Error:<br><br>" + generateTable(e);
                        console.log(e.detail)
                    })
                }
                else { console.log(err); }
            });
    });

// Show a single user, given an ID
// Add a new User
// Edit an existing user
// Delete an existing user

/**
 * JSON and Javascript uses key:value pairs {key:value} 
 * Java uses fields as key and their values as value {fied:value}
 * 
 * So the following java field:
 * 
 * private int age = 1;
 * 
 * translates into the following JSON:
 * {"key" : value}
 * {"age": 1}
 * 
 * This is important for understanding the two for..in loops in the method.
 * 
 * First we get all keys (fields) for headers
 * 
 * Then we get all values (field values) for table-data using element[key].
 * 
 * The table is meant to be able to take arrays and single objects.
 * 
 * The way it handles this is by adding two if-statement-guards,
 * one at the beginning and one at the end.
 * 
 * It is important to notice the order of the guard statements.
 * 
 * 
 * This table is made with thanks to github.com/HrBjarup.
 * 
 * @param {*} element 
 * @param {*} index 
 * @param {*} array 
 */
function generateTable(element, index, array) {
    let resultString = ""; //initialize as String

    //First pass-through
    if (index === 0 || typeof index === "undefined") //Index 0 or doesn't exist (single object)
    {
        //setup table
        resultString += '<table><thead>';
        //setup table-header with keys ("fields" of a Java-object)
        for (const key in element) {
            if (object.hasOwnProperty(key)) {
                resultString += '<th>' + key + '</th>';
            }

        }
        resultString += '</thead>'
    }
    //generate rows of the keys' values
    resultString += '<tr>';
    for (const key in element) {
        console.log("__FOR " + index + " OF " + array);
        console.log('KEY: ' + key);
        console.log('ELEMENT: ' + element);
        console.log('VALUE: ' + element[key]);
        if (object.hasOwnProperty(key)) {
            resultString += '<td>' + element[key] + '</td>';

        }
    }
    resultString += '</tr>';

    //Final pass-through
    if (typeof array === "undefined" ||
        typeof index === "undefined" ||
        index === array.length - 1) //if array/index is undefined (single object) or we are at the final index.
    {
        resultString += '</table>';
    }
    return resultString;
}