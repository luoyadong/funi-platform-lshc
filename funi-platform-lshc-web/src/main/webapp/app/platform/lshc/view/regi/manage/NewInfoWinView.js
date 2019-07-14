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
    // config: {
    //     parentContainer:null
    // },
    // constructor: function (config) {
    //     var me = this;
    //     config = config || {};
    //     Ext.applyIf(config, me.config);
    //     this.callParent(arguments);
    // },
    title: '新增普查',
    width: 640,
    height:480,
    autoScroll: false,
    //resizable: true,
    buttonAlign: 'center',
    modal: true,
    maskDisabled:false,
    initWinEdit:function(){
        var me = this;
        var editHouseView = me.queryById("lshc-view-regi-HouseEditView-itemId");
    },
    initComponent: function () {
        var me = this;
        me.on('close', function(){

            var editHouseView = me.queryById("lshc-regi-houseedit-parent-itemId");
            editHouseView.destroy();
            me.destroy();
            // //关闭清理状态
            // editHouseView.config.bizId = null;
            // editHouseView.config.parentContainer = null;
            // editHouseView.resetForm();

        });
        Ext.apply(me, {
			xtype:'container',
			border: true,
            width:640,
            height:480,
            items: [
                {
                    itemId:'lshc-view-regi-HouseEditView-itemId',
                    xtype:'lshc-view-regi-HouseEditView',winContainer:me,parentContainer:me.config.parentContainer
				}
			]
        });
        me.callParent(arguments);
    }

})