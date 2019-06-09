/**
 * 审批
 */
Ext.onReady(function(){
    Ext.QuickTips.init();
    Ext.form.Field.prototype.msgTarget='side';
});
Ext.define('app.platform.lshc.view.regi.manage.ApproveWinView', {
    extend: 'Ext.window.Window',
    xtype: 'lshc-view-regi-ApproveWinView',
    config: {
        parentPanel:null
    },
    constructor: function (config) {
        var me = this;
        config = config || {};
        Ext.applyIf(config, me.config);
        this.callParent(arguments);
    },
    title: '审批',
    width: 600,
    height:320,
    //autoScroll: true,
    resizable: false,
    buttonAlign: 'center',
    modal: true,
    initComponent: function () {
        var me = this;
        me.on('close', function(){
        });
        Ext.apply(me, {
            xtype:'container',
            items: [
                {
                    xtype: 'form',
                    scope: me,
                    layout: 'column',
                    border: true,
                    width:600,
                    height:320,
                    items: [
                        {
                            xtype: 'panel', layout: 'column', columnWidth: 1, margin: '10 0 0 0',
                            items: [
								{
									/*xtype: 'radiogroup',*/
									xtype : 'fieldcontainer',
									defaultType: 'radiofield',
									columnWidth: .9,
									colspan: 2,
									fieldLabel: '审批结论',
									allowBlank:false,
									layout: 'hbox',
									labelWidth: 80,
									margin: '0 0 0 10',
									items:[{ boxLabel: '同意',
										name: 'rentorType',
										inputValue: '1',
										checked: true},{
										boxLabel: '取消',
										name: 'rentorType',
										inputValue: '2'
									}]

								},
                                {
                                    xtype: 'textarea',
                                    itemId: 'backReason',
                                    fieldLabel: '撤销原因',
                                    columnWidth: .9,
                                    height:200,
                                    labelWidth: 80,
                                    margin: '0 0 0 10',
                                    allowBlank:false,
                                },
                            ]
                        },
                        {
                            xtype: 'panel', layout: 'column', columnWidth: 1, margin: '10 0 0 0',
                            items: [
                                {
                                    xtype: 'button',
                                    margin: '5 0 0 250',
                                    text: '提交',
                                    handler:function(){
                                        var param = {};
                                        param.backReason = me.queryById("backReason").getValue();
                                        param.historyId = me.config.historyId;
                                        if(null == param.backReason || param.backReason == ''){
                                            Ext.Msg.alert('提示', '撤销原因不能为空');
                                            return false;
                                        }
                                        if(null == param.historyId || param.historyId == ''){
                                            Ext.Msg.alert('提示', '收费记录ID不能为空');
                                            return false;
                                        }
                                        var result = Ext.appContext.invokeService(Funi.core.Context.path("ghouse","/charge/cancelCharge"),param);
                                        if(result && result != null && result != '') {
                                            Ext.Msg.alert('提示', result.message);
                                            if(result.success) {
                                                me.close();
                                                me.config.parentStore.load();
                                            }
                                        }
                                    }
                                },
                                {
                                    xtype: 'button',
                                    margin: '5 0 0 5',
                                    text: '取消',
                                    handler:function(){
                                        me.close();
                                    }
                                }
                            ]
                        }
                    ]
                }

            ]
        });
        me.callParent(arguments);
    }

})