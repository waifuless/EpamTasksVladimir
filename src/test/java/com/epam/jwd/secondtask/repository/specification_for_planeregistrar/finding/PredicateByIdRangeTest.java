package com.epam.jwd.secondtask.repository.specification_for_planeregistrar.finding;

import com.epam.jwd.secondtask.model.Plane;
import com.epam.jwd.secondtask.model.PlaneRegistrar;
import com.epam.jwd.secondtask.repository.PredicateForRepository;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class PredicateByIdRangeTest {

    @DataProvider
    public Object[][] predicateData() {
        return new Object[][]{
                {new PlaneRegistrar("1",
                        Plane.of(1, 2, 3, 4)).createWithId(2),
                        1, 5, true},
                {new PlaneRegistrar("2",
                        Plane.of(1, 2, 3, 4)).createWithId(20),
                        1, 5, false},
                {new PlaneRegistrar("3",
                        Plane.of(1, 2, 3, 4)).createWithId(1),
                        -100, 10, true},
                {new PlaneRegistrar("4",
                        Plane.of(1, 2, 3, 4)).createWithId(20),
                        -100, 10, false}
        };
    }

    @Test(dataProvider = "predicateData")
    public void testPredicate(PlaneRegistrar reg, long startRange, long endRange, boolean expectedResult) {
        PredicateForRepository<PlaneRegistrar> predicate = new PredicateByIdRange<>(startRange, endRange);
        assertEquals(predicate.test(reg), expectedResult);
    }
}