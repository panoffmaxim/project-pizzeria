package kz.epam.pizzeria.service.pagination;

import java.util.Map;

public interface PaginationService {
    Map<Integer, PaginationStatus> calculate(int allCount, int current, int pageLimit);
}
