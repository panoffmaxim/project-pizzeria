package kz.epam.pizzeria.service.pagination.impl;

import kz.epam.pizzeria.constant.OtherConstants;
import kz.epam.pizzeria.service.pagination.PaginationService;
import kz.epam.pizzeria.service.pagination.PaginationStatus;

import java.util.Map;
import java.util.TreeMap;

import static kz.epam.pizzeria.service.pagination.PaginationStatus.*;

public class PaginationServiceImpl implements PaginationService {

    @Override
    public Map<Integer, PaginationStatus> calculate(int allCount, int current, int pageLimit) {
        Map<Integer, PaginationStatus> map = new TreeMap<>();
        if (allCount < 0 || current < 0 || pageLimit < 0) {
            return map;
        }
        int barCount = (allCount + pageLimit - 1) / pageLimit;
        if (barCount > OtherConstants.BAR_LIMIT) {
            if (current < 1 || current > barCount) {
                outOfRange(map, barCount);
            } else {
                if (current < OtherConstants.BAR_LIMIT - 3) {
                    moreToBegin(map, barCount);
                } else if (barCount - current < OtherConstants.BAR_LIMIT - 3) {
                    moreToEnd(map, barCount);
                } else {
                    inMid(current, map);
                }
            }
        } else {
            markLess(current, map, barCount);
        }
        markActive(current, map, barCount);
        markPreviousAndNext(current, map, barCount);
        return map;
    }

    private void markActive(int current, Map<Integer, PaginationStatus> map, int barCount) {
        if (current > 0 && current <= barCount) {
            map.put(current, ACTIVE);
        }
    }

    private void moreToEnd(Map<Integer, PaginationStatus> map, int barCount) {
        map.put(1, NORMAL);
        map.put(2, THREE_POINTS);
        for (int i = barCount; i > barCount - (OtherConstants.BAR_LIMIT - 2); i--) {
            map.put(i, NORMAL);
        }
    }

    private void moreToBegin(Map<Integer, PaginationStatus> map, int barCount) {
        int i;
        for (i = 1; i < OtherConstants.BAR_LIMIT - 3; i++) {
            map.put(i, NORMAL);
        }
        map.put(i, NORMAL);
        map.put(barCount - 1, THREE_POINTS);
        map.put(barCount, NORMAL);
    }

    private void inMid(int current, Map<Integer, PaginationStatus> map) {
        map.put(1, NORMAL);
        map.put(2, THREE_POINTS);
        map.put(OtherConstants.BAR_LIMIT, NORMAL);
        map.put(OtherConstants.BAR_LIMIT - 1, THREE_POINTS);
        int remainPoints = OtherConstants.BAR_LIMIT - 4;
        int counter = 1;
        while (remainPoints > 0) {
            map.put(current - counter, NORMAL);
            remainPoints--;
            if (remainPoints != 0) {
                map.put(current + counter, NORMAL);
            }
            remainPoints--;
        }
    }

    private void outOfRange(Map<Integer, PaginationStatus> map, int barCount) {
        int i;
        for (i = 1; i < barCount - 2; i++) {
            map.put(i, NORMAL);
        }
        map.put(i++, THREE_POINTS);
        map.put(i, NORMAL);
    }

    private void markLess(int current, Map<Integer, PaginationStatus> map, int barCount) {
        for (int i = 1; i <= barCount; i++) {
            map.put(i, NORMAL);
        }
    }

    private void markPreviousAndNext(int current, Map<Integer, PaginationStatus> map, int barCount) {
        if (current == 1) {
            map.put(0, DISABLED);
        } else {
            map.put(0, NORMAL);
        }
        if (current == barCount) {
            map.put(barCount + 1, DISABLED);
        } else {
            map.put(barCount + 1, NORMAL);
        }
    }
}
