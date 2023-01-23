function getTekstiTv() {
  let sivu = 100;
  let alisivu = 1;
  let url = "/tekstitv/" + sivu + "/" + alisivu;
  fetch(url)
    .then((response) => response.text())
    .then((data) => {
      document.querySelector("#tekstitv-img").src = "data:image/png;base64," + data;
    });
  return false;
}

setInterval(getTekstiTv(), 10000);
