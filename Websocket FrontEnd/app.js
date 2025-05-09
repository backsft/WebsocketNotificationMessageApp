// app.js

let stompClient = null;
let username = localStorage.getItem('username');
document.getElementById('userDisplay').innerText = username;

function connect() {
    let socket = new SockJS('http://localhost:8080/ws'); // WebSocket connection to backend
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {
        console.log('Connected: ' + frame);

        // Subscribe to messages sent to this user (username)
        stompClient.subscribe('/topic/messages/' + username, function (message) {
            let msg = JSON.parse(message.body);
            showMessage(msg.fromUser + ": " + msg.content); // Display new message
        });
    });
}

// Send a message
function sendMessage() {
    let msg = {
        fromUser: username,
        toUser: document.getElementById('toUser').value,
        content: document.getElementById('messageContent').value
    };
    stompClient.send("/app/chat.send", {}, JSON.stringify(msg)); // Send message to backend
}

// Show message in the message list
function showMessage(message) {
    let li = document.createElement('li');
    li.className = 'list-group-item';
    li.innerText = message;
    document.getElementById('messageList').appendChild(li); // Add to message list
}

// Call connect function on page load to establish WebSocket connection
window.onload = connect;
