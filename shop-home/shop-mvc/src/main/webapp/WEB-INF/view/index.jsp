<%--
  Created by IntelliJ IDEA.
  User: ASUS
  Date: 2019/11/20
  Time: 16:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link type="text/css" href="/js/css/style.css" rel="stylesheet" />
    <link rel="stylesheet" href="/js/layui-master/dist/css/layui.css"/>
    <link rel="stylesheet" href="/js/bootstrap-3.3.7-dist/css/bootstrap.css"/>
    <link rel="stylesheet" href="/js/DataTables-1.10.18/css/dataTables.bootstrap.css"/>
    <link rel="stylesheet" href="/js/DataTables-1.10.18/css/dataTables.bootstrap.css"/>
    <link rel="stylesheet" href="/js/zTree/css/demo.css"/>
    <link rel="stylesheet" href="/js/zTree/css/zTreeStyle/zTreeStyle.css"/>
    <link rel="stylesheet" href="/js/jsp/bootstrap-3.3.7-dist/css/bootstrap.css"/>
    <link href="/js/jsp/DataTables-1.10.18/css/dataTables.bootstrap.min.css" rel="stylesheet" type="text/css"/>
    <link rel="stylesheet" href="/js/jsp/bootstrap-datetimepicker/css/bootstrap-datetimepicker.min.css">
</head>
<body>
<!--左侧菜单-->
<div class="sec-mainL left">
    <div class="mainL-hd-box">
        <h2 class="mainL-hd"><a href="#">购物返彩贝商家</a></h2>
    </div>
    <!--菜单列表-->
    <ul class="sec-mainNav">

    </ul>
</div>
<div  style="margin-left:20px" class="left">
    <div class="row" id="categoryShow"></div>
    <div class="row" id="brandShow"></div>
</div>
<div class="container">
    <div class="row">
        <div class="col-md-12">
            <div class="panel panel-warning">
                <div class="panel-heading">商品查询</div>
                <div class="panel-body">
                    <form class="form-horizontal">
                        <div class="form-group">
                            <label class="col-sm-2 control-label">商品名称:</label>
                            <div class="col-sm-4">
                                <input type="text" class="form-control" id="shopName" placeholder="请输入商品名称...">
                            </div>
                            <label class="col-sm-2 control-label">商品ID:</label>
                            <div class="col-sm-4">
                                <input type="text" class="form-control" id="shopId" placeholder="请输入商品ID...">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-2 control-label">创建时间:</label>
                            <div class="col-sm-4">
                                <div class="input-group">
                                    <input type="text" class="form-control" id="mincreate" placeholder="最小创建时间..." aria-describedby="basic-addon2">
                                    <span class="input-group-addon" id="basic-addon2"><i class="glyphicon glyphicon-calendar"></i></span>
                                    <input type="text" class="form-control" id="maxcreate" placeholder="最大创建时间..." aria-describedby="basic-addon2">
                                </div>
                            </div>
                            <label class="col-sm-2 control-label">价格范围:</label>
                            <div class="col-sm-4">
                                <div class="input-group">
                                    <input type="text" class="form-control" id="minprice" placeholder="最小价格...">
                                    <span class="input-group-addon"><i class="glyphicon glyphicon-yen"></i></span>
                                    <input type="text" class="form-control" id="maxprice" placeholder="最大价格...">
                                </div>
                            </div>
                        </div>
                        <div style="text-align: center">
                            <button class="btn btn-primary" type="button" onclick="eche()"><i class="glyphicon glyphicon-search"></i>搜索</button>
                            <button class="btn btn-default" type="reset" onclick="querylist()"><i class="glyphicon glyphicon-refresh"></i>重置</button>
                        </div>
                    </form>
                </div>
            </div>
            <div style="background-color: #a2aec7;width: 100%">
                <button class="btn btn-primary" type="button" onclick="aaa()"><i class="glyphicon glyphicon-shopping-cart"></i>查看购物车 <span class="badge" id="cartNum">0</span></button>
            </div>
        </div>

    <div class="col-md-12">
        <div class="panel panel-warning">
            <div class="panel-heading">商品列表</div>
            <table id="a"  class="table table-striped table-bordered" style="width:100%">
                <thead>
                <tr>
                    <td>商品名称</td>
                    <td>价格</td>
                    <td>创建时间</td>
                    <td>图片</td>
                    <td>操作</td>
                </tr>
                </thead>
            </table>
        </div>
    </div>
