# gradleInitProDemo

运行环境：eclipse+mysql就可以启动项目，不需要安装其它服务比如：tomcat,maven。其实后台还是借助类似这些服务，但开发时不需要关心这些了。
gradle初始项目demo,jetty简单main运行项目
mysql 脚本在WEB-INF/change.sql文件

#项目框架

###springMVC,spring,mybatis,shiro权限,mysql,html+json数据为前端显示（restful风格），没有jsp页面为了前后端分离，随时换掉前端考虑

#启动步骤

###1：建好数据库，并执行脚本
###2：在根目录下执行 gradlew eclipse 会自动初始gradle环境，并下载相关jar
###3：修改配置初始化等级为0(pro1.properties文件 initLevel=0)，会自动扫描系统注解菜单，并初始化用户admin admin和角色数据，并写入到数据库
###4:运行SimpleMain mian方法启动项目 
###5:打开localhost:9808   输入admin admin即可登陆，所有菜单 角色数据已有
###6：第二次启动时要把(pro1.properties文件 initLevel=0)改为2，或其它，根据需求修改，详情请看说明。
###发张效果图看看


![图1](/doc/imgEffect/20160609145114.png)
![图2](/doc/imgEffect/20160607140339.png)
![图3](/doc/imgEffect/20160609145311.png)
![图3](/doc/imgEffect/20160609145345.png)
![图3](/doc/imgEffect/20160609153358.png)
![图3](/doc/imgEffect/20160619193031.png)
![图3](/doc/imgEffect/20160619193054.png)

