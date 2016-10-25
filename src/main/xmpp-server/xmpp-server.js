
var Client = require('./node-xmpp-client/index')
var xmpp = require('./node-xmpp-server/index')
var ClubProjectChatClient = require('./ClubProjectChatClient.js');

var startServer = function ()
{
    server = new xmpp.C2S.TCPServer({port: 5222,domain: 'localhost'})

    server.on('connection', function (client)
    {

        var cpccCallback = new Object();
        cpccCallback.onConnect = function()
        {

        }

        cpccCallback.onClose = function()
        {
            client.end();
        }

        cpccCallback.onIncomingMessage = function(message)
        {
            var from = eval('('+message+')').sender;
            var to = eval('('+message+')').recipient;
            var msg = eval('('+message+')').message;
            var xmppElement = new xmpp.Element('message', { "to": to, "from": from,  type: 'chat', 'xml:lang': 'ko' }).c('body').t(msg);
            client.send(xmppElement);
        }

        client.on('authenticate', function (opts, cb)
        {
            var nick = opts.username;
            client.socket = ClubProjectChatClient.newConnection('localhost:8080/clubproject/chat',cpccCallback,nick);
            console.log("nick"+nick);
            cb(null, opts)
        })

        client.on('online', function () 
        {

        })

        client.on('stanza', function (stanza)
        {
            if(stanza.name=="message")
            {
                var msg = stanza.children[0].children[0];
                if(msg)client.socket.send(stanza.attrs.to,msg);
            }
            else
            {
                var from = stanza.attrs.from
                stanza.attrs.from = stanza.attrs.to
                stanza.attrs.to = from
                client.send(stanza)
            }
        })

        client.on('disconnect', function ()
        {

        })
    })

    server.on('listening', function(){})
}

startServer();
