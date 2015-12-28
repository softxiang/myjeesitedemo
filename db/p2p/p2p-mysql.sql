SET SESSION FOREIGN_KEY_CHECKS=0;

/* Drop Tables */

DROP TABLE IF EXISTS c_p2p_products;
DROP TABLE IF EXISTS c_p2p_series;
DROP TABLE IF EXISTS c_p2p_platform;




/* Create Tables */

-- 业务数据子表
CREATE TABLE c_p2p_platform
(
	-- 编号
	id varchar(64) NOT NULL COMMENT '编号',
	-- 名称
	name varchar(200) NOT NULL COMMENT '名称',
	-- 平台地址
	platformuri varchar(200) COMMENT '平台地址',
	-- 公司名称
	companyname varchar(200) COMMENT '公司名称',
	-- 法人
	corporation varchar(200) COMMENT '法人',
	-- 详细地址
	address varchar(1000) COMMENT '详细地址',
	-- 省
	province varchar(100) COMMENT '省',
	-- 市
	city varchar(100) COMMENT '市',
	-- 创建者
	create_by varchar(64) NOT NULL COMMENT '创建者',
	-- 创建时间
	create_date datetime NOT NULL COMMENT '创建时间',
	-- 更新者
	update_by varchar(64) NOT NULL COMMENT '更新者',
	-- 更新时间
	update_date datetime NOT NULL COMMENT '更新时间',
	-- 删除标记（0：正常；1：删除）
	del_flag char(1) DEFAULT '0' NOT NULL COMMENT '删除标记（0：正常；1：删除）',
	PRIMARY KEY (id)
) COMMENT = '业务数据子表';


-- 业务数据表
CREATE TABLE c_p2p_products
(
	-- 编号
	id varchar(64) NOT NULL COMMENT '编号',
	-- 系列id
	seriesid varchar(64) NOT NULL COMMENT '系列id',
	-- 原平台id
	sid varchar(50) COMMENT '原平台id',
	-- 原平台编号
	snum varchar(50) COMMENT '原平台编号',
	-- 标名
	name varchar(200) COMMENT '标名',
	-- 详细地址
	detailuri varchar(500) COMMENT '详细地址',
	-- 利率
	rate decimal(7,4) DEFAULT 0.0000 COMMENT '利率',
	-- 平台利率
	platformrate decimal(7,4) DEFAULT 0.0000 COMMENT '平台利率',
	-- 总利率=利率+平台利率
	totalrate decimal(7,4) COMMENT '总利率=利率+平台利率',
	-- 项目预计万元收益
	wanrate int COMMENT '项目预计万元收益',
	-- 总金额
	totalmoney decimal DEFAULT 0 COMMENT '总金额',
	-- 投资期限字符串
	term varchar(50) COMMENT '投资期限字符串',
	termday int DEFAULT 0,
	-- 月数
	termmonth int DEFAULT 0 COMMENT '月数',
	-- 年数
	termyear int DEFAULT 0 COMMENT '年数',
	-- 进度
	schedule float DEFAULT 0 COMMENT '进度',
	-- 开始时间
	starttime varchar(500) COMMENT '开始时间',
	-- 计息时间
	interesttime varchar(50) COMMENT '计息时间',
	-- 还款方式
	repaymode varchar(50) COMMENT '还款方式',
	-- 还款日
	repaydate varchar(50) COMMENT '还款日',
	-- 是否是转让产品
	istransfer char(1) COMMENT '是否是转让产品',
	-- 是否允许转让
	allowtransfer char(1) COMMENT '是否允许转让',
	-- 转让说明，如多久后可转让，转让手续费
	transfernotice varchar(200) COMMENT '转让说明，如多久后可转让，转让手续费',
	-- 是否抵押
	ismortgage char(1) COMMENT '是否抵押',
	-- 是否有担保
	isguarantee char(1) COMMENT '是否有担保',
	-- 创建者
	create_by varchar(64) NOT NULL COMMENT '创建者',
	-- 创建时间
	create_date datetime NOT NULL COMMENT '创建时间',
	-- 更新者
	update_by varchar(64) NOT NULL COMMENT '更新者',
	-- 更新时间
	update_date datetime NOT NULL COMMENT '更新时间',
	-- 删除标记（0：正常；1：删除）
	del_flag char(1) DEFAULT '0' NOT NULL COMMENT '删除标记（0：正常；1：删除）',
	PRIMARY KEY (id)
) COMMENT = '业务数据表';


