package com.epam.jwd.thirdtask.service.parser;

import com.epam.jwd.thirdtask.model.TextComponent;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class SentenceParserTest {

    private final static String SENTENCE_TO_PARSE = "Are you from, ku, po la?";

    @Test
    void parseTest() {
        SentenceParser sentenceParser = SentenceParser.getInstance();
        TextComponent text = sentenceParser.parse(SENTENCE_TO_PARSE);
        assertEquals(9, text.getComponents().size());
    }
}