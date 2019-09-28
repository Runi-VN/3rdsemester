# Exercise notes
[Exercise link](https://docs.google.com/document/d/1VMx1XdbnIbJ6ik98qPywGyrRiqbQuIM2u9DpJmXCnlk/edit)

```
2) 
For the previous task it was possible to obtain data right from restcountries.eu,
via an AJAX call made from within your Browser (as sketched to the right). 
*Use Chrome Developer tools to explain (with focus on the Same Origin Policy) why this is possible.* 
```
![](https://i.imgur.com/L65qQVU.png)
The host server (restcountries.eu using Cloudflare) allows any origins (they use the wildcard symbol \*) to send GET requests to the specific resource.