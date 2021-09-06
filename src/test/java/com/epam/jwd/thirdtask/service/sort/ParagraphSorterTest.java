package com.epam.jwd.thirdtask.service.sort;

import com.epam.jwd.thirdtask.comparator.ParagraphComparator;
import com.epam.jwd.thirdtask.model.Paragraph;
import com.epam.jwd.thirdtask.model.Sentence;
import com.epam.jwd.thirdtask.model.Text;
import com.epam.jwd.thirdtask.model.TextComponent;
import com.epam.jwd.thirdtask.service.Command;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(MockitoExtension.class)
class ParagraphSorterTest {

    private final static Text TEXT_TO_SORT = new Text();
    private final static Map<Command, Comparator<TextComponent>> MAP_OF_COMMANDS = new HashMap<>();
    @Mock
    private TextComponentSorter lowerSorter;
    @InjectMocks
    private ParagraphSorter paragraphSorter;

    @BeforeAll
    static void prepareAll() {
        MAP_OF_COMMANDS.put(Command.SORT_PARAGRAPHS, ParagraphComparator.getInstance());
        List<Paragraph> paragraphs = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            paragraphs.add(new Paragraph());
        }
        Arrays.asList(new Sentence(), new Sentence()).forEach(paragraphs.get(0)::addComponent);
        Arrays.asList(new Sentence(), new Sentence(), new Sentence()).forEach(paragraphs.get(1)::addComponent);
        paragraphs.get(2).addComponent(new Sentence());
        Arrays.asList(new Sentence(), new Sentence(), new Sentence(), new Sentence())
                .forEach(paragraphs.get(3)::addComponent);
        paragraphs.forEach(TEXT_TO_SORT::addComponent);
    }

    @Test
    void sortTest() {
        paragraphSorter.sort(MAP_OF_COMMANDS, TEXT_TO_SORT);
        final List<TextComponent> listOfComponents = TEXT_TO_SORT.getComponents();
        for (int i = 0; i < listOfComponents.size() - 1; i++) {
            assertTrue(listOfComponents.get(i).getComponents().size()
                    < listOfComponents.get(i + 1).getComponents().size());
        }
    }

    @RepeatedTest(2)
    void testSorterIsSingleton() {
        assertSame(ParagraphSorter.getInstance(), ParagraphSorter.getInstance());
    }
}