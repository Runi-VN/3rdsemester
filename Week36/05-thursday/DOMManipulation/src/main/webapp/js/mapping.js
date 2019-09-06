let names = ["Runi", "Lars", "Jan", "Peter", "Bo", "Frederik"];

//a
let nameToList = function (nameArray) {
    let htmlArray = nameArray.map(name => "<li>" + name + "</li>");
    htmlArray.unshift("<ul>");
    htmlArray.push("</ul>");
    document.getElementById("namelist").innerHTML = htmlArray.join('');
};
nameToList(names); //add names

//b
document.getElementById("btnAdd").addEventListener("click", function (event) {
    event.preventDefault();
    let name = document.getElementById("newname").value;
    names.push(name);
    nameToList(names);
});

//c.1
document.getElementById("btnRemoveFirst").addEventListener("click", function (event) {
    event.preventDefault();
    names.shift();
    nameToList(names);
});

//c.2
document.getElementById("btnRemoveLast").addEventListener("click", function (event) {
    event.preventDefault();
    let name = document.getElementById("newname").value;
    names.pop();
    nameToList(names);
});
