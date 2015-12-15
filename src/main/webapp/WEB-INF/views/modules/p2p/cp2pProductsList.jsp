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
			<li><label>系列id：</label>
				<form:input path="cp2pSeries.id" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
			<li><label>原id：</label>
				<form:input path="sid" htmlEscape="false" maxlength="50" class="input-medium"/>
			</li>
			<li><label>原编号：</label>
				<form:input path="snum" htmlEscape="false" maxlength="50" class="input-medium"/>
			</li>
			<li><label>名称：</label>
				<form:input path="name" htmlEscape="false" maxlength="100" class="input-medium"/>
			</li>
			<li><label>详细地址：</label>
				<form:input path="detailuri" htmlEscape="false" maxlength="500" class="input-medium"/>
			</li>
			<li><label>利率：</label>
				<form:input path="rate" htmlEscape="false" class="input-medium"/>
			</li>
			<li><label>平台利率：</label>
				<form:input path="platformrate" htmlEscape="false" class="input-medium"/>
			</li>
			<li><label>总利率：</label>
				<form:input path="totalrate" htmlEscape="false" class="input-medium"/>
			</li>
			<li><label>万元预计收益：</label>
				<form:input path="wanrate" htmlEscape="false" class="input-medium"/>
			</li>
			<li><label>总金额：</label>
				<form:input path="totalmoney" htmlEscape="false" class="input-medium"/>
			</li>
			<li><label>周期：</label>
				<form:input path="term" htmlEscape="false" maxlength="50" class="input-medium"/>
			</li>
			<li><label>周期天：</label>
				<form:input path="termday" htmlEscape="false" class="input-medium"/>
			</li>
			<li><label>周期月：</label>
				<form:input path="termmonth" htmlEscape="false" class="input-medium"/>
			</li>
			<li><label>周期年：</label>
				<form:input path="termyear" htmlEscape="false" class="input-medium"/>
			</li>
			<li><label>进度：</label>
				<form:input path="schedule" htmlEscape="false" class="input-medium"/>
			</li>
			<li><label>计息时间：</label>
				<form:input path="interesttime" htmlEscape="false" maxlength="50" class="input-medium"/>
			</li>
			<li><label>还款方式：</label>
				<form:input path="repaymode" htmlEscape="false" maxlength="50" class="input-medium"/>
			</li>
			<li><label>还款日期：</label>
				<form:input path="repaydate" htmlEscape="false" maxlength="50" class="input-medium"/>
			</li>
			<li><label>是否转让：</label>
				<form:input path="istransfer" htmlEscape="false" maxlength="1" class="input-medium"/>
			</li>
			<li><label>允许转让：</label>
				<form:input path="allowtransfer" htmlEscape="false" maxlength="1" class="input-medium"/>
			</li>
			<li><label>转让注意事项：</label>
				<form:input path="transfernotice" htmlEscape="false" maxlength="200" class="input-medium"/>
			</li>
			<li><label>是否抵押：</label>
				<form:input path="ismortgage" htmlEscape="false" maxlength="1" class="input-medium"/>
			</li>
			<li><label>是否担保：</label>
				<form:input path="isguarantee" htmlEscape="false" maxlength="1" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>系列id</th>
				<th>原id</th>
				<th>原编号</th>
				<th>名称</th>
				<th>详细地址</th>
				<th>利率</th>
				<th>平台利率</th>
				<th>总利率</th>
				<th>万元预计收益</th>
				<th>总金额</th>
				<th>周期</th>
				<th>周期天</th>
				<th>周期月</th>
				<th>周期年</th>
				<th>进度</th>
				<th>计息时间</th>
				<th>还款方式</th>
				<th>还款日期</th>
				<th>update_date</th>
				<th>remarks</th>
				<shiro:hasPermission name="p2p:cp2pProducts:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="cp2pProducts">
			<tr>
				<td><a href="${ctx}/p2p/cp2pProducts/form?id=${cp2pProducts.id}">
					${cp2pProducts.cp2pSeries.id}
				</a></td>
				<td>
					${cp2pProducts.sid}
				</td>
				<td>
					${cp2pProducts.snum}
				</td>
				<td>
					${cp2pProducts.name}
				</td>
				<td>
					${cp2pProducts.detailuri}
				</td>
				<td>
					${cp2pProducts.rate}
				</td>
				<td>
					${cp2pProducts.platformrate}
				</td>
				<td>
					${cp2pProducts.totalrate}
				</td>
				<td>
					${cp2pProducts.wanrate}
				</td>
				<td>
					${cp2pProducts.totalmoney}
				</td>
				<td>
					${cp2pProducts.term}
				</td>
				<td>
					${cp2pProducts.termday}
				</td>
				<td>
					${cp2pProducts.termmonth}
				</td>
				<td>
					${cp2pProducts.termyear}
				</td>
				<td>
					${cp2pProducts.schedule}
				</td>
				<td>
					${cp2pProducts.interesttime}
				</td>
				<td>
					${cp2pProducts.repaymode}
				</td>
				<td>
					${cp2pProducts.repaydate}
				</td>
				<td>
					<fmt:formatDate value="${cp2pProducts.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${cp2pProducts.remarks}
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