
function updateList() {
    let request = new XMLHttpRequest();

    request.onreadystatechange = function() {
        if(this.readyState === 4 && this.status === 200) {
            let result = JSON.parse(this.response);

            let table = document.getElementById('tickets');

            let oldTBody = table.tBodies[0];
            let newTBody = document.createElement("tBody");

            for(let i = 0; i < result.length; i++) {
                let bRow = document.createElement("tr");

                let tdOneWay = document.createElement('td');
                tdOneWay.innerHTML = result[i].oneWay;
                let tdFrom = document.createElement('td');
                tdFrom.innerHTML = result[i].from
                let tdTo = document.createElement('td');
                tdTo.innerHTML = result[i].to
                let tdDepart = document.createElement('td');
                tdDepart.innerHTML = result[i].departDate
                let tdReturn = document.createElement('td');
                let tdCompany = document.createElement('td');
                tdCompany.innerHTML = '<a href="https://www.google.com/search?q=' + result[i].aviationCompanyName + '">'
                    + result[i].aviationCompanyName + '</a>';

                let returnDate = result[i].returnDate;
                if(returnDate === null || returnDate === undefined) {
                    tdReturn.innerHTML = '/'
                } else {
                    tdReturn.innerHTML = result[i].returnDate;
                }

                bRow.appendChild(tdOneWay)
                bRow.appendChild(tdFrom)
                bRow.appendChild(tdTo)
                bRow.appendChild(tdDepart)
                bRow.appendChild(tdReturn)
                bRow.appendChild(tdCompany)

                newTBody.appendChild(bRow);
            }

            table.replaceChild(newTBody, oldTBody);
        }
    }

    let select = document.getElementById('ticket-type');

    if(select.options[select.selectedIndex].value === 'all') {
        request.open('GET', '/tickets', false);
    } else {
        if(select.options[select.selectedIndex].value === 'one-way') {
            request.open('GET', '/tickets?oneWay=true', false);
        } else {
            request.open('GET', '/tickets?oneWay=false', false);
        }
    }

    request.send();


}

function verifyFields() {
    if(document.getElementById('from').value === null || document.getElementById('from').value === '') {
        return false;
    }

    if(document.getElementById('to').value === null || document.getElementById('to').value === '') {
        return false;
    }

    let select = document.getElementById('create-ticket-type')
    if(select.options[select.selectedIndex].value === 'one-way' && document.getElementById('return').value !== '') {
        return false;
    }
    return  true;
}

function createNewTicket(e) {
    if (e.preventDefault) e.preventDefault();

    if(verifyFields()) {
        let select = document.getElementById('create-ticket-type');
        let oneWay = select.options[select.selectedIndex].value === 'one-way';
        let requestPayload = {
            'from': document.getElementById('from').value,
            'to': document.getElementById('to').value,
            'oneWay': oneWay,
            'aviationCompanyId': parseInt(document.getElementById('company')
                .options[document.getElementById('company').selectedIndex].value),
            'departDate': document.getElementById('depart').value,
            'returnDate': document.getElementById('return').value === '' ?
                null : document.getElementById('return').value
        }

        console.log(requestPayload);

        let request = new XMLHttpRequest();

        request.onreadystatechange = function() {
            if (this.readyState === 4 && this.status === 200) {
                let result = JSON.parse(this.response);
                if(!result.status) {
                    alert(result.message);
                } else {
                    updateList();
                }
            }
        };

        request.open('POST', '/tickets', true);
        request.setRequestHeader('Content-Type', 'application/json');

        request.send(JSON.stringify(requestPayload))
    } else {
        alert("Incorrect content in fields")
    }

    return false;
}

window.onload = function () {
 updateList();
};

document.getElementById('ticket-type').addEventListener('change', function f() {
    updateList();
});

document.getElementById('create-ticket').addEventListener('submit', createNewTicket)