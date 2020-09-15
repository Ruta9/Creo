package com.example.demo.validation;

import com.example.demo.data.Status;
import com.example.demo.enums.TicketType;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class StatusUniquenessConstraintValidator implements ConstraintValidator<StatusUniquenessConstraint, Collection<Status>> {

        @Override
        public void initialize(StatusUniquenessConstraint statusCollection) {
        }

        @Override
        public boolean isValid(Collection<Status> statuses,
                               ConstraintValidatorContext cxt) {
            if (statuses == null) return true;
            Collection<Status> storyStatuses = statuses.stream().filter(s -> s.getTicketType().equals(TicketType.STORY)).collect(Collectors.toList());
            if(namesAreDistinct(storyStatuses)) {
                Collection<Status> taskStatuses = statuses.stream().filter(s -> s.getTicketType().equals(TicketType.TASK)).collect(Collectors.toList());
                return namesAreDistinct(taskStatuses);
            }
            else return false;
        }

        private boolean namesAreDistinct(Collection<Status> statuses){
            Set<String> names = new HashSet<>();
            for (Status status : statuses) {
                names.add(status.getName());
            }
            return names.size() == statuses.size();
        }

}
