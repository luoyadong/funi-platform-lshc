/**
 *
 *@author 3
 */
Ext.define("app.platform.lshc.view.regi.home.HomeMainView",{
    extend:"Ext.panel.Panel",
    width:"100%",

    //height:600,
    height:"100%",
    layout:"fit",
    requires:[
    ],
    title:"首页",
    xtype:"lshc-regi-home-HomeMainView",
    itemId:"lshc-regi-home-HomeMainView-itemId",
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
        var url = Funi.core.Context.path('lshc', '/home.html');
        Ext.applyIf(this,
            {
                items:[
                       {
                            //title: '资金监管协议', itemId: 'tab8',   glyph: 0xf008,
                            html: '<iframe id="frame_main"  src="'+url+'"  width="100%" height="100%" frameborder="0" scrolling="auto"></iframe>'
                        }
				]
            });
        this.callParent(arguments);
    }
});