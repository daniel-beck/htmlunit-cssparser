/*
 * Copyright (c) 2019 Ronald Brill.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.gargoylesoftware.css.parser.media;

import org.junit.Assert;
import org.junit.Test;

import com.gargoylesoftware.css.dom.CSSValueImpl;
import com.gargoylesoftware.css.dom.Property;

/**
 * Testcases for {@link MediaQuery}.
 * @author Ronald Brill
 */
public class MediaQueryTest {

    /**
     * @throws Exception if any error occurs
     */
    @Test
    public void testToString() throws Exception {
        MediaQuery mq = new MediaQuery("test");
        Assert.assertEquals("test", mq.toString());

        mq = new MediaQuery("test", false, false);
        Assert.assertEquals("test", mq.toString());

        mq = new MediaQuery("test", true, false);
        Assert.assertEquals("only test", mq.toString());

        mq = new MediaQuery("test", false, true);
        Assert.assertEquals("not test", mq.toString());
    }

    /**
     * @throws Exception if any error occurs
     */
    @Test
    public void properties() throws Exception {
        Property prop = new Property("prop", new CSSValueImpl(null), false);
        MediaQuery mq = new MediaQuery("test");
        mq.addMediaProperty(prop);
        Assert.assertEquals("test and (prop: )", mq.toString());

        final CSSValueImpl value = new CSSValueImpl(null);
        value.setCssText("10dpi");
        prop = new Property("prop", value, false);
        mq = new MediaQuery("test", true, false);
        mq.addMediaProperty(prop);
        Assert.assertEquals("only test and (prop: 10dpi)", mq.toString());

        Assert.assertEquals(1, mq.getProperties().size());

        prop = new Property("min-foo", value, false);
        mq.addMediaProperty(prop);
        Assert.assertEquals("only test and (prop: 10dpi) and (min-foo: 10dpi)", mq.toString());
    }

    /**
     * @throws Exception if any error occurs
     */
    @Test
    public void media() throws Exception {
        final MediaQuery mq = new MediaQuery("test");
        Assert.assertEquals("test", mq.getMedia());
    }
}
