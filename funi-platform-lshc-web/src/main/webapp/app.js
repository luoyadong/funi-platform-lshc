Ext.onReady(function(){
    Ext.application({
        extend: 'app.Application',
        name: 'app',
        requires: [
            'app.platform.lshc.view.main.Main',
            'app.Application'
        ],
        launch: function () {
            this.callParent();
            Ext.setGlyphFontFamily('FontAwesome');
            var main =  Ext.create('app.platform.lshc.view.main.Main', {
                renderTo: Ext.getBody()
            });
            Ext.mainFrame = main;
        }
    });
})

