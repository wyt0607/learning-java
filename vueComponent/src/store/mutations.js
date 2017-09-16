/**
 * Created by wton on 2016/10/6.
 */
import {INIT_WEBSOCKET, SET_MESSAGE, MDZZ, SET_USERLIST, SET_PRIVATE_MESSAGE} from "./types"

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
        console.log(data)
        let {userList} = JSON.parse(data);
        state.userList = JSON.parse(userList);
    },
    [SET_PRIVATE_MESSAGE]: (state, {data}) => {
        console.log(data)
        state.priMessages.push(JSON.parse(data))
    },
    SET_NAME: (state, name) => {
        console.log(name)

        state.name = name
    }
}

export default mutations