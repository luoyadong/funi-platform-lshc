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
    isHiddenBtn:function(btnType){//1:新建 2 批量导入 3导出 4审批 5删除 6编辑 7 批量提交 8 文件删除
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
    exportExcel:function(){
        var me = this
        var selectObjArray = me.getSelectionModel().getSelection();
        if(selectObjArray.length!=1){
            Ext.MessageBox.alert("温馨提示", "请选择房屋!");
            return;
        }
        var ids = new Array();
        //组装ids
        for(var i=0;i< selectObjArray.length;i++){
            var record = selectObjArray[i].data;
            ids.push(record.id);
        }
        var url = "/regiInfo/exportRegiInfoVoList";
        ExcelUtils.exportExcel({"ids":ids}, url);
    },
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
                                    hidden:me.isHiddenBtn(1),
                                    handler: function () {
                                        Ext.create("app.platform.lshc.view.regi.manage.NewInfoWinView",{
                                            config:{parentContainer:me.config.parentContainer}
                                        }).show();
                                    }
                                },
                                {
                                    xtype: 'button', text: '批量导入', scope: me, glyph: 'xf234@FontAwesome',
                                    hidden:me.isHiddenBtn(2),
                                    handler: function () {
                                        var url = "/manage/checkRegiInfoList";///manage/importRegiInfoList
                                        var store = me.store;//需要刷新的store
                                        ExcelUtils.importExcel(url, store);

                                    }
                                },
                                {
                                    xtype: 'button', text: '导出', scope: me, glyph: 'xf234@FontAwesome',
                                    handler: function () {
                                        //导出数据
                                        me.exportExcel();

                                    }
                                },
                                {
                                    xtype: 'button', text: '审批', scope: me, glyph: 0xf044,
                                    hidden:me.isHiddenBtn(4),
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
                                    hidden:me.isHiddenBtn(5),
                                    handler: function () {

                                        var selectObjArray = me.down("gridpanel").getSelectionModel().getSelection();
                                        if(selectObjArray.length!=1){
                                            Ext.MessageBox.alert("温馨提示", "请选择房屋!");
                                            return;
                                        }

                                        var ids = new Array();
                                        //组装ids
                                        for(var i=0;i< selectObjArray.length;i++){
                                            var record = selectObjArray[i].data;
                                            ids.push(record.id);
                                        }

                                        Ext.Msg.confirm('提示',  '确定要删除吗？', function (btn) {
                                            if (btn === 'yes') {
                                                //执行删除
                                                app.platform.lshc.view.base.RequestUtils.post_json(ids,"/manage/batchDeleteRegiInfo",false,false);
                                                //刷新列表
                                                me.store.proxy.extraParams = me.getParams();
                                                me.store.loadPage(1);
                                            }
                                        })

                                    }
                                },
                                {
                                    xtype: 'button', text: '批量提交', scope: me, glyph: 'xf014@FontAwesome',
                                    hidden:me.isHiddenBtn(7),
                                    handler: function () {

                                        var selectObjArray = me.down("gridpanel").getSelectionModel().getSelection();
                                        if(selectObjArray.length!=1){
                                            Ext.MessageBox.alert("温馨提示", "请选择房屋!");
                                            return;
                                        }

                                        var ids = new Array();
                                        //组装ids
                                        for(var i=0;i< selectObjArray.length;i++){
                                            var record = selectObjArray[i].data;
                                            ids.push(record.id);
                                        }

                                        Ext.Msg.confirm('提示',  '提交后将发起审批流程，确定要发起批量提交吗？', function (btn) {
                                            if (btn === 'yes') {
                                                //执行删除
                                                app.platform.lshc.view.base.RequestUtils.post_json(ids,"/manage/batchDeleteRegiInfo",false,false);
                                                //刷新列表
                                                me.store.proxy.extraParams = me.getParams();
                                                me.store.loadPage(1);
                                            }
                                        })

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

