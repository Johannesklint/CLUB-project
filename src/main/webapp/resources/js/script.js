/* <![CDATA[ */
var wsocket;
var serviceLocation = "ws://localhost:8888/clubproject/chat/";
var $nickName;
var $message;
var $chatWindow;
var room = '';

function onMessageReceived(evt) {
	var msg = JSON.parse(evt.data);
	var $messageLine = $('<tr><td class="received">' + msg.received
			+ '</td><td class="user label label-info">' + msg.sender
			+ '</td><td class="message badge">' + msg.message + '</td></tr>');
	$chatWindow.append($messageLine);
}
function sendMessage() {
	var msg = '{"message":"' + $message.val() + '", "sender":"'
			+ $nickName.val() + '", "received":""}';
	wsocket.send(msg);
	$message.val('').focus();
}

function connectToChatserver() {
	room = 'java';
	console.log(room);
	wsocket = new WebSocket(serviceLocation + room);
	wsocket.onmessage = onMessageReceived;
}

$(document).ready(function() {
	connectToChatserver();	
	$nickName = $('#nickname');
	$message = $('#message');
	$chatWindow = $('#response');
	$('.chat-wrapper').show();
	$nickName.focus();

	$('#do-chat').submit(function(evt) {
		evt.preventDefault();
		sendMessage()
	});


});

/* ]]> */