<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>p2p产品系列管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			//$("#name").focus();
			$("#inputForm").validate({
				submitHandler: function(form){
					loading('正在提交，请稍等...');
					form.submit();
				},
				errorContainer: "#messageBox",
				errorPlacement: function(error, element) {
					$("#messageBox").text("输入有误，请先更正。");
					if (element.is(":checkbox")||element.is(":radio")||element.parent().is(".input-append")){
						error.appendTo(element.parent().parent());
					} else {
						error.insertAfter(element);
					}
				}
			});
		});
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/p2p/cp2pSeries/">p2p产品系列列表</a></li>
		<li class="active"><a href="${ctx}/p2p/cp2pSeries/form?id=${cp2pSeries.id}">p2p产品系列<shiro:hasPermission name="p2p:cp2pSeries:edit">${not empty cp2pSeries.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="p2p:cp2pSeries:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="cp2pSeries" action="${ctx}/p2p/cp2pSeries/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<div class="control-group">
			<label class="control-label">平台：</label>
			<div class="controls">
				<form:select path="cp2pPlatform.id" class="input-xlarge required">
					<form:option value="" label="请选择"/>
					<form:options items="${cp2pPlatformList}" itemLabel="name" itemValue="id" htmlEscape="false"/>
				</form:select>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">名称：</label>
			<div class="controls">
				<form:input path="name" htmlEscape="false" maxlength="200" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">列表地址：</label>
			<div class="controls">
				<form:input path="listuri" htmlEscape="false" maxlength="200" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">分页参数：</label>
			<div class="controls">
				<form:input path="pageattr" htmlEscape="false" maxlength="50" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">最大页数：</label>
			<div class="controls">
				<form:input path="pagemax" htmlEscape="false" class="input-xlarge  digits"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">返回结果类型：</label>
			<div class="controls">
				<form:select path="listdatatype" class="input-xlarge ">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('p2p_serieslist_type')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">列表获取表达式：</label>
			<div class="controls">
				<form:input path="listrootexp" htmlEscape="false" maxlength="200" class="input-xlarge "/>
				<span class="help-inline"><font color="">表达式:{cssExp}#regex#</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">明细id表达式：</label>
			<div class="controls">
				<form:input path="detailidexp" htmlEscape="false" maxlength="500" class="input-xlarge "/>
				<span class="help-inline"><font color="">表达式:${cp2pSeries.detailidexp}</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">详细页前缀：</label>
			<div class="controls">
				<form:input path="detailprefix" htmlEscape="false" maxlength="500" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">项目编号表达式：</label>
			<div class="controls">
				<form:input path="snumexp" htmlEscape="false" maxlength="500" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">名称表达式：</label>
			<div class="controls">
				<form:input path="nameexp" htmlEscape="false" maxlength="500" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">利率表达式：</label>
			<div class="controls">
				<form:input path="rateexp" htmlEscape="false" maxlength="500" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">平台利率表达式：</label>
			<div class="controls">
				<form:input path="platformrateexp" htmlEscape="false" maxlength="500" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">总金额表达式：</label>
			<div class="controls">
				<form:input path="totolmoneyexp" htmlEscape="false" maxlength="500" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">期限表达式：</label>
			<div class="controls">
				<form:input path="termexp" htmlEscape="false" maxlength="500" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">进度表达式：</label>
			<div class="controls">
				<form:input path="scheduleexp" htmlEscape="false" maxlength="500" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">开始日期表达式：</label>
			<div class="controls">
				<form:input path="starttimeexp" htmlEscape="false" maxlength="500" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">还款方法表达式：</label>
			<div class="controls">
				<form:input path="repaymodeexp" htmlEscape="false" maxlength="500" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">还款日期表达式：</label>
			<div class="controls">
				<form:input path="repaydateexp" htmlEscape="false" maxlength="500" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">是否转让标表达式：</label>
			<div class="controls">
				<form:input path="istransferexp" htmlEscape="false" maxlength="500" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">允许转让表达式：</label>
			<div class="controls">
				<form:input path="allowtransfer" htmlEscape="false" maxlength="500" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">转让说明表达式：</label>
			<div class="controls">
				<form:input path="transfernoticeexp" htmlEscape="false" maxlength="500" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">是否抵押表达式：</label>
			<div class="controls">
				<form:input path="ismortgageexp" htmlEscape="false" maxlength="500" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">是否担保表达式：</label>
			<div class="controls">
				<form:input path="isguaranteeexp" htmlEscape="false" maxlength="500" class="input-xlarge "/>
			</div>
		</div>
		<div class="form-actions">
			<shiro:hasPermission name="p2p:cp2pSeries:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>