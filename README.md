# ffvgrupo2
Grupo 2 - Capítulo 4 em diante
1) Reunião de Revisão do Sprint
As funcionalidades estão operando e precisamos atualizar o design. Houveram problemas no cadastro de livros
e na conexão JPA com o framework Spring Tools e a base de dados, no entanto pretendemos solucionar o problema
nos próximos incrementos.
2) Reunião de Retrospectiva do Sprint
O processo de desenvolvimento fluiu de forma consistente, as funcionalidades foram implementadas continuamente,
cada nova iteração produziu diferentes resultados que careciam em alguns momentos de correção. No fim a abordagem
incremental resultou na finalização de partes dos requisitos.

Proximo passo: CRUD de usuario.
<h1>Projeto - Sistema de Controle de Empréstimo e Devolução de Livros<h1>
<h2>Centro Paula Souza - Fatec Ferraz - Curso de Análise e Desenvolvimento de Sistemas<h2>
<h3>Disciplina – Programação Web – Prof. Edson Saraiva de Almeida</h3>
<h4>Grupo 2</h4>
- Igor Gomes Oliveira Ramos
- Leandro de Oliveira Lozano
- Matheus Silva do Espirito Santo
- Michel de Lima Soares
  
<h4>Objetivo</h4>
<p>Este projeto tem como objetivo implementar uma aplicação de empréstimo e devolução de livros, para se familiarizar com a
plataforma de desenvolvimento web JEE Spring Boot.</p>
  
<h4>Processo de Desenvolvimento</h4>
O processo de desenvolvimento segue uma adaptação do Scrum. Cada interação tem a duração de 4 aulas. Ao final da interação o
código é avaliado pelo time junto com o professor na atividade de revisão da sprint. Em seguida a reunião de restrospectiva do sprint é
realizada pelo grupo e uma ata é publicada no Teams.

<h4>Problemas de implementacao</h4>
<p>A implementacao gerou uma serie de problemas, os principais foram no momento de implementar o cadastro de livros, usuarios e emprestimos.
O maior trabalho foi o registro de emprestimos sendo que os outros registros nao consumiram tanto quanto ele. Mas no fim, apos muito
esforco conseguimos implementar essa funcionalidade tao importante para o projeto. Outras funcionalidades como o login foram consideravelmente
mais simples do que as comentadas acima. O menu ficou um pouco mais simples, no entanto ajuda bastante na navegacao.</p>

<h3>Backlog do produto</h3>
<h4>REQ01 – Cadastrar Livro</h4>
Como – atendente da biblioteca
Eu quero – cadastrar um livro
De maneira que – seja possível consultar e emprestar o livro

<h4>REQ01CT01 – cadastrar com sucesso</h4>
Dado: que o atendente da biblioteca tem um livro não cadastrado
Quando: o atendente informa os dados do livro
Então: o sistema valida os dados E apresenta uma mensagem confirmando o cadastro do livro
- REQ02 - Consultar livro
- REQ03 - Alterar livro
- REQ04 - Excluir livro
- REQ05 – Cadastrar usuário
Como – atendente da biblioteca
Eu quero – cadastrar um usuário
De modo que – seja possível realizar o empréstimo de um livro.
- REQ05CT01 – Cadastro de usuário com sucesso
Dado (pré-requisito) – que o RA do usuário não está cadastrado.
Quando (descrição das ações que devem ser realizadas) – o atendente digita as informações do usuário e confirma a operação.
Então (resultado esperado) – o sistema retorna uma mensagem confirmando que o cadastro foi realizado
- REQ05CT02 – Cadastro de usuário invalido
Dado (pré-requisito) – que o RA do usuário está cadastrado.
Quando (descrição das ações que devem ser realizadas) – o atendente digita as informações do usuário e confirma a operação.
Então (resultado esperado) – o sistema retorna uma mensagem informando que o usuário já está cadastrado
<h4>1. Planejamento da Sprint</h4>
Durante a fase de planejamento as funcionalidades nesta interação são selecionadas do backlog do produto.
<h4>2. Estratégia de desenvolvimento.</h4>
Na primeira interação a meta é criar um baseline (base de sustentação) da arquitetura do sistema a fim de definir como o código será
organizado nas próximas interações. A arquitetura se desenvolve a partir de um exame dos requisitos mais significativos (aqueles que
têm grande impacto na arquitetura do sistema) e de uma avaliação de risco. A estabilidade da arquitetura é avaliada através de um ou
mais protótipos de arquitetura. O projeto do “Sistema de Controle de Empréstimo de Livros” deve se utilizar de uma arquitetura que
permita flexibilidade na configuração do sistema de persistência (mudança do sistema de gerenciamento de banco de dados) e
manutenções na interface de usuário com poucos efeitos colaterais. A arquitetura selecionada para atender esta necessidade é a
arquitetura MVC.
<h5>Divisao de tarefas</h5>
A estratégia de construção e integração do software será ascendente na hierarquia de controle, ou seja, da base de dados (backend) para
a interface de interação homem máquina (frontend).
<h4>Modelo de Domínio</h4>
![modelo de dominio](https://user-images.githubusercontent.com/14267502/84425324-bd55a580-abf7-11ea-99c7-f427b80fb7cc.png)
