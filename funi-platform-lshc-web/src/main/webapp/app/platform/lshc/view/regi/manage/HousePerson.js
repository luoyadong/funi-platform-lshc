/**
 * 人员信息
 * reated by yadong.luo on 2019/5/17.
 */
Ext.define('app.platform.lshc.view.regi.manage.HousePerson', {
    extend: 'Ext.panel.Panel',
    xtype:'lshc-view-HousePerson-xtype',
    itemId:'lshc-view-HousePerson-itemId',
	requires:[
		"app.platform.lshc.view.regi.manage.RightEntWin",
        "app.platform.lshc.view.base.RequestUtils"
    ],
    getData:function(){
        var grid = this.down("xgridpanel");
        var store = grid.store;
        var array = new Array();
        var count = store.getCount();
        for(var i=0;i<count;i++){
            if("1" == store.getAt(i).data.isNew){
                store.getAt(i).data.id = "";
            }
            array.push(store.getAt(i).data);
        }
        return array;
        //throw {message:"请添加产权人信息"};
    },
    config: {
        //主容器
        houseId:null,
        isUpdate:false
    },

    initComponent: function () {
        var me = this;
       var store = Ext.create("Funi.data.ListStore",{
            //url:app.platform.ghouse.view.base.RequestUtils.url("/contract/list"),
            fields:[
			    {type:"string",name:"id"},
                {type:"string",name:"isNew"},
                {type:"string",name:"entName"},
                {type:"string",name:"idNo"},
                {type:"string",name:"sex"},
                {type:"string",name:"entType"},
                {type:"string",name:"entNation"},
                {type:"string",name:"entNative"},
				{type:"string",name:"tel"},
                {type:"string",name:"marriageStatus"}
            ],
			load:false,
            pageSize:15
        });
        Ext.apply(me, {
            items: [
                {
                    xtype: 'xgridpanel',
                    itemId: 'lshc-manage-housePerson-panelList',
                    border: true,
                    minHeight: 100,
                    width: "100%",
                    store: store,
                    margin: '0 0 20 0',
                    columnLines: true,
                    listeners: {
                        afterrender: function () {
                            // me.store.loadData(me.params.data.gather);
                        }
                    },
                    tbar: [
                        '->', {
                            xtype: "container",
                            defaults: {
                                margin: '0 5 0 5'
                            },
                            items: [
                                {
                                    xtype: 'button',
                                    text: '添加',
                                    glyph: 0xf0fe,
                                    //access:"AUTH_GHOUSE_HOUSE_ENT_ADD",
                                    handler: function () {
                                        Ext.create("app.platform.lshc.view.regi.manage.RightEntWin", {
                                            residentStore: store
                                        }).show();
                                    }
                                }, {
                                    xtype: 'button',
                                    text: '删除',
                                    //access:"AUTH_GHOUSE_HOUSE_ENT_DELETE",
                                    glyph: 0xf00d,
                                    dock: "top",
                                    disabled: false,
                                    cls: "selected-action",
                                    handler: function (cmp) {
                                        var orgRecord = me.queryById("ghouse-manage-HousePropety-resident").getSelectionModel().getSelection()[0];
                                        Ext.MessageBox.confirm("确认", '确认删除：' + orgRecord.data.entName, function (btn, text) {
                                            if (btn === "yes") {
                                                Funi.core.Http.post({
                                                    url: Funi.core.Context.path("ghouse", "/house/deleteRightEnt"),
                                                    params: {id:orgRecord.data.id},
                                                    //请求成功的回调函数
                                                    success: function (response, opts) {
                                                        me.queryById("ghouse-manage-HousePropety-resident").store.remove(orgRecord);
                                                        //store.reload();
                                                    }
                                                });
                                            }
                                        })
                                    }
                                }
                            ]
                        }
                    ],
                    columns: [
                        {text: 'id', dataIndex: 'id',hidden:true},
                        {text:"isNew",dataIndex:"isNew",hidden:true},
						 {text: '姓名', dataIndex: 'entName',flex: 1, align: 'center'},
						 {text: '证件号码', dataIndex: 'idNo',flex: 1.5, align: 'center'},
						 {text: '性别', dataIndex: 'sex',flex: 0.5, align: 'center'},
						 {text: '人员类别', dataIndex: 'entType',flex: 0.5, align: 'center'},
                        {text: '民族', dataIndex: 'entNation',flex: 1, align: 'center'},
                        {text: '籍贯', dataIndex: 'entNative', flex: 1, align: 'center'},
                        {text: '联系电话', dataIndex: 'tel', flex: 1, align: 'center'},
                        {text: '婚姻状况', dataIndex: 'marriageStatus', flex: 1, align: 'center'}
                    ]
                }
            ]
        });
    
        me.callParent(arguments);
    }
});