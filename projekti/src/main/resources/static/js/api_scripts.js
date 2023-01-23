let user;
let boxId;
let apiName;
let apiValue;
let visible;

function apiSelection(element) {
  let contId = element.parentNode.parentNode.parentNode.parentNode.id;
  let parent = document.getElementById(contId);
  element.parentElement.style.display = "none";
  parent.querySelector(".user-input").style.display = "block";

  if (element.id.startsWith("weather")) {
    parent.querySelector(".weather-selection").style.display = "block";
  }
  if (element.id.startsWith("price")) {
    parent.querySelector(".price-selection").style.display = "block";
  }
  if (element.id.startsWith("hsl")) {
    parent.querySelector(".hsl-selection").style.display = "block";
  }
  if (element.id.startsWith("stock")) {
    parent.querySelector(".stock-selection").style.display = "block";
  }
  if (element.id.startsWith("joke")) {
    parent.querySelector(".user-input").style.display = "none";
    parent.querySelector(".joke-api").style.display = "block";
  }
}

function changeApi(element) {
  let contId = element.parentNode.parentNode.parentNode.id;
  let parent = document.getElementById(contId);
  parent.querySelector(".api-selection").style.display = "block";
  parent.querySelector(".user-input").style.display = "none";
  parent.querySelector(".weather-selection").style.display = "none";
  parent.querySelector(".weather-api").style.display = "none";
  parent.querySelector(".price-selection").style.display = "none";
  parent.querySelector(".price-api").style.display = "none";
  parent.querySelector(".hsl-selection").style.display = "none";
  parent.querySelector(".hsl-api").style.display = "none";
  parent.querySelector(".stock-selection").style.display = "none";
  parent.querySelector(".stock-api").style.display = "none";
  parent.querySelector(".joke-api").style.display = "none";
}

function getWeatherApi(element) {
  let apiId = 1;
  apiName = "weather"
  if (apiValue != null) {
    let parent = document.querySelector("#box" + boxId);
    parent.querySelector(".api-selection").style.display = "none";
    parent.querySelector(".user-input").style.display = "none";
    let weatherApi = parent.querySelector(".weather-api");
    weatherApi.style.display = "block";
    let city = apiValue;
    fetch("/weather/" + city)
      .then((response) => response.json())
      .then((weather) => {
        let temp = weather.temp + "°C";
        let icon = weather.icon;
        let iconUrl = weather.iconUrl;
        weatherApi.querySelector("#city").innerHTML = city;
        weatherApi.querySelector("#weather").innerHTML = temp;
        weatherApi.querySelector("#weather-icon").src = iconUrl;
      });
  } else {
    let contId = element.parentNode.parentNode.parentNode.parentNode.parentNode.parentNode.id;
    boxId = Number(contId.slice(-1));
    let parent = document.getElementById(contId);
    parent.querySelector(".user-input").style.display = "none";
    let weatherApi = parent.querySelector(".weather-api");
    weatherApi.style.display = "block";
    let city = parent.querySelector("#user-input-text-weather").value;
    PostUserLogs(apiId, apiName, boxId, city);
    fetch("/weather/" + city)
      .then((response) => response.json())
      .then((weather) => {
        let temp = weather.temp + "°C";
        let icon = weather.icon;
        let iconUrl = weather.iconUrl;
        weatherApi.querySelector("#city").innerHTML = city;
        weatherApi.querySelector("#weather").innerHTML = temp;
        weatherApi.querySelector("#weather-icon").src = iconUrl;
      });
  }
  return false;
}

