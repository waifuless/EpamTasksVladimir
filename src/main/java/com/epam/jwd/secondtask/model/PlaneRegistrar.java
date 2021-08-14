package com.epam.jwd.secondtask.model;

import com.epam.jwd.secondtask.exception.ExceptionMessages;
import com.epam.jwd.secondtask.exception.PlaneIsInvalidException;
import com.epam.jwd.secondtask.service.calculation.AngleOfPlanesCalculator;
import com.epam.jwd.secondtask.validation.PlaneValidationFactory;
import com.epam.jwd.secondtask.validation.ValidationStrategy;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.math.BigDecimal;

public class PlaneRegistrar implements PropertyChangeListener {

    private final static Logger LOG = LogManager.getLogger(PlaneRegistrar.class);
    private final static ValidationStrategy planeValidator = PlaneValidationFactory
            .getValidationStrategy(PlaneValidationFactory.WayToValidPlane.VALIDATE_WITH_BOOL);
    private final static AngleOfPlanesCalculator angleCalculator = AngleOfPlanesCalculator.getInstance();

    private final int id;
    private final String name;
    private final Plane plane;
    private BigDecimal angleWithOxy;
    private BigDecimal angleWithOxz;
    private BigDecimal angleWithOyz;
    private boolean planeIsValid;

    public PlaneRegistrar(int id, String name, Plane plane) {
        this.id = id;
        this.name = name;
        this.plane = plane;
        plane.addPropertyChangeListener(this);
        this.planeIsValid = planeValidator.isPlaneValid(plane);
        if (planeIsValid) {
            updateAngles();
        }
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        planeIsValid = planeValidator.isPlaneValid(plane);
        if (planeIsValid) {
            updateAngles();
        }
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    /**
     * Angles getters work when plane is valid
     * When plane is invalid angles can`t be calculated
     */
    public BigDecimal getAngleWithOxy() {
        if (planeIsValid) {
            return angleWithOxy;
        } else {
            PlaneIsInvalidException ex = new PlaneIsInvalidException(ExceptionMessages.ALL_COEFFICIENTS_ARE_ZERO_MCG);
            LOG.error(ex.getMessage(), ex);
            throw ex;
        }
    }

    public BigDecimal getAngleWithOxz() {
        if (planeIsValid) {
            return angleWithOxz;
        } else {
            PlaneIsInvalidException ex = new PlaneIsInvalidException(ExceptionMessages.ALL_COEFFICIENTS_ARE_ZERO_MCG);
            LOG.error(ex.getMessage(), ex);
            throw ex;
        }
    }

    public BigDecimal getAngleWithOyz() {
        if (planeIsValid) {
            return angleWithOyz;
        } else {
            PlaneIsInvalidException ex = new PlaneIsInvalidException(ExceptionMessages.ALL_COEFFICIENTS_ARE_ZERO_MCG);
            LOG.error(ex.getMessage(), ex);
            throw ex;
        }
    }

    public Plane getPlane() {
        return plane;
    }

    public boolean isPlaneIsValid() {
        return planeIsValid;
    }

    private void updateAngles() {
        angleWithOxy = angleCalculator.calculateAngleBetweenPlanes(plane, AngleOfPlanesCalculator.OXY_PLANE);
        angleWithOxz = angleCalculator.calculateAngleBetweenPlanes(plane, AngleOfPlanesCalculator.OXZ_PLANE);
        angleWithOyz = angleCalculator.calculateAngleBetweenPlanes(plane, AngleOfPlanesCalculator.OYZ_PLANE);
    }
}
