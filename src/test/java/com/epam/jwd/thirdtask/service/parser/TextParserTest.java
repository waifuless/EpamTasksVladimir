package com.epam.jwd.thirdtask.service.parser;

import com.epam.jwd.thirdtask.model.Text;
import com.epam.jwd.thirdtask.service.Commands;
import com.epam.jwd.thirdtask.service.sort.TextSorter;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.EnumSet;
import java.util.stream.Stream;

class TextParserTest {

    @Test
    void parse() {
        TextParser parser = TextParser.getInstance();
        String originText = readLineByLineJava8("src/test/resources/test_text.txt");
        System.out.println(originText+"\n\n\n\n\n\n\n\n");
        Text text = (Text) parser.parse(originText);
        System.out.println(text.getText());
        TextSorter sorter = TextSorter.getInstance();
        EnumSet<Commands> commands = EnumSet.of(Commands.SORT_PARAGRAPHS, Commands.SORT_SENTENCES);
        sorter.sort(commands, text);
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