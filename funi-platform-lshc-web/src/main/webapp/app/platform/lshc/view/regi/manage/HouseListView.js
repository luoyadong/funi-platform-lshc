/**
 * 房屋普查管理
 * @author luoyadong@funi365.com
 */

Ext.define('app.platform.lshc.view.regi.manage.HouseListView', {
    extend: 'Ext.Panel',
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
		bizId:null

    },
    layout:"fit",
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
            url:app.platform.lshc.view.base.RequestUtils.url("/manage/getRegiInfoVoList"),
            fields:[
			    {type:"string",name:"id"},
                {type:"string",name:"houseId"},
                {type:"string",name:"unitNo"},
                {type:"string",name:"layer"},
                {type:"string",name:"roomNo"},
                {type:"string",name:"fileCount"},
                {type:"string",name:"jobAcceptId"},
                {type:"string",name:"nodeName"},
                {type:"string",name:"auditStatus"}
            ],
            pageSize:15
        });
        Ext.apply(me, {
            items:[{
                tbar: {
                    layout: 'column', scope: me,
                    items: [
                        {
                            xtype: 'toolbar', columnWidth: 1, scope: me,
                            items: [
                                '->',
                                {
                                    xtype: 'button', text: '新建普查', scope: me, glyph: 0xf0fe,
                                    handler: function () {
                                        Ext.create("app.platform.lshc.view.regi.manage.NewInfoWinView",{
                                            config:{parentContainer:me.config.parentContainer}
                                        }).show();
                                        //Ext.Msg.alert('提示', '已新增！');
                                    }
                                },
                                {
                                    xtype: 'button', text: '批量导入', scope: me, glyph: 'xf234@FontAwesome',
                                    handler: function () {
                                        var url = "/build/importRegiInfo";
                                        var store = null;//需要刷新的store
                                        ExcelUtils.importExcel(url, store);
                                        //Ext.Msg.alert('提示', '已新增！');

                                    }
                                },
                                {
                                    xtype: 'button', text: '导出', scope: me, glyph: 'xf234@FontAwesome',
                                    handler: function () {

                                        //导出数据
                                        var param = {
                                            ids: [123, 2121, 2121, 2121]
                                        };
                                        var url = "/build/exportRegiInfoVoList";
                                        ExcelUtils.exportExcel(param, url);
                                        //Ext.Msg.alert('提示', '已新增！');

                                    }
                                },
                                {
                                    xtype: 'button', text: '审批', scope: me, glyph: 0xf044,
                                    handler: function () {
                                        var selectObjArray = me.down("gridpanel").getSelectionModel().getSelection();
                                        if(selectObjArray.length < 1){
                                            Ext.MessageBox.alert("温馨提示", "请选房屋数据!");
                                            return;
                                        }
                                        var ids = new Array();
                                        //状态检测
                                        for(var i=0;i< selectObjArray.length;i++){
                                            var record = selectObjArray[i].data;
                                            if(record.auditStatus == null){
                                                Ext.MessageBox.alert("温馨提示", "无状态数据!房屋编号:"+record.houseId);
                                                return;
                                            }
                                            for(var j=i+1;j< selectObjArray.lenght;j++){
                                                var record2 = selectObjArray[j].data;
                                                if(record.auditStatus != record2.auditStatus){//状态相等
                                                    Ext.MessageBox.alert("温馨提示", "请选相同状态的数据!房屋编号:"+record.houseId);
                                                    return;
                                                }
                                            }
                                            ids.push(record.id);
                                        }
                                        var oneRecord = selectObjArray[0].data;
                                        var serviceNum = oneRecord.id;
                                        var nodeName = oneRecord.nodeName;
                                        var approveWin = Ext.create("app.platform.lshc.view.regi.manage.ApproveWinView");
                                        approveWin.config.serviceNum = serviceNum;
                                        approveWin.config.nodeName = nodeName;
                                        approveWin.config.ids = ids;
                                        approveWin.config.parentContainer = me.config.parentContainer;
                                        approveWin.initCons();
                                        approveWin.show();
                                    }
                                },

                                {
                                    xtype: 'button', text: '删除', scope: me, glyph: 'xf014@FontAwesome',
                                    handler: function () {

                                        //Ext.Msg.alert('提示', '已新增！');

                                    }
                                }
                            ]
                        }
                    ]
                },
                height:405,
                xtype: 'gridpanel',
                itemId: 'houseGridpanel',
                border: true,
                store: store,
                columnLines: true,
                selModel: {selType: 'checkboxmodel', mode: "SINGLE"},
                dockedItems: [{
                    xtype: 'pagingtoolbar',
                    store: store,
                    dock: 'bottom',
                    displayInfo: true
                }],
                viewConfig: {
                    enableTextSelection: true
                },
                columns: [
                    {text: '业务ID', hidden: true, dataIndex: 'id', align: 'center'},
                    {text: '审批受理ID', hidden: true, dataIndex: 'jobAcceptId', align: 'center'},
                    {text: '节点名称', hidden: true, dataIndex: 'nodeName', align: 'center'},
                    {text: '房屋编号', dataIndex: 'houseId', width: '25%', align: 'center'},
                    {text: '单元', dataIndex: 'unitNo', width: '10%', align: 'center'},
                    {text: '楼层', dataIndex: 'layer', width: '10%', align: 'center'},
                    {text: '房号', dataIndex: 'roomNo', BIZ_NO: '12%', align: 'center'},
                    {text: '状态', dataIndex: 'auditStatus', width: '15%', align: 'center'},
                    {text: '图片', dataIndex: 'fileCount', width: '15%', align: 'center'}
                ],
                listeners: {
                    cellclick: function (table, td, cellIndex, record, tr, rowIndex, e, eOpts) {
                        var id = record.data.id;
                        me.config.parentContainer.initHouseDetail(id);
                    },
                    itemdblclick: function (dataview, record, item, index, e) {

                    }
                }
            }
            ]


        });
        me.callParent(arguments);

    }
});

