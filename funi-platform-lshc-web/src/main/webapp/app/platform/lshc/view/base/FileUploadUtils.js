/**
 *上传附件、展示附件列表、并且提供删除操作
 *yadong
 */
Ext.define('app.platform.lshc.view.base.FileUploadUtils', {
    extend: 'Ext.form.Panel',
    xtype: 'ghouse-view-base-FileUploadUtils',
    requires: [
    ],
    frame: false,
    border: false,
    config: {
        bizId:null,//业务件Id
        linkType:null,//要件类别，请自行编号，eg:小区-变更要件：001
        btnTitle:"上传文件",//上传按钮名称
        fileNum:4,//最多可上传几个文件
        isHidden:false
    },
    itemId:'FileUploadUtilsId',
    constructor: function (config) {
        var me = this;
        config = config || {};
        Ext.applyIf(config, me.config);
        this.callParent(arguments);
    },
    initComponent: function () {
        var me = this;

        Ext.apply(me, {
            items: [
                        {
                            xtype: 'form',
                            layout: 'hbox',
                            itemId:'FileUploadUtilsItemsId',
                            getAttachmentList:function(){
                                var array = new Array();
                                var fieldcontainerList = Ext.ComponentQuery.query("fieldcontainer",this);
                                for(var i=0;i<fieldcontainerList.length;i++){
                                    var textfieldList = Ext.ComponentQuery.query("textfield",fieldcontainerList[i]);
                                    var obj = new Object();
                                    for(var j=0;j<textfieldList.length;j++){
                                        obj[textfieldList[j].name]= textfieldList[j].value;
                                    }
                                    array.push(obj);
                                }
                                return array;
                            },
                            items: [
                                {
                                    xtype: 'fileuploadfield',
                                    itemId:'uploadFileItemId',
                                    margin: '5 0 0 5',
                                    buttonOnly: true,
                                    hidden:me.config.isHidden,
                                    labelSeparator: '',
                                    name: 'uploadFile',
                                    buttonText: '+'+me.config.btnTitle,

                                    listeners: {
                                        change: function () {
                                            var conts = me.query("fieldcontainer");
                                            if(null != conts && conts.length >=me.config.fileNum){
                                                Ext.Msg.alert('提示', "要件数量不得超过["+me.config.fileNum+"]件！");
                                                return false;
                                            }
                                            if(me.config.linkType == null){
                                                Ext.Msg.alert('提示', "请传入文件类别！");
                                                return false;
                                            }
                                            var form = me.queryById("FileUploadUtilsItemsId");
                                            var fileName =me.queryById('uploadFileItemId').getValue().split('.');
                                            var saveFileName=fileName[0].split('\\')[fileName[0].split('\\').length-1]+'.'+ fileName[fileName.length - 1];

                                            linkFileType = fileName[fileName.length - 1];
                                            if (linkFileType != "jpg" && linkFileType != "png"
                                                && linkFileType != "doc" && linkFileType != "xls"
                                                && linkFileType!= "jpeg" && linkFileType != "pdf" && linkFileType != "zip"
                                                && linkFileType != "docx" && linkFileType != "xlsx") {
                                                Ext.Msg.alert("系统提示", "上传文件格式仅支持（doc/xls/pdf/jpg/jpeg/png/docx/xlsx/zip）!");
                                                return false;
                                            }
                                            if (form.isValid()) {
                                                form.submit({
                                                    method: 'POST',
                                                    url: Funi.core.Context.path("lshc", "/FileController/createFile"),//后台处理的页面
                                                    waitMsg: '文件上传中，请稍等...',
                                                    success: function (form, action) {
                                                    },
                                                    failure: function (form, action) {
                                                        console.info(action)
                                                        //上传成功，刷新界面
                                                        if(null != action && null != action.result){
                                                            var linkFileUrl = action.result.linkFileUrl;
                                                            //linkFileName,linkType,fileType,fileUrl
                                                            me.refreshPage(saveFileName,me.config.linkType,linkFileType,linkFileUrl,null);
                                                        }
                                                    }
                                                });
                                            }
                                        }
                                    }
                                }

                            ]
                        }


            ] });
        //注册表单
        me.callParent(arguments);
        //初始化附件列表
        me.findFileList();
    },
    //删除文件
    deleteFileById:function(thisObj){
        var me = this;
        Ext.Msg.confirm('确认', '你确认要删除要件吗？', function (btn) {
            if (btn === 'yes') {
                var parantObj = thisObj.findParentByType("fieldcontainer");
                var fileIdTmp = parantObj.queryById("fileId").getValue();

                if(me.config.bizId != null && '-999' != fileIdTmp){//编辑情况且为之前老数据，才会删除

                    var result = Ext.appContext.invokeService(Funi.core.Context.path("lshc","/FileController/deleteFile"),{fileId:fileIdTmp});
                    if(result == null || result == '') {
                        Ext.Msg.alert('提示', "删除失败，请联系管理员！");
                        return false;
                    } else {
                        Ext.Msg.alert('提示', "操作成功!");
                    }
                }

                //删除组件
                me.queryById("FileUploadUtilsItemsId").remove(parantObj);

            }
        });

    },
    //查询文件
    findFileList:function(){
        var me = this;
        if(me.config.linkType == null){
            Ext.Msg.alert('提示', "请传入文件类别！");
            return false;
        }

        if(me.config.bizId != null){//需要查询，新建无需查询
                var rr = Ext.appContext.invokeService(Funi.core.Context.path("lshc","/FileController/findFileList"),
                    {bizId:me.config.bizId,fileType:me.config.linkType});
                if(rr != null && null != rr.result && null != rr.result.list) {
                    var tmpList = rr.result.list;
                    for(var j in tmpList){
                        if(null != tmpList){
                            var linkFileName = tmpList[j].linkFileName;
                            var linkType = tmpList[j].linkType;
                            var fileType = tmpList[j].linkFileType;
                            var fileUrl = tmpList[j].linkFileUrl;
                            var fileId = tmpList[j].id;
                            //刷新列表
                            me.refreshPage(linkFileName,linkType,fileType,fileUrl,fileId);
                        }
                    }
                } else {
                    console.info("查询结果为空!");
                }
        }

    },
    //刷新界面操作
    refreshPage:function(linkFileName,linkType,fileType,fileUrl,fileId){
        var me = this;

        if(null == fileId){
            fileId = '-999';
        }
        var fileNameTmp = linkFileName;
        //数据渲染，增加、删除界面元素
        if(fileNameTmp.length >8){
            fileNameTmp = Ext.util.Format.ellipsis(linkFileName,8);
            fileNameTmp += fileType;
        }

        var item = {
            xtype: "fieldcontainer",
            colspan: 1,
            maxLength: '146px',
            width:'146px',
            layout: 'hbox',
            items:[
                {
                    xtype: 'textfield',
                    hidden: true,
                    value:fileId,
                    itemId:'fileId',
                    name: 'fileId'
                },
                {
                    xtype: 'textfield',
                    hidden: true,
                    value:linkFileName,
                    itemId:'linkFileName',
                    name: 'linkFileName'
                },
                {
                    xtype: 'textfield',
                    hidden: true,
                    value:linkFileName,
                    itemId:'linkShowName',
                    name: 'linkShowName'
                },
                {
                    xtype: 'textfield',
                    hidden: true,
                    value:linkType,
                    itemId:'linkType',
                    name: 'linkType'
                },
                {
                    xtype: 'textfield',
                    hidden: true,
                    value:fileUrl,
                    itemId:'linkFileUrl',
                    name: 'linkFileUrl'
                },
                {
                    xtype: 'textfield',
                    hidden: true,
                    value:fileType,
                    itemId:'linkFileType',
                    name: 'linkFileType'
                },
                {
                    xtype: "button",
                    height:'20px',
                    text:'X',
                    width:'25px',
                    itemId:"delete_file",
                    hidden:me.config.isHidden,
                    style: {
                        marginTop:'6px',
                        marginBottom: '0px'
                    },
                    handler: function(){
                        me.deleteFileById(this);

                    }
                },
                {   xtype: 'tbtext',
                    margin: '10 0 0 0',
                    width:'98px',
                    text:'<a href="'+fileUrl+'" target="_blank">'+fileNameTmp+'</a>'
                }

            ]
        };

        //create
        var fileItem = new Ext.form.FieldContainer(item);
        var pObj = me.queryById("FileUploadUtilsItemsId");
        pObj.add(fileItem);

    },
    //获得文件数据集合
    //使用,eg: var fielUpload = me.queryById('file-upload-view-item-id-xx');
    //fielUpload.getFileData()
    //返回对象：linkFileName（文件名）、linkShowName（显示文件名）、linkType（文件分类）、
    //          linkFileType（文件类型）、linkFileUrl（文件url）
    getFileData:function(){
        var me = this;
        var data = []
        var record = {};
        //获得所有文件类型的值
        var conts = me.query("fieldcontainer");
        if(null != conts && conts.length >0){
            for(var i=0;i< conts.length;i++){
                var cont = conts[i];
                if(null != cont && null !=cont.queryById("linkFileName")){
                    //每条记录取值 linkFileName,linkType,fileType,fileUrl
                    var fileId= cont.queryById("fileId").getValue();
                    var linkFileName= cont.queryById("linkFileName").getValue();
                    var linkType= cont.queryById("linkType").getValue();
                    var linkFileType= cont.queryById("linkFileType").getValue();
                    var linkFileUrl= cont.queryById("linkFileUrl").getValue();
                    var linkShowName= cont.queryById("linkShowName").getValue();

                    //说明是新增的，至于删除的已经直接删除关联了
                    if("-999" == fileId){
                        record = {
                            linkFileName:linkFileName,
                            linkType:linkType,
                            linkFileType:linkFileType,
                            linkFileUrl:linkFileUrl,
                            linkShowName:linkShowName
                        }

                        data.push(record);
                    }

                }

            }
        }

        return data;
    }
});

function __addArrayList__(src,desc){
    if(null == src || null == desc){
        Ext.Msg.alert('提示', "源集合或者目标集合不能为空");
        return false;
    }
    for(var t in src){
        desc.push(src[t]);
    }
    return desc;
}
