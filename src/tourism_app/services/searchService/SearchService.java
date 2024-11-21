package tourism_app.services.searchService;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class SearchService<T> implements Searchable<T> {
    private final Map<String, Predicate<T>> searchCriteria = new LinkedHashMap<>();

    @Override
    public Set<String> getSearchFields() {
        return searchCriteria.keySet();
    }

    @Override
    public void addSearchCriterion(String field, Predicate<T> criterion) {
        searchCriteria.put(field, criterion);
    }

    @Override
    public List<T> search(String field, List<T> items) {
        Predicate<T> criterion = searchCriteria.get(field);
        if (criterion == null) {
            System.out.println("Invalid search field.");
            return Collections.emptyList();
        }
        return items.stream().filter(criterion).collect(Collectors.toList());
    }
}

