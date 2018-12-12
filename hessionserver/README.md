简单说来，Hessian是一个轻量级的RPC框架

它基于HTTP协议传输，使用Hessian二进制序列化，对于数据包比较大的情况比较友好。

但是它的参数和返回值都需要实现Serializable接口。

spring版本配置
在web.xml里面配置
<!-- servlet -->
	<servlet>
		<servlet-name>hessian</servlet-name>
		<servlet-class>org.springframework.web.servlet.DispatcherServlet</servlet-class>
		<load-on-startup>1</load-on-startup>
		<init-param>
			<param-name>contextConfigLocation</param-name>
			<param-value>
				classpath:/META-INF/XXX.xml
			</param-value>
		</init-param>
	</servlet>
	<servlet-mapping>
		<servlet-name>hessian</servlet-name>
		<url-pattern>/hessian/*</url-pattern>
	</servlet-mapping>
	
在XXX_hessian.xml配置
	<!-- hessian -->
	<bean name="/manualTxn" class="org.springframework.remoting.caucho.HessianServiceExporter">
		<property name="service" ref="sdp.pcs.ManualTxnHandler" />
		<property name="serviceInterface" value="com.shengpay.pos.pcs.api.ManualTxn" />
	</bean>

	
调用的时候
<bean id="fundApi" class="org.springframework.remoting.caucho.HessianProxyFactoryBean">
        <property name="serviceUrl" value="${remote.url}"/>
        <property name="serviceInterface" value="com.XXXXApi"/>
		<property name="connectTimeout" value="${ConnectionTimeout}" />
		<property name="readTimeout" value="${ReceiveTimeout}" />
    </bean>	
    
    
  序列化与反序列化
  
  Serialization
  Object obj = ...;
  
  OutputStream os = new FileOutputStream("test.xml");
  Hessian2Output out = new Hessian2Output(os);
 
  out.writeObject(obj);
  os.close();
  
  
  Deserialization
  InputStream is = new FileInputStream("test.xml");
  Hessian2Input in = new Hessian2Input(is);
  
  Object obj = in.readObject(null);
  is.close();  