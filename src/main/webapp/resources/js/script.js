/* <![CDATA[ */
var wsocket;

var serviceLocation = "ws://" + document.location.host + "/clubproject/chat/";
var $nickName;
var $message;
var $chatWindow;
var room = '';

function onMessageReceived(evt) {
	var msg = JSON.parse(evt.data);
	var $messageLine = $('<h4>' + msg.sender + '<em>' + msg.received + '</em></h4>'
			+ '<p> '+ msg.message +' </p><hr>');
//	var $messageLine = $('<tr><th class="user label label-info"><h5>' + msg.sender + '</h5>'
//			+ '</th><td class="received"><em>' + msg.received + '</em>'
//			+ '</td></tr><td class="message badge">' + msg.message + '</td>');
	$chatWindow.append($messageLine);
}
function sendMessage() {
	console.log(serviceLocation);
	var msg = '{"message":"' + $message.val() + '", "sender":"'
			+ $nickName.val() + '", "received":""}';
	wsocket.send(msg);
	$message.val('').focus();
}

function connectToChatserver() {
	room = 'java';
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
