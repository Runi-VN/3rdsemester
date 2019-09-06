var cars = [
    {id: 1, year: 1997, make: 'Ford', model: 'E350', price: 3000},
    {id: 2, year: 1999, make: 'Chevy', model: 'Venture', price: 4900},
    {id: 3, year: 2000, make: 'Chevy', model: 'Venture', price: 5000},
    {id: 4, year: 1996, make: 'Jeep', model: 'Grand Cherokee', price: 4799},
    {id: 5, year: 2005, make: 'Volvo', model: 'V70', price: 44799}
];

//https://www.valentinog.com/blog/html-table/

//function generateTableHead(table, data) {
//    let thead = table.createTHead();
//    let row = thead.insertRow();
//    for (let key of data) {
//        let th = document.createElement("th");
//        let text = document.createTextNode(key);
//        th.appendChild(text);
//        row.appendChild(th);
//    }
//}
//function generateTable(table, data) {
//    for (let element of data) {
//        let row = table.insertRow();
//        for (let key in element) {
//            let cell = row.insertCell();
//            let text = document.createTextNode(element[key]);
//            cell.appendChild(text);
//        }
//    }
//}
///**
// * 
// * @param {String} htmlTableName - name of htmlTableElement to populate
// * @param {Array} tableData collection to write to table
// * @returns {undefined} an HTML table populated in the DOM
// */
//function masterGenerate(htmlTableName, tableData) {
//    let table = document.getElementById(htmlTableName);
//    let data = Object.keys(tableData[0]);
//    generateTable(table, tableData); //table first to ensure proper cells, tbody and rows
//    generateTableHead(table, data); //then thead
//}
//masterGenerate("carTable", cars);

/*
 The above was much smarter but the task requires HTML lol 
 */

// TABLE HELPER FUNCTIONS

// START OF TABLE CREATION - USE THE FUNCTIONS IN ORDER:
// ADD THEM TO A STRING LIKE += IN THE ORDER SEEN HERE. 

// 1)
// MAKES A TABLE HEADER. HAND IT AN ARRAY OF VALUES FOR A TABLE HEADER ROW.
function tableHeader(array) {
    let returnString = "<table class=\"table\"><thead>";
    array.forEach(element => returnString += "<th scope=\"col\">" + element + "</th>");
    return returnString + "</thead><tbody>";
}

// 2)
// MAKES A SINGLE TABLE ROW. HAND IT AN ARRAY OF VALUES FOR A TABLE BODY ROW.
function tableRow(element) {
    let returnString = "<tr>";
    //Object.values(array).forEach(element => returnString += "<td>" + element + "</td>");
    //array.forEach(element => returnString += "<td>" + Object.values(element) + "</td>");
    console.log(element);
    Object.values(element).forEach(element => returnString += "<td>" + element + "</td>");
    //element.forEach(element => returnString += "<td>" + element + "</td>");
    //array.forEach(element => returnString += "<td>" + Object.values(array[element]) + "</td>");
    return returnString + "</tr>";
}

// 3)
// END OF TABLE
function endOfTable() {
    return "</tbody></table>";
}

// END OF TABLE HELPER FUNCTIONS

function generateTable(array) {
    let htmlTableString = tableHeader(Object.keys(array[0]));

    array.forEach(element => htmlTableString += tableRow(element));

    htmlTableString += endOfTable();

    return htmlTableString;
//    //Object.keys(cars[0]), Object.values(cars)
//    let htmlTableString = tableHeader(Object.keys(array[0]));
//    //array.forEach(element => htmlTableString += tableRow(element));
//    for (var elem in array) {
//        htmlTableString += tableRow(array);
//    }
//    htmlTableString += endOfTable();
//    return htmlTableString;
}
document.getElementById("insertTable").innerHTML =
        generateTable(cars);

document.getElementById("btnPrice").addEventListener("click", function (event) {
    //event.preventDefault();
    let price = document.getElementById("price").value;
    let cheapCars = cars.filter(car => car.price < price);
    document.getElementById("insertTable").innerHTML =
    generateTable(cheapCars);
});
