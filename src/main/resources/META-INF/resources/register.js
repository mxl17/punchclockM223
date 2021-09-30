const URL = 'http://localhost:8080';
let users = [];

function register(e) {
    const user = {};
    user['username'] = document.getElementById("uname").value;
    user['password'] = document.getElementById("psw").value;

    fetch(`${URL}/users`,
        {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(user)
        }).then((result) => {
            result.json().then((user) => {
                if (user === false) {
                    document.getElementById("errorField").style.visibility = "visible";
                } else {
                    window.location = "http://localhost:8080/login.html"
                }
            });
    });
}