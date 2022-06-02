package models.custom.constraints;

import models.Tournament;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Date;

public class StartEndDateValidator implements ConstraintValidator<StartEndDate, Tournament>
{

    @Override
    public void initialize(StartEndDate constraintAnnotation)
    {
    }

    @Override
    public boolean isValid(Tournament value, ConstraintValidatorContext context)
    {
        if (value.getEndDate() == null || value.getStartDate() == null)
        {
            return true;
        }

        return value.getEndDate().getTime() >= value.getStartDate().getTime();
    }

}
