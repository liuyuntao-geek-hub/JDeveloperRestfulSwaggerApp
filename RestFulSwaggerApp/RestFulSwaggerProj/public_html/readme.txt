Swagger General
-----------------------------------------
http://idratherbewriting.com/pubapis_swagger/
	- Example and tricks

http://swagger.io/getting-started/

	- Top-down: use Swagger Editor
	- Bottom-up
		- Jax-RS = Restful Service using Java
			- https://github.com/swagger-api/swagger-core/wiki/Swagger-Core-JAX-RS-Project-Setup-1.5.X
---------
Download Swagger project: 
https://dzone.com/articles/swagger-make-developers-love
https://github.com/reta/jax-rs-2.0-swagger
----------
		- Node.js
			- https://scotch.io/tutorials/speed-up-your-restful-api-development-in-node-js-with-swagger
================================================


Swagger General
--------------------------------------
Step 1 - Concepts
	- Swagger is working with REST API as documentation
	- Readable to human and Computer
	- Provide Web Interface = user can find out what the REST Service is using input and output => can invoke directly from the web

Step 2 - Top-down
	- Using Swagger Editor => create definition => use Swagger Codegen => create code for server implementation

Step 3 - Bottom-up
	- Already have Restful API = need to create Swagger Definition
		= Manual: using Swagger Editor
		= Auto:
			- Node.js
			- JAX-RS (java solution): This will auto create Swagger by adding annotation into java code
			https://github.com/swagger-api/swagger-core/wiki/Swagger-Core-JAX-RS-Project-Setup-1.5.X
Step 5 - User of API
	- Server has Restful API + Swagger
	- Client can use Swagger UI + Swagger Codegen to create client code automatically

Step 6 - Implementation
	- Spring Web Application = work with Swagger plug in directly
	- Web.xml & Servlet = work with Swagger

=================================================

Swagger Example with Web.xml & Servlet
(JDeveloper example)
-------------------------------------------------
Section 1 - Restful API in JDev
---------------------------
11g 
---------------------------------------------------------------------------------------------------
http://one-size-doesnt-fit-all.blogspot.com/2010/10/rest-web-services-in-oracles-jdeveloper.html
E:\java\jersey_rest\REST Web Services in Oracle's JDeveloper in 10 easy steps.mht
-----------------------------------------------------------------------------
Step 0 - in eClipse Maven Project: DemoMavenEclipseProject
-----------------
<dependency>
  <groupId>io.swagger</groupId>
  <artifactId>swagger-jersey-jaxrs</artifactId>
  <version>1.5.0</version>
</dependency>
----------------------
	-> run -> Maven Test
	-> check the folders in: MavenWebAppRestSwaggerSample
	

Step 1 - Create Generic Application:
	Path: E:\java\workspace11117\SquareTwoStudyWorkSpace\RestFulSwaggerApp
	Project: RestFulSwaggerProj
	Application: RestFulSwaggerApp

Step 2 - Download Jersey
https://jersey.java.net/
	E:\java\jersey_rest\jersey-bundle-1.19.jar


Step 3 - Create new -> folder:
	E:\java\workspace11117\SquareTwoStudyWorkSpace\RestFulSwaggerApp\RestFulSwaggerProj\lib
	copy the E:\java\jersey_rest\jersey-bundle-1.19.jar here
	copy the E:\java\Maven\eClipseRepository\io\swagger\swagger-jersey-jaxrs\1.5.0\swagger-jersey-jaxrs-1.5.0.jar here
	copy the E:\java\Maven\eClipseRepository\io\swagger\swagger-jaxrs\1.5.0\swagger-jaxrs-1.5.0.jar
	copy the E:\java\Maven\eClipseRepository\io\swagger\swagger-core\1.5.0\swagger-core-1.5.0.jar
	copy the E:\java\Maven\eClipseRepository\io\swagger\swagger-annotations\1.5.0\swagger-annotations-1.5.0.jar
	copy the E:\java\Maven\eClipseRepository\io\swagger\swagger-models\1.5.0\swagger-models-1.5.0.jar
	copy the E:\java\Maven\eClipseRepository\com\fasterxml\jackson\jaxrs\jackson-jaxrs-json-provider\2.4.2\jackson-jaxrs-json-provider-2.4.2.jar
	copy the E:\java\Maven\eClipseRepository\com\fasterxml\jackson\jaxrs\jackson-jaxrs-base\2.4.2\jackson-jaxrs-base-2.4.2.jar
	copy the E:\java\Maven\eClipseRepository\com\fasterxml\jackson\core\jackson-core\2.8.1\jackson-core-2.8.1.jar
	copy the E:\java\Maven\eClipseRepository\org\slf4j\slf4j-api\1.7.12\slf4j-api-1.7.12.jar
	copy the E:\java\Maven\eClipseRepository\com\fasterxml\jackson\core\jackson-databind\2.6.3\jackson-databind-2.6.3.jar
	copy the E:\java\Maven\eClipseRepository\com\fasterxml\jackson\dataformat\jackson-dataformat-xml\2.4.2\jackson-dataformat-xml-2.4.2.jar
	copy the E:\java\Maven\eClipseRepository\com\fasterxml\jackson\dataformat\jackson-dataformat-yaml\2.4.2\jackson-dataformat-yaml-2.4.2.jar
	*****
	check the list in: E:\java\workspace11117\SquareTwoStudyWorkSpace\RestFulSwaggerApp\RestFulSwaggerProj\lib

