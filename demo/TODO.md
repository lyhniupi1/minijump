1、在sqlite中创建一张邮箱表EPCC_EMAIL_CTRL:
    邮箱EEC_EMAIL作为主键
    用户名 EEC_USERNAME
    description描述 EEC_DESC
    创建时间    EEC_CREATETIME
    操作柜员号  EEC_MODTELLER
    最后更新时间    EEC_LASTMODTIME

2、demo\src\config\sqlmap 目录下放mybatis的xml文件，其中包含sql语句
    对EPCC_EMAIL_CTRL表进行增删改查

3、demo\src\main\java\com\bocom\epcc\dao 目录下放dao类，用于持有mybatis的sqlmap，进行增删改查

4、业务流程：
通过 demo\src\main\java\com\bocom\run\Controller.java 接收请求
    url是 handleEmail.json ，operatetype是操作类型，对邮箱表进行增删改查
调用 demo\src\main\java\com\bocom\epcc\action
再调用 demo\src\main\java\com\bocom\epcc\services
再调用 demo\src\main\java\com\bocom\epcc\dao
转换为定义的 demo\src\main\java\com\bocom\epcc\entity
返回给外围