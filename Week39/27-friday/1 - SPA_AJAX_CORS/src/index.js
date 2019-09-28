import 'bootstrap/dist/css/bootstrap.css'

let url = 'http://restcountries.eu/rest/v1/alpha?codes=';
let svgOutput = document.getElementById("svgOutput");

function fetchWithErrorCheck(res) {
    if (!res.ok) {
        return Promise.reject({ status: res.status, fullError: res.json() })
    }
    return res.json();
}
let targetCountry = "";
document.getElementById("svg2").addEventListener("click", function (event) {
    let countryCode = event.target.id;
    if (countryCode == 'ru-main') countryCode = 'ru'; //fix error lol (Russia)
    if (countryCode == 'gb-gbn') countryCode = 'gb'; //fix error lol (Great Britain)
    //There are a few others that don't work either, mainly GB...(Northern) Ireland, Wales, etc.
    //if (countryCode == 'svg2') countryCode = 'u press da wrong place'; //hmm
    svgOutput.innerHTML = ""; //reset output
    if (countryCode !== 'svg2' && countryCode !== 'Large masses of water') {
        colorHelper(event.target)
        fetchHelper(countryCode);
    }
});

function colorHelper(eventTargetCountry) {
    if (targetCountry !== "") { //if there is an "old" targetCountry
        //Set its color to regular
        targetCountry.style = 'fill:#c0c0c0;stroke:#ffffff;stroke-width:0.40000001;stroke-miterlimit:4;stroke-dasharray:none';
    }
    targetCountry = eventTargetCountry; //update old targetCountry to current to prepare check for next event
    eventTargetCountry.style = 'fill:#800000;stroke:#ffffff;stroke-width:0.11153841;stroke-miterlimit:4;stroke-dasharray:none';
    //Would have been cool to color bordering countries too
}

function fetchHelper(code) {
    fetch(url + code)
        .then(res => fetchWithErrorCheck(res))
        .then(data => {

            dataGenerate(data);
        })
        .catch(err => {
            if (err.status) {
                err.fullError.then(e => {
                    svgOutput.innerHTML = "<p>Error:</p>" + JSON.stringify(e);
                    console.log(e.detail)
                })
            }
            else { console.log(err); }
        });
}

function dataGenerate(jsonInput) {
    //could also have used the API LOL https://restcountries.eu/rest/v2/alpha/{countryID}?fields=name;population;area;borders
    let data = {
        Country: jsonInput[0].name,
        Population: jsonInput[0].population,
        Area: jsonInput[0].area,
        Borders: jsonInput[0].borders
    };
    for (const key in data) {
        if (data.hasOwnProperty(key)) {
            const value = data[key];
            svgOutput.innerHTML += `${key}:` + ` ${value}<br>`;
        }
    }
    return data;
}



//ES7+ async/await doesn't work on our webpack
//   async function fetchHelper(code) {
//     console.log(code);
//     const response =  await fetch(url + code)
//     const json = fetchWithErrorCheck(response);
//     console.log(response);

//     return  dataSort(json);
// }

