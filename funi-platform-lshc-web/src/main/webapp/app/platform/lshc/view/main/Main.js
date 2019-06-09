
Ext.define('app.platform.lshc.view.main.Main', {
    extend: 'Ext.Viewport',
    layout: 'border',
    uses : ['app.platform.lshc.view.ux.ButtonTransparent'],

    config: {
        username:'',
        orgName:''
    },
    initComponent: function () {
        var me = this;
        Ext.applyIf(this, {
            name:'bordercontainer',
            layout: 'border',
            items: [
                { region: "north",itemId:'maintop', height: 50, margin: '0 0 0 0', padding: '0px', collapsible: false, xtype: 'toolbar', border: false,
                    defaults : {
                        xtype : 'buttontransparent'
                    },
                    items: [{
                        padding:'0 0 0 30',
                        xtype: 'component',
                        autoEl: {tag: 'div',cls:'logo'}
                    },'->',  '->', '->', '->',{
                        text:'帮助',
                        glyph : 0xf059
                    },{
                        text : '关于',
                        glyph : 0xf06a
                    },{
                        text : '设置',
                        glyph : 0xf0c9
                    },{
                        text:'注销',
                        glyph: 0xf011,
                        handler: function () {
                            me.logout();
                        }
                    },{
                        glyph : 0xf102,
                        tooltip : '隐藏顶部和底部区域',
                        handler: function () {
                            me._hiddenTopBottom();
                        }
                    }]

                },
                {
                    itemId:'mainbottom',
                    xtype : 'toolbar',
                    region : 'south',
                    defaults:{
                        xtype:'buttontransparent'
                    },
                    items : [{
                        xtype:'label',
                        text:'当前用户:'
                    }, {
                        itemId:'username',
                        text:''
                    },{
                        xtype:'label',
                        text:'所属机构:'
                    },{
                        itemId:'orgName',
                        text:''
                    }]
                },
                { region: "west", border: true, padding: '0px', split: true, margin: '0', collapsible: true, collapsed: false,
                    xtype: 'treepanel',
                    title: '功能菜单',
                    glyph:0xf0c9,
                    width: 250,
                    store:this.__createMenuStore(),
                    rootVisible: false,
                    listeners:{
                        scope:this,
                        itemclick: function (source, record, item, index, e, eOpts) {
                            me.__openIframeView(record.data.text, record.data.url);
                        }
                    }
                },{
                    itemId: 'centerBox', region: "center", layout: 'fit', border: true, margin: '0 0 0 0', collapsible: false,
                    xtype: 'tabpanel',
                    listeners:{
                        'tabchange':function(tabPanel, newCard, oldCard, eOpts){
                        }
                    }
                }

            ]
        });
        this.callParent(arguments);
        me.query('#username')[0].text = me.config.username;
        me.query('#orgName')[0].text = me.config.orgName;
    },

    __createMenuStore: function () {
        // var result = Ext.appContext.invokeService("resource/findSystemTree", {});
        //  var jsonStr = '{"attributes":{"username":"ghouse","orgName":{"children":null,"dm":"42161","id":"2EF040D5129101A8E053AC1DFBCE01A8","mc":"大同市房管局"}},"message":"获取数据成功","result":{"appCode":"","children":[{"appCode":"","children":[{"appCode":"","children":[{"appCode":"","children":[],"groupCode":"","id":"f73ce1e6-3dfa-4d30-96fa-201136831f10","name":"公房管理","nodeLevel":"","parentId":"0174cc40-34d1-4767-a99d-f766382389bf","url":"/ghouse/getView?viewName=app.platform.ghouse.view.house.manage.HouseMainView"},{"appCode":"","children":[],"groupCode":"","id":"e72be311-09a6-4713-8464-1ed8cdcfb80c","name":"小区管理","nodeLevel":"","parentId":"0174cc40-34d1-4767-a99d-f766382389bf","url":"/ghouse/getView?viewName=app.platform.ghouse.view.house.community.CommunityMainView"},{"appCode":"","children":[],"groupCode":"","id":"8558f3bc-083c-4f06-9d0d-47ddb7b3e13e","name":"公房分布图","nodeLevel":"","parentId":"0174cc40-34d1-4767-a99d-f766382389bf","url":"/ghouse/getView?viewName=app.platform.ghouse.view.house.map.MapMainView"},{"appCode":"","children":[],"groupCode":"","id":"6def5268-c75a-44c8-a193-a787640a147a","name":"综合查询","nodeLevel":"","parentId":"0174cc40-34d1-4767-a99d-f766382389bf","url":"/ghouse/getView?viewName=app.platform.ghouse.view.house.query.QueryMainView"}],"groupCode":"","id":"0174cc40-34d1-4767-a99d-f766382389bf","name":"房屋管理","nodeLevel":"","parentId":"2f8d7bca-d350-4d8c-af2e-6971eb9fbac3","url":""},{"appCode":"","children":[{"appCode":"","children":[],"groupCode":"","id":"26ad080b-99c1-4f46-b032-eb46c2b1bc32","name":"合同管理","nodeLevel":"","parentId":"9e71e805-9990-47f3-9ec2-9045d56ed3a4","url":"/ghouse/getView?viewName=app.platform.ghouse.view.rent.contract.ContractMainView"},{"appCode":"","children":[],"groupCode":"","id":"7d5c975d-2ee1-4096-a7b6-f25e0d8ecefc","name":"合同待审批","nodeLevel":"","parentId":"9e71e805-9990-47f3-9ec2-9045d56ed3a4","url":"/ghouse/getView?viewName=app.platform.ghouse.view.rent.contract.ToDoContractMainView"},{"appCode":"","children":[],"groupCode":"","id":"68dba702-eac6-4b9e-85e1-caa4bf20f6e8","name":"合同已审批","nodeLevel":"","parentId":"9e71e805-9990-47f3-9ec2-9045d56ed3a4","url":"/ghouse/getView?viewName=app.platform.ghouse.view.rent.contract.DoneContractMainView"},{"appCode":"","children":[],"groupCode":"","id":"de4b5a36-84be-4e58-a2ef-27056628eff7","name":"住户管理","nodeLevel":"","parentId":"9e71e805-9990-47f3-9ec2-9045d56ed3a4","url":"/ghouse/getView?viewName=app.platform.ghouse.view.rent.inhabitant.InhabitantMainView"},{"appCode":"","children":[],"groupCode":"","id":"10cd378a-93f1-4802-b8b5-a9304d16cbd5","name":"年审通知","nodeLevel":"","parentId":"9e71e805-9990-47f3-9ec2-9045d56ed3a4","url":"/ghouse/getView?viewName=app.platform.ghouse.view.rent.check.CheckMainView"}],"groupCode":"","id":"9e71e805-9990-47f3-9ec2-9045d56ed3a4","name":"租赁管理","nodeLevel":"","parentId":"2f8d7bca-d350-4d8c-af2e-6971eb9fbac3","url":""},{"appCode":"","children":[{"appCode":"","children":[],"groupCode":"","id":"40ee11ce-c6ec-4001-b0f1-6bb6186a742e","name":"租金管理","nodeLevel":"","parentId":"f33a5eb8-8d23-4b23-ac95-331c09accdf3","url":"/ghouse/getView?viewName=app.platform.ghouse.view.charge.rent.RentMainView"},{"appCode":"","children":[],"groupCode":"","id":"31ef23fb-6b2f-43c2-9bab-67f7a790af29","name":"历史收费记录","nodeLevel":"","parentId":"f33a5eb8-8d23-4b23-ac95-331c09accdf3","url":"/ghouse/getView?viewName=app.platform.ghouse.view.charge.his.HisMainView"},{"appCode":"","children":[],"groupCode":"","id":"eeeb2912-c26d-4853-890a-3510c2dd0bf0","name":"欠费催缴","nodeLevel":"","parentId":"f33a5eb8-8d23-4b23-ac95-331c09accdf3","url":"/ghouse/getView?viewName=app.platform.ghouse.view.charge.arrearage.ArrearageMainView"},{"appCode":"","children":[],"groupCode":"","id":"4f0b8ff7-1b8f-4848-9079-e1ceff9cef46","name":"保证金管理","nodeLevel":"","parentId":"f33a5eb8-8d23-4b23-ac95-331c09accdf3","url":"/ghouse/getView?viewName=app.platform.ghouse.view.charge.deposit.DepositMainView"}],"groupCode":"","id":"f33a5eb8-8d23-4b23-ac95-331c09accdf3","name":"收费管理","nodeLevel":"","parentId":"2f8d7bca-d350-4d8c-af2e-6971eb9fbac3","url":""},{"appCode":"","children":[{"appCode":"","children":[],"groupCode":"","id":"11656888-2607-42a0-a826-58d9fed6603a","name":"维修管理 ","nodeLevel":"","parentId":"dc75d566-5993-4f41-abfa-3db1dce3e74b","url":"/ghouse/getView?viewName=app.platform.ghouse.view.maintanence.manage.MaintanMainView"},{"appCode":"","children":[],"groupCode":"","id":"eae41b44-e485-4ea6-856d-61c5c4f99d1e","name":"维修待审批","nodeLevel":"","parentId":"dc75d566-5993-4f41-abfa-3db1dce3e74b","url":"/ghouse/getView?viewName=app.platform.ghouse.view.maintanence.manage.ToDoMaintanMainView"},{"appCode":"","children":[],"groupCode":"","id":"5cc0b436-47f8-4349-ae59-f1384f4a21d9","name":"维修已审批","nodeLevel":"","parentId":"dc75d566-5993-4f41-abfa-3db1dce3e74b","url":"/ghouse/getView?viewName=app.platform.ghouse.view.maintanence.manage.DoneMaintanMainView"}],"groupCode":"","id":"dc75d566-5993-4f41-abfa-3db1dce3e74b","name":"维修管理","nodeLevel":"","parentId":"2f8d7bca-d350-4d8c-af2e-6971eb9fbac3","url":""},{"appCode":"","children":[{"appCode":"","children":[],"groupCode":"","id":"5af30367-9fdc-4209-88d4-3960623f940f","name":"合同模板设置","nodeLevel":"","parentId":"708235cd-eda3-4220-88bf-a3ffd7639464","url":"/ghouse/getView?viewName=app.platform.ghouse.view.setting.contract.ContractSettingView"},{"appCode":"","children":[],"groupCode":"","id":"8bef7602-7abe-4d11-a87f-0dbf8b3bf15d","name":"票据模板设置","nodeLevel":"","parentId":"708235cd-eda3-4220-88bf-a3ffd7639464","url":"/ghouse/getView?viewName=app.platform.ghouse.view.setting.bill.BillSettingView"},{"appCode":"","children":[],"groupCode":"","id":"420f66ec-064a-4af3-95bb-ad8c2b42fbb9","name":"业务参数设置","nodeLevel":"","parentId":"708235cd-eda3-4220-88bf-a3ffd7639464","url":"/ghouse/getView?viewName=app.platform.ghouse.view.setting.biz.BizSettingView"},{"appCode":"","children":[],"groupCode":"","id":"fe41fc67-12e6-44b8-8cc7-0e83a53dbbca","name":"档案存放点设置","nodeLevel":"","parentId":"708235cd-eda3-4220-88bf-a3ffd7639464","url":"/ghouse/getView?viewName=app.platform.ghouse.view.setting.doc.DocSettingView"}],"groupCode":"","id":"708235cd-eda3-4220-88bf-a3ffd7639464","name":"系统设置","nodeLevel":"","parentId":"2f8d7bca-d350-4d8c-af2e-6971eb9fbac3","url":""}],"groupCode":"","id":"2f8d7bca-d350-4d8c-af2e-6971eb9fbac3","name":"直管公房系统","nodeLevel":"","parentId":"1","url":""}],"groupCode":"","id":"1","name":"智慧房产云平台","nodeLevel":"","parentId":"0","url":""},"status":"200"}';
        var jsonStr = '{"attributes":{"username":"lshc_0","orgName":{"children":null,"dm":"42161","id":"2EF040D5129101A8E053AC1DFBCE01A8","mc":"大同市房管局"}},"message":"获取数据成功","result":{"appCode":"","children":[{"appCode":"","children":[{"appCode":"","children":[{"appCode":"","children":[],"groupCode":"","id":"f73ce1e6-3dfa-4d30-96fa-201136831f10","name":"登记管理","nodeLevel":"","parentId":"0174cc40-34d1-4767-a99d-f766382389bf","url":"/lshc/getView?viewName=app.platform.lshc.view.regi.manage.RegiMainView"},{"appCode":"","children":[],"groupCode":"","id":"e72be311-09a6-4713-8464-1ed8cdcfb80c","name":"待办件","nodeLevel":"","parentId":"0174cc40-34d1-4767-a99d-f766382389bf","url":"/lshc/getView?viewName=app.platform.lshc.view.regi.approve.ToDoRegiMainView"},{"appCode":"","children":[],"groupCode":"","id":"8558f3bc-083c-4f06-9d0d-47ddb7b3e13e","name":"已办件","nodeLevel":"","parentId":"0174cc40-34d1-4767-a99d-f766382389bf","url":"/lshc/getView?viewName=app.platform.lshc.view.regi.approve.DoneRegiMainView"},{"appCode":"","children":[],"groupCode":"","id":"6def5268-c75a-44c8-a193-a787640a147a","name":"综合查询","nodeLevel":"","parentId":"0174cc40-34d1-4767-a99d-f766382389bf","url":"/lshc/getView?viewName=app.platform.lshc.view.regi.query.QueryMainView"}],"groupCode":"","id":"0174cc40-34d1-4767-a99d-f766382389bf","name":"房普查登记","nodeLevel":"","parentId":"2f8d7bca-d350-4d8c-af2e-6971eb9fbac3","url":""}],"groupCode":"","id":"2f8d7bca-d350-4d8c-af2e-6971eb9fbac3","name":"房屋普查","nodeLevel":"","parentId":"1","url":""}],"groupCode":"","id":"1","name":"智慧房产云平台","nodeLevel":"","parentId":"0","url":""},"status":"200"}';
        var result = JSON.parse(jsonStr);
        if (result.status == '200') {
            var me = this;
            var username = result.attributes.username;
            var orgName = result.attributes.orgName.mc;
            me.config.username = username;
            me.config.orgName = orgName;
            var store = Ext.create('Ext.data.TreeStore', {
                root: result.result,
                fields: [{
                    name: 'text',
                    mapping: 'name'
                }]
            });
            return store;
        } else {
            Ext.Msg.alert('错误', result.message);
        }
    },


    logout:function(){

        var action = Ext.create('Ext.form.Basic',this,{
            standardSubmit:true
        })

        action.submit({
            url: '/lshc/j_spring_cas_security_logout',
            clientValidation: false
        });
    },

    _hiddenTopBottom : function() {
        var me  = this;
        // 如果要操纵控件，最好的办法是根据相对路径来找到该控件，用down或up最好，尽量少用getCmp()函数。
        me.getComponent("maintop").hide();
        me.getComponent("mainbottom").hide();
        if (!this.showButton) { // 显示顶部和底部的一个控件，在顶部和底部隐藏了以后，显示在页面的最右上角
            this.showButton = Ext.widget('component', {
                glyph : 0xf103,
                view : this,
                floating : true,
                x : document.body.clientWidth - 32,
                y : 0,
                height : 6,
                width : 26,
                style : 'background-color:blue',
                listeners : {
                    el : {
                        click : function(el) {
                            var c = Ext.getCmp(el.target.id); // 取得component的id值
                            me.getComponent("maintop").show();
                            me.getComponent("mainbottom").show();
                            c.hide();
                        }
                    }
                }
            })
        };
        this.showButton.show();
    },


    /**
     * 打开一个新视图
     * @param viewClsName @type String 视图类名称
     * @param config 视图配置
     * @param onlyOne @type boolean 是否仅允许单实例，默认false
     * @return 打开的视图对象
     */
    __openIframeView: function (viewClsName, url, config) {
        config = config || {};
        //取得视图
        var urlPatterns = url.split("?");
        var view = urlPatterns[1].replace("viewName=", "");
        try {
            Funi.core.Dashboard.newTab({title: viewClsName, view: view});
        } catch (e) {
            Funi.core.Dashboard.error({message: "视图加载错误"});
        }
    },
    findView: function (viewClassName) {
        var result = false;
        Ext.mainFrame.getComponent("centerBox").items.each(function (item, index, length) {
            if (item.title && item.title == viewClassName) {
                result = item;
                return result;
            }
        });
        return result;
    },
    /**
     * 打开一个新视图
     * @param viewClsName @type String 视图类名称
     * @param config 视图配置
     * @param onlyOne @type boolean 是否仅允许单实例，默认false
     * @return 打开的视图对象
     */
    __openView: function (viewClsName, config, onlyOne) {
        config = config || {};
        var newView = {};
        if (onlyOne) {
            var v = Ext.mainFrame.findView(config.text);
            if(v){
                Ext.mainFrame.getComponent("centerBox").remove(v);
            }
            newView = Ext.create(viewClsName, config);
            newView.viewClassName = viewClsName;
        }
        else {
            newView = Ext.create(viewClsName, config);
            newView.viewClassName = viewClsName;
        }
        var tabView = {xtype: 'container', title: config.text, items: [newView], scrollable:true, closable: true, layout: 'fit' };
        var tab = Ext.mainFrame.getComponent("centerBox").add(tabView);
        tab.innerView = newView;
        newView.meOwner = tab;
        Ext.mainFrame.getComponent("centerBox").setActiveTab(tab);
        Ext.mainFrame.doLayout();
        return newView;
    },
    /**
     * 关闭一个视图
     * @param viewClsName @type String 视图类名称
     * @param config 视图配置
     * @param onlyOne @type boolean 是否仅允许单实例，默认false
     * @return 打开的视图对象
     */
    __closeView: function (viewClsName, url, config) {
        config = config || {};
        if (url != '') {
            var v = Ext.mainFrame.findView(viewClsName);
            if (v) {
                Ext.mainFrame.getComponent("centerBox").setActiveTab(v);
                return v;
            } else {
                var urlPatterns = url.split("?");
                var testUrl = urlPatterns[0], view = urlPatterns[1].replace("viewName=", "");
                var tabContainer = Ext.create({
                    xtype: 'container',
                    title: viewClsName,
                    scrollable: false,
                    closable: true,
                    layout: 'fit'
                });
                var tabContentView = Ext.create(view, {title: viewClsName});
                tabContainer.add(tabContentView);
                var tab = Ext.mainFrame.getComponent("centerBox").add(tabContainer);
                Ext.mainFrame.getComponent("centerBox").setActiveTab(tabContainer);
            }
        }
    },
    //打开流程图
    openImg:function(regiid,uuid){
        var me = this;
        var win = Ext.create('app.OpenViewContext').openWindow("app.platform.lshc.view.ux.ImgFile",{blueprintId:regiid,uuid:uuid},{width: 1000, height: 600});
    }
});