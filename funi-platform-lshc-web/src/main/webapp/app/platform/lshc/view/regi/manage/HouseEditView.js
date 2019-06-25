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

    },
    resetForm:function(){
        //房屋信息 regiInfo entInfoList fileList
        var houseView = this.queryById("lshc-regi-houseedit-tabpanel-itemId");
        houseView.getForm().setValues(null);
        //房屋信息-人员列表，lshc-view-HousePerson-itemId
        var entView = this.queryById("lshc-regi-houseedit-tabpanel-itemId").down("xgridpanel");
        entView.store.loadData(null,false);
        //entView.getSelectionModel().selectAll(true);

        //文件列表
        var approveView = this.queryById("lshc-file-houseedit-tabpanel-itemId").down("gridpanel");
        approveView.store.loadData(null,false);

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
        var showSubmitBtn = true;
        if(this.config.bizId != null && this.config.bizId != undefined){
            showSubmitBtn = false;
        }
        return showSubmitBtn;
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
									var formData = me.getData();
                                    var hcId = me.config.bizId;
                                    if(me.config.bizId != null){
                                        formData.regiInfo.id = hcId;
                                        app.platform.lshc.view.base.RequestUtils.post_json(formData, "/manage/editRegiInfo", false, false);
                                    }else{//新增
                                        hcId = app.platform.lshc.view.base.RequestUtils.return_post_json(formData, "/manage/addRegiInfo",false,false);
                                    }
                                    //alert(hcId);
                                    //调用错误会影响上面的值
                                    me.config.parentContainer.initHouseDetail(hcId);
                                    me.winContainer.close();
                                    //me.winContainer.destroy();
                                }
                            },
							{
                                xtype: 'button',hidden:!me.isShowSubmitBtn(), text: '提交', scope: me,glyph: 'xf234@FontAwesome',
                                handler: function () {
                                    var formData = me.getData();
                                    app.platform.lshc.view.base.RequestUtils.post_json(formData, "/manage/submitRegiInfo",false,false);
                                    me.winContainer.close();

                                    //刷新左侧的房屋列表
                                    me.config.parentContainer.initHouseList();
                                	//Ext.Msg.alert('提示', '已新增！');
                                }
                            },
							 {
                                xtype: 'button',hidden:!me.isShowSubmitBtn(), text: '提交后继续新增', scope: me,glyph: 'xf234@FontAwesome',
								margin:'0 50 0 0',
                                handler: function () {
                                    Ext.Msg.alert('提示', '暂未提供！');
                                    return;
                                    var formData = me.getData();
                                    app.platform.lshc.view.base.RequestUtils.request(formData, "/manage/submitRegiInfo");

                                    //清除当前界面的输入
                                    me.resetForm();
                                    //刷新左侧的房屋列表
                                    me.config.parentContainer.initHouseList();

                                	//Ext.Msg.alert('提示', '已新增！');

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
							title:'图片',
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
