function getHsl() {
    let address = "Keilaranta";
    document.getElementById("address").innerHTML = address;
    let html =""
    HTTP.get("hsl/"+address).then(hsls => {
        hsls.forEach(hsl => {
            html += `<tr class="hslRow">
            <td class = "tdtime">${hsl.time}</td>
            <td class = "tdstop">${hsl.stop}</td>
            <td class = "tdvehicle">${hsl.vehicle}</td></tr>`;
                });
        document.getElementById("hslTable").innerHTML = html;
    });
}
setInterval(getHsl(), 360000)