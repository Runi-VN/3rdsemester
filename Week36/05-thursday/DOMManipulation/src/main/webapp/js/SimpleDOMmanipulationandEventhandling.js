//window.addEventListener('DOMContentLoaded', (event) => {
//    exercise1a();
//});
function exercise1a() {
    let divs = Array.from(document.getElementsByTagName('div'));
    divs.forEach(div => {
        div.style.backgroundColor = 'black';
    });
}
exercise1a();

function exercise1b() {
    let div1 = document.getElementById('div1');
    let div2 = document.getElementById('div2');
    let div3 = document.getElementById('div3');

    div1.style.backgroundColor = makeRandomColor();
    div2.style.backgroundColor = makeRandomColor();
    div3.style.backgroundColor = makeRandomColor();
}
//used for exercise1b^
function makeRandomColor() {
    var o = Math.round, r = Math.random, s = 255;
    return 'rgba(' + o(r()*s) + ',' + o(r()*s) + ',' + o(r()*s) + ',' + r().toFixed(1) + ')';
}

