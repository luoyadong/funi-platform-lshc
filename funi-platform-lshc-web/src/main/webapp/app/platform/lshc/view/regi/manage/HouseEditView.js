/**
 *
 *@author 3
 */
Ext.define("app.platform.lshc.view.regi.manage.HouseEditView",{
    extend: 'Ext.Panel',
	xtype: 'lshc-view-regi-HouseEditView',
    layout:"fit",
    config:{},
    requires:[
		"app.platform.lshc.view.regi.manage.HouseTab",
		"app.platform.lshc.view.regi.manage.FileTab",
		"app.platform.lshc.view.regi.manage.ApproveTab",
		"app.platform.lshc.view.regi.manage.RightEntWin"
    ],
    itemId:'lshc-regi-houseedit-parent-itemId',
    
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
							xtype:"lshc-regi-manage-houseInfo",
							title:'普查信息',
							overflowY:'auto',
							width:"100%",
							height:500,
							disabled:false
						},{
							title:'图片',
							glyph: 'xf0e0@FontAwesome',
							xtype:"lshc-regi-manage-file",
							disabled:false,
							overflowY:'auto',
							width:"100%",
							itemId:'tab2'
						}
					]
				}
				
			]
        });
        me.callParent(arguments);
    }
})