function getPriceApi(element) {
  let apiId = 2;
  apiName = "price";
  if (apiValue != null) {
    let parent = document.querySelector("#box" + boxId);
    parent.querySelector(".api-selection").style.display = "none";
    parent.querySelector(".user-input").style.display = "none";
    let priceApi = parent.querySelector(".price-api");
    priceApi.style.display = "block";
    let userSelection = apiValue;
    if (userSelection === "electricity") {
      parent.querySelector("#price-api-inflation-content").style.display = "none";
      parent.querySelector("#price-api-euribor-content").style.display = "none";
      parent.querySelector("#price-api-electricity-content").style.display = "block";
      fetch("/spot-price-today")
        .then((response) => response.json())
        .then((data) => {
          priceApi.querySelector(".average-spot").innerHTML = `Average: ${data.AverageSpot} c/kWh`;
          priceApi.querySelector(".highest-spot").innerHTML = `High: ${data.HighestSpot} c/kWh`;
          priceApi.querySelector(".lowest-spot").innerHTML = `Low: ${data.LowestSpot} c/kWh`;
        });

      fetch("/spot-price-tomorrow")
        .then((response) => response.json())
        .then((data) => {
          if (data.AverageSpot === undefined) {
            priceApi.querySelector(".average-spot-tomorrow").innerHTML = "After 15:00";
          } else {
            priceApi.querySelector(".average-spot-tomorrow").innerHTML = `Average: ${data.AverageSpot} c/kWh`;
          }
          if (data.HighestSpot === undefined) {
            priceApi.querySelector(".highest-spot-tomorrow").innerHTML = "After 15:00";
          } else {
            priceApi.querySelector(".highest-spot-tomorrow").innerHTML = `High: ${data.HighestSpot} c/kWh`;
          }
          if (data.LowestSpot === undefined) {
            priceApi.querySelector(".lowest-spot-tomorrow").innerHTML = "After 15:00";
          } else {
            priceApi.querySelector(".lowest-spot-tomorrow").innerHTML = `Low: ${data.LowestSpot} c/kWh`;
          }
        });
    }
    if (userSelection === "euribor") {
      parent.querySelector("#price-api-electricity-content").style.display = "none";
      parent.querySelector("#price-api-inflation-content").style.display = "none";
      parent.querySelector("#price-api-euribor-content").style.display = "block";
      fetch("/euribor")
        .then((response) => response.json())
        .then((data) => {
          parent.querySelector(".euribor1").innerHTML = `1kk: ${data.non_central_bank_rates[1].rate_pct}%`;
          parent.querySelector(".euribor3").innerHTML = `3kk: ${data.non_central_bank_rates[3].rate_pct}%`;
          parent.querySelector(".euribor12").innerHTML = `12kk: ${data.non_central_bank_rates[4].rate_pct}%`;
        });
    }
    if (userSelection === "inflation") {
      parent.querySelector("#price-api-electricity-content").style.display = "none";
      parent.querySelector("#price-api-euribor-content").style.display = "none";
      parent.querySelector("#price-api-inflation-content").style.display = "block";
      fetch("/inflation")
        .then((response) => response.text())
        .then((data) => {
          parent.querySelector(".inflation").innerHTML = data;
        });
    }
  } else {
    let contId = element.parentNode.parentNode.parentNode.parentNode.parentNode.id;
    let parent = document.getElementById(contId);
    boxId = Number(contId.slice(-1));
    parent.querySelector(".user-input").style.display = "none";
    let priceApi = parent.querySelector(".price-api");
    priceApi.style.display = "block";
    let userSelection = parent.querySelector("#price-selection").value;
    PostUserLogs(apiId, apiName, boxId, userSelection);
    if (userSelection === "electricity") {
      parent.querySelector("#price-api-inflation-content").style.display = "none";
      parent.querySelector("#price-api-euribor-content").style.display = "none";
      parent.querySelector("#price-api-electricity-content").style.display = "block";
      fetch("/spot-price-today")
        .then((response) => response.json())
        .then((data) => {
          priceApi.querySelector(".average-spot").innerHTML = `Average: ${data.AverageSpot} c/kWh`;
          priceApi.querySelector(".highest-spot").innerHTML = `High: ${data.HighestSpot} c/kWh`;
          priceApi.querySelector(".lowest-spot").innerHTML = `Low: ${data.LowestSpot} c/kWh`;
        });

      fetch("/spot-price-tomorrow")
        .then((response) => response.json())
        .then((data) => {
          if (data.AverageSpot === undefined) {
            priceApi.querySelector(".average-spot-tomorrow").innerHTML = "After 15:00";
          } else {
            priceApi.querySelector(".average-spot-tomorrow").innerHTML = `Average: ${data.AverageSpot} c/kWh`;
          }
          if (data.HighestSpot === undefined) {
            priceApi.querySelector(".highest-spot-tomorrow").innerHTML = "After 15:00";
          } else {
            priceApi.querySelector(".highest-spot-tomorrow").innerHTML = `High: ${data.HighestSpot} c/kWh`;
          }
          if (data.LowestSpot === undefined) {
            priceApi.querySelector(".lowest-spot-tomorrow").innerHTML = "After 15:00";
          } else {
            priceApi.querySelector(".lowest-spot-tomorrow").innerHTML = `Low: ${data.LowestSpot} c/kWh`;
          }
        });
    }
    if (userSelection === "euribor") {
      parent.querySelector("#price-api-electricity-content").style.display = "none";
      parent.querySelector("#price-api-inflation-content").style.display = "none";
      parent.querySelector("#price-api-euribor-content").style.display = "block";
      fetch("/euribor")
        .then((response) => response.json())
        .then((data) => {
          parent.querySelector(".euribor1").innerHTML = `1kk: ${data.non_central_bank_rates[1].rate_pct}%`;
          parent.querySelector(".euribor3").innerHTML = `3kk: ${data.non_central_bank_rates[3].rate_pct}%`;
          parent.querySelector(".euribor12").innerHTML = `12kk: ${data.non_central_bank_rates[4].rate_pct}%`;
        });
    }
    if (userSelection === "inflation") {
      parent.querySelector("#price-api-electricity-content").style.display = "none";
      parent.querySelector("#price-api-euribor-content").style.display = "none";
      parent.querySelector("#price-api-inflation-content").style.display = "block";
      fetch("/inflation")
        .then((response) => response.text())
        .then((data) => {
          parent.querySelector(".inflation").innerHTML = data;
        });
    }
  }
}


