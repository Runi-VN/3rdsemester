console.log("Exercise A");
var boys = ["Peter", "lars", "Ole"];
var girls = ["Janne", "hanne", "Sanne"];
console.log(boys);
console.log(girls);

console.log("----------");
console.log("Exercise B");
var all = boys.concat(girls);
console.log(all);

console.log("----------");
console.log("Exercise C");
var commas = all.join();
var hyphens = all.join("-");
console.log(commas);
console.log(hyphens);

console.log("----------");
console.log("Exercise D");
all.push("Lone", "Gitte");
console.log(all);

console.log("----------");
console.log("Exercise E");
all.unshift("Hans", "Kurt");
console.log(all);

console.log("----------");
console.log("Exercise F");
all.shift();
console.log(all);

console.log("----------");
console.log("Exercise G");
all.pop();
console.log(all);

console.log("----------");
console.log("Exercise H");
/* 
 //The easy way
 all.splice((all.length/2)-1, 2); //start from middleOfArray-1, delete two going forward
 console.log(all);
 */

/* 
 //the harder way, the old way
 removePersons = function(name){ //standard function expression
 (...)
 };
 */

//the harder way, the current way
removePerson = (name, array) => { //arrow function expression
//need error handling
//could take an array of names to remove, instead of having to call several times
//findIndex could be used, but it uses callback, therefore a 2nd function is needed
    array.splice(array.indexOf(name), 1);
};
removePerson("Ole", all);
removePerson("Janne", all);
console.log(all);

console.log("----------");
console.log("Exercise I");
all.reverse();
console.log(all);

console.log("----------");
console.log("Exercise J");
all.sort();
console.log(all);
/*
 * It sorts unicode point values. 
 * In this case big letters alphabetically -> small letters alphabetically.
 */

console.log("----------");
console.log("Exercise K");
all.sort((a, b) => a.toLocaleLowerCase().localeCompare(b.toLocaleLowerCase()));
//sorts based on all being lowercase, but doesn't actually mutate
//takes into account non-ASCII chars (overkill)
console.log(all);

console.log("----------");
console.log("Exercise L");
var allUpperCase = all.map(e => e.toUpperCase());
console.log(allUpperCase);

console.log("----------");
console.log("Exercise M");
var allSpecial = all.filter(e => e.charAt(0) === "L" || e.charAt(0) === "l");
console.log(allSpecial);




