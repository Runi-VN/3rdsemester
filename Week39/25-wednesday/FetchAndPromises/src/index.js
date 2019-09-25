import 'bootstrap/dist/css/bootstrap.css'
//import jokes from "./jokes";

/* new way */
window.onload = function () {
    let jokeID;
    let joke;
    let reference;
    let returnString;

    setInterval(function fetchJoke() {
        fetch('https://studypoints.info/jokes/api/jokes/random') //notice changed url
            .then(function (response) {
                return response.json();
            })
            .then(function (data) {
                //assumes joke object is consistent
                jokeID = "<li> ID: " + data.id + "</li>";
                joke = "<li> JOKE: " + data.joke + "</li>";
                reference = "<li> REFERENCE: " + data.reference + "</li>";
                returnString = "<ul>" + jokeID + joke + reference + "</ul>";
            });

        return fetchJoke;
    }(), 3600000); //self-invocate once, then run every hour


    document.getElementById("btnJoke").addEventListener("click", function () {
        document.getElementById("myDiv").innerHTML = returnString;
    })
}

/*Add the necessary event handlers, so when pressing each of the “hearts”, 
    it will write the  message North, South, East or West respectively.*/
document.getElementById("svgContainer").addEventListener("click", function (event) {
    document.getElementById("svgOutput").innerHTML = event.target.parentElement.id;
    /*
    This is done by bubbling, to challenge myself.
    For any element within my _svgContainer_ I add an EventListener.
    Normally event.target equals the actual element ID (of the <RECT>-tag), such as "rect2816".
    However I need to grab the ID of the <g>-tag, which is the parent element. (NOT parent node)
    Only issue with this solution is that it also grabs the div-name if you click whitespace.
    */

});



/* old way */

// document.getElementById("btnJoke").addEventListener("click", function () {
//     fetch('https://studypoints.info/jokes/api/jokes/random')
//         .then(function (response) {
//             return response.json();
//         })
//         .then(function (data) {
//             //assumes joke object is consistent
//             let jokeID = "<li> ID:" + data.id + "</li>";
//             let joke = "<li> JOKE: " + data.joke + "</li>";
//             let reference = "<li> REFERENCE: " + data.reference + "</li>";
//             let returnString = "<ul>" + jokeID + joke + reference + "</ul>";
//             document.getElementById("myDiv").innerHTML = returnString;
//         });
// })