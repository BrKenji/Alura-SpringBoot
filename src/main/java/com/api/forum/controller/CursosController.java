package com.api.forum.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.api.forum.dto.CursoDto;
import com.api.forum.form.CursoForm;
import com.api.forum.form.UpdateCursoForm;
import com.api.forum.model.Curso;
import com.api.forum.repository.CursoRepository;

@RestController
@RequestMapping("/cursos")
public class CursosController {

	@Autowired
	private CursoRepository cursoRepository;
	
	@GetMapping
	@Cacheable(value = "cursosList")
	public List<CursoDto> getAllCursos() {
		
		List<Curso> cursos = cursoRepository.findAll();
		return CursoDto.cursoModelToDto(cursos);
		
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<CursoDto> detailCurso(@PathVariable Long id) {
		
		Optional<Curso> curso = cursoRepository.findById(id);
		
		if(curso.isPresent()) {
			return ResponseEntity.ok(new CursoDto(curso.get()));
		} else {
			return ResponseEntity.notFound().build();
		}
		
	}
	
	@PostMapping
	@Transactional
	@CacheEvict(value = "cursosList", allEntries = true)
	public ResponseEntity<CursoDto> createCurso(@RequestBody @Valid CursoForm cursoForm,
			UriComponentsBuilder uriBuilder) {
		
		Curso curso = cursoForm.cursoFormToModel();
		cursoRepository.save(curso);
		
		URI uri = uriBuilder.path("/cursos/{id}").buildAndExpand(curso.getId()).toUri();
		
		return ResponseEntity.created(uri).body(new CursoDto(curso));
	}
	
	@PutMapping("/{id}")
	@Transactional
	@CacheEvict(value = "cursosList", allEntries = true)
	public ResponseEntity<CursoDto> updateCurso(@PathVariable Long id,
			@RequestBody @Valid UpdateCursoForm updateForm) {
		
		Optional<Curso> optional = cursoRepository.findById(id);
		
		if(optional.isPresent()) {
			Curso curso = updateForm.updateCurso(id, cursoRepository);
			return ResponseEntity.ok(new CursoDto(curso));
		} else {
			return ResponseEntity.notFound().build();
		}
		
	}
	
	@DeleteMapping("/{id}")
	@Transactional
	@CacheEvict(value = "cursosList", allEntries = true)
	public ResponseEntity<?> deleteCurso(@PathVariable Long id) {
		System.out.println("OK");
		Optional<Curso> curso = cursoRepository.findById(id);
		System.out.println(curso);
		
		if(curso.isPresent()) {
			cursoRepository.deleteById(id);
			return ResponseEntity.ok().build();
		} else {
			return ResponseEntity.notFound().build();
		}
		
	}
	
}
