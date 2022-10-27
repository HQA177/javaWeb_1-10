// 获取登录按钮
let dl = document.getElementById("sub_btn");
// 获取登录按钮
let username = document.querySelector("#username");
// 获取登录按钮
let password = document.querySelector("#password");
// 获取提示标签
let ts = document.getElementById("ts");

function selectThis(username,password) {
    document.write("<form action=\"/userHtml?user=login_success\" method=post name=form1 style='display:none'>");
    document.write("<input type=hidden name=username value='"+username+"'/>");
    document.write("<input type=hidden name=password value='"+password+"'/>");
    document.write("</form>");
    document.form1.submit();
}


dl.onclick = function (){
    let name = username.value;
    let word = password.value;
    let url = "/userHtml?user=login_success&username=" + name + "&password=" +word;
    console.log(url)
    axios.post(url).then(resp =>{
        console.log(url)
        if (resp.data == 0){
            ts.style.visibility ="visible";
        }else {
            selectThis(name,word);
        }
    }).catch(err => {
        console.log(err)
    })
}