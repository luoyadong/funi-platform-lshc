/**
 * @author huan.lin
 */
Ext.define('app.platform.lshc.view.base.ClearComboBox', {
    extend: "Funi.form.field.ComboBox",
    alias: "widget.clearcombobox",
    editable: false,
    listeners: {
        expand: function () {
            arguments[0].clearValue();
        }
    }
});