package com.api.forum.controller;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.api.forum.controller.form.CursoForm;
import com.api.forum.dto.CursoDto;
import com.api.forum.model.Curso;
import com.api.forum.repository.CursoRepository;

@RestController
@RequestMapping("/cursos")
public class CursosController {

	@Autowired
	private CursoRepository cursoRepository;
	
	@GetMapping
	public List<CursoDto> getAllCursos() {
		
		List<Curso> cursos = cursoRepository.findAll();
		return CursoDto.cursoModelToDto(cursos);
		
	}
	
	@PostMapping
	public ResponseEntity<CursoDto> createCurso(@RequestBody @Valid CursoForm cursoForm,
			UriComponentsBuilder uriBuilder) {
		
		Curso curso = cursoForm.cursoFormToModel();
		cursoRepository.save(curso);
		
		URI uri = uriBuilder.path("/cursos/{id}").buildAndExpand(curso.getId()).toUri();
		
		return ResponseEntity.created(uri).body(new CursoDto(curso));
	}
	
}
