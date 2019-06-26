/**
 * 新增产权人
 *
 * yadong
 */
Ext.define('app.platform.lshc.view.regi.manage.RightEntWin', {
    extend: 'Ext.Window',
    modal: true,
    requires:[],
    width:695,
    height:255,
    title:'添加人口信息',
    layout:"fit",
    border:true,
    initComponent: function () {
        var me = this;
        Ext.apply(me, {
            items: [
                {
                    xtype:'form',
                    itemId:"rightEntItemId",
                    margin:'20 0 0 30',
                    layout: {
                        type: 'table',
                        columns: 2,colspan:1
                    },
                    items:[
                        {text: 'id', dataIndex: 'id',hidden:true},
                        { xtype: "textfield",dataIndex: 'isNew',name:"isNew",value:"1",hidden:true},
                        {
                            xtype: "textfield", labelAlign:"right",
                            emptyText: "姓名", allowBlank: false, fieldLabel: '姓名',  width:300,
                            labelWidth: 90, margin: '11 0 0 0',name:'entName'
                        },
						 {
                            xtype: "textfield", labelAlign:"right",
                            emptyText: "证件号码", allowBlank: false, fieldLabel: '证件号码',  width:300,
                            labelWidth: 90, margin: '11 0 0 0',name:'idNo',vtype:"idcard"
                        },
                        {
                            xtype:'xcombobox',
                            labelAlign:"right",
                            itemId:'sexItemId',
                            fieldLabel:'性别',
                            emptyText:'全部',
                            name:"sex",
                            editable:false,
                            width:300,
                            labelWidth: 90,
                            margin: '11 0 0 0',
                            triggerAction:'all',
                            dataSourceUrl:app.platform.lshc.view.base.RequestUtils.url('/basic/getDictionaryListName?type=GENDER')
                        },

                        {
                            xtype:'xcombobox',
                            labelAlign:"right",
                            itemId:'entTypeItemId',
                            fieldLabel:'人员类别',
                            emptyText:'全部',
                            name:"entType",
                            editable:false,
                            width:300,
                            labelWidth: 90,
                            margin: '11 0 0 0',
                            triggerAction:'all',
                            dataSourceUrl:app.platform.lshc.view.base.RequestUtils.url('/basic/getDictionaryListName?type=ENT_TYPE')
                        },

                        {
                            xtype:'xcombobox',
                            labelAlign:"right",
                            itemId:'entNationItemId',
                            fieldLabel:'民族',
                            emptyText:'全部',
                            name:"entNation",
                            editable:false,
                            width:300,
                            labelWidth: 90,
                            margin: '11 0 0 0',
                            triggerAction:'all',
                            dataSourceUrl:app.platform.lshc.view.base.RequestUtils.url('/basic/getDictionaryListName?type=ENT_NATION')
                        },
                        {
                            xtype:'xcombobox',
                            labelAlign:"right",
                            itemId:'entNativeItemId',
                            fieldLabel:'籍贯',
                            emptyText:'全部',
                            name:"entNative",
                            editable:false,
                            width:300,
                            labelWidth: 90,
                            margin: '11 0 0 0',
                            triggerAction:'all',
                            dataSourceUrl:app.platform.lshc.view.base.RequestUtils.url('/basic/getDictionaryListName?type=ENT_NATIVE')
                        },
                        {
                            xtype: "textfield", labelAlign:"right",
                            emptyText: "联系电话", allowBlank: false, fieldLabel: '联系电话',width:300,
                            labelWidth: 90, margin: '11 0 0 0',name:'tel'
                        },
                        {
                            xtype:'xcombobox',
                            labelAlign:"right",
                            itemId:'marriageStatusItemId',
                            fieldLabel:'婚姻状况',
                            emptyText:'全部',
                            name:"marriageStatus",
                            editable:false,
                            width:300,
                            labelWidth: 90,
                            margin: '11 0 0 0',
                            triggerAction:'all',
                            dataSourceUrl:app.platform.lshc.view.base.RequestUtils.url('/basic/getDictionaryListName?type=MARRIAGE_STATUS')
                        }

                    ]
                }
            ],
            buttons:[
                {
                    text: "确认", glyph: 0xf0a4,
                    listeners:{
                        click:function(){
                            var formView = me.queryById("rightEntItemId").form;
                            if (me.valideFrom()) {
                                var data = formView.getValues();
                                me.residentStore.add(data);
                                me.close();
                            }
                        }
                    }
                },{
                    text:'取消',glyph: 0xf00d,handler:function()
                    {
                        me.close();
                    }
                }
            ]
        });
        me.callParent(arguments);

    },
    valideFrom:function(){
        var me = this;

        var formView = me.queryById("rightEntItemId").form;
        var data = formView.getValues();
        if(null == data.entName || "" == data.entName){
            Ext.Msg.alert("提示","请输入姓名");
            return false;
        }
        if(null == data.sex || "" == data.sex){
            Ext.Msg.alert("提示","请输入性别！");
            return false;
        }
        if(null == data.idNo || "" == data.idNo){
            Ext.Msg.alert("提示","请输入证件号码！");
            return false;
        }else{
            if(me.CheckIdentityCode(data.idNo,'0-100')!=0){
                Ext.Msg.alert('提示',me.CheckIdentityCode(data.idNo));
                return false;
            }
        }
		  if(null == data.entNation || "" == data.entNation){
            Ext.Msg.alert("提示","请输入民族！");
            return false;
        }
		  if(null == data.entType || "" == data.entType){
            Ext.Msg.alert("提示","请输入人员类别！");
            return false;
        }
		  if(null == data.entNative || "" == data.entNative){
            Ext.Msg.alert("提示","请输入籍贯！");
            return false;
        }
        if(null == data.tel || "" == data.tel){
            Ext.Msg.alert("提示","请输入联系电话！");
            return false;
        }
       if(null == data.marriageStatus || "" == data.marriageStatus){
            Ext.Msg.alert("提示","请输入婚姻状况！");
            return false;
        }
        return true;

    },
    CheckIdentityCode:function(code, rangeAge) {
        var cityArr = { 11: "北京", 12: "天津", 13: "河北", 14: "山西", 15: "内蒙古", 21: "辽宁", 22: "吉林", 23: "黑龙江", 31: "上海", 32: "江苏", 33: "浙江", 34: "安徽", 35: "福建", 36: "江西", 37: "山东", 41: "河南", 42: "湖北", 43: "湖南", 44: "广东", 45: "广西", 46: "海南", 50: "重庆", 51: "四川", 52: "贵州", 53: "云南", 54: "西藏", 61: "陕西", 62: "甘肃", 63: "青海", 64: "宁夏", 65: "新疆" }//, 71: "台湾", 81: "香港", 82: "澳门", 91: "国外"

        if (code.length == 0 || code == null || code == undefined) {
            return "0";
        }
        var info = ""
        reg = /(^\d{15}$)|(^\d{17}([0-9]|X)$)/i;
        if (!reg.test(code)) {
            return "产权身份证格式不正确！";
        }
        code = code.replace(/x$/i, "a");
        if (cityArr[parseInt(code.substr(0, 2))] == null) {
            return "产权身份证格式不正确！";
        }
        birthDay = code.substr(6, 4) + "-" + Number(code.substr(10, 2)) + "-" + Number(code.substr(12, 2));
        var d = new Date(birthDay.replace(/-/g, "/"));
        if (birthDay != (d.getFullYear() + "-" + (d.getMonth() + 1) + "-" + d.getDate())) {
            return "产权身份证格式不正确！";
        }
        return this.GetAge(birthDay, rangeAge);
        //alert(cityArr[parseInt(code.substr(0, 2))] + "," + birthDay + "," + (code.substr(16, 1) % 2 ? "男" : "女") + " 年龄：" + age);
    },
    /*
     * 年龄检测是否合法
     * birthDay:出生年月[格式：1999-03-15] rangeAge：年龄范围[格式：25-55]
     * 返回值 为空合法 ，不为空则验证不通过
     */
    GetAge:function(birthDay, rangeAge) {
        if (rangeAge == null || rangeAge.indexOf("-") == -1)
            return "";
        var msg = "";
        var nowDate = new Date();
        var bir = new Date(birthDay.split('-')[0], birthDay.split('-')[1], birthDay.split('-')[2]);var age = parseInt(Math.abs(nowDate - bir) / 1000 / 60 / 60 / 24 / 365);//年龄：天数/365
        var min = 0;
        var max = 0;
        try {
            min = parseInt(rangeAge.split('-')[0]);
            max = parseInt(rangeAge.split('-')[1]);
        } catch (e) { }
        if ((min != 0 || max != 0) && (age < min || age > max))
            msg = "产权身份证格式不正确年龄必须满" + min + "~" + max + "周岁！";
        return msg;
    }
})