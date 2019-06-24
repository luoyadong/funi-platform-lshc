/**
 * 文件
 *@author 3
 */
Ext.define("app.platform.lshc.view.regi.manage.ApproveTab",{
    extend:"Ext.panel.Panel",
    requires:[
        "app.platform.lshc.view.base.RequestUtils"
    ],
    xtype:"lshc-regi-manage-approve",
	itemId:"lshc-regi-manage-approve-itemId",
    layout:"fit",
    initComponent:function(){
        var me = this;
		var store = Ext.create("Funi.data.ListStore",{
            url:app.platform.lshc.view.base.RequestUtils.url("/job/log/list"),
            fields:[
			    {type:"string",name:"id"},
                {type: "string", name: "createDateStr"},
                {type: "string", name: "conclusion"},
                {type: "string", name: "auditName"},
                {type: "string", name: "unitName"},
                {type: "string", name: "jobOpinion"}
            ],
            pageSize:50
        });
        Ext.applyIf(me,{
			xtype: 'gridpanel',
            itemId: 'approveGridpanel',
            border: true,
            store: store,
            columnLines: true,
            viewConfig: {
                enableTextSelection: false
            },
            //selType: "checkboxmodel",
		    columns:[
                   {text:"id",dataIndex:"id",hidden:true},
                   {text:"序号",dataIndex:"id",hidden:true},
                   {text:"操作时间",dataIndex:"createDateStr",flex: 1},
                   {text:"操作动作",dataIndex:"conclusion",flex: 1},
                   {text:"操作账号",dataIndex:"auditName",flex: 1},
                   {text:"所属单位",dataIndex:"unitName",flex: 1},
                   {text: '审核原因', dataIndex: 'jobOpinion', flex: 1}
		   ]
        });
        this.callParent(arguments);
    }
})

