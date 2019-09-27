
document.getElementByID(

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
    let _name = document.getElementById("input-name").value;
    let _email = document.getElementById("input-email").value; //is actually occupation

    var userInfo = {
        id: null,
        name: _name,
        occupation: _email
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