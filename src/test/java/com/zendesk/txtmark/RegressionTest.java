package com.zendesk.txtmark;

/*
 * Copyright 2015 dbowen.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import com.github.rjeschke.txtmark.Processor;
import java.io.File;
import java.io.IOException;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Ignore;

/**
 *
 */
public class RegressionTest {
    
    String[][] stringTests = {
        {"foo\n<\nbar", "<p>foo\n&lt;\nbar</p>\n"},
        {"foo\r<\nbar", "<p>foo\n&lt;\nbar</p>\n"},
        {"foo\n<\rbar", "<p>foo\n&lt;\nbar</p>\n"},
        {"<", "<p>&lt;</p>\n"},
        {"# #######  ", "<h1></h1>\n"},
        {"[3]: ", "<p>[3]:</p>\n"},
        {"[ ![](http://xyz.com/$(K,!hy!~~_32-260x260-0-0.JPG) ](http://foobar.com/view?tid=97)<br />",
         "<p><a href=\"http://foobar.com/view?tid=97\"> ![](http://xyz.com/$(K,!hy!~~_32-260x260-0-0.JPG) </a><br  /></p>\n"
        },
    };
    
    @Test
    public void testStrings() {
        for (String[] test : stringTests) {
            assertEquals(test[1], Processor.process(test[0]));
        }
    }

    @Test
    public void testFiles() {
        File testDir = new File("src/test/resources/tests");
        File[] tests = testDir.listFiles();
        for (File test : tests) {
            try {
                Processor.process(test);
            }
            catch (IOException ioe) {
                ioe.printStackTrace();
                fail(ioe.getMessage());
            }
        }
    }
    
}
