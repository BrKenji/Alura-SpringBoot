INSERT INTO USUARIO(nome, email, senha) VALUES('Aluno', 'aluno@email.com', '$2a$10$RXiTePEpImwz8k6DpOR.7eQ2GvVWcoaYk5nL6wP2NcKkAMYdL31Li');

INSERT INTO CURSO(nome, categoria) VALUES('Spring Boot', 'Programação');
INSERT INTO CURSO(nome, categoria) VALUES('HTML 5', 'Front-end');
INSERT INTO CURSO(nome, categoria) VALUES('Django', 'Programação');
INSERT INTO CURSO(nome, categoria) VALUES('MondoDB', 'Database');
INSERT INTO CURSO(nome, categoria) VALUES('PHP', 'Back-end');

INSERT INTO TOPICO(titulo, mensagem, data_criacao, status, autor_id, curso_id) VALUES('Dúvida 1', 'Erro ao criar projeto', '2019-05-05 18:00:00', 'NAO_RESPONDIDO', 1, 1);
INSERT INTO TOPICO(titulo, mensagem, data_criacao, status, autor_id, curso_id) VALUES('Dúvida 2', 'Projeto não compila', '2019-05-05 20:00:00', 'NAO_RESPONDIDO', 1, 1);
INSERT INTO TOPICO(titulo, mensagem, data_criacao, status, autor_id, curso_id) VALUES('Dúvida 3', 'Projeto não compila', '2019-05-05 14:00:00', 'NAO_RESPONDIDO', 1, 3);
INSERT INTO TOPICO(titulo, mensagem, data_criacao, status, autor_id, curso_id) VALUES('Dúvida 4', 'Não consigo criar coleção', '2019-05-05 13:00:00', 'NAO_RESPONDIDO', 1, 4);
INSERT INTO TOPICO(titulo, mensagem, data_criacao, status, autor_id, curso_id) VALUES('Dúvida 5', 'Sem acesso a base de dados', '2019-05-05 09:00:00', 'NAO_RESPONDIDO', 1, 4);
INSERT INTO TOPICO(titulo, mensagem, data_criacao, status, autor_id, curso_id) VALUES('Dúvida 6', 'Problema com instalação do PHP', '2019-05-05 12:00:00', 'NAO_RESPONDIDO', 1, 5);
INSERT INTO TOPICO(titulo, mensagem, data_criacao, status, autor_id, curso_id) VALUES('Dúvida 7', 'Tag HTML', '2019-05-05 20:00:00', 'NAO_RESPONDIDO', 1, 2);
