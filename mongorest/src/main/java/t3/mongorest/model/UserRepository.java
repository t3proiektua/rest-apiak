package t3.mongorest.model;

import java.util.List;

import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository {
    List<Userr> findAll();
    Userr findById(String id);
    Userr save(Userr userr);
    long delete(String name);
}
