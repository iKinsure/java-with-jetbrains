package org.ikinsure.platform;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CodeRepository extends CrudRepository<Code, Long> {

    Code findById(long id);
    List<Code> findByIdBetween(long from, long to);

}
