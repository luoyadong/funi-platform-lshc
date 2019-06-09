/**
 * 房屋普查管理
 * @author luoyadong@funi365.com
 */

Ext.define('app.platform.lshc.view.regi.manage.RegiMainView', {
    extend: 'Ext.grid.Panel',
    xtype: 'psfs-view-foundsApply-ApplyListView',
    requires: [
        //新增项目分期信息Tab视图
        'app.platform.lshc.view.regi.manage.ApproveWinView',
        'app.platform.lshc.view.regi.manage.RegiDetailView'
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
                                xtype: 'button', text: '审批', scope: me,glyph: 'xf234@FontAwesome',
                                handler: function () {

									Ext.create('app.platform.lshc.view.regi.manage.ApproveWinView', {
										parentViewName: 'app.platform.lshc.view.regi.manage.RegiMainView',
										parentPanel: this,
										parentContainer : me.config.parentContainer
										//parentStore : store,
										//historyId: record.data.historyId
									}).show();

                                	//Ext.Msg.alert('提示', '已新增！');

                                }
                            }
                        ]
                    },

                    {
                        xtype: 'toolbar', columnWidth: 1,scope: me, itemId: 'search',
                        items: [
                            {
                                xtype: 'button', text: '删除', scope: me,
                                glyph: 'xf014@FontAwesome',
                                handler: function () {
                                	//Ext.Msg.alert('提示', '已删除！');

                                    var createContractView = Ext.create("app.platform.lshc.view.regi.manage.RegiDetailView",{
                                        config:{parentContainer:me}
                                    });
                                    var currentActiveTab =  Ext.mainFrame.queryById("centerBox").getActiveTab();
                                    console.log(currentActiveTab)
                                    console.log(createContractView)
                                    me.hide();
                                    //createContractView.show();
                                    currentActiveTab.add(createContractView);
                                }
                            },
                            {
                                xtype: 'textfield',
                                emptyText: '请输入',
                                labelWidth: 60,
                                width: 180,
                                itemId: 'searchContent'
                            },

                            {
                                xtype: 'button', text: '重置', scope: me,
                                glyph:'xf0e2@FontAwesome',
                                handler: function () {
                                    me.query("#searchPeriodCom")[0].setValue('');
                                    me.query("#searchContent")[0].setValue('');
                                    me.query("#searchPeriodStatus")[0].setValue('');
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
                {text: '业务件号', dataIndex: 'PERIOD_CODE', width: '14%', align: 'center'},
                {text: '项目（分期）编号', dataIndex: 'PERIOD_CODE', width: '14%', align: 'center'},
                {text: '业务类型', hidden:true,dataIndex: 'BIZ_NO', width: '14%', align: 'center'},
                {text: '业务类型', dataIndex: 'BIZ_NO', BIZ_NO: '14%', align: 'center'},
                {text: '项目（分期）名称', dataIndex: 'PERIOD_CODE', width: '14%', align: 'center'},
                {text: '监管编号', dataIndex: 'BIZ_NO', width: '14%', align: 'center'}
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

