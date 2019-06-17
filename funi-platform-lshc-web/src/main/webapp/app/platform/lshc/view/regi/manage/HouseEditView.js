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
		"app.platform.lshc.view.base.CommonUtils"
    ],
	itemId:'lshc-regi-houseedit-parent-itemId',
	config: {
        //主容器
        parentContainer: null,
		//普查主表ID
		bizId:null
    },
    constructor: function (config) {
        var me = this;
        config = config || {};
        Ext.applyIf(config, me.config);
        this.callParent(arguments);
    },
    fillForm:function(formData){
        //房屋信息
        var houseView = this.queryById("lshc-regi-manage-houseInfo-itemId").down("form");

		//房屋信息-人员列表
        var entView = this.queryById("lshc-view-HousePerson-itemId").down("xgridpanel");
        entView.store.loadData(formData.houseDtoList,false);
        //entView.getSelectionModel().selectAll(true);

		//文件列表
        var approveView = this.queryById("lshc-regi-manage-file-itemId").down("gridpanel");
        approveView.store.loadData(formData.houseDtoList,false);

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
       app.platform.lshc.view.base.CommonUtils.extend(result,fileView.getData());
		console.log(result)
        return result;
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
									me.getData();
                                	//Ext.Msg.alert('提示', '已新增！');

                                }
                            },
							{
                                xtype: 'button', text: '提交', scope: me,glyph: 'xf234@FontAwesome',
                                handler: function () {

                                	//Ext.Msg.alert('提示', '已新增！');

                                }
                            },
							 {
                                xtype: 'button', text: '提交后继续新增', scope: me,glyph: 'xf234@FontAwesome',
								margin:'0 50 0 0',
                                handler: function () {

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
							itemId:'tab1',
							glyph: 'xf0c0@FontAwesome',
							title:'普查信息',
							overflowY:'auto',
							width:"100%",
							height:500,
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
							itemId:'tab2',
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
