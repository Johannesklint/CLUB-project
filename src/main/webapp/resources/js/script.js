/* <![CDATA[ */
var wsocket;

var serviceLocation = "ws://" + document.location.host + "/clubproject/chat/";
var $nickName;
var $message;
var $chatWindow;
var room = '';
var adr = '';

function onMessageReceived(evt) {
	var msg = JSON.parse(evt.data);
	var $messageLine = jq$('<h4>' + msg._senderFullName + ' | <em>' + msg.received + '</em></h4>'
			+ '<p> '+ msg.message +' </p><hr>');
//	var $messageLine = jq$('<tr><th class="user label label-info"><h5>' + msg.sender + '</h5>'
//			+ '</th><td class="received"><em>' + msg.received + '</em>'
//			+ '</td></tr><td class="message badge">' + msg.message + '</td>');
	$chatWindow.append($messageLine);
	
	scrollToChatContainerBottom();
}
function sendMessage() {
	console.log(adr);
	
	var data = new Object();
	data.message = $message.val()
	data.sender = jq$('#from-cpcid').val();
	data.received = "";
	data.recipient = jq$('#to-cpcid').val();
	data.chatRoom = jq$('#chat-room').val();

	if(data.recipient===undefined)data.recipient=""
	if(data.chatRoom===undefined)data.chatRoom=""
	
	var msg = JSON.stringify(data);
	wsocket.send(msg);
	$message.val('').focus();
}

function connectToChatserver() {
	room = jq$('#chat-room').val();
	var cpcid = jq$('#from-cpcid').val();
	if(room===undefined)room="";

	wsocket = new WebSocket(serviceLocation + room+"/"+cpcid);
	adr = serviceLocation + room+"/"+cpcid;
	wsocket.onmessage = onMessageReceived;
}

function scrollToChatContainerBottom() {
	jq$('#response').stop().animate({
		scrollTop: jq$('#response')[0].scrollHeight
	}, 800);
}

jq$(document).ready(function() {
	connectToChatserver();	
	$nickName = jq$('#nickname');
	$message = jq$('#message');
	$chatWindow = jq$('#response');
	jq$('.chat-wrapper').show();
	$nickName.focus();

	jq$('#do-chat').submit(function(evt) {
		evt.preventDefault();
		sendMessage()
	});


});



/* ]]> */
