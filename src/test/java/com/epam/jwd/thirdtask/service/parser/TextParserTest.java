package com.epam.jwd.thirdtask.service.parser;

import com.epam.jwd.thirdtask.comparator.MinimalUnitComparator;
import com.epam.jwd.thirdtask.comparator.ParagraphComparator;
import com.epam.jwd.thirdtask.comparator.SentenceComparator;
import com.epam.jwd.thirdtask.io.FileToOneStringReader;
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
    void parse() throws IOException {
        TextParser parser = TextParser.getInstance();
        String originText = FileToOneStringReader.getInstance()
                .readToOneString("src/test/resources/test_text.txt");
        System.out.println(originText + "\n\n\n\n\n\n\n\n");
        Text text = (Text) parser.parse(originText);
        System.out.println(text.getText());

        TextComponentSorter sorter = TextComponentSorter.getTextSorter();
        Map<Command, Comparator<TextComponent>> commands
                = new HashMap<>();
        commands.put(Command.SORT_PARAGRAPHS, ParagraphComparator.getInstance());
        commands.put(Command.SORT_SENTENCES, SentenceComparator.getInstance());
        commands.put(Command.SORT_MINIMAL_UNITS, MinimalUnitComparator.by('a'));
        sorter.sort(commands, text);
        System.out.println(text.getText());


    }
}