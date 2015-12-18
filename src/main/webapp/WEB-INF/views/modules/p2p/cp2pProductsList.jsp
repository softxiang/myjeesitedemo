<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>p2p产品管理</title>
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
		<li class="active"><a href="${ctx}/p2p/cp2pProducts/">p2p产品列表</a></li>
		<shiro:hasPermission name="p2p:cp2pProducts:edit"><li><a href="${ctx}/p2p/cp2pProducts/form">p2p产品添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="cp2pProducts" action="${ctx}/p2p/cp2pProducts/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>产品系列：</label>
				<form:select path="cp2pSeries.id" class="input-medium">
					<form:option value="" label="请选择"/>
					<form:options items="${cp2pSeriesList}" itemLabel="name" itemValue="id" htmlEscape="false"/>
				</form:select>
			</li>
			<li><label>名称：</label>
				<form:input path="name" htmlEscape="false" maxlength="200" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>名称</th>
				<th>修改时间</th>
				<shiro:hasPermission name="p2p:cp2pProducts:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="cp2pProducts">
			<tr>
				<td><a href="${ctx}/p2p/cp2pProducts/form?id=${cp2pProducts.id}">
					${cp2pProducts.name}
				</a></td>
				<td>
					<fmt:formatDate value="${cp2pProducts.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<shiro:hasPermission name="p2p:cp2pProducts:edit"><td>
    				<a href="${ctx}/p2p/cp2pProducts/form?id=${cp2pProducts.id}">修改</a>
					<a href="${ctx}/p2p/cp2pProducts/delete?id=${cp2pProducts.id}" onclick="return confirmx('确认要删除该p2p产品吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>