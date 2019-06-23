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
    getParams:function(){
        var me = this;
        var formElements = Ext.ComponentQuery.query("textfield",this);
        var obj = new Object();
        obj["buildId"] = me.config.bizId;
        for(var i=0;i<formElements.length;i++){
            obj[formElements[i].name] =formElements[i].value;
        }
        return obj;
    },
	resetParams:function(){
        var formElements = Ext.ComponentQuery.query("textfield",this);
        var obj = new Object();
        for(var i=0;i<formElements.length;i++){
			this.queryById(formElements[i].itemId).setValue(null)
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

		//初始化房屋列表,HouseListView-Tab-itemId  lshc-view-regi-HouseListView-itemId
        me.initHouseList();
	},
    initHouseList:function(){
        var me = this;
        gridObjStore = me.getHouseListStore();
        gridObjStore.proxy.extraParams = me.getParams();
        gridObjStore.loadPage(1);
    },
    getHouseListStore:function(){
        var houseListPanel = this.queryById("lshc-view-regi-HouseListView-itemId");
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

         var houseListView = this.queryById("lshc-view-regi-HouseListView-itemId");
		 var view = Ext.ComponentQuery.query("textfield,button,checkbox,toolbar",houseListView);
            for(var j=0;j<view.length;j++){
                if(view[j].xtype=="button"){
					if(view[j].text != '导出' && view[j].text != '审批'){
						view[j].hide();
					}
                    
                }else{
                    view[j].readOnly=true;
                }
            }
       
        var tabpanelView = this.queryById("lshc-regi-housedetail-parent-tabpanel-itemId");
        for(var i=0;i<tabpanelView.items.length;i++){
            if(tabpanelView.items.items[i].down("xgridpanel") && tabpanelView.items.items[i].down("xgridpanel").selModel){
                tabpanelView.items.items[i].down("xgridpanel").disableSelection=true;
                tabpanelView.items.items[i].down("xgridpanel").addListener("beforecellclick",function(){
                    return false;
                }) ;
                tabpanelView.items.items[i].down("xgridpanel").addListener("beforedeselect",function(){
                    return false;
                })
            }
            var view = Ext.ComponentQuery.query("textfield,button,checkbox,toolbar",tabpanelView.items.items[i]);
            for(var j=0;j<view.length;j++){
                if(view[j].xtype=="button" || view[j].xtype=="toolbar"){
                    if(view[j].text != '导出' && view[j].text != '审批'){
						view[j].hide();
					}
                }else{
                    view[j].readOnly=true;
					view[j].disabled = true;
                }

            }
        }
       
        //上传
        var uploadViews = Ext.ComponentQuery.query("fileuploadfield,#delete_file",this);
        for(var i=0;i<uploadViews.length;i++){
            uploadViews[i].hidden=true;
        }

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

                var houseDetalPanel = me.queryById("lshc-view-regi-HouseDetailView-itemId");
                houseDetalPanel.config.bizId = id;
                houseDetalPanel.fillForm(data);
                //houseDetalPanel.readOnly(true);
            },
            failure: function () {
                Ext.MessageBox.alert("温馨提示", "服务器异常,请稍后重试!");
            }
        });

        //更新右侧顶部状态
        me.initStatus("初审通过", "①社区", "->②街道办 ", "->③区政府->④市住建局");

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
							{
                                xtype: 'combobox',
                                fieldLabel: '状态',
                                emptyText: '全部',
                                store: mStore,
                                editable: false,
                                valueField: 'value',
								name:"",
                                itemId:'isVacant',
                                displayField: 'name',
                                labelWidth: 60,
                                width: 150,
                            },
                           {
								xtype:"textfield",
								labelAlign:'right',
								itemId:"houseIdItemId",
								name:"houseId",
								labelWidth:65,
								fieldLabel:'房屋编码',
								width:240,
								emptyText:"房屋编码"
						  },
                            {
								xtype:"textfield",
								labelAlign:'right',
								itemId:"entNameItemId",
								name:"entName",labelWidth:65,fieldLabel:'人员姓名',
								width:240,
								emptyText:"人员姓名"
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
                                }
                            }
                        ]
                    }
                ]
            },
	          // xtype:'container',
				height: '100%',
				width:'100%',
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
                //height: 40,
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
								html:'<div style="font-weight:bold;font-size:10">XS1234561</div>'
							},
							{   xtype: 'tbtext',
								margin: '0 0 0 0',
								//width:'98px',
							    itemId:"addressTbItemId",
								html:'<div style="font-weight:bold;font-size:10;margin-left:10;float:right">城关区纳金乡1001号2栋</div>'
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
								html:'<div style="font-weight:bold;font-size:10;color:green;">审核通过</div>'
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
								html:'<div style="font-weight:bold;font-size:11;color:green;">①社区->②街道办</div>'
							},
                           {   xtype: 'tbtext',
								margin: '0 0 0 0',
								//width:'98px',
						        itemId:"doingTbItemId",
								html:'<div style="font-weight:bold;font-size:11;color:orange;">->③区政府</div>'
							},
							{   xtype: 'tbtext',
								margin: '0 0 0 0',
								//width:'98px',
							   itemId:"todoTbItemId",
								html:'<div style="font-weight:bold;font-size:11;margin-right:50;">->④市住建局</div>'
							}
                        ]
                    }	
				]
            }, {
                region: 'west',//左方
                width: '45%',
                items:[
					{
                           // title: '房屋列表信息',
							itemId: 'HouseListView-Tab-itemId',
                            autoScroll: true,
                            scrollable: true,
                            height:450,
                            //bodyStyle : 'overflow-y:scroll',
                            // bodyStyle : 'overflow-x:hidden; overflow-y:scroll',
                            items: [
                                {xtype: 'lshc-view-regi-HouseListView',parentContainer:me,bizId:me.bizId}
                            ]
                        }
				]
            }, {
                region: 'center',
                items:[
					{
                            itemId: 'tab2',
                            autoScroll: true,
                            scrollable: true,
                            items: [
                                {xtype: 'lshc-view-regi-HouseDetailView'}
                            ]
                        }
				]
            }]
            
        });
        me.callParent(arguments);
    } 

});

