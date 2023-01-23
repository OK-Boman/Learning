class Api extends HTMLElement {
    connectedCallback() {
        this.innerHTML = `
    <div class="api-container">
    <input type="button" class="close-button" value=" X " onclick="return changeApi(this)"></button>
    <!-- API SELECTION -->
    <div class="api-selection" style="display: block;">
        Select what to display
        <br><br>
        <p onclick=apiSelection(this) id="weather-api-link">Weather</p>
        <p onclick=apiSelection(this) id="price-api-link">Price</p>
        <p onclick=apiSelection(this) id="hsl-api-link">HSL</p>
        <p onclick=apiSelection(this) id="stock-api-link">Stock</p>
        <p onclick=getJokeApi(this) id="joke-api-link">Joke</p>
    </div>
    <div class="user-input" style="display: none;">
        <!-- WEATHER API INPUT -->
        <div class="weather-selection" style="display: none;">
            <p id="api-selection-text">Enter a city</p><br>
            <form class="weather-form">
                <input type="text" id="user-input-text-weather">
                <input type="submit" onclick="return getWeatherApi(this)" hidden />
            </form>
        </div>
        <!-- PRICE API INPUT -->
        <div class="price-selection" style="display: none;">
            <p>Select what to track</p><br>
            <select id="price-selection" onChange="return getPriceApi(this)">
                <option disabled selected value> Select an option </option>
                <option value="electricity">Electricity</option>
                <option value="euribor">Euribor</option>
                <option value="inflation">Inflation</option>
            </select>
        </div>
        <!-- HSL API INPUT -->
        <div class="hsl-selection" style="display: none;">
            <p id="api-selection-text">Enter an address</p><br>
            <form class="hsl-form">
                <input type="text" id="user-input-text-hsl">
                <input type="submit" onclick="return getHSLApi(this)" hidden />
            </form>
        </div>
        <!-- STOCK API INPUT -->
        <div class="stock-selection" style="display: none;">
            <p id="api-selection-text">Enter a stock symbol</p><br>
            <form class="stock-form">
                <input type="text" id="user-input-text-stock">
                <input type="submit" onclick="return getStockPriceApi(this)" hidden />
            </form>
        </div>
    </div>
    <!-- WEATHER API CONTENT -->
    <div class="weather-api" style="display: none;">
        <div id="weather-api-content">
            <p id="city"></p>
            <p id="weather"></p>
            <img src="" id="weather-icon">
        </div>
    </div>
    <!-- PRICE API CONTENT -->
    <div class="price-api" style="display: none;">
        <div id="price-api-electricity-content" style="display: none;">
            <p>
            <h3>Electricity today</h3><br>
            <p class="average-spot"></p>
            <p class="highest-spot"></p>
            <p class="lowest-spot"></p>
            <br><h3>Electricity tomorrow</h3><br>
            <p class="average-spot-tomorrow">After 15:00</p>
            <p class="highest-spot-tomorrow">After 15:00</p>
            <p class="lowest-spot-tomorrow">After 15:00</p>
        </p>
        </div>
        <div id="price-api-euribor-content" style="display: none;">
        <p>
        <h3>Euribor</h3><br>
        <p class="euribor1"></p>
        <p class="euribor3"></p>
        <p class="euribor12"></p>
    </p>
    </div>
    <div id="price-api-inflation-content" style="display: none;">
    <p>
    <h3>Inflation</h3><br>
    <p class="inflation"></p>
    </p>
    </div>
    </div>
    <!-- HSL API CONTENT -->
    <div class="hsl-api" style="display: none;">
    <h3 id="address"></h3>
    <table id="table">
        <thead>
            <tr><th id="time">Time</th>
                <th id="stop">To</th>
                <th id="vehicle">Line </th></tr>
        </thead>
        <tbody id="hslTable">
        </tbody>
    </table>
    </div>
    <!-- STOCK API CONTENT -->
    <div class="stock-api" style="display: none;">
    <h3 id="stock"></h3>
        <div id="stock-api-content">
        </br><p id="stock-price"></p>
        </br><p id="stock-symbol"></p>
        </div>
    </div>
    <!-- JOKE API CONTENT -->
    <div class="joke-api" style="display: none;">
        <div id="joke-api-content">
            <p id="joke-text"></p>
        </div>
    </div>
          `;
    }
}

customElements.define("api-content", Api);
