const today = new Date();
const hrs = today.getHours();
const container = document.querySelector('#container');

let myImage = document.createElement('img');
myImage.src = (hrs < 12) ? "./images/pic-1.jpg" : "./images/pic-1.jpg";
container.appendChild(myImage);