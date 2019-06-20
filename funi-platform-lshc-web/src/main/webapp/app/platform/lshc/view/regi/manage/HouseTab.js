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
                {'name': '全部', value: ''},
                {'name': '录入',value:0},
                {'name': '提交',value:1},
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
                                    columns: 6
                                },
                                items: [
										{
										xtype: 'combobox',
										fieldLabel: '实际地址',
										margin: '0 0 5 40',
										emptyText: '全部',
										store: mStore,
										editable: false,
										hidden:me.config.isDetail,
										name:"addressCity",
										valueField: 'value',
										itemId:'isVacant',
										displayField: 'name',
										labelWidth: 60,
										width: 130,
										colspan:1
									},
									{
										xtype: 'combobox',
										emptyText: '全部',
										store: mStore,
										editable: false,
										hidden:me.config.isDetail,
										name:"addressRegion",
										valueField: 'value',
										itemId:'isVacant2',
										displayField: 'name',
										width: 65
										,colspan:1
									},
									{
										xtype: 'combobox',
										emptyText: '全部',
										store: mStore,
										editable: false,
										hidden:me.config.isDetail,
										name:"addressCounty",
										valueField: 'value',
										itemId:'isVacant3',
										displayField: 'name',
										width: 65
										,colspan:1
									},


									{
										xtype: 'combobox',
										emptyText: '全部',
										margin: '0 0 5 20',
										store: mStore,
										editable: false,
										hidden:me.config.isDetail,
										name:"roomNo",
										valueField: 'value',
										itemId:'isVacant4',
										displayField: 'name',
										width: 65
										,colspan:1
									},
									{xtype: 'tbtext', text: '号',  margin: '0 10 0 10',colspan:2,hidden:me.config.isDetail},
									{
										xtype: 'combobox',
										emptyText: '全部',
										store: mStore,
										editable: false,
										hidden:me.config.isDetail,
										name:"buildNo",
										valueField: 'value',
										itemId:'isVacant5',
										displayField: 'name',
										margin: '0 0 5 40',
										labelWidth: 60,
										fieldLabel: ' ',
										width: 130
										,colspan:1
									},
									{xtype: 'tbtext', text: '栋',  margin: '0 0 0 -16',colspan:1,hidden:me.config.isDetail},
									{
										xtype: 'combobox',
										emptyText: '全部',
										store: mStore,
										editable: false,
										hidden:me.config.isDetail,
										name:"unitNo",
										valueField: 'value',
										itemId:'isVacant6',
										displayField: 'name',
										margin: '0 5 5 -50',
										width: 65
										,colspan:1
									},
									{xtype: 'tbtext', text: '单元',  margin: '0 5 5 -60',colspan:1,hidden:me.config.isDetail},
									{
										xtype: 'combobox',
										emptyText: '全部',
										store: mStore,
										editable: false,
										hidden:me.config.isDetail,
										name:"layer",
										valueField: 'value',
										itemId:'isVacant7',
										displayField: 'name',
										margin: '0 0 5 -85',
										width: 65
										,colspan:1
									},
									{xtype: 'tbtext', text: '层',  margin: '0 5 0 -80',colspan:1,hidden:me.config.isDetail},

                                     
									  
									  
									  {
                                        xtype: "textfield",name:"rightAddr",
										//columnWidth:11,
										width:510,
										hidden:!me.config.isDetail,
										colspan:6,
                                        emptyText: "实际地址",fieldLabel: '实际地址',itemId:"ghouse-rent-contract-contract-contractNo2-itemId2"
                                    },
									  {
                                        xtype: "textfield",name:"rightAddr",
										//columnWidth:11,
										width:510,
										colspan:6,
                                        emptyText: "产权证书地址",fieldLabel: '产权证书地址',itemId:"ghouse-rent-contract-contract-contractNo2-itemId2"
                                    },
									  {
                                        xtype: "textfield",name:"houseArea",
										//width:180,
										colspan:3,
                                        emptyText: "建筑面积",fieldLabel: '建筑面积',itemId:"ghouse-rent-contract-contract-contractNo2-itemId3"
                                    },
									  {
                                        xtype: "textfield",name:"innerHouseArea",
										colspan:3,
										margin: '0 0 5 0',
										//width:180,
                                        emptyText: "套内面积",fieldLabel: '套内面积',itemId:"ghouse-rent-contract-contract-contractNo2-itemId4"
                                    },
									{
										xtype: 'combobox',
										emptyText: '全部',
										fieldLabel: '房屋户型',
										labelWidth: 60,
										store: mStore,
										editable: false,
										name:"houseRoom",
										valueField: 'value',
										itemId:'isVacant10',
										displayField: 'name',
										margin: '0 0 0 40',
										width: 130,
										colspan:1
									},
									{xtype: 'tbtext', text: '室',  margin: '0 0 0 -18',colspan:1},
									{
										xtype: 'combobox',
										emptyText: '全部',
										store: mStore,
										editable: false,
										name:"houseHall",
										valueField: 'value',
										itemId:'isVacant11',
										displayField: 'name',
										width: 60,
										margin: '0 5 0 -50',
										//columnWidth:.65,
										colspan:1
									},
									{xtype: 'tbtext', text: '厅',  margin: '0 5 0 -70',colspan:1},
									{
                                        xtype: "textfield",name:"isRegi",
										margin: '5 5 0 0',
										colspan:2,
										//width:180,
                                        emptyText: "是否办理产权",fieldLabel: '是否办理产权',itemId:"ghouse-rent-contract-contract-contractNo2-itemId12"
                                    },

									  {
                                        xtype: "textfield",name:"rightNo",
										margin: '5 5 0 0',
										colspan:3,
										//width:180,
                                        emptyText: "产权证号",fieldLabel: '产权证号',itemId:"ghouse-rent-contract-contract-contractNo2-itemId13"
                                    },
									  {
                                        xtype: "textfield",name:"buildDate",
										margin: '5 5 0 0',
										colspan:3,
									//	width:180,
                                        emptyText: "房屋建成年份",fieldLabel: '房屋建成年份',itemId:"ghouse-rent-contract-contract-contractNo2-itemId14"
                                    },
									  {
                                        xtype: "textfield",name:"houseType",
										margin: '5 5 0 0',
										colspan:3,
										//width:180,
                                        emptyText: "房屋类别",fieldLabel: '房屋类别',itemId:"ghouse-rent-contract-contract-contractNo2-itemId15"
                                    },
									  {
                                        xtype: "textfield",name:"houseStructure",
										margin: '5 5 0 0',
										colspan:3,
										//width:180,
                                        emptyText: "房屋结构",fieldLabel: '房屋结构',itemId:"ghouse-rent-contract-contract-contractNo2-itemId16"
                                    },
									  {
                                        xtype: "textfield",name:"houseUse",
										margin: '5 5 0 0',
										colspan:3,
										//width:180,
                                        emptyText: "房屋用途",fieldLabel: '房屋用途',itemId:"ghouse-rent-contract-contract-contractNo2-itemId17"
                                    },
									  {
                                        xtype: "textfield",name:"landStatus",
										margin: '5 5 0 0',
										colspan:3,
									//	width:180,
                                        emptyText: "土地性质",fieldLabel: '土地性质',itemId:"ghouse-rent-contract-contract-contractNo2-itemId18"
                                    },
									  {
                                        xtype: "textfield",name:"preSaleNo",
										margin: '5 5 0 0',
										colspan:3,
									//	width:180,
                                        emptyText: "预售许可证号",fieldLabel: '预售许可证号',itemId:"ghouse-rent-contract-contract-contractNo2-itemId19"
                                    },
									  {
                                        xtype: "textfield",name:"fitStatus",
										margin: '5 5 0 0',
										colspan:3,
									//	width:180,
                                        emptyText: "装修状态",fieldLabel: '装修状态',itemId:"ghouse-rent-contract-contract-contractNo2-itemId20"
                                    },
								
									  {
                                        xtype: "textfield",name:"personNum",
										margin: '5 5 0 0',
										colspan:3,
										//width:180,
                                        emptyText: "居住人数",fieldLabel: '居住人数',itemId:"ghouse-rent-contract-contract-contractNo2-itemId22"
                                    },
									 {
                                        xtype: "textfield",name:"isCheckIn",
										margin: '5 5 0 0',
										colspan:3,
										//width:180,
                                        emptyText: "是否入住",fieldLabel: '是否入住',itemId:"ghouse-rent-contract-contract-contractNo2-itemId21"
                                    },
									
									  {
                                        xtype: "textfield",name:"rentStartDate",
										margin: '5 5 0 0',
										colspan:3,
										//width:180,
                                        emptyText: "出租开始时间",fieldLabel: '出租开始时间',itemId:"ghouse-rent-contract-contract-contractNo2-itemId24"
                                    },
									  {
                                        xtype: "textfield",name:"isRent",
										margin: '5 5 0 0',
										colspan:3,
										//width:180,
                                        emptyText: "是否出租",fieldLabel: '是否出租',itemId:"ghouse-rent-contract-contract-contractNo2-itemId23"
                                    },
									  {
                                        xtype: "textfield",name:"rentEndDate",
										margin: '5 5 0 0',
										colspan:3,
										//width:180,
                                        emptyText: "出租结束时间",fieldLabel: '出租结束时间',itemId:"ghouse-rent-contract-contract-contractNo2-itemId25"
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
										height:200,
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