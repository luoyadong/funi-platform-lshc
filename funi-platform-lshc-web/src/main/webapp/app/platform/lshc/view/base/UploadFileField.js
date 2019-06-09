Ext.define('app.platform.lshc.view.base.UploadFileField', {
    extend: 'Ext.form.Panel',
    alias: 'widget.uploadfilefield',
    requires: [
        'app.platform.lshc.view.base.RequestUtils',
        'app.platform.lshc.view.base.MsgUtils'
    ],
    fileUpload: true,
    enctype: 'multipart/form-data',
    padding: '0 0 5 0',
    initComponent: function () {
        var me = this;

        var buttonText;
        var style;
        if (me.params){
            buttonText=me.params.buttonText;
            style=me.params.style;
        }
        me.items = [
            {
                xtype: 'filefield',
                buttonOnly: true,
                name: 'file',
                buttonConfig: {glyph: 0xf093},
                buttonText: buttonText ? buttonText : '上传图片',
                style: style,
                listeners: {
                    change: function (cmp, newValue, oldValue) {
                        if (newValue) {
                            var img_reg = /\.([jJ][pP][gG]){1}$|\.([jJ][pP][eE][gG]){1}$|\.([pP][nN][gG]){1}$/;
                            if (!img_reg.test(newValue)) {
                                MsgUtils.error('请选择jpg/png/jpeg格式图片进行上传!');
                                cmp.reset();
                                return false;
                            }
                            cmp.up().submit({
                                method: 'POST',
                                url: Funi.core.Context.path("shs", "/file/upload"),
                                waitMsg: '图片上传中，请稍等...',
                                success: function (form, action) {
                                    me.fireEvent("success", action.result.result);
                                },
                                failure: function (form, action) {
                                    MsgUtils.error('上传失败！');
                                }
                            });
                        }
                    }
                }
            }
        ];
        me.callParent();
    }

});