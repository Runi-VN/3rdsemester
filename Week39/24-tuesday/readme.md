# Exercise Notes (Tuesday 24th)
[Exercise link](https://docs.google.com/document/d/1vl8J-PUiFIzUt6jCE9gGpiw5XvOW1L3FeouTiWemwt8/edit)

## HTML Screenshots:


### BEFORE CLICK  
![](https://i.imgur.com/VAX0vgz.png)  


### AFTER CLICK
(not pretty I know, but I did as asked lol)  
(it uses my own namearray, not the one shown above)  
![](https://i.imgur.com/lX0j1di.png)  


## 6) HOISTING
There are examples available at https://www.w3schools.com/js/js_hoisting.asp

- What hoisting is  
*Hoisting is JavaScript's default behavior of moving declarations to the top.*  
If you declare something with a *hoistworthy* keyword it will be pushed to the top of the Javascript function when run.

- What is the difference between the keyword var and the ES6 keyword let?  
'let' declares a variable within a block scope. Javascript cannot access it outside its scoped block. It is sometimes (wrongly) refered to as "private" and it is deleted after use.
- 'var' declares a variable within a function body.

*A variable defined using a `var` statement is known throughout the function it is defined in, from the start of the function.  
A variable defined using a `let` statement is only known in the block it is defined in, from the moment it is defined onward.* [(Source)](https://stackoverflow.com/questions/762011/whats-the-difference-between-using-let-and-var)

DOES IT HOIST?
- [x] VAR
- [ ] LET
- [ ] CONST

Here's an example on `VAR`-hoisting from [MDN](https://developer.mozilla.org/en-US/docs/Web/JavaScript/Reference/Statements/var#var_hoisting):  

```javascript
bla = 2;
var bla;

// ...is implicitly understood as:

var bla;
bla = 2;
```  

## 7) `this` in JavaScript

- How this in JavaScript differ from this in Java  
  - Java: `this` refers to the current instantiation of an object.
  - Javascript: The value of this is determined mostly by the invocation context of function and where it is called. [read](https://stackoverflow.com/questions/3127429/how-does-the-this-keyword-work)
    - **Method**: this refers to the owner object.  
    - **Alone**: this refers to the global object.
    - **Function**: this refers to the global object. (strict mode = undefined)
    - **Event**: this refers to the element that received the event.  

Methods like call(), and apply() can refer this to any object. [(Source)](https://www.w3schools.com/js/js_this.asp)

- The purpose of the methods call(), apply() and bind()  
Reusing methods for different objects. [In a nutshell (40 second video)](https://youtu.be/c0mLRpw-9rI?t=837)
  - Call(): Attaches an outside function to an object and returns the result.  
![](https://i.imgur.com/RGh1yAK.png)  
If I wanted multiple arguments, I would have to refactor my `addToThis`-function and include the arguments when calling the function. This is where **Apply()** comes in handy.  
  - Apply(): Is similar to *call* but  instead of taking single arguments, it takes an array:  
  ![](https://i.imgur.com/rkOcp8z.png)  
  - Bind(): actually binds the object and function *together* and returns a new function
  ![](https://i.imgur.com/WftIXaq.png)
  
Choosing one is not always easy. [Here](https://stackoverflow.com/a/54562241) is a discussion of invocation *times* as well as `this`-usages.
