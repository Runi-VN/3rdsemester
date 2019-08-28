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
- This header controls whether or not the network connection stays open after the current transaction finishes. [Options: Keep-Alive, Closed]  
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


## Exercise 3  


## Exercise 4  


## Exercise 5  


## Exercise 6  


## Exercise 7  


## Exercise 8  
