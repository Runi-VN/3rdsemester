# Exercise Notes

## 1) CORS with Java and Jax-rs

### The Backend  
*Test the API, using POSTMAN. (all CRUD-operations)*

#### READ
![](https://i.imgur.com/meukLbe.png)

#### CREATE
![](https://i.imgur.com/nr06bdX.png)

#### UPDATE
![](https://i.imgur.com/NL8aOU6.png)

#### DELETE
![](https://i.imgur.com/D2i6fEa.png)

*This should not cause any CORS-related problems, WHY?*
I have found a variety of answers:
1) The (very) basic webserver is not set up to handle SOP/CORS.

2) CORS is a dev-tool, not a browser, and does not care about Same/origin policy [(source)](https://stackoverflow.com/questions/36250615/cors-with-postman#comment60130902_36250615)  
However, if I manually access the endpoint, then dev-tools and view network -> filter by XHR there are no results. There aren't any related headers either.  
So I don't think it sends anything CORS-related at all.

3) The request lives up to CORS requirements.  
I haven't been able to find out how POSTMAN sends its requests, but this theory is that we live up to all the requirements for CORS:

![](https://i.imgur.com/gxWeuN8.png)  
![](https://i.imgur.com/pMAg6IJ.png)

### The Frontend  
*Does this work?, can you use the API, from your new “SPA-client”? If not explain why (the errors you get).*  
No, it does not. The server is not set up for CORS-handling.  
The error message & response headers are actually a bit vague (probably due to the simple webserver):  
#### CONSOLE  
![](https://i.imgur.com/KoDosEE.png)  
#### HEADERS  
![](https://i.imgur.com/mJuNkj3.png)  

*Verify that you can use the full API. Monitor all request, especially POST, and make sure you can explain all the request made by the browser, also those which are not a GET, POST, PUT or DELETE request.*

#### READ
![](https://i.imgur.com/6sCNH4v.png)

#### CREATE
![](https://i.imgur.com/VWtanie.png)

#### UPDATE
![](https://i.imgur.com/RD37nqH.png)

#### DELETE
![](https://i.imgur.com/4zMguSK.png)

## 2) CORS with Java and Jax-rs for a “real” project  
Skipped. This is essentially the same assignment as above and the same is included in the friday assignment.

## 3) Deploy a SPA on separate Servers  
http://runivn.surge.sh/

