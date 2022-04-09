-- auto-generated definition
create table table_user
(
    id     int auto_increment comment '用户id'
        primary key,
    name   varchar(255)  not null comment '用户名',
    pwd    varchar(255)  not null comment '密码',
    sign   varchar(255)  not null comment '签名',
    avatar varchar(255)  not null comment '头像',
    type   int default 0 not null comment '0:普通用户
1:管理员'
)
    comment '用户表';