package com.epam.jwd.secondtask.repository;

import com.epam.jwd.secondtask.model.Plane;
import com.epam.jwd.secondtask.model.PlaneRegistrar;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class RepositoryOnList_WithBefore_Test {

    private Repository<PlaneRegistrar> repository = new RepositoryOnList<>();
    private List<PlaneRegistrar> list;

    @BeforeClass
    public void before_find() {
        repository = new RepositoryOnList<>();
        list = new ArrayList<>();
        PlaneRegistrar registrar;
        for (int i = 1; i <= 10; i++) {
            registrar = repository.save(new PlaneRegistrar(String.format("%d", i),
                    Plane.of(2, 2, 2, 0)));
            list.add(registrar);
        }
    }

    @Test
    public void testReturnObjectsAndInRep_Same() {
        for (int i = 1; i <= 10; i++) {
            Assert.assertSame(repository.findById(i), list.get(i - 1));
        }
    }

    @Test
    public void testFindById() {
        for (int i = 1; i <= 10; i++) {
            Assert.assertEquals(repository.findById(i), list.get(i - 1));
        }
    }

    @Test
    public void testFindId() {
        for (int i = 1; i <= 10; i++) {
            Assert.assertEquals(repository.findId(list.get(i - 1)), i);
        }
    }

    @Test
    public void testFindAll() {
        Assert.assertEquals(repository.findAll(), list);
    }

    @Test
    public void testFindAll_Have_SideEffects_OnObjects() {
        List<PlaneRegistrar> repList = repository.findAll();
        long id = repList.get(0).getId();
        repList.get(0).getPlane().setCoefficientA(BigDecimal.valueOf(33));
        Assert.assertEquals(repository.findById(id).getPlane().getCoefficientA(), BigDecimal.valueOf(33));
    }

    @Test
    public void test_FindAllMatch_HaveSideEffects_OnObjects() {
        List<PlaneRegistrar> repList = repository.findAllMatch(x -> x.getId() > 0);
        long id = repList.get(0).getId();
        repList.get(0).getPlane().setCoefficientA(BigDecimal.TEN);
        Assert.assertEquals(list.get(0).getPlane().getCoefficientA(), BigDecimal.TEN);
        Assert.assertEquals(repository.findById(id).getPlane().getCoefficientA(), BigDecimal.TEN);
    }

    @Test
    public void testCount() {
        Assert.assertEquals(repository.count(), list.size());
    }

    @Test
    public void testFindAllMatch_return_valid() {
        List<PlaneRegistrar> foundList = repository.findAllMatch(x -> x.getId() > 5);
        for (PlaneRegistrar registrar : foundList) {
            Assert.assertTrue(registrar.getId() > 5);
        }
    }
}
