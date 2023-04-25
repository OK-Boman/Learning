function getQuote() {
  document.getElementById("outputti").innerHTML = "";
  fetch("https://api.quotable.io/random")
    .then((response) => response.json())
    .then((data) => {
      const quote = data.content;
      const author = data.author;
      const quoteElement = document.createElement("p");
      quoteElement.innerHTML = `"${quote}" </br></br> - ${author}`;

      document.getElementById("outputti").appendChild(quoteElement);
    });
} 
getQuote();
setInterval(getQuote, 900000);

const clockElement = document.getElementById("box1");
setInterval(() => {
  const date = new Date();
  const hours = date.getHours();
  const minutes = date.getMinutes();
  const day = date.getDate();
  const month = date.getMonth() + 1;
  const year = date.getFullYear();
  const weekday = date.getDay();
  const weekdays = ["Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"];
  const weekdayName = weekdays[weekday];

  if (minutes < 10) {
    clockElement.innerHTML = `${hours}:0${minutes} </br> ${weekdayName} </br> ${day}.${month}.${year}`;
  } else {
    clockElement.innerHTML = `${hours}:${minutes} </br> ${weekdayName} </br> ${day}.${month}.${year}`;
  }
}, 10000);

function hideMenu() {
  let menuOpen = document.querySelector(".toggler").checked;
  if (menuOpen = true) {
    document.querySelector(".toggler").checked = false;
} 
}
