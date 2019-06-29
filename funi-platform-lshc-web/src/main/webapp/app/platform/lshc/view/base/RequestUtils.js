/**
 * 封装ajax请求
 *
 * @author yadong
 */
Ext.define('app.platform.lshc.view.base.RequestUtils', {
    alternateClassName: 'RequestUtils',
    formJs: false,
    statics: {
        url: function (path) {
            // if (!this.formJs) {
            //     this.();
            //     this.formloadJsJs = true;
            // }
            return Funi.core.Context.path('lshc', path);
        },
        //data:请求数据，url：请求地址，isAsync：是否异步，allowTips是否允许弹框提示
        post: function (data, url, isAsync, allowTips) {
            if (!url) {
                throw new BizException('无效请求地址！');
            }
            var message = null;
            var exceptionStr = '服务器异常,请重试!';
            Ext.Ajax.request({
                url: app.platform.lshc.view.base.RequestUtils.url(url),
                method: 'post',
                params: data,
                async: isAsync,
                success: function (response) {
                    var data = JSON.parse(response.responseText);
                    message = data.result != null ? data.result : exceptionStr;
                },
                failure: function () {
                    message = exceptionStr;
                }
            });
            if (message != null && allowTips) {
                Ext.Msg.alert('温馨提示', message);
            }
        },
        post_json: function (data, url, isAsync, allowTips) {
            if (!url) {
                throw new BizException('无效请求地址！');
            }
            var message = null;
            var flag = true;
            var exceptionStr = '服务器异常,请重试!';
            Ext.Ajax.request({
                url: app.platform.lshc.view.base.RequestUtils.url(url),
                method: 'post',
                jsonData: data,
                async: isAsync,
                contentType: "application/json;charset=UTF-8",
                dataType: 'json',
                success: function (response) {
                    allowTips = true;
                    if(allowTips){
                        var data = JSON.parse(response.responseText);
                        message = data.result != null?data.result:data.message;
                        if(data.success){
                            Ext.Msg.alert('温馨提示', "操作成功！");
                        }else{
                            flag = false;
                            Ext.Msg.alert('温馨提示', data.result);
                        }
                    }
                },
                failure: function () {
                    flag = false;
                    message = exceptionStr;
                    Ext.Msg.alert('温馨提示', message);
                }
            });
            return flag;
            // if (message != null && allowTips) {
            //     Ext.Msg.alert('温馨提示', message);
            // }
        },
        return_post_json: function (data, url, isAsync, allowTips) {
            if (!url) {
                throw new BizException('无效请求地址！');
            }
            var message = null;
            var flag = true;
            var rtObj = {};
            var exceptionStr = '服务器异常,请重试!';
            Ext.Ajax.request({
                url: app.platform.lshc.view.base.RequestUtils.url(url),
                method: 'post',
                jsonData: data,
                async: isAsync,
                contentType: "application/json;charset=UTF-8",
                dataType: 'json',
                success: function (response) {
                    var data = JSON.parse(response.responseText);
                    if(data.success){
                        message = data.result
                        Ext.Msg.alert('温馨提示', "操作成功！");
                    }else{
                        flag = false;
                        Ext.Msg.alert('温馨提示', data.result);
                    }
                },
                failure: function () {
                    message = exceptionStr;
                    flag = false;
                    Ext.Msg.alert('温馨提示', message);
                }
            });
            rtObj['flag'] = flag;
            rtObj['data'] = message
            return rtObj;
        },
        request: function (data, url) {
            if (!url) {
                throw new BizException('无效请求地址！');
            }
            var message = null;
            var result = null;
            var status = false;
            Ext.Ajax.request({
                url: app.platform.lshc.view.base.RequestUtils.url(url),
                method: 'post',
                params: data,
                async: false,
                contentType: "application/json;charset=UTF-8",
                dataType: 'json',
                success: function (response) {
                    var data = JSON.parse(response.responseText);
                    if (!data.success || data.success == 'false') {
                        message = data.result;
                        if (data.result) {
                            //返回false,result里面有值 视为通过数据展示异常信息
                            result = data.result;
                        }
                    } else {
                        status = true;
                        //有result则返回result，否则返回message
                        result = data.result != null ? data.result : data.result;
                    }
                },
                failure: function () {
                    message = '服务器异常,请重试!';
                }
            });
            if (!status) {
                throw new BizException(message, result);
            }
            return result;
        },
        //加载存量房系统的js
        loadMapJs: function (jsName) {
            //地图引入js，只引用一次
            var ip = window.Funi.core.Context._host;
            var scriptList = document.getElementsByTagName('script');
            var myHead = document.getElementsByTagName('HEAD').item(0);
            //判断是否有引入js
            var flag = false;
            Ext.each(scriptList, function (item) {
                if (item.src.indexOf(jsName) != -1) {
                    flag = true;
                    return false;
                }
            });
            if(!flag){
                var shForm = document.createElement('script');
                shForm.type = 'text/javascript';
                shForm.src = 'http://' + ip + '/lshc/'+jsName;
                myHead.appendChild(shForm);
            }
        },
        //加载存量房系统的css
        loadMapCss: function (cssName) {
            //引入地图的js，只引用一次
            var ip = window.Funi.core.Context._host;
            var scriptList = document.getElementsByTagName('link');
            var myHead = document.getElementsByTagName('HEAD').item(0);
            //判断是否有引入js
            var flag = false;
            Ext.each(scriptList, function (item) {
                if (item.src.indexOf(cssName) != -1) {
                    flag = true;
                    return false;
                }
            });
            if(!flag){
                var shForm = document.createElement('link');
                shForm.type = 'text/css';
                shForm.rel="stylesheet";
                shForm.src = 'http://' + ip + '/lshc/'+cssName;
                myHead.appendChild(shForm);
            }
        }
    }
});

function BizException(message, result) {
    return {message: message, result: result};
}