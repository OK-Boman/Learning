

fetch('/spot-price-today')
    .then(response => response.json())
    .then(data => {
        document.getElementById("average-spot").innerHTML = `Average: ${data.AverageSpot} c/kWh`;
        document.getElementById("highest-spot").innerHTML = `High: ${data.HighestSpot} c/kWh`;
        document.getElementById("lowest-spot").innerHTML = `Low: ${data.LowestSpot} c/kWh`;
    });

fetch('/spot-price-tomorrow')
    .then(response => response.json())
    .then(data => {
        if (data.AverageSpot === undefined) {
            document.getElementById("average-spot-tomorrow").innerHTML = "After 15:00";
        } else {
            document.getElementById("average-spot-tomorrow").innerHTML = `Average: ${data.AverageSpot} c/kWh`;
        }
        if (data.HighestSpot === undefined) {
            document.getElementById("highest-spot-tomorrow").innerHTML = "After 15:00";
        } else {
            document.getElementById("highest-spot-tomorrow").innerHTML = `High: ${data.HighestSpot} c/kWh`;
        }
        if (data.LowestSpot === undefined) {
            document.getElementById("lowest-spot-tomorrow").innerHTML = "After 15:00";
        } else {
            document.getElementById("lowest-spot-tomorrow").innerHTML = `Low: ${data.LowestSpot} c/kWh`;
        }
    });