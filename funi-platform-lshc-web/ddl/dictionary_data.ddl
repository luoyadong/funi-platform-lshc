--房屋结构:钢筋混凝土、钢、砖混、砖木、其他
delete from lshc_dictionary where config_type='HOUSE_STRUCTURE';

insert into lshc_dictionary ( NAME, FORMER_NAME, CONFIG_TYPE, PID, ISONLINE, NOTE)
values ('钢筋混凝土', '钢筋混凝土', 'HOUSE_STRUCTURE', null, 1, null);

insert into lshc_dictionary ( NAME, FORMER_NAME, CONFIG_TYPE, PID, ISONLINE, NOTE)
values ('钢', '钢', 'HOUSE_STRUCTURE', null, 1, null);

insert into lshc_dictionary (NAME, FORMER_NAME, CONFIG_TYPE, PID, ISONLINE, NOTE)
values ('砖混', '砖混', 'HOUSE_STRUCTURE', null, 1, null);

insert into lshc_dictionary ( NAME, FORMER_NAME, CONFIG_TYPE, PID, ISONLINE, NOTE)
values ( '砖木', '砖木', 'HOUSE_STRUCTURE', null, 1, null);

insert into lshc_dictionary ( NAME, FORMER_NAME, CONFIG_TYPE, PID, ISONLINE, NOTE)
values ('其他', '其他', 'HOUSE_STRUCTURE', null, 1, null);

--房屋类别:商品房、周转房、公租房 、自建房、集资房、安置房、已购公房 、其他
delete from lshc_dictionary where config_type='HOUSE_TYPE';

insert into lshc_dictionary (NAME, FORMER_NAME, CONFIG_TYPE, PID, ISONLINE, NOTE)
values ( '商品房', '商品房', 'HOUSE_TYPE', null, 1, null);

insert into lshc_dictionary (NAME, FORMER_NAME, CONFIG_TYPE, PID, ISONLINE, NOTE)
values ( '周转房', '周转房', 'HOUSE_TYPE', null, 1, null);

insert into lshc_dictionary (NAME, FORMER_NAME, CONFIG_TYPE, PID, ISONLINE, NOTE)
values ( '公租房', '公租房', 'HOUSE_TYPE', null, 1, null);

insert into lshc_dictionary (NAME, FORMER_NAME, CONFIG_TYPE, PID, ISONLINE, NOTE)
values ( '自建房', '自建房', 'HOUSE_TYPE', null, 1, null);

insert into lshc_dictionary (NAME, FORMER_NAME, CONFIG_TYPE, PID, ISONLINE, NOTE)
values ('集资房', '集资房', 'HOUSE_TYPE', null, 1, null);

insert into lshc_dictionary (NAME, FORMER_NAME, CONFIG_TYPE, PID, ISONLINE, NOTE)
values ( '安置房', '安置房', 'HOUSE_TYPE', null, 1, null);

insert into lshc_dictionary (NAME, FORMER_NAME, CONFIG_TYPE, PID, ISONLINE, NOTE)
values ( '已购公房', '已购公房', 'HOUSE_TYPE', null, 1, null);

insert into lshc_dictionary (NAME, FORMER_NAME, CONFIG_TYPE, PID, ISONLINE, NOTE)
values ( '其他', '其他', 'HOUSE_TYPE', null, 1, null);

--房屋用途:
delete from lshc_dictionary where config_type='HOUSE_USE';

insert into lshc_dictionary (NAME, FORMER_NAME, CONFIG_TYPE, PID, ISONLINE, NOTE)
values ('车位', '车位', 'HOUSE_USE', null, 1, '其他');

insert into lshc_dictionary (NAME, FORMER_NAME, CONFIG_TYPE, PID, ISONLINE, NOTE)
values ('车库', '车库', 'HOUSE_USE', null, 1, '其他');

insert into lshc_dictionary (NAME, FORMER_NAME, CONFIG_TYPE, PID, ISONLINE, NOTE)
values ( '集体宿舍', '集体宿舍', 'HOUSE_USE', null, 1, '其他');

