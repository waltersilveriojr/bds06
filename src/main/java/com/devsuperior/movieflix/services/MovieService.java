package com.devsuperior.movieflix.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devsuperior.movieflix.dto.MovieDTO;
import com.devsuperior.movieflix.dto.MovieGenreDTO;
import com.devsuperior.movieflix.entities.Movie;
import com.devsuperior.movieflix.repositories.MovieRepository;
import com.devsuperior.movieflix.services.exceptions.ResourceNotFoundException;

@Service
public class MovieService {


	@Autowired
	private MovieRepository repository;	
	
	@Transactional(readOnly = true)
	public MovieDTO findById(Long id) {		
		Optional<Movie> obj = repository.findById(id);
		Movie entity = obj.orElseThrow(() -> new ResourceNotFoundException("Entity not found"));
		return new MovieGenreDTO(entity);
	}

	public Page<MovieDTO> findAllPaged(Pageable pageable,  Long genreId) {
		Page<MovieDTO> page = repository.findMovies(pageable, genreId);
		return page;		
	}

}
