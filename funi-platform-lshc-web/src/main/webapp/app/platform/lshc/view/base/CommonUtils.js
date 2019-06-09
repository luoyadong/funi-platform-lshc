/**
 * 常用工具
 *
 * @author huan.lin
 */
Ext.define('app.platform.lshc.view.base.CommonUtils', {
    alternateClassName: 'CommonUtils',
    statics: {
        close: function (com) {
            var tabPanel = com.up().up();
            var currentTab = com.up();
            tabPanel.remove(currentTab);
        },
        closeItemId: function (itemId) {
            var tabPanel = Ext.mainFrame.queryById('centerBox');
            var obj = tabPanel.queryById(itemId);
            tabPanel.remove(obj.up());
        },
        refresh:function (itemId) {
            var tabPanel = Ext.mainFrame.queryById('centerBox');
            var obj = tabPanel.queryById(itemId);
            if (obj && typeof obj._reload != 'undefined' && obj._reload instanceof Function) {
                obj._reload();
            }
        },

        newTab: function (view) {
            Ext.mainFrame.queryById('centerBox').getActiveTab().add(0, view);
        },
        getInputWrapCls: function (type) {
            return type == 1 ? 'x-form-text-wrap' : '';
        },
        getTriggerWrapCls: function (type) {
            return type == 1 ? 'x-form-trigger-wrap' : '';
        },
        getDisplayed: function (type) {
            return type != 1;
        },
        settingDisabled: function (com, f) {
            var fieldArray = [];
            fieldArray.push(com.query("*[xtype=textfield]"));
            fieldArray.push(com.query("*[xtype=numberfield]"));
            fieldArray.push(com.query("*[xtype=datefield]"));
            fieldArray.push(com.query("*[xtype=select]"));
            Ext.each(fieldArray, function (item) {
                Ext.each(item, function (it) {
                    it.setDisabled(f);
                });
            });
        },
        handleNullValue: function (value) {
            return value ? value : '-';
        },
        //身份证15位转18位
        convertIdNumber: function (val) {
            var result = '';
            if (val && val.length == 15) {
                var factorArr = [7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2];
                var checkCodeArr = [1, 0, 'X', 9, 8, 7, 6, 5, 4, 3, 2];
                val = val.substring(0, 6) + '19' + val.substring(6);
                var valArr = val.split('');
                var sumVal = 0;
                for (var i = 0; i < valArr.length; i++) {
                    sumVal += parseInt(valArr[i]) * factorArr[i];
                }
                var checkCode = checkCodeArr[sumVal % 11];
                result = val + checkCode;
            } else {
                result = val;
            }
            return result;

        },
        //验证18位身份证
        validIdNumber: function (val) {
            if (val && val.length == 18) {
                var lastChar = val.substring(17, 18);
                if (lastChar == 'X') {
                    val = val.substring(0, 17) + 'x';
                }
                var Ai = val.length == 18 ? val.substring(0, 17) : val.slice(0, 6) + '19' + val.slice(6, 16);
                if (!/^\d+$/.test(Ai)) {
                    return false;
                }
                var yyyy = Ai.slice(6, 10), mm = Ai.slice(10, 12) - 1, dd = Ai.slice(12, 14);
                var d = new Date(yyyy, mm, dd), now = new Date();
                var year = d.getFullYear(), mon = d.getMonth(), day = d.getDate();
                if (year != yyyy || mon != mm || day != dd || d > now) {
                    return false;
                }
                var arrVerifyCode = [1, 0, 'x', 9, 8, 7, 6, 5, 4, 3, 2];
                var Wi = [7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2];
                for (var i = 0, ret = 0; i < 17; i++) {
                    ret += Ai.charAt(i) * Wi[i];
                }
                Ai += arrVerifyCode[ret %= 11];
                if (val == Ai) {
                    return true;
                }
            }
            return false;
        },
        nvl: function (value, msg) {
            return value ? value : msg ? msg : '-';
        },
        extend:function(des, src) {
            if (src instanceof Array) {
                for (var i = 0, len = src.length; i < len; i++)
                    extend(des, src[i], override);
            }
            else {
                for (var i in src) {
                    // if(!(i in des)){
                    des[i] = src[i];
                    //}
                }
            }
            return des;
        },
        //获得报表host,后续可统一配置
        getReportHost:function(){
            var reportHost = "192.168.1.250:8080";
            return reportHost;
        }
    }
});