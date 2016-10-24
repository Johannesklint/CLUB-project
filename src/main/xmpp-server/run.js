
var Client = require('./node-xmpp-client/index')
var xmpp = require('./node-xmpp-server/index')


// -----------------------------
var the_client = null;

var portal = Object();

portal.isLoginGood = function(username, password) {
	if(username === 'client1' && password === 'secret') return true;
	return false;
};

portal.sendMessage = function(from, to, message,client,stanza) {

    portal.onMessage(to,from,"echo "+message,client,stanza)
}

portal.onMessage = function(from, to, message,client,stanza) {
    var aaa = new xmpp.Element('message', { "to": to, "from": from,  type: 'chat', 'xml:lang': 'ko' }).c('body').t(message);
    the_client.send(aaa);
}
// ------------------

var startServer = function ()
{
    server = new xmpp.C2S.TCPServer({port: 5222,domain: 'localhost'})

    server.on('connection', function (client)
    {
        the_client = client;


        client.on('authenticate', function (opts, cb)
        {
            if (portal.isLoginGood(opts.username,opts.password))
            {
                cb(null, opts)
            }
            else
            {
                cb(false)
            }
        })

        client.on('online', function () 
        {

        })


        client.on('stanza', function (stanza)
        {
            if(stanza.name=="message")
            {
                portal.sendMessage(stanza.attrs.from,stanza.attrs.to,stanza.children[0].children[0],client,stanza);
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
