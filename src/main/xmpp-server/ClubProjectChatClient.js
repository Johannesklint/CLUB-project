var WebSocketClient = require('websocket').client;


var ClubProjectChatClient = function()
{
};


ClubProjectChatClient.prototype.newConnection = function(host,o,nick)
{
    var newConnection = new Object();
    newConnection.nick = nick;
    newConnection.client = new WebSocketClient();
    newConnection.connection = null;

    newConnection.client.on('connect', function(c)
    {
        c.on('error', function(error)
        {
            console.log(error);
        });
        c.on('close', function()
        {
            o.onClose();
        });
        c.on('message', function(message)
        {
            if (message.type === 'utf8')
            {
                o.onIncomingMessage(message.utf8Data);
            }
        });

        newConnection.connection = c;

        o.onConnect();

    });   

    newConnection.client.on('connectFailed', function(error) {
    });

    newConnection.client.connect('ws://'+host+"//"+nick, null);

    newConnection.send = function(to,message)
    {
        var connection = newConnection.connection;

        if(connection && connection.connected)
        {
            var data = new Object();
            data.sender = newConnection.nick;
            data.message = message;
            data.recipient = to;
            data.chatRoom = ""; //TODO: this make this client not a chat room availble client. ok?
            connection.sendUTF(JSON.stringify(data));
        }
        else throw "can not send when not connected"

    };

    return newConnection;
}

module.exports = new ClubProjectChatClient();
