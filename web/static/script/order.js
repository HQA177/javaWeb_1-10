// 主页面查询页数跳转确定按钮
let btn2 = document.getElementById("btn2");
// 跳转的页数
let tz = document.getElementById("tz");
// 后端获取的最大页数
let yc = document.getElementById("yc");


btn2.onclick = function (){
    let val = tz.value;
    let number = parseInt(val);
    if (yc.value >= number && number > 0){
        page(val)
    }else {
        alert('没有此页面');
        tz.value = " ";
    }
}

function page(pageNo){
    window.location.href="/orderHtml?order=getOrderCount&orderPageNo="+pageNo;
}

// 获取当前li(页码)
let active = document.querySelector(".active");
let e = document.querySelector(".e");
// 让当前页码和其上下兄弟显示
active.style.display = "block";
if(active.innerText == "1"){
    active.nextElementSibling.style = "block";
    active.nextElementSibling.nextElementSibling.style = "block";
    active.nextElementSibling.nextElementSibling.nextElementSibling.style = "block";
    active.nextElementSibling.nextElementSibling.nextElementSibling.nextElementSibling.style = "block";
}else if(active.innerText == e.innerText){
    active.previousElementSibling.style = "block";
    active.previousElementSibling.previousElementSibling.style = "block";
    active.previousElementSibling.previousElementSibling.previousElementSibling.style = "block";
    active.previousElementSibling.previousElementSibling.previousElementSibling.previousElementSibling.style = "block";
}else if(active.innerText == "2"){
    active.previousElementSibling.style = "block";
    active.nextElementSibling.style = "block";
    active.nextElementSibling.nextElementSibling.style = "block";
    active.nextElementSibling.nextElementSibling.nextElementSibling.style = "block";
}else if(Number(active.innerText) == Number(e.innerText)-1){
    active.previousElementSibling.style = "block";
    active.previousElementSibling.previousElementSibling.style = "block";
    active.previousElementSibling.previousElementSibling.previousElementSibling.style = "block";
    active.nextElementSibling.style = "block";
}else{
    active.previousElementSibling.previousElementSibling.style = "block";
    active.previousElementSibling.style = "block";
    active.nextElementSibling.style = "block";
    active.nextElementSibling.nextElementSibling.style = "block";
}