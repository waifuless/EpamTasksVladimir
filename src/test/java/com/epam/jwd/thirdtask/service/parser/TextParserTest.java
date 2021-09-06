package com.epam.jwd.thirdtask.service.parser;

import com.epam.jwd.thirdtask.model.Paragraph;
import com.epam.jwd.thirdtask.model.TextComponent;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TextParserTest {

    private final static String TEXT_TO_PARSE = "    AAAsdasd.\n" +
            "\tBuba!\n" +
            "    Lupo.\n";

    @Mock
    private ComponentParser lowerHandler;

    @InjectMocks
    private TextParser textParser;

    @Test
    void parseTest() {
        when(lowerHandler.parse(anyString())).thenReturn(new Paragraph());
        TextComponent text = textParser.parse(TEXT_TO_PARSE);
        assertEquals(3, text.getComponents().size());
    }

    @RepeatedTest(2)
    void testParserIsSingleton() {
        assertSame(TextParser.getInstance(), TextParser.getInstance());
    }
}