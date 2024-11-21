package tourism_app.services.searchService;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public abstract class SearchService<T> {
    private final Map<String, Predicate<T>> searchCriteria = new LinkedHashMap<>();

    protected void addSearchCriterion(String name, Predicate<T> criterion) {
        searchCriteria.put(name, criterion);
    }

    public List<T> search(String field, List<T> items) {
        Predicate<T> criterion = searchCriteria.get(field);
        if (criterion == null) {
            System.out.println("Invalid search field: " + field);
            return Collections.emptyList();
        }
        return items.stream().filter(criterion).collect(Collectors.toList());
    }

    public void displayAvailableSearchFields() {
        System.out.println("Available search fields:");
        int i = 1;
        for (String field : searchCriteria.keySet()) {
            System.out.println(i++ + ". " + field);
        }
    }

    public List<String> getSearchFields() {
        return new ArrayList<>(searchCriteria.keySet());
    }
}
