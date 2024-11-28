package tourism_app.services.searchService;

import tourism_app.services.utils.InputValidator;

import java.util.*;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public abstract class SearchService<T> {
    private final Map<String, Supplier<Predicate<T>>> searchCriteriaSuppliers = new LinkedHashMap<>();
    private final Set<String> usedCriteria = new HashSet<>();
    protected final InputValidator inputValidator;

    protected SearchService(InputValidator inputValidator) {
        this.inputValidator = inputValidator;
    }

    protected void addSearchCriterion(String name, Supplier<Predicate<T>> supplier) {
        searchCriteriaSuppliers.put(name, supplier);
    }

    public List<T> search(List<T> items) {
        List<Predicate<T>> activeCriteria = new ArrayList<>();
        System.out.println("Starting search.");

        while (true) {
            displayAvailableSearchFields();
            int fieldIndex = inputValidator.getValidIntInRange("Please select criteria:", 1, searchCriteriaSuppliers.size());
            String field = getSearchFields().get(fieldIndex - 1);

            if (usedCriteria.contains(field)) {
                System.out.println("Criterion already added. Choose another field.");
                continue;
            }

            Supplier<Predicate<T>> supplier = searchCriteriaSuppliers.get(field);
            if (supplier != null) {
                activeCriteria.add(supplier.get());
                usedCriteria.add(field);
            }

            System.out.print("Add another criterion? (y/n): ");
            if (!inputValidator.getYesOrNo()) {
                break;
            }
        }

        return items.stream()
                .filter(activeCriteria.stream().reduce(x -> true, Predicate::and))
                .collect(Collectors.toList());
    }

    public void displayAvailableSearchFields() {
        System.out.println("Available search fields:");
        List<String> fields = getSearchFields();
        for (int i = 0; i < fields.size(); i++) {
            System.out.printf("%d. %s%n", i + 1, fields.get(i));
        }
    }

    public List<String> getSearchFields() {
        return new ArrayList<>(searchCriteriaSuppliers.keySet());
    }
}
