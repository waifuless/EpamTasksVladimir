package com.epam.jwd.secondtask.repository;

import com.epam.jwd.secondtask.model.Plane;
import com.epam.jwd.secondtask.model.PlaneRegistrar;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Comparator;
import java.util.List;

public class RepositoryOnListTest {

    private Repository<PlaneRegistrar> repository = new RepositoryOnList<>();

    @DataProvider
    public Object[][] planeRegistrarProvider() {
        return new Object[][]{
                {new PlaneRegistrar("1", Plane.of(2, 2, 2, 0))},
                {new PlaneRegistrar("2", Plane.of(1, 2, 2, 2))},
                {new PlaneRegistrar("-2", Plane.of(2, 2.5, 2, 3))},
                {new PlaneRegistrar("blablabla", Plane.of(0, 2, 6.4, -1))}
        };
    }


    @Test(dataProvider = "planeRegistrarProvider")
    public void testReceivedEqualsSaved(PlaneRegistrar registrar) {
        PlaneRegistrar newRegistrar = repository.save(registrar);
        Assert.assertEquals(newRegistrar, repository.findById(newRegistrar.getId()));
    }

    @Test(dataProvider = "planeRegistrarProvider")
    public void testEqualsOldRegistrarAndAfterSave(PlaneRegistrar oldRegistrar) {
        long idSaved = repository.save(oldRegistrar).getId();
        Assert.assertEquals(oldRegistrar, repository.findById(idSaved));
    }

    @Test
    public void testSort() {
        repository = new RepositoryOnList<>();
        for (int i = 1; i <= 10; i++) {
            repository.save(new PlaneRegistrar(String.format("%d", i),
                    Plane.of(2, 2, 2, 0)));
        }
        repository.sort(new Comparator<PlaneRegistrar>() {
            @Override
            public int compare(PlaneRegistrar o1, PlaneRegistrar o2) {
                return -Long.compare(o1.getId(), o2.getId());
            }
        });
        List<PlaneRegistrar> list = repository.findAll();
        for (int i = 1; i < repository.count(); i++) {
            Assert.assertTrue(list.get(i - 1).getId() > list.get(i).getId());
        }
    }

    @Test
    public void testUpdate() {
    }

    @Test
    public void testDeleteById() {
    }

    @Test
    public void testDeleteAll() {
    }


    @Test
    public void testIsExistById() {
    }

    @Test
    public void testIsExist() {
    }
}