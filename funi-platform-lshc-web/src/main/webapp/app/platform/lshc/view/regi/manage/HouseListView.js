/**
 * 房屋普查管理
 * @author luoyadong@funi365.com
 */

Ext.define('app.platform.lshc.view.regi.manage.HouseListView', {
    extend: 'Ext.grid.Panel',
	itemId:"lshc-view-regi-HouseListView-itemId",
    xtype: 'lshc-view-regi-HouseListView',
    requires: [
        //新增项目分期信息Tab视图
        'app.platform.lshc.view.regi.manage.ApproveWinView',
		'app.platform.lshc.view.regi.manage.NewInfoWinView',
		'app.platform.lshc.view.base.ExcelUtils',
		'app.platform.lshc.view.base.RequestUtils'
    ],
    config: {
        //主容器
        parentContainer: null,
		//普查主表ID
		bizId:null,
		
    },
    constructor: function (config) {
        var me = this;
        config = config || {};
        Ext.applyIf(config, me.config);
        this.callParent(arguments);
    },
    title: null,

    initComponent: function () {
        var me = this;
		
        var store = Ext.create("Funi.data.ListStore",{
            //url:app.platform.ghouse.view.base.RequestUtils.url("/contract/list"),
            fields:[
			    {type:"string",name:"id"},
                {type:"string",name:"houseId"},
                {type:"string",name:"unitNo"},
                {type:"string",name:"layer"},
                {type:"string",name:"roomNo"},
                {type:"string",name:"regiStatus"},
                {type:"string",name:"fileCount"}
            ],
			data:[
			{"id":"eqweqwewq","houseId":1,"unitNo":"1","layer":"2","roomNo":"2018","regiStatus":"初审通过","fileCount":3},
			{"id":"eqweqwewq2","houseId":2,"unitNo":"1","layer":"2","roomNo":"2018","regiStatus":"初审通过","fileCount":3}	
			],
			load:false,
            pageSize:15
        });
        Ext.apply(me, {

            tbar: {
                layout: 'column', scope: me,height:30,
                items: [
                    {
                        xtype: 'toolbar', columnWidth: 1, scope: me,
                        items: [
                            '->',
							 {
                                xtype: 'button', text: '新建普查', scope: me,glyph: 0xf0fe,
                                handler: function () {
                                    Ext.create("app.platform.lshc.view.regi.manage.NewInfoWinView").show();
                                	//Ext.Msg.alert('提示', '已新增！');

                                }
                            },
								 {
                                xtype: 'button', text: '批量导入', scope: me,glyph: 'xf234@FontAwesome',
                                handler: function () {
									var url = "/build/importRegiInfo";
									var store = null;//需要刷新的store
									ExcelUtils.importExcel(url,store);
                                	//Ext.Msg.alert('提示', '已新增！');

                                }
                            },
							 {
                                xtype: 'button', text: '导出', scope: me,glyph: 'xf234@FontAwesome',
                                handler: function () {

									//导出数据
									var param = {
										ids:[123,2121,2121,2121]
									};
									var url = "/build/exportRegiInfoVoList";
									ExcelUtils.exportExcel(param,url);
                                	//Ext.Msg.alert('提示', '已新增！');

                                }
                            },
								 {
                                xtype: 'button', text: '审批', scope: me,glyph: 0xf044,
                                handler: function () {
								Ext.create("app.platform.lshc.view.regi.manage.ApproveWinView").show();
								}
								 },
									
                            {
                                xtype: 'button', text: '删除', scope: me,glyph: 'xf014@FontAwesome',
                                handler: function () {

                                	//Ext.Msg.alert('提示', '已新增！');

                                }
                            }
                        ]
                    }
                ]
            },
            xtype: 'gridpanel',
            itemId: 'houseGridpanel',
            border: true,
            store: store,
            columnLines: true,
			selModel: {selType: 'checkboxmodel',mode:"SINGLE"},
		   dockedItems :[{
			   xtype: 'pagingtoolbar',
			   store: store,
			   dock: 'bottom',
			   displayInfo: true
		   }],
            viewConfig: {
                enableTextSelection: true
            },
            columns: [
                {text: '业务ID',hidden:true, dataIndex: 'id', align: 'center'},
                {text: '房屋编号', dataIndex: 'houseId', width: '25%', align: 'center'},
                {text: '单元', dataIndex: 'unitNo', width: '10%', align: 'center'},
                {text: '楼层', dataIndex: 'layer', width: '10%', align: 'center'},
                {text: '房号', dataIndex: 'roomNo', BIZ_NO: '12%', align: 'center'},
                {text: '状态', dataIndex: 'regiStatus', width: '15%', align: 'center'},
                {text: '图片', dataIndex: 'fileCount', width: '15%', align: 'center'}
            ],
            listeners: {
                cellclick: function (table, td, cellIndex, record, tr, rowIndex, e, eOpts) {
                                  var id = record.data.id;
                                  //加载普查详情tabs
                                  Ext.Ajax.request({
                                       url:app.platform.lshc.view.base.RequestUtils.url("/build/getRegiInfoDetail"),
                                       method:"post",
                                       async:false,
									   params:{houseId:id},
                                       success:function(response)
                                       {
                                           var data = JSON.parse(response.responseText);
										   console.log("-----get regi detail:")
										   console.log(data)
											
										   var houseDetalPanel = me.config.parentContainer.queryById("lshc-view-regi-HouseDetailView-itemId");
                                            houseDetalPanel.config.bizId=id;
                                           houseDetalPanel.fillForm(data);
                                           //houseDetalPanel.readOnly(true);
                                       },
                                       failure:function(){
                                           Ext.MessageBox.alert("温馨提示", "服务器异常,请稍后重试!");
                                       }
                                   });

                                  //更新右侧顶部状态
								  me.config.parentContainer.initStatus("初审通过","①社区","->②街道办 ","->③区政府->④市住建局");
                                    
                },
                itemdblclick: function (dataview, record, item, index, e) {
                   
                }
            }

        });
        me.callParent(arguments);

    }
});

