/**
 * 文件
 *@author 3
 */
Ext.define("app.platform.lshc.view.regi.manage.FileTab",{
    extend:"Ext.grid.Panel",
    requires:[
    ],
    xtype:"lshc-regi-manage-file",
    //layout:"fit",
    itemId:"lshc-regi-manage-file-itemId",
    initComponent:function(){
        var me = this;
        Ext.applyIf(me,{
			tbar: {
                layout: 'column', scope: me,
				height:200,
				width:200,
                items: [
                    {
                        xtype: 'toolbar', columnWidth: 1, scope: me,
                        items: [
                            '->',
							 {
                                xtype: 'button', text: '上传', scope: me,glyph: 'xf234@FontAwesome',
                                handler: function () {

                                }
                            }
                        ]
                    }
                ]
            },
			 xtype: 'gridpanel',
            itemId: 'proGridpanel2',
            border: true,
            //store: store,
            columnLines: true,
            viewConfig: {
                enableTextSelection: true
            },
            selType: "checkboxmodel",
		    columns:[
			   {text:"序号",dataIndex:"contractNo",width:'10%'},
			   {text:"图片名称",dataIndex:"contractStatusStr",width:'20%'},
			   {text:"附件大小",dataIndex:"houseNum",width:'10%'},
			   {text:"上传时间",dataIndex:"rentArea",width:'15%'},
			   {text:"上传账号",dataIndex:"rentPerMonth",width:'15%'},
			   {text:"所属单位",dataIndex:"organizationName",width:'15%'},
			   {text: '操作', dataIndex: 'houseStatus', width: '15%', align: 'center'
					, renderer : function(data, metadata, record, rowIndex, columnIndex,store) {
							var oper=null;
							oper= '<a href="javascript:void(0)" style="color: #1f18ff;margin-right:14px;margin-left:2px" class="edit-bill">回单录入</a>';
							return oper;
						}
					}
		   ]
        });
        this.callParent(arguments);
    }
})

