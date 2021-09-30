const URL = 'http://localhost:8080';
let entries = [];

const dateAndTimeToDate = (dateString, timeString) => {
    return new Date(`${dateString}T${timeString}`).toISOString();
};

const createEntry = (e) => {
    e.preventDefault();
    const formData = new FormData(e.target);
    const entry = {};
    entry['checkIn'] = dateAndTimeToDate(formData.get('checkInDate'), formData.get('checkInTime'));
    entry['checkOut'] = dateAndTimeToDate(formData.get('checkOutDate'), formData.get('checkOutTime'));
    entry['category'] = {"id" : document.getElementById("category").value};
    entry['workspace'] = {"id" : document.getElementById("workspace").value};
    entry['user'] = {"id" : localStorage.getItem("id")};

    fetch(`${URL}/entries`,
        {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
                'Authorization': 'Bearer ' + localStorage.getItem("token")
            },
            body: JSON.stringify(entry)
        }).then((result) => {
        result.json().then((entry) => {
            entries.push(entry);
            renderEntries();
        });
    });
};

const indexEntries = () => {
    let token = localStorage.getItem("token");
    fetch(`${URL}/entries`, {
        method: 'GET',
        headers: {
            'Authorization': 'Bearer ' + token
        }
    }).then((result) => {
        result.json().then((result) => {
            entries = result;
            renderEntries();
        });
    });
    renderEntries();
};

fetch(`${URL}/entries`, {
    headers: {
        'Authorization': 'Bearer ' + localStorage.getItem("token")
    }
}).then(function (response) {
    if (response.status === 401) {
        window.location = "/login.html";
    }
});

const createCell = (text) => {
    const cell = document.createElement('td');
    cell.innerText = text;
    return cell;
};

const renderEntries = () => {
    const display = document.querySelector('#entryDisplay');
    display.innerHTML = '';
    entries.forEach((entry) => {
        const row = document.createElement('tr');
        row.appendChild(createCell(entry.id));
        row.appendChild(createCell(new Date(entry.checkIn).toLocaleString()));
        row.appendChild(createCell(new Date(entry.checkOut).toLocaleString()));
        row.appendChild(createCell(entry.category.title));
        row.appendChild(createCell(entry.workspace.title));
        let btn = document.createElement("button");
        btn.innerText = "Delete";
        btn.onclick = () => {
            deleteEntry(entry.id)
        }
        row.appendChild(btn);
        display.appendChild(row);
    });
};

function deleteEntry(id) {
    fetch(`${URL}/entries/${id}`, {
        method: 'DELETE',
        headers: {
            'Authorization': 'Bearer ' + localStorage.getItem("token")
        }
    });
    location.reload();
}

document.addEventListener('DOMContentLoaded', function(){
    const createEntryForm = document.querySelector('#createEntryForm');
    createEntryForm.addEventListener('submit', createEntry);
    indexEntries();
});

function logout() {
    localStorage.setItem("token", "");
    localStorage.setItem("id", "");
    location.reload();
}

function loadCategory() {
    fetch(`${URL}/categories`, {
        method: 'GET',
        headers: {
            'Authorization': 'Bearer ' + localStorage.getItem("token")
        }
    }).then((result) => {
        result.json().then((result) => {
            result.forEach((result) => {
                let option = new Option(result.title);
                option.value = result.id;
                document.getElementById("category").add(option);
            })
        })
    })
}

function loadWorkspace() {
    fetch(`${URL}/workspaces`, {
        method: 'GET',
        headers: {
            'Authorization': 'Bearer ' + localStorage.getItem("token")
        }
    }).then((result) => {
        result.json().then((result) => {
            result.forEach((result) => {
                let option = new Option(result.title);
                option.value = result.id;
                document.getElementById("workspace").add(option);
            })
        })
    })
}