-- 业务数据子表
CREATE TABLE c_p2p_series
(
	-- 编号
	id varchar(64) NOT NULL COMMENT '编号',
	-- 平台id
	platformid varchar(64) NOT NULL COMMENT '平台id',
	-- 名称
	name varchar(200) COMMENT '名称',
	-- 列表地址
	listuri varchar(200) COMMENT '列表地址',
	-- 分页参数
	pageattr varchar(50) COMMENT '分页参数',
	-- 最大查询页数
	pagemax int COMMENT '最大查询页数',
	-- 列表返回值类型
	listdatatype varchar(64) COMMENT '列表返回值类型',
	-- 如果为docment则为css选择器
	-- 如果为json则为json data root
	listrootexp varchar(200) COMMENT '如果为docment则为css选择器
如果为json则为json data root',
	-- 获取id的表达式
	detailidexp varchar(500) COMMENT '获取id的表达式',
	-- cssexp:css;regexp:xxx#内容#xxx;
	listscheduleexp varchar(500) COMMENT 'cssexp:css;regexp:xxx#内容#xxx;',
	-- 详细页地址前缀
	detailprefix varchar(500) COMMENT '详细页地址前缀',
	-- 格式:
	-- cssexp:cssexp;regexp:xxx#内容#xxx;
	snumexp varchar(500) COMMENT '格式:
cssexp:cssexp;regexp:xxx#内容#xxx;',
	-- 名称表达式
	-- cssexp:cssexp;regexp:xxx#内容#xxx;
	nameexp varchar(500) COMMENT '名称表达式
cssexp:cssexp;regexp:xxx#内容#xxx;',
	-- cssexp:cssexp;regexp:xxx#内容#xxx;
	rateexp varchar(500) COMMENT 'cssexp:cssexp;regexp:xxx#内容#xxx;',
	-- cssexp:cssexp;regexp:xxx#内容#xxx;
	platformrateexp varchar(500) COMMENT 'cssexp:cssexp;regexp:xxx#内容#xxx;',
	-- cssexp:cssexp;regexp:xxx#内容#xxx;
	totalmoneyexp varchar(500) COMMENT 'cssexp:cssexp;regexp:xxx#内容#xxx;',
	-- cssexp:cssexp;regexp:xxx#内容#xxx;
	termexp varchar(500) COMMENT 'cssexp:cssexp;regexp:xxx#内容#xxx;',
	-- cssexp:cssexp;regexp:xxx#内容#xxx;
	scheduleexp varchar(500) COMMENT 'cssexp:cssexp;regexp:xxx#内容#xxx;',
	-- cssexp:cssexp;regexp:xxx#内容#xxx;
	starttimeexp varchar(500) COMMENT 'cssexp:cssexp;regexp:xxx#内容#xxx;',
	-- cssexp:cssexp;regexp:xxx#内容#xxx;
	repaymodeexp varchar(500) COMMENT 'cssexp:cssexp;regexp:xxx#内容#xxx;',
	-- cssexp:cssexp;regexp:xxx#内容#xxx;
	repaydateexp varchar(500) COMMENT 'cssexp:cssexp;regexp:xxx#内容#xxx;',
	-- cssexp:cssexp;regexp:xxx#内容#xxx;
	istransferexp varchar(500) COMMENT 'cssexp:cssexp;regexp:xxx#内容#xxx;',
	-- cssexp:cssexp;regexp:xxx#内容#xxx;
	allowtransfer varchar(500) COMMENT 'cssexp:cssexp;regexp:xxx#内容#xxx;',
	-- cssexp:cssexp;regexp:xxx#内容#xxx;
	transfernoticeexp varchar(500) COMMENT 'cssexp:cssexp;regexp:xxx#内容#xxx;',
	-- cssexp:cssexp;regexp:xxx#内容#xxx;
	ismortgageexp varchar(500) COMMENT 'cssexp:cssexp;regexp:xxx#内容#xxx;',
	-- cssexp:cssexp;regexp:xxx#内容#xxx;
	isguaranteeexp varchar(500) COMMENT 'cssexp:cssexp;regexp:xxx#内容#xxx;',
	-- 创建者
	create_by varchar(64) NOT NULL COMMENT '创建者',
	-- 创建时间
	create_date datetime NOT NULL COMMENT '创建时间',
	-- 更新者
	update_by varchar(64) NOT NULL COMMENT '更新者',
	-- 更新时间
	update_date datetime NOT NULL COMMENT '更新时间',
	-- 删除标记（0：正常；1：删除）
	del_flag char(1) DEFAULT '0' NOT NULL COMMENT '删除标记（0：正常；1：删除）',
	PRIMARY KEY (id)
) COMMENT = '业务数据子表';



/* Create Foreign Keys */

ALTER TABLE c_p2p_series
	ADD CONSTRAINT pk_p2p_series_plat FOREIGN KEY (platformid)
	REFERENCES c_p2p_platform (id)
	ON UPDATE NO ACTION
	ON DELETE NO ACTION
;


ALTER TABLE c_p2p_products
	ADD FOREIGN KEY (seriesid)
	REFERENCES c_p2p_series (id)
	ON UPDATE NO ACTION
	ON DELETE NO ACTION
;



