var ws;
var isAdminUser;
var user;
var userChatText = '';
var chats = new Array();

function showChat() {
    document.getElementById("div_chat").style.display = "block";
    document.getElementById("div_cart").style.display = "none";
}

function hideChat() {
    document.getElementById("div_chat").style.display = "none";
}


function startChatSession(name) {
    isAdminUser = (name == 'Bookstore');
    user = name;
    // alert("isAdminUser : " + isAdminUser);
    ws = new WebSocket("ws://localhost:8080/BookstoreFrontend/chat/" + name);

    ws.onmessage = onmessage;

}

var onmessage = function (event) {
    var chat = {};
    var jsonMessage = JSON.parse(event.data);
    var from = jsonMessage.from;
    var value = jsonMessage.value;
    var type = jsonMessage.type;

    if (type == 'ACTION') {
        alert('ACTION');
        removeChat(from);
        return;
    }

    if (isAdminUser) {
        if (from == 'Bookstore') {
            chat = getChat(getInputChatSelectorValue());
        } else { //customer from
            if (chatExists(from)) {
                chat = getChat(from);
            } else {
                chat.user = from;
                chat.conversation = '';
                chats[chats.length] = chat;
                addChatSelector(from); //lo hace visible en option
            }
            var inputChatSelector = document.getElementById('inputChatSelector');
            inputChatSelector.value = from;
        }
        chat.conversation = chat.conversation + '>' + from + ': ' + value + '\n';
        onChangeChatSelector();
    } else {
        userChatText = userChatText + '>' + from + ': ' + value + '\n';
        var outputChat = document.getElementById('outputChat');
        outputChat.innerHTML = userChatText;
    }

};

function sendMessage() {
    var jsonMessage = {};
    if (isAdminUser) {
        var chatSelector = getInputChatSelectorValue();
        jsonMessage.to = chatSelector;
    } else {
        jsonMessage.from = user;
    }

    var inputMessage = document.getElementById('inputMessage');
    var text = inputMessage.value;

    jsonMessage.value = text; //objeto propiedad value
    var jsonMessageStr = JSON.stringify(jsonMessage);

    ws.send(jsonMessageStr);
    inputMessage.value = '';
}

function endChatSession() {
    clearChat();
    ws.close();
}

/**********************
 * Util function
 * ********************
 */

function chatExists(chatSelector) {
    var i;
    for (i = 0; i < chats.length; i++) {
        if (chats[i].user == chatSelector) {
            return true;
        }
    }
    return false;
}

function addChatSelector(chatSelector) {
    var inputChatSelector = document.getElementById('inputChatSelector');
    var htmlOption = document.createElement("option");
    htmlOption.id = chatSelector;
    var htmlText = document.createTextNode(chatSelector);
    htmlOption.appendChild(htmlText);
    inputChatSelector.appendChild(htmlOption);

}
function getChat(chatSelector) {
    var i;
    for (i = 0; i < chats.length; i++) {
        if (chats[i].user == chatSelector) {
            return chats[i];
        }
    }
    return null;
}

function onChangeChatSelector() {
    var chatSelector = getInputChatSelectorValue();
    if(chatSelector != ''){
        setSpecificChat(chatSelector);
    }else{
        clearChat();
    }
}

function getInputChatSelectorValue() {
    var inputChatSelector = document.getElementById('inputChatSelector');
    return inputChatSelector.value;
}

function setSpecificChat(chatSelector) {
    var i;
    for (i = 0; i < chats.length; i++) {
        if (chats[i].user == chatSelector) {
            var conversation = chats[i].conversation;
            var outputChat = document.getElementById('outputChat');
            outputChat.innerHTML = conversation;
            break;
        }
    }
}

function clearChat() {
    userChatText = '';
    var outputChat = document.getElementById('outputChat');
    outputChat.innerHTML = '';

    var inputMessage = document.getElementById('inputMessage');
    inputMessage.value = '';
}

function removeChat(chatSelector) {
    removeChatObject(chatSelector);
    removeSelector(chatSelector);
    onChangeChatSelector();
}

function removeChatObject(chatSelector) {
    var i;
    for (i = 0; i < chats.length; i++) {
        if (chats[i].user == chatSelector) {
            chats.splice(i, 1);
            break;
        }
    }
}

function removeSelector(chatSelector) {
    var inputChatSelector = document.getElementById('inputChatSelector');
    var htmlOption = document.getElementById(chatSelector);
    inputChatSelector.removeChild(htmlOption);
}