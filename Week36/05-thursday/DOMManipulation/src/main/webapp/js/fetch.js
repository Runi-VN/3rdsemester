document.getElementById("btnUser").addEventListener("click", function () {
    let userID = document.getElementById("userID").value;
    let url = "https://jsonplaceholder.typicode.com/users/" + userID;

    fetch(url)
            .then(res => res.json()) //in flow1, just do it
            .then(data => {
                // Inside this callback, and only here, the response data is available
                document.getElementById("output").innerHTML = JSON.stringify(data, null, 2);
                console.log("data", data);
                /* data now contains the response, converted to JavaScript
                 Observe the output from the log-output above
                 Now, just build your DOM changes using the data*/
            });

});

document.getElementById("btnAll").addEventListener("click", function () {
    let url = "https://jsonplaceholder.typicode.com/users/";

    fetch(url)
            .then(res => res.json()) //in flow1, just do it
            .then(data => {
                // Inside this callback, and only here, the response data is available
                document.getElementById("output").innerHTML = JSON.stringify(data, null, 3);
                console.log("data", data);
                /* data now contains the response, converted to JavaScript
                 Observe the output from the log-output above
                 Now, just build your DOM changes using the data*/
            });

});