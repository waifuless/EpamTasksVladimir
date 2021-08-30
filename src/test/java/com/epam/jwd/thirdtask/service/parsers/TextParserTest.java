package com.epam.jwd.thirdtask.service.parsers;

import com.epam.jwd.thirdtask.model.Text;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class TextParserTest {

    @Test
    void parse() {
        TextParser textParser = new TextParser();
        String originText = readLineByLineJava8("src/test/resources/test_text.txt");
        //System.out.println(originText+"\n\n\n\n\n\n\n\n");
        Text text = (Text) textParser.parse(originText);
        System.out.println(text.getText());
    }

    private static String readLineByLineJava8(String filePath)
    {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines( Paths.get(filePath), StandardCharsets.UTF_8))
        {
            stream.forEach(s -> contentBuilder.append(s).append("\n"));
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        return contentBuilder.toString();
    }
}