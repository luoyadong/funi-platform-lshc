/**
 * 房屋普查管理
 * @author luoyadong@funi365.com
 */

Ext.define('app.platform.lshc.view.regi.manage.RegiMainView', {
    extend: 'Ext.grid.Panel',
    xtype: 'lshc-view-regi-RegiMainView',
    requires: [
        //新增项目分期信息Tab视图
        'app.platform.lshc.view.base.RequestUtils',
        'app.platform.lshc.view.regi.manage.ApproveWinView',
        'app.platform.lshc.view.regi.manage.RegiDetailView',
        'app.platform.lshc.view.regi.manage.ApproveTab',
        'app.platform.lshc.view.regi.manage.HouseListView',
        'app.platform.lshc.view.regi.manage.HouseDetailView',//房屋详情
        'app.platform.lshc.view.base.ExcelUtils'
    ],
    config: {
        //主容器
        parentContainer: null
    },
    constructor: function (config) {
        var me = this;
        config = config || {};
        Ext.applyIf(config, me.config);
        this.callParent(arguments);
    },
    title: null,
    initHouseList:function(){
        var me = this;
        var gridObjStore = me.store;
        gridObjStore.proxy.extraParams = me.getParams();
        gridObjStore.loadPage(1);
    },
    getParams:function(){
        var formElements = Ext.ComponentQuery.query("textfield",this);
        var obj = new Object();
        for(var i=0;i<formElements.length;i++){
            obj[formElements[i].name] =formElements[i].value;
        }
        return obj;
    },
	resetParams:function(){
        var formElements = Ext.ComponentQuery.query("textfield",this);
        var obj = new Object();
        for(var i=0;i<formElements.length;i++){
			this.queryById(formElements[i].itemId).setValue(null)
        }
        return obj;
    },
    exportExcel:function(){
        var me = this
        var selectObjArray = me.getSelectionModel().getSelection();
        if(selectObjArray.length < 1){
            Ext.MessageBox.alert("温馨提示", "请选择楼栋!");
            return;
        }
        var ids = new Array();
        //组装ids
        for(var i=0;i< selectObjArray.length;i++){
            var record = selectObjArray[i].data;
            ids.push(record.id);
        }
        var url = "/manage/exportBuildInfoVoList";
        ExcelUtils.exportExcel({"ids":ids}, url);
        Ext.Msg.alert('提示', '数据导出结束！');
    },
    initHouseDetail:function(hcId){
        this.initHouseList();
    },
    setTask: function (show, message) {
        var myTask = Ext.lshc_task;
        if (!myTask) {
            if (!message) {
                message = "请等待...";
            }
            var myTask = new Ext.LoadMask({
                msg: message,
                target: Ext.mainFrame
            });
            Ext.lshc_task = myTask;
        }
        if (message) {
            myTask.msg = message;
        }
        if (show) {
            myTask.show();
        } else {
            myTask.hide();
        }
        return myTask;
    },
    initComponent: function () {
        var me = this;
        var mStore = Ext.create('Ext.data.Store', {
            fields: ['name','value'],
            autoLoad: true,
            data: [
                {'name': '全部', value: ''},
                {'name': '城关区',value:0},
                {'name': '市辖区',value:1},
            ]
        });
        var mStore2 = Ext.create('Ext.data.Store', {
            fields: ['name','value'],
            autoLoad: true,
            data: [
                {'name': '全部', value: ''},
                {'name': '纳金乡',value:0},
                {'name': '热巴香',value:1},
            ]
        });
		var store = Ext.create("Funi.data.ListStore",{
            url:app.platform.lshc.view.base.RequestUtils.url("/manage/getBuildInfoList"),
            fields:[
			    {type:"string",name:"id"},
                {type:"string",name:"serialNo"},
                {type:"string",name:"region"},
                {type:"string",name:"street"},
                {type:"string",name:"projectName"},
                {type:"string",name:"mapCode"},
                {type:"string",name:"address"},
                {type:"string",name:"houseCount"}
            ],
            pageSize:15
        });
        Ext.apply(me, {

                    tbar: {
                        layout: 'column', scope: me,
                        items: [
                            {
                                xtype: 'toolbar', columnWidth: 1, scope: me,
                                items: [
                                   // '->',
                                    {xtype:'xcombobox',
                                        itemId:'regionItemId',
                                        fieldLabel:'区(县)',
                                        labelWidth:56,
                                        emptyText:'全部',
                                        width:156,
                                        name:"region",
                                        editable:false,
                                        triggerAction:'all',
                                        dataSourceUrl:app.platform.lshc.view.base.RequestUtils.url('/basic/getAllRegionList'),
                                        listeners:{
                                            change:function(){
                                                var cabinet = me.queryById("streetItemId");
                                                cabinet.store.proxy.extraParams = {regionId:arguments[1]};
                                                cabinet.store.load();
                                                me.queryById("streetItemId").clearValue();
                                            }
                                        }
                                    },
                                    {xtype:'xcombobox',
                                        itemId:'streetItemId',
                                        fieldLabel:'街道(乡镇)',
                                        labelWidth: 75,
                                        width: 170,
                                        emptyText:'全部',
                                        name:"street",
                                        editable:false,
                                        triggerAction:'all',
                                        dataSourceUrl:app.platform.lshc.view.base.RequestUtils.url('/basic/getAllBlockListByRegionId')
                                    },
                                    {
                                        xtype:"textfield",
                                        labelAlign:'right',
                                        itemId:"projectItemId",
                                        name:"projectName",
                                        labelWidth:60,
                                        fieldLabel:'小区名称',
                                        width:170,
                                        emptyText:"小区名称"
                                    },
                                    {
                                        xtype:"textfield",
                                        labelAlign:'right',
                                        itemId:"buildtemId",
                                        name:"mapCode",
                                        labelWidth:90,
                                        fieldLabel:'楼栋地图编号',
                                        width:190,
                                        emptyText:"楼栋地图编号"
                                    },
                                    {
                                        xtype:"textfield",
                                        labelAlign:'right',
                                        itemId:"addrItemId",
                                        name:"address",
                                        labelWidth:60,
                                        fieldLabel:'实际地址',
                                        width:190,
                                        emptyText:"实际地址"
                                    }
                                    ,
                                    {xtype: 'button',text:"查询",glyph:0xf002,handler:function(){
                                        console.log(me.getParams())
                                        store.proxy.extraParams = me.getParams();//获取列表store
                                        store.loadPage(1);
                                    }},
                                    {
                                        xtype: 'button', text: '重置', scope: me,
                                        glyph:'xf0e2@FontAwesome',
                                        handler: function () {
                                            me.resetParams();
                                        }
                                    }
                                ]
                            },
                            {
                                xtype: 'toolbar', columnWidth: 1,scope: me, itemId: 'search',
                                items: [
                                    {
                                        xtype: 'button', text: '新建普查', scope: me, glyph: 0xf0fe,
                                        handler: function () {
                                            Ext.create("app.platform.lshc.view.regi.manage.NewInfoWinView",{
                                                config:{parentContainer:me}
                                            }).show();
                                        }
                                    },
                                    {
                                        xtype: 'button', text: '批量导入', scope: me, glyph: 'xf234@FontAwesome',
                                        handler: function () {
                                            var url = "/manage/checkReturnRegiInfoList";///manage/importRegiInfoList
                                            var store = me.store;//需要刷新的store
                                            ExcelUtils.importExcel(url, store);

                                        }
                                    },
                                    {
                                        xtype: 'button', text: '删除', scope: me,
                                        glyph: 'xf014@FontAwesome',
                                        handler: function () {
                                            var selectObjArray = me.getSelectionModel().getSelection();
                                            if(selectObjArray.length < 1){
                                                Ext.MessageBox.alert("温馨提示", "请选择楼栋!");
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
                                                        app.platform.lshc.view.base.RequestUtils.post_json(ids,"/manage/batchDeleteBuildInfo",false,false);
                                                        //刷新列表
                                                        me.store.proxy.extraParams = me.getParams();
                                                        me.store.loadPage(1);
                                                    }
                                            })

                                        }
                                    },
                                    {
                                        xtype: 'button', text: '导出', scope: me,
                                        glyph: 0xf158,
                                        handler: function () {
                                            me.exportExcel();
                                        }
                                    }
                                ]
                            }
                        ]
                    },
                    xtype: 'gridpanel',
                    itemId: 'buildGridpanel',
                    border: true,
                    store: store,
                    columnLines: true,
                    selModel: {selType: 'checkboxmodel',title:'全选'},
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
                       // {text: '序号', dataIndex: 'serialNo', width: '5%', align: 'center'},
                        {text: '楼栋地图编号', dataIndex: 'mapCode', width: '20%', align: 'center'
                        , renderer : function(data, metadata, record, rowIndex, columnIndex,store) {
                                return  '<a href="javascript:void(0)" style="color: #1f18ff;margin-right:14px;margin-left:2px" class="build-detail-info">'+record.data.mapCode+'</a>';
                            }
                        },
                        {text: '区（县）', dataIndex: 'region', width: '10%', align: 'center'},
                        {text: '街道（乡镇）', dataIndex: 'street', width: '15%', align: 'center'},
                        {text: '项目（小区）名称', dataIndex: 'projectName', width: '19%', align: 'center'},
                        {text: '房屋套数', dataIndex: 'houseCount', width: '14%', align: 'center'},
                        {text: '实际地址', dataIndex: 'address', width: '20%', align: 'center'}
                    ],
                    listeners: {
                        cellclick: function (table, td, cellIndex, record, tr, rowIndex, e, eOpts) {
                            //cellIndex==1
                            var className = e.getTarget().className;
                            if (className == 'build-detail-info') {
                                //参数不可乱传，穿错参数会导致无法渲染
                                var createContractView = Ext.create("app.platform.lshc.view.regi.manage.RegiDetailView",{
                                    config:{parentContainer:me,bizId:record.data.id}
                                });

                                var currentActiveTab =  Ext.mainFrame.queryById("centerBox").getActiveTab();
                                me.hide();
                                currentActiveTab.add(createContractView);
                                createContractView.config.srcType = 0
                                createContractView.initDetail(record.data.mapCode,record.data.address);
                                createContractView.initBtn();
                            }
                        },
                        itemdblclick: function (dataview, record, item, index, e) {

                            // var createContractView = Ext.create("app.platform.lshc.view.regi.manage.RegiDetailView",{
                            //     config:{parentContainer:me,bizId:record.data.id,mapCode:record.data.mapCode,address:record.data.address}
                            // });
                            //
                            // var currentActiveTab =  Ext.mainFrame.queryById("centerBox").getActiveTab();
                            // me.hide();
                            // currentActiveTab.add(createContractView);

                        }
                    }


        });
        me.callParent(arguments);

    }
});

