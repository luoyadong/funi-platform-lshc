/**
 * 房屋普查管理
 * @author luoyadong@funi365.com
 */

Ext.define('app.platform.lshc.view.regi.manage.RegiDetailView', {
    extend: 'Ext.Panel',
    xtype: 'lshc-view-regi-RegiDetailView',
    requires: [
        //房屋列表信息
        'app.platform.lshc.view.regi.manage.HouseListView',
		'app.platform.lshc.view.regi.manage.HouseDetailView',//房屋详情
        'app.platform.lshc.view.base.RequestUtils'
    ],
    config: {
        // //主容器
        // parentContainer: null,
		// //楼栋表ID
		// bizId:null
    },
    //需要注释，否者传参数会报错
    // constructor: function (config) {
    //     var me = this;
    //     config = config || {};
    //     Ext.applyIf(config, me.config);
    //     this.callParent(arguments);
    // },
    title: null,
    // height:'100%',
    getParams:function(){
        var me = this;
        var formElements = Ext.ComponentQuery.query("textfield",this);
        var obj = new Object();
        obj["buildId"] = me.config.bizId;
        if(null != me.config.auditStatus){
            obj["auditStatus"] = me.config.auditStatus;
        }
        for(var i=0;i<formElements.length;i++){
            obj[formElements[i].name] =formElements[i].value;
        }
        return obj;
    },
	resetParams:function(){
        var formElements = Ext.ComponentQuery.query("textfield",this);
        var obj = new Object();
        for(var i=0;i<formElements.length;i++){
            if(null != formElements[i]){
                this.queryById(formElements[i].itemId).setValue(null)
            }
        }
        return obj;
    },
	initDetail:function(mapCode,address){
	    var me = this;

		//初始化楼栋信息
        var mapCodeTb = me.queryById("mapCodeTbItemId");
		mapCodeTb.setHtml( '<div style="font-weight:bold;font-size:10">'+mapCode+'</div>');

        var addressTb = me.queryById("addressTbItemId");
		addressTb.setHtml(  '<div style="font-weight:bold;font-size:10;margin-left:10;float:right">'+address+'</div>');

        //初始化高度
        var hList = me.queryById("HouseListView-Tab-itemId");
        hList.down("gridpanel").setHeight(me.queryById("westItemId").getHeight()-20);

        var houseDetalPanel = me.queryById("lshc-view-regi-HouseDetailView-itemId");
        houseDetalPanel.config.contHeight = me.queryById("westItemId").getHeight() - 112;
        houseDetalPanel.initHeight();

		//初始化房屋列表,HouseListView-Tab-itemId  lshc-view-regi-HouseListView-itemId
        me.initHouseList();
	},
    initHouseList:function(){
        var me = this;
        if(me.config.srcType == null){
            Ext.MessageBox.alert("温馨提示","操作类型不明确！");
            return
        }

        gridObjStore = me.getHouseListStore();
        if(me.config.srcType == 1){//综合
            gridObjStore.proxy.url = app.platform.lshc.view.base.RequestUtils.url("/regiInfo/getRegiInfoVoList");
        }else  if(me.config.srcType == 2){//待办
            gridObjStore.proxy.url = app.platform.lshc.view.base.RequestUtils.url("/undone/getRegiInfoVoList");
        }else if(me.config.srcType == 3){//已办
            gridObjStore.proxy.url = app.platform.lshc.view.base.RequestUtils.url("/completed/getRegiInfoVoList");
        }else{//管理
            gridObjStore.proxy.url = app.platform.lshc.view.base.RequestUtils.url("/manage/getRegiInfoVoList");
        }

        gridObjStore.proxy.extraParams = me.getParams();
        gridObjStore.loadPage(1);

    },
    getHouseListStore:function(){
        var houseListPanel = this.queryById("HouseListView-Tab-itemId");//lshc-view-regi-HouseListView-itemId
        var gridObjStore = houseListPanel.down("gridpanel").store;
        return gridObjStore;
    },
	initStatus:function(status,doneStatus,doingStatus,todoStatus){
	    var me = this;
		//初始化顶部状态进度信息
        var statusTb = me.queryById("statusTbItemId");
		statusTb.setHtml(  '<div style="font-weight:bold;font-size:10;color:green;">'+status+'</div>')

        var doneTb = me.queryById("doneTbItemId");
		doneTb.setHtml( '<div style="font-weight:bold;font-size:11;color:green;">'+doneStatus+'</div>')

		var doingTb = me.queryById("doingTbItemId");
		doingTb.setHtml( '<div style="font-weight:bold;font-size:11;color:orange;">'+doingStatus+'</div>')

		var todoTb = me.queryById("todoTbItemId");
		todoTb.setHtml('<div style="font-weight:bold;font-size:11;margin-right:50;">'+todoStatus+'</div>')
	},
	readOnly:function(){
        var tabpanelView = this.queryById("lshc-regi-housedetail-parent-tabpanel-itemId");
        for(var i=0;i<tabpanelView.items.length;i++){
            if(tabpanelView.items.items[i].down("xgridpanel") && tabpanelView.items.items[i].down("xgridpanel").selModel){
                tabpanelView.items.items[i].down("xgridpanel").disableSelection=true;
                tabpanelView.items.items[i].down("xgridpanel").addListener("beforecellclick",function(){
                    return false;
                });
                tabpanelView.items.items[i].down("xgridpanel").addListener("beforedeselect",function(){
                    return false;
                })
            }

            if(tabpanelView.items.items[i].down("gridpanel") && tabpanelView.items.items[i].down("gridpanel").selModel){
                tabpanelView.items.items[i].down("gridpanel").disableSelection=true;
                tabpanelView.items.items[i].down("gridpanel").addListener("beforecellclick",function(){
                    return false;
                });
                tabpanelView.items.items[i].down("gridpanel").addListener("beforedeselect",function(){
                    return false;
                })
            }

            var view = Ext.ComponentQuery.query("textfield,numberfield,xcombobox,checkbox",tabpanelView.items.items[i]);
            for(var j=0;j<view.length;j++){
                //view[j].setDisabled(true);
                view[j].setReadOnly(true);
            }

            var houseListView = this.queryById("lshc-view-regi-HouseDetailView-itemId");
            var view = Ext.ComponentQuery.query("button",houseListView);
            for(var j=0;j<view.length;j++){
                if(view[j].xtype=="button"
                    && (view[j].text == "添加" || view[j].text == "删除")){
                    view[j].hide();
                }
            }

        }
    },
    initBtn:function(){
        var me = this;
        var houseListView = this.queryById("lshc-view-regi-HouseListView-itemId");
        var view = Ext.ComponentQuery.query("button",houseListView);
        for(var j=0;j<view.length;j++){
            if(view[j].xtype=="button"){

                var btnText = view[j].text;
                if(me.config.srcType == 1){//综合
                    if(btnText == "导出" || btnText =="退回"){
                    }else{
                        view[j].hide();
                    }
                }else  if(me.config.srcType == 2){//待办
                    if(btnText == "导出" || btnText == "审批"){
                    }else{
                        view[j].hide();
                    }
                }else if(me.config.srcType == 3){//已办,//1:新建 2 批量导入 3导出 4审批 5删除 6编辑 7 批量提交
                    if(btnText == "导出"){
                    }else{
                        view[j].hide();
                    }
                }else{//管理
                    if(btnText == "审批" || btnText =="退回"){
                        view[j].hide();
                    }
                }

            }
        }

        var houseDetailView = this.queryById("lshc-view-regi-HouseDetailView-itemId");
        var view = Ext.ComponentQuery.query("button",houseDetailView);
        for(var j=0;j<view.length;j++){
            if(view[j].xtype=="button"){
                var btnText = view[j].text;
                if(me.config.srcType != 0 && btnText != '打印'){
                    view[j].hide();
                }
                if(btnText == '删除文件'){
                    view[j].hide();
                }
            }
        }

        //if(me.config.srcType != 0){
            var uploadViews = Ext.ComponentQuery.query("fileuploadfield",houseDetailView);
            for(var i=0;i<uploadViews.length;i++){
                uploadViews[i].hidden=true;
            }
        //}

        this.readOnly();
    },
    initHouseDetail:function(hcId){
        var me = this
        var id = hcId;
        //加载普查详情tabs
        Ext.Ajax.request({
            url: app.platform.lshc.view.base.RequestUtils.url("/regiInfo/getRegiInfoDetail"),
            method: "post",
            async: false,
            params: {hcId: id},
            success: function (response) {
                var data = JSON.parse(response.responseText);
                console.log("-----get regi detail:")
                console.log(data)

                //更新右侧顶部状态
                var hStatus = data.regiInfo.houseStatus;
                if(hStatus.indexOf("新") != -1
                || hStatus.indexOf("录") != -1
                || hStatus.indexOf("初审不通过") != -1){
                    me.initStatus(hStatus, "", "①社区", "->②街道办->③区政府->④市普查办");
                }else if(hStatus.indexOf("待初审") != -1
                    || hStatus.indexOf("复审不通过") != -1){
                    me.initStatus(hStatus, "①社区", "->②街道办", "->③区政府->④市普查办");
                }else if(hStatus.indexOf("初审通过") != -1
                    || hStatus.indexOf("退回") != -1){
                    me.initStatus(hStatus, "①社区->②街道办", "->③区政府", "->④市普查办");
                }else if(hStatus.indexOf("复审通过") != -1){
                    me.initStatus(hStatus, "①社区->②街道办->③区政府", "->④市普查办", "");
                }else{
                    me.config.parentContainer.initStatus("无", "", "", "无");
                    //me.initStatus(hStatus, "", "", "①社区->②街道办->③区政府->④市普查办");
                }

                var houseDetalPanel = me.queryById("lshc-view-regi-HouseDetailView-itemId");
                houseDetalPanel.config.bizId = id;
                houseDetalPanel.fillForm(data);
                //houseDetalPanel.readOnly(true);
            },
            failure: function () {
                Ext.MessageBox.alert("温馨提示", "服务器异常,请稍后重试!");
            }
        });

    },
    resetForm:function(){
        var houseDetailView = this.queryById("lshc-view-regi-HouseDetailView-itemId");
        houseDetailView.resetForm();
    },
    setTask: function (show, message) {
        var myTask = Ext.lshc_task;
        var me = this;
        if (!myTask) {
            if (!message) {
                message = "请等待...";
            }
            var myTask = new Ext.LoadMask({
                msg: message,
                target: Ext.mainFrame
            });
            Ext.lshc_task = myTask;
        }
        if (message) {
            myTask.msg = message;
        }
        if (show) {
            myTask.show();
        } else {
            myTask.hide();
        }
        return myTask;
    },
    initComponent: function () {
        var me = this;

       var mStore = Ext.create('Ext.data.Store', {
            fields: ['name','value'],
            autoLoad: true,
            data: [
                {'name': '全部', value: ''},
                {'name': '录入',value:0},
                {'name': '初审',value:1}
            ]
        });

        Ext.apply(me, {
        tbar: {
                layout: 'column', scope: me,
                items: [
                    
                    {
                        xtype: 'toolbar', columnWidth: 1,scope: me, itemId: 'search',
                        items: [
							'->',
                            {xtype:'xcombobox',
                                itemId:'houseStatusItemId',
                                fieldLabel:'房屋状态',
                                labelWidth:56,
                                emptyText:'全部',
                                width:156,
                                name:"houseStatus",
                                editable:false,
                                triggerAction:'all',
                                dataSourceUrl:app.platform.lshc.view.base.RequestUtils.url('/basic/getDictionaryListName?type=HOUSE_STATUS&showALL=1')
                            },
                           {
								xtype:"textfield",
								labelAlign:'right',
								itemId:"houseIdItemId",
								name:"houseId",
								labelWidth:56,
								fieldLabel:'房屋编码',
								width:160,
								emptyText:"房屋编码"
						  },
                            {
                                xtype:"textfield",
                                labelAlign:'right',
                                itemId:"idNoItemId4",
                                name:"idNo",
                                labelWidth:105,
                                fieldLabel:'人员/单位证件号',
                                width:220,
                                emptyText:"人员/单位证件号"
                            },
                           {
                                xtype: 'button', text: '查询', scope: me,
                                glyph:0xf002,
                                handler: function () {
                                     gridObjStore = me.getHouseListStore();
                                     gridObjStore.proxy.extraParams = me.getParams();//获取列表store
								     gridObjStore.loadPage(1);
									 //点击第一条记录
                                }
                            },
                            {
                                xtype: 'button', text: '重置', scope: me,
                                glyph:'xf0e2@FontAwesome',
                                handler: function () {
                                    me.resetParams();
                                }
                            },
                            {
                                xtype:'button',
                                text:'返回',
                                hidden:false,
                                glyph: 0xf0a5,
                                handler:function() {
                                    me.config.parentContainer.show();
                                    me.config.parentContainer.initHouseList();
                                    me.destroy();
                                }
                            }
                        ]
                    }
                ]
            },
	          // xtype:'container',
				// height: '100%',
				// width:'100%',
                padding:"0 0 0 5",
			   layout:{
					type:'border'
				//	regionWeights:{
					//	west:30,
					//	east:30
					//}
				},
				
			 items: [{
                region: 'north',//指定组建具体位置，上方
                height: '200',
				items:[
				 {
                        xtype: 'toolbar', columnWidth: 1,scope: me, itemId: 'search',
                        items: [
                            {   xtype: 'tbtext',
								margin: '0 0 0 0',
								//width:'98px',
								html:'<div style="font-weight:bold;font-size:13">楼栋信息：</div>'
							},
							{   xtype: 'tbtext',
								margin: '0 0 0 0',
								//width:'98px',
							    itemId:"mapCodeTbItemId",
								html:'<div style="font-weight:bold;font-size:10"></div>'
							},
							{   xtype: 'tbtext',
								margin: '0 0 0 0',
								//width:'98px',
							    itemId:"addressTbItemId",
								html:'<div style="font-weight:bold;font-size:10;margin-left:10;float:right"></div>'
							},
								"->",
							{   xtype: 'tbtext',
								margin: '0 0 0 0',
								//width:'98px',
								html:'<div style="font-weight:bold;font-size:13;">状态：</div>'
							},
							{   xtype: 'tbtext',
								margin: '0 0 0 0',
								//width:'98px',
						       itemId:"statusTbItemId",
								html:'<div style="font-weight:bold;font-size:10;color:green;"></div>'
							},
							{   xtype: 'tbtext',
								margin: '0 0 0 0',
								//width:'98px',
								html:'<div style="font-weight:bold;font-size:13;margin-left:10;">房屋当前处理环节：</div>'
							},
							{   xtype: 'tbtext',
								margin: '0 0 0 0',
								//width:'98px',
						       itemId:"doneTbItemId",
								html:'<div style="font-weight:bold;font-size:11;color:green;"></div>'
							},
                           {   xtype: 'tbtext',
								margin: '0 0 0 0',
								//width:'98px',
						        itemId:"doingTbItemId",
								html:'<div style="font-weight:bold;font-size:11;color:orange;"></div>'
							},
							{   xtype: 'tbtext',
								margin: '0 0 0 0',
								//width:'98px',
							   itemId:"todoTbItemId",
								html:'<div style="font-weight:bold;font-size:11;margin-right:50;">①社区->②街道办->③区政府->④市普查办</div>'
							}
                        ]
                    }	
				]
            }, {
                region: 'west',//左方
                width: '42%',
                 itemId:'westItemId',
                // height:'90%',
                items:[
					{
                           // title: '房屋列表信息',
							itemId: 'HouseListView-Tab-itemId',
                            autoScroll: true,
                            scrollable: true,
                            // autoHeight:true,
                            // height:'300',
                            //bodyStyle : 'overflow-y:scroll',
                            // bodyStyle : 'overflow-x:hidden; overflow-y:scroll',
                            items: [
                                {xtype: 'lshc-view-regi-HouseListView',parentContainer:me,bizId:me.config.bizId,srcType:me.config.srcType}
                            ]
                        }
				]
            }, {
                region: 'center',
                // height:'90%',
                items:[
					{
                            itemId: 'tab2',
                            autoScroll: true,
                            scrollable: true,
                            border:true,
                            items: [
                                {xtype: 'lshc-view-regi-HouseDetailView',parentContainer:me,srcType:me.config.srcType}
                            ]
                        }
				]
            }]
            
        });
        me.callParent(arguments);
    } 

});

