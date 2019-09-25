//1) Using existing functions that takes a callback as an argument

console.log("Declare a JavaScript array and initialize it with some names (Lars, Jan, Peter, Bo, Frederik etc.). Use the filter method to create a new array with only names that contains the letter 'a'.");

let nameArray = ["Lars", "Jan", "Peter", "Bo", "Frederik",
    "Arne", "Knud", "Pernille", "Zebra"];
let nameCheck = nameArray.filter(name => name.includes('a'));
console.log(nameCheck);

console.log("Use the names-array created above, and, using its map method, create a new array with all names reversed.");

let reverseNames = nameArray.map(name =>
    name.split('').reverse().join(''));
//Basically turns each name-element into an char-array(?), 
//uses the array-function reverse() and then joins the chars into Strings again.
console.log(reverseNames);

//2) Implement user defined functions that take callbacks as an argument

console.log("a: Implement your own filter method");
function myFilter(array, callback) {
    return callback(array);
}

function filterNameArray(array) {
    let filteredNames = [];
    array.forEach(element => {
        if (element.includes('a')) filteredNames.push(element);
    });
    return filteredNames;
}

// // function filterNameArray(currentValue, index, arr, thisValue) {



// //     if (arr[currentValue].toLowerCase() ==='a') return arr;
// //     return filterNameArray(++currentValue, ++index, arr, ++thisValue)
// // }
console.log(myFilter(nameArray, filterNameArray));


console.log("b: Implement your own map method");
function myMap(array, callback) {
    return callback(array);
}

function mapNameArray(array) {
    let mappedArray = [];
    array.forEach(element => {
        mappedArray.push(element.split('').reverse().join(''));
    });
    return mappedArray;
}
console.log(myMap(nameArray, mapNameArray));

//3) Using the Prototype property to add new functionality to existing objects
console.log("Create a new filter function as sketched below");

Array.prototype.myFilter = myFilter2;

function myFilter2(callback) {
    let filteredNames = [];

    for (let index = 0; index < this.length; index++) {
        let element = this[index];
        if (callback(element)) filteredNames.push(element);
    }
    return filteredNames;
}

// function filterByLetter() {
//     return this.element.includes('a');
// }
let newFilterNameArray = nameArray.myFilter(a => a.includes('a'));
console.log(newFilterNameArray);

console.log("Create a new map function as sketched below");
Array.prototype.myMap = mapReverse;

function mapReverse(callback) {
    let mappedNames = [];

    for (let index = 0; index < this.length; index++) {
        let element = this[index];
        let mappedElement = (callback(element));
        mappedNames.push(mappedElement);
    }
    return mappedNames;
}
let newMappedArray = nameArray.myMap(a => a.split('').reverse().join(''));
console.log(newMappedArray);

//4) Getting really comfortable with filter and map
//Use map + a sufficient callback to map numbers into

//this array: [4, 8, 15, 21, 11]
//difference:([3, 5, 10, 11, 0];)
var numbers = [1, 3, 5, 10, 11];

// let result = numbers.map((element, numbers, difference) => {
//     console.log(element);
//     return element += difference[difference.indexOf(element)];
// })

//Every number (except the last) is added up with the following. (i1+i2, i+2+3,...)
function newMyMap(element, index, array_) {
    while (index < array_.length - 1) {
        return element + array_[index + 1];
    }
    return element; //at last position, we want just the element. 
}

let result = numbers.map(newMyMap);
console.log(" a) Use map + a sufficient callback to map numbers into this array: [4, 8, 15, 21, 11]");
console.log(result);

console.log("b) Use map() to create the <a>’s for a navigation set and eventually a string like below(...)");

function linkHelper(element) {
    return '<a href=””>' + element;
}

let returnLinkString = '<nav> \n';
let nameLinks = nameArray.map(linkHelper);
returnLinkString += nameLinks.join('</a>\n') + '\n<nav>';
console.log(returnLinkString);

console.log("c) Use map()+(join + ..) to create a string, representing a two column table, for the data given below:");
var cNames = [{ name: "Lars", phone: "1234567" }, { name: "Peter", phone: "675843" }, { name: "Jan", phone: "98547" }, { name: "Bo", phone: "79345" }];

