/**
 * 消息提示框
 *
 * @author haiyang.li
 */
Ext.define('app.platform.lshc.view.base.MsgUtils', {
    alternateClassName: 'MsgUtils',
    statics: {
        config: function (message, icon) {
            return {
                title: '温馨提示',
                message: message,
                buttons: Ext.Msg.OK,
                icon: icon
            }
        },
        error: function (message) {
            Ext.Msg.alert(this.config(message, Ext.Msg.ERROR));
        },
        warning: function (message) {
            Ext.Msg.alert(this.config(message, Ext.Msg.WARNING));
        },
        info: function (message) {
            Ext.Msg.alert(this.config(message, Ext.Msg.INFO));
        },
        question: function (message) {
            Ext.Msg.alert(this.config(message, Ext.Msg.QUESTION));
        }
    }
});