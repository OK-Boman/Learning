function getWeather(city) {
  HTTP.get("weather/" + city).then((weather) => {
    let temp = weather.temp + "°C";
    let icon = weather.icon;
    let iconUrl = weather.iconUrl;
    document.getElementById("city").innerHTML = city;
    document.getElementById("weather").innerHTML = temp;
    document.getElementById("weather-icon").src = iconUrl;
  });
}
setInterval(getWeather("Helsinki"), 180000);