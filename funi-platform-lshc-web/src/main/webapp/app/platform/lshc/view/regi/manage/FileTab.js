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
                if("1" == store.getAt(i).data.isNew){
                  store.getAt(i).data.id = "";
                }
                array.push(store.getAt(i).data);
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
                {type:"string",name:"url"},
                {type:"string",name:"isNew"}

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
            height:400,
            selModel: {selType: 'checkboxmodel', mode: "SINGLE"},
            viewConfig: {
                enableTextSelection: true
            },
            //selType: "checkboxmodel",
		    columns:[
			   {text:"id",dataIndex:"id",hidden:true},
               {text:"isNew",dataIndex:"isNew",hidden:true},
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
                           // if(!me.parentContainer.isHiddenBtn(8)){
                           //     oper += '<a href="javascript:void(0)" style="color: #1f18ff;margin-right:14px;margin-left:2px" class="del-contr-info">删除</a>';
                           // }
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
                                    var rData = me.getStore().getAt(rowIndex);
                                    me.getStore().remove(rData);
                                    //me.store.commitChanges();
                                    //me.getView().refresh();
                                    // var result = Ext.appContext.invokeService(Funi.core.Context.path("ghouse","/template/delContrTemp"),{id: record.data.id});
                                    // if(result == null || result == '') {
                                    //     Ext.Msg.alert('提示', "删除失败，请联系管理员！");
                                    //     return false;
                                    // } else {
                                    // }
                                    // store.loadPage(1);
                                }
                            });
                        }
                    }
                }
        });
		 me.dockedItems = [{
            xtype: 'toolbar',
          /*  style: {
                background: '#f3f7f7'
            },*/
            dock: 'top',
            items: [{
                        xtype: 'toolbar', columnWidth: 1, scope: me,
                        width:82,
                        access:"AUTH_LSHC_FILELIST_UPLOAD",
                        items: [
                              {
                                xtype: 'form',
                                layout: 'hbox',
                                itemId: 'FileUploadUtilsItemsId',
                                items: [{
                                    xtype: 'fileuploadfield',
                                    itemId: 'uploadFileItemId',
                                    margin: '0 0 0 5',
                                    buttonOnly: true,
                                    labelSeparator: '',
                                    name: 'uploadFile',
                                    buttonText: "文件上传",

                                    listeners: {
                                        change: function () {

                                            var conts = me.query("fieldcontainer");

                                            var form = me.queryById("FileUploadUtilsItemsId");
                                            var fileName = me.queryById('uploadFileItemId').getValue().split('.');
                                            var saveFileName = fileName[0].split('\\')[fileName[0].split('\\').length - 1] + '.' + fileName[fileName.length - 1];

                                            linkFileType = fileName[fileName.length - 1];
                                            if (linkFileType != "jpg" && linkFileType != "png"
                                                && linkFileType != "doc" && linkFileType != "xls"
                                                && linkFileType != "jpeg" && linkFileType != "pdf" && linkFileType != "zip"
                                                && linkFileType != "docx" && linkFileType != "xlsx") {
                                                Ext.Msg.alert("系统提示", "上传文件格式仅支持（doc/xls/pdf/jpg/jpeg/png/docx/xlsx/zip）!");
                                                return false;
                                            }
                                            if (form.isValid()) {
                                                form.submit({
                                                    method: 'POST',
                                                    url: Funi.core.Context.path("lshc", "/FileController/createFile"),//后台处理的页面
                                                    waitMsg: '文件上传中，请稍等...',
                                                    success: function (form, action) {
                                                    },
                                                    failure: function (form, action) {
                                                        console.info(action)
                                                        //上传成功，刷新界面
                                                        if (null != action && null != action.result) {
                                                            var fileRecord = action.result;
                                                            var linkFileUrl = fileRecord.linkFileUrl;
                                                            //linkFileName,linkType,fileType,fileUrl

                                                            var rData = {
                                                                "isNew":"1",
                                                                "fileName":saveFileName,
                                                                "fileSize":12,
                                                                "submitDate":fileRecord.uploadDate,
                                                                "userName":fileRecord.userName,
                                                                "unitName":fileRecord.unitName,
                                                                "url":fileRecord.linkFileUrl
                                                            }
                                                            me.store.add(rData);
                                                        }
                                                    }
                                                });
                                            }
                                        }
                                    }
                                }
                                ]
                            }

                        ]
                    },
                        {
                            xtype: 'button', text: '删除文件', scope: me, glyph: 'xf014@FontAwesome',

                            handler: function () {

                                var selectObjArray = me.getSelectionModel().getSelection();
                                if(selectObjArray.length!=1){
                                    Ext.MessageBox.alert("温馨提示", "请选择文件!");
                                    return;
                                }

                                Ext.Msg.confirm('提示',  '文件删除，保存后将不能查看文件，确定要删除吗？', function (btn) {
                                    if (btn === 'yes') {

                                        for(var i=0;i< selectObjArray.length;i++){
                                            var record = selectObjArray[i];
                                            me.getStore().remove(record);
                                        }

                                    }
                                })

                            }
                        }
                    ]
        }];

        this.callParent(arguments);
    }
})

