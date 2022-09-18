package com.api.forum.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
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

import com.api.forum.dto.DetailedTopicoDto;
import com.api.forum.dto.TopicoDto;
import com.api.forum.form.TopicoForm;
import com.api.forum.form.UpdateTopicoForm;
import com.api.forum.model.Topico;
import com.api.forum.repository.CursoRepository;
import com.api.forum.repository.TopicoRepository;

@RestController
@RequestMapping("/topicos")
public class TopicosController {
	
	@Autowired
	private TopicoRepository topicoRepository;
	
	@Autowired
	private CursoRepository cursoRepository;
	
	@GetMapping
	public List<TopicoDto> getTopicsList(String nomeCurso){
		
		if(nomeCurso == null) {
			List<Topico> topicos = topicoRepository.findAll();
			return TopicoDto.topicModelToDto(topicos);
		} else {
			List<Topico> topicos = topicoRepository.getByCourseName(nomeCurso);
			return TopicoDto.topicModelToDto(topicos);	
		}
		
	}
	
	// @Valid bean, warns springs that the form, pulling the data from the request, to run the validation beans
	
	@PostMapping
	@Transactional
	public ResponseEntity<TopicoDto> createTopic(@RequestBody @Valid TopicoForm topicoForm,
			UriComponentsBuilder uriBuilder) {
		
		Topico topico = topicoForm.topicFormToModel(cursoRepository);
		
		topicoRepository.save(topico);
		
		URI uri = uriBuilder.path("/topicos/{id}").buildAndExpand(topico.getId()).toUri();
		
		return ResponseEntity.created(uri).body(new TopicoDto(topico));
		
	}
	
	@PutMapping("/{id}")
	@Transactional // @Transactional tells Spring to commit the transaction
	public ResponseEntity<TopicoDto> updateTopicos(@PathVariable Long id,
			@RequestBody @Valid UpdateTopicoForm updateForm){
		
		Optional<Topico> optional = topicoRepository.findById(id);
		
		if(optional.isPresent()) {
			Topico topico = updateForm.update(id, topicoRepository);
			return ResponseEntity.ok(new TopicoDto(topico));
		} else {
			return ResponseEntity.notFound().build();
		}
		
	}
	
	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity<?> deleteTopico(@PathVariable Long id){
		
		Optional<Topico> optional = topicoRepository.findById(id);
		
		if(optional.isPresent()) {
			topicoRepository.deleteById(id);
			return ResponseEntity.ok().build();
		} else {
			return ResponseEntity.notFound().build();
		}
	
	}
	
	@GetMapping("/count")
	public int topicosCount(String nomeCurso) {
		
		if(nomeCurso == null) {
			return topicoRepository.findAll().size();
		} else {
			return topicoRepository.getByCourseName(nomeCurso).size();	
		}
		
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<DetailedTopicoDto> detail(@PathVariable Long id) {
		
		Optional<Topico> topico = topicoRepository.findById(id);
		
		if(topico.isPresent()) {
			return ResponseEntity.ok(new DetailedTopicoDto(topico.get()));
		} else {
			return ResponseEntity.notFound().build();
		}
	}
	
	
	
}
