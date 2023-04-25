
function getPrice() {
    HTTP.get("stock-price/BINANCE:BTCEUR").then((price) => {
        let priceValue = price.c;
        let changeValue = price.dp;
        document.getElementById("stock-price").innerHTML = `Price: ${priceValue} €`;
        document.getElementById("stock-change").innerHTML = `Change: ${changeValue} %`;
    });
}
setInterval(getPrice(), 180000);


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

