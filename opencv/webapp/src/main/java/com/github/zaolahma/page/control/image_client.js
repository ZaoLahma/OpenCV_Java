var websocket = null;

function connect() {
    var wsProtocol = window.location.protocol == "https:" ? "wss" : "ws";
    var wsURI = wsProtocol + '://' + window.location.host + '/${final.war.name}/websocket/helloName';
    websocket = new WebSocket(wsURI);

    websocket.onopen = function() {
        displayStatus('Open');
        document.getElementById('sayHello').disabled = false;
        displayMessage('Connection is now open. Type a name and click Say Hello to send a message.');
    };
    websocket.onmessage = function(event) {
        // log the event
        //displayMessage('The response was received! ' + event.data, 'success');
        displayImage(event.data);
    };
    websocket.onerror = function(event) {
        // log the event
        displayMessage('Error! ' + event.data, 'error');
    };
    websocket.onclose = function() {
        displayStatus('Closed');
        displayMessage('The connection was closed or timed out. Please click the Open Connection button to reconnect.');
        document.getElementById('sayHello').disabled = true;
    };
}

function disconnect() {
    if (websocket !== null) {
        websocket.close();
        websocket = null;
    }
    message.setAttribute("class", "message");
    message.value = 'WebSocket closed.';
    // log the event
}

function sendMessage() {
    if (websocket !== null) {
        var content = document.getElementById('name').value;
        websocket.send(content);
    } else {
        displayMessage('WebSocket connection is not established. Please click the Open Connection button.', 'error');
    }
}

function displayMessage(data, style) {
    var message = document.getElementById('hellomessage');
    message.setAttribute("class", style);
    message.value = data;
}

function displayImage(data) {
    var canvas = document.getElementById('image-canvas');
    var ctxt = canvas.getContext("2d");
    ctxt.clearRect(0, 0, canvas.width, canvas.height);
    let image = new Image();
    image.onload = function() {
        ctxt.drawImage(image, 0, 0);
    }
    image.src = 'data:image/png;base64,' + data;
}

function displayStatus(status) {
    var currentStatus = document.getElementById('currentstatus');
    currentStatus.value = status;
}