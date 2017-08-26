/**
 * Created by wton on 2016/10/6.
 */
import {INIT_WEBSOCKET, SET_MESSAGE, MDZZ, SET_USERLIST} from "./types"

const mutations = {
    [INIT_WEBSOCKET]: (state, websocketClient) => {
        state.websocketClient = websocketClient;
    },
    [SET_MESSAGE]: (state, {data}) => {
        state.messages.push(JSON.parse(data))
    },
    [MDZZ]: (state, mdzz) => {
        state.mdzz = mdzz;
    },
    [SET_USERLIST]: (state, {data}) => {
        let {userList} = JSON.parse(data);
        state.userList = JSON.parse(userList);
    }
}

export default mutations