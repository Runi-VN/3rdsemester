# Wednesday exercises 

### Finding individual jokes/Adding new jokes
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

### Small application to display a quote of the hour  
6) Use developer-tools in your browser and it’s network options to monitor the AJAX-request. Explain why, what you did above, is even possible, when we know the Same Origin Policy governs when/where AJAX-request can go
![](https://i.imgur.com/2AzsRR3.png)  


>The CORS mechanism lets you specify in a request that you want to retrieve a cross-origin resource (in fetch this is enabled by default). The browser adds an `Origin` header to the request, and then requests the appropriate resource. The browser only returns the response if the server returns an `Access-Control-Allow-Origin` header specifying that the origin has permission to request the resource. *In practice, servers that expect a variety of parties to request their resources (such as 3rd party APIs) set a **wildcard value** for the `Access-Control-Allow-Origin` header, allowing anyone to access that resource.*  

[Source](https://developers.google.com/web/ilt/pwa/working-with-the-fetch-api)  

So, in short, fetch by default adds our request for a cross-origin resource. Then our browser adds a `Origin`-header **[1 in image]**, and as can be seen from the response, the server has a *wildcard value* (\*) set for the `Access-Control-Allow-Origin` **[2 in image]** allowing us access as if we were origin.

