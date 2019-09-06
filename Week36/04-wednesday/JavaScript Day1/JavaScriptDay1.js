console.log("###TASK 1 - JavaScript functions and Callbacks###");
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

console.log("###TASK 2 - Callbacks (with map, filter and forEach)###");
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

console.log("###TASK 3 - Asynchronous Callbacks###");
/* 
When you execute something synchronously, you wait for it to finish before moving on to another task. 
When you execute something asynchronously, you can move on to another task before it finishes.
*/
console.log("Exercise 1");
console.log("----------");
console.log("  @Expected: a,d,f,e(1s),b(2s)");
console.log("Exercise 2");
console.log("----------");
var msgPrinter = function (msg, delay) {
    setTimeout(function () {
        console.log(msg);
    }, delay);
};
console.log("  @RESULT: ");
// console.log("aaaaaaaaaa");
// msgPrinter("bbbbbbbbbb", 2000);
// console.log("dddddddddd");
// msgPrinter("eeeeeeeeee", 1000);
// console.log("ffffffffff");
console.log("Correct \n(Removed msgPrinter-output, otherwise it messed up future code\n");

console.log("###TASK 4 -  this and constructor functions###");
console.log("Exercise 1 (see notes)");
console.log("----------");

// // // // function Person(name) { //an constructor function with 'name' parameter
// // // //     this.name = name;
// // // //     console.log("Name: " + this.name);
// // // //     setTimeout(function () {
// // // //         console.log("Hi  " + this.name); //Explain this
// // // //     }, 2000);
// // // // }

// // // //   Person("Kurt Wonnegut"); //an instance
/*
This calls the Person function (constructor) on a global scale (is not tied to a var), which then:
sets local (now global) parameter name to the parameter.
Prints the "local" parameter.
Runs another function, but as it has a timeout, the "Im global"-message is logged first.
After 2s the inner-function (with its own scope) is posted, but it does not have any >name< within its own scope.
*/
// // // // console.log("I'm global: " + name);
//The Person function is "global" and sets name for the whole document.
//Does not run if Person is not run first.

console.log("Exercise 2 (see notes)");
console.log("----------");

// // // var p = new Person("Kurt Wonnegut"); //Create an instance using the constructor function
// // // //console.log("I'm global: "+ name);  //What’s different ?
/*
The function now has it own scope within the variable p.
That means 'name' now no longer is global and line 207 can't run.
We still get "hi undefined" because we have an inner-inner scope
*/

console.log("Exercise 3 (see notes)");
console.log("----------");

//Store a reference to the outer this
// // // // function Person(name){
// // // //     this.name = name;
// // // //     var self = this;
// // // //     console.log("Name: "+ this.name);
// // // //     setTimeout(function(){
// // // //       console.log("Hi  "+self.name);  
// // // //     },2000);
// // // //   }

// // // //   var p = new Person("Kurt Wonnegut");
/*
now the inner-inner function (setTimeout) has access to its parent _variable_ "self"
and we now get "hi <name>" instead of "hi undefined"
*/


//Using the bind(..) function
// // // // function Person(name) {
// // // //     this.name = name;
// // // //     console.log("Name: " + this.name);
// // // //     setTimeout(function () {
// // // //         console.log("Hi  " + this.name);
// // // //     }.bind(this), 2000);
// // // // }


// // // // var p = new Person("Kurt Wonnegut");
/*
Same output as above, but using bind.
Bind basically grants access to the specified functions initial object/parameters.
In this case we bind on "this", or "Person".
*/

console.log("Exercise 4 (see notes)");
console.log("----------");

// // // // var greeter = function(){
// // // //     console.log(this.message);
// // // //   };
// // // //   var comp1 = { message: "Hello World" };
// // // //   var comp2 = { message: "Hi" };

// // // //   var g1 = greeter.bind(comp1 );//We can store a reference, with a specific “this” to use
// // // //   var g2 = greeter.bind(comp2 );//And here another “this”
// // // //   setTimeout(g1,500);
// // // //   setTimeout(g2,1000);
/*
we add a greeter-function which simply clg's _this_ message.
we add two variables, comp1/comp2 with two different messages.
we add two new variables, g1/g2. 
These variables are a combination of the greeter-function and the relative comp-variable.

Using bind, our g1/g2-variables now have an instance of the greeter function,
which have access to a BOUND message from the comp1/comp2-variables.

the g1/g2-variable is called with a timeout of 500/1000, displaying access to the greeter function and their relative message.
*/

console.log("###TASK 5 -  JavaScript Objects###");
console.log("Exercise 1");
console.log("----------");

var myObject = {
    name: "Johnson",
    birthday: "12-12-2012",
    hobby: "Woodshop",
    email: "Johnson@wood.com"
};
var getObject = (object) => {
    for (prop in object) {
        console.log(prop, myObject[prop]);
    }
}
getObject(myObject);
console.log("---LETS REMOVE HOBBY---");
delete myObject.hobby;
getObject(myObject);
console.log("---LETS ADD PHONENUMBER---");
myObject.phone = 88888888;
getObject(myObject);

console.log("Exercise 2");
console.log("----------");

function Person(firstName, lastName, age) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.age = age;
    var self = this;
    // this.getDetails = function (this) {
    //     return getObject(this);
    // }.bind(getObject());
    this.getDetails = function () {
        for (prop in self) {
            console.log(prop, self[prop]);
        }
    }
}
var James = new Person("Jamesy", "James", 19);
James.getDetails(); //playing around, not supposed to return getDetails as a prop.

console.log("###TASK 6 -  Reusable Modules with Closures###");
console.log("Exercise 1");
console.log("----------");

console.log("Not in this repository");