<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
response.setContentType("text/html;charset=UTF-8");
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html lang="en">

<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>基于Python的网络爬虫</title>
    <!-- Bootstrap Core CSS -->
    <link href="<%=path%>/bower_components/bootstrap/dist/css/bootstrap.min.css" rel="stylesheet">
    <!-- MetisMenu CSS -->
    <link href="<%=path%>/bower_components/metisMenu/dist/metisMenu.min.css" rel="stylesheet">
    <!-- Custom CSS -->
    <link href="<%=path%>/dist/css/sb-admin-2.css" rel="stylesheet">
    <!-- Custom Fonts -->
    <link href="<%=path%>/bower_components/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">


</head>

<body>

    <div id="wrapper">
        <div id="page-wrapper">
        
        	<a style="width: 850px" class="btn btn-block btn-social btn-bitbucket" href="<%=path%>/crawler/Question_getQuestionsByKey.action?content=${content}&index=${index}">
                 <i class="fa fa-bitbucket"></i> 返回
            </a>
            <div class="row" style="width: 850px">
                <div class="col-lg-12" align="left">
                    <h1 class="page-header">${question.question_name}
                    </h1>
                </div>
            </div>
            
            <!-- 遍历开始 -->
			<s:iterator value="#session.answers" var="answer">
			
				<s:if test='%{#answer.num=="0"}'>
	         		<div class="row">
		                <div class="col-lg-4">
		                    <div class="panel panel-green" style="width: 800px">
		                        <div class="panel-heading">
		                           	答案<s:property value="#answer.id"/>
		                        </div>
		                        <div class="panel-body">
									作者：<p><s:property value="#answer.author"/></p>
		                        </div>
		                        <div class="panel-body">
		                           	 内容：<p><s:property value="#answer.answer"/></p>
		                        </div>
		                        <div class="panel-footer">
		                           	支持数：<p><s:property value="#answer.vote_amount"/></p>
		                        </div>
		                    </div>
		                </div>
                
            		</div>
	      		</s:if>
	      		
	      		<s:elseif test='%{#answer.num=="1"}'>
	         		<div class="row">
		                <div class="col-lg-4">
		                    <div class="panel panel-yellow" style="width: 800px">
		                        <div class="panel-heading">
		                           	答案<s:property value="#answer.id"/>
		                        </div>
		                        <div class="panel-body">
									作者：<p><s:property value="#answer.author"/></p>
		                        </div>
		                        <div class="panel-body">
		                           	 内容：<p><s:property value="#answer.answer"/></p>
		                        </div>
		                        <div class="panel-footer">
		                           	支持数：<p><s:property value="#answer.vote_amount"/></p>
		                        </div>
		                    </div>
		                </div>
		            </div>
	      		</s:elseif>
	      		
	      		<s:else>
	      			<div class="row">
		                <div class="col-lg-4">
		                    <div class="panel panel-red" style="width: 800px">
		                        <div class="panel-heading">
		                           	答案<s:property value="#answer.id"/>
		                        </div>
		                        <div class="panel-body">
									作者：<p><s:property value="#answer.author"/></p>
		                        </div>
		                        <div class="panel-body">
		                           	 内容：<p><s:property value="#answer.answer"/></p>
		                        </div>
		                        <div class="panel-footer">
		                           	支持数：<p><s:property value="#answer.vote_amount"/></p>
		                        </div>
		                    </div>
		                </div>
		                
		            </div>
	      		</s:else> 
            
            </s:iterator>
			<!-- 遍历结束 -->
            
        </div>
    </div>

    <!-- jQuery -->
    <script src="../bower_components/jquery/dist/jquery.min.js"></script>

    <!-- Bootstrap Core JavaScript -->
    <script src="../bower_components/bootstrap/dist/js/bootstrap.min.js"></script>

    <!-- Metis Menu Plugin JavaScript -->
    <script src="../bower_components/metisMenu/dist/metisMenu.min.js"></script>

    <!-- Custom Theme JavaScript -->
    <script src="../dist/js/sb-admin-2.js"></script>

</body>

</html>

