import 'bootstrap/dist/css/bootstrap.css'
import jokes from "./jokes";

const allJokes = jokes.getJokes().map(joke => "<li>"+joke+"</li>");
document.getElementById("jokes").innerHTML = allJokes.join("");


//Task 1 - Finding individual jokes
document.getElementById("btnGetJoke").addEventListener("click", function(){
    let id = document.getElementById("jokeID").value;
    const getJoke = jokes.getJokeById(id);
    document.getElementById("jokeOutput").innerHTML = getJoke;
});

//Task 2 - Adding new jokes
document.getElementById("btnAddJoke").addEventListener("click", function(){
    let joke = document.getElementById("jokeInput").value;
    const addJoke = jokes.addJoke(joke);
    document.getElementById("newJokeOutput").innerHTML = "Din jokes ID er: " + addJoke + " (husk minus 1 pga. 0-indekseret)";
});