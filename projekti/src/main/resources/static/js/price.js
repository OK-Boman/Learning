
function getEuribor() {
    fetch("/euribor")
    .then((response) => response.json())
    .then((data) => {
      document.getElementById("euribor1").innerHTML = `1kk: ${data.non_central_bank_rates[1].rate_pct}%`;
      document.getElementById("euribor3").innerHTML = `3kk: ${data.non_central_bank_rates[3].rate_pct}%`;
      document.getElementById("euribor12").innerHTML = `12kk: ${data.non_central_bank_rates[4].rate_pct}%`;
    });
  }

function getInflation() {
    fetch("/inflation")
    .then((response) => response.text())
    .then((data) => {
        document.getElementById("inflation").innerHTML = data;
    });
}

function getPrice(symbol) {
    HTTP.get("stock-price/" + symbol).then((price) => {
        let priceValue = price.c;
        let changeValue = price.dp;
        document.getElementById("stock-name").innerHTML = `${symbol}`;
        document.getElementById("stock-price").innerHTML = `Price: ${priceValue} $`;
        document.getElementById("stock-change").innerHTML = `Change: ${changeValue} %`;
    });
}
setInterval(getPrice("AAPL"), 1000);



// bitcoin/usd; BINANCE:BTCBUSD
// bitcoin/eur; BINANCE:BTCEUR
// apple; aapl
// nasdaq-100; NDX
// dow jones; DJI
//microsft; MSFT
//google; GOOGL
//facebook; FB
//amazon; AMZN
//tesla; TSLA
//netflix; NFLX
//spotify; SPOT
//nokia; NOK

