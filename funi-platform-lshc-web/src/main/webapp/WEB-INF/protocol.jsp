<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE HTML>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<title>大同市新建商品房预售资金监管协议</title>
	<meta name="keywords" content="大同市新建商品房预售资金监管协议">
	<meta name="description" content=大同市新建商品房预售资金监管协议">
	<script type="text/javascript" src="../app/platform/us/jquery/jquery-1.11.3.min.js"></script>
	<style>
		/*
		* reset.css
		*/
		body {
			/*font: normal 12px/1.5 "宋体";*/
			font-size: 16px;
			-webkit-print-color-adjust: exact;

		}

		html {
			color: black;
			overflow-y: scroll;
		}

		body, div, dl, dt, dd, ul, ol, li, h1, h2, h3, h4, h5, h6, pre, code, form, fieldset, legend, input, button, textarea, p, blockquote, th, td {
			margin: 0;
			padding: 0;
		}

		abbr, img, object, a img, :link img, :visited img, a object, :link object, :visited object {
			border: 0;
		}

		address, caption, cite, code, dfn, em, th, var, i {
			font-style: normal;
			/*font-weight: normal;*/
		}

		ul {
			list-style: none;
		}

		caption, th {
			text-align: left;
		}

		/*h1, h2, h3, h4, h5, h6 {*/
		/*font-size: 100%;*/
		/*}*/

		/*h1 {*/
		/*font-size: 18px;*/
		/*}*/

		/*h2 {*/
		/*font-size: 16px;*/
		/*}*/

		/*h3 {*/
		/*font-size: 14px;*/
		/*}*/

		q:before, q:after {
			content: "";
		}

		abbr, acronym {
			border: none;
			font-variant: normal;
		}

		sup {
			vertical-align: text-top;
		}

		sub {
			vertical-align: text-bottom;
		}

		form {
			display: inline;
		}

		input, button, textarea, select {
			font-family: inherit;
			font-size: inherit;
			font-weight: inherit;
			*font-size: 100%;
			background: transparent;
		}

		textarea {
			resize: none;
			border: 1px solid #dddddd;
			padding: 5px 10px;
			line-height: 20px;
		}

		input[type=text]:focus, input[type=password]:focus, textarea:focus {
			outline: 0;
		}

		input[type='radio'] {
			margin-left: 0;
			cursor: pointer;

		}

		input[type='checkbox'] {
			margin-left: 0;
			margin-top: 0;
			padding-left: 0;
			padding-top: 0;
			cursor: pointer;
		}

		input:disabled {
			background: none;
			cursor: default;
		}

		button {
			cursor: pointer;
			overflow: visible;
			padding: 0;
			width: auto;
		}

		input.gray {
			color: darkgrey;
		}

		input, button {
			vertical-align: middle;
		}

		label {
			vertical-align: top;
		}

		table {
			width: 100%;
			border-collapse: collapse;
			border-spacing: 0;
			font-size: inherit;
			font: 100%;
		}

		th {
			display: table-cell;
		}

		tr {
			display: table-row;
			vertical-align: inherit;
		}

		thead {
			display: table-header-group;
			vertical-align: middle;
		}

		pre, code, kbd, samp, tt {
			font-family: "Courier New", Courier, monospace;
		}

		small {
			font-size: 100%;
		}

		a {
			text-decoration: none;
			cursor: pointer;
		}

		a:hover {
			text-decoration: none;
		}

		abbr, acronym {
			border-bottom: 1px dotted;
			cursor: help;
		}

		ins {
			text-decoration: none;
		}

		del {
			text-decoration: line-through;
		}

		hr {
			color: #d1d7dc;
			background-color: #d1d7dc;
			border: none;
			height: 1px;
		}

		embed, object {
			display: block;
			outline: 0;
		}

		img {
			display: block;
			border: 0;
		}

		.clearfix {
			*zoom: 1;
		}

		.clearfix:after {
			content: "\0020";
			display: block;
			height: 0;
			clear: both;
			visibility: hidden;
		}

		.undis {
			display: none;
		}

		/*强制分页*/
		.print-page {
			page-break-after: always;
		}

		/*设置打印机*/
		@media print {
			.noprint {
				display: none
			}
		}

		@media print {
			html, body {
				background: none;
			}
		}

		@media print {
			@page {
				size: 195mm 275mm;  /* or size: 794px 1123px;   */
			}

		}

		/*合同样式*/
		.constract {
			margin: 0 auto;
			width: 850px;
			line-height: 35px;
			font-size: 24px;
		}

		.constract .ht-famly {
			background: none;
			border: none;
			color: #000;
			font-size: 32px;
			font-weight: 700;
			visibility: visible;
		}

		.constract .ht-famly .title {
			display: left;
			display: inline;
		}

		.constract .ht-famly .famly {
			float: right;
			font-size: 24px;
		}

		.constract .httitle {
			padding-top: 10px;
			text-align: center;
		}

		/*.constract .httitle h2 {*/
		/*background: none;*/
		/*border: none;*/
		/*color: #000;*/
		/*font-size: 36px;*/
		/*font-weight: 700;*/
		/*font-family: " 微软雅黑 " !important;*/
		/*}*/
		.constract h1 {
			font-size: 36px;
			font-weight: 600;
			font-family: "microsoft yahei";
		}

		.constract .httitle h3, .constract .httitle em, .constract .httitle .pjjl .pj-right h4 .graystart, .pjjl .pj-right h4 .constract .httitle .graystart {
			color: #000;
			display: block;
			font-size: 28px;
			border: none;
			font-weight: bold;
		}

		.constract .httitle em, .constract .httitle .pjjl .pj-right h4 .graystart, .pjjl .pj-right h4 .constract .httitle .graystart {
			font-weight: normal;
			font-size: 20px;
		}

		.constract h5 {
			font-size: 24px;
			padding-top: 20px;
		}

		.constract h5 span, .constract h5 .sub_add .width33 span, .sub_add .width33 .constract h5 span, .constract h5 .sub_add .width50 span, .sub_add .width50 .constract h5 span {
			font-weight: normal;
			border: none;
			padding: 0;
			width: auto;
		}

		.constract .jg-gz {
			padding-top: 30px;
		}

		.constract .bottomcheck {
			margin: 0 auto;
			width: 450px;
		}

		.constract .bottomcheck-l {
			margin: 0 auto;
			width: 720px;
		}

		.constract .minilist i {
			padding-left: 35px;
		}

		.constract h6 {
			text-align: center;
			font-size: 26px;
		}

		.constract p {
			text-indent: 25px;
			font-size: 24px;
			margin: 10px 0;
		}

		.constract p u {
			text-decoration: none;
		}

		.constract p em, .constract p .pjjl .pj-right h4 .graystart, .pjjl .pj-right h4 .constract p .graystart {
			margin-right: 10px;
		}

		.constract p i {
			border-bottom: 1px solid black;
		}

		.constract span, .constract .sub_add .width33 span, .sub_add .width33 .constract span, .constract .sub_add .width50 span, .sub_add .width50 .constract span {
			display: inline-block !important;
			height: 30px;
			width: 120px;
			padding: 0 2px;
			text-indent: 0;
			text-align: center;
			border-bottom: 1px solid #000;
			word-break: break-all;
			word-wrap: break-word;
			overflow: hidden;
		}

		.constract span input, .constract .sub_add .width33 span input, .sub_add .width33 .constract span input, .constract .sub_add .width50 span input, .sub_add .width50 .constract span input {
			border: none;
			border-radius: 0;
			height: 31px;
			line-height: 26px;
			overflow: hidden;
			width: 120px;
			background: #fff;
			color: #000;
		}

		.constract span input:disabled {
			color: #000;
		}

		.constract span input[disabled] {
			color: #000;
		}

		.constract input[type=text]:focus {
			outline: none;
			border: none;
			box-shadow: none;
			background: #fff;
			color: #000;
		}

		.constract .input-ht-m {
			width: 220px;
		}

		.constract .input-ht-m input {
			width: 220px;
		}

		.constract .input-ht-250 {
			width: 250px;
		}

		.constract .input-ht-250 input {
			width: 250px;
		}

		.constract .input-ht-200 {
			width: 420px;
		}

		.constract .input-ht-200 input {
			width: 420px;
		}

		.constract .input-ht-L500 {
			width: 530px;
		}

		.constract .input-ht-L500 input {
			width: 530px;
		}

		.constract .input-ht-s {
			width: 110px;
		}

		.constract .input-ht-s input {
			width: 110px;
		}

		.constract .input-ht-small {
			width: 140px;
		}

		.constract .input-ht-small input {
			width: 140px;
		}

		.constract .input-ht-ss {
			width: 50px;
		}

		.constract .input-ht-ss input {
			width: 50px;
		}

		.constract .input-ht-l {
			width: 600px;
		}

		.constract .input-ht-l input {
			width: 600px;
		}

		.constract .input-ht-fj, .constract .input-ht-fj:focus {
			border: none;
		}

		.constract .input-ht-noborder, .constract .input-ht-noborder:focus, .constract .input-ht-noborder-l, .constract .input-ht-noborder-l:focus, .constract .input-ht-noborder-s, .constract .input-ht-noborder-s:focus, .constract .input-ht-noborder-60, .constract .input-ht-noborder-60:focus, .constract .input-ht-noborder-80, .constract .input-ht-noborder-80:focus, .constract .input-ht-noborder-m, .constract .input-ht-noborder-m:focus {
			border: none;
			width: 230px;
			background: #fff;
			color: #000;
		}

		.constract .input-ht-noborder-l, .constract .input-ht-noborder-l:focus {
			width: 600px;
		}

		.constract .input-ht-noborder-s, .constract .input-ht-noborder-s:focus {
			width: 150px;
		}

		.constract .input-ht-noborder-60, .constract .input-ht-noborder-60:focus {
			width: 100px;
		}

		.constract .input-ht-noborder-80, .constract .input-ht-noborder-80:focus {
			width: 170px;
		}

		.constract .input-ht-noborder-m, .constract .input-ht-noborder-m:focus {
			width: 230px;
		}

		.constract textarea, .constract textarea:focus {
			border: none;
			width: 92%;
			height: 200px;
			box-shadow: none;
		}

		.constract .noedit {
			display: inline;
			padding: 0 20px 0 2px;
			text-indent: 0;
			text-align: center;
			border-bottom: 1px solid #000;
			word-break: break-all;
			word-wrap: break-word;
			overflow: inherit;
		}

		.constract .fjinfo {
			margin-top: 280px;
		}

		.constract .fjinfo .width400 {
			margin: 0px auto;
			width: 720px;
			font-size: 18px;
		}

		.constract .fjinfo span {
			height: 28px;
		}

		.constract .fjinfo input {
			border: none;
			border-radius: 0;
			height: 29px;
			line-height: 24px;
			overflow: hidden;
		}

		.constract .fjinfo h5 {
			text-align: center;
		}

		.constract .fjinfo em, .constract .fjinfo .pjjl .pj-right h4 .graystart, .pjjl .pj-right h4 .constract .fjinfo .graystart {
			font-weight: bold;
		}

		.constract .card-txt li, .constract .text-left li {
			float: left;
			display: inline;
			/*min-width: 300px;*/
			padding-bottom: 20px;
		}

		.constract .card-txt-div {
			margin: 0 auto;
			width: 800px;
			text-align: center
		}

		.constract .card-txt li.li-500, .constract .text-left li.li-500 {
			width: 800px;
			margin: 0 auto;
		}

		.constract .text-left span {
			text-align: left;
		}

		.constract .ht-tab {
			padding: 2px;
			width: 100%;
			border: 1px solid black;
			font-size: 18px;
		}

		.constract .ht-tab span {
			display: inline-block;
			height: 24px;
			width: 100px;
			padding: 0;
			text-indent: 0;
			text-align: center;
			border-bottom: 1px solid #000;
			overflow: hidden;
		}

		.constract .ht-tab span input {
			height: 22px;
			line-height: 23px;
			width: 100px;
		}

		.constract .ht-tab .input-ht-ss {
			width: 40px;
		}

		.constract .ht-tab .input-ht-ss input {
			width: 40px;
		}

		.constract .ht-tab .input-ht-80 {
			width: 80px;
		}

		.constract .ht-tab .input-ht-80 input {
			width: 80px;
		}

		.constract .ht-tab th, .constract .ht-tab td {
			border: 1px solid black;
			background: none;
			line-height: 25px;
			height: 25px;
		}

		.constract .ht-tab.zl-table th, .constract .ht-tab.zl-table td {
			border: 1px solid black;
			background: none;
			line-height: 34px;
			height: 34px;
		}

		.constract .ht-tab.zl-table th p, .constract .ht-tab.zl-table td p {
			font-size: 18px;
			text-indent: 0;
			margin: 0;
			text-align: left !important;
		}

		.constract .ht-tab.zl-table td {
			text-align: center;
		}

		.constract .ht-tab th {
			padding-left: 5px;
			text-align: center;
		}

		.constract .ht-tab td {
			padding: 0 0 5px 5px;
			text-align: left;
			word-break: break-all;
			word-wrap: break-word;
			overflow: hidden;
		}

		.constract .ht-tab td label {
			padding-right: 15px;
		}

		.constract .ht-tab .td-center {
			text-align: center;
		}

		.constract .ht-tab sup {
			vertical-align: top;
		}

		.constract .ht-tab .pad-label label {
			padding-right: 0;
		}

		.constract .red-txt {
			color: #f00;
		}

		.constract .house-tab th, .constract .house-tab td {
			font-size: 24px;
		}

		.constract .house-tab th {
			white-space: nowrap;
		}

		/**/
		.input-rent, .input-rent:focus {
			line-height: 24px;
			height: 24px;
			border-bottom: 1px solid #000 !important;
			border-right: none;
			border-left: none;
			border-top: none;
			vertical-align: text-top;
			color: #000;
		}

		.input-rent.input-rent:disabled, .input-rent:focus.input-rent:disabled {
			background: none;
		}

		.input-rent.input-d-y, .input-rent:focus.input-d-y {
			width: 70px;
			text-align: center;
		}

		.input-rent.input-price, .input-rent:focus.input-price {
			width: 100px;
			text-align: center;
		}

		.input-rent.input-price-big, .input-rent:focus.input-price-big {
			width: 310px;
			text-align: center;
		}

		.input-rent-noborder {
			border: none;
			background: #fff;
			color: #000;
			vertical-align: text-top;
		}

		.input-rent-noborder.w-middle {
			width: 230px;
		}

		.constract p.indent-none {
			text-indent: 0px !important;
		}
	</style>
