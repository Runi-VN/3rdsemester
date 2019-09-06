
//document.getElementById("div2").addEventListener("click", hello);
//function hello(divId) {
//    console.log("hi from div " + divId);
//}
//document.getElementById("outer").addEventListener("click", function (div) {
//    console.log("Hello from " + this.id + ". TARGET: " + div.target.id);
//});

document.getElementById("outer").addEventListener("click", function (div) {
   document.getElementById("output").innerHTML = "Hello from " + this.id + ". TARGET: " + div.target.id;
});

//function hello(divId) {
//    console.log("hi from div " + divId);
//}

//Array.from(document.getElementsByClassName("mydiv"))
//        .forEach(function (div) {
//            div.addEventListener("click", function () {
//                hello(this.id);
//            });
//        });