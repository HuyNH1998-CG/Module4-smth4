package bigg.service;

import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface IGeneralService<T> {
    Iterable<T> findAll();
    Optional<T> finById(long id);
    void save(T t);
    void remove(long id);
}