Step 4 - project -> properties -> lib & path -> add jar file

Step 5 - Create pojo:
--------------
package com.RestFulSwaggerApp;

public class RestPojo
{
    public RestPojo()
    {
        super();
    }
    
    public String SayHello( String inputName)
    {
        return "hello: "+ inputName;
    }
}
----------------------

Step 6 - Add annotation to class level:

import javax.ws.rs.Path;
@Path("hello")
public class RestPojo

Step 7 - click the yellow point on left of @Path("hello") -> confiure web.xml for Jersey JAX-RS web Services
	= This will create web.xml
-----------------------------
  <servlet>
    <servlet-name>jersey</servlet-name>
    <servlet-class>com.sun.jersey.spi.container.servlet.ServletContainer</servlet-class>
    <load-on-startup>1</load-on-startup>
  </servlet>
  <servlet-mapping>
    <servlet-name>jersey</servlet-name>
    <url-pattern>/resource/*</url-pattern>
  </servlet-mapping>
-----------------------------
  <servlet>
    <servlet-name>jersey</servlet-name>
    <servlet-class>com.sun.jersey.spi.container.servlet.ServletContainer</servlet-class>
       <init-param>
            <param-name>com.sun.jersey.config.property.packages</param-name>
            <param-value>
                io.swagger.jaxrs.json,
                io.swagger.jaxrs.listing,
                com.RestFulSwaggerApp
            </param-value>
        </init-param>
    <load-on-startup>1</load-on-startup>
  </servlet>
  
      <servlet>
        <servlet-name>Jersey2Config</servlet-name>
        <servlet-class>io.swagger.jaxrs.config.DefaultJaxrsConfig</servlet-class>
        <init-param>
            <param-name>api.version</param-name>
            <param-value>1.0.0</param-value>
        </init-param>
        <init-param>
            <param-name>swagger.api.basepath</param-name>
            <param-value>http://127.0.0.1:7101/RestHelloSampleContext/resource</param-value>
        </init-param>
        <load-on-startup>2</load-on-startup>
    </servlet>
----------------------------------------

Step 8 - Add get & produce to the function:

    
    @GET
    @Path("/SayHello")
    @Produces(MediaType.APPLICATION_JSON)
    public String SayHello(@QueryParam("inputName")  String inputName)
    {
        return "hello: "+ inputName;
    }

Step 9 - Change the root-context path:
	E:\java\workspace11117\SquareTwoStudyWorkSpace\RestFulSwaggerApp\RestFulSwaggerProj\RestFulSwaggerProj.jpr
---------------
   <hash n="oracle.jdeveloper.model.J2eeSettings">
      <value n="j2eeWebAppName" v="RestFulSwaggerApp-RestFulSwaggerProj-webapp"/>
      <value n="j2eeWebContextRoot" v="RestFulSwaggerApp-RestFulSwaggerProj-context-root"/>
   </hash>
--------------
---------------
   <hash n="oracle.jdeveloper.model.J2eeSettings">
      <value n="j2eeWebAppName" v="RestFulSimpleServerApp-RestFulSimpleServerProj-webapp"/>
      <value n="j2eeWebContextRoot" v="RestHelloSampleContext"/>
   </hash>
--------------
Right click RestFulSwaggerProj -> Properties -> Java EE Application -> Java EE Web Context Root


Step 10 - Save all + right click RestPojo.java -> test web service
	URL should be: http://127.0.0.1:7101/RestHelloSampleContext/resource/hello/SayHello?inputName=Yuntao%20Liu
                       http://localhost:7101/RestHelloSampleContext/resource/hello/SayHello?inputName=Yuntao%20Liu

Step 11 - Create Folder inside WEB-ING/lib
	E:\java\workspace11117\SquareTwoStudyWorkSpace\RestFulSwaggerApp\RestFulSwaggerProj\public_html\WEB-INF\lib
	copy: E:\java\workspace11117\SquareTwoStudyWorkSpace\RestFulSwaggerApp\RestFulSwaggerProj\lib
		to here

http://localhost:7101/RestHelloSampleContext/resource/swagger.json

Step 12 - Edit the Pojo:

---------------------
@Path("/hello")
@Api(value = "/hello", description = "manage message class")
public class RestPojo
{
    public RestPojo()
    {
        super();
    }
    
    @GET
    @Path("/SayHello")
    @Produces(MediaType.APPLICATION_JSON)
    @ApiOperation( 
    	    value = "Displaying message", 
    	    notes = "Get input name and display", 
    	    response = String.class ,
    	    responseContainer = "List"
    	)
    public String SayHello(@QueryParam("inputName")  String inputName)
    {
      //  return "Hello: "+ inputName;
        return "{'name':'"+ inputName + "', 'Description':'Test on Server'}";
    }
}

-----------------------------------

Step 13 - Swagger UI 
https://tech.homeaway.com/development/2016/06/02/generating-swagger-spec.html
https://github.com/swagger-api/swagger-ui/tree/master/dist
https://github.com/swagger-api/swagger-ui
	- download the code
	to: E:\Actuate\Actuate_CheatCode\Java\OracleStudy\Swagger_Jax-RS_Restful\swagger-ui-master\swagger-ui-master\dist
Action 1 - Download the lastest UI code:
	E:\Actuate\Actuate_CheatCode\Java\OracleStudy\Swagger_Jax-RS_Restful\swagger-ui-master

Action 2 - Copy
E:\Actuate\Actuate_CheatCode\Java\OracleStudy\Swagger_Jax-RS_Restful\swagger-ui-master\swagger-ui-master\dist\*
	To
E:\java\workspace11117\SquareTwoStudyWorkSpace\RestFulSwaggerApp\RestFulSwaggerProj\public_html

Action 3 - Edit the E:\java\workspace11117\SquareTwoStudyWorkSpace\RestFulSwaggerApp\RestFulSwaggerProj\public_html\index.html
	url = "http://petstore.swagger.io/v2/swagger.json";
	url = "http://localhost:7101/RestHelloSampleContext/resource/swagger.json";
Action 5 - redeploy and check:

	http://localhost:7101/RestHelloSampleContext/index.html

Step 14 - Get error: http://localhost:7101/RestHelloSampleContext/index.html
	Can't read from server. It may not have the appropriate access-control-origin settings.
	Resolve: make sure 
		http://localhost:7101/RestHelloSampleContext/index.html
		http://localhost:7101/RestHelloSampleContext/resource/swagger.json
			= localhost or 127.0.0.1 = need to be same on both html




Step 15 - another Examples

	https://github.com/swagger-api/swagger-samples/tree/master/java/java-jersey-jaxrs
	E:\Actuate\Actuate_CheatCode\Java\OracleStudy\Swagger_Jax-RS_Restful\swagger-samples-master




Step 16 - Deploy to 192.168.1.21:7004 - this sthe Soa_server1
	in web.xml:
		<param-value>http://192.168.1.21:7004/RestHelloSampleContext/resource</param-value>


	Right click RestFulSimpleServerProj -> deploy -> new deploy profile -> War File -> name: RestSwaggerExample
		= this will create the profile
	Right click RestFulSimpleServerProj -> deploy -> RestFulSimpleServer -> Deploy to Application Server -> Soa12cServer
		- this will actual deploy -> only select the soa_cluster1 => osb_server1 & soa_server1 will have it
	http://192.168.1.21:7004/RestHelloSampleContext/resource/hello/SayHello?inputName=Yuntao%20Liu
	http://192.168.1.21:7004/RestHelloSampleContext/resource/swagger.json
	http://192.168.1.21:7004/RestHelloSampleContext/index.html

Error:
	Deployment failed because [11:03:42 PM] Weblogic Server Exception: weblogic.application.ModuleException: java.lang.VerifyError: Cannot inherit from final class
Root cause:
	guava library conflict: 
Solution:
	remove guava lib and use the default old Oracle guava library.
******************************************************************
