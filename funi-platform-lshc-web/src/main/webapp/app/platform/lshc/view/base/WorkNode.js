Ext.define('app.platform.lshc.view.base.WorkNode', {
    extend: 'Ext.Panel',
    layout: 'fit',
    title: "工作意见",
    requires:["app.platform.lshc.view.base.RequestUtils"],
    itemId: 'ghouse-base-WorkNode-ItemId',
    xtype: "ghouse-base-WorkNode-xtype",
    listeners: {
        render: function () {

        }
    },
    getData:function(){
        var form = this.queryById("ghouse-base-worknodepanel-itemId").down("form");
        if(!form)return{};
        if(form.isValid()){
            var values = form.getValues();
            var displayVal = this.queryById("ghouse-base-worknode-result").getRawValue();
            var jobResultId = values.jobResult;
            values.jobResult = displayVal;
            return {"jobAccept":{"serviceNum":values.serviceNum},"jobLog":values,"jobResultId":jobResultId};
        }
        throw {message:"请填写工作意见"};
    },
    close: true,
    items: [{xtype: 'ghouse-base-worknodepanel-xtype'}]
});
Ext.define('app.platform.lshc.view.base.NodeContractPanel', {
    extend: 'Ext.Panel',
    border: true,
    padding: '20',
    width: '100%',
    layout: "vbox",
    itemId: "ghouse-base-worknodepanel-itemId",
    xtype: 'ghouse-base-worknodepanel-xtype',
    initComponent: function () {
        Ext.applyIf(this, {
            items: [  {
                xtype: "form",
                items: [{
                    xtype: "xcombobox",
                    itemId: 'ghouse-base-worknode-result',
                    fieldLabel: '审核结果',
                    editable: false,
                    allowBlank: false,
                    valueField: 'code',
                    displayField:"name",
                    name: "jobResult",
                    dataSourceUrl:"/",
                    listeners: {
                        afterRender: function () {
                            var combo = this;
                            combo.store.load({
                                callback: function () {
                                    var sources = this.data.items[0];
                                    if (sources) {
                                        combo.select(sources);
                                    }
                                }
                            });
                        },
                        change: function (cmp, newValue, oldValue, eOpts) {

                        }
                    }
                }, {
                    xtype: 'textarea', itemId: "ghouse-base-worknodepanel-option-itemId", fieldLabel: '审核意见',
                    width: 788, height: 188, maxLength: 2000,
                    name: 'jobOpinion'
                },{
                        xtype: 'textfield', itemId: "ghouse-base-worknodepanel-serviceNum-itemId", hidden: true,
                        name: 'serviceNum'
                },{
                    xtype: 'textfield', itemId: "ghouse-base-worknodepanel-nodeName-itemId", hidden: true,
                    name: 'nodeName'
                }]
            }, {
                xtype: 'ghouse-base-worknode-grid-xtype'
            }]
        });
        this.callParent(arguments);
    }
});
Ext.define('app.platform.lshc.view.base.WorkNodeGrid', {
    extend: 'Ext.grid.Panel',
    border: true,
    width: '100%',
    height: 180,
    columnLines: true,
    autoScroll: true,
    overflowY: 'auto',
    xtype: 'ghouse-base-worknode-grid-xtype',
    itemId:'ghouse-base-worknode-grid-itemId',
    tbar: [{
        xtype: 'label',
        text: '流转记录'
    }],
    columns: [{
        text: "节点",
        dataIndex: "nodeName",
        width: 157,
        renderer: function (val) {
            return val;
        }
    }, {
        text: "人员", dataIndex: "auditName", width: 157
    }, {
        text: "时间", dataIndex: "createDateStr", width: 157
    }, {
        text: "结论", dataIndex: "conclusion", width: 200
    }, {
        text: "处理意见", dataIndex: "jobOpinion", flex: 1
    }],
    initComponent: function () {
        var store = Ext.create("Funi.data.ListStore", {
            url: app.platform.lshc.view.base.RequestUtils.url("/job/log/list"),
            autoLoad: false,
            fields: [
                {type: "string", name: "nodeName"},
                {type: "string", name: "auditName"},
                {type: "string", name: "createDateStr"},
                {type: "string", name: "conclusion"},
                {type: "string", name: "jobOpinion"}
            ]
        });
        Ext.applyIf(this, {store: store});
        this.callParent(arguments);
    }
});