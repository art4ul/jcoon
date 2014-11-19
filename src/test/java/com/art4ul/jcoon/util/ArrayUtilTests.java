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

/**
 * Created by Artsem Semianenka
 * Website: http://art4ul.com
 * 11/11/14.
 */
public class ArrayUtilTests {

    @Test
    public void getFirstValueTest() {
        Integer element = 1;
        Integer[] testArray = {element, 2, 3};
        Assert.assertNull("getFirstValue method should return NULL in case of input parameter is NULL",
                ArrayUtil.getFirstValue(null));

        Assert.assertNull("getFirstValue method should return NULL in case of input parameter is empty array",
                ArrayUtil.getFirstValue(new Integer[]{}));

        Assert.assertEquals("getFirstValue method should return first element of array",
                element, ArrayUtil.getFirstValue(testArray));
    }

    @Test
    public void getFirstOrDefaultTest() {
        Integer element = 1;
        Integer[] testArray = {element, 2, 3};
        Assert.assertEquals("getFirstOrDefault method should return value of second parameter in case of first input parameter is NULL",
                "Default", ArrayUtil.getFirstOrDefault(null, "Default"));

        Assert.assertEquals("getFirstOrDefault method should return value of second parameter in case of first input parameter is empty array",
                "Default", ArrayUtil.getFirstOrDefault(new String[]{}, "Default"));

        Assert.assertEquals("getFirstOrDefault method should return first element of array",
                element, ArrayUtil.getFirstOrDefault(testArray, new Integer(2)));
    }
}