function getHSLApi(element) {
  let apiId = 3;
  apiName = "hsl";
  if (apiValue != null) {
    let parent = document.querySelector("#box" + boxId);
    parent.querySelector(".api-selection").style.display = "none";
    parent.querySelector(".user-input").style.display = "none";
    let hslApi = parent.querySelector(".hsl-api");
    hslApi.style.display = "block";
    parent.querySelector("#address").innerHTML = apiValue;
    let html = "";
    HTTP.get("hsl/" + apiValue).then((hsls) => {
      hsls.forEach((hsl) => {
        html += `<tr class="hslRow">
                    <td class = "tdtime">${hsl.time}</td>
                    <td class = "tdstop">${hsl.stop}</td>
                    <td class = "tdvehicle">${hsl.vehicle}</td></tr>`;
      });
      parent.querySelector("#hslTable").innerHTML = html;
    });
  } else {
    contId = element.parentNode.parentNode.parentNode.parentNode.parentNode.parentNode.id;
    boxId = Number(contId.slice(-1));
    let parent = document.getElementById(contId);
    parent.querySelector(".api-selection").style.display = "none";
    parent.querySelector(".user-input").style.display = "none";
    let hslApi = parent.querySelector(".hsl-api");
    hslApi.style.display = "block";
    let address = parent.querySelector("#user-input-text-hsl").value;
    PostUserLogs(apiId, apiName, boxId, address);
    parent.querySelector("#address").innerHTML = address;
    let html = "";
    HTTP.get("hsl/" + address).then((hsls) => {
      hsls.forEach((hsl) => {
        html += `<tr class="hslRow">
                    <td class = "tdtime">${hsl.time}</td>
                    <td class = "tdstop">${hsl.stop}</td>
                    <td class = "tdvehicle">${hsl.vehicle}</td></tr>`;
      });
      parent.querySelector("#hslTable").innerHTML = html;
    });
  }
  return false;
}

// stock-symbol is percentual change in the output

