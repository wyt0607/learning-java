const fs = require("fs");
const mdzz = fs.readdirSync("./src/assets/mdzz");
console.log(mdzz)
mdzz.forEach((e) => {
    fs.writeFileSync("./dist/" + e, fs.readFileSync("./src/assets/mdzz/" + e))
})