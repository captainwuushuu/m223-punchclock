package ch.zli.m223.punchclock.validation;

import ch.zli.m223.punchclock.domain.Entry;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class CheckOutValidator implements ConstraintValidator<CheckOutConstraint, Entry> {

    public void initialize(CheckOutConstraint constraintAnnotation) {
    }

    public boolean isValid(Entry value,
                           ConstraintValidatorContext context) {
        return value.getCheckOut().isAfter(value.getCheckIn());
    }
}
