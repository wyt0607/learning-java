export const initWebsocket = function (websocketClient, commit) {
    websocketClient.onopen = function (e) {
        console.log(e)
    }
    websocketClient.onmessage = function (e) {
        console.log(e)
        let {type} = JSON.parse(e.data);
        commit(type, e);
    }
    websocketClient.onclose = function (e) {
        console.log("websocket close")
    }
    websocketClient.onerror = function (e) {
        console.log("websocket error")
    }
}

