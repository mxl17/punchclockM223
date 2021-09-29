const URL = 'http://localhost:8080';

function login() {
    let username = document.getElementById("uname").value;
    let password = document.getElementById("psw").value;

    fetch(`${URL}/auth/login`, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: '{"password": "' + password + '", "username": "' + username + '"}'
    }).then((result) => {
        result.text().then((result) => {
            console.log(result);
            localStorage.setItem("token", result);
        });
        window.location = "http://localhost:8080";
    });
}