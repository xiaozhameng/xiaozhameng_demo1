<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>MaiMangXiaoZhaMeng</title>

    <!-- Bootstrap -->
    <link href="/static/boostrap/bootstrap-3.3.0-dist/dist/css/bootstrap.min.css" rel="stylesheet">

    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
    <script src="http://cdn.bootcss.com/html5shiv/3.7.2/html5shiv.min.js"></script>
    <script src="http://cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>
<body>
    <%--导航栏--%>
    <nav class="navbar navbar-default navbar-fixed-top" role="navigation">
        <div class="container-fluid">
            <%--网站Logo--%>
            <div class="navbar-header">
                <a class="navbar-brand" href="#">
                    MaimangXiaoZhaMeng
                </a>
            </div>
            <ul class="nav navbar-nav">
                <li class="active"><a href="#">首页 <span class="sr-only">(current)</span></a></li><!--普通菜单-->
                <li><a href="#">婚庆案例</a></li>
                <li><a href="#">婚庆知识</a></li>
                <li><a href="#">实力展示</a></li>
                <li><a href="#">留言板</a></li>
                <li><a href="#">关于我们</a></li>
            </ul>
            <%--form表单--%>
            <form class="navbar-form navbar-left" role="search">
                <div class="form-group">
                    <input type="text" class="form-control" placeholder="检索信息">
                </div>
                <button type="submit" class="btn btn-default">检索</button>
            </form>
            <ul class="nav navbar-nav navbar-right">

                <li><a href="#">登录</a></li>
                <li><a href="#">注册</a></li>
            </ul>
        </div>
    </nav>

    <%--全景轮播图--%>
    <div id="carousel-example-generic" class="carousel slide" data-ride="carousel">
        <!-- ol指示器  ol标签与ul标签不同 ol为是有序列表 ul为是无序列表 -->
        <ol class="carousel-indicators">
           <!-- 指示器 -->
           <li data-target="#carousel-example-generic" data-slide-to="0" class="active"></li>
           <li data-target="#carousel-example-generic" data-slide-to="1"></li>
           <li data-target="#carousel-example-generic" data-slide-to="2"></li>
           <li data-target="#carousel-example-generic" data-slide-to="3"></li>
        </ol>

        <!-- 包装的轮播图片-->
        <div class="carousel-inner" role="listbox">
            <!--图片-->
            <div class="item active">
                <img src="http://test.sharegis.cn/Images/1.jpg" alt="风景1">
                <div class="carousel-caption">
                    <!--h4中的内容显示到图片上面，-->
                    <h4>真正的才智是刚毅的志向。 —— 拿破仑</h4>
                </div>
            </div>
            <div class="item">
                <img src="http://test.sharegis.cn/Images/2.jpg" alt="风景2">
                <div class="carousel-caption">
                    <h4>志向不过是记忆的奴隶，生气勃勃地降生，但却很难成长。 —— 莎士比亚</h4>
                </div>
            </div>
            <div class="item">
                <img src="http://test.sharegis.cn/Images/3.jpg" alt="风景3">
                <div class="carousel-caption">
                    <h4>志向和热爱是伟大行为的双翼。 —— 歌德</h4>
                </div>
            </div>
            <div class="item">
                <img src="http://test.sharegis.cn/Images/4.jpg" alt="风景4">
                <div class="carousel-caption">
                    <h4>生活有度，人生添寿。 —— 书摘</h4>
                </div>
            </div>
        </div>
        <!-- Controls -->
        <a class="left carousel-control" href="#carousel-example-generic" role="button" data-slide="prev">
            <span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
            <span class="sr-only">Previous</span>
        </a>
        <a class="right carousel-control" href="#carousel-example-generic" role="button" data-slide="next">
            <span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
            <span class="sr-only">Next</span>
        </a>
    </div>

    <%--网格布局系统--%>
    <div class="container">
        <div class="hd04">
            <a href="http://www.whxmt.com/c/news/" title="查看更多 最新活动" style="border-width: 5px;">
               <h2 class="text-center"> 最新活动-ACTIVITYS</h2>
            </a>
            <p class="text-center">
                xiaozhameng 是一自强不息的少年，他凭借自身不卑不亢的品质和吃苦耐劳的精神，在帝都的雾霾中拼命生长···
            </p>
            <p class="text-center">
                They say a person needs just three things to be truly happy in this world:someone to love,something to do,and something to hope for.
            </p>
        </div>
        <div class="row">
            <div class="col-md-6">
                <img src="static/images/sample/14342750190.jpg" class="img-responsive">
                <p class="text-left">
                    郭大哥不喜欢这个网站
                </p>
            </div>
            <div class="col-md-6">
                <img src="static/images/sample/14352258400.jpg" class="img-responsive">
                <p class="text-left">
                    小张颖可能喜欢
                </p>
            </div>
        </div>
        <div class="row">
            <div class="col-md-4">关心问题一</div>
            <div class="col-md-4">关心问题二</div>
            <div class="col-md-4">关心问题三</div>
        </div>
        <div class="row">
            <p class="text-center">案例CASE</p>
            <p class="text-center">案例说明-----------------------</p>
        </div>
        <div class="row">
            <div class="col-md-4">
                <img src="static/images/caseImage/14686371240.jpg" class="img-responsive">
                <p>这是一张图片</p>
            </div>
            <div class="col-md-4">
                <img src="static/images/caseImage/14686376120.jpg" class="img-responsive">
                <p>这是一张图片</p>
            </div>
            <div class="col-md-4">
                <img src="static/images/caseImage/14686469770.jpg" class="img-responsive">
                <p>这是一张图片</p>
            </div>
    </div>
<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<script src="http://cdn.bootcss.com/jquery/1.11.1/jquery.min.js"></script>
<!-- Include all compiled plugins (below), or include individual files as needed -->
<script src="/static/boostrap/bootstrap-3.3.0-dist/dist/js/bootstrap.min.js"></script>
</body>
</html>