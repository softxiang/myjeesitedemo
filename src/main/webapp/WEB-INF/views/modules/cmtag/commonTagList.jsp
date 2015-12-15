<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>标签管理</title>
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
		<li class="active"><a href="${ctx}/cmtag/commonTag/">标签列表</a></li>
		<shiro:hasPermission name="cmtag:commonTag:edit"><li><a href="${ctx}/cmtag/commonTag/form">标签添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="commonTag" action="${ctx}/cmtag/commonTag/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>标签：</label>
				<form:input path="name" htmlEscape="false" maxlength="50" class="input-medium"/>
			</li>
			<li><label>标签分类：</label>
			</li>
			<li><label>排序：</label>
				<form:input path="sort" htmlEscape="false" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>标签</th>
				<th>标签分类</th>
				<th>排序</th>
				<th>update_date</th>
				<th>remarks</th>
				<shiro:hasPermission name="cmtag:commonTag:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="commonTag">
			<tr>
				<td><a href="${ctx}/cmtag/commonTag/form?id=${commonTag.id}">
					${commonTag.name}
				</a></td>
				<td>
					${commonTag.commonCategory.name}
				</td>
				<td>
					${commonTag.sort}
				</td>
				<td>
					<fmt:formatDate value="${commonTag.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${commonTag.remarks}
				</td>
				<shiro:hasPermission name="cmtag:commonTag:edit"><td>
    				<a href="${ctx}/cmtag/commonTag/form?id=${commonTag.id}">修改</a>
					<a href="${ctx}/cmtag/commonTag/delete?id=${commonTag.id}" onclick="return confirmx('确认要删除该标签吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>