package com.epam.jwd.secondtask.repository;


import com.epam.jwd.secondtask.exception.PlaneConstructedException;
import com.epam.jwd.secondtask.model.Plane;
import com.epam.jwd.secondtask.model.PlaneRegistrar;
import com.epam.jwd.secondtask.repository.specification_for_planeregistrar.comparing.ComparatorFactory;
import com.epam.jwd.secondtask.repository.specification_for_planeregistrar.finding.PredicateByIdRange;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Comparator;
import java.util.List;
import java.util.Random;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class RepositorySortAndFindTest {

    private final static RepositoryOnList<PlaneRegistrar> repository = new RepositoryOnList<>();


    @BeforeMethod
    public void init_Repository() {
        repository.deleteAll();
        Random random = new Random();
        for (int i = 1; i <= 10; i++) {
            try {
                repository.save(new PlaneRegistrar(String.format("%d", i),
                        Plane.of(random.nextDouble() * 20 - 7, random.nextDouble() * 20 - 7,
                                random.nextDouble() * 20 - 7, random.nextDouble() * 20 - 7)));
            } catch (PlaneConstructedException ignored) {//it throws when all coefficients(a,b,c) are zero.
                i--; //try again
            }
        }
    }

    @Test
    public void testSortById() {
        repository.sort(ComparatorFactory.getComparator(ComparatorFactory.CompareParameter.ID));
        List<PlaneRegistrar> list = repository.findAll();
        for (int i = 0; i < list.size() - 1; i++) {
            assertTrue(list.get(i).getId() < list.get(i + 1).getId());
        }
    }

    @Test
    public void testSortById_Reversed() {
        repository.sort(ComparatorFactory.getComparator(ComparatorFactory.CompareParameter.ID).reversed());
        List<PlaneRegistrar> list = repository.findAll();
        for (int i = 0; i < list.size() - 1; i++) {
            assertTrue(list.get(i).getId() > list.get(i + 1).getId());
        }
    }

    @Test
    public void testSortByAngle_Oxy() {
        repository.sort(ComparatorFactory.getComparator(ComparatorFactory.CompareParameter.ANGLE_OXY));
        List<PlaneRegistrar> list = repository.findAll();
        for (int i = 0; i < list.size() - 1; i++) {
            assertTrue(list.get(i).getAngleWithOxy().compareTo(list.get(i + 1).getAngleWithOxy()) <= 0);
        }
    }

    @Test
    public void testSortByAngle_Oxz() {
        repository.sort(ComparatorFactory.getComparator(ComparatorFactory.CompareParameter.ANGLE_OXZ));
        List<PlaneRegistrar> list = repository.findAll();
        for (int i = 0; i < list.size() - 1; i++) {
            assertTrue(list.get(i).getAngleWithOxz().compareTo(list.get(i + 1).getAngleWithOxz()) <= 0);
        }
    }

    @Test
    public void testSortByAngle_Oyz() {
        repository.sort(ComparatorFactory.getComparator(ComparatorFactory.CompareParameter.ANGLE_OYZ));
        List<PlaneRegistrar> list = repository.findAll();
        for (int i = 0; i < list.size() - 1; i++) {
            assertTrue(list.get(i).getAngleWithOyz().compareTo(list.get(i + 1).getAngleWithOyz()) <= 0);
        }
    }

    @Test
    public void testSortAndFindById() {
        Comparator<PlaneRegistrar> comparator = ComparatorFactory.getComparator(ComparatorFactory.CompareParameter.ID);
        PredicateForRepository<PlaneRegistrar> predicate = new PredicateByIdRange<>(4, 10);
        List<PlaneRegistrar> list = repository.findAllMatchSorted(predicate, comparator);
        assertEquals(list.size(), 7);
        for (int i = 1; i <= 7; i++) {
            assertEquals(list.get(i - 1).getId(), i + 3);
        }
    }

    @Test
    public void test_SortReversed_AndFindById() {
        Comparator<PlaneRegistrar> comparator = ComparatorFactory
                .getComparator(ComparatorFactory.CompareParameter.ID)
                .reversed();
        PredicateForRepository<PlaneRegistrar> predicate = new PredicateByIdRange<>(4, 10);
        List<PlaneRegistrar> list = repository.findAllMatchSorted(predicate, comparator);
        assertEquals(list.size(), 7);
        int j = 10;
        for (int i = 1; i <= 7; i++) {
            assertEquals(list.get(i - 1).getId(), j--);
        }
    }

    @Test
    public void testSort() {
        Repository<PlaneRegistrar> localRepository = new RepositoryOnList<>();
        for (int i = 1; i <= 10; i++) {
            localRepository.save(new PlaneRegistrar(String.format("%d", i),
                    Plane.of(2, 2, 2, 0)));
        }
        localRepository.sort(new Comparator<PlaneRegistrar>() {
            @Override
            public int compare(PlaneRegistrar o1, PlaneRegistrar o2) {
                return -Long.compare(o1.getId(), o2.getId());
            }
        });
        List<PlaneRegistrar> list = localRepository.findAll();
        for (int i = 1; i < localRepository.count(); i++) {
            Assert.assertTrue(list.get(i - 1).getId() > list.get(i).getId());
        }
    }
}
