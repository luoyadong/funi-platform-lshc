/**
 *
 *@author 3
 */
Ext.define("app.platform.lshc.view.regi.manage.HouseTab",{
    extend:"Ext.panel.Panel",
    width:"100%",
    //height:600,
    height:"100%",
    layout:"fit",
    requires:[
		"app.platform.lshc.view.regi.manage.HousePerson"
    ],
    xtype:"lshc-regi-manage-houseInfo",
    itemId:"lshc-regi-manage-houseInfo-itemId",
	config: {
        //主容器
        parentContainer: null,
		//是否详情
		isDetail:true
    },
    constructor: function (config) {
        var me = this;
        config = config || {};
        Ext.applyIf(config, me.config);
        this.callParent(arguments);
    },
    getData:function(){
        var form = this.down("form");
		var entView = this.queryById("lshc-view-HousePerson-tab-itemId");
        var rtJson = {};
        if(form.isValid()){
			 rtJson.regiInfo = form.getValues();

			rtJson.regiInfo.region = this.queryById("regionItemId2").getRawValue();
			rtJson.regiInfo.street = this.queryById("streetItemId2").getRawValue();

			 rtJson.entInfoList = entView.getData();
			 return rtJson;
        }
        throw {message:"请填写普查信息"};
    },

    initComponent:function()
    {
        var me =this;
        var mStore = Ext.create('Ext.data.Store', {
            fields: ['name','value'],
            autoLoad: true,
            data: [
                {'name': '拉萨市',value:'拉萨市'}
            ]
        });
		//房屋用途
		var useStore = Ext.create('Ext.data.Store', {
			fields: ['name','value'],
			autoLoad: true,
			data: [
				{'name': '住宅',value:'住宅'},
				{'name': '商业体',value:'商业体'}
			]
		});
		//房屋结构
		var strStore = Ext.create('Ext.data.Store', {
			fields: ['name','value'],
			autoLoad: true,
			data: [
				{'name': '框架',value:'框架'},
				{'name': '砖混',value:'砖混'}
			]
		});
        Ext.applyIf(this,
            {
                items:[
					{
					xtype: 'form',
					itemId:"lshc-regi-manage-houseInfo-form-itemId",
					overflowY:'auto',
                    initHide:function(obj){
                    },
                    listeners: {
                        "afterrender": function (cmp) {
                        }
                    },
                    items:[
                        {
                            xtype: 'fieldset',
                            margin: '5 5 0 5',
                            border: true,
                            width: "100%",
                            title: '小区信息',
                            defaults: {
                                layout: 'vbox',
                                border: false
                            }, items: [
                            {
                                xtype: 'fieldset',
                                defaults: {
                                    labelAlign:"right"
                                },
                                layout: {
                                    type: 'column',
                                    columns: 11
                                },
                                items: [
                                    {
                                        xtype: "textfield",name:"projectName",
                                        emptyText: "项目(小区)名称",fieldLabel: '项目(小区)名称',itemId:"ghouse-rent-contract-contract-contractNo-itemId"
                                    }, {
                                        xtype: "textfield", name:"estateUnitName",fieldLabel: '物业管理单位'
                                    }
                                ]
                            }]
                        },
						{
                            xtype: 'fieldset',
                            margin: '5 5 0 5',
                            border: true,
                            width: "100%",
                            title: '楼栋信息',
                            defaults: {
                                layout: 'vbox',
                                border: false
                            }, items: [
                            {
                                xtype: 'fieldset',
                                defaults: {
                                    labelAlign:"right"
                                },
                                layout: {
                                    type: 'table',
                                    columns: 2
                                },
                                items: [
                                    {
                                        xtype: "textfield",name:"mapCode",
										//columnWidth:2,
										width:510,
										//colspan:4,
                                        emptyText: "楼栋地图编号",fieldLabel: '楼栋地图编号',itemId:"ghouse-rent-contract-contract-contractNo2-itemId"
                                    }
                                ]
                            }
						]
                        },
						{
                            xtype: 'fieldset',
                            margin: '5 5 0 5',
                            border: true,
                            width: "100%",
                            title: '房屋信息',
                            defaults: {
                                layout: 'vbox',
                                border: false
                            },
							
							items: [
								 {
                                xtype: 'fieldset',
                                defaults: {
                                    labelAlign:"right"
                                },
                                layout: {
                                    type: 'table',
                                    columns: 8
                                },
                                items: [
										{
										xtype: 'combobox',
										fieldLabel: '实际地址',
										emptyText: '全部',
										store: mStore,
										editable: false,
										hidden:me.config.isDetail,
										name:"addressCity",
										valueField: 'value',
										itemId:'isVacant',
										displayField: 'name',
										labelWidth: 100,
										value:"拉萨市",
										hidden:me.config.isDetail,
										width: 170,
										colspan:1
									},
									{xtype: 'tbtext', text: '', colspan:1,hidden:me.config.isDetail},
									{xtype:'xcombobox',
										itemId:'regionItemId2',
										emptyText:'区(县)',
										width: 70
										,colspan:1,
										name:"region",
										editable:false,
										triggerAction:'all',
										hidden:me.config.isDetail,
										dataSourceUrl:app.platform.lshc.view.base.RequestUtils.url('/basic/getAllRegionList'),
										listeners:{
											change:function(){
												var cabinet = me.queryById("streetItemId2");
												cabinet.store.proxy.extraParams = {regionId:arguments[1]};
												cabinet.store.load();
												me.queryById("streetItemId2").clearValue();
											}
										}
									},
									{xtype:'xcombobox',
										itemId:'streetItemId2',
										width: 70
										,colspan:1,
										emptyText:'街道',
										name:"street",
										editable:false,
										hidden:me.config.isDetail,
										triggerAction:'all',
										dataSourceUrl:app.platform.lshc.view.base.RequestUtils.url('/basic/getAllBlockListByRegionId')
									},
									{xtype: 'tbtext', text: '', colspan:1,hidden:me.config.isDetail},
									{
										xtype: "textfield",name:"roomNo",
										hidden:me.config.isDetail,
										colspan:1,
										width: 70,
										emptyText: "房号"
									},
									{xtype: 'tbtext', text: '号',hidden:me.config.isDetail,  colspan:2,hidden:me.config.isDetail,width:115,style:"text-align:left"},

									{
										xtype: "textfield",name:"buildNo",
										colspan:1,
										hidden:me.config.isDetail,
										labelWidth: 100,
										fieldLabel: ' ',
										width: 170,
										emptyText: "栋"
									},
									{xtype: 'tbtext', hidden:me.config.isDetail,text: '栋',colspan:1,hidden:me.config.isDetail},

									{
										xtype: "numberfield",
										name: "unitNo",
										cls: "lessee-personal",
										emptyText: "单元",
										hidden:me.config.isDetail,
										width: 70,
										colspan:1
									},
									{xtype: 'tbtext', hidden:me.config.isDetail,text: '单元',colspan:1,hidden:me.config.isDetail},

									{
										xtype: "numberfield",
										name: "layer",
										cls: "lessee-personal",
										emptyText: "层",
										hidden:me.config.isDetail,
										width: 70,
										colspan:1
									},
									{xtype: 'tbtext',hidden:me.config.isDetail, text: '层',colspan:4,hidden:me.config.isDetail,width:198,style:"text-align:left"},


									  {
                                        xtype: "textfield",name:"rightAddr",
										//columnWidth:11,
										width:510,
										hidden:!me.config.isDetail,
										colspan:8,
                                        emptyText: "实际地址",fieldLabel: '实际地址',itemId:"ghouse-rent-contract-contract-contractNo2-itemId2"
                                    },
									  {
                                        xtype: "textfield",name:"rightAddr",
										//columnWidth:11,
										width:510,
										colspan:8,
                                        emptyText: "产权证书地址",fieldLabel: '产权证书地址',itemId:"ghouse-rent-contract-contract-contractNo2-itemId2"
                                    },
									  {
                                        xtype: "numberfield",name:"houseArea",
										//width:180,
										colspan:4,
                                        emptyText: "建筑面积",fieldLabel: '建筑面积',itemId:"ghouse-rent-contract-contract-contractNo2-itemId3"
                                    },
									  {
                                        xtype: "numberfield",name:"innerHouseArea",
										colspan:4,
										//width:180,
                                        emptyText: "套内面积",fieldLabel: '套内面积',itemId:"ghouse-rent-contract-contract-contractNo2-itemId4"
                                    },
									{
										xtype: "numberfield",
										name: "houseRoom",
										cls: "lessee-personal",
										emptyText: "室",
										width: 170,
										fieldLabel: '房屋户型',
										labelWidth: 100,
										colspan:1
									},

									{xtype: 'tbtext', text: '室',colspan:1},
									{
										xtype: "numberfield",
										name: "houseHall",
										cls: "lessee-personal",
										emptyText: "厅",
										width: 70,
										colspan:1
									},
									{xtype: 'tbtext', text: '厅', colspan:1},
									{
										colspan:4,
										xtype:'xcombobox',
										itemId:'isRegiItemId',
										fieldLabel:'是否办理产权',
										emptyText:'全部',
										name:"isRegi",
										editable:false,
										triggerAction:'all',
										dataSourceUrl:app.platform.lshc.view.base.RequestUtils.url('/basic/getDictionaryListName?type=WHETHER')
									},
									  {
                                        xtype: "textfield",name:"rightNo",
										colspan:4,
										//width:180,
                                        emptyText: "产权证号",fieldLabel: '产权证号',itemId:"ghouse-rent-contract-contract-contractNo2-itemId13"
                                    },
									{
										xtype:'datefield',colspan:4,
										fieldLabel:'房屋建成年份', name:"buildDate", cls:"renew",
										format:'Y-m-d'
									},
									{
										colspan:4,
										xtype:'xcombobox',
										itemId:'houseTypeItemId',
										fieldLabel:'房屋类别',
										emptyText:'全部',
										name:"houseType",
										editable:false,
										triggerAction:'all',
										dataSourceUrl:app.platform.lshc.view.base.RequestUtils.url('/basic/getDictionaryListName?type=HOUSE_TYPE')
									},
									{
										colspan:4,
										xtype:'xcombobox',
										itemId:'houseStructureItemId',
										fieldLabel:'房屋结构',
										emptyText:'全部',
										name:"houseStructure",
										editable:false,
										triggerAction:'all'
										,store:strStore
										//,dataSourceUrl:app.platform.lshc.view.base.RequestUtils.url('/basic/getDictionaryListName?type=HOUSE_STRUCTURE')
									},
									{
										colspan:4,
										xtype:'xcombobox',
										itemId:'houseUseItemId',
										fieldLabel:'房屋用途',
										emptyText:'全部',
										name:"houseUse",
										editable:false,
										triggerAction:'all'
									    ,store:useStore
										//,dataSourceUrl:app.platform.lshc.view.base.RequestUtils.url('/basic/getDictionaryListName?type=HOUSE_USE')
									},
									{
										colspan:4,
										xtype:'xcombobox',
										itemId:'landStatusItemId',
										fieldLabel:'土地性质',
										emptyText:'全部',
										name:"landStatus",
										editable:false,
										triggerAction:'all',
										dataSourceUrl:app.platform.lshc.view.base.RequestUtils.url('/basic/getDictionaryListName?type=LAND_NATURE')
									},

									  {
                                        xtype: "textfield",name:"preSaleNo",
										colspan:4,
									//	width:180,
                                        emptyText: "预售许可证号",fieldLabel: '预售许可证号',itemId:"ghouse-rent-contract-contract-contractNo2-itemId19"
                                    },
									{
										colspan:4,
										xtype:'xcombobox',
										itemId:'fitStatusItemId',
										fieldLabel:'装修状态',
										emptyText:'全部',
										name:"fitStatus",
										editable:false,
										triggerAction:'all',
										dataSourceUrl:app.platform.lshc.view.base.RequestUtils.url('/basic/getDictionaryListName?type=FIT_STATUS')
									},
								
									  {
                                        xtype: "textfield",name:"personNum",
										colspan:4,
										//width:180,
                                        emptyText: "居住人数",fieldLabel: '居住人数',itemId:"ghouse-rent-contract-contract-contractNo2-itemId22"
                                    },
									{
										colspan:4,
										xtype:'xcombobox',
										itemId:'isCheckInItemId',
										fieldLabel:'是否入住',
										emptyText:'全部',
										name:"isCheckIn",
										editable:false,
										triggerAction:'all',
										dataSourceUrl:app.platform.lshc.view.base.RequestUtils.url('/basic/getDictionaryListName?type=WHETHER')
									},
									{
										xtype:'datefield',colspan:4,
										fieldLabel:'出租开始时间', name:"rentStartDate", cls:"renew",
										format:'Y-m-d'
									},

									{
										colspan:4,
										xtype:'xcombobox',
										itemId:'isRentItemId',
										fieldLabel:'是否出租',
										emptyText:'全部',
										name:"isRent",
										editable:false,
										triggerAction:'all',
										dataSourceUrl:app.platform.lshc.view.base.RequestUtils.url('/basic/getDictionaryListName?type=WHETHER')
									},
									{
										xtype:'datefield',colspan:4,
										fieldLabel:'出租结束时间', name:"rentEndDate", cls:"renew",
										format:'Y-m-d'
									}
                                ]
                            }
							]
                        },
						{
                            xtype: 'fieldset',
                            margin: '5 5 0 5',
                            border: true,
                            width: "100%",
                            title: '人口信息',
							height:200,
                            defaults: {
                                layout: 'vbox',
                                border: false
                            },
							items:[
								{
										//title:'人口信息',
										itemId:'lshc-view-HousePerson-tab-itemId',
										xtype:"lshc-view-HousePerson-xtype",
										overflowY:'auto',
										disabled:false,
										width:"100%",
										height:200
									}	
								]
						}
					]
					}
				]
            });
        this.callParent(arguments);
    }
});