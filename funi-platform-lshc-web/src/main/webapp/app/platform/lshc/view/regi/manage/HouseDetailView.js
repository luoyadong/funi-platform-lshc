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
        //房屋信息
        var houseView = this.queryById("lshc-regi-manage-houseInfo-itemId").down("form");

		//房屋信息-人员列表
        var entView = this.queryById("lshc-view-HousePerson-itemId").down("xgridpanel");
        entView.store.loadData(formData.houseDtoList,false);
        //entView.getSelectionModel().selectAll(true);

		//文件列表
        var approveView = this.queryById("lshc-regi-manage-file-itemId").down("gridpanel");
        approveView.store.loadData(formData.houseDtoList,false);
        //approveView.getSelectionModel().selectAll(true);

        //审批过程
        var approveView = this.queryById("lshc-regi-manage-approve-itemId").down("gridpanel");
        approveView.store.loadData(formData.houseDtoList,false);
        //approveView.getSelectionModel().selectAll(true);

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
                            hidden:false,
                            glyph: 0xf044,
                            handler:function() {
								if(me.config.bizId == null){
									alert("请选择左侧的房屋数据！");
									return;
								}
								var id = me.config.bizId ;
								var editWin = Ext.create("app.platform.lshc.view.regi.manage.NewInfoWinView");
							    editWin.show();

								Ext.Ajax.request({
                                       url:app.platform.lshc.view.base.RequestUtils.url("/build/getRegiInfoDetail"),
                                       method:"post",
                                       async:false,
									   params:{houseId:id},
                                       success:function(response)
                                       {
                                           var data = JSON.parse(response.responseText);
										   console.log("-----get regi edit detail:")
										   console.log(data)
											
										   var editView = editWin.queryById("lshc-regi-houseedit-parent-itemId");
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
                    itemId:'tab1',
                    glyph: 'xf0c0@FontAwesome',
                    xtype:"lshc-regi-manage-houseInfo",
                    title:'普查信息',
                    overflowY:'auto',
                    width:"100%",
					height:500,
                    disabled:false
                },{
                    title:'图片',
                    glyph: 'xf0e0@FontAwesome',
                    xtype:"lshc-regi-manage-file",
                    disabled:false,
                    overflowY:'auto',
                    width:"100%",
                    itemId:'tab2'
                },{
                    title:'处理过程',
                    glyph: 'xf022@FontAwesome',
					itemId:'tab3',
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