insert into lshc_dictionary (NAME, FORMER_NAME, CONFIG_TYPE, PID, ISONLINE, NOTE)
values ( '工业', '工业', 'HOUSE_USE', null, 1, '其他');

insert into lshc_dictionary (NAME, FORMER_NAME, CONFIG_TYPE, PID, ISONLINE, NOTE)
values ( '公用设施', '公用设施', 'HOUSE_USE', null, 1, '其他');

insert into lshc_dictionary (NAME, FORMER_NAME, CONFIG_TYPE, PID, ISONLINE, NOTE)
values ( '铁路', '铁路', 'HOUSE_USE', null, 1, '其他');

insert into lshc_dictionary (NAME, FORMER_NAME, CONFIG_TYPE, PID, ISONLINE, NOTE)
values ('民航', '民航', 'HOUSE_USE', null, 1, '其他');

insert into lshc_dictionary (NAME, FORMER_NAME, CONFIG_TYPE, PID, ISONLINE, NOTE)
values ( '航运', '航运', 'HOUSE_USE', null, 1, '其他');

insert into lshc_dictionary (NAME, FORMER_NAME, CONFIG_TYPE, PID, ISONLINE, NOTE)
values ( '公交运输', '公交运输', 'HOUSE_USE', null, 1, '其他');

insert into lshc_dictionary (NAME, FORMER_NAME, CONFIG_TYPE, PID, ISONLINE, NOTE)
values ('仓储', '仓储', 'HOUSE_USE', null, 1, '其他');

insert into lshc_dictionary (NAME, FORMER_NAME, CONFIG_TYPE, PID, ISONLINE, NOTE)
values ( '旅游', '旅游', 'HOUSE_USE', null, 1, '其他');

insert into lshc_dictionary (NAME, FORMER_NAME, CONFIG_TYPE, PID, ISONLINE, NOTE)
values ('金融保险', '金融保险', 'HOUSE_USE', null, 1, '其他');

insert into lshc_dictionary (NAME, FORMER_NAME, CONFIG_TYPE, PID, ISONLINE, NOTE)
values ( '电讯信息', '电讯信息', 'HOUSE_USE', null, 1, '其他');

insert into lshc_dictionary (NAME, FORMER_NAME, CONFIG_TYPE, PID, ISONLINE, NOTE)
values ( '教育', '教育', 'HOUSE_USE', null, 1, '其他');

insert into lshc_dictionary (NAME, FORMER_NAME, CONFIG_TYPE, PID, ISONLINE, NOTE)
values ('医疗卫生', '医疗卫生', 'HOUSE_USE', null, 1, '其他');

insert into lshc_dictionary (NAME, FORMER_NAME, CONFIG_TYPE, PID, ISONLINE, NOTE)
values ( '科研', '科研', 'HOUSE_USE', null, 1, '其他');

insert into lshc_dictionary (NAME, FORMER_NAME, CONFIG_TYPE, PID, ISONLINE, NOTE)
values ( '文化', '文化', 'HOUSE_USE', null, 1, '其他');

insert into lshc_dictionary (NAME, FORMER_NAME, CONFIG_TYPE, PID, ISONLINE, NOTE)
values ('新闻', '新闻', 'HOUSE_USE', null, 1, '其他');

insert into lshc_dictionary (NAME, FORMER_NAME, CONFIG_TYPE, PID, ISONLINE, NOTE)
values ('娱乐', '娱乐', 'HOUSE_USE', null, 1, '其他');

insert into lshc_dictionary (NAME, FORMER_NAME, CONFIG_TYPE, PID, ISONLINE, NOTE)
values ( '园林绿化', '园林绿化', 'HOUSE_USE', null, 1, '其他');

insert into lshc_dictionary (NAME, FORMER_NAME, CONFIG_TYPE, PID, ISONLINE, NOTE)
values ( '体育', '体育', 'HOUSE_USE', null, 1, '其他');

