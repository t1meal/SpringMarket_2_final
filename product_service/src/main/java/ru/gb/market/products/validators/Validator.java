package ru.gb.market.products.validators;

public interface Validator<E> {
    void validate(E e);
}
