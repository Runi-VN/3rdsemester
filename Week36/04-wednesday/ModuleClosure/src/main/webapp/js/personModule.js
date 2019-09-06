/* 
 Implement a reusable function using the Module pattern that should encapsulate information about a person (name, and age) and returns an object with the following methods:
 setAge
 setName
 getInfo (should return a string like Peter, 45)
 */

var person = function () {
    var name;
    var age;

    this.setName = function setName(Name) {
        name = Name;
    };

    this.setAge = function setAge(Age)
    {
        age = Age;
    };

    this.getInfo = function getInfo() {
        return name + ", " + age;
    };
};
