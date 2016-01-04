String.prototype.format = function() {
	var args = arguments;
	return this.replace(/{(\d+)}/g, function() {
		return args[arguments[1]];
	});
};
String.prototype.sub = function(len, ext) {
	ext = ext || '';
	var r = /[^\x00-\xff]/g;
	if (this.replace(r, "mm").length <= len)
		return this;
	var m = Math.floor(len / 2);
	for (var i = m; i < this.length; i++) {
		if (this.substr(0, i).replace(r, "mm").length >= len) {
			return this.substr(0, i) + ext;
		}
	}
	return this;
};
String.prototype.encode = function() {
	if (!this)
		return '';
	var re = this;
	var q1 = [ /\x26/g, /\x3C/g, /\x3E/g, /\x20/g ];
	var q2 = [ "&amp;", "&lt;", "&gt;", "&nbsp;" ];
	for (var i = 0; i < q1.length; i++)
		re = re.replace(q1[i], q2[i]);
	return re;
};
String.prototype.lengthEx = function() {
	return this.replace(/[^\x00-\xff]/g, "--").length;
};
if (!Array.indexOf) {
	Array.prototype.indexOf = function(obj, start) {
		for (var i = (start || 0); i < this.length; i++)
			if (this[i] == obj)
				return i;
		return -1;
	}
};
$.fn.disable = function() {
	if ($(this).length == 0)
		return false;
	this.get(0).disabled = true;
};
$.fn.enable = function() {
	if ($(this).length == 0)
		return false;
	this.get(0).disabled = false;
};
$.extend({
	reg : function(nsc) {
		var b = nsc.indexOf(".prototype") != -1;
		if (b)
			nsc = nsc.substr(0, nsc.length - 10);
		var d = nsc.split(".");
		var o = window[d[0]] = d.length == 0 && b ? window[d[0]] || function() {
		} : window[d[0]] || {};
		for (var i = 1; i < d.length; ++i)
			o = o[d[i]] = d.length == i + 1 && b ? o[d[i]] || function() {
			} : o[d[i]] || {};
		if (b)
			o.prototype = {};
		return b ? o.prototype : o;
	}
});

