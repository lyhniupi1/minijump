## 项目结构
### spring启动容器
demo\src\main\java\com\bocom\run\DemoApplication.java

### 容器bean位置
demo\src\main\java\com\bocom 目录下有注解的都是bean

demo\src\config\sqlmap 目录下放mybatis的xml文件，其中包含sql语句

demo\src\main\java\com\bocom\epcc\dao 目录下放dao类，用于持有mybatis的sqlmap，进行增删改查

### 实现什么
通过 demo\src\main\java\com\bocom\run\Controller.java 接收请求，
调用 demo\src\main\java\com\bocom\epcc\action
再调用 demo\src\main\java\com\bocom\epcc\services
再调用 demo\src\main\java\com\bocom\epcc\dao
转换为定义的 demo\src\main\java\com\bocom\epcc\entity
返回给外围