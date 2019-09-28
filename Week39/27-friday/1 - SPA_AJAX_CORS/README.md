# Exercise notes
[Exercise link](https://docs.google.com/document/d/1VMx1XdbnIbJ6ik98qPywGyrRiqbQuIM2u9DpJmXCnlk/edit)  


- Explain about the Object Model, and why it’s (very) relevant for modern Web-development  

The Document Object Model (DOM) is ...

- Explain (using an example of your own choice) about JavaScript events, and Event Bubbling
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

    
- Elaborate on how JSON or XML supports communication between subsystems, even when the subsystems are implemented on diﬀerent platforms.
- Explain the topic AJAX and how it has changed the way modern web-applications are created
- Explain the Same Origin Policy (for AJAX), and different ways to work around it



```
2) 
For the previous task it was possible to obtain data right from restcountries.eu,
via an AJAX call made from within your Browser (as sketched to the right). 
*Use Chrome Developer tools to explain (with focus on the Same Origin Policy) why this is possible.* 
```
![](https://i.imgur.com/L65qQVU.png)  
The host server (restcountries.eu using Cloudflare) allows any origins (they use the wildcard symbol \*) to send GET requests to the specific resource.
