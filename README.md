## 快速实现

mvn archetype:generate -DarchetypeArtifactId=jersey-quickstart-grizzly2 -DarchetypeGroupId=org.glassfish.jersey.archetypes -DinteractiveMode=false -DgroupId=com.example -DartifactId=simple-service -Dpackage=com.example -DarchetypeVersion=2.9

以三层逻辑划分，资源类位于逻辑层的最高层——API层，其下为Service层和数据访问层，在三层逻辑中，API层用于对外公布接口，
对于REST服务，API层的资源类用于对外公布REST服务接口。其下的两层，REST应用的开发和标准Web开发的区别不是很大。

    //1: 资源路径
    @Path("myresource")
    public class MyResource 
    // 2: 资源方法
    @GET
        @Produces(MediaType.TEXT_PLAIN)
        public String getIt() {
            return "Got it!";
        }
    
前面的rest是一个web工程，将rest配置为servlet，相应的是启动服务的是tomcat web服务器，如果不用Tomcat，用什么？
JavaSE的应用需要定义一个入口类来启动服务器并加载项目资源，而JavaEE的应用则不需要，本身扮演着入口类的角色。

    (1) 服务器路径
    public static final String BASE_URI = "http://localhost:8080/myapp/";
    (2) 资源， ResourceConfig 加载在package中定义的资源，有类，有res
    会根据该包中所含类的REST资源路径的注解，子内存中做好映射。
    final ResourceConfig rc = new ResourceConfig().packages("com.example");
    (3) Grizzly Http 服务器
    return GrizzlyHttpServerFactory.createHttpServer(URI.create(BASE_URI), rc);
    
Grizzly
    
    grizzly是Jersey提供的集成测试追踪默认的内嵌测试服务器，有了Grizzly，就可以在不启动额外Servlet容器服务器的情况下，测试
    REST服务。
    
测试

    private HttpServer server;  服务器类
    private WebTarget target;   客户端资源定位类 WebTarget
    
    准备环境
    启动服务器
    实例化一个client，模拟用户，传给客户端一个url，模拟浏览器，获取到客户端资源定位类实例 Target
    
    测试方法 testGetIt()
    已经定位资源类 target,像默认的资源位置 myresource发出get请求  Path位置
    
    
    
    