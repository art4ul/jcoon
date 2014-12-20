jCoon
=====
This library is created to simplify the process of creating REST client in Spring Framework. Instead writing a large amount of identical code you should just create java interface and add a few annotations.

[![Build Status](https://travis-ci.org/art4ul/jcoon.svg?branch=master)](https://travis-ci.org/art4ul/jcoon)


Maven repository
-------
Add maven repository :
```xml
<repositories>
     <repository>
         <id>oss-sonatype-staging</id>
         <name>sonatype</name>
         <url>https://oss.sonatype.org/content/groups/staging</url>
     </repository>
</repositories>
```
and dependency to pom.xml:
```xml
<dependency>
    <groupId>com.art4ul</groupId>
    <artifactId>jcoon</artifactId>
    <version>0.3.2-SNAPSHOT</version>
</dependency>
```

Example
-------
To create Rest client you need to perform only 3 steps:

1. Add the following bean definition in spring application context:

    ```xml
    <bean class="com.art4ul.jcoon.bean.RestClientAnnotationBeanPostProcessor"/>
    ```

  or if you want use custom RestTemplate with message converter:

    ```xml
    <bean id="jsonMarshaller" class="org.springframework.http.converter.json.MappingJacksonHttpMessageConverter"/>

    <bean id="restTemplate" class="org.springframework.web.client.RestTemplate" depends-on="jsonMarshaller">
         <property name="messageConverters" ref="jsonMarshaller"/>
    </bean>

    <bean class="com.art4ul.jcoon.bean.RestClientAnnotationBeanPostProcessor" depends-on="restTemplate">
         <constructor-arg index="0" ref="restTemplate"/>
    </bean>
    ```

2. Create interface and discribe methods using Spring Web annotations like: ``` @RequestMapping, @RequestParam, @RequestBody ``` etc.

    ``` java
    @RequestMapping("example1")
    public interface ExampleRestClient {
        @BaseUrl
        void setBaseUrl(String url);

        @RequestMapping(value = "/exampleGet", method = RequestMethod.GET)
        ResultModel exampleGetRequest(@RequestParam("name") String name);

    }
    ```

3. And use it in your code:

    ``` java
    @Service
    public class TestService {

        @RestClient
        private ExampleRestClient restClient;

        public void doTest() {

            restClient.setBaseUrl("http://localhost:8080");

            ResultModel result = restClient.exampleGetRequest("world");
        }
    }
    ```

For more information see the [sample project](https://github.com/art4ul/jcoon-example)
