/**
 *
 *@author 3
 */
Ext.define("app.platform.lshc.view.regi.manage.HouseEditView",{
    extend: 'Ext.Panel',
	xtype: 'lshc-view-regi-HouseEditView',
    layout:"fit",
    requires:[
		"app.platform.lshc.view.regi.manage.HouseTab",
		"app.platform.lshc.view.regi.manage.FileTab",
		"app.platform.lshc.view.regi.manage.ApproveTab",
		"app.platform.lshc.view.regi.manage.RightEntWin",
		"app.platform.lshc.view.base.CommonUtils",
        'app.platform.lshc.view.base.RequestUtils'
    ],
    //maskDisabled:true,
	itemId:'lshc-regi-houseedit-parent-itemId',
	config: {
        //主容器
        parentContainer: null,
		//普查主表ID
		bizId:null,
        winContainer:null
    },
    constructor: function (config) {
        var me = this;
        config = config || {};
        Ext.applyIf(config, me.config);
        this.callParent(arguments);
    },
    fillForm:function(formData){
        this.config.houseStatus = formData.regiInfo.houseStatus;
        //房屋信息 regiInfo entInfoList fileList
        var houseView = this.queryById("lshc-regi-houseedit-tabpanel-itemId").queryById("lshc-regi-manage-houseInfo-form-itemId");
        houseView.getForm().setValues(formData.regiInfo);
        //房屋信息-人员列表，lshc-view-HousePerson-itemId
        var entView = this.queryById("lshc-regi-houseedit-tabpanel-itemId").down("xgridpanel");
        entView.store.loadData(formData.entInfoList,false);
        //entView.getSelectionModel().selectAll(true);

        //文件列表
        var approveView = this.queryById("lshc-file-houseedit-tabpanel-itemId").down("gridpanel");
        approveView.store.loadData(formData.fileList,false);

        this.isShowSubmitBtn();

        var hTab = this.queryById("lshc-regi-houseedit-tabpanel-itemId").queryById("lshc-regi-manage-houseInfo-itemId");
        hTab.initEditStreet(formData.regiInfo.street);

    },
    resetForm:function(){
        this.config.bizId = null;
        this.config.houseStatus = null;
        //房屋信息 regiInfo entInfoList fileList
        var houseView = this.queryById("lshc-regi-houseedit-tabpanel-itemId").queryById("lshc-regi-manage-houseInfo-form-itemId");
        var view = Ext.ComponentQuery.query("textfield,numberfield,xcombobox,checkbox",houseView);
        for(var j=0;j<view.length;j++){
            view[j].setValue(null);
        }

        //房屋信息-人员列表，lshc-view-HousePerson-itemId
        var entView = this.queryById("lshc-regi-houseedit-tabpanel-itemId").down("xgridpanel");
        entView.store.loadData({},false);

        //文件列表
        var approveView = this.queryById("lshc-file-houseedit-tabpanel-itemId").down("gridpanel");
        approveView.store.loadData({},false);

        this.isShowSubmitBtn();

    },
	getData:function(){
        var result={};
        //var tabpanelView = this.queryById("lshc-regi-houseedit-parent-tabpanel-itemId");
        // for(var i=0;i<tabpanelView.items.length;i++){
		//	console.log(tabpanelView.items.items[i])
        //   tabpanelView.setDisabled(false);
        //   app.platform.lshc.view.base.CommonUtils.extend(result,tabpanelView.items.items[i].getData());
       //  }

       var houseView = this.queryById("lshc-regi-manage-houseInfo-itemId");
       var fileView = this.queryById("lshc-regi-manage-file-itemId");
       app.platform.lshc.view.base.CommonUtils.extend(result,houseView.getData());
        result.fileList = fileView.getData()
       //app.platform.lshc.view.base.CommonUtils.extend(result,fileView.getData());
		console.log(result)
        return result;
    },
    isShowSubmitBtn:function(){

        var me = this;
        var resetSubmitBtn = me.queryById("submitAndResetItemId")
        var submitBtn = me.queryById("submitItemId")
        if(me.config.houseStatus != null
            && me.config.houseStatus != '新建'
            && me.config.houseStatus != null && me.config.houseStatus != '初审不通过'){
            resetSubmitBtn.hide();
            submitBtn.hide();
        }

        // var showSubmitBtn = true;
        // if(this.config.bizId != null && this.config.bizId != undefined){
        //     showSubmitBtn = false;
        // }
        // return showSubmitBtn;
    },

    initComponent: function () {
        var me = this;
        Ext.apply(me, {
			tbar: {
                layout: 'column', scope: me,
                items: [
                    {
                        xtype: 'toolbar', columnWidth: 1, scope: me,
                        items: [
                            '->',
							 {
                                xtype: 'button', text: '保存', scope: me,glyph: 0xf0fe,
                                handler: function () {
                                    // me.config.parentContainer.setTask(true)
									var formData = me.getData();
                                    var hcId = me.config.bizId;
                                    var flag = true;
                                    if(me.config.bizId != null){
                                        formData.regiInfo.id = hcId;
                                        flag = app.platform.lshc.view.base.RequestUtils.post_json(formData, "/manage/editRegiInfo", false, false);
                                    }else{//新增
                                        var rtObj = app.platform.lshc.view.base.RequestUtils.return_post_json(formData, "/manage/addRegiInfo",false,false);
                                        hcId = rtObj.data
                                        flag = rtObj.flag
                                    }
                                    //调用错误会影响上面的值
                                    if(flag){
                                        me.config.parentContainer.initHouseList();
                                        me.config.parentContainer.initHouseDetail(hcId);
                                        me.winContainer.close();
                                    }
                                }
                            },
							{
                                xtype: 'button',itemId:"submitItemId", text: '提交', scope: me,glyph: 0xf0a4,
                                handler: function () {
                                    var formData = me.getData();
                                    var hcId = me.config.bizId;
                                    var flag = true;
                                    if(me.config.bizId != null){
                                        formData.regiInfo.id = hcId;
                                        flag = app.platform.lshc.view.base.RequestUtils.post_json(formData, "/manage/editAndSubmitRegiInfo", false, false);
                                    }else{//新增
                                        var rtObj = app.platform.lshc.view.base.RequestUtils.return_post_json(formData, "/manage/submitRegiInfo",false,false);
                                        hcId = rtObj.data
                                        flag = rtObj.flag
                                    }
                                    if(flag){
                                        me.config.parentContainer.initHouseList();
                                        me.config.parentContainer.resetForm();
                                        me.config.parentContainer.initStatus("无", "", "", "无");
                                        //me.config.parentContainer.initHouseDetail(hcId);
                                        me.winContainer.close();
                                    }

                                }
                            },
							 {
                                xtype: 'button',itemId:"submitAndResetItemId",text: '提交后继续新增', scope: me,glyph: 'xf234@FontAwesome',
								margin:'0 50 0 0',
                                handler: function () {
                                    var formData = me.getData();
                                    var hcId = me.config.bizId;
                                    var flag = true;
                                    if(me.config.bizId != null){
                                        formData.regiInfo.id = hcId;
                                        flag = app.platform.lshc.view.base.RequestUtils.post_json(formData, "/manage/editAndSubmitRegiInfo", false, false);
                                    }else{//新增
                                        var rtObj = app.platform.lshc.view.base.RequestUtils.return_post_json(formData, "/manage/submitRegiInfo",false,false);
                                        hcId = rtObj.data
                                        flag = rtObj.flag
                                    }

                                    if(flag) {
                                        //重新设置编辑界面
                                        me.resetForm();
                                        //刷新左侧的房屋列表
                                        me.config.parentContainer.initHouseList();
                                        me.config.parentContainer.resetForm();
                                        me.config.parentContainer.initStatus("无", "", "", "无");
                                        //me.config.parentContainer.initHouseDetail(hcId);
                                        //me.winContainer.close();
                                    }

                                }
                            }
                        ]
                    }
                ]
            },
            items:[
				{
					xtype:'tabpanel',itemId:'lshc-regi-houseedit-parent-tabpanel-itemId',
					items:[
					   {
							itemId:'lshc-regi-houseedit-tabpanel-itemId',
							glyph: 'xf0c0@FontAwesome',
							title:'普查信息',
							overflowY:'auto',
							width:"100%",
							height:340,
							disabled:false,
							 items: [
                                {xtype: 'lshc-regi-manage-houseInfo',isDetail:false}
                            ]
						},{
							title:'附件',
							glyph: 'xf0e0@FontAwesome',
							disabled:false,
							overflowY:'auto',
							width:"100%",
							itemId:'lshc-file-houseedit-tabpanel-itemId',
							items: [
                                {xtype: 'lshc-regi-manage-file'}
                            ]
						}
					]
				}
				
			]
        });
        me.callParent(arguments);
    }
})
