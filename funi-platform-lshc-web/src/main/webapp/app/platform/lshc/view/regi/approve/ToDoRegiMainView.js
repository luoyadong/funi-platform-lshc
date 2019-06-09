/**
 * 房屋普查管理
 * @author luoyadong@funi365.com
 */

Ext.define('app.platform.lshc.view.regi.approve.ToDoRegiMainView', {
    extend: 'Ext.panel.Panel',
    xtype: 'lshc-view-regi-RegiDetailView',
    requires: [
        //房屋列表信息
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

        Ext.apply(me, {
            tbar: {
                layout: 'column', scope: me,
                items: [

                    {
                        xtype: 'toolbar', columnWidth: 1,scope: me, itemId: 'search',
                        items: [
                            '->',
                            {
                                xtype: 'combobox',
                                fieldLabel: '状态',
                                emptyText: '全部',
                                store: mStore,
                                editable: false,
                                valueField: 'value',
                                itemId:'isVacant',
                                displayField: 'name',
                                labelWidth: 60,
                                width: 150,
                            },
                            {
                                xtype:"textfield",
                                labelAlign:'right',
                                itemId:"ghost-rent-contract-keyword-itemId",
                                name:"keyword",
                                labelWidth:65,
                                fieldLabel:'房屋编码',
                                width:240,
                                emptyText:"房屋编码"
                            },
                            {
                                xtype:"textfield",
                                labelAlign:'right',
                                itemId:"ghost-rent-contract-keyword2-itemId",
                                name:"keyword",labelWidth:65,fieldLabel:'人员姓名',
                                width:240,
                                emptyText:"人员姓名"
                            },
                            {
                                xtype: 'button', text: '查询', scope: me,
                                glyph:0xf002,
                                handler: function () {

                                }
                            },
                            {
                                xtype: 'button', text: '重置', scope: me,
                                glyph:'xf0e2@FontAwesome',
                                handler: function () {
                                    me.query("#searchPeriodCom")[0].setValue('');
                                    me.query("#searchContent")[0].setValue('');
                                    me.query("#searchPeriodStatus")[0].setValue('');
                                }
                            },
                            {
                                xtype:'button',
                                text:'返回',
                                hidden:false,
                                glyph: 0xf0a5,
                                handler:function() {
                                    me.config.parentContainer.show();
                                }
                            }
                        ]
                    }
                ]
            },
            // xtype:'container',
            height: '100%',
            width:'100%',
            layout:{
                type:'border',
                //	regionWeights:{
                //	west:30,
                //	east:30
                //}
            },

            items: [{
                region: 'north',//指定组建具体位置，上方
                //height: 40,
                items:[
                    {
                        xtype: 'toolbar', columnWidth: 1,scope: me, itemId: 'search',
                        items: [
                            {   xtype: 'tbtext',
                                margin: '0 0 0 0',
                                //width:'98px',
                                html:'<div style="font-weight:bold;font-size:13">楼栋信息：</div>'
                            },
                            {   xtype: 'tbtext',
                                margin: '0 0 0 0',
                                //width:'98px',
                                html:'<div style="font-weight:bold;font-size:10">XS1234561</div>'
                            },
                            {   xtype: 'tbtext',
                                margin: '0 0 0 0',
                                //width:'98px',
                                html:'<div style="font-weight:bold;font-size:10;margin-left:10;float:right">城关区纳金乡1001号2栋</div>'
                            },
                            "->",
                            {   xtype: 'tbtext',
                                margin: '0 0 0 0',
                                //width:'98px',
                                html:'<div style="font-weight:bold;font-size:13;">状态：</div>'
                            },
                            {   xtype: 'tbtext',
                                margin: '0 0 0 0',
                                //width:'98px',
                                html:'<div style="font-weight:bold;font-size:10;color:green;">审核通过</div>'
                            },
                            {   xtype: 'tbtext',
                                margin: '0 0 0 0',
                                //width:'98px',
                                html:'<div style="font-weight:bold;font-size:13;margin-left:10;">房屋当前处理环节：</div>'
                            },
                            {   xtype: 'tbtext',
                                margin: '0 0 0 0',
                                //width:'98px',
                                html:'<div style="font-weight:bold;font-size:11;color:green;">①社区->②街道办</div>'
                            },
                            {   xtype: 'tbtext',
                                margin: '0 0 0 0',
                                //width:'98px',
                                html:'<div style="font-weight:bold;font-size:11;color:orange;">->③区政府</div>'
                            },
                            {   xtype: 'tbtext',
                                margin: '0 0 0 0',
                                //width:'98px',
                                html:'<div style="font-weight:bold;font-size:11;margin-right:50;">->④市住建局</div>'
                            }
                        ]
                    }
                ]
            }, {
                region: 'west',//左方
                width: '40%',
                items:[
                    {
                        // title: '房屋列表信息',
                        itemId: 'tab1',
                        autoScroll: true,
                        scrollable: true,
                        //height:405,
                        // bodyStyle : 'overflow-x:hidden; overflow-y:scroll',
                        items: [
                            {xtype: 'lshc-view-regi-HouseListView'}
                        ]
                    }
                ]
            }, {
                region: 'center',
                items:[
                    {
                        itemId: 'tab2',
                        autoScroll: true,
                        scrollable: true,
                        items: [
                            {xtype: 'lshc-view-regi-HouseDetailView'}
                        ]
                    }
                ]
            }]

        });
        me.callParent(arguments);

    }
});