</head>
<body>
	<div class="opts noprint" style='padding-left:300px'>
			<br/>
			<button style='width:150px;height:40px' type="submit" id='submit' class="submit" >签约并打印</button>
			</div>
<form class="submit-ht" value="" action="" method="post">
	<div class="constract">
		<div class="new-content">
			<div class="ht-famly"><b class="title"></b><b class="famly">监管编号：<i
					class="red-txt"><input type="text"  class="input-rent-noborder " disabled="disabled" value="${supCode}" name='supCode' style="width: 180px;"></i></b>
			</div>
			<br/><br/>

			<div class="httitle">
				<h1>大同市新建商品房预售资金监管协议</h1>
				<br>

				<h3></h3>
			</div>
			<br/>
			<br/>

			<div class="card-txt-div">
				<ul class="card-txt clearfix">
				<input  name='accountBizId' value="${bizId}" type="hidden" >
					<input  name='bizStatus' value="${bizStatus}" type="hidden" >
					       <input  name='print' value="${print}" type="hidden" >
					<li>甲方：<input type="text" name='firstParty' value="大同市房屋交易权属登记管理中心" disabled="disabled" class="input-rent"
					                                   style="width: 620px">
					</li>
					<li>地址：<input type="text" name='firstPartyAddr' value="大同市御东区文盛街北侧兴梓园A区44号楼" disabled="disabled" class="input-rent"
												   style="width: 620px" >
					</li>

				</ul>
			</div>
			<div class="card-txt-div">
				<ul class="card-txt clearfix">
					<li>乙方：<input type="text"  disabled="disabled" name='secondParty' value="${secondParty}" class="input-rent"
												   style="width: 620px" >
					</li>
					<li>地址：<input type="text" disabled="disabled" name='secondPartyAddr' value="${secondPartyAddr}" class="input-rent"
												   style="width: 620px" >
					</li>

				</ul>
			</div>

			<div class="card-txt-div">
				<ul class="card-txt clearfix">
					<li>丙方：<input type="text" disabled="disabled" name='thirdParty' value="${thirdParty}" class="input-rent"
												   style="width: 620px" >
					</li>
					<li>地址：<input type="text" disabled="disabled" name='thirdPartyAddr' value="${thirdPartyAddr}" class="input-rent"style="width: 620px" >
					</li>

				</ul>
			</div>
			<p>
				为维护购房人的合法权益，保证新建商品房预售资金用于项目建设，根据《关于进一步加强房地产市场监管完善商品住房预售制度有关问题的通知》（建房【2010】53号）、《山西省商品房预售资金监管办法》（晋建房字【2011】318号）、《大同市人民政府办公厅关于加强商品房预售资金监管的通知》（同政办发【2017】97号）及配套文件和有关法律、法规规定，甲、乙、丙三方经协商现就坐落于
				<input type="text" name='projectAddress' value="${projectAddress}" class="input-rent"
																																														 style="width: 655px" disabled="disabled">，项目名称为<input type="text"  class="input-rent" value="${projectName}" name='projectName'
																																																											   style="width: 655px" disabled="disabled"></p>的新建商品房预售资金监管事项达成以下协议：
			<p>
			 一、该项目商品房预售资金的收存、拨付和使用均应遵守上述相关法规、文件及本协议。
			</p>
			<p>
	二、乙方应在遵守《大同市新建商品房预售资金监管金融服务协议》的基础上，严格履行账户管理、资金收存、资金划拨等监管义务，积极为丙方协调解决资金监管专用pos机的安装、维护工作，并向丙方与购房人提供优质、高效的金融服务。
			</p>
			<p>
				三、丙方在乙方开设新建商品房预售资金监管账户。
				监管账户户名：<input type="text" disabled="disabled" name='accountName' value="${accountName}" class="input-rent"style="width: 800px" >
				<p>监管账号:</p><input type="text" disabled="disabled" name='accountNum' value="${accountNum}" class="input-rent"style="width: 800px" ><p>该项目为:<input type="text" name='renovation' value="${renovation}" class="input-rent"style="width: 280px" >(精装修／非精装修)项目；</p>
			</p>
			<p>
			该监管账户的使用范围仅限本项目预售资金的收存、拨付和使用；该监管账户不可支取现金，不得办理除查询功能以外的网上银行业务，不得办理质押，不得作为保证金账户及办理其他业务。
			</p>
			<p>
				四、丙方应协助购房人将房价款全部存入在乙方开设的监管账户，不得另设其他账户收存本项目预售资金，不得直接收存房价款。
			</p>
			<p>
				购房人通过新建商品房预售资金监管专用pos机刷卡交款的，由于系统、pos机具故障等原因刷卡后无法入账的，乙方应即时联系甲方、丙方，协调解决入账问题。
			</p>
			<p>
				五、对产生的不明入账款项，乙方应与资金发起行核实资金来源后在资金监管系统中将该笔资金确认到所对应商品房合同编号或者监管资金子账户编号内；对于核实资金来源确有困难的，乙方应向丙方提供该笔进款的付款方账号、金额、付款时间等相关信息，由丙方确定该笔资金对应的合同编号或者监管资金子账户编号。不明入账款项经确认后，丙方方可申请使用该笔资金。
			</p>
			<p>
				监管账户实际余额与监管网络系统显示余额不符时，乙方应当积极配合甲方查找原因，纠正误差情况，调整差错期间，监管账户暂停拨付。
			</p>
			<p>
				六、每笔预售资金进入监管账户，留存监管资金后，丙方可以通过监管系统申请提取监管资金以外的资金。丙方应按照施工进度和资金使用节点申请提取监管资金，用于支付建筑安装、区内配套建设等费用。
			</p>
			<p>
				监管资金按照“建设层数达三分之一”节点、主体结构验收、竣工验收、竣工验收备案、完成不动产首次登记等环节设置资金使用节点。
			</p>
			<p>
				七、甲方受理申请后3个工作日内完成审核，经审核符合要求的，为丙方出具《新建商品房预售监管资金拨付通知书》，丙方凭该通知书及划款凭证，到乙方办理资金划拨手续。
			</p>

			<p>
				丙方申请解除资金监管，甲方自受理申请后2个工作日内完成审核，审核通过的，向丙方出具《新建商品房预售资金监管账户解除监管通知书》，丙方持此通知书到乙方办理解除资金监管手续。
			</p>
			<p>
				对不明入账冲正及未网签退款，丙方申请，甲方审核无误后，出具《新建商品房预售资金监管冲正通知书》或《新建商品房预售资金监管退款通知书》，丙方持《新建商品房预售资金监管冲正通知书》或《新建商品房预售资金监管退款通知书》到乙方办理资金冲正或划拨手续。
			</p>
			<p>
				《新建商品房预售监管资金拨付通知书》、《新建商品房预售资金监管账户解除监管通知书》、《新建商品房预售资金监管冲正通知书》或《新建商品房预售资金监管退款通知书》是乙方划拨资金的必要手续。
			</p>
			<p>
				丙方向甲方承诺所提供的资金监管相关资料真实准确。甲方仅承担丙方提供资料的书面审核责任，不对具体内容的真实性承担审核责任。由于丙方提供资料不真实造成拨付资金错误或延误的，丙方应承担相关法律和经济责任。
			</p>
			<p>
			八、乙方不得从监管账户中扣除管理费、划款手续费等银行收费，但可在监管账户孳息、账户管理费、划款手续费等方面为丙方提供相应的优惠措施。
			</p>
			<p>
			九、因履行本协议发生争议，三方协商解决。协商不成的，依法向人民法院提起诉讼。
			</p>
			<p>
			十、本协议自三方签字、盖章之日起生效，至账户注销或解除资金监管时终止。本协议一式三份，三方各执一份，
            具有同等法律效力。
            </p>
			<p>
			十一、本协议未尽事宜，经三方协商一致，可签订补充协议。补充协议同本协议具有同等法律效力。
			</p>
			<br/>
			<ul class="card-txt clearfix">
				<li>甲方（公章）：<input type="text" value="" class="input-rent-noborder w-middle" style="width: 100px;"></li>
				<li>乙方（公章）： <input type="text" value="" class="input-rent-noborder " style="width: 100px;"></li>
				<li>丙方 (公章) ： <input type="text" value="" class="input-rent-noborder " style="width: 100px;"></li>
			</ul>
			<br/>
			<ul class="card-txt clearfix">
				<li>法定代表人 ：<input type="text" value="" class="input-rent-noborder w-middle " style="width:110px " ></li>
				<li>法定代表人 ：<input type="text"  value="" class="input-rent-noborder" style="width: 130px;"></li>
				<li>法定代表人 ：<input type="text"  value="" class="input-rent-noborder" style="width: 110px;"></li>
			</ul>
			<ul class="card-txt clearfix">
				<li>签章：<input type="text" value="" class="input-rent-noborder w-middle " style="width:190px " ></li>
				<li>签章：<input type="text"  value="" class="input-rent-noborder" style="width: 215px;"></li>
				<li>签章：<input type="text"  value="" class="input-rent-noborder" style="width: 130px;"></li>
			</ul>
			<ul class="card-txt clearfix">
				<li>年<input type="text" value="" class="input-rent-noborder " style="width:40px " disabled>月<input type="text" value="" class="input-rent-noborder " style="width:40px " disabled>日<input type="text" value="" class="input-rent-noborder " style="width:110px " disabled></li>
				<li>年<input type="text" value="" class="input-rent-noborder " style="width:40px " disabled>月<input type="text" value="" class="input-rent-noborder " style="width:40px " disabled>日<input type="text" value="" class="input-rent-noborder " style="width:130px " disabled></li>
				<li>年<input type="text" value="" class="input-rent-noborder " style="width:40px " disabled>月<input type="text" value="" class="input-rent-noborder " style="width:40px " disabled>日<input type="text" value="" class="input-rent-noborder " style="width:80px " disabled></li>
			</ul>
			<br/> <br/>


		</div>
	</div>
