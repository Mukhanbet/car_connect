package com.example.car_connect.specification;

import com.example.car_connect.model.domain.Car;
import com.example.car_connect.model.dto.car.CarFilter;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

public class CarSpecification {
    public static Specification<Car> filterByCriteria(CarFilter filter) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (filter.getMake() != null && !filter.getMake().isEmpty()) {
                predicates.add(criteriaBuilder.equal(root.get("make"), filter.getMake()));
            }

            if (filter.getModel() != null && !filter.getModel().isEmpty()) {
                predicates.add(criteriaBuilder.equal(root.get("model"), filter.getModel()));
            }

            if (filter.getColor() != null && !filter.getColor().isEmpty()) {
                predicates.add(criteriaBuilder.equal(root.get("color"), filter.getColor()));
            }

            if (filter.getYear() != null) {
                predicates.add(criteriaBuilder.equal(root.get("year"), filter.getYear()));
            }

            if (filter.getPrice() != null) {
                predicates.add(criteriaBuilder.equal(root.get("price"), filter.getPrice()));
            }

            if (filter.getLocation() != null && !filter.getLocation().isEmpty()) {
                predicates.add(criteriaBuilder.equal(root.get("location"), filter.getLocation()));
            }

            if (filter.getAvailableFrom() != null) {
                predicates.add(criteriaBuilder.equal(root.get("availableFrom"), filter.getAvailableFrom()));
            }

            if (filter.getRating() != null) {
                predicates.add(criteriaBuilder.equal(root.get("rating"), filter.getRating()));
            }

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }
}
