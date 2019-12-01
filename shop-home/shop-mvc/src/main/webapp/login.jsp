<%--
  Created by IntelliJ IDEA.
  User: ASUS
  Date: 2019/11/21
  Time: 20:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<input type="hidden" id="j" />
手机号:<input type="text" id="phone" /><br/>
验证码:<input type="password" id="codes" /><br/>
<input type="button" id="uu" value="获取验证码" onclick="f()" /><br/>
<div class="codeTime"><span>6</span><span>0</span></div><input type="button" value="登陆" onclick="logins()" /><br/>
<script src="/js/jquery-3.3.1.min.js"></script>
<script type="text/javascript">
    var obj;
    function f(){
        var phone = $("#phone").val();
        if(phone==""){
            alert("手机号不能为空")
            return false;
        }
        var a=document.getElementById("uu")
        a.remove();
            $.ajax({
            url:"/testUser/login.jhtml",
            type:"post",
            data:{"phone":phone},
            async:false,
            success:function (result) {
                var obj = eval("("+result.code+")");
                console.info(obj.a);
                if(obj.code==200){
                   obj = obj.obj;
                    $("#j").val(obj);
                }
            },error:function () {

            }
        })
        var time = 60;
        var timer = setInterval(function () {
            time--;
            var str = ("000"+time).slice(-2);
            $(".codeTime span").text(function(i){return str[i];});
            if (time==0) {
                clearInterval(timer)
                alert("超时了")
               // history.go(0);
            }
        }, 1000);
    };
    function logins() {
        var j = $("#j").val();
        var phone = $("#phone").val();
        var codes = $("#codes").val();
        if (phone == "") {
            alert("手机号不能为空")
            return false;
        }
        if (codes == "") {
            alert("验证码不能为空")
            return false;
        }
        $.ajax({
            url: "/testUser/logins.jhtml",
            type: "post",
            data: {"phone": phone, "checkCode": codes},
            async: false,
            success: function (result) {
                var obj = eval("(" + result.code + ")");
                //alert(JSON.stringify(obj.data))
                sessionStorage.setItem("token", obj.data);
                window.location.href = "/test/aaa.jhtml";
                /* var obj = eval("("+result.code+")");
                 console.info(obj.code);
                 var datas=obj;*/
                //alert(datas);
            }, error: function () {

            }
        })
    }
</script>
</body>
</html>
