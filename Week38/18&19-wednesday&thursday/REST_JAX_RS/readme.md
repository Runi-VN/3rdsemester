# Exercise notes (wednesday/thursday)  

## The facade and the matching endpoints  

### 4) Complete the GET methods in the facade, and implementing matching REST endpoints. 
Postman 
 

```
For the REST-endpoints that returns a Person, the JSON object should be built like this 
(observe the absence of the two date-fields):
```  
```java 
{"fName":"Kurt","lName":"Wonnegut", phone:"12345678","id":0}  
```

getPerson()  
![](https://i.imgur.com/FsSEnRY.png)  
```
For the GET method that returns all Persons, the JSON must have this format:  
```  

```java
{ 
  "all" :[
    {"fName":"Kurt","lName":"Wonnegut","phone":"12345678","id":0},
    {"fName":"Peter","lName":"Hansen","phone":"12345678","id":1}
  ] 
}  
```  
getAllPersons()  
![](https://i.imgur.com/sOATOWV.png)

### 5) Complete the addPerson(..) method in the facade, and implement a matching REST endpoint. Test using Postman

```
For the REST-endpoints that creates a Person, send JSON without the id property:  
```  

```java
{"fName":"Kurt","lName":"Wonnegut", phone:"12345678"}
```
addPerson()  
![](https://i.imgur.com/4EWiWy4.png)  

### 6) Complete the editPerson(..) method in the facade, and implement a matching REST endpoint. Test using Postman  

editPerson()  
**BEFORE**  
![](https://i.imgur.com/IxwmCKi.png)  
**AFTER**  
![](https://i.imgur.com/E5qwPgw.png)  

### 7) Complete the deletePerson(..) method in the facade, and implement a matching REST endpoint. Test using Postman  
