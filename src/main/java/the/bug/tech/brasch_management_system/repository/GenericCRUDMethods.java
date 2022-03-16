package the.bug.tech.brasch_management_system.repository;

import java.util.List;

public interface GenericCRUDMethods<T, ID> {

    T create(T t);
    T delete(T t);
    List<T> findAll();
    T findById(ID id);
    T update(T t);
    void clear();
}
