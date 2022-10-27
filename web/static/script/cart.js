function update(id,bookCount){
    if (bookCount < 1){
    window.location.href = "cartHtml?cart=updateCartItem&id="+id+"&bookCount=1";
}else {
    window.location.href = "cartHtml?cart=updateCartItem&id="+id+"&bookCount="+bookCount;
}
}
    // 获取结账按钮
    let an = document.getElementById("an");
    let cd = document.querySelectorAll(".cd");
    an.onclick = function (){
    if (cd.length == 0){
    alert("没有要结的账！")
}else {
    window.location.href = "orderHtml?order=createOrder";
}
}