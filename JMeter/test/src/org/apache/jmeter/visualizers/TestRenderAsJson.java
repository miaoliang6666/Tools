package org.apache.jmeter.visualizers;

import static org.junit.Assert.assertEquals;

import java.lang.reflect.Method;
import org.junit.Before;
import org.junit.Test;

public class TestRenderAsJson {

    private Method prettyJSON;
    private final String TAB = ":   ";

    private String prettyJSON(String prettify) throws Exception {
        return (String) prettyJSON.invoke(null, prettify);
    }

    @Before
    public void setUp() throws Exception {
        prettyJSON = RenderAsJSON.class.getDeclaredMethod("prettyJSON",
                String.class);
        prettyJSON.setAccessible(true);
    }

    @Test
    public void testRenderResultWithLongStringBug54826() throws Exception {
        StringBuilder json = new StringBuilder();
        json.append("\"customData\":\"");
        for (int i = 0; i < 100; i++) {
            json.append("somenotsorandomtext");
        }
        json.append("\"");

        assertEquals("{\n" + TAB + json.toString() + "\n}",
                prettyJSON("{" + json.toString() + "}"));
    }

    @Test
    public void testRenderResultSimpleObject() throws Exception {
        assertEquals("{\n}", prettyJSON("{}"));
    }

    @Test
    public void testRenderResultSimpleArray() throws Exception {
        assertEquals("[\n]", prettyJSON("[]"));
    }

    @Test
    public void testRenderResultSimpleNumber() throws Exception {
        assertEquals("42", prettyJSON("42"));
    }

    @Test
    public void testRenderResultSimpleString() throws Exception {
        assertEquals("Hello World", prettyJSON("Hello World"));
    }

    @Test
    public void testRenderResultSimpleStructure() throws Exception {
        assertEquals(
                "{\n" + TAB + "\"Hello\": \"World\", \n" + TAB + "\"more\": \n"
                        + TAB + "[\n" + TAB + TAB + "\"Something\", \n" + TAB
                        + TAB + "\"else\", \n" + TAB + "]\n}",
                prettyJSON("{\"Hello\": \"World\", \"more\": [\"Something\", \"else\", ]}"));
    }

}
