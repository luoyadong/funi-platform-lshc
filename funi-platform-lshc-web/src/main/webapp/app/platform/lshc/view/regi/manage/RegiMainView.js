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
        ,'app.platform.lshc.view.base.PrintInfoWindow'
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
            //下拉菜单存储展示的值
            if((formElements[i].name == 'region'
                || formElements[i].name =='street'
                || formElements[i].name =='communityName')
                && null != formElements[i].value
                && "" != formElements[i].value
                && " " != formElements[i].value){
                obj[formElements[i].name] = this.queryById(formElements[i].name+"ItemId").getRawValue()
            }else{
                obj[formElements[i].name] =formElements[i].value;
            }
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

        // var myTask = new Ext.LoadMask(target,{
        //     msg: "sasa",
        //     removeMask:true//完成后移除
        // });
    },
    resetForm:function(){

    },
    initStatus:function(){

    },
    initComponent: function () {
        var me = this;
        var mStore = Ext.create('Ext.data.Store', {
            fields: ['name','value'],
            autoLoad: true,
            data: [
                {'name': '全部', value: ''},
                {'name': '未提交',value:0},
                {'name': '已提交',value:1}
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
                {type:"string",name:"houseCount"},

                {type:"string",name:"communityName"},
                {type:"string",name:"buildName"}
            ],
            pageSize:18
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
                                        labelAlign:'right',
                                        fieldLabel:'区(县)',
                                        labelWidth:56,
                                        emptyText:'全部',
                                        width:186,
                                        name:"region",
                                        editable:false,
                                        triggerAction:'all',
                                        dataSourceUrl:app.platform.lshc.view.base.RequestUtils.url('/basic/getAllRegionList?showALL=1'),
                                        listeners:{
                                            change:function(){
                                                var cabinet = me.queryById("streetItemId");
                                                cabinet.store.proxy.extraParams = {regionId:arguments[1]};
                                                cabinet.store.load();
                                                me.queryById("streetItemId").clearValue();
                                                me.queryById("communityNameItemId").clearValue();
                                            }
                                        }
                                    },
                                    {xtype:'xcombobox',
                                        itemId:'streetItemId',
                                        fieldLabel:'街道(乡镇)',
                                        labelAlign:'right',
                                        labelWidth: 90,
                                        width: 200,
                                        emptyText:'全部',
                                        name:"street",
                                        editable:false,
                                        triggerAction:'all',
                                        dataSourceUrl:app.platform.lshc.view.base.RequestUtils.url('/basic/getAllBlockListByRegionId?showALL=1'),
                                        listeners:{
                                            change:function(){
                                                var cabinet = me.queryById("communityNameItemId");
                                                cabinet.store.proxy.extraParams = {blockId:arguments[1]};
                                                cabinet.store.load();
                                                me.queryById("communityNameItemId").clearValue();
                                            }
                                        }
                                    },
                                    {xtype:'xcombobox',
                                        itemId:'communityNameItemId',
                                        fieldLabel:'社区名称',
                                        labelAlign:'right',
                                        labelWidth: 65,
                                        width: 190,
                                        emptyText:'全部',
                                        name:"communityName",
                                        editable:false,
                                        triggerAction:'all',
                                        dataSourceUrl:app.platform.lshc.view.base.RequestUtils.url('/basic/getAllStreetListByRegionId?showALL=1')
                                    },
                                    {
                                        xtype:"textfield",
                                        labelAlign:'right',
                                        itemId:"projectItemId",
                                        name:"projectName",
                                        labelWidth:60,
                                        fieldLabel:'小区名称',
                                        width:200,
                                        emptyText:"小区名称"
                                    },
                                    {
                                        xtype:'xcombobox',
                                        itemId:'auditStatusItemId',
                                        fieldLabel:'提交状态',
                                        labelWidth:56,
                                        emptyText:'全部',
                                        width:136,
                                        store:mStore,
                                        name:"auditStatus",
                                        editable:false,
                                        triggerAction:'all'
                                    },
                                    {xtype: 'button',
                                        margin:'0 0 0 5',
                                        text:"查询",glyph:0xf002,handler:function(){
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
                                xtype: 'toolbar', columnWidth: 1, scope: me,
                                items: [
                                    // '->',
                                    {
                                        xtype:"textfield",
                                        labelAlign:'right',
                                        itemId:"addrItemId",
                                        name:"address",
                                        labelWidth:56,
                                        fieldLabel:'实际地址',
                                        width:186,
                                        emptyText:"实际地址"
                                    }
                                    ,
                                    {
                                        xtype:"textfield",
                                        labelAlign:'right',
                                        itemId:"buildtemId",
                                        name:"mapCode",
                                        labelWidth:90,
                                        fieldLabel:'楼栋地图编号',
                                        width:200,
                                        emptyText:"楼栋地图编号"
                                    },
                                    {
                                        xtype:"textfield",
                                        labelAlign:'right',
                                        itemId:"buildNameItemId",
                                        name:"buildName",
                                        labelWidth:65,
                                        fieldLabel:'楼栋名称',
                                        width:190,
                                        emptyText:"楼栋名称"
                                    },
                                    {
                                        xtype:"textfield",
                                        labelAlign:'right',
                                        itemId:"houseIdItemId",
                                        name:"houseId",
                                        labelWidth:60,
                                        fieldLabel:'房屋编号',
                                        width:200,
                                        emptyText:"房屋编号"
                                    },
                                    {
                                        xtype:"textfield",
                                        labelAlign:'right',
                                        itemId:"idNoItemId4",
                                        name:"idNo",
                                        labelWidth:105,
                                        fieldLabel:'人员/单位证件号',
                                        width:220,
                                        emptyText:"人员/单位证件号"
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
                                        glyph: 0xf1c3,
                                        handler: function () {
                                            me.exportExcel();
                                        }
                                    },
                                    {
                                        xtype:'button',text:"打印",glyph: 'xf02f@FontAwesome',itemId:"managePrintBtnItemId",
                                        handler:function(){
                                            var selectObjArray = me.getSelectionModel().getSelection();
                                            if(selectObjArray.length < 1){
                                                Ext.MessageBox.alert("温馨提示", "请选择楼栋!");
                                                return;
                                            }
                                            var ids = "";
                                            //组装ids
                                            for(var i=0;i< selectObjArray.length;i++){
                                                var record = selectObjArray[i].data;
                                                ids = ids + record.id+",";
                                            }
                                            if("" != ids && ids.indexOf(",") != -1){
                                                ids = ids.substr(0,ids.length - 1);
                                            }

                                            var   printInfoWin = Ext.create("app.platform.lshc.view.base.PrintInfoWindow", {
                                                bizTypeId: '1',
                                                showReport2:true,
                                                showReport3:false,
                                                bizParams:{'buildIds':ids}
                                            });
                                            printInfoWin.initStore();
                                            printInfoWin.show();
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
                        {text: '区（县）', dataIndex: 'region', flex:1, align: 'center'},
                        {text: '街道（乡镇）', dataIndex: 'street', flex:1, align: 'center'},
                        {text: '社区名称', dataIndex: 'communityName', flex:1, align: 'center'},
                        {text: '项目（小区）名称', dataIndex: 'projectName', flex:1, align: 'center'},
                        {text: '楼栋地图编号', dataIndex: 'mapCode', flex:1, align: 'center'},
                        //     , renderer : function(data, metadata, record, rowIndex, columnIndex,store) {
                        //     return  '<a href="javascript:void(0)" style="color: #1f18ff;margin-right:14px;margin-left:2px" class="build-detail-info">'+record.data.mapCode+'</a>';
                        // }
                       // },
                        {text: '楼栋名称', dataIndex: 'buildName', flex:1, align: 'center'},
                        {text: '房屋套数', dataIndex: 'houseCount', flex:1, align: 'center'},
                        {text: '实际地址', dataIndex: 'address', flex:1.5, align: 'center'}
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
                            createContractView.resetParams();

                        }
                    }


        });
        me.callParent(arguments);

    }
});

