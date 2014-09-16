工程基于Spring Boot技术构建，配置文件极大简化，框架依赖关系在pom.xml文件中配置。
pom.xml中集成了aeroscout webservice接口

数据库配置文件：
src/main/resource/application.properties

import.sql 会在程序启动时运行。添加SQL语句到import.sql即可实现自动导入数据。


代码结构：

    domain目录，存放实体类。实体类关联数据库中的表格。实体类采用注解的方式实现和数据库表格之间的映射。
    			具体写法参考 City.java.  由于数据库配置属性包含：spring.jpa.hibernate.ddl-auto=create-drop
    			因此系统启动会自动根据City.java内的注解生成数据库表结构，同步到数据库。下次重启再次重新创建表。
    			
    dao目录：存放操作实体类增删改查的dao类。实现CrudRepository接口即可自动具有若干可用的数据库操作方法。也可在dao里按照一定的规则自定义查询方法。
    		参考实现：CityRepository
    
    service目录：存放处理业务逻辑的service。一般service会调用dao（repository）
    
    web目录： 存放处理前台浏览器请求的controller类。 通过@RequestMapping注解绑定前台请求路径，一般会调用service提供的方法。
    
    timer目录： 存放定时运行的业务逻辑。数据同步功能可在此添加。具体写法请参考TimerTask.java

运行方法：
      右键选中 sync项目->run as ->Maven build ->Goals
      在弹出的页面中Goals栏 填写：  spring-boot:run
      
      保存即可。下次选中 Run As ：Maven Build 则自动运行。
      
注意事项：
	因采用maven构建，项目所依赖的jar包均通过互联网下载。考虑到国内网络情况，首次下载可能耗时很久。
	AeroScout所需要的依赖包已经配置到pom.xml中，会自动下载，不需要单独拷贝。
	
		