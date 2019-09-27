import 'bootstrap/dist/css/bootstrap.css'

let url = 'http://restcountries.eu/rest/v1/alpha?codes=';

function fetchWithErrorCheck(res) {
    if (!res.ok) {
        return Promise.reject({ status: res.status, fullError: res.json() })
    }
    return res.json();
}


document.getElementById("svgContainer").addEventListener("click", function (event) {
    let countryCode = event.target.id;
    document.getElementById("svgOutput").innerHTML = fetchHelper(countryCode);
    console.log(event.target.id);

});

  async function fetchHelper(code) {
    console.log(code);
    const response =  await fetch(url + code)
    const json = fetchWithErrorCheck(response);
    console.log(response);
    
    return  dataSort(json);
}

 function dataSort(jsonInput){
    let data = {
        Country : name,
        Population : population,
        Area : area,
        Borders : borders
    };
    return JSON.stringify(data, null, 2);
}