insert into lshc_dictionary (NAME, FORMER_NAME, CONFIG_TYPE, PID, ISONLINE, NOTE)
values ('办公（行政、事业）', '办公（行政、事业）', 'HOUSE_USE', null, 1, '其他');

insert into lshc_dictionary (NAME, FORMER_NAME, CONFIG_TYPE, PID, ISONLINE, NOTE)
values ('军事', '军事', 'HOUSE_USE', null, 1, '其他');

insert into lshc_dictionary (NAME, FORMER_NAME, CONFIG_TYPE, PID, ISONLINE, NOTE)
values ('涉外', '涉外', 'HOUSE_USE', null, 1, '其他');

insert into lshc_dictionary (NAME, FORMER_NAME, CONFIG_TYPE, PID, ISONLINE, NOTE)
values ( '宗教', '宗教', 'HOUSE_USE', null, 1, '其他');

insert into lshc_dictionary (NAME, FORMER_NAME, CONFIG_TYPE, PID, ISONLINE, NOTE)
values ( '监狱', '监狱', 'HOUSE_USE', null, 1, '其他');

insert into lshc_dictionary (NAME, FORMER_NAME, CONFIG_TYPE, PID, ISONLINE, NOTE)
values ('住宅', '住宅', 'HOUSE_USE', null, 1, null);

insert into lshc_dictionary (NAME, FORMER_NAME, CONFIG_TYPE, PID, ISONLINE, NOTE)
values ( '商业', '商业', 'HOUSE_USE', null, 1, null);

insert into lshc_dictionary (NAME, FORMER_NAME, CONFIG_TYPE, PID, ISONLINE, NOTE)
values ( '办公', '办公', 'HOUSE_USE', null, 1, null);

--性别
delete from lshc_dictionary where config_type= 'GENDER';
insert into lshc_dictionary (NAME, FORMER_NAME, CONFIG_TYPE, PID, ISONLINE, NOTE)
values ( '男性', '男性', 'GENDER', null, 1, null);
insert into lshc_dictionary (NAME, FORMER_NAME, CONFIG_TYPE, PID, ISONLINE, NOTE)
values ( '女性', '女性', 'GENDER', null, 1, null);

--民族:source table:A134_D
delete from lshc_dictionary where config_type= 'ENT_NATION';
insert into lshc_dictionary (NAME, FORMER_NAME, CONFIG_TYPE, PID, ISONLINE, NOTE)
values ( '白族', '白族', 'ENT_NATION', null, 1, null);

insert into lshc_dictionary (NAME, FORMER_NAME, CONFIG_TYPE, PID, ISONLINE, NOTE)
values ( '保安族', '保安族', 'ENT_NATION', null, 1, null);

insert into lshc_dictionary (NAME, FORMER_NAME, CONFIG_TYPE, PID, ISONLINE, NOTE)
values ( '布依族', '布依族', 'ENT_NATION', null, 1, null);

insert into lshc_dictionary (NAME, FORMER_NAME, CONFIG_TYPE, PID, ISONLINE, NOTE)
values ( '怒族', '怒族', 'ENT_NATION', null, 1, null);

insert into lshc_dictionary (NAME, FORMER_NAME, CONFIG_TYPE, PID, ISONLINE, NOTE)
values ( '普米族', '普米族', 'ENT_NATION', null, 1, null);

insert into lshc_dictionary (NAME, FORMER_NAME, CONFIG_TYPE, PID, ISONLINE, NOTE)
values ( '羌族', '羌族', 'ENT_NATION', null, 1, null);

insert into lshc_dictionary (NAME, FORMER_NAME, CONFIG_TYPE, PID, ISONLINE, NOTE)
values ( '撒拉族', '撒拉族', 'ENT_NATION', null, 1, null);

insert into lshc_dictionary (NAME, FORMER_NAME, CONFIG_TYPE, PID, ISONLINE, NOTE)
values ( '水族', '水族', 'ENT_NATION', null, 1, null);

