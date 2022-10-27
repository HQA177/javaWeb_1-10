let user = /^[a-zA-Z0-9_-]{6,16}$/;
let pass = /^[0-9a-zA-Z]{8,17}$/;
let email = /^[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(\.[a-zA-Z0-9_-]+)+$/;
let data = document.getElementsByClassName("errMess");
// 密码是否一致的事件
function mm(){
    let mm1 = document.getElementById("mm1").value;
    let mm2 = document.getElementById("mm2").value;
    if (mm1 == mm2){
        data[2].style.visibility = "hidden";
    }else {
        data[2].style.visibility="visible";
    }
}

// 密码正则的事件
function cd(){
    let mm1 = document.getElementById("mm1").value;
    if (pass.test(mm1)){
        data[1].style.visibility = "hidden";
    }else {
        data[1].style.visibility="visible";
    }
}

// 用户名正则的事件
let date;
function yhm(){
    let ipt_yhm = document.getElementById("ipt_yhm").value;
    if (user.test(ipt_yhm)){
        data[0].style.visibility ="hidden";
        let url = "/userHtml?user=findUserByName&name="+ipt_yhm
        axios.post(url).then(resp =>{
            if (resp.data > 0){
                data[0].innerText = "用户名已经存在";
                data[0].style.visibility ="visible";
                date = false;
                return false;
            }else {
                data[0].style.visibility ="hidden";
                date = true;
            }
        }).catch(err => {
            console.log(err)
        })

    }else {
        data[0].innerText = "用户名应为6~16位数组和字母组成";
        data[0].style.visibility="visible";
        date = false;
    }
}

// 邮箱正则的事件
function yx(){
    let ipt_yx = document.getElementById("ipt_yx").value;
    if (email.test(ipt_yx)){
        data[3].style.visibility ="hidden";
    }else {
        data[3].style.visibility="visible";
    }
}

// 判断所有的正则是否为true
let an = document.getElementById("an")
an.onclick = function (e){
    let mm1 = document.getElementById("mm1").value;
    let mm2 = document.getElementById("mm2").value;
    let ipt_yhm = document.getElementById("ipt_yhm").value;
    let ipt_yx = document.getElementById("ipt_yx").value;
    console.log(ipt_yzm.value)
    console.log(str)
    if ((mm1 === mm2) && (pass.test(mm1)) && (user.test(ipt_yhm)) && (email.test(ipt_yx)) && (ipt_yzm.value.toLowerCase() === str.toLowerCase()) && date){
        console.log(1);
    }else {
        e.preventDefault();
    }
}

// 验证码
let str;
let yzm = document.querySelector("#yzm");
let ipt_yzm = document.querySelector("#ipt_yzm");
// 验证码字符
let chars = "qwertyuiopasdfghjklzxcvbnmQWERTYUIOPASDFGHJKLZXCVBNM1234567890";
// 生成m-n的数字
function getRandom(m,n){
    return Math.floor(Math.random()*(n-m+1)+m);
}
//  验证码禁用文本选中
yzm.addEventListener("selectstart",function(e){
    e.preventDefault();
})
// 生成随机验证码
function run(){
    str = '';
    // 若验证码存在，则清除
    while(yzm.hasChildNodes()){
        yzm.removeChild(yzm.firstChild);
    }
    // 生成6位数的验证码
    for(let i=0;i<4;i++){
        let span = document.createElement('span');
        span.innerHTML = chars[getRandom(0,chars.length-1)]; //生成随机数，并取得对应值
        span.style.display = "inline-block";
        span.style.fontSize = getRandom(16,24)+"px";  //随机字体大小
        span.style.color = 'rgb('+getRandom(0,200)+','+getRandom(0,200)+','+getRandom(0,200)+')';  //随机字体颜色
        span.style.transform = 'translate('+getRandom(-5,5)+'px,'+getRandom(-5,5)+'px) rotate('+getRandom(-20,20)+'deg)'; //随机平移旋转
        str += span.innerHTML; //将str拼接，和input值对比
        yzm.appendChild(span);
    }
}
run(); //进入页面生成验证码
yzm.addEventListener("click",run);
ipt_yzm.addEventListener("blur",function(){
    if (ipt_yzm.value != false){
        if(ipt_yzm.value.toLowerCase() == str.toLowerCase()){   //转化为小写进行比较
            data[4].style.visibility="hidden";
        }else{
            data[4].style.visibility="visible";
        }
        // ipt_yzm.value = ""; //清空输入框
        // run();
    }
})