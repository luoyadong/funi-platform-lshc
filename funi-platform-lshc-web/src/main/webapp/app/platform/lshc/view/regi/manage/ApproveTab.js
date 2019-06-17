/**
 * 文件
 *@author 3
 */
Ext.define("app.platform.lshc.view.regi.manage.ApproveTab",{
    extend:"Ext.grid.Panel",
    requires:[
    ],
    xtype:"lshc-regi-manage-approve",
	itemId:"lshc-regi-manage-approve-itemId",
    layout:"fit",
    initComponent:function(){
        var me = this;
		var store = Ext.create("Funi.data.ListStore",{
            //url:app.platform.ghouse.view.base.RequestUtils.url("/job/log/list"),
            fields:[
			    {type:"string",name:"id"},
                 {type: "string", name: "createDateStr"},
                {type: "string", name: "conclusion"},
                {type: "string", name: "auditName"},
                {type: "string", name: "unitName"},
                {type: "string", name: "jobOpinion"}
            ],
			data:[
			{"id":"eqweqwewq","createDateStr":"2019-06-16","conclusion":"初审通过","auditName":"小强","unitName":"拉萨房管局",
				"jobOpinion":"同意"},
			{"id":"eqweqwewq2","createDateStr":"2019-06-16","conclusion":"复审不通过","auditName":"小强","unitName":"拉萨房管局",
				"jobOpinion":"不同意"}
			],
			load:false,
            pageSize:15
        });
        Ext.applyIf(me,{
			xtype: 'gridpanel',
            itemId: 'approveGridpanel',
            border: true,
            store: store,
            columnLines: true,
            viewConfig: {
                enableTextSelection: true
            },
            selType: "checkboxmodel",
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

