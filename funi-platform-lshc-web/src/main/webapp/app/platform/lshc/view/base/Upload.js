Ext.define('app.platform.lshc.view.base.Upload', {
    extend: 'Ext.window.Window',
    layout: 'fit',
    height: 600,
    width: 800,
    modal: true,
    border: true,
    title: '文件上传',
    glyph: 0xf093,
    config: {
        callback: null
    },
    initComponent: function () {
        var me = this;
        var src = '/rent/upload/ImgUpload.html';
        Ext.applyIf(me, {
            items: [{
                xtype: 'panel', anchor: '100%',
                html: '<iframe id="upload" src=' + src + ' frameborder="0" width="100%" height="100%" name="upload"></iframe>',
            }]
        });
        me.callParent(arguments);
    },
    buttons: [{
        text: '确认', glyph: 0xf0a4, handler: function () {
            var doc = Ext.getDom('upload').contentDocument;
            var fileData = doc.getElementById('fileData').value;
            this.up('window').config.callback({data: fileData});
            this.up('window').close();
        }
    }, {
        text: '取消', glyph: 0xf00d, handler: function () {
            this.up('window').close();
        }
    }]
});