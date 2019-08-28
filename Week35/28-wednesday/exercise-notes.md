# Notes related to the Wednesday Exercises
[Exercise link - The HTTP Protocol](https://docs.google.com/document/d/1yZnup_hF7s4WI0K6VWL2yc0XxPJ1_CkSt8GgZkjG3Mw/edit)

## Exercise 1
<img src="https://i.imgur.com/dTy9lpM.png" width="400" height="400" align="right">
Explaining HTTP headers. This is what I got:  

----  

### General  
 
##### Request URL
  - The URL that was requested by the client
##### Request Method
  - GET. We attempted to retrieve the document with the GET request method.
##### Status Code
  - 304, not modified (Which equals success - this is due to caching) (Was 200 (=OK, success) before screenprint)
##### Remote Address:
  - Localhost on port 8080
##### Referrer policy:
  - This is the default policy and it is based on secure protocol referal. This type will transfer *(HTTP -> HTTP, HTTPS -> HTTPS)* or even improve *(HTTP -> HTTPS)* but **never** downgrade. *(HTTPS -> HTTP)* ([Details](https://developer.mozilla.org/en-US/docs/Web/HTTP/Headers/Referrer-Policy))

----  
### Response Headers

##### HTTP/1.1 304
- The response is sent with version 1.1 of HTTP and its status code is 304 (as covered above)

##### ETag: 
- Related to the check done by the server for caching. The `W/` specifies a weak check was done. The numbers are often generated from hash, a timestamp or other. Not specified. ([Details](https://developer.mozilla.org/en-US/docs/Web/HTTP/Headers/ETag))

##### Date
- Date of the response. 2 hours behind local-time, as we are in GMT+2 due to timezone + summertime.

---- 
### Request Headers  

##### GET /http_protocol_wednesday/ HTTP 1.1
- The actual request. We use the GET-method for the specified URI (resource) using HTTP version 1.1

##### Host
- Where is the abovementioned resource located? Here!

##### Connection
- This header controls whether or not the network connection stays open after the current transaction finishes. (Reduces handshakes) [Options: Keep-Alive, Closed]  
  - Keep-Alive has either default values or parameter options for timeout (earliest timeout) or max (max amount of requests).

##### Cache-control
- Handles caching functions, max-age has a parameter of seconds. Can also handle cache type and [much more](https://developer.mozilla.org/en-US/docs/Web/HTTP/Headers/Cache-Control).

##### Upgrade-Insecure-Requests
- Handles requests for upgrading from HTTP to HTTPs. If a request is made and HTTPs is available it can now redirect to the HTTP version.

##### User-Agent
- Contains information about the client, in this case we are shown compatibility with Mozilla, AppleWebKit, KHTML/Gecko and Safari, running Windows 10 through a Chrome V76 Browser. [Neat website with explanation](http://useragentstring.com/).

##### Sec-Fetch-Mode/Sec-Fetch-User/Sec-Fetch-Site
- Kind of hard to find details about, I believe it is related to Document Fetching on the Request interface. It is explained partly [here](https://w3c.github.io/webappsec-fetch-metadata/#sec-fetch-mode-header). I believe it is out of scope for this assignment.

##### Accept
- The kind of MIME-types (document-types) accepted by the request.

##### Accept-Encoding
- The kind of encodings accepted by the request.

##### Accept-Language
- The kind of languages acepted by the request.

##### If-None-Match:
- Cache *ETag* to use in case of no modifications since last check. See below.

##### If-Modified-Since
- Date since last cache (11 minutes). Since there were no changes since then we loaded the cache with the mentioned ETag.

## Exercise 2
explain the purpose of the connection header.
- See above.


## Exercise 3  
explain:
##### The two HTTP-request you see
- I get two GET-requests, one for redirect with a status code of [302 FOUND](https://developer.mozilla.org/en-US/docs/Web/HTTP/Status/302) and one for r.html with a status code of 200 OK.

##### How the browser knew where to go in the second request
- We set up a servlet to send a redirect to the `r.html`-resource to any and all GET-requests to the `/redirect`-URI.


## Exercise 3a  
Head to the unsafe version of studypoints.info and explain what happens.
- The header returns the following:
  - Request URL: http://studypoints.info/
  - Request Method: GET
  - Status Code: 301 Moved Permanently
- The response returns this interesting part:
  - Location: https://studypoints.info/
  - Server: nginx/1.14.0 (Ubuntu)
  
The http-address changed permanently to the https-address thanks to an redirect (probably done by the nginx service(?)).

## Exercise 4a  
<img src="https://i.imgur.com/6qLwJcn.png" align="right">  

- Tomcat gives us the java exception while the status code 500 (INTERNAL SERVER ERROR) is returned.  
- Another interesting part is how the connection is set to `close` instead of the regular `Keep-Alive`.  

## Exercise 4b
- Returns an 404 error (not found) status code.


## Exercise 4c
-  1xx (Informational): The request was received, continuing process
-  2xx (Successful): The request was successfully received, understood and accepted
-  3xx (Redirection): Further action needs to be taken in order to complete the request
-  4xx (Client Error): The request contains bad syntax or cannot be fulfilled
-  5xx (Server Error): The server failed to fulfill an apparently valid request


## Exercise 5  
- Here is a picture of my solution:

![tablesolutionimage](https://i.imgur.com/HyAHmtu.png)

## Exercise 6
- GET-method returns the following: `/formtest.html?firstName=Bølle&lastName=Bob&hidden=12345678#`
  - Everything is shown in the address bar.  
![](https://i.imgur.com/tZl2k57.png)

- POST-method returns the following: `/formtest.html#`
  - Everything is in the header.  
![](https://i.imgur.com/eer16av.png)

## Exercise 7
- Entering `http://localhost:8080/wednesday_7-8/SessionDemo?name=Rúni` I get the following interesting headers:
  - `Cookie: JSESSIONID=FF3A75E8F0239DF3E9E87156C8A5663E`
- Closing the tab and reloading, the cookie is still the same:
  - `Cookie: JSESSIONID=FF3A75E8F0239DF3E9E87156C8A5663E`
- Removing parameters and reloading, the cookie is still the same:  
![](https://i.imgur.com/z8tKdqS.png)  

- Tomcat Manager has a good way of showing what is going on:
![](https://i.imgur.com/lhvjDYF.png)

Simply put, a cookie is a small piece of data stored on the client-side which servers use when communicating with clients. Cookies are used to identify a client when sending a subsequent request.

## Exercise 8
