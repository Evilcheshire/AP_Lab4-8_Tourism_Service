package tourism_app.services.searchService;
import java.util.List;
import java.util.Set;
import java.util.function.Predicate;

public interface Searchable<T> {
    Set<String> getSearchFields();

    void addSearchCriterion(String field, Predicate<T> criterion);

    List<T> search(String field, List<T> items);
}
