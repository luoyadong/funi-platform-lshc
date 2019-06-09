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
    getData:function(){
        var form = this.down("form");
        if(form.isValid()){
            return {"contract":form.getValues()};
        }
        throw {message:"请填写合同信息"};
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
                                        xtype: "textfield",name:"contractNo",
                                        emptyText: "项目(小区)名称",fieldLabel: '项目(小区)名称',readOnly:true ,itemId:"ghouse-rent-contract-contract-contractNo-itemId"
                                    }, {
                                        xtype: "numberfield", hideTrigger: true, hideTrigger: true,name:"bulidArea",fieldLabel: '物业管理单位',readOnly:true
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
                                        xtype: "textfield",name:"contractNo",
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
                                    columns: 11
                                },
                                items: [
										{
										xtype: 'combobox',
										fieldLabel: '实际地址',
										margin: '0 0 5 40',
										emptyText: '全部',
										store: mStore,
										editable: false,
										valueField: 'value',
										itemId:'isVacant',
										displayField: 'name',
										labelWidth: 60,
										width: 130
									},
									{
										xtype: 'combobox',
										emptyText: '全部',
										store: mStore,
										editable: false,
										valueField: 'value',
										itemId:'isVacant2',
										displayField: 'name',
										width: 65
									},
									{
										xtype: 'combobox',
										emptyText: '全部',
										store: mStore,
										editable: false,
										valueField: 'value',
										itemId:'isVacant3',
										displayField: 'name',
										width: 65
									},
									{
										xtype: 'combobox',
										emptyText: '全部',
										margin: '0 0 5 20',
										store: mStore,
										editable: false,
										valueField: 'value',
										itemId:'isVacant4',
										displayField: 'name',
										width: 65
									},
									{xtype: 'label', text: '号',  margin: '0 10 0 10'},
									{
										xtype: 'combobox',
										emptyText: '全部',
										store: mStore,
										editable: false,
										valueField: 'value',
										itemId:'isVacant5',
										displayField: 'name',
										width: 65
									},
									{xtype: 'label', text: '栋',  margin: '0 0 0 10'},
									{
										xtype: 'combobox',
										emptyText: '全部',
										store: mStore,
										editable: false,
										valueField: 'value',
										itemId:'isVacant6',
										displayField: 'name',
										margin: '0 5 5 -15',
										width: 65
									},
									{xtype: 'label', text: '单元',  margin: '0 5 5 -55'},
									{
										xtype: 'combobox',
										emptyText: '全部',
										store: mStore,
										editable: false,
										valueField: 'value',
										itemId:'isVacant7',
										displayField: 'name',
										margin: '0 0 5 -45',
										width: 65
									},
									{xtype: 'label', text: '层',  margin: '0 5 0 -15'},
									  {
                                        xtype: "textfield",name:"contractNo",
										//columnWidth:11,
										width:510,
										colspan:11,
                                        emptyText: "产权证书地址",fieldLabel: '产权证书地址',itemId:"ghouse-rent-contract-contract-contractNo2-itemId2"
                                    },
									  {
                                        xtype: "textfield",name:"contractNo",
										//width:180,
										colspan:6,
                                        emptyText: "建筑面积",fieldLabel: '建筑面积',itemId:"ghouse-rent-contract-contract-contractNo2-itemId3"
                                    },
									  {
                                        xtype: "textfield",name:"contractNo",
										colspan:5,
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
										valueField: 'value',
										itemId:'isVacant10',
										displayField: 'name',
										margin: '0 0 0 40',
										width: 130
									},
									{xtype: 'label', text: '室',  margin: '0 5 0 5'},
									{
										xtype: 'combobox',
										emptyText: '全部',
										store: mStore,
										editable: false,
										valueField: 'value',
										itemId:'isVacant11',
										displayField: 'name',
										width: 60,
										margin: '0 5 0 -40'
									},
									{xtype: 'label', text: '厅',  margin: '0 5 0 -40',colspan:3},
									{
                                        xtype: "textfield",name:"contractNo",
										margin: '5 5 0 0',
										colspan:5,
										//width:180,
                                        emptyText: "是否办理产权",fieldLabel: '是否办理产权',itemId:"ghouse-rent-contract-contract-contractNo2-itemId12"
                                    },

									  {
                                        xtype: "textfield",name:"contractNo",
										margin: '5 5 0 0',
										colspan:6,
										//width:180,
                                        emptyText: "产权证号",fieldLabel: '产权证号',itemId:"ghouse-rent-contract-contract-contractNo2-itemId13"
                                    },
									  {
                                        xtype: "textfield",name:"contractNo",
										margin: '5 5 0 0',
										colspan:5,
									//	width:180,
                                        emptyText: "房屋建成年份",fieldLabel: '房屋建成年份',itemId:"ghouse-rent-contract-contract-contractNo2-itemId14"
                                    },
									  {
                                        xtype: "textfield",name:"contractNo",
										margin: '5 5 0 0',
										colspan:6,
										//width:180,
                                        emptyText: "房屋类别",fieldLabel: '房屋类别',itemId:"ghouse-rent-contract-contract-contractNo2-itemId15"
                                    },
									  {
                                        xtype: "textfield",name:"contractNo",
										margin: '5 5 0 0',
										colspan:5,
										//width:180,
                                        emptyText: "房屋结构",fieldLabel: '房屋结构',itemId:"ghouse-rent-contract-contract-contractNo2-itemId16"
                                    },
									  {
                                        xtype: "textfield",name:"contractNo",
										margin: '5 5 0 0',
										colspan:6,
										//width:180,
                                        emptyText: "房屋用途",fieldLabel: '房屋用途',itemId:"ghouse-rent-contract-contract-contractNo2-itemId17"
                                    },
									  {
                                        xtype: "textfield",name:"contractNo",
										margin: '5 5 0 0',
										colspan:5,
									//	width:180,
                                        emptyText: "土地性质",fieldLabel: '土地性质',itemId:"ghouse-rent-contract-contract-contractNo2-itemId18"
                                    },
									  {
                                        xtype: "textfield",name:"contractNo",
										margin: '5 5 0 0',
										colspan:6,
									//	width:180,
                                        emptyText: "预售许可证号",fieldLabel: '预售许可证号',itemId:"ghouse-rent-contract-contract-contractNo2-itemId19"
                                    },
									  {
                                        xtype: "textfield",name:"contractNo",
										margin: '5 5 0 0',
										colspan:5,
									//	width:180,
                                        emptyText: "装修状态",fieldLabel: '装修状态',itemId:"ghouse-rent-contract-contract-contractNo2-itemId20"
                                    },
								
									  {
                                        xtype: "textfield",name:"contractNo",
										margin: '5 5 0 0',
										colspan:6,
										//width:180,
                                        emptyText: "居住人数",fieldLabel: '居住人数',itemId:"ghouse-rent-contract-contract-contractNo2-itemId22"
                                    },
									 {
                                        xtype: "textfield",name:"contractNo",
										margin: '5 5 0 0',
										colspan:5,
										//width:180,
                                        emptyText: "是否入住",fieldLabel: '是否入住',itemId:"ghouse-rent-contract-contract-contractNo2-itemId21"
                                    },
									
									  {
                                        xtype: "textfield",name:"contractNo",
										margin: '5 5 0 0',
										colspan:6,
										//width:180,
                                        emptyText: "出租开始时间",fieldLabel: '出租开始时间',itemId:"ghouse-rent-contract-contract-contractNo2-itemId24"
                                    },
									  {
                                        xtype: "textfield",name:"contractNo",
										margin: '5 5 0 0',
										colspan:5,
										//width:180,
                                        emptyText: "是否出租",fieldLabel: '是否出租',itemId:"ghouse-rent-contract-contract-contractNo2-itemId23"
                                    },
									  {
                                        xtype: "textfield",name:"contractNo",
										margin: '5 5 0 0',
										colspan:11,
										//width:180,
                                        emptyText: "出租结束时间",fieldLabel: '出租结束时间',itemId:"ghouse-rent-contract-contract-contractNo2-itemId25"
                                    }
                                ]
                            }
							]
                        },
						{
							title:'人口信息',
							itemId:'tab3',
							xtype:"lshc-regi-manage-approve",
							overflowY:'auto',
							disabled:false,
							width:"100%",
							height:200,
						}

					]
					}
				]
            });
        this.callParent(arguments);
    }
});