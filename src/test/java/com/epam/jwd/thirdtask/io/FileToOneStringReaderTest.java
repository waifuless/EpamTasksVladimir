package com.epam.jwd.thirdtask.io;

import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class FileToOneStringReaderTest {

    private final static String TEXT_IN_FILE = "    Hi, . I am: Vova. Lola.\n" +
            "Asdasdd asdasasd asddsaas. Koka pola la. AAAAAAAAAAAA!\n" +
            "    I am (20+20*(1+(500/5))) Karl5.\n" +
            "    Lol? (((1+2)*3)/6/2*4+1).\n" +
            "    (1+3+2*(2*2)-3+(100/4)/5).\n" +
            "    Invalid expressions: (5/0), (5///2).\n";
    private final static String TEXT_FILE_PATH = "src/test/resources/test_text.txt";

    @Test
    void readToOneString() throws IOException {
        FileToOneStringReader reader = FileToOneStringReader.getInstance();
        assertEquals(TEXT_IN_FILE ,reader.readToOneString(TEXT_FILE_PATH));
    }
}