let cResult = cNames.map(function (e) {
    //return '<tr>' + '<td>' + e.name + '</td>' + '</tr>' + '<tr>' + '<td>' + e.phone + '</td>' + '</tr>';
    return '<tr>' + '<td>' + e.name + '</td>' + '<td>' + e.phone + '</td>' + '</tr>';
})

function generateNameString(array) {
    let returnNameString = "<table>\n";
    array.forEach(element => {
        returnNameString += element;
    });
    return returnNameString + '\n</table>';
}
console.log(generateNameString(cResult));

console.log("d) Create a single html-file and test the two examples given above. (OUTCOMMENTED)");
// // window.addEventListener('DOMContentLoaded', function () {
// //     document.getElementById("myList").innerHTML = returnLinkString;
// // });

// // window.addEventListener('DOMContentLoaded', function () {
// //     document.getElementById("myTable").innerHTML = generateNameString(cResult);
// // });

// // console.log("e) Add a button with a click-handler and use the filter method to find only names containing the letter ");
// // document.getElementById("btn").addEventListener("click", function (e) {
// //     document.getElementById("myTable").innerHTML = generateNameString(nameCheck);
// // }); //not going to do this the hard way lol

//5. reduce
console.log("a) Use join to create a single string from all, with names: comma-, space. and  # - separated.");
var all = ["Lars", "Peter", "Jan", "Bo"];
var allJoin = all.join(" ");
console.log(allJoin);
var allJoin = all.join(", ");
console.log(allJoin);
var allJoin = all.join("#");
console.log(allJoin);
// var allJoin = all.join('#' + ' ' + ', '); //shit dont work 
// console.log(allJoin);

console.log("b) Given this array: var numbers = [2, 3, 67, 33] SUM IT ALL AND RETURN 105 LOL");
var numbers = [2, 3, 67, 33];
let reduceResult = numbers.reduce((a, b) => { return a + b });
console.log(reduceResult);

console.log("Create a reducer callback that, using the Array’s  reduce() method, will return the average age of all members (25 for the provided array).");
var members = [
    { name: "Peter", age: 18 },
    { name: "Jan", age: 35 },
    { name: "Janne", age: 25 },
    { name: "Martin", age: 22 }]

let memberReduce = members.reduce((total, element, index, array) => {
    total += element.age;
    if (index === array.length - 1) {
        return total / array.length;
    }
    return total;
}, 0); //need the intialvalue....

console.log(memberReduce);

console.log("d) Imagine you were to create a system that could count votes for the presidential election in USA.");
var votes = ["Clinton", "Trump", "Clinton", "Clinton", "Trump", "Trump", "Trump", "None"];
let voteReduce = votes.reduce((total, vote) => {
    if (!total[vote]) total[vote] = 1; //if targeted vote doesn't exist, initialize as key:value (object) with value 1
    else total[vote] += 1; //if targeted vote exists, add 1 to its value
    return total;
}, {}); //https://www.freecodecamp.org/news/reduce-f47a7da511a9/ || Creating a Tally with the Reduce Method In JavaScript​
//a["clinton"] = 1;
console.log(voteReduce);

//8 Reusable Modules with Closures 


console.log("1) Implement and test the Closure Counter Example from w3schools: (OUTCOMMENTED)");
// var add = (function () {
//     var counter = 0;
//     return function (number) { counter += number; return counter; }
// })();

// document.getElementById("buttonCount").addEventListener("click", function () {
//     document.getElementById("demo").innerHTML = add(5);
// });

console.log("2) Implement a reusable function using the Module pattern that should encapsulate information about a person (name, and age) and returns an object with the following methods:");

console.log("This task has been done before...");
var personModule = (function () {
    //private
    var age;
    var name;
    function getInfo() {
        return name + ", " + age;
    }
    function setAge(age1) {
        age = age1;
        return age;
    }
    function setName(name2) {
        name = name2;
        return name;
    }
    return {
        //public
        setAge: setAge,
        setName: setName,
        getInfo: getInfo
    }
    /*
    Above is the module revealer pattern. 
    The methods could also be in the public part, to be a regular module pattern
    */


})();
let testPerson = personModule;
testPerson.setAge(45);
testPerson.setName("Peter");
console.log(testPerson.getInfo());