$.extend($.reg("myfunc"), {
	lazy_jump : function(secs, url) {
		if (--secs > 0) {
			setTimeout("myfunc.lazy_jump(" + secs + ",'" + url + "')", 1000);
		} else {
			location.href = url;
		}
	}
});
$.extend($.reg("myfunc.user"), {
	showLayer : function(id, open, close) {
		$("html").addClass("overflow");
		$(id + ",.gray_layer").show();
		$(".gray_layer").height($(document).height());
		if (open)
			open();

		$(id + " > .close").unbind().click(function() {
			if (close)
				close();
			$(id + ",.gray_layer").hide();
			$("html").removeClass("overflow");
			return false;
		});
	},
	showLayerEx : function(id, open, close, top) {
		top = top || 200;
		this.showLayer(id, open, close);
		var obj = $(id).last(), winh = $(window).height(), t = $(document).scrollTop();
		obj.css("top", t + top + 'px');
	},
	hideLayer : function(id, close) {
		$(id + " > .close").click();
		if (close)
			close();
	},
	process : function(msg, mode, id) {
		mode = typeof mode == 'boolean' ? mode : true;
		id = id || 'process-message-win';
		msg = msg || '正在加载中...';
		if (mode)
			$("body").append('<div class="confirm_gray_layer"></div>');
		$("body").append('<div class="win process-message-win" id="' + id + '">' + msg + '</div>');
		if (mode)
			$(".confirm_gray_layer").show().height($(document).height());
		myfunc.user.showLayerEx('#' + id, null, null, 350);
	},
	processClose : function(id) {
		id = id || "process-message-win";
		myfunc.user.hideLayer("#" + id);
		$(".confirm_gray_layer,#" + id).remove();
	},
	successMsg : function(msg, time) {
		$(".container .main").append('<div class="success-message-win">' + msg + '</div>');
		var ml = -($(".success-message-win").width() / 2);
		$(".success-message-win").css({
			"margin-left" : ml
		});
		$('.success-message-win').show();
		setTimeout(function() {
			$(".success-message-win").remove();
		}, time || 2000);
	},
	errorMsg : function(msg, time) {
		$(".container .main").append('<div class="error-message-win">' + msg + '</div>');
		var ml = -($(".error-message-win").width() / 2);
		$(".error-message-win").css({
			"margin-left" : ml
		});
		$('.error-message-win').show();
		setTimeout(function() {
			$(".error-message-win").remove();
		}, time || 2000);
	},
	get : function(url, data, func, error) {
		if ($.isFunction(data)) {
			error = func;
			func = data;
			$data = null;
		}
		;
		$.ajax({
			type : 'get',
			url : url,
			data : data,
			success : function(json) {
				if (func)
					func(json);
			},
			error : function() {
				if (error)
					error();
			}
		});
	},
	post : function(url, data, func, error) {
		if ($.isFunction(data)) {
			error = func;
			func = data;
			$data = null;
		}
		;
		$.ajax({
			type : 'post',
			url : url,
			data : data,
			success : function(json) {
				if (func)
					func(json);
			},
			error : function() {
				if (error)
					error();
			}
		});
	},
	checkblur1 : function(obj, pwdNoCheckMax) {
		pwdNoCheckMax = pwdNoCheckMax || 0;
		var id = $(obj).attr("id");
		var v = $(obj).val();
		var objpoint = $(obj).parent().siblings(".point");
		var objp = $(obj).parent();
		var result = true;
		/* 用户名 */
		if (id == "username") {
			if (v.length == 0) {
				result = false;
				objpoint.addClass("point-ico2").text("用户名不能为空").show();
				objp.addClass("input-text-error").removeClass("input-text-cur");
			} else {
				objpoint.removeClass("point-ico2").addClass("point-ico1").text("");
				objp.removeClass("input-text-error");
			}
		}
		/* 密码 */
		if (id == "password") {
			if (v.length < 3) {
				result = false;
				objpoint.addClass("point-ico2").text("密码不能少于6个字符").show();
				objp.addClass("input-text-error").removeClass("input-text-cur");
			} else if (!pwdNoCheckMax && v.length > 15) {
				result = false;
				objpoint.addClass("point-ico2").text("密码不能大于15个字符").show();
				objp.addClass("input-text-error").removeClass("input-text-cur");
			} else {
				objpoint.removeClass("point-ico2").addClass("point-ico1").text("");
				objp.removeClass("input-text-error");
			}
		}
		if (id == "password2") {
			if (v.length < 6) {
				result = false;
				objpoint.addClass("point-ico2").text("密码不能少于6个字符").show();
				objp.addClass("input-text-error").removeClass("input-text-cur");
			} else if (v != $("#password").val()) {
				result = false;
				objpoint.addClass("point-ico2").text("两次密码不一致").show();
				objp.addClass("input-text-error").removeClass("input-text-cur");
			} else {
				objpoint.removeClass("point-ico2").addClass("point-ico1").text("");
				objp.removeClass("input-text-error");
			}
		}
		/* 邮箱 */
		if (id == "email") {
			var reg = /^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+(.[a-zA-Z0-9_-])+/;
			if (!v) {
				result = false;
				objpoint.addClass("point-ico2").text("邮箱不能为空").show();
				objp.addClass("input-text-error").removeClass("input-text-cur");
			} else if (!reg.test(v)) {
				result = false;
				objpoint.addClass("point-ico2").text("请输入正确格式的电子邮箱").show();
				objp.addClass("input-text-error").removeClass("input-text-cur");
			} else {
				objpoint.removeClass("point-ico2").addClass("point-ico1").text("");
				objp.removeClass("input-text-error");
			}
		}
		/* 验证码 */
		if (id == "yanzm") {
			if (!v) {
				result = false;
				objpoint.addClass("point-ico2").text("请输入验证码").show();
				objp.addClass("input-text-error").removeClass("input-text-cur");
			} else {
				objpoint.removeClass("point-ico2").addClass("point-ico1").text("");
				objp.removeClass("input-text-error");
			}
		}
		/* 用户类型 */
		if (id == "usertype") {
			if (!$("#usertype").val() || $("#usertype").nextAll(".input-radio").hasClass("radio-cur") == false) {
				$("#usertype").siblings(".point").addClass("point-ico2").text("请选择用户类型").show();
				result = false;
			} else {
				$("#usertype").siblings(".point").addClass("point-ico1").removeClass("point-ico2").text("").show();
			}
		}
		return result;
	},
	checkfocus : function() {
		$("#username, #password, #password2, #email, #yanzm").focus(function() {
			var promptcode = new Array();
			promptcode['username'] = '用户名不能为空';
			promptcode['password'] = '密码不少于6位数';
			promptcode['password2'] = '再次输入密码';
			promptcode['email'] = '请输入E-mail地址';
			promptcode['yanzm'] = '请输入验证码';
			$(this).parent('.input-text').removeClass('input-text-error').siblings('.point').removeClass('point-ico1 point-ico2').html(promptcode[$(this).attr('id')]);
			return false;
		});
	},
	register : function() {
		$("#password, #password2, #usertype").blur(function() {
			myfunc.user.checkblur1(this);
			return false;
		});
		myfunc.user.checkfocus();
		$(document).keydown(function(e) {
			e = window.event || e;
			var code = e.keyCode || e.which;
			if (code == 13)
				$("#reg_submit").click();
		});
		$("#reg_submit").click(function() {
			var auths = new Array('', 'username', 'password', 'password2', 'email', 'usertype', 'yanzm');
			for (var i = 0, len = auths.length; i < len; i++) {
				if ($(this).hasClass(auths[i]) == true)
					continue;
				if (!myfunc.user.checkblur1('#' + auths[i]))
					return false;
			}
			;
			if ($('#username').val() == '' || $('#email').val() == '' || $('#yanzm').val() == '')
				return false;
			myfunc.user.process();
			myfunc.user.post('http://' + window.location.host + "/user/auth_register", {
				username : $('#username').val(),
				password : $('#password').val(),
				password2 : $('#password2').val(),
				email : $('#email').val(),
				usertype : $('#usertype').val(),
				secanswer : $('#yanzm').val(),
				free : $("#reg_free").val()
			}, function(data) {
				if (data.status == 1) {
					_paq.push([ "setCustomVariable", 1, "id", data.member.uid, "visit" ]);
					_paq.push([ "setCustomVariable", 2, "name", data.member.username, "visit" ]);
					_paq.push([ "setCustomVariable", 3, "refer", "pc_register", "visit" ]);
					_paq.push([ "setCookieDomain", "*.myfunc.com" ]);
					_paq.push([ "enableLinkTracking" ]);
					_paq.push([ "trackEvent", "bbs.myfunc.com", "bbsregister", "page" ]);
					_paq.push([ "setTrackerUrl", "\/\/piwik.myfunc.com/piwik.php" ]);
					_paq.push([ "setSiteId", 1 ]);
					$("#reg_submit").disable();
					myfunc.user.successMsg(data.msg);
					myfunc.lazy_jump(2, data.refer);
				} else if (data.status == -1 || data.status == -2 || data.status == -3 || data.status == -4 || data.status == -6) {
					$('#' + auths[Math.abs(data.status)]).parent().addClass("input-text-error").removeClass("input-text-cur").siblings(".point").addClass("point-ico2").text(data.msg).show();
				} else if (data.status == -5) {
					$("#usertype").siblings(".point").addClass("point-ico2").text(data.msg).show();
				} else if (data.status == -9) {
					myfunc.user.errorMsg(data.msg);
					myfunc.lazy_jump(2, data.refer);
				} else
					myfunc.user.errorMsg(data.msg);
				myfunc.user.processClose();
			}, function() {
				myfunc.user.processClose();
			});
			return false;
		});
		$(".main-left .yanzm").click(function() {
			var $this = $(this);
			myfunc.user.get('http://' + window.location.host + "/user/register", {
				get_captcha : 1
			}, function(data) {
				if (data)
					$this.html(data);
			});
			return false;
		});
		$(".register-ajax").blur(function() {
			var $this = $(this);
			// if ($this.val() == '') return false; ||
			// $this.parent().hasClass('input-text-error')
			myfunc.user.post('http://' + window.location.host + '/user/register_ajax_auth', {
				auth_type : $this.attr('id'),
				auth_val : $this.val()
			}, function(data) {
				if (data.status == -1) {
					$("#reg_submit").addClass($this.attr('id'));
					$this.parent().addClass("input-text-error").removeClass("input-text-cur").siblings(".point").addClass("point-ico2").text(data.msg).show();
				} else {
					$("#reg_submit").removeClass($this.attr('id'));
					$this.parent().removeClass("input-text-error").siblings(".point").removeClass("point-ico2").addClass("point-ico1").text("");
				}
			});
			return false;
		})
	},

	qqconnect_register : function() {
		myfunc.user.checkfocus();
		$(document).keydown(function(e) {
			e = window.event || e;
			var code = e.keyCode || e.which;
			if (code == 13)
				$("#reg_submit").click();
		});
		$(".qq_improve_sub").click(function() {
			var auths = new Array('', 'username', '', '', 'email', 'usertype');
			for (var i = 0, len = auths.length; i < len; i++) {
				if (!auths[i] || $(this).hasClass(auths[i]) == true)
					continue;
				if (!myfunc.user.checkblur1('#' + auths[i]))
					return false;
			}
			;
			myfunc.user.process();
			var is_check = $("#use_qzone_avatar_qqshow").is(':checked') ? 1 : 0;
			myfunc.user.post('http://' + window.location.host + "/user/memberconnect", {
				username : $('#username').val(),
				email : $('#email').val(),
				usertype : $('#usertype').val(),
				use_qzone_avatar_qqshow : is_check
			}, function(data) {
				if (data.status == 1) {
					_paq.push([ "setCustomVariable", 1, "id", data.member.uid, "visit" ]);
					_paq.push([ "setCustomVariable", 2, "name", data.member.username, "visit" ]);
					_paq.push([ "setCustomVariable", 3, "refer", "qq_improve", "visit" ]);
					_paq.push([ "setCookieDomain", "*.myfunc.com" ]);
					_paq.push([ "enableLinkTracking" ]);
					_paq.push([ "trackEvent", "bbs.myfunc.com", "bbsregister", "page" ]);
					_paq.push([ "setTrackerUrl", "\/\/piwik.myfunc.com/piwik.php" ]);
					_paq.push([ "setSiteId", 1 ]);
					$(".qq_improve_sub").disable();
					myfunc.user.successMsg(data.msg);
					myfunc.lazy_jump(2, data.refer);
				} else if (data.status == -1 || data.status == -4) {
					$('#' + auths[Math.abs(data.status)]).parent().addClass("input-text-error").removeClass("input-text-cur").siblings(".point").addClass("point-ico2").text(data.msg).show();
				} else if (data.status == -5) {
					$("#usertype").siblings(".point").addClass("point-ico2").text(data.msg).show();
				} else if (data.status == -9) {
					myfunc.user.errorMsg(data.msg);
					myfunc.lazy_jump(2, data.refer);
				} else
					myfunc.user.errorMsg(data.msg);
				myfunc.user.processClose();
			}, function() {
				myfunc.user.processClose();
			});

			return false;
		});
		$(".qq_bind_sub").click(function() {
			if (!myfunc.user.checkblur1('#username') || !myfunc.user.checkblur1('#password'))
				return false;
			myfunc.user.process();
			var is_check = $("#use_qzone_avatar_qqshow").is(':checked') ? 1 : 0;
			myfunc.user.post('http://' + window.location.host + "/user/qqconnect_login_bind", {
				username : $('#username').val(),
				password : hex_md5($('#password').val()),
				use_qzone_avatar_qqshow : is_check
			}, function(data) {
				if (data.error_code == 0) {
					_paq.push([ "setCustomVariable", 1, "id", data.data.member.uid, "visit" ]);
					_paq.push([ "setCustomVariable", 2, "name", data.data.member.username, "visit" ]);
					_paq.push([ "setCustomVariable", 3, "refer", "qq_bind", "visit" ]);
					_paq.push([ "setCookieDomain", "*.myfunc.com" ]);
					_paq.push([ "enableLinkTracking" ]);
					_paq.push([ "trackEvent", "bbs.myfunc.com", "bbsregister", "page" ]);
					_paq.push([ "setTrackerUrl", "\/\/piwik.myfunc.com/piwik.php" ]);
					_paq.push([ "setSiteId", 1 ]);
					$(".qq_bind_sub").disable();
					myfunc.user.successMsg(data.msg);
					myfunc.lazy_jump(2, data.qq_refer);// location.href =
														// data.data.refer;
				} else {
					myfunc.user.errorMsg(data.msg);
				}
				myfunc.user.processClose();
			}, function() {
				myfunc.user.processClose();
			});

			return false;
		});
		$(".register-ajax").blur(function() {
			var $this = $(this);
			// if ($this.val() == '') return false; ||
			// $this.parent().hasClass('input-text-error')
			myfunc.user.post('http://' + window.location.host + '/user/register_ajax_auth', {
				auth_type : $this.attr('id'),
				auth_val : $this.val()
			}, function(data) {
				if (data.status == -1) {
					$("#reg_submit").addClass($this.attr('id'));
					$this.parent().addClass("input-text-error").removeClass("input-text-cur").siblings(".point").addClass("point-ico2").text(data.msg).show();
				} else {
					$("#reg_submit").removeClass($this.attr('id'));
					$this.parent().removeClass("input-text-error").siblings(".point").removeClass("point-ico2").addClass("point-ico1").text("");
				}
			});
			return false;
		})
	},

	register_wap : function() {
		$(".login-verification-label").click(function() {
			var $this = $(this);
			myfunc.user.get('http://' + window.location.host + "/user/register", {
				get_captcha : 1
			}, function(data) {
				if (data)
					$this.html(data);
			});
			return false;
		});
		$("#reg_submit").click(function() {
			if ($("#name").val() == '') {
				alert('用户名不能为空');
				return false;
			}
			if ($("#password").val() == '') {
				alert('密码不能为空');
				return false;
			}
			/*
			 * var re = /^1\d{10}$/; if (!re.test($("#phone").val())){
			 * alert('请输入正确的手机格式'); return false; }
			 */
			var re = /^(\w-*\.*)+@(\w-?)+(\.\w{2,})+$/
			if (!re.test($("#email").val())) {
				alert('请输入正确的email格式');
				return false;
			}
			if ($("#smscode").val() == '') {
				alert('请输入验证码');
				return false;
			}
			myfunc.user.process();
			myfunc.user.post('http://' + window.location.host + "/user/wap_register", {
				username : $('#name').val(),
				password : $('#password').val(),
				email : $('#email').val(),
				usertype : $(".login-check .login-check-div.on .group").val(),
				secanswer : $('#smscode').val()
			}, function(data) {
				if (data.status == 1) {
					_paq.push([ "setCustomVariable", 1, "id", data.member.uid, "visit" ]);
					_paq.push([ "setCustomVariable", 2, "name", data.member.username, "visit" ]);
					_paq.push([ "setCustomVariable", 3, "refer", "wap_register", "visit" ]);
					_paq.push([ "setCookieDomain", "*.myfunc.com" ]);
					_paq.push([ "enableLinkTracking" ]);
					_paq.push([ "trackEvent", "bbs.myfunc.com", "bbsregister", "page" ]);
					_paq.push([ "setTrackerUrl", "\/\/piwik.myfunc.com/piwik.php" ]);
					_paq.push([ "setSiteId", 1 ]);
					$("#reg_submit").disable();
					alert(data.msg);
					myfunc.lazy_jump(2, data.refer);
				} else {
					alert(data.msg);
				}
				myfunc.user.processClose();
			}, function() {
				myfunc.user.processClose();
			});
			return false;
		});
		$(".login-check-div").click(function() {
			$(this).addClass("on").siblings("div").removeClass("on");
			return false;
		});
	},

	check_submit : function(csrf_name, csrf_value) {
		var auto_login = 0;
		if ($(".login_check span i").attr('class') == 'cur') {
			auto_login = 1;
		}
		;
		myfunc.user.post(window.location.href, {
			username : $('#username').val(),
			password : $('#password').val(),
			login_submit : 1
		}, function(data) {
			if (data.error_code == 0) {
				// 成功
				_paq.push([ "setCustomVariable", 1, "id", data.data.member.uid, "visit" ]);
				_paq.push([ "setCustomVariable", 2, "name", data.data.member.username, "visit" ]);
				_paq.push([ "setCustomVariable", 3, "refer", "pc_login", "visit" ]);
				_paq.push([ "setCookieDomain", "*.myfunc.com" ]);
				_paq.push([ "enableLinkTracking" ]);
				_paq.push([ "trackEvent", "bbs.myfunc.com", "bbslogin", "page" ]);
				_paq.push([ "setTrackerUrl", "\/\/piwik.myfunc.com/piwik.php" ]);
				_paq.push([ "setSiteId", 1 ]);
				myfunc.user.successMsg(data.msg);
				myfunc.lazy_jump(2, data.data.refer);// location.href =
													// data.data.refer;
			} else {
				myfunc.user.errorMsg(data.msg);
			}
			$('#login_submit').removeAttr('disabled');
			$('#login_submit').val('登录');
		}, function() {
		});
	}
});

