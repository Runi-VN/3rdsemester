console.log("###TASK 1###");
console.log("Exercise 1");
console.log("----------");

function add(n1, n2) {
    return n1 + n2;
}

var sub = function (n1, n2) {
    return n1 - n2
}

var cb = function (n1, n2, callback) {
    try {
        typeof n1 === "number"; //Will fail if n1 is undefined, or is not a number
        typeof n2 === "number"; //Will fail if n2 is undefined, or is not a number
        typeof callback === "function"; //Will fail if callback is undefined or is not a function

        return "Result from the two numbers: " + n1 + "+" + n2 + "=" + callback(n1, n2);
    } catch (e) {
        //return "An error occured, most likely your argument types. Details:" + console.log(e.log);
        return console.error(e.name + ': ' + e.message);
    }
};
console.log("Added functions");

console.log("Exercise 2");
console.log("----------");

console.log("Exercise 2.1");
console.log(add(1, 2)) //3
console.log("Exercise 2.2");
console.log(add) //Reference? Or "the function" it self
console.log("Exercise 2.3");
console.log(add(1, 2, 3)) //3. Ignores 3rd parameter
console.log("Exercise 2.4");
console.log(add(1)); //NaN - missing argument.
console.log("Exercise 2.5");
console.log(cb(3, 3, add)); //text string: 3+3=6
console.log("Exercise 2.6");
console.log(cb(4, 3, sub)); //text string: 4+3=1 (actually using plus, and not a minus. nice code)
console.log("Exercise 2.7");
console.log(cb(3, 3, add())); //error. tries to run cb with a method without parameters. would never work.
console.log("Exercise 2.8");
console.log(cb(3, "hh", add)); //text string: 3+hh=3hh

console.log("Exercise 3");
console.log("----------");
console.log(cb.toString());

console.log("Exercise 4");
console.log("----------");
var mul = function (n1, n2) {
    return n1 * n2;
}
console.log(mul.toString());
console.log(cb(3, 3, mul));
console.log(cb(6, 6, mul));
console.log(cb(9, 9, mul));

console.log("Exercise 5");
console.log("----------");
console.log(cb(10, 5, (n1, n2) => {
    return n1 / n2;
}));

console.log("###TASK 2###");
console.log("Exercise 1");
console.log("----------");
let names = ["Runi", "Lars", "Jan", "Peter", "Bo", "Frederik"];
let shortNames = names.filter(name => name.length <= 3);
console.log(shortNames);

console.log("Exercise 2");
console.log("----------");
let bigNames = names.map(name => name.toUpperCase());
console.log(bigNames);

console.log("Exercise 3");
console.log("----------");
let ex3 = function (nameArray) {
    let htmlArray = nameArray.map(name => "<li>" + name + "</li>");
    htmlArray.unshift("<ul>");
    htmlArray.push("</ul>");
    return htmlArray.join('');
}
console.log(ex3(names));

console.log("Exercise 4");
console.log("----------");
var cars = [{
        id: 1,
        year: 1997,
        make: 'Ford',
        model: 'E350',
        price: 3000
    },
    {
        id: 2,
        year: 1999,
        make: 'Chevy',
        model: 'Venture',
        price: 4900
    },
    {
        id: 3,
        year: 2000,
        make: 'Chevy',
        model: 'Venture',
        price: 5000
    },
    {
        id: 4,
        year: 1996,
        make: 'Jeep',
        model: 'Grand Cherokee',
        price: 4799
    },
    {
        id: 5,
        year: 2005,
        make: 'Volvo',
        model: 'V70',
        price: 44799
    }
];
let cars1999 = cars.filter(car => car.year > 1999);
console.log("%d cars newer than 1999: ", cars1999.length);
console.log(cars1999);

let carsVolvos = cars.filter(car => car.make === "Volvo");
console.log("%d car from Volvo: ", carsVolvos.length);
console.log(carsVolvos);

let carsCheap = cars.filter(car => car.price < 5000);
console.log("%d cars below the price of 5k: ", carsCheap.length);
console.log(carsCheap);

console.log("Exercise 4a");
console.log("----------");
let ex4a = function (array, tableName) {
    //assuming all _objects_ in array are equal
    for (var c in array) {
        var SqlString = "INSERT INTO " + tableName + " (";
        var key = Object.getOwnPropertyNames(array[c]); //returns fields, ("id, year, make, model, price")
        //console.log(Object.keys(array[c])); //similar
        SqlString += key + ") VALUES (" + Object.values(array[c]) + ");";
        console.log(SqlString);
    }
    //let sqlArray = cars.map(car =>
    //  "INSERT INTO cars (id,year,make,model,price) VALUES) ( " +
    //car.id + ", " + car.year + ", " + car.make + ", " + car.model + ", " + car.price);
}
ex4a(cars, "cars");