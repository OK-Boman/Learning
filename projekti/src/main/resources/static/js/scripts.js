function makeTheCall(url, method, data) {
    return new Promise(function (resolve, reject) {
        let x = new XMLHttpRequest();
        x.onreadystatechange = function () {
            if (x.readyState == 4) {
                try {
                    let obj = JSON.parse(x.responseText);
                    resolve(obj);
                } catch (ex) {
                    reject(x);
                }
            }
            ;
        }
        x.open(method,url, true);
        x.setRequestHeader("Content-Type", "application/json");
        x.setRequestHeader("Accept", "application/json");
        x.send(data && JSON.stringify(data));
    });
}

let HTTP = {
    get: url => makeTheCall(url, "get"),
    put: (url, data) => makeTheCall(url, "put", data),
    post: (url, data) => makeTheCall(url, "post", data),
    delete: (url) => makeTheCall(url, "delete")
};

function postUser(userId, userEmail) {
    let user = { userId: userId, email: userEmail}            
    HTTP.post("/kojelauta/", user).then(b => {
    });
}