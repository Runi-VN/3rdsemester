import 'bootstrap/dist/css/bootstrap.css'


let output = document.getElementById("output");
let url = 'http://localhost:3333/api/users/';

// Show all users (in a table)
document.getElementById("btnAllUsers").addEventListener("click",
    function getAllUsers() {
        fetch(url)
            .then(res => fetchWithErrorCheck(res))
            .then(data => {
                output.innerHTML = data.map((e, i, a) => generateTable(e, i, a)).join("");
            })
            .catch(err => {
                if (err.status) {
                    err.fullError.then(e => {
                        output.innerHTML = "<p>Error:</p><br><br>" + generateTable(e);
                        console.log(e.detail)
                    })
                }
                else { console.log(err); }
            });

    });

// Show a single user, given an ID
document.getElementById("btnSingleUser").addEventListener("click",
    function getSingleUser() {
        let id = document.getElementById("userID").value;
        fetch(url + id)
            .then(res => fetchWithErrorCheck(res))
            .then(data => {
                output.innerHTML = generateTable(data);
            })
            .catch(err => {
                if (err.status) {
                    err.fullError.then(e => {
                        output.innerHTML = "<p>Error:</p><br><br>" + generateTable(e);
                        console.log(e.detail)
                    })
                }
                else { console.log(err); }
            });
    });

// Add a new User
document.getElementById("btnAddUser").addEventListener("click",
    function addUser() {
        let options = makeOptions("POST");
        fetch(url, options)
            .then(res => fetchWithErrorCheck(res))
            .then(data => {
                output.innerHTML = "<p>Success:</p>" + generateTable(data);
            })
            .catch(err => {
                if (err.status) {
                    err.fullError.then(e => {
                        output.innerHTML = "<p>Error:</p>" + generateTable(e);
                        console.log(e.detail)
                    })
                }
                else { console.log(err); }
            });
    });

// Edit an existing user
document.getElementById("btnEditUser").addEventListener("click",
    function editUser() {
        let id = document.getElementById("input-id").value;
        let options = makeOptions("PUT");
        fetch(url + id, options)
            .then(res => fetchWithErrorCheck(res))
            .then(data => {
                output.innerHTML = "<p>Success:</p>"; //no data
            })
            .catch(err => {
                if (err.status) {
                    err.fullError.then(e => {
                        output.innerHTML = "<p>Error:</p>" + generateTable(e);
                        console.log(e.detail)
                    })
                }
                else { console.log(err); }
            });
    });


// Delete an existing user
document.getElementById("btnDeleteUser").addEventListener("click",
    function deleteUser() {
        let id = document.getElementById("deleteUserId").value;
        let options = makeOptions("DELETE");
        fetch(url + id, options)
            .then(res => fetchWithErrorCheck(res))
            .then(data => {
                output.innerHTML = "<p>Success:</p>" + generateTable(data);
            })
            .catch(err => {
                if (err.status) {
                    err.fullError.then(e => {
                        output.innerHTML = "<p>Error:</p>" + generateTable(e);
                        console.log(e.detail)
                    })
                }
                else { console.log(err); }
            });
    });


//outside assignment scope
// function clearTable() {
//     if (output.childNodes.length != 0) {
//         document.getElementById("btnClear").style.display = "";
//     }
//     else {
//         document.getElementById("btnClear").style.display = "none";
//     }
// };

//clear table data (no function...)
document.getElementById("btnClear").addEventListener("click", function clearTable() {
    output.innerHTML = "";
});

function fetchWithErrorCheck(res) {
    if (!res.ok) {
        return Promise.reject({ status: res.status, fullError: res.json() })
    }
    return res.json();
}

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
        resultString += '<table class="table table-striped table-dark"><thead>';
        //setup table-header with keys ("fields" of a Java-object)
        for (const key in element) {
            if (element.hasOwnProperty(key)) {
                resultString += '<th>' + key + '</th>';
            }

        }
        resultString += '</thead>'
    }
    //generate rows of the keys' values
    resultString += '<tbody><tr>';
    for (const key in element) {
        // console.log("__FOR " + index + " OF " + array);
        // console.log('KEY: ' + key);
        // console.log('ELEMENT: ' + element);
        // console.log('VALUE: ' + element[key]);
        if (element.hasOwnProperty(key)) {
            resultString += '<td>' + element[key] + '</td>';

        }
    }
    resultString += '</tr></tbody>';

    //Final pass-through
    if (typeof array === "undefined" ||
        typeof index === "undefined" ||
        index === array.length - 1) //if array/index is undefined (single object) or we are at the final index.
    {
        resultString += '</table>';
    }
    return resultString;
}

function makeOptions(http_method) {
    let _age = document.getElementById("input-age").value;
    let _name = document.getElementById("input-name").value;
    let _gender;
    let _email = document.getElementById("input-email").value;
    if (document.getElementById("gender-m").checked) {
        _gender = "male"
    } else if (document.getElementById("gender-f").checked){
        _gender = "female";
    }

    var userInfo = {
        id: null,
        age: _age,
        name: _name,
        gender: _gender,
        email: _email
    }

    let options = {
        method: http_method,
        headers: {
            "Content-type": "application/json"
        }
    }

    if (http_method ==="DELETE")
    {
        return options;
    }

    let editId = document.getElementById("input-id").value;
    if (typeof editId === "number") {
        userInfo.id = editId;
    }
    //add userInfo to options
    options.body = JSON.stringify(userInfo);

    return options;
}