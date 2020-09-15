package com.example.demo.validation;

import com.example.demo.data.Status;
import com.example.demo.enums.TicketType;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Collection;
import java.util.stream.Collectors;

public class StatusSizeConstraintValidator implements ConstraintValidator<StatusSizeConstraint, Collection<Status>> {

    @Override
    public void initialize(StatusSizeConstraint statusCollection) {
    }

    @Override
    public boolean isValid(Collection<Status> statuses,
                           ConstraintValidatorContext cxt) {
        if (statuses == null) return false;
        Collection<Status> storyStatuses = statuses.stream().filter(s -> s.getTicketType().equals(TicketType.STORY)).collect(Collectors.toList());
        if(checkIfValidSize(storyStatuses)) {
            Collection<Status> taskStatuses = statuses.stream().filter(s -> s.getTicketType().equals(TicketType.TASK)).collect(Collectors.toList());
            return checkIfValidSize(taskStatuses);
        }
        else return false;
    }

    private boolean checkIfValidSize(Collection<Status> statuses){
        return statuses.size() >= 2 && statuses.size() <= 8;
    }
}
