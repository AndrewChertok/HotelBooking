package com.hotelbooking;

import org.junit.Assert;
import java.util.List;
import java.util.stream.Collectors;

public class BeanMatcher<T> {

    private Equality<T> equality;

    private Class<T> entityClass;

    public interface Equality<T> {
        boolean areEqual(T expected, T actual);
    }

    private BeanMatcher(Class<T> entityClass, Equality<T> equality) {
        this.entityClass = entityClass;
        this.equality = equality;
    }

    public static <T> BeanMatcher<T> of(Class<T> entityClass) {
        return of(entityClass,
                (T expected, T actual) -> expected == actual || String.valueOf(expected).equals(String.valueOf(actual)));
    }

    public static <T> BeanMatcher<T> of(Class<T> entityClass, Equality<T> equality) {
        return new BeanMatcher<>(entityClass, equality);
    }



    private class Wrapper {
        private T entity;

        private Wrapper(T entity) {
            this.entity = entity;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Wrapper that = (Wrapper) o;
            return entity != null ? equality.areEqual(entity, that.entity) : that.entity == null;
        }

        @Override
        public String toString() {
            return String.valueOf(entity);
        }
    }

    public void assertEquals(T expected, T actual) {
        Assert.assertEquals(wrap(expected), wrap(actual));
    }

    public void assertListEquals(List<T> expected, List<T> actual) {
        Assert.assertEquals(wrap(expected), wrap(actual));
    }

    private Wrapper wrap(T entity) {
        return new Wrapper(entity);
    }

    private List<Wrapper> wrap(List<T> list) {
        return list.stream().map(this::wrap).collect(Collectors.toList());
    }


}