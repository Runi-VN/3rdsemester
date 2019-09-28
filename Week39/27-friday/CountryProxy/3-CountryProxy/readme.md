# Exercise notes

Deployed on droplet @ https://runivn.dk/proxy/api/proxy/  

Accessing the proxy (only takes JSON) is by going here:  
```html
https://runivn.dk/proxy/api/proxy/newproxy/URL={url}
```

Example:  
https://runivn.dk/proxy/api/proxy/newproxy?URL=https://restcountries.eu/rest/v2/alpha/dk?fields=name;population;area;borders

Here is the output of accessing country code `ru`, using the proxy through the SVG-eventhandler:
![](https://i.imgur.com/lMaYnyU.png)