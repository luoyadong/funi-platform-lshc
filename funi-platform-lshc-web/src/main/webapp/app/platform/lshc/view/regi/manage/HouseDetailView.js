/**
 *
 *@author 3
 */
Ext.define("app.platform.lshc.view.regi.manage.HouseDetailView",{
    extend: 'Ext.Panel',
	xtype: 'lshc-view-regi-HouseDetailView',
	itemId:"lshc-view-regi-HouseDetailView-itemId",
    layout:"fit",
    requires:[
		"app.platform.lshc.view.regi.manage.HouseTab",
		"app.platform.lshc.view.regi.manage.FileTab",
		"app.platform.lshc.view.regi.manage.ApproveTab",
		"app.platform.lshc.view.regi.manage.RightEntWin",
		'app.platform.lshc.view.regi.manage.NewInfoWinView'
    ],
	config: {
        //主容器
        parentContainer: null,
		//普查主表ID
		bizId:null
    },
    constructor: function (config) {
        var me = this;
        config = config || {};
        Ext.applyIf(config, me.config);
        this.callParent(arguments);
    },
	fillForm:function(formData){
        this.config.houseStatus = formData.regiInfo.houseStatus;
        //房屋信息 regiInfo entInfoList fileList
        var houseView = this.queryById("lshc-regi-manage-houseInfo-form-itemId");
        houseView.getForm().setValues(formData.regiInfo);
		//房屋信息-人员列表，lshc-view-HousePerson-itemId
        var entView = this.queryById("lshc-regi-housedetail-tabpanel-itemId").down("xgridpanel");
        entView.store.loadData(formData.entInfoList,false);
        //entView.getSelectionModel().selectAll(true);

		//文件列表
        var approveView = this.queryById("lshc-regi-file-tabpanel-itemId");
        approveView.store.loadData(formData.fileList,false);
        //approveView.getSelectionModel().selectAll(true);

        //审批过程
        var obj = new Object();
        obj["serviceNum"] = this.config.bizId;
        var approveView = this.queryById("lshc-regi-approve-tabpanel-itemId");
        approveView.store.proxy.extraParams = obj;
        approveView.store.loadPage(1);

        //承租人
       // var lesseeFormView = this.queryById("ghouse-rent-contract-lessee-form-itemId");
       // lesseeFormView.getForm().setValues(formData.lessee);
      //  lesseeFormView.initHide(formData);
     //   var lesseeUpload = lesseeFormView.queryById("FileUploadUtilsId");
     //   lesseeUpload.config.bizId=formData.contract.lesseeId;
      //  lesseeUpload.findFileList();
       // lesseeFormView.setDisabled(true);
        //住户
      //  var residentView = this.queryById("ghouse-rent-contract-resident");
      //  residentView.store.loadData(formData.residentList,false);
    },
    isHiddenBtn:function(btnType){//1:新建 2 批量导入 3导出 4审批 5删除 6编辑 7 批量提交
        var me = this;
        //access:"AUTH_LSHC_HOUSELIST_ADD"
        if(me.config.srcType == 1){//综合
            if(btnType == 3){
                return false
            }else{
                return true;
            }
        }else  if(me.config.srcType == 2){//待办
            if(btnType == 3 || btnType == 4){
                return false
            }else{
                return true;
            }
        }else if(me.config.srcType == 3){//已办,//1:新建 2 批量导入 3导出 4审批 5删除 6编辑 7 批量提交
            if(btnType == 3){
                return false
            }else{
                return true;
            }
        }else{//管理
            if(btnType == 4){
                return true
            }else{
                return false;
            }
        }
    },
    initComponent: function () {
        var me = this;
        Ext.apply(me, {
            tbar: {
				layout: 'column', scope: me,
                items: [
                {
                    xtype: 'toolbar', scope: me,
                    items:[
                        {
                            xtype:'button',
                            text:'编辑',
                            glyph: 0xf044,
                            handler:function() {
								if(me.config.bizId == null){
                                    Ext.MessageBox.alert("温馨提示","请选择左侧的房屋数据！");
									return;
								}
								var id = me.config.bizId ;
                                var hStatus = me.config.houseStatus;
                                if(hStatus != '新建' && hStatus != '初审不通过'){
                                    Ext.MessageBox.alert("温馨提示","审批中的数据不能被编辑！");
                                    return;
                                }
								var editWin = Ext.create("app.platform.lshc.view.regi.manage.NewInfoWinView",
                                    {config:{parentContainer:me.config.parentContainer}});
							    editWin.show();

								Ext.Ajax.request({
                                       url:app.platform.lshc.view.base.RequestUtils.url("/regiInfo/getRegiInfoDetail"),
                                       method:"post",
                                       async:false,
									   params:{hcId:id},
                                       success:function(response)
                                       {
                                           var data = JSON.parse(response.responseText);
										   console.log("-----get regi edit detail:")
										   console.log(data)
											
										    var editView = editWin.queryById("lshc-view-regi-HouseEditView-itemId");
                                            editView.config.bizId=id;
                                            editView.fillForm(data);
                                       },
                                       failure:function(){
                                           Ext.MessageBox.alert("温馨提示", "服务器异常,请稍后重试!");
                                       }
                                   });

                            }
                        },
                        {
                            xtype:'button',text:"打印",glyph: 'xf02f@FontAwesome',itemId:"commit",
                            handler:function(){
                                        Ext.create("app.platform.lshc.view.regi.manage.RightEntWin", {
                                            
                                        }).show();
                            }
                        }
                    ]
                }

			]
            },
			items:[
        {
            xtype:'tabpanel',
			itemId:'lshc-regi-housedetail-parent-tabpanel-itemId',
            items:[
               {
                    itemId:'lshc-regi-housedetail-tabpanel-itemId',
                    glyph: 'xf0c0@FontAwesome',
                    xtype:"lshc-regi-manage-houseInfo",
                    title:'普查信息',
                    overflowY:'auto',
                    width:"100%",
                    height:275,
                    disabled:false
                },{
                    title:'附件',
                    glyph: 'xf0e0@FontAwesome',
                    xtype:"lshc-regi-manage-file",
                    disabled:false,
                    overflowY:'auto',
                    width:"100%",
                    itemId:'lshc-regi-file-tabpanel-itemId',
                    parentContainer:me
                },{
                    title:'处理过程',
                    glyph: 'xf022@FontAwesome',
					itemId:'lshc-regi-approve-tabpanel-itemId',
                    xtype:"lshc-regi-manage-approve",
                    overflowY:'auto',
                    disabled:false,
                    width:"100%"
                }
            ]
        }
    ]

        });
        me.callParent(arguments);
    }
})
