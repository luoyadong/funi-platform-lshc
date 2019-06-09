/**
 * @author haiyang.li
 */
Ext.define('app.platform.lshc.view.base.PubCombo', {
    extend: "Funi.form.field.ComboBox",
    alias: "widget.pubCombo",
    editable: false,
    listeners: {
        //渲染时候，默认选中第一个下拉框
        beforeRender: function () {
            var combo = this;
            combo.store.load({
                callback: function () {
                    combo.select(this.data.items[0]);
                }
            });
        }
    },
    //直接通过displayValue设置值,仅用于displayField为name的组件
    setDisplayValue: function (value) {
        var cmp = this;
        Ext.each(cmp.store.data.items, function (record) {
            if (record.data.name == value) {
                cmp.select(record);
                return false;
            }
        });
    }
});