</div>
</div>
</body>
<script src="/js/jquery-3.3.1.min.js"></script>
<script src="/js/layui-master/dist/layui.js"></script>
<script src="/js/zTree/js/jquery.ztree.all.js"></script>
<script src="/js/DataTables-1.10.18/js/jquery.dataTables.js"></script>
<script src="/js/bootstrap-3.3.7-dist/js/bootstrap.js"></script>
<script  src="/js/jsp/DataTables-1.10.18/js/jquery.dataTables.min.js"></script>
<script  src="/js/jsp/DataTables-1.10.18/js/dataTables.bootstrap.min.js"></script>
<script  src="/js/jsp/bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>
<script  src="/js/jsp/bootbox/bootbox.min.js"></script>
<script  src="/js/jsp/bootbox/bootbox.locales.min.js"></script>
<script  src="/js/jsp/bootstrap-datetimepicker/js/moment-with-locales.js"></script>
<script  src="/js/jsp/bootstrap-datetimepicker/js/bootstrap-datetimepicker.min.js"></script>
<script type="text/javascript">
    var  allCateList=[];
    var token="";
    if(sessionStorage.getItem("token")){
        token=sessionStorage.getItem("token");
    }
    $(function () {
        $.ajaxSetup({ //发送请求前触发
            contentType:"application/x-www-form-urlencoded;charset=utf-8",
            complete: function (XMLHttpRequest,textStatus) {
                var nologin=XMLHttpRequest.getResponseHeader("NOLONGIN");
                // alert("返回的状态码："+nologin);
               /* if (nologin=="5006" ) {
                    window.location.href="http://localhost:8080/";
                }*/
            },
            beforeSend: function (xhr) { //可以设置自定义标头
                // alert("获取的token值:"+token);
                xhr.setRequestHeader('token', token);
            }
        })
        intiCategory();
        var $top = $('.sec-mainNav').offset().top + $('.sec-mainNav').height()
        //左侧导航动画
        $('.sec-mainNav li').on('mouseenter', function () {
            var cateAndBrandHtml='<div class="menu-panel">';
            //大类别ID
            var pid=$(this).attr("pid");
            //查询该大类别下的所有小类别
            var cateHtml=queryCateByPid(pid);
            cateAndBrandHtml+=cateHtml;
            //查询该类别下的品牌
            var brandHtml=queryBrandByCateId(pid);
            cateAndBrandHtml+=brandHtml;
            cateAndBrandHtml+='<a href="" class="menu-panel-btn"><span>查看全部商家</span> <em></em></a>';
            cateAndBrandHtml+='</div>';
            $(this).append(cateAndBrandHtml)
            var $height = $(this).offset().top + $(this).find('.menu-panel').outerHeight();
            $(this).find('.menu-panel').show();
            if ($height - $top >= 0) {
                $(this).find('.menu-panel').css({
                    top: -($height - $top) + 'px'
                })
            }
        });
        $('.sec-mainNav li').on('mouseleave', function () {
            $(this).find('.menu-panel').hide();
        });

    });

    function intiCategory() {
        $.ajax({
            url: "/test/shopQuery.jhtml",
            dataType: "json",
            type: "get",
            async: false,
            success: function (result) {
                var obj = eval("("+result.a+")");
                console.info(obj.a);
                if (result.code == 200) {
                    var data = result.a;
                    allCateList=obj.data;
                    var cateHtml = "";
                    for (var i = 0; i < obj.data.length; i++) {
                        cateHtml += '<li pid="'+obj.data[i].shopId+'">';
                        cateHtml += '<h3>' + obj.data[i].shopName + '</h3>'
                        cateHtml += '<div class="menu-tab">'
                        var children = obj.data[i].children;
                        for (var j = 0; j < children.length; j++) {
                            if(j<3){
                                cateHtml += ' <a href="javascript:void(0)">' + children[j].shopName + '</a>';
                            }else{
                                break;
                            }
                        }
                        cateHtml += '<div class="fix"></div>';
                        cateHtml += '</div>';
                        cateHtml += '<span class="menu-more">更多</span>';
                        cateHtml += '</li>';
                    }
                    $(".sec-mainNav").html(cateHtml);
                }
            }
        })
    }

    function  queryCateByPid(pid) {
        var cateHtml=' <div class="menu-panel-hd">';
        cateHtml+=' <h4>热门分类</h4>';
        cateHtml+='  <div class="sub-group">';
        for(var i=0;i<allCateList.length;i++){
            if(pid == allCateList[i].shopId){
                var children=allCateList[i].children;
                for(var j=0;j<children.length;j++){
                    cateHtml+='<a href="javascript:void(0)" pcateId="'+pid+'" cateId="'+children[j].shopId+'" onclick="onclickCateOrBrand(this)">'+children[j].shopName+'</a>';
                }
                break;
            }
        }
        cateHtml+='</div>';
        cateHtml+='</div>';
        return cateHtml;
    }
    /**
     * 查询品牌信息
     */
    function queryBrandByCateId(pid){
        var brandHtml='<div class="menu-panel-bd">';
        brandHtml+="<ul>";
        $.ajax({
            url:"/brandtest/brandQuery.jhtml",
            dataType:"json",
            data:{"pid":pid},
            type:"get",
            async: false,
            success:function (result) {
                var obj = eval("("+result.a+")");
               // alert(JSON.stringify(obj.data[0].brandId))
                if(result.code == 200){
                    for(var i=0;i<obj.data.length;i++){
                        brandHtml+=" <li>";
                        brandHtml+='<a href="javascript:void(0)" id="jsw" pcateId="'+pid+'" brandId="'+obj.data[i].brandId+'" onclick="onclickCateOrBrand(this);"><img id="uu" src="'+obj.data[i].brandLogo+'" /></a> ';
                        brandHtml+=" </li>";
                    }
                }
            }

        })
        brandHtml+="</ul>";
        brandHtml+="</div>";
        return brandHtml;
    }

    /**
     * 点击类型触发的事件
     * @param obj
     */
    function  onclickCateOrBrand(obj) {

        var cateId=$(obj).attr("cateId");
        var src=$("#uu").attr("src");
        var pid = $("#jsw").attr("pcateId");
        var brandId = $("#jsw").attr("brandId");
        if(typeof (cateId) == "undefined"){
            cateId=null;
        }
        if(typeof (brandId) == "undefined"){
            brandId=null;
        }
        queryCateRightShow(pid,cateId);
        brandRightShow(pid,brandId);
        var v_param={};
        v_param.pid=pid;
        v_param.src=src;
        //v_param.brandId=brandId;
        dataTables.settings()[0].ajax.data=v_param;
        dataTables.ajax.reload();
    }


    //拼接品牌的数据
    function brandRightShow(pid,brandId){
        var brandHtml='<div class="menu-panel-bd">';
        brandHtml+="<ul>";
        $.ajax({
            url:"/brandtest/brandQuery.jhtml",
            dataType:"json",
            data:{"pid":pid},
            type:"get",
            async:false,
            success:function (result) {
                var obj = eval("("+result.a+")");
                if(result.code == 200){
                    var data=result.data;
                    for(var i=0;i<obj.data.length;i++){
                        brandHtml+=" <li>";
                        if(brandId == obj.data[i].brandId){
                            brandHtml+='<a class="aclass" href="javascript:void(0)" onclick="clickBrand(this)"><img src="'+obj.data[i].brandLogo+'" /></a> ';
                        }else {
                            brandHtml+='<a href="javascript:void(0)" onclick="clickBrand(this)"><img src="'+obj.data[i].brandLogo+'" /></a> ';
                        }
                        brandHtml+=" </li>";
                    }
                }
            }

        })
        brandHtml+="</ul>";
        brandHtml+="</div>";
        $("#brandShow").html(brandHtml);
    }
    //根据商品类别的大类拼出小类
    function  queryCateRightShow(pid,cateId) {
        var cateHtml='<div class="menu-panel-hd">';
        cateHtml+=' <h4>热门分类</h4>';
        cateHtml+='  <div class="sub-group">';
        for(var i=0;i<allCateList.length;i++){
            if(pid == allCateList[i].shopId){
                var children=allCateList[i].children;
                for(var j=0;j<children.length;j++){
                    if(children[j].shopId == cateId){
                        cateHtml+='<a class="aclass" href="javascript:void(0)" pcateId="'+pid+'" cateId="'+children[j].shopId+'" onclick="queryProduct(this)">'+children[j].shopName+'</a>';
                    }else{
                        cateHtml+='<a href="javascript:void(0)" pcateId="'+pid+'" cateId="'+children[j].shopId+'" onclick="queryProduct(this)" >'+children[j].shopName+'</a>';
                    }
                }
                break;
            }
        }
        cateHtml+='</div>';
        cateHtml+='</div>';
        $("#categoryShow").html(cateHtml);
    }

    function clickBrand(obj) {
        //首先切换样式
        var allLi=$(obj).parent(0).siblings();
        allLi.each(function(){
            $(this).find("a").removeClass("aclass");
        })
        $(obj).addClass("aclass");
       /* var v_param={};

        dataTables.settings()[0].ajax.data=v_param;
        dataTables.ajax.reload();*/
    }
