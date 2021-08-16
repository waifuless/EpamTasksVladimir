package com.epam.jwd.secondtask.model;

import com.epam.jwd.secondtask.exception.ExceptionMessages;
import com.epam.jwd.secondtask.exception.PlaneIsInvalidException;
import com.epam.jwd.secondtask.repository.EntityWithId;
import com.epam.jwd.secondtask.service.calculation.AngleOfPlanesCalculator;
import com.epam.jwd.secondtask.validation.PlaneValidationFactory;
import com.epam.jwd.secondtask.validation.ValidationStrategy;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.math.BigDecimal;
import java.util.Objects;

public class PlaneRegistrar implements PropertyChangeListener, EntityWithId {

    private final static Logger LOG = LogManager.getLogger(PlaneRegistrar.class);
    private final static ValidationStrategy planeValidator = PlaneValidationFactory
            .getValidationStrategy(PlaneValidationFactory.WayToValidPlane.VALIDATE_WITH_BOOL);
    private final static AngleOfPlanesCalculator angleCalculator = AngleOfPlanesCalculator.getInstance();

    private final Plane plane;

    private final long id;
    private final String name;
    private BigDecimal angleWithOxy;
    private BigDecimal angleWithOxz;
    private BigDecimal angleWithOyz;
    private boolean planeIsValid;

    /**
     * It`s private constructor, so oldRegistrar already calculated data can be just copied
     */
    private PlaneRegistrar(long id, PlaneRegistrar oldRegistrar) {
        this.id = id;
        this.name = oldRegistrar.name;
        this.angleWithOxy = oldRegistrar.angleWithOxy;
        this.angleWithOxz = oldRegistrar.angleWithOxz;
        this.angleWithOyz = oldRegistrar.angleWithOyz;
        this.planeIsValid = oldRegistrar.planeIsValid;
        this.plane = Plane.of(oldRegistrar.plane);
        this.plane.addPropertyChangeListener(this);
    }

    public PlaneRegistrar(String name, Plane plane) {
        this.id = -1;
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
        if(evt.getPropertyName().equalsIgnoreCase("freeTerm")){
            //ignore when change free term(it does not change any angle)
            return;
        }
        planeIsValid = planeValidator.isPlaneValid(plane);
        if (planeIsValid) {
            updateAngles();
        }
    }

    @Override
    public long getId() {
        return id;
    }

    @Override
    public EntityWithId createWithId(long id) {
        return new PlaneRegistrar(id, this);
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

    public boolean isPlaneValid() {
        return planeIsValid;
    }

    private void updateAngles() {
        angleWithOxy = angleCalculator.calculateAngleBetweenPlanes(plane, AngleOfPlanesCalculator.OXY_PLANE);
        angleWithOxz = angleCalculator.calculateAngleBetweenPlanes(plane, AngleOfPlanesCalculator.OXZ_PLANE);
        angleWithOyz = angleCalculator.calculateAngleBetweenPlanes(plane, AngleOfPlanesCalculator.OYZ_PLANE);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PlaneRegistrar registrar = (PlaneRegistrar) o;

        if (planeIsValid != registrar.planeIsValid) return false;
        if (!Objects.equals(plane, registrar.plane)) return false;
        if (!Objects.equals(name, registrar.name)) return false;
        if (!Objects.equals(angleWithOxy, registrar.angleWithOxy))
            return false;
        if (!Objects.equals(angleWithOxz, registrar.angleWithOxz))
            return false;
        return Objects.equals(angleWithOyz, registrar.angleWithOyz);
    }

    @Override
    public int hashCode() {
        int result = plane != null ? plane.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (angleWithOxy != null ? angleWithOxy.hashCode() : 0);
        result = 31 * result + (angleWithOxz != null ? angleWithOxz.hashCode() : 0);
        result = 31 * result + (angleWithOyz != null ? angleWithOyz.hashCode() : 0);
        result = 31 * result + (planeIsValid ? 1 : 0);
        return result;
    }

    @Override
    public String toString() {
        return "PlaneRegistrar{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", angleWithOxy=" + angleWithOxy +
                ", angleWithOxz=" + angleWithOxz +
                ", angleWithOyz=" + angleWithOyz +
                ", planeIsValid=" + planeIsValid +
                ", plane=" + plane +
                '}';
    }
}
