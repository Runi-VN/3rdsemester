document.getElementById("buttons").addEventListener("click", function () {
    document.getElementById("display").innerHTML += event.target.innerText;
});

document.getElementById("calculate").addEventListener("click", function () {
    event.stopPropagation();
    let display = document.getElementById("display").innerHTML;
    document.getElementById("display").innerHTML = eval(display);
});

//
//document.getElementById("outer").addEventListener("click", function (div) {
//   document.getElementById("output").innerHTML = "Hello from " + this.id + ". TARGET: " + div.target.id;
//});