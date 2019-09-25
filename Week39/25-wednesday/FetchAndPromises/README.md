# Simple template to create SPA's with plain JavaScript using Babel, Webpack and Webpacks devserver

## Getting started

- If not alredy done, install nodejs and a sufficient JavaScript Editor (we suggest vs-code)
- Clone this project
- In the folder it was cloned into, type npm install
- In the folder it was cloned into (if you have installed vs-code) type "code ." to open vs-code
- In the folder it was cloned into type npm start, to run the project via Webpacks development server

### Exercises 

#### Finding individual jokes/Adding new jokes
![](https://i.imgur.com/qAwSZtu.png)  

##### HTML code:
```html
<label for="jokeID">Input joke ID</label>
    <input id="jokeID" type="number"></input>
    <button type="button" id="btnGetJoke">Get Joke</button>
    <p id="jokeOutput"></p>
    <br>
    <label for="newJoke">Input new joke</label>
    <input id="jokeInput" type="text"></input>
    <button type="button" id="btnAddJoke">Add Joke</button>
    <p id="newJokeOutput"></p>
```  

##### JavaScript code:  
```javascript
import jokes from "./jokes";

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
```
