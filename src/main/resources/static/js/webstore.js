function shopping() {
    var id=$("#item_id").val();
    var name=$("#item_name").val();
    var price=$("#item_price").val();
    var count=1;
    var userId=$("#item_userId").val();
    var stock=$("#item_stock").val();
    var type=$("#item_type").val();
    var url="shop";
    shopBook(id,name,price,count,userId,stock,type,url);
}
// function deleteB() {
//     var productName=$("#productName").text();
//     return confirm("你确定要删除【"+productName+"】");
// }
function seckill() {
    var id=$("#seckill_id").val();
    var name=$("#seckill_name").val();
    var price=$("#seckill_price").val();
    var count=1;
    var userId=$("#seckill_userId").val();
    var stock=$("#seckill_stock").val();
    var type=$("#seckill_type").val();
    var url="seckill";
    shopBook(id,name,price,count,userId,stock,type,url);
}

//普通购买
function shopBook(id,name,price,count,userId,stock,type,url) {
    $.ajax({
        type:"POST",
        url:"/"+url,
        contentType:"application/json",
        success:function (data) {
            if(data.code==200){
                // window.location.reload();
                alert("购买成功，您的订单号是："+data.data);
            }else{
                    alert(data.data);
                    // var b = confirm(data.message);
                    // //自带的一个带有确认和取消的提示框，点击确认返回true，点击取消返回false
                    // if (b) {
                    //     localStorage.setItem("errorAfter","true");
                    //     window.open("https://github.com/login/oauth/authorize?client_id=589755417b2298a49fcb&redirect_uri=http://localhost:8888/callback&state=1");
                    // } else {
                    //     alert(data.message);
                    // }
            }
        },
        dataType:"json",
        data:JSON.stringify({
            "id":id,
            "name":name,
            "type":type,
            "price":price,
            "count":count,
            "userId":userId,
            "stock":stock
        })
    });
}


