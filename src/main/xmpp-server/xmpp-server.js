
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
            if(client.socket.nick==eval('('+message+')').sender) return; //ignore message client has sent and recive back. //TODO: think that using clinet.socket here is a HACK

            var from = eval('('+message+')').sender+'@localhost';
            var to = eval('('+message+')').recipient;
            var msg = eval('('+message+')').message;
            var xmppElement = new xmpp.Element('message', { "to": to, "from": from,  type: 'chat', 'xml:lang': 'ko' }).c('body').t(msg);
            client.send(xmppElement);
        }

        client.on('authenticate', function (opts, cb)
        {
            var nick = opts.username;
            client.socket = ClubProjectChatClient.newConnection('localhost:8080/clubproject/chat',cpccCallback,nick);
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
                var to = stanza.attrs.to;
                var toCpcid = to.substr(0, to.indexOf('@')); 
                if(msg)client.socket.send(toCpcid,msg);
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
