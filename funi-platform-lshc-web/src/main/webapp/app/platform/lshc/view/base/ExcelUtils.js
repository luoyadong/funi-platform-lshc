/**
 * 常用工具
 *
 * @author yadong
 */
Ext.define('app.platform.lshc.view.base.ExcelUtils', {
    alternateClassName: 'ExcelUtils',
    statics: {
        exportExcel: function (param,url) {
            if (!Ext.fly('frmDummy')) {
                var frm = document.createElement('form');
                frm.id = 'frmDummy';
                frm.name = id;
                frm.className = 'x-hidden';
                document.body.appendChild(frm);
            }
            Ext.Ajax.request({
                disableCaching: true ,
                url : Funi.core.Context.path("lshc",url),
                timeout: 10000000,
                method : 'post',
                waitMsg:'数据导出中，请稍后...',
                isUpload: true,
                form: Ext.fly('frmDummy'),
                params : param,
                success:function(response,opts){
                    Ext.Msg.alert('提示', '导出数据成功！');
                },
                failure:function(response,opts){
                    Ext.Msg.alert('提示', '导出数据失败！');
                }
            });
        },
        //目的只是为了导入数据,而非上传文件
        importExcel:function(url, store){
            var formTmp = this;
            var win = Ext.create("Ext.Window",{
                itemId: 'ghouse-detail-excel-win-itemId',
                height:100,
                width:400,
                items: [
                    {
                        tbar: {
                            xtype: 'form',
                            itemId: '_excelFormTmpId',
                            name: '_excelFormTmpId',
                            fileUpload: true,
                            enctype: 'multipart/form-data',
                            frame: true,
                            model: true,
                            //title: '数据导入',
                            labelWidth: 50,
                            defaults: {
                                //anchor: '50%',
                                allowBlank: false,
                                msgTarget: 'side'
                            },
                            items: [{
                                xtype: 'fileuploadfield',
                                itemId: 'importExcel',
                                emptyText: '导入Excel表格',
                                labelSeparator: '',
                                fieldLabel: '导入文件',
                                name: 'uploadFile',
                                buttonText: '选择',
                                margin: '0 0 0 0',
                                width: 300,
                                labelWidth: 65,
                                allowBlank: false,
                                blankText: '请先选择导入文件',
                                listeners: {
                                    change: function () {//.query('#_excelFormTmpId')[0]
                                        var me = this;
                                        var fileName = me.getValue().split('.');
                                        fileName = fileName[fileName.length - 1];
                                        if (fileName != "xls" && fileName != "xlsx") {
                                            Ext.Msg.alert("系统提示", "请选择xls/xlsx文件!");
                                            return;
                                        }
                                    }
                                }
                            },
                                {
                                    xtype: 'button',
                                    text: '开始导入数据',
                                    glyph: 'xf08b@FontAwesome',
                                    margin: '0 0 0 69',
                                    handler: function () {
                                        var form = win.queryById('_excelFormTmpId').form;// me.form;

                                        if (form.isValid()) {
                                            form.submit({
                                                method: 'POST',
                                                cache: false,
                                                timeout: 180000, //seconds
                                                url: Funi.core.Context.path("lshc", url),//后台处理的页面
                                                waitMsg: '导入中，请稍等...',
                                                success: function (form, action) {
                                                    console.info(action)
                                                    if(null != action
                                                        && null != action.response
                                                        && null != action.response.responseText){
                                                        var rtJson = null;
                                                        try
                                                        {
                                                            rtJson = JSON.parse(action.response.responseText);
                                                        }
                                                        catch(err)
                                                        {
                                                            Ext.Msg.alert('提示', '导入失败，请重新导入');
                                                            return false;
                                                        }
                                                        if(rtJson.success && rtJson.success == true){
                                                            //Ext.Msg.alert('提示', '导入成功');

                                                            if(rtJson.result != null && rtJson.message != null
                                                                && rtJson.message != ""){//说明校验未通过

                                                                var dataList = rtJson.result
                                                                Ext.Msg.confirm('提示',  rtJson.message+'你确定要继续吗？', function (btn) {
                                                                        if (btn === 'yes') {

                                                                            var message = null;
                                                                            var exceptionStr = '服务器异常,请重试!';
                                                                            Ext.Ajax.request({
                                                                                url: Funi.core.Context.path('lshc', '/manage/importDataRegiInfoList'),
                                                                                method: 'post',
                                                                                jsonData: {"excelRegiInfo":dataList},
                                                                                async: false,
                                                                                contentType: "application/json;charset=UTF-8",
                                                                                dataType: 'json',
                                                                                success: function (response) {

                                                                                    var data = JSON.parse(response.responseText);
                                                                                    message = data.result != null ? data.result : exceptionStr;
                                                                                    if (null != store) {
                                                                                        store.reload();
                                                                                    }
                                                                                    Ext.Msg.alert('提示', message);
                                                                                },
                                                                                failure: function () {

                                                                                    Ext.Msg.alert('提示', '导入失败，请重新导入');

                                                                                }
                                                                            });

                                                                        }
                                                                    }
                                                                )

                                                            }

                                                        }else{
                                                            if(null != rtJson && null != rtJson.message){//check_msg
                                                                Ext.Msg.alert('提示', '导入失败:'+rtJson.message);
                                                            }else{
                                                                Ext.Msg.alert('提示', '导入失败，请重新导入');
                                                            }
                                                        }
                                                    }else{
                                                        Ext.Msg.alert('提示', '导入失败，请重新导入');
                                                    }
                                                },
                                                failure: function (form, action) {
                                                    if(null != action
                                                        && null != action.response
                                                        && null != action.response.responseText) {
                                                        var rtJson = null;
                                                        try {
                                                            rtJson = JSON.parse(action.response.responseText);
                                                        }
                                                        catch (err) {
                                                            Ext.Msg.alert('提示', '导入失败，请重新导入');
                                                            return false;
                                                        }
                                                        if(null != rtJson && null != rtJson.message){//check_msg
                                                            Ext.Msg.alert('提示', '导入失败:'+rtJson.message);
                                                        }else{
                                                            Ext.Msg.alert('提示', '导入失败，请重新导入');
                                                        }

                                                    }else{
                                                        Ext.Msg.alert('提示', '导入失败，服务器异常！');
                                                    }

                                                }
                                            });
                                        } else {
                                            Ext.Msg.alert('提示', '请先选择导入文件');
                                        }
                                    }
                                },
                                {
                                    xtype: 'tbtext',
                                    text: '下载模板',
                                    margin: '0 0 0 0'

                                }

                            ]
                        }}]
            });
            win.show();
        }
    }
});