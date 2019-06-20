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
	getData:function(){
        //var grid = this.queryById("fileGridpanelItemId");
        var store = this.store;
        var array = new Array();
        var count = store.getCount();
        if(count >0){
            for(var i=0;i<count;i++){
                // if(null == store.getAt(i).data.houseId || "" == store.getAt(i).data.houseId){
                array.push(store.getAt(i).data);
                // }
            }
        }
        return array;
        //throw {message:"请添加产权人信息"};
    },
    initComponent:function(){
        var me = this;
		var store = Ext.create("Funi.data.ListStore",{
            //url:app.platform.ghouse.view.base.RequestUtils.url("/contract/list"),
            fields:[
			    {type:"string",name:"id"},
                {type:"string",name:"fileName"},
                {type:"string",name:"fileSize"},
                {type:"string",name:"submitDate"},
                {type:"string",name:"userName"},
                {type:"string",name:"unitName"},
                {type:"string",name:"url"}
            ],
			load:false,
            pageSize:15
        });
        Ext.applyIf(me,{
			xtype: 'gridpanel',
            itemId: 'fileGridpanelItemId',
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
			   {text:"图片名称",dataIndex:"fileName",flex: 1},
			   {text:"附件大小",dataIndex:"fileSize",flex: 1},
			   {text:"上传时间",dataIndex:"submitDate",flex: 1},
			   {text:"上传账号",dataIndex:"userName",flex: 1},
			   {text:"所属单位",dataIndex:"unitName",flex: 1},
			   {text: '操作', dataIndex: 'url',flex: 1, align: 'center'
					, renderer : function(data, metadata, record, rowIndex, columnIndex,store) {
							var oper=null;
                            var tempUrl = record.data.url;
                            var oper;
                            if(tempUrl != null && tempUrl !="null"&& tempUrl !="NULL"){
                                oper = '<a href="'+tempUrl+'"  target="_blank" style="color: #1f18ff;margin-right:14px;margin-left:2px" class="preview-contr-url">预览</a>';
                            }
                           
                            oper += '<a href="javascript:void(0)" style="color: #1f18ff;margin-right:14px;margin-left:2px" class="del-contr-info">删除</a>';
                            return oper;
						}
			   }
		   ],
		listeners: {
                    cellClick: function (obj, td, cellIndex, record, tr, rowIndex, e) {
                        var className = e.getTarget().className;
                        if (className == 'del-contr-info') {
                            //删除票据信息
                            Ext.Msg.confirm('确认', '你确认要删除这条记录吗？', function (btn) {
                                if (btn === 'yes') {
                                    var result = Ext.appContext.invokeService(Funi.core.Context.path("ghouse","/template/delContrTemp"),{id: record.data.id});
                                    if(result == null || result == '') {
                                        Ext.Msg.alert('提示', "删除失败，请联系管理员！");
                                        return false;
                                    } else {
                                    }
                                    store.loadPage(1);
                                }
                            });
                        }
                    }
                }
        });
		 me.dockedItems = [{
            xtype: 'toolbar',
            style: {
                background: '#f3f7f7'
            },
            dock: 'top',
            items: [{
                        xtype: 'toolbar', columnWidth: 1, scope: me,
                        items: [
							 {
                                    xtype: 'fileuploadfield',
                                    itemId:'uploadFileItemId',
                                    margin: '5 0 0 5',
                                    buttonOnly: true,
                                    labelSeparator: '',
                                    name: 'uploadFile',
                                    buttonText: "文件上传",

                                    listeners: {
                                        change: function () {
                                           
										   alert("上传成功！")
                                        }
                                    }
                                }
                        ]
                    }]
        }];

        this.callParent(arguments);
    }
})

