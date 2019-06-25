/**
 * 审批
 */
Ext.onReady(function(){
    Ext.QuickTips.init();
    Ext.form.Field.prototype.msgTarget='side';
});
Ext.define('app.platform.lshc.view.regi.manage.ApproveWinView', {
    extend: 'Ext.window.Window',
    xtype: 'lshc-view-regi-ApproveWinView',
    requires:[
        'app.platform.lshc.view.base.RequestUtils'
    ],
    config: {
        parentPanel:null
    },
    constructor: function (config) {
        var me = this;
        config = config || {};
        Ext.applyIf(config, me.config);
        this.callParent(arguments);
    },
    title: '审批',
    width: 600,
    height:320,
    //autoScroll: true,
    resizable: false,
    buttonAlign: 'center',
    modal: true,
    checkSameStatus:function(){

    },
    initCons:function(){
        var me = this
        //任选一条记录的状态，查询出下一个节点的审批结论
        if(null == me.config.serviceNum){
            Ext.MessageBox.alert("温馨提示","没有房屋标识数据，请联系管理员！");
            return;
        }
        var data = app.platform.lshc.view.base.RequestUtils.request({"serviceNum":me.config.serviceNum,"nodeName":me.config.nodeName},"/manage/conclusions");
        console.log("-------audit data---")
        console.log(data)
        console.log(me.config.ids)

        var auditView = me.queryById("auditResultItemId");
        auditView.removeAll();
        var len = data.length;
        for(var i=0;i<len;i++){
            var cRecord = data[i];
            var name = cRecord.name;
            var code = cRecord.code;
            if("结束" == name){
                continue;
            }
            if("退回" == name){
                auditView.add(
                    {   boxLabel: ''+name,
                        name: 'jobResult',
                        inputValue: ''+code,
                        checked: true}
                )
                continue;
            }
            if(i == 0){
                auditView.add(
                    {   boxLabel: ''+name,
                        name: 'jobResult',
                        inputValue: ''+code,
                        checked: true}
                )
            }else{
                auditView.add(
                    {   boxLabel: name,
                        name: 'jobResult',
                        inputValue: code}
                )
            }
        }

        //当前节点名称
        var nodeNameView = me.queryById("lshc-base-worknodepanel-nodeName-itemId");
        nodeNameView.setValue(me.config.nodeName);

    }
    ,
    getData:function(){
        var form = this.down("form");
        if(!form)return{};
        if(form.isValid()){
            var values = form.getValues();
            //var displayVal = this.queryById("auditResultItemId").getRawValue();
            var displayVal = "";
            var radioView = this.queryById("auditResultItemId").items.items;
            if(radioView != null && radioView.length >0){
                for(var j=0;j<radioView.length;j++ ){
                    if(radioView[j].checked){
                        displayVal = radioView[j].boxLabel;
                        break;
                    }
                }
            }
            if(displayVal == ""){
                Ext.MessageBox.alert("温馨提示","没有可用的什么结论！");
                return
            }
            var jobResultId = values.jobResult;
            var jobResult = displayVal;
            return {"ids":this.config.ids,
                "result":jobResult,"jobResultId":jobResultId,"nodeName":this.config.nodeName,"desc":values.jobOpinion};
        }
        throw {message:"请填写审批意见"};
    },
    sumitAudit:function(){
        var me = this;
        var extData = me.getData();
        console.log("-------audit submit---")
        console.log(extData)
        app.platform.lshc.view.base.RequestUtils.post_json(extData,"/undone/batchAuditRegiInfoList",false,false);

        //关闭当前窗口
        me.close();

        //刷新列表
        me.config.parentContainer.initHouseList();
    },
    initComponent: function () {
        var me = this;
        me.on('close', function(){
        });
        Ext.apply(me, {
            xtype:'container',
            items: [
                {
                    xtype: 'form',
                    scope: me,
                    layout: 'column',
                    border: true,
                    width:600,
                    height:320,
                    items: [
                        {
                            xtype: 'panel', layout: 'column', columnWidth: 1, margin: '10 0 0 0',
                            items: [
								{
									/*xtype: 'radiogroup',*/
									xtype : 'fieldcontainer',
									defaultType: 'radiofield',
                                    itemId:'auditResultItemId',
									columnWidth: .9,
									colspan: 2,
									fieldLabel: '审批结论',
									allowBlank:false,
									layout: 'hbox',
									labelWidth: 80,
									margin: '0 0 0 10',
									items:[{ boxLabel: '同意',
										name: 'jobResult',
										inputValue: '1',
										checked: true},{
										boxLabel: '取消',
										name: 'jobResult',
										inputValue: '2'
									}]

								},
                                {
                                    xtype: 'textarea',
                                    itemId: 'jobOpinionItemId',
                                    fieldLabel: '处理意见',
                                    name:"jobOpinion",
                                    margin:'20 0 0 0',
                                    columnWidth: .9,
                                    height:180,
                                    labelWidth: 80,
                                    margin: '0 0 0 10',
                                    allowBlank:false,
                                },
                                {
                                    xtype: 'textfield', itemId: "lshc-base-worknodepanel-nodeName-itemId", hidden: true,
                                    name: 'nodeName'
                                }
                            ]
                        },
                        {
                            xtype: 'panel', layout: 'column', columnWidth: 1, margin: '10 0 0 0',
                            items: [
                                {
                                    xtype: 'button',
                                    margin: '5 0 0 250',
                                    text: '提交',
                                    handler:function(){
                                        me.sumitAudit();

                                    }
                                },
                                {
                                    xtype: 'button',
                                    margin: '5 0 0 5',
                                    text: '取消',
                                    handler:function(){
                                        me.close();
                                    }
                                }
                            ]
                        }
                    ]
                }

            ]
        });
        me.callParent(arguments);
    }

})