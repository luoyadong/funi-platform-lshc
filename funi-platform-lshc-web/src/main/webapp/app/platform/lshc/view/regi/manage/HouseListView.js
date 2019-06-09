/**
 * 房屋普查管理
 * @author luoyadong@funi365.com
 */

Ext.define('app.platform.lshc.view.regi.manage.HouseListView', {
    extend: 'Ext.grid.Panel',
    xtype: 'lshc-view-regi-HouseListView',
    requires: [
        //新增项目分期信息Tab视图
        'app.platform.lshc.view.regi.manage.ApproveWinView',
		'app.platform.lshc.view.regi.manage.NewInfoWinView'
    ],
    config: {
        //主容器
        parentContainer: null
    },
    constructor: function (config) {
        var me = this;
        config = config || {};
        Ext.applyIf(config, me.config);
        this.callParent(arguments);
    },
    title: null,

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
                                xtype: 'button', text: '新建普查', scope: me,glyph: 0xf0fe,
                                handler: function () {
                                    Ext.create("app.platform.lshc.view.regi.manage.NewInfoWinView").show();
                                	//Ext.Msg.alert('提示', '已新增！');

                                }
                            },
								 {
                                xtype: 'button', text: '批量导入', scope: me,glyph: 'xf234@FontAwesome',
                                handler: function () {

                                	//Ext.Msg.alert('提示', '已新增！');

                                }
                            },
							 {
                                xtype: 'button', text: '导出', scope: me,glyph: 'xf234@FontAwesome',
                                handler: function () {

                                	//Ext.Msg.alert('提示', '已新增！');

                                }
                            },
								 {
                                xtype: 'button', text: '审批', scope: me,glyph: 0xf044,
                                handler: function () {
								Ext.create("app.platform.lshc.view.regi.manage.ApproveWinView").show();
								}
								 },
									
                            {
                                xtype: 'button', text: '删除', scope: me,glyph: 'xf014@FontAwesome',
                                handler: function () {

                                	//Ext.Msg.alert('提示', '已新增！');

                                }
                            }
                        ]
                    }
                ]
            },
            xtype: 'gridpanel',
            itemId: 'proGridpanel',
            border: true,
            //store: store,
            columnLines: true,
            viewConfig: {
                enableTextSelection: true
            },
            selType: "checkboxmodel",
            columns: [
                {text: '业务ID',hidden:true, dataIndex: 'PERIOD_CODE', align: 'center'},
                {text: '房屋编号', dataIndex: 'PERIOD_CODE', width: '20%', align: 'center'},
                {text: '单元', dataIndex: 'PERIOD_CODE', width: '15%', align: 'center'},
                {text: '楼层', hidden:true,dataIndex: 'BIZ_NO', width: '15%', align: 'center'},
                {text: '房号', dataIndex: 'BIZ_NO', BIZ_NO: '15%', align: 'center'},
                {text: '状态', dataIndex: 'PERIOD_CODE', width: '15%', align: 'center'},
                {text: '图片', dataIndex: 'BIZ_NO', width: '20%', align: 'center'}
            ],
            listeners: {
                cellclick: function (table, td, cellIndex, record, tr, rowIndex, e, eOpts) {

                },
                itemdblclick: function (dataview, record, item, index, e) {
                   
                }
            }

        });
        me.callParent(arguments);

    }
});

