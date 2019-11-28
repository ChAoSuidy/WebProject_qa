## 项目来源：
师承码匠社区，使用SpringBoot做一个对标https://elasticsearch.cn的论坛网站

## 资料：
[码匠社区](http://www.mawen.co/)  
[Spring官网](https://spring.io/)  
[bilibili本项目学习视频](https://www.bilibili.com/video/av50200264/)


## 工具：
[Github](https://github.com/)  
[bootstrap](https://www.bootcss.com/)  
[Authorizing OAuth Apps](https://developer.github.com/apps/building-oauth-apps/authorizing-oauth-apps/)  
[okhttp](https://square.github.io/okhttp/)  
[fastJson](https://mvnrepository.com/)

## 脚本：
```sql
-- auto-generated definition
create table tab_user
(
    id           int auto_increment primary key,
    account_id   varchar(100) null,
    name         varchar(50)  null,
    token        char(36)     null,
    gmt_create   bigint       null,
    gmt_modified bigint       null
);
```