$(function() {
	$(".input-text .input").each(function() {
		var thisVal = $(this).val();
		// 判断文本框的值是否为空，有值的情况就隐藏提示语，没有值就显示
		if (thisVal != "") {
			$(this).siblings("span").hide();
		} else {
			$(this).siblings("span").show();
		}
		// 输入框聚焦效果
		$(this).focus(function() {
			$(this).siblings("span").hide();
			$(".input-text").removeClass("input-text-cur");
			$(this).parent().addClass("input-text-cur");
			$(this).parent().siblings(".point").show();
		}).blur(function() {
			var val = $(this).val();
			if (val != "") {
				$(this).siblings("span").hide();
			} else {
				$(this).siblings("span").show();
			}
		});
	});
	/* 模拟单选 */
	$(".input-radio").click(function() {
		$(this).addClass("radio-cur").siblings(".input-radio").removeClass("radio-cur");
		$(this).addClass("radio-cur").siblings("input").val($(this).attr("val"));
	});
	$(".submit").click(function() {
	})
	/* 模拟复选 */
	$(".login_check span i").click(function() {
		$(this).toggleClass("cur");
	});
	/* 头部用户名选择 */
	$(".top_login1 label").click(function() {
		$(this).children(".userlogin").toggle();
	});
	$(".top_login1 .userlogin a").click(function() {
		$(this).parent().siblings("a").text($(this).text());
	});
})