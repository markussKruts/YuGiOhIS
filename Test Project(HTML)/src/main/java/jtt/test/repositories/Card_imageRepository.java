package jtt.test.repositories;

import java.sql.Blob;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import jtt.test.dto.Card_image;


public interface Card_imageRepository extends CrudRepository<Card_image, Integer>{
	@Query("SELECT u FROM Card_image u WHERE u.image = :image")
	Card_image findByImage(@Param("image") byte[] image);
}
