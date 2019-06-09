/**
 * 审批
 */
Ext.onReady(function(){
    Ext.QuickTips.init();
    Ext.form.Field.prototype.msgTarget='side';
});
Ext.define('app.platform.lshc.view.regi.manage.NewInfoWinView', {
    extend: 'Ext.window.Window',
	requires: [
        //新增项目分期信息Tab视图
        'app.platform.lshc.view.regi.manage.HouseEditView'
    ],
    xtype: 'lshc-view-regi-NewInfoWinView',
    config: {
        parentPanel:null
    },
    constructor: function (config) {
        var me = this;
        config = config || {};
        Ext.applyIf(config, me.config);
        this.callParent(arguments);
    },
    title: '新增普查',
    width: 900,
    height:580,
    autoScroll: false,
    resizable: true,
    buttonAlign: 'center',
    modal: true,
    initComponent: function () {
        var me = this;
        me.on('close', function(){
        });
        Ext.apply(me, {
			xtype:'container',
			border: true,
            width:900,
            height:580,
            items: [
                {
                    xtype:'lshc-view-regi-HouseEditView'
				}
			]
        });
        me.callParent(arguments);
    }

})