/**
 *
 *@author 3
 */
Ext.define('app.platform.lshc.view.base.FileUtils', {
    alternateClassName: 'FileUtils',
    formJs: false,
    statics: {
        handle:function(e){
            if (cla == 'delete-file') {
                Ext.Msg.confirm('提示', '你确定要删除选中的要件吗？', function (btn) {
                        if (btn === 'yes') {

                        }
                    }
                )
            }
            else if (cla == 'upload-file') {
                var win = Ext.create("Ext.Window",{
                    itemId: 'shfs-detail-accessory-win-itemId',
                    items: {
                        height: 200,
                        width: 400,
                        xtype: 'fileUpload',
                        listeners: {
                            afterUpload: function (returnData) {
                                if (returnData) {
                                    var win = this.up();
                                    var fileName =   "<p><a class='opened-file' attachment='" + returnData.fileStoreId + "'>" + returnData.fileName + "</a>  <a class='delete-file attachment='" + returnData.fileStoreId + "'>删除</a></p>";
                                }
                            }}
                    }
                });
                win.show();
            }else if(cla == 'opened-file'){
                //me._openImgWindow(id);
            }
        }
    }
});
