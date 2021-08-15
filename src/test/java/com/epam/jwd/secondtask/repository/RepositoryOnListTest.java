package com.epam.jwd.secondtask.repository;

import com.epam.jwd.secondtask.model.Plane;
import com.epam.jwd.secondtask.model.PlaneRegistrar;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.ArrayList;
import java.util.List;

public class RepositoryOnListTest {

    private Repository<PlaneRegistrar> repository = new RepositoryOnList<>();
    private List<PlaneRegistrar> list;

    @DataProvider
    public Object[][] planeRegistrarProvider() {
        return new Object[][]{
                {new PlaneRegistrar("1", Plane.of(2, 2, 2, 0))},
                {new PlaneRegistrar("2", Plane.of(1, 2, 2, 2))},
                {new PlaneRegistrar("-2", Plane.of(2, 2.5, 2, 3))},
                {new PlaneRegistrar("blablabla", Plane.of(0, 2, 6.4, -1))}
        };
    }


    @Test
    public void testSaveCount() {
        repository = new RepositoryOnList<>();
        PlaneRegistrar registrar = new PlaneRegistrar("1", Plane.of(2, 2, 2, 0));
        for(int i=1;i<=10;i++){
            repository.save(registrar);
            Assert.assertEquals(repository.findAll().size(), i);
        }
    }

    @Test(dataProvider = "planeRegistrarProvider")
    public void testReceivedEqualsSaved(PlaneRegistrar registrar){
        PlaneRegistrar newRegistrar = repository.save(registrar);
        Assert.assertEquals(newRegistrar, repository.findById(newRegistrar.getId()));
    }

    @Test(dataProvider = "planeRegistrarProvider")
    public void testEqualsOldRegistrarAndAfterSave(PlaneRegistrar oldRegistrar){
        long idSaved = repository.save(oldRegistrar).getId();
        Assert.assertEquals(oldRegistrar, repository.findById(idSaved));
    }

    @BeforeGroups(groups = "FindingAndIndexing")
    public void before_find(){
        repository = new RepositoryOnList<>();
        list = new ArrayList<>();
        PlaneRegistrar registrar;
        for(int i=1;i<=10;i++){
            registrar = repository.save(new PlaneRegistrar(String.format("%d", i),
                    Plane.of(2, 2, 2, 0)));
            list.add(registrar);
        }
    }

    @Test(groups = "FindingAndIndexing")
    public void testFindById() {
        for(int i=1;i<=10;i++){
            Assert.assertEquals(repository.findById(i), list.get(i-1));
        }
    }

    @Test(groups = "FindingAndIndexing")
    public void testFindId() {
        for(int i=1;i<=10;i++){
            Assert.assertEquals(repository.findId(list.get(i-1)), i);
        }
    }

    @Test(groups = "FindingAndIndexing")
    public void testFindAll() {
        Assert.assertEquals(repository.findAll(), list);
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
    public void testCount() {
    }

    @Test
    public void testIsExistById() {
    }

    @Test
    public void testIsExist() {
    }
}