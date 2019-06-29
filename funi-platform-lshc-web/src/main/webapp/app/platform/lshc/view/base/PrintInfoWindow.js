/**
 * 重写报表打印类的_getPrintUrl函数
 */
Ext.define("Funi.biz.Printer", {
    extend: 'Funi.biz.Printer',
    alias: "widget.frprint",
    _getPrintUrl: function (reportlet, encode) {
        var me = this;
        me.webReportHost = Funi.core.Context.path("WebReport", "/ReportServer");
        if (me.webReportHost.indexOf("localhost") != -1 ||
            me.webReportHost.indexOf("127.0.0.1") != -1) {
            me.webReportHost = "http://localhost:8075/WebReport/ReportServer";
        }
        return (me.webReportHost + "?reportlets=")
            + ( encode ? encodeURI(JSON.stringify(reportlet)) : JSON.stringify(reportlet))
            + ("&__showtoolbar__=" + !me.config.hidePrintToolbar + "&__cumulatepagenumber__=" + me.config.cumulatePageNumber);

    }
})
Ext.define('app.platform.lshc.view.base.PrintInfoWindow', {
    extend: 'Ext.window.Window',
    xtype: 'lshc-base-printInfoWindow',
    title: '<span style="color:black;font-size:14px;height:100px;"><b>选择打印报表</b></span>',
    width: 360,
    height:250,
    floating:true,
    modal:true,
    closeAction:'hide',
    config:{
        bizTypeId:-1,//0:主界面打印 1:房屋列表打印
        roleCode:'',
        moduleCode:'',
        logicRegionCode:'',//片区编码,eg:"123,345,456"
        bizId:''//普查Id
    },

    layout: {
        padding: '10 0 0 0',
        type: 'vbox',
        align: 'stretch'
    },

    //====构造方法========================================================================
    constructor: function (config) {
        var me = this;
        config = config || {};
        Ext.applyIf(config, me.config);
        this.callParent(arguments);
    },
    initStore:function(){
        var me = this
        var record = {"rpName":"Lshc_PuChaTongJi.cpt","rpInChinese":"调查统计表"};
        if(me.config.bizTypeId == 1){
            var store = me.down("gridpanel").getStore();
            store.add(record);
        }
    },
    // ====视图构建========================================================================
    initComponent: function () {
        var me = this;
        //必须输入重要参数
        var checkFlag = me._checkInputParam();
        if(!checkFlag){
            return checkFlag;
        }
        //校验输入参数
        var printInfoStore = Ext.create("Funi.data.ListStore",{
            //url:"/",
            fields:[
                {type:"string",name:"rpName"},
                {type:"string",name:"rpInChinese"}
            ],
            // data:[
            //     {"rpName":"rpName","rpInChinese":"调查统计表"}
            // ],
            load:false,
            pageSize:10
        });
        Ext.apply(me, {
            items: [
                {
                    xtype: 'gridpanel',
                    multiColumnSort: true,//多行排序
                    store: printInfoStore,
                    viewConfig: {
                        enableTextSelection: true
                    },
                    selModel: {
                        mode: "SIMPLE"
                    },
                    selType: "checkboxmodel",
                    columnLines: true,
                    plugins: 'gridfilters',
                    columns: [
                        {text: '名称', flex:1, dataIndex: 'rpName',hidden:true},
                        {text: '名称', flex:1, dataIndex: 'rpInChinese'}
                    ]
                }
            ],
            buttons: [
                {
                    xtype: 'frprint',
                    cumulatePageNumber:false,
                    itemId: 'frprintItemid',
                    hidePrintToolbar: false,
                    listeners: {
                        printready: function ($cmp) {
                            me.query("#printBtnItemid")[0].setDisabled(false);
                        }
                    }
                },
                {
                    xtype: 'button',
                    itemId: 'printBtnItemid',
                    disabled: true,
                    text: '打印',
                    handler: function ($cmp) {
                        var selList = me.down('gridpanel').getSelection();
                        if(selList.length < 1){
                            Ext.Msg.alert('错误', '无选择打印的报表项');
                            return;
                        }else{
                            me.query("#printBtnItemid")[0].setDisabled(true);
                            var url = [];

                            var printer = me.query("#frprintItemid")[0];

                            for(var i = 0; i < selList.length; i++){
                                var name = selList[i].data.rpName;
                                url.push({'reportlet':name,'bizId':me.config.bizId});
                            }

                            var printer = me.query("#frprintItemid")[0];
                            printer.print({
                                type:'doURLPDFPrint',
                                reportlet: url
                            });
                            me.query("#printBtnItemid")[0].setDisabled(false);
                        }
                    }
                },
                {
                    text: '<span style="color:black;font-size:12px;">取消</span>',
                    height: 30, width: 45,
                    style: {background: 'white', border: '1px solid lightgray'},
                    handler: function () {
                        me.close();
                    }
                }
            ]
        })
        me.callParent(arguments);
    },
    //检查传入参数
    _checkInputParam:function(){
        var me = this;
        if(Ext.isEmpty(me.config.bizId)){
            Ext.Msg.alert('提示', '参数缺失，请检查数据完整性!');
            return false;
        }
        return true;
    }
});