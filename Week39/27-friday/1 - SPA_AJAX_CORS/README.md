# Exercise notes
[Exercise link](https://docs.google.com/document/d/1VMx1XdbnIbJ6ik98qPywGyrRiqbQuIM2u9DpJmXCnlk/edit)  

## Exercise 1

- **Explain about the Object Model, and why it’s (very) relevant for modern Web-development**  

The Document Object Model (DOM) is a programming API for HTML. We use it to present a logical tree of HTML elements on a website.  
A good example would be a HTML `<table>`-element:  
![](https://www.w3.org/TR/WD-DOM/table.gif)  
Using this API we can now use node, parent and child relations to more easily distinguish between elements and their individual scopes.

This week we have spent a lot of time manipulating the DOM using javascript, starting with only a simple `<div>`-element and generating an entire *Single Page Application*.

DOM manipulation is also useful for giving feedback to the user. Is their input correct? Was the order succesful?

- **Explain (using an example of your own choice) about JavaScript events, and Event Bubbling**  

I think [my code](https://github.com/Runi-VN/3rdsemester/blob/master/Week39/25-wednesday/FetchAndPromises/src/index.js#L35) for Wednesdays `FetchAndPromises`-assignment [exercise 3](https://docs.google.com/document/d/1_PkGqF-1MVt0sFDR90ARJlUhQ8DsyC4L12NI6E-iWMc/edit#heading=h.wecyiqakcy9) is a perfect fit for this:

```javascript
document.getElementById("svgContainer").addEventListener("click", function (event) {
document.getElementById("svgOutput").innerHTML = event.target.parentElement.id;
```  
Every time an element within the `svgContainer` is clicked, an event is *fired* and I add a listener to it.
At first I wanted to grab this particular element with `event.target` and further grab it's `id`-property with `event.target.id`, but I found that the click actually grabs the `path`-element [(reference code)](https://raw.githubusercontent.com/Cphdat3sem2017f/StartcodeExercises/master/JS/fourHearts.svg) and not the `g`-element that I want.  

Clarification:  
For instance, `event.target.id` would return the clicked `path.id`-property: `path2820`, but this is not what I wanted! I wanted `north`.  
But I am in luck, as the HTML has a method to grab the DOM parent element, which is the `g`-element, using `event.target.parentElement.id`. This returns `north`.  

Bubbling in this example  works by adding an eventListener to the parentElement `svgContainer` which then adds an eventListener to all its own elements. The element then fires its eventlistener and the method *bubbles up*.  
The reverse of this is called *capturing* or often refered to as *trickling*. See [this](https://stackoverflow.com/a/4616704) for more.

    
- **Elaborate on how JSON or XML supports communication between subsystems, even when the subsystems are implemented on diﬀerent platforms.**  

If all data is communicated in the same way, you can parse it so that it works with your own system.  
As long as the data is sent/received in the same way, you can do whatever you want with it.

This is such a big advantage, so that from your data can sprout new systems. Think of weather apps relying on weather APIs, or how [Momondo](https://www.momondo.dk/) gets prices from all airlines and returns it sorted to the user.

I draw parallels to the TPC/IP stack where data is sent as *binary* but both the sender/receiver can understand and use the data in their own way.

- **Explain the topic AJAX and how it has changed the way modern web-applications are created**  

AJAX stands for Asynchronous JavaScript And XML.  

The idea of AJAX is to chain actions together based on *promises*. If a promise has met its requirements, the next action will take over, and so on, and so on.

The neat part of this is how we can do CRUD operations on a webpage seemingly effortless.  
Everything is done behind-the-scenes and if done properly we ensure that the process is either succesful or an error message is returned.  
[w3schools](https://www.w3schools.com/whatis/whatis_ajax.asp) has an interesting feature and article of this subject.  
[Wikipedia](https://en.wikipedia.org/wiki/Ajax_(programming)#Fetch_example) has some examples, including ES7 async/await.

- **Explain the Same Origin Policy (for AJAX), and different ways to work around it**  

The [Same Origin Policy](https://developer.mozilla.org/en-US/docs/Web/Security/Same-origin_policy) is in Ajax used to determine if an HTTP request is from the same origin as the receiver or not. The following requirements must be met:  
   - Protocol (http/s)
   - Port (if specified) (8080)
   - Host (runivn.dk)
So if an request is sent from https:runivn.dk:8080/app to https:runivn.dk:8080/api/person/all - everything is good.

As discussed this week, there are ways to get around the Same Origin Policy.

The webserver can set up [CORS](https://developer.mozilla.org/en-US/docs/Web/HTTP/CORS) to handle either specific hosts or all using the wildcard character (\*).

We also set up a proxy handler, where the idea is for the webserver to only allow one host access, but then allow this host (the proxy) to delegate requests.

## Exercise 2  
```
For the previous task it was possible to obtain data right from restcountries.eu,
via an AJAX call made from within your Browser (as sketched to the right). 
*Use Chrome Developer tools to explain (with focus on the Same Origin Policy) why this is possible.* 
```
![](https://i.imgur.com/L65qQVU.png)  
The host server (restcountries.eu using Cloudflare) allows any origins (they use the wildcard symbol \*) to send GET requests to the specific resource.
