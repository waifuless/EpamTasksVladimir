package com.epam.jwd.secondtask.repository;

import com.epam.jwd.secondtask.exception.PlaneConstructedException;
import com.epam.jwd.secondtask.model.Plane;
import com.epam.jwd.secondtask.model.PlaneRegistrar;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Random;

import static org.testng.Assert.*;

public class RepositoryOnListRedactAndDeleteTest {

    private Repository<PlaneRegistrar> repository = new RepositoryOnList<>();

    @BeforeMethod
    public void init_Repository() {
        repository = new RepositoryOnList<>();
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
    public void testUpdate() {
        PlaneRegistrar newRegistrar = new PlaneRegistrar("10",
                Plane.of(100, 100, 100, 100));
        PlaneRegistrar newRegWithId = repository.update(newRegistrar, 2);
        assertEquals(repository.findById(2), newRegistrar);
        assertNotEquals(newRegWithId.getId(), 2);
    }

    @Test
    public void testDeleteById() {
        assertTrue(repository.isExistById(4));
        repository.deleteById(4);
        assertFalse(repository.isExistById(4));
    }

    @Test
    public void testDeleteAll() {
        assertNotEquals(repository.findAll().size(), 0);
        repository.deleteAll();
        assertEquals(repository.findAll().size(), 0);
    }
}