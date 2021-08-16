package com.epam.jwd.secondtask.validation;

import com.epam.jwd.secondtask.model.Plane;

public interface ValidationStrategy {
    boolean isPlaneValid(Plane plane);
}
