/**
 *
 *@author 3
 */
Ext.define("app.platform.lshc.view.regi.manage.HouseDetailView",{
    extend: 'Ext.Panel',
	xtype: 'lshc-view-regi-HouseDetailView',
    layout:"fit",
    config:{},
    requires:[
		"app.platform.lshc.view.regi.manage.HouseTab",
		"app.platform.lshc.view.regi.manage.FileTab",
		"app.platform.lshc.view.regi.manage.ApproveTab",
		"app.platform.lshc.view.regi.manage.RightEntWin"
    ],
    itemId:'lshc-regi-housedetail-parent-itemId',
    items:[
        {
            xtype:'tabpanel',itemId:'lshc-regi-housedetail-parent-tabpanel-itemId',
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
                },{
                    title:'处理过程',
                    glyph: 'xf022@FontAwesome',
					itemId:'tab3',
                    xtype:"lshc-regi-manage-approve",
                    overflowY:'auto',
                    disabled:false,
                    width:"100%"
                }
            ]
        }
    ],
    initComponent: function () {
        var me = this;
        Ext.apply(me, {
            tbar: [
                {
                    xtype: 'toolbar', scope: me,height:30,
                    items:[
                        {
                            xtype:'button',
                            text:'编辑',
                            hidden:false,
                            glyph: 0xf044,
                            handler:function() {
                            }
                        },
                        {
                            xtype:'button',text:"打印",glyph: 'xf02f@FontAwesome',itemId:"commit",
                            handler:function(){
                                        Ext.create("app.platform.lshc.view.regi.manage.RightEntWin", {
                                            
                                        }).show();
                            }
                        }
                    ]
                }
            ]
        });
        me.callParent(arguments);
    }
})