</script>
<script>
    $(function () {
        initDatemin();
        initDatemax();
        querylist();
    })
</script>
<style>
    .aclass {
        border: 1px solid red;
    }
</style>
<script>
    function initDatemin() {
        $("#mincreate").datetimepicker({
            format: "YYYY-MM-DD",
            locale: "zh-CN",
            showClear: true
        })
    }function initDatemax() {
        $("#maxcreate").datetimepicker({
            format: "YYYY-MM-DD",
            locale: "zh-CN",
            showClear: true
        })
    }
    var dataTables;
    function querylist() {
        dataTables=$('#a').DataTable(
            {
                "language": {
                    "url": "/js/Chinese.json"
                },
                "processing":true,
                "serverSide" : true,
                "lengthMenu":[4],
                "destroy":true,
                "ajax": {
                    url:"/test/queryList.jhtml",
                    type: 'POST'
                },
                columns: [
                    { data: 'shopName' },
                    { data: 'price'},
                    { data: 'createtime'},
                    { data: 'imgPath',render:function (data, type, row, meta) {
                        if(data!=null){
                            return '<img src="'+data+'" title="'+row.subtitle+'">';
                        }else {
                            return "";
                        }
                        }},
                    { data: 'shopId',render:function (data, type, row, meta) {
                            return '<button class="btn btn-primary" type="button" onclick="addCarts('+data+')">加入购物车</button>';
                        }}
                ],
                "processing":true,
                bLengthChange: false,
                "serverSide" : true,
                "searching": false, //是否开启搜索
            }
        );
    };

    function aaa() {
        window.open("carts.html","_blank");
    }
    //加入购物车
    function addCarts(productId){
        if (token=="") {
            alert("请先登陆用户")
            window.location.href="http://localhost:8080/";
        }
        $.ajax({
            url:"/cart/cart.jhtml",
            dataType:"json",
            type:"post",
            data:{
                "productId":productId
            },
            success:function (result) {
                var obj = eval("("+result.data+")");
                    $("#cartNum").html(obj.data);
                    //alert("添加购物车成功");
            }
        })
    }
</script>
<script>
    function eche(){
        var shopName = $("#shopName").val();
        var shopId = $("#shopId").val();
        var minprice = $("#minprice").val();
        var maxprice = $("#maxprice").val();
        var mincreate = $("#mincreate").val();
        var maxcreate = $("#maxcreate").val();

        var v_param={};
        v_param.shopName=shopName;
        v_param.shopId=shopId;
        v_param.minprice=minprice;
        v_param.maxprice=maxprice;
        v_param.mincreate=mincreate;
        v_param.maxcreate=maxcreate;
        dataTables.settings()[0].ajax.data=v_param;
        dataTables.ajax.reload();
    }
</script>
</html>