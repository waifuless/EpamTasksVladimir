package com.epam.jwd.secondtask.repository.specification_for_planeregistrar.comparing;

import com.epam.jwd.secondtask.model.Plane;
import com.epam.jwd.secondtask.model.PlaneRegistrar;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Comparator;

public class ComparatorByAngleTest {

    /**
     * expected registrar1 parameters
     * angle with oxy:  70.52877936550931
     * oxz: 48.189685104221404
     * oyz: 48.189685104221404
     */
    private final static PlaneRegistrar registrar1 = new PlaneRegistrar
            ("first", Plane.of(8, 8, 4, 0));

    /**
     * expected registrar2 parameters
     * angle with oxy:  58.00718299981828
     * oxz: 48.527065691059505
     * oyz: 58.00718299981828
     */
    private final static PlaneRegistrar registrar2 = new PlaneRegistrar
            ("first", Plane.of(4, -5, 4, 0));

    @Test
    public void testCompare_Oxy() {
        Comparator<PlaneRegistrar> comparator = ComparatorFactory
                .getComparator(ComparatorFactory.CompareParameter.ANGLE_OXY);
        Assert.assertTrue(comparator.compare(registrar1, registrar2) > 0);
    }

    @Test
    public void testReverseCompare_Oxy() {
        Comparator<PlaneRegistrar> comparator = ComparatorFactory
                .getComparator(ComparatorFactory.CompareParameter.ANGLE_OXY);
        Assert.assertTrue(comparator.reversed().compare(registrar1, registrar2) < 0);
    }

    @Test
    public void testCompare_Oxz() {
        Comparator<PlaneRegistrar> comparator = ComparatorFactory
                .getComparator(ComparatorFactory.CompareParameter.ANGLE_OXZ);
        Assert.assertTrue(comparator.compare(registrar1, registrar2) < 0);
    }

    @Test
    public void testReverseCompare_Oxz() {
        Comparator<PlaneRegistrar> comparator = ComparatorFactory
                .getComparator(ComparatorFactory.CompareParameter.ANGLE_OXZ);
        Assert.assertTrue(comparator.reversed().compare(registrar1, registrar2) > 0);
    }

    @Test
    public void testCompare_Oyz() {
        Comparator<PlaneRegistrar> comparator = ComparatorFactory
                .getComparator(ComparatorFactory.CompareParameter.ANGLE_OYZ);
        Assert.assertTrue(comparator.compare(registrar1, registrar2) < 0);
    }

    @Test
    public void testReverseCompare_Oyz() {
        Comparator<PlaneRegistrar> comparator = ComparatorFactory
                .getComparator(ComparatorFactory.CompareParameter.ANGLE_OYZ);
        Assert.assertTrue(comparator.reversed().compare(registrar1, registrar2) > 0);
    }

}