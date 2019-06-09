/**
 * 新增产权人
 *
 * yadong
 */
Ext.define('app.platform.lshc.view.regi.manage.RightEntWin', {
    extend: 'Ext.Window',
    modal: true,
    requires:[],
    width:420,
    height:280,
    title:'添加产权人',
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
                    items:[
                        {text: 'id', dataIndex: 'id',hidden:true},
                        {text: 'houIs', dataIndex: 'houseId',hidden:true},
                        {
                            xtype: "textfield", labelAlign:"right",
                            emptyText: "产权证书编号", allowBlank: false, fieldLabel: '产权证书编号',  width:300,
                            labelWidth: 90, margin: '11 0 0 0',name:'propertyCertificateNo'
                        },
                        {
                            xtype: "textfield", labelAlign:"right",
                            emptyText: "产权人姓名", allowBlank: false, fieldLabel: '产权人姓名',  width:300,
                            labelWidth: 90, margin: '11 0 0 0',name:'propertyUserName'
                        },
                        {
                            xtype: "textfield", labelAlign:"right",
                            emptyText: "产权人身份证", allowBlank: false, fieldLabel: '产权人身份证',width:300,
                            labelWidth: 90, margin: '11 0 0 0',name:'propertyUserIdNumber',vtype:"idcard"
                        },
                        {
                            xtype: "textfield", labelAlign:"right",width:300,
                            emptyText: "产权人联系电话", allowBlank: false, fieldLabel: '联系电话',
                            labelWidth: 90, margin: '11 0 0 0',name:'propertyUserTel'
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
        if(null == data.propertyCertificateNo || "" == data.propertyCertificateNo){
            Ext.Msg.alert("提示","请输入产权证书编号");
            return false;
        }
        if(null == data.propertyUserName || "" == data.propertyUserName){
            Ext.Msg.alert("提示","请输入产权人姓名！");
            return false;
        }
        if(null == data.propertyUserIdNumber || "" == data.propertyUserIdNumber){
            Ext.Msg.alert("提示","请输入产权人身份证！");
            return false;
        }else{
            if(me.CheckIdentityCode(data.propertyUserIdNumber,'0-100')!=0){
                Ext.Msg.alert('提示',me.CheckIdentityCode(data.propertyUserIdNumber));
                return false;
            }
        }

        if(null ==  data.propertyUserTel || "" == data.propertyUserTel){
            Ext.Msg.alert("提示","请输入产权人联系电话！");
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