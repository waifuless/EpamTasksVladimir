package com.epam.jwd.thirdtask.service.parser;

import com.epam.jwd.thirdtask.comparator.ParagraphComparator;
import com.epam.jwd.thirdtask.comparator.SentenceComparator;
import com.epam.jwd.thirdtask.model.Text;
import com.epam.jwd.thirdtask.model.TextComponent;
import com.epam.jwd.thirdtask.service.Command;
import com.epam.jwd.thirdtask.service.sort.TextComponentSorter;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

class TextParserTest {

    @Test
    void parse() {
        TextParser parser = TextParser.getInstance();
        String originText = readLineByLineJava8("src/test/resources/test_text.txt");
        System.out.println(originText+"\n\n\n\n\n\n\n\n");
        Text text = (Text) parser.parse(originText);
        System.out.println(text.getText());
        TextComponentSorter sorter = TextComponentSorter.getTextSorter();
        Map<Command, Comparator<TextComponent>> commands
                = new HashMap<>();
        commands.put(Command.SORT_PARAGRAPHS, new ParagraphComparator());
        commands.put(Command.SORT_SENTENCES, new SentenceComparator());
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