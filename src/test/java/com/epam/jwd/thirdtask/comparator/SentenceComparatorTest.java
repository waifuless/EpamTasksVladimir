package com.epam.jwd.thirdtask.comparator;

import com.epam.jwd.thirdtask.model.Paragraph;
import com.epam.jwd.thirdtask.model.Sentence;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

//todo: make sentences comparing
class SentenceComparatorTest {

    private final ParagraphComparator comparator = ParagraphComparator.getInstance();

    public static Object[][] paragraphCompareTestData() {
        return new Object[][]{
                {Arrays.asList(new Sentence(), new Sentence()), Arrays.asList(new Sentence(), new Sentence()), 0},
                {Arrays.asList(new Sentence(), new Sentence(), new Sentence()),
                        Arrays.asList(new Sentence(), new Sentence()), 1},
                {Arrays.asList(new Sentence(), new Sentence()),
                        Arrays.asList(new Sentence(), new Sentence(), new Sentence()), -1}
        };
    }

    @ParameterizedTest
    @MethodSource("paragraphCompareTestData")
    void compareTest(List<Sentence> sentences1, List<Sentence> sentences2, int result) {
        Paragraph paragraph1 = new Paragraph();
        Paragraph paragraph2 = new Paragraph();
        sentences1.forEach(paragraph1::addComponent);
        sentences2.forEach(paragraph2::addComponent);
        assertEquals(comparator.compare(paragraph1, paragraph2), result);
    }
}