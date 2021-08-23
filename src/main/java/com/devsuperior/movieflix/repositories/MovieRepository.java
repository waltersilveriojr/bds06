package com.devsuperior.movieflix.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.devsuperior.movieflix.dto.MovieDTO;
import com.devsuperior.movieflix.entities.Movie;

public interface MovieRepository extends JpaRepository<Movie, Long> {

	@Query("SELECT new com.devsuperior.movieflix.dto.MovieDTO(obj.id, obj.title, obj.subTitle, obj.year, obj.imgUrl) FROM Movie obj "
			   +"WHERE "
			   +"(:genreId = 0L OR obj.genre.id = :genreId) "
			   +" ORDER BY obj.title")
		Page<MovieDTO> findMovies(Pageable pageable, Long genreId);	
	
}
