package com.epam.jwd.secondtask.repository;

import com.epam.jwd.secondtask.exception.PlaneConstructedException;
import com.epam.jwd.secondtask.exception.repositoryexception.InvalidIdException;
import com.epam.jwd.secondtask.model.Plane;
import com.epam.jwd.secondtask.model.PlaneRegistrar;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Random;

public class RepositoryOnListSaveAndExistingTest {

    private final Repository<PlaneRegistrar> repositoryForSave = new RepositoryOnList<>();
    private final Repository<PlaneRegistrar> repositoryToCheckExisting = new RepositoryOnList<>();

    @BeforeClass
    public void init_Repository() {
        repositoryToCheckExisting.deleteAll();
        Random random = new Random();
        for (int i = 1; i <= 5; i++) {
            try {
                repositoryToCheckExisting.save(new PlaneRegistrar(String.format("%d", i),
                        Plane.of(random.nextDouble() * 20 - 7, random.nextDouble() * 20 - 7,
                                random.nextDouble() * 20 - 7, random.nextDouble() * 20 - 7)));
            } catch (PlaneConstructedException ignored) {//it throws when all coefficients(a,b,c) are zero.
                i--; //try again
            }
        }
    }

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
        PlaneRegistrar newRegistrar = repositoryForSave.save(registrar);
        Assert.assertEquals(newRegistrar, repositoryForSave.findById(newRegistrar.getId()));
    }

    @Test(dataProvider = "planeRegistrarProvider")
    public void testEqualsOldRegistrarAndAfterSave(PlaneRegistrar oldRegistrar) {
        long idSaved = repositoryForSave.save(oldRegistrar).getId();
        Assert.assertEquals(oldRegistrar, repositoryForSave.findById(idSaved));
    }

    @Test
    public void testIsExistById_true_whenExist() {
        for (int i = 1; i <= 5; i++) {
            Assert.assertTrue(repositoryToCheckExisting.isExistById(i));
        }
    }

    @Test
    public void testIsExistById_false_whenNotExist() {
        for (int i = 10; i <= 15; i++) {
            Assert.assertFalse(repositoryToCheckExisting.isExistById(i));
        }
    }


    @Test(expectedExceptions = InvalidIdException.class)
    public void testIsExistById_exception_whenIdInvalid() {
        repositoryToCheckExisting.isExistById(-1);
    }

    @Test
    public void testIsExist_true_whenExist() {
        for (PlaneRegistrar registrar : repositoryToCheckExisting.findAll()) {
            Assert.assertTrue(repositoryToCheckExisting.isExist(registrar));
        }
    }

    @Test
    public void testIsExist_false_whenNotExist() {
        PlaneRegistrar registrar = new PlaneRegistrar("10",
                Plane.of(100, 100, 100, 100));
        Assert.assertFalse(repositoryToCheckExisting.isExist(registrar));
    }
}