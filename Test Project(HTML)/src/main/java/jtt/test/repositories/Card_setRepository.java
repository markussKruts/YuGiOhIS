package jtt.test.repositories;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import jtt.test.dto.Card_set;
import jtt.test.dto.User;

public interface Card_setRepository extends CrudRepository<Card_set, Integer>{
    @Modifying
    @Transactional
	@Query("DELETE FROM Card_set c WHERE c.set_id.id = :setId")
	void deleteBySetId(@Param("setId") int setId);
}
