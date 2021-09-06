package com.epam.jwd.thirdtask.service.parser;

import com.epam.jwd.thirdtask.model.Sentence;
import com.epam.jwd.thirdtask.model.TextComponent;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ParagraphParserTest {

    private final static String PARAGRAPH_TO_PARSE = "    Hola! Are you from lola? Ke, pu, ka! Free potato.";

    @Mock
    private ComponentParser lowerHandler;

    @InjectMocks
    private ParagraphParser paragraphParser;

    @Test
    void parseTest() {
        when(lowerHandler.parse(anyString())).thenReturn(new Sentence());
        TextComponent paragraph = paragraphParser.parse(PARAGRAPH_TO_PARSE);
        assertEquals(4, paragraph.getComponents().size());
    }
}