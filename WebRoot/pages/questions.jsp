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

    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
        <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->

</head>

<body>

    <div id="wrapper">
        <div id="page-wrapper">
        
        	<a style="width: 850px" class="btn btn-block btn-social btn-bitbucket" href="<%=path%>/crawler/Question_index.action">
                 <i class="fa fa-bitbucket"></i> 返回
            </a>
            <div class="row" style="width: 900px">
                <div class="col-lg-12">
                	<%
                		String flag = session.getAttribute("flag").toString();
                		String content = session.getAttribute("content").toString();
                		if(flag.equals("1")){
                			out.print("<h1 class='page-header'>主题："+content+"</h1>");
                		} else{
                			out.print("<h1 class='page-header'>关于“"+content+"”的问题</h1>");
                		}
                	%>
                </div>
            </div>
            
            <%
            	//分页
				String index = String.valueOf(session.getAttribute("index"));
            	String total = String.valueOf(session.getAttribute("total"));
            	int allNum = Integer.parseInt(total);
				int num = Integer.parseInt(index);
				int up = num-1;
				int down = num +1;
			%>
            
            
            <div class="panel-body">
            <div class="dataTable_wrapper">
            <table class="table table-striped table-bordered table-hover" id="dataTables-example">
            <!-- 遍历开始 -->
			<s:iterator value="#session.questions" var="question">
			
				<s:if test='%{#question.num=="0"}'>
	         		<div class="row">
		                <div class="col-lg-4">
		                    <div class="panel panel-green" style="width: 800px">
		                        <div class="panel-heading">
		                           	问题<s:property value="#question.id"/>
		                        </div>
		                        <div class="panel-body">
		                            <p><s:property value="#question.question_name"/></p>
		                        </div>
		                        <div class="panel-footer">
		                           	 <a class="btn btn-block btn-social btn-dropbox" href="<%=path%>/crawler/Question_getAnswers.action?questionid=<s:property value='#question.link_id'/>&content=${content}&index=${index}">
		                                <i class="fa fa-dropbox"></i> 查看答案
		                            </a>
		                        </div>
		                    </div>
		                </div>
                
            		</div>
	      		</s:if>
	      		
	      		<s:elseif test='%{#question.num=="1"}'>
	         		<div class="row">
		                <div class="col-lg-4">
		                    <div class="panel panel-yellow" style="width: 800px">
		                        <div class="panel-heading">
		                           	问题<s:property value="#question.id"/>
		                        </div>
		                        <div class="panel-body">
		                            <p><s:property value="#question.question_name"/></p>
		                        </div>
		                        <div class="panel-footer">
		                           	 <a class="btn btn-block btn-social btn-dropbox" href="<%=path%>/crawler/Question_getAnswers.action?questionid=<s:property value='#question.link_id'/>&content=${content}&index=${index}">
		                                <i class="fa fa-dropbox"></i> 查看答案
		                            </a>
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
		                           	问题<s:property value="#question.id"/>
		                        </div>
		                        <div class="panel-body">
		                            <p><s:property value="#question.question_name"/></p>
		                        </div>
		                        <div class="panel-footer">
		                           	 <a class="btn btn-block btn-social btn-dropbox" href="<%=path%>/crawler/Question_getAnswers.action?questionid=<s:property value='#question.link_id'/>&content=${content}&index=${index}">
		                                <i class="fa fa-dropbox"></i> 查看答案
		                            </a>
		                        </div>
		                    </div>
		                </div>
		                
		            </div>
	      		</s:else> 
            
            </s:iterator>
			<!-- 遍历结束 -->
			
			
			
			</table>
			</div>
				<nav>
				  <ul class="pagination">
				    <li><a href="<%=path%>/crawler/Question_getQuestionsByKey.action?index=<%=up%>&content=${content}">上一页</a></li>
				    <li><a href="<%=path%>/crawler/Question_getQuestionsByKey.action?index=<%=down%>&content=${content}">下一页</a></li>
				    <li><a>&nbsp;&nbsp;当前为第<%=num%>页</a></li>
				    <li><a>&nbsp;&nbsp;共<%=allNum%>页</a></li>
				  </ul>
				</nav>
            </div>
            
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

