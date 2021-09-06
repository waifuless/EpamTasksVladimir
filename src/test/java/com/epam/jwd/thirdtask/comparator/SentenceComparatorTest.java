package com.epam.jwd.thirdtask.comparator;

import com.epam.jwd.thirdtask.model.MinimalUnit;
import com.epam.jwd.thirdtask.model.Sentence;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;

class SentenceComparatorTest {

    private final SentenceComparator comparator = SentenceComparator.getInstance();

    public static Object[][] sentencesCompareTestData() {
        return new Object[][]{
                {Arrays.asList(new MinimalUnit("123"), new MinimalUnit("321")),
                        Arrays.asList(new MinimalUnit("vov"), new MinimalUnit("abc")), 0},
                {Arrays.asList(new MinimalUnit("1234"), new MinimalUnit("32145")),
                        Arrays.asList(new MinimalUnit("a"), new MinimalUnit("13")), 1},
                {Arrays.asList(new MinimalUnit("abc"), new MinimalUnit("a")),
                        Arrays.asList(new MinimalUnit("321123"), new MinimalUnit("ASD")), -1}
        };
    }

    @ParameterizedTest
    @MethodSource("sentencesCompareTestData")
    void compareTest(List<MinimalUnit> units1, List<MinimalUnit> units2, int result) {
        Sentence sentence1 = new Sentence();
        Sentence sentence2 = new Sentence();
        units1.forEach(sentence1::addComponent);
        units2.forEach(sentence2::addComponent);
        assertEquals(result, comparator.compare(sentence1, sentence2));
    }

    @RepeatedTest(2)
    void testSentenceComparatorIsSingleton() {
        assertSame(SentenceComparator.getInstance(), SentenceComparator.getInstance());
    }
}