insert into lshc_dictionary (NAME, FORMER_NAME, CONFIG_TYPE, PID, ISONLINE, NOTE)
values ( '土家族', '土家族', 'ENT_NATION', null, 1, null);

insert into lshc_dictionary (NAME, FORMER_NAME, CONFIG_TYPE, PID, ISONLINE, NOTE)
values ( '土族', '土族', 'ENT_NATION', null, 1, null);

insert into lshc_dictionary (NAME, FORMER_NAME, CONFIG_TYPE, PID, ISONLINE, NOTE)
values ( '东乡族', '东乡族', 'ENT_NATION', null, 1, null);

insert into lshc_dictionary (NAME, FORMER_NAME, CONFIG_TYPE, PID, ISONLINE, NOTE)
values ( '侗族', '侗族', 'ENT_NATION', null, 1, null);

insert into lshc_dictionary (NAME, FORMER_NAME, CONFIG_TYPE, PID, ISONLINE, NOTE)
values ( '仡佬族', '仡佬族', 'ENT_NATION', null, 1, null);

insert into lshc_dictionary (NAME, FORMER_NAME, CONFIG_TYPE, PID, ISONLINE, NOTE)
values ( '哈尼族', '哈尼族', 'ENT_NATION', null, 1, null);

insert into lshc_dictionary (NAME, FORMER_NAME, CONFIG_TYPE, PID, ISONLINE, NOTE)
values ( '哈萨克族', '哈萨克族', 'ENT_NATION', null, 1, null);

insert into lshc_dictionary (NAME, FORMER_NAME, CONFIG_TYPE, PID, ISONLINE, NOTE)
values ( '汉族', '汉族', 'ENT_NATION', null, 1, null);

insert into lshc_dictionary (NAME, FORMER_NAME, CONFIG_TYPE, PID, ISONLINE, NOTE)
values ( '京族', '京族', 'ENT_NATION', null, 1, null);

insert into lshc_dictionary (NAME, FORMER_NAME, CONFIG_TYPE, PID, ISONLINE, NOTE)
values ( '苗族', '苗族', 'ENT_NATION', null, 1, null);

insert into lshc_dictionary (NAME, FORMER_NAME, CONFIG_TYPE, PID, ISONLINE, NOTE)
values ( '佤族', '佤族', 'ENT_NATION', null, 1, null);

insert into lshc_dictionary (NAME, FORMER_NAME, CONFIG_TYPE, PID, ISONLINE, NOTE)
values ( '维吾尔族', '维吾尔族', 'ENT_NATION', null, 1, null);

insert into lshc_dictionary (NAME, FORMER_NAME, CONFIG_TYPE, PID, ISONLINE, NOTE)
values ( '乌孜别克族', '乌孜别克族', 'ENT_NATION', null, 1, null);

insert into lshc_dictionary (NAME, FORMER_NAME, CONFIG_TYPE, PID, ISONLINE, NOTE)
values ( '瑶族', '瑶族', 'ENT_NATION', null, 1, null);

insert into lshc_dictionary (NAME, FORMER_NAME, CONFIG_TYPE, PID, ISONLINE, NOTE)
values ( '阿昌族', '阿昌族', 'ENT_NATION', null, 1, null);

insert into lshc_dictionary (NAME, FORMER_NAME, CONFIG_TYPE, PID, ISONLINE, NOTE)
values ( '布朗族', '布朗族', 'ENT_NATION', null, 1, null);

insert into lshc_dictionary (NAME, FORMER_NAME, CONFIG_TYPE, PID, ISONLINE, NOTE)
values ( '德昂族', '德昂族', 'ENT_NATION', null, 1, null);

insert into lshc_dictionary (NAME, FORMER_NAME, CONFIG_TYPE, PID, ISONLINE, NOTE)
values ( '独龙族', '独龙族', 'ENT_NATION', null, 1, null);

insert into lshc_dictionary (NAME, FORMER_NAME, CONFIG_TYPE, PID, ISONLINE, NOTE)
values ( '俄罗斯族', '俄罗斯族', 'ENT_NATION', null, 1, null);

