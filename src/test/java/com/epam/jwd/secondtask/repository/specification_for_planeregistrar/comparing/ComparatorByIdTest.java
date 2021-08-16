package com.epam.jwd.secondtask.repository.specification_for_planeregistrar.comparing;

import com.epam.jwd.secondtask.model.Plane;
import com.epam.jwd.secondtask.model.PlaneRegistrar;
import com.epam.jwd.secondtask.repository.RepositoryOnList;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Comparator;

import static org.testng.Assert.assertTrue;

public class ComparatorByIdTest {

    private final static PlaneRegistrar registrar1 = new PlaneRegistrar
            ("first", Plane.of(8, 8, 4, 0));

    private final static PlaneRegistrar registrar2 = new PlaneRegistrar
            ("first", Plane.of(4, -5, 4, 0));

    private final static RepositoryOnList<PlaneRegistrar> repo = new RepositoryOnList<>();

    @BeforeMethod
    public void init_repository() {
        repo.save(registrar1);
        repo.save(registrar2);
    }

    @Test
    public void testCompare_ByID() {
        Comparator<PlaneRegistrar> comparator = ComparatorFactory
                .getComparator(ComparatorFactory.CompareParameter.ID);
        assertTrue(comparator.compare(repo.findById(1), repo.findById(2)) < 0);
    }

    @Test
    public void testCompare_ByID_Reversed() {
        Comparator<PlaneRegistrar> comparator = ComparatorFactory
                .getComparator(ComparatorFactory.CompareParameter.ID)
                .reversed();
        assertTrue(comparator.compare(repo.findById(1), repo.findById(2)) > 0);
    }

    @Test
    public void test_Both_ID_Null() {
        Comparator<PlaneRegistrar> comparator = ComparatorFactory
                .getComparator(ComparatorFactory.CompareParameter.ID);
        assertTrue(comparator.compare(registrar1, registrar2) == 0);
    }

    @Test
    public void test_First_ID_Null() {
        Comparator<PlaneRegistrar> comparator = ComparatorFactory
                .getComparator(ComparatorFactory.CompareParameter.ID);
        assertTrue(comparator.compare(registrar1, repo.findById(2)) < 0);
    }

    @Test
    public void test_Second_ID_Null() {
        Comparator<PlaneRegistrar> comparator = ComparatorFactory
                .getComparator(ComparatorFactory.CompareParameter.ID);
        assertTrue(comparator.compare(repo.findById(1), registrar2) > 0);
    }
}