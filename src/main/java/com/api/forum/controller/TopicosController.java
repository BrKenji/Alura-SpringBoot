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

import com.api.forum.controller.form.TopicoForm;
import com.api.forum.dto.TopicoDto;
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
	public ResponseEntity<TopicoDto> createTopic(@RequestBody @Valid TopicoForm topicoForm,
			UriComponentsBuilder uriBuilder) {
		
		Topico topico = topicoForm.topicFormToModel(cursoRepository);
		
		topicoRepository.save(topico);
		
		URI uri = uriBuilder.path("/topicos/{id}").buildAndExpand(topico.getId()).toUri();
		
		return ResponseEntity.created(uri).body(new TopicoDto(topico));
		
	}
	
	@GetMapping("/count")
	public int topicosCount(String nomeCurso) {
		
		if(nomeCurso == null) {
			return topicoRepository.findAll().size();
		} else {
			return topicoRepository.getByCourseName(nomeCurso).size();	
		}
		
	}
	
	
	
}
