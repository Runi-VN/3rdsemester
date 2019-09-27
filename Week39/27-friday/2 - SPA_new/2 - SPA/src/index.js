import 'bootstrap/dist/css/bootstrap.css'

let output = document.getElementById("output");
let addOutput = document.getElementById("addOutput");
let deleteOutput = document.getElementById("deleteOutput");
let url = 'http://localhost:8080/jpareststarter/api/person/';

window.onload = getAllUsers; //ensures table is generated on load
//document.addEventListener("DOMContentLoaded", tBodyListener), false; //adds listener after load

function fetchWithErrorCheck(res) {
    if (!res.ok) {
        return Promise.reject({ status: res.status, fullError: res.json() })
    }
    return res.json();
}

// Get and add all users to table (Exercise 1)
function getAllUsers() {
    output.innerHTML = ""; //clears table 
    //(not necessary but I like the table-generation-animation-blink)
    fetch(url + 'all/week2')
        .then(res => fetchWithErrorCheck(res))
        .then(data => {
            output.innerHTML = data.all.map((e, i, a) => generateTable(e, i, a)).join("");
        })
        .catch(err => {
            if (err.status) {
                err.fullError.then(e => {
                    output.innerHTML = "<p>Error:</p><br><br>" + generateTable(e);
                    console.log(e.detail)
                })
            }
            else { console.log(err); }
        })
};

// Exercise 2
document.getElementById("btnAllUsers").addEventListener("click", getAllUsers);

// Add a new Person (Exercise 3)
document.getElementById("btnAddPerson").addEventListener("click",
    function addUser() {
        let options = makeOptions("POST");
        fetch(url + "add", options)
            .then(res => fetchWithErrorCheck(res))
            .then(data => {
                addOutput.innerHTML = "<p>Success</p>";
            })
            .catch(err => {
                if (err.status) {
                    err.fullError.then(e => {
                        addOutput.innerHTML = "<p>Error:</p>" + generateTable(e);
                        console.log(e.detail)
                    })
                }
                else { console.log(err); }
            });
    });

//Exercise 4
//Cannot be loaded before DOM because element doesn't exist
document.addEventListener('click', function (e) {
    //console.log(e.target.classList);
    if ((e.target.classList.contains("btnDelete"))) {
        deleteUser(e.target.id)
        setTimeout(getAllUsers, 100); //AJAX doesn't work for me (see slack), this "works" tho
    } else if ((e.target.classList.contains("btnEdit"))) {
        let id = parseInt(e.target.id);
        editUser(id)
        setTimeout(getAllUsers, 100); //AJAX doesn't work for me (see slack), this "works" tho
    }
});

//Exercise 4.1 - DELETE
function deleteUser(id) {
    let options = makeOptions("DELETE");
    fetch(url + id + "/delete", options)
        .then(res => fetchWithErrorCheck(res))
        .then(data => {
            deleteOutput.innerHTML = "<p>Success</p>";
        })
        .catch(err => {
            if (err.status) {
                err.fullError.then(e => {
                    deleteOutput.innerHTML = `<p>Error:</p> ${JSON.stringify(e)}`;
                    console.log(e.detail)
                })
            }
            else { console.log(err); }
        });
}

//Exercise 4.2 - EDIT
function editUser(id) {
    let options = makeOptions("PUT", id);
    fetch(url + "edit", options)
        .then(res => fetchWithErrorCheck(res))
        .then(data => {
            deleteOutput.innerHTML = "<p>Success</p>"; //no data
        })
        .catch(err => {
            if (err.status) {
                err.fullError.then(e => {
                    deleteOutput.innerHTML = `<p>Error:</p> ${JSON.stringify(e)}`;
                    console.log(e.detail)
                })
            }
            else console.log(err); 
        });
};

// // function tBodyListener() {
// //     document.getElementById("tableTbody").addEventListener("click", function (event) {
// //         console.log(event.target);
// //     })
// // }

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
        resultString += '<th>Options</th>';
        resultString += '</thead>'
    }
    //generate rows of the keys' values
    resultString += '<tbody id="tableTbody"><tr>';
    let id;
    for (const key in element) {
        if (key === 'id') id = element[key]; //first element value
        // console.log("__FOR " + index + " OF " + array);
        // console.log('KEY: ' + key);
        // console.log('ELEMENT: ' + element);
        // console.log('VALUE: ' + element[key]);
        if (element.hasOwnProperty(key)) {
            resultString += '<td>' + element[key] + '</td>';
        }

        if (key === 'phone')//last element value
        {
            resultString += `<td><a href="#" class="btnDelete" id="${id}">delete</a> `
                + `/ <a href="#" class="btnEdit" id="${id}">edit</a></td>`;
        }

    }
    //resultString += '<td><a href="#" class="btnDelete" id="${id}">delete</a></td>';
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

function makeOptions(http_method, editId) {
    let _firstname = document.getElementById("input-fname").value;
    let _lastName = document.getElementById("input-lname").value;
    let _phone = document.getElementById("input-phone").value;

    var userInfo = {
        id: null,
        firstName: _firstname,
        lastName: _lastName,
        phone: _phone
    }

    let options = {
        method: http_method,
        headers: {
            "Content-type": "application/json"
        }
    }
    
    
    //If we are editing we need ID
    if (typeof editId === "number") {
        userInfo.id = editId;
    }
    //add userInfo to options
    options.body = JSON.stringify(userInfo);

    return options;
}

