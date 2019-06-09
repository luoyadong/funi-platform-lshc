/**
 * 土地产权
 * reated by guochuan.deng on 2017/5/17.
 */
Ext.define('app.platform.lshc.view.regi.manage.HousePerson', {
    extend: 'Ext.panel.Panel',
    xtype:'lshc-view-HousePerson-xtype',
    itemId:'lshc-view-HousePerson-itemId',
	requires:[
		"app.platform.lshc.view.regi.manage.RightEntWin"
    ],
    getData:function(){
        var grid = this.down("xgridpanel");
        var store = grid.store;
        var array = new Array();
        var count = store.getCount();
        for(var i=0;i<count;i++){
            if(null == store.getAt(i).data.houseId || "" == store.getAt(i).data.houseId){
                array.push(store.getAt(i).data);
            }
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
       
        Ext.apply(me, {
            items: [
                {
                    xtype: 'xgridpanel',
                    itemId: 'ghouse-manage-HousePropety-resident',
                    border: true,
                    minHeight: 100,
                    width: "100%",
                    //store: store,
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
                                    //access:"AUTH_GHOUSE_HOUSE_ENT_ADD",
                                    handler: function () {
                                        Ext.create("app.platform.lshc.view.regi.manage.RightEntWin", {
                                            
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
                                        Ext.MessageBox.confirm("确认", '确认删除产权人：' + orgRecord.data.propertyUserName, function (btn, text) {
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
                        {text: 'houIs', dataIndex: 'houseId',hidden:true},
                        {text: '产权证书编号', dataIndex: 'propertyCertificateNo',flex: 1, align: 'center'},
                        {text: '产权人姓名', dataIndex: 'propertyUserName', flex: 1, align: 'center'},
                        {text: '产权人身份证号', dataIndex: 'propertyUserIdNumber', flex: 1, align: 'center'},
                        {text: '产权人联系电话', dataIndex: 'propertyUserTel', flex: 1, align: 'center'},
                    ]
                }
            ]
        });
        me.dockedItems = [{
            xtype: 'toolbar',
            style: {
                background: '#f3f7f7'
            },
            dock: 'top',
            items: [{
                xtype: 'displayfield',
                value: '<span style="font-weight: bold;font-size: 15px;">产权人信息</span>'
            }]
        }];
        me.callParent(arguments);
    }
});