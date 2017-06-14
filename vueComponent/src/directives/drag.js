/**
 * Created by WTON on 2017/6/15.
 */
export default {
    bind: function (el) {
        if (!el.draggable) {
            el.draggable = true;
        }
        el.style.position = 'absolute'
    },
    inserted: function (el) {
        let disX = null;
        let disY = null;
        el.onmousedown = (el) => {
            disX = el.clientX - el.target.offsetLeft;
            disY = el.clientY - el.target.offsetTop;
            console.log(disX)
            console.log(disY)
        }
        el.ondragend = (el) => {
            console.log(el)
            el.target.style.left = el.x - disX + "px";
            el.target.style.top = el.y - disY + "px";
        }
    }
}