<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>平台信息管理</title>
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
		<li class="active"><a href="${ctx}/p2p/cp2pPlatform/">平台信息列表</a></li>
		<shiro:hasPermission name="p2p:cp2pPlatform:edit"><li><a href="${ctx}/p2p/cp2pPlatform/form">平台信息添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="cp2pPlatform" action="${ctx}/p2p/cp2pPlatform/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>名称：</label>
				<form:input path="name" htmlEscape="false" maxlength="100" class="input-medium"/>
			</li>
			<li><label>官网：</label>
				<form:input path="uri" htmlEscape="false" maxlength="200" class="input-medium"/>
			</li>
			<li><label>公司名称：</label>
				<form:input path="companyname" htmlEscape="false" maxlength="200" class="input-medium"/>
			</li>
			<li><label>法人：</label>
				<form:input path="corporation" htmlEscape="false" maxlength="200" class="input-medium"/>
			</li>
			<li><label>详细地址：</label>
				<form:input path="address" htmlEscape="false" maxlength="1000" class="input-medium"/>
			</li>
			<li><label>省：</label>
				<form:input path="province" htmlEscape="false" maxlength="100" class="input-medium"/>
			</li>
			<li><label>市：</label>
				<form:input path="city" htmlEscape="false" maxlength="100" class="input-medium"/>
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
				<th>官网</th>
				<th>公司名称</th>
				<th>法人</th>
				<th>详细地址</th>
				<th>省</th>
				<th>市</th>
				<th>update_date</th>
				<th>remarks</th>
				<shiro:hasPermission name="p2p:cp2pPlatform:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="cp2pPlatform">
			<tr>
				<td><a href="${ctx}/p2p/cp2pPlatform/form?id=${cp2pPlatform.id}">
					${cp2pPlatform.name}
				</a></td>
				<td>
					${cp2pPlatform.uri}
				</td>
				<td>
					${cp2pPlatform.companyname}
				</td>
				<td>
					${cp2pPlatform.corporation}
				</td>
				<td>
					${cp2pPlatform.address}
				</td>
				<td>
					${cp2pPlatform.province}
				</td>
				<td>
					${cp2pPlatform.city}
				</td>
				<td>
					<fmt:formatDate value="${cp2pPlatform.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${cp2pPlatform.remarks}
				</td>
				<shiro:hasPermission name="p2p:cp2pPlatform:edit"><td>
    				<a href="${ctx}/p2p/cp2pPlatform/form?id=${cp2pPlatform.id}">修改</a>
					<a href="${ctx}/p2p/cp2pPlatform/delete?id=${cp2pPlatform.id}" onclick="return confirmx('确认要删除该平台信息吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>