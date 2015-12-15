<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>产品系列管理</title>
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
		<li class="active"><a href="${ctx}/p2p/cp2pSeries/">产品系列列表</a></li>
		<shiro:hasPermission name="p2p:cp2pSeries:edit"><li><a href="${ctx}/p2p/cp2pSeries/form">产品系列添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="cp2pSeries" action="${ctx}/p2p/cp2pSeries/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>类型名称：</label>
				<form:input path="name" htmlEscape="false" maxlength="100" class="input-medium"/>
			</li>
			<li><label>列表地址：</label>
				<form:input path="listuri" htmlEscape="false" maxlength="200" class="input-medium"/>
			</li>
			<li><label>分页参数：</label>
				<form:input path="pageattr" htmlEscape="false" maxlength="50" class="input-medium"/>
			</li>
			<li><label>最大获取页数：</label>
				<form:input path="pagemax" htmlEscape="false" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>类型名称</th>
				<th>列表地址</th>
				<th>分页参数</th>
				<th>最大获取页数</th>
				<th>update_date</th>
				<th>remarks</th>
				<shiro:hasPermission name="p2p:cp2pSeries:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="cp2pSeries">
			<tr>
				<td><a href="${ctx}/p2p/cp2pSeries/form?id=${cp2pSeries.id}">
					${cp2pSeries.name}
				</a></td>
				<td>
					${cp2pSeries.listuri}
				</td>
				<td>
					${cp2pSeries.pageattr}
				</td>
				<td>
					${cp2pSeries.pagemax}
				</td>
				<td>
					<fmt:formatDate value="${cp2pSeries.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${cp2pSeries.remarks}
				</td>
				<shiro:hasPermission name="p2p:cp2pSeries:edit"><td>
    				<a href="${ctx}/p2p/cp2pSeries/form?id=${cp2pSeries.id}">修改</a>
					<a href="${ctx}/p2p/cp2pSeries/delete?id=${cp2pSeries.id}" onclick="return confirmx('确认要删除该产品系列吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>