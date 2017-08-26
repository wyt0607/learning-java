/**
 * Created by wton on 2016/10/6.
 */
import axios from "axios"
import {initWebsocket} from "../websocket/websocketManager"
import {INIT_WEBSOCKET, MDZZ} from "./types"

const actions = {
    [INIT_WEBSOCKET]: ({commit}, websocketClient) => {
        initWebsocket(websocketClient, commit)
        commit("INIT_WEBSOCKET", websocketClient)
    },
    [MDZZ]: ({commit}, mdzz) => {
        axios.get(mdzz).then(response => {
            console.log(response)
            commit("MDZZ", response.data)
        }).catch(error => console.log(error))
    }
}

export default actions