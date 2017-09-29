/**
 * Created by WTON on 2017/4/17.
 */
const fs = require("fs");
var exec = require('child_process').exec;
const file = JSON.parse(fs.readFileSync('./package.json', 'utf-8'));
let cmdStr = 'yarn upgrade ';

Object.keys(file.dependencies).forEach(function (e) {
    cmdStr += e + ' ';
})

Object.keys(file.devDependencies).forEach(function (e) {
    cmdStr += e + ' ';
})

const cmds = [
    cmdStr
]
cmds.forEach((e)=>{
    console.log(e)
    exec(e,(error,stdout,stderror)=>{
        if (!error){
            console.log(error);
        }
        if(!stderror){
            console.log(stderror);
        }
        if (!stdout){
            console.log(stdout);
        }
    });
});