insert into lshc_dictionary (NAME, FORMER_NAME, CONFIG_TYPE, PID, ISONLINE, NOTE)
values ( '鄂伦春族', '鄂伦春族', 'ENT_NATION', null, 1, null);

insert into lshc_dictionary (NAME, FORMER_NAME, CONFIG_TYPE, PID, ISONLINE, NOTE)
values ( '鄂温克族', '鄂温克族', 'ENT_NATION', null, 1, null);

insert into lshc_dictionary (NAME, FORMER_NAME, CONFIG_TYPE, PID, ISONLINE, NOTE)
values ( '高山族', '高山族', 'ENT_NATION', null, 1, null);

insert into lshc_dictionary (NAME, FORMER_NAME, CONFIG_TYPE, PID, ISONLINE, NOTE)
values ( '赫哲族', '赫哲族', 'ENT_NATION', null, 1, null);

insert into lshc_dictionary (NAME, FORMER_NAME, CONFIG_TYPE, PID, ISONLINE, NOTE)
values ( '回族', '回族', 'ENT_NATION', null, 1, null);

insert into lshc_dictionary (NAME, FORMER_NAME, CONFIG_TYPE, PID, ISONLINE, NOTE)
values ( '基诺族', '基诺族', 'ENT_NATION', null, 1, null);

insert into lshc_dictionary (NAME, FORMER_NAME, CONFIG_TYPE, PID, ISONLINE, NOTE)
values ( '景颇族', '景颇族', 'ENT_NATION', null, 1, null);

insert into lshc_dictionary (NAME, FORMER_NAME, CONFIG_TYPE, PID, ISONLINE, NOTE)
values ( '黎族', '黎族', 'ENT_NATION', null, 1, null);

insert into lshc_dictionary (NAME, FORMER_NAME, CONFIG_TYPE, PID, ISONLINE, NOTE)
values ( '满族', '满族', 'ENT_NATION', null, 1, null);

insert into lshc_dictionary (NAME, FORMER_NAME, CONFIG_TYPE, PID, ISONLINE, NOTE)
values ( '蒙古族', '蒙古族', 'ENT_NATION', null, 1, null);

insert into lshc_dictionary (NAME, FORMER_NAME, CONFIG_TYPE, PID, ISONLINE, NOTE)
values ( '纳西族', '纳西族', 'ENT_NATION', null, 1, null);

insert into lshc_dictionary (NAME, FORMER_NAME, CONFIG_TYPE, PID, ISONLINE, NOTE)
values ( '塔吉克族', '塔吉克族', 'ENT_NATION', null, 1, null);

insert into lshc_dictionary (NAME, FORMER_NAME, CONFIG_TYPE, PID, ISONLINE, NOTE)
values ( '彝族', '彝族', 'ENT_NATION', null, 1, null);

insert into lshc_dictionary (NAME, FORMER_NAME, CONFIG_TYPE, PID, ISONLINE, NOTE)
values ( '藏族', '藏族', 'ENT_NATION', null, 1, null);

insert into lshc_dictionary (NAME, FORMER_NAME, CONFIG_TYPE, PID, ISONLINE, NOTE)
values ( '壮族', '壮族', 'ENT_NATION', null, 1, null);

insert into lshc_dictionary (NAME, FORMER_NAME, CONFIG_TYPE, PID, ISONLINE, NOTE)
values ( '其他', '其他', 'ENT_NATION', null, 1, null);

--装修状态
delete from lshc_dictionary where config_type='FIT_STATUS';

insert into lshc_dictionary (NAME, FORMER_NAME, CONFIG_TYPE, PID, ISONLINE, NOTE)
values ( '已装', '已装', 'FIT_STATUS', null, 1, null);

insert into lshc_dictionary ( NAME, FORMER_NAME, CONFIG_TYPE, PID, ISONLINE, NOTE)
values ('未装', '未装', 'FIT_STATUS', null, 1, null);