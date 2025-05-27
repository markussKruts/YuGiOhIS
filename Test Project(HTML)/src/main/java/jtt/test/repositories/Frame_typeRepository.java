package jtt.test.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import jtt.test.dto.Card_Type;
import jtt.test.dto.FrameType;

public interface Frame_typeRepository extends CrudRepository<FrameType, Integer>{
	@Query("SELECT u FROM FrameType u WHERE u.name = :name")
	FrameType findByName(@Param("name") String name);
}
