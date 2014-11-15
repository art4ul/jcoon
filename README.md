jCoon
=====

Simple REST client for Java based on interfaces


[![Build Status](https://travis-ci.org/art4ul/jcoon.svg?branch=master)](https://travis-ci.org/art4ul/jcoon)

Summary
-------
This library is created to simplify the process of creating REST client in Java. Instead writing a large amount of identical code you should just create java interface and add a few annotations.

Example
-------
To create Rest client just create interface like this:

    @RequestMapping("example1")
    public interface ExampleRestClient {

        @BaseUrl
        void setBaseUrl(String url);

        @RequestMapping(value = "/exampleGet", method = RequestMethod.GET)
        ResultModel exampleGetRequest(@RequestParam("name") String name);

    }

And use it in your code:

    @Service
    public class TestService {

        @RestClient
        private ExampleRestClient restClient;

        public void doTest() {

            restClient.setBaseUrl("http://localhost:8080");

            ResultModel result = restClient.exampleGetRequest("world");
        }
    }

For more information download [sample project](https://github.com/art4ul/jcoon/tree/master/samples)