function getStockPriceApi(element) {
  let apiId = 4;
  apiName = "stock"
  if (apiValue != null) {
    let parent = document.querySelector("#box" + boxId);
    parent.querySelector(".api-selection").style.display = "none";
    parent.querySelector(".user-input").style.display = "none";
    let stockApi = parent.querySelector(".stock-api");
    stockApi.style.display = "block";
    let stock = apiValue
    parent.querySelector("#stock").innerHTML = stock;
    let html = "";
    fetch("stock-price/" + stock)
      .then((response) => response.json())
      .then((stockInfo) => {
        let price = stockInfo.c;
        let change = stockInfo.dp;
        stockApi.querySelector("#stock-price").innerHTML = "$ " + price;
        stockApi.querySelector("#stock-symbol").innerHTML = change + " %";
      });
  } else {
    let contId = element.parentNode.parentNode.parentNode.parentNode.parentNode.parentNode.id;
    boxId = Number(contId.slice(-1));
    let parent = document.getElementById(contId);
    parent.querySelector(".user-input").style.display = "none";
    parent.querySelector(".api-selection").style.display = "none";
    let stockApi = parent.querySelector(".stock-api");
    stockApi.style.display = "block";
    let stock = parent.querySelector("#user-input-text-stock").value;
    parent.querySelector("#stock").innerHTML = stock;
    let html = "";
    PostUserLogs(apiId, apiName, boxId, stock);
    fetch("stock-price/" + stock)
      .then((response) => response.json())
      .then((stockInfo) => {
        let price = stockInfo.c;
        let change = stockInfo.dp;
        stockApi.querySelector("#stock-price").innerHTML = "$ " + price;
        stockApi.querySelector("#stock-symbol").innerHTML = change + " %";
      });
  }
  return false;
}
function getTekstiTv() {
  let apiId = 6;
  apiName = "textTv";
  if (apiValue != null) {
    let userSivu = apiValue
    sessionStorage.setItem("userSivu", userSivu);
    var obj2 = sessionStorage.userSivu;

    let sivu = userSivu;
    if (userSivu == 0) {
      sivu = 100;
    } else {
      sivu = userSivu;
    }
    let alisivu = 1;
    let url = "/tekstitv/" + sivu + "/" + alisivu;
    fetch(url)
      .then((response) => response.text())
      .then((data) => {
        document.querySelector("#tekstitv-img").src = "data:image/png;base64," + data;
      });
  } else {
    let userSivu = document.querySelector("#tekstitv-sivu").value;
    let sivu = userSivu;
    if (userSivu == 0) {
      sivu = 100;
    } else {
      sivu = userSivu;
    }
    let alisivu=1;
    if(userSivu.includes("/")){
    const array=userSivu.split("/")
    alisivu=array[1]
    console.log(alisivu)
  }
    let url = "/tekstitv/" + sivu + "/" + alisivu;
    PostUserLogs(apiId, apiName, 5, userSivu)
    fetch(url)
      .then((response) => response.text())
      .then((data) => {
        document.querySelector("#tekstitv-img").src = "data:image/png;base64," + data;
      })
  }
  return false;
}
function getJokeApi(element) {
  let apiId = 5;
  apiName = "joke";
  if (apiValue != null) {
    let parent = document.querySelector("#box" + boxId);
    parent.querySelector(".user-input").style.display = "none";
    let jokeApi = parent.querySelector(".joke-api");
    parent.querySelector(".api-selection").style.display = "none";
    jokeApi.style.display = "block";
    fetch("/joke")
      .then((response) => response.json())
      .then((data) => {
        parent.querySelector("#joke-text").innerHTML = `${data[0].joke}`;
      });
  } else {
    let contId = element.parentNode.parentNode.parentNode.parentNode.id;
    boxId = Number(contId.slice(-1));
    let parent = document.getElementById(contId);
    let jokeApi = parent.querySelector(".joke-api");
    parent.querySelector(".api-selection").style.display = "none";
    jokeApi.style.display = "block";
    PostUserLogs(apiId, apiName, boxId, "joke");
    fetch("/joke")
      .then((response) => response.json())
      .then((data) => {
        parent.querySelector("#joke-text").innerHTML = `${data[0].joke}`;
      });
  }
}

function showApi(apiId) {
  if (apiId == 1) {
    getWeatherApi(boxId);
  }
  if (apiId == 2) {
    getPriceApi(boxId);
  }
  if (apiId == 3) {
    getHSLApi(boxId);
  }
  if (apiId == 4) {
    getStockPriceApi(boxId);
  }
  if (apiId == 5) {
    getJokeApi(boxId);
  } if (apiId == 6) {
    getTekstiTv()
  }
}

function PostUserLogs(apiId, apiName, boxId, apiValue) {
  let userId = sessionStorage.getItem("userId");
  let userInfo = { apiId: apiId, apiName: apiName, boxId: boxId, apiValue: apiValue, userId: userId, visible: true }
  HTTP.put("kojelauta/", userInfo).then((b) => {
  });
}

window.onload = function () {

  let userId = sessionStorage.getItem("userId");
  HTTP.get("kojelauta/" + userId).then(users => {
    users.forEach(dbUser => {
      apiName = dbUser.apiName
      apiId = dbUser.apiId
      boxId = dbUser.boxId
      apiValue = dbUser.apiValue
      visible = dbUser.visible
      showApi(apiId);
    });
  });
}