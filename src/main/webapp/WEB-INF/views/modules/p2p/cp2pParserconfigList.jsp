<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>采集器配置管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			
		});
		function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			$("#searchForm").submit();
        	return false;
        }
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/p2p/cp2pParserconfig/">采集器配置列表</a></li>
		<shiro:hasPermission name="p2p:cp2pParserconfig:edit"><li><a href="${ctx}/p2p/cp2pParserconfig/form">采集器配置添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="cp2pParserconfig" action="${ctx}/p2p/cp2pParserconfig/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>产品系列：</label>
				<form:select path="cp2pSeries.id" htmlEscape="false" maxlength="64" class="input-medium">
					<form:option value="" label=""/>
					<form:options items="${seriesList}" itemLabel="name" itemValue="id" htmlEscape="false"/>
				</form:select>
			</li>
			<li><label>类名：</label>
				<form:input path="classname" htmlEscape="false" maxlength="200" class="input-medium"/>
			</li>
			<li><label>时间Cron：</label>
				<form:input path="cronex" htmlEscape="false" maxlength="200" class="input-medium"/>
			</li>
			<li><label>状态：</label>
				<form:input path="status" htmlEscape="false" maxlength="50" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>产品系列</th>
				<th>类名</th>
				<th>时间Cron</th>
				<th>状态</th>
				<th>update_date</th>
				<th>remarks</th>
				<shiro:hasPermission name="p2p:cp2pParserconfig:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="cp2pParserconfig">
			<tr>
				<td>
					${cp2pParserconfig.cp2pSeries.name}
				</td>
				<td>
					${cp2pParserconfig.classname}
				</td>
				<td>
					${cp2pParserconfig.cronex}
				</td>
				<td>
					${cp2pParserconfig.status}
				</td>
				<td>
					<fmt:formatDate value="${cp2pParserconfig.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${cp2pParserconfig.remarks}
				</td>
				<shiro:hasPermission name="p2p:cp2pParserconfig:edit"><td>
					<a href="${ctx}/p2p/cp2pParserconfig/addQuartz?id=${cp2pParserconfig.id}" onclick="return confirmx('确认要将此配置加入任务吗？', this.href)">加入任务</a>
					<a href="${ctx}/p2p/cp2pParserconfig/form?id=${cp2pParserconfig.id}">查看任务状态</a>
    				<a href="${ctx}/p2p/cp2pParserconfig/form?id=${cp2pParserconfig.id}">修改</a>
					<a href="${ctx}/p2p/cp2pParserconfig/delete?id=${cp2pParserconfig.id}" onclick="return confirmx('确认要删除该采集器配置吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>