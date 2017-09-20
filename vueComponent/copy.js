const fs = require("fs");
const mdzz = fs.readdirSync("./src/assets/mdzz");
mdzz.forEach((e) => {
    fs.writeFileSync("./dist/" + e, fs.readFileSync("./src/assets/mdzz/" + e))
})