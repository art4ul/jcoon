/*
 * Copyright (C) 2014 Artsem Semianenka (http://art4ul.com/)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.art4ul.jcoon.util;

import junit.framework.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;


/**
 * Created by Artsem Semianenka
 * Website: http://art4ul.com
 * 11/11/14.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/applicationContext.xml"})
public class HttpRequestUtilTests {


    /*@Test
    public void addHeadersTest() {
        Context mockContext = mock(RestClientContext.class);
        when(mockContext.getHttpHeaders()).thenReturn(new HttpHeaders());
        HttpRequestUtil.addHeaders(mockContext, new String[]{"key1=value1;key2=value2", "key3=value3"});
        Assert.assertEquals("addHeaders should return 3 elements of HttpHeaders",
                3, mockContext.getHttpHeaders().size());
        Assert.assertEquals("value2", mockContext.getHttpHeaders().get("key2").get(0));
    }*/

    @Test
    public void getAcceptedTypesTest() {
        String[] array = new String[]{"value1", "value2", "value3", "value4"};
        List<MediaType> mediaTypes = HttpRequestUtil.getAcceptedTypes(array);
        Assert.assertEquals(4, mediaTypes.size());

        Assert.assertEquals("getAcceptedTypes method should return empty list in case of input parameter is NULL",
                0, HttpRequestUtil.getAcceptedTypes(null).size());

    }
}
