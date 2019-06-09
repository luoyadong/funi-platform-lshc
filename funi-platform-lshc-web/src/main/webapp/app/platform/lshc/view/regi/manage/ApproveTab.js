/**
 * 文件
 *@author 3
 */
Ext.define("app.platform.lshc.view.regi.manage.ApproveTab",{
    extend:"Ext.grid.Panel",
    requires:[
    ],
    xtype:"lshc-regi-manage-approve",
    layout:"fit",
    itemId:"lshc-regi-manage-approve-itemId",
    initComponent:function(){
        var me = this;
        Ext.applyIf(me,{
			xtype: 'gridpanel',
            itemId: 'proGridpanel',
            border: true,
            //store: store,
            columnLines: true,
            viewConfig: {
                enableTextSelection: true
            },
            selType: "checkboxmodel",
		    columns:[
			    {text:"序号",dataIndex:"contractNo",width:'5%'},
                       {text:"操作时间",dataIndex:"contractStatusStr",width:'15%'},
                       {text:"操作动作",dataIndex:"houseNum",width:'10%'},
                       {text:"上传时间",dataIndex:"rentArea",width:'15%'},
                       {text:"操作账号",dataIndex:"rentPerMonth",width:'15%'},
                       {text:"所属单位",dataIndex:"organizationName",width:'15%'},
                       {text: '审核原因', dataIndex: 'houseStatus', width: '15%', align: 'center'}
		   ]
        });
        this.callParent(arguments);
    }
})

