package com.epam.jwd.secondtask.repository.specification_for_planeregistrar.comparing;

import com.epam.jwd.secondtask.model.PlaneRegistrar;

import java.util.Comparator;

public class ComparatorFactory {

    private static ComparatorById byId;
    private static ComparatorByName byCommonName;
    private static ComparatorByName byIgnoreCaseName;
    private static ComparatorByAngle byOxyAngle;
    private static ComparatorByAngle byOxzAngle;
    private static ComparatorByAngle byOyzAngle;


    public static <T extends PlaneRegistrar> Comparator<T> getComparator(CompareParameter parameter) {
        switch (parameter) {
            case ID:
                return getById();
            case NAME_COMMON_CASE:
            case NAME_IGNORE_CASE:
                return getByName(parameter);
            case ANGLE_OXY:
            case ANGLE_OXZ:
            case ANGLE_OYZ:
                return getByAngle(parameter);
            default:
                throw new IllegalArgumentException();
        }
    }

    private static <T extends PlaneRegistrar> Comparator<T> getById() {
        if (byId == null) {
            byId = new ComparatorById();
        }
        return (Comparator<T>) byId;
    }

    private static <T extends PlaneRegistrar> Comparator<T> getByName(CompareParameter compareParameter) {
        switch (compareParameter) {
            case NAME_COMMON_CASE:
                if (byCommonName == null) {
                    byCommonName = new ComparatorByName(ComparatorByName.CompareMode.COMMON);
                }
                return (Comparator<T>) byCommonName;
            case NAME_IGNORE_CASE:
                if (byIgnoreCaseName == null) {
                    byIgnoreCaseName = new ComparatorByName(ComparatorByName.CompareMode.IGNORE_CASE);
                }
                return (Comparator<T>) byIgnoreCaseName;
            default:
                throw new IllegalArgumentException();
        }
    }

    private static <T extends PlaneRegistrar> Comparator<T> getByAngle(CompareParameter compareParameter) {
        switch (compareParameter) {
            case ANGLE_OXY:
                if (byOxyAngle == null) {
                    byOxyAngle = new ComparatorByAngle(ComparatorByAngle.Angle.OXY);
                }
                return (Comparator<T>) byOxyAngle;
            case ANGLE_OXZ:
                if (byOxzAngle == null) {
                    byOxzAngle = new ComparatorByAngle(ComparatorByAngle.Angle.OXZ);
                }
                return (Comparator<T>) byOxzAngle;
            case ANGLE_OYZ:
                if (byOyzAngle == null) {
                    byOyzAngle = new ComparatorByAngle(ComparatorByAngle.Angle.OYZ);
                }
                return (Comparator<T>) byOyzAngle;
            default:
                throw new IllegalArgumentException();
        }
    }


    enum CompareParameter {
        ID,
        NAME_COMMON_CASE,
        NAME_IGNORE_CASE,
        ANGLE_OXY,
        ANGLE_OXZ,
        ANGLE_OYZ
    }

}
