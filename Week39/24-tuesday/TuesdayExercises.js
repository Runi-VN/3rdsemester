//1) Using existing functions that takes a callback as an argument

console.log("Declare a JavaScript array and initialize it with some names (Lars, Jan, Peter, Bo, Frederik etc.). Use the filter method to create a new array with only names that contains the letter 'a'.");

let nameArray = ["Lars", "Jan", "Peter", "Bo", "Frederik",
    "Arne", "Knud", "Pernille", "Zebra"];

console.log(nameArray.filter(name => name.includes('a')));

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