</form>
<script>

	$(function () {
		//文档加载完执行
		var findView=function(status){
				console.log(status);
		if("办结"==status){
		$("input[name='renovation']").attr('disabled','disabled');
		$("#submit").text("打印");
		}
		}

		//提交
		$('#submit').on('click', function(){
		//监管编号
		var submitData={
		accountBizId:$("input[name='accountBizId']").val(),
		secondParty:$("input[name='secondParty']").val(),
		secondPartyAddr:$("input[name='secondPartyAddr']").val(),
		supCode:$("input[name='supCode']").val(),
		accountName:$("input[name='accountName']").val(),
		accountNum:$("input[name='accountNum']").val(),
		thirdParty:$("input[name='thirdParty']").val(),
		thirdPartyAddr:$("input[name='thirdPartyAddr']").val(),
		projectAddress:$("input[name='projectAddress']").val(),
		renovation:$("input[name='renovation']").val(),
		projectName:$("input[name='projectName']").val()
		}
		var saveData=[];
		for(var k in submitData){
		var data={fieldName:k ,fieldValue:submitData[k]}
		saveData.push(data);
		}
        //打印
            //打印之前
            function doPrint() {
                var url="/psfs/foundsAgreementController/findProtocolPage?bizId="+submitData.accountBizId+"&print="+1;
                //打印
                if($("input[name='print']").val()==1){
                    window.print();
                }else{
                    window.open(url);
                }

            }
		//验证是否已经保存
				$.ajax({
                  type: 'POST',
                  url: "/psfs/foundsAgreementController/findProtocolByBiz",
                  async : false,
                  dataType:"json",
                  data: {bizId:submitData.accountBizId},
                  success:function(data){

                  if(data.result.list.length>=1){
                  //修改
                  	$.ajax({
                                              type: 'POST',
                                              url: "/psfs/foundsAgreementController/updateProtocol",
                                              async : false,
                                              dataType:"json",
                                              data: {list:JSON.stringify(saveData),bizId:submitData.accountBizId},
                                              success:function(data){
                                             doPrint()
                                              },
                                              error:function(data){
                                              alert("签约失败");
                                    			}
                                            });
                                    return;
                  }else{
                  	//保存
                  		$.ajax({
                            type: 'POST',
                            url: "/psfs/foundsAgreementController/createProtocol",
                            async : false,
                            dataType:"json",
                            data: {list:JSON.stringify(saveData)},
                            success:function(data){
                              doPrint()
                            },
                            error:function(data){
                            alert("签约失败");
                  			}
                          });
                  }


                  }
                });


		});
		$(window).load(function () {
	     	findView($("input[name='bizStatus']").val());
	     	   //打印
                        if($("input[name='print']").val()==1){
                            window.print();
                        }
			//身份证件类型选择打钩
			$('.sfz-choose').each(function (i) {
				var $this = $(this), str = $this.find("input[type=radio]:checked");
				str.after('√');

			});
			//大写高度样式
			$(".big-price").each(function (i) {
				var $this = $(this), str = $this.parent('span');
				str.css("height", "32px");

			});
		})
		//radio选择加载选择项
		function txtchange(str1, str2, str3) {
			$(str1).change(function () {
				var $this = $(this);
				var txt = $(this).find('input:checked').val();
				if ($this.attr('checked', true)) {
					$this.parents(str2).find(str3).text(txt);
				}
			});

		}

		txtchange($('.lab1'), ('.checkboxValue'), ('.fs-check'));
		//所有checkbox radio的样式
		$('input[type=checkbox],input[type=radio]').css({width: '16px', height: '16px'});
		/*****金额大小写转换***
		 *
		 * @param num
		 * @returns {*}
		 */
		//金额大小写转换
		function numToChinese(num) {
			var str1 = '零壹贰叁肆伍陆柒捌玖';  //0-9所对应的汉字
			var str2 = '万仟佰拾亿仟佰拾万仟佰拾元角分'; //数字位所对应的汉字
			var str3;    //从原num值中取出的值
			var str4;    //数字的字符串形式
			var str5 = '';  //人民币大写金额形式
			var i;    //循环变量
			var j;    //num的值乘以100的字符串长度
			var ch1;    //数字的汉语读法
			var ch2;    //数字位的汉字读法
			var nzero = 0;  //用来计算连续的零值是几个

			num = Math.abs(num).toFixed(2);  //将num取绝对值并四舍五入取2位小数
			str4 = (num * 100).toFixed(0).toString();  //将num乘100并转换成字符串形式
			j = str4.length;      //找出最高位
			if (j > 10) {
				return '土豪，您输入的太多了';
			}
			str2 = str2.substr(15 - j);    //取出对应位数的str2的值。如：200.55,j为5所以str2=佰拾元角分

			//循环取出每一位需要转换的值
			for (i = 0; i < j; i++) {
				str3 = str4.substr(i, 1);   //取出需转换的某一位的值
				if (i != (j - 3) && i != (j - 7) && i != (j - 11) && i != (j - 15)) {    //当所取位数不为元、万、亿、万亿上的数字时
					if (str3 == '0') {
						ch1 = '';
						ch2 = '';
						nzero = nzero + 1;
					}
					else {
						if (str3 != '0' && nzero != 0) {
							ch1 = '零' + str1.substr(str3 * 1, 1);
							ch2 = str2.substr(i, 1);
							nzero = 0;
						}
						else {
							ch1 = str1.substr(str3 * 1, 1);
							ch2 = str2.substr(i, 1);
							nzero = 0;
						}
					}
				}
				else { //该位是万亿，亿，万，元位等关键位
					if (str3 != '0' && nzero != 0) {
						ch1 = "零" + str1.substr(str3 * 1, 1);
						ch2 = str2.substr(i, 1);
						nzero = 0;
					}
					else {
						if (str3 != '0' && nzero == 0) {
							ch1 = str1.substr(str3 * 1, 1);
							ch2 = str2.substr(i, 1);
							nzero = 0;
						}
						else {
							if (str3 == '0' && nzero >= 3) {
								ch1 = '';
								ch2 = '';
								nzero = nzero + 1;
							}
							else {
								if (j >= 11) {
									ch1 = '';
									nzero = nzero + 1;
								}
								else {
									ch1 = '';
									ch2 = str2.substr(i, 1);
									nzero = nzero + 1;
								}
							}
						}
					}
				}
				if (i == (j - 11) || i == (j - 3)) {  //如果该位是亿位或元位，则必须写上
					ch2 = str2.substr(i, 1);
				}
				str5 = str5 + ch1 + ch2;

				if (i == j - 1 && str3 == '0') {   //最后一位（分）为0时，加上“整”
					str5 = str5 + '整';
				}
			}
			if (num == 0) {
				str5 = '零元整';
			}
			return str5;
		}

		//进来默认的金额大小写转换
		for (var i = 0; i < $('.total-price').length; i++) {
			if (!$('.total-price').eq(i).val() == '') {
				var bigPrice = $('.total-price').eq(i).parent().next().children(), bigValue = numToChinese(parseFloat($('.total-price').eq(i).val()));
				bigPrice.val(bigValue);
			}
		}
		//输入时的金额大小写转换
		$('.total-price').keyup(function () {
			var bigPrice = $(this).parent().next().children();
			var bigValue = parseFloat($(this).val()) ? numToChinese(parseFloat($(this).val())) : '';
			if ($(this).val().length == 0) {
				bigPrice.val('');
			}
			else {
				bigPrice.val(bigValue);
			}
		});
		$('.total-price').blur(function () {
			if ($(this).val().length > 0) {
				var bigPrice = parseFloat($(this).val()).toFixed(2);
				$(this).val(bigPrice);
			}
		});
	})
</script>

</body>
</html>
