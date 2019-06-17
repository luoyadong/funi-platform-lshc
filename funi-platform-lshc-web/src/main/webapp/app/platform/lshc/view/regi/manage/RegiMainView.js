/**
 * 房屋普查管理
 * @author luoyadong@funi365.com
 */

Ext.define('app.platform.lshc.view.regi.manage.RegiMainView', {
    extend: 'Ext.grid.Panel',
    xtype: 'lshc-view-regi-RegiMainView',
    requires: [
        //新增项目分期信息Tab视图
        'app.platform.lshc.view.regi.manage.ApproveWinView',
        'app.platform.lshc.view.regi.manage.RegiDetailView',
        'app.platform.lshc.view.regi.manage.ApproveTab',
        'app.platform.lshc.view.regi.manage.HouseListView',
        'app.platform.lshc.view.regi.manage.HouseDetailView'//房屋详情
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
    initComponent: function () {
        var me = this;
        var mStore = Ext.create('Ext.data.Store', {
            fields: ['name','value'],
            autoLoad: true,
            data: [
                {'name': '全部', value: ''},
                {'name': '录入',value:0},
                {'name': '提交',value:1},
            ]
        });
		var store = Ext.create("Funi.data.ListStore",{
            //url:app.platform.ghouse.view.base.RequestUtils.url("/contract/list"),
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
			data:[
			{"id":"eqweqwewq","serialNo":1,"region":"321321","street":"321321","projectName":"dasdas","mapCode":"321321312","address":"dasdsa","houseCount":12},
			{"id":"eqweqwewq2","serialNo":2,"region":"321321","street":"321321","projectName":"dasdas","mapCode":"321321312","address":"dasdsa","houseCount":12}	
			],
			load:false,
            pageSize:15
        });
        Ext.apply(me, {

                    tbar: {
                        layout: 'column', scope: me,
                        items: [
                            {
                                xtype: 'toolbar', columnWidth: 1, scope: me,
                                items: [
                                    '->',
                                    {
                                        xtype: 'combobox',
                                        fieldLabel: '区（县）',
                                        emptyText: '全部',
                                        store: mStore,
                                        editable: false,
                                        valueField: 'value',
                                        name:"region",
                                        itemId:'regionItemId',
                                        displayField: 'name',
                                        labelWidth: 65,
                                        width: 170,
                                        triggerAction:'all',
                                        //dataSourceUrl:app.platform.ghouse.view.base.RequestUtils.url('/archives/getArchivesList'),
                                        listeners:{
                                            change:function(){
                                                alert(arguments[1])
                                                //var cabinet = me.queryById("ghost-rent-contract-cabinet-itemId");
                                                // cabinet.store.proxy.extraParams = {id:arguments[1]};
                                                //cabinet.store.load();
                                                // me.queryById("ghost-rent-contract-cabinet-itemId").clearValue();
                                            }
                                        }
                                    },
                                    {
                                        xtype: 'combobox',
                                        fieldLabel: '街道（乡镇）',
                                        emptyText: '全部',
                                        store: mStore,
                                        editable: false,
                                        name:"street",
                                        valueField: 'value',
                                        itemId:'streetItemId',
                                        displayField: 'name',
                                        labelWidth: 80,
                                        width: 190
                                    },
                                    {
                                        xtype:"textfield",
                                        labelAlign:'right',
                                        itemId:"projectItemId",
                                        name:"keyword",
                                        name:"projectName",
                                        labelWidth:65,
                                        fieldLabel:'小区名称',
                                        width:200,
                                        emptyText:"小区名称"
                                    },
                                    {
                                        xtype:"textfield",
                                        labelAlign:'right',
                                        itemId:"buildtemId",
                                        name:"mapCode",
                                        labelWidth:81,
                                        fieldLabel:'楼栋地图编号',
                                        width:200,
                                        emptyText:"楼栋地图编号"
                                    },
                                    {
                                        xtype:"textfield",
                                        labelAlign:'right',
                                        itemId:"addrItemId",
                                        name:"address",
                                        labelWidth:65,
                                        fieldLabel:'实际地址',
                                        width:200,
                                        emptyText:"实际地址"
                                    },
                                    {
                                        xtype:"textfield",
                                        labelAlign:'right',
                                        itemId:"idNoItemId",
                                        name:"idNo",
                                        labelWidth:65,
                                        fieldLabel:'证件号码',
                                        width:200,
                                        emptyText:"证件号码"
                                    },
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
                                        xtype: 'button', text: '删除', scope: me,
                                        glyph: 'xf014@FontAwesome',
                                        handler: function () {
                                            console.log(me.down("gridpanel"))
                                            console.log(me.down("gridpanel").getSelectionModel())
                                            var selectObjArray = me.down("gridpanel").getSelectionModel().getSelection();
                                            if(selectObjArray.length!=1){
                                                Ext.MessageBox.alert("温馨提示", "请选择一份合同!");
                                                return;
                                            }
                                            //selectObjArray[0].data.xxxxx

                                        }
                                    },
                                    {
                                        xtype: 'button', text: '打印', scope: me,
                                        glyph: 'xf014@FontAwesome',
                                        handler: function () {
                                            //Ext.Msg.alert('提示', '已删除！');

                                            var createContractView = Ext.create("app.platform.lshc.view.regi.manage.RegiDetailView",{
                                                config:{parentContainer:me}
                                            });
                                            var currentActiveTab =  Ext.mainFrame.queryById("centerBox").getActiveTab();
                                            console.log(currentActiveTab)
                                            console.log(createContractView)
                                            me.hide();
                                            //createContractView.show();
                                            currentActiveTab.add(createContractView);
                                        }
                                    },
                                    {
                                        xtype: 'button', text: '审批', scope: me,glyph: 'xf234@FontAwesome',
                                        handler: function () {

                                            Ext.create('app.platform.lshc.view.regi.manage.ApproveWinView', {
                                                parentViewName: 'app.platform.lshc.view.regi.manage.RegiMainView',
                                                parentPanel: this,
                                                parentContainer : me.config.parentContainer
                                                //parentStore : store,
                                                //historyId: record.data.historyId
                                            }).show();

                                            //Ext.Msg.alert('提示', '已新增！');

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
                        {text: '序号', dataIndex: 'serialNo', width: '5%', align: 'center'},
                        {text: '区（县）', dataIndex: 'region', width: '10%', align: 'center'},
                        {text: '街道（乡镇）', dataIndex: 'street', width: '15%', align: 'center'},
                        {text: '项目（小区）名称', dataIndex: 'projectName', width: '19%', align: 'center'},
                        {text: '楼栋地图编号', dataIndex: 'mapCode', width: '15%', align: 'center'},
                        {text: '房屋套数', dataIndex: 'houseCount', width: '14%', align: 'center'},
                        {text: '实际地址', dataIndex: 'address', width: '20%', align: 'center'}
                    ],
                    listeners: {
                        cellclick: function (table, td, cellIndex, record, tr, rowIndex, e, eOpts) {
                            //  if(cellIndex==1){
                            //参数不可乱传，穿错参数会导致无法渲染
                                   var createContractView = Ext.create("app.platform.lshc.view.regi.manage.RegiDetailView",{
                                      config:{parentContainer:me,bizId:record.data.id,mapCode:record.data.mapCode,address:record.data.address}
                                   });

                            var currentActiveTab =  Ext.mainFrame.queryById("centerBox").getActiveTab();
                            me.hide();
                            currentActiveTab.add(createContractView);

                            //createContractView.initDetail(record.mapCode,record.address);

                            // Ext.Ajax.request({
                            //     url:app.platform.ghouse.view.base.RequestUtils.url("/contract/view/"+record.id+"/1"),
                            //     method:"post",
                            //     async:false,
                            //     success:function(response)
                            //     {
                            //         var data = JSON.parse(response.responseText);
                            //         createContractView.addWorkNode(data.result.contract.serviceNum,record.data.nodeName,true);
                            //         createContractView.fillForm(data.result);
                            //         createContractView.readOnly(true);
                            //         createContractView.modifyTitle();
                            //     },
                            //     failure:function(){
                            //         Ext.MessageBox.alert("温馨提示", "服务器异常,请稍后重试!");
                            //     }
                            // });
                            // }
                        },
                        itemdblclick: function (dataview, record, item, index, e) {

                        }
                    }


        });
        me.callParent(arguments);

    }
});

