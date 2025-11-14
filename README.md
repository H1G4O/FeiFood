📘 FeiFood – Sistema de Pedidos e Gerenciamento de Alimentos
Relatório Técnico / README do Projeto
🧾 1. Apresentação do Projeto

O FeiFood é um sistema inspirado no funcionamento de plataformas de delivery, desenvolvido em Java utilizando Apache NetBeans, Swing (JFrame Forms), PostgreSQL (pgAdmin4) e arquitetura MVC + DAO.

O objetivo do sistema é permitir que usuários realizem cadastro, login, visualização de alimentos disponíveis, além de criar, editar e excluir pedidos, com manipulação de itens em tempo real.

Este projeto foi desenvolvido como parte da disciplina de Programação Orientada a Objetos e do laboratório universitário, integrando conceitos de:

Modelagem de dados

CRUD completo

Interface gráfica

Banco de dados relacional

Arquitetura MVC

DAO (Data Access Object)

Relacionamentos 1:N e N:N

Boas práticas de programação

🎯 2. Objetivos do Sistema

O FeiFood foi projetado para cumprir as seguintes funcionalidades principais:

✔ Cadastro de clientes

Inserir novos usuários

Validar formato de dados

Armazenar em PostgreSQL

✔ Login de clientes

Verificação segura via PreparedStatement

Tela de login integrada com o banco

✔ Visualização e busca de alimentos

Consulta dinâmica no banco

Filtro por nome via LIKE

Exibição dos preços formatados (R$) e porções (g)

✔ Criação de pedidos

Adicionar alimentos ao carrinho

Definir quantidades

Remover alimentos

Exibir totais do pedido

✔ Persistência do pedido

Inserção na tabela tb_pedidos

Inserção dos itens na tabela tb_itempedido

Relacionamento via chave estrangeira

🏛 3. Arquitetura do Sistema

O FeiFood segue a arquitetura MVC (Model — View — Controller), organizada da seguinte forma:

📌 3.1 Model

Conjunto de classes que representam as entidades do sistema:

User

Alimento

Pedido

ItemPedido

Cada model possui atributos, construtores, getters e setters.

📌 3.2 DAO

Classes responsáveis por comunicação com o banco:

ClienteDAO

AlimentoDAO

PedidoDAO

ItemPedidoDAO

Cada DAO utiliza:

PreparedStatement

conexões fornecidas por Conexao.java

retorno com ResultSet quando necessário

todas as consultas são parametrizadas para evitar SQL Injection

📌 3.3 Controller

Camada intermediária entre UI e DAO:

ControleLogin

ControlePesquisaAlimento

ControlePedido

Responsabilidades:

Interpretar ações da interface

Validar entradas

Chamar os DAOs correspondentes

Atualizar tabelas e telas

📌 3.4 View

Interface gráfica desenvolvida no NetBeans:

TelaInicial (login)

TelaCadastroCliente

TelaPrincipal

RealizarPedido

GerenciarPedidos

A construção utiliza:

JFrame

JTable com DefaultTableModel

JTextField

JButton

JScrollPane

JOptionPane

🗄 4. Estrutura do Banco de Dados

Um banco PostgreSQL foi modelado com as seguintes tabelas principais:

4.1 — Tabela tb_cliente
Coluna	Tipo	Observação
id_cliente	SERIAL	PK
nome_cliente	VARCHAR	
sobrenome_cliente	VARCHAR	
nascimento_cliente	DATE	
email_cliente	VARCHAR	Único
senha_cliente	VARCHAR	
4.2 — Tabela tb_alimentos
Coluna	Tipo	Observação
id_alimento	SERIAL	PK
nome_alimento	VARCHAR	
tipo_alimento	VARCHAR	
preco_alimento	NUMERIC(10,2)	
descricao_alimento	VARCHAR	
porcao_alimento	INTEGER	
nota_alimento	NUMERIC	NOT NULL (default definido)
4.3 — Tabela tb_pedido
Coluna	Tipo	Observação
id_pedido	SERIAL	PK
id_cliente	INT	FK → cliente
data_pedido	TIMESTAMP	NOW() default
total_pedido	NUMERIC	
4.4 — Tabela tb_itempedido
Coluna	Tipo	Observação
id_item	SERIAL	PK
id_pedido	INT	FK → pedido
id_alimento	INT	FK → alimento
quantidade	INT	
subtotal	NUMERIC	
🖥 5. Fluxo de Funcionamento do Sistema

A seguir, o fluxo completo do FeiFood.

▶ 5.1 Login

Usuário informa email e senha

Controller → chama DAO

Se encontrado → abre TelaPrincipal

Se inválido → mensagem de erro

▶ 5.2 Listagem e Pesquisa de Alimentos

Tela carrega todos os alimentos na JTable

Campo txtPesquisa filtra por nome

Resultados atualizados dinamicamente

▶ 5.3 Seleção e construção do pedido

Usuário seleciona alimentos da tabela

Pressiona btnAdicionar

Itens são adicionados à tabela do pedido

Sistema calcula subtotal + total geral

▶ 5.4 Salvando o pedido no banco

Controller cria registro na tabela tb_pedido

Obtém automaticamente o ID gerado

Insere cada item na tabela tb_itempedido

Exibe mensagem de sucesso

🛠 6. Tecnologias Utilizadas
Tecnologia	Função
Java 17	Linguagem principal
Swing (JFrame Forms)	Interface gráfica
Apache NetBeans	IDE
PostgreSQL + pgAdmin4	Banco de dados
JDBC	Conexão com o banco
MVC + DAO	Arquitetura do sistema
GitHub / README.md	Documentação
📌 7. Próximos Passos e Melhorias Futuras

O projeto já é totalmente funcional, mas pode ser expandido:

🔹 Implementar sistema de avaliações

Salvar notas dos alimentos e calcular média.

🔹 Pagamentos

Simulação ou integração com APIs.

🔹 Tema visual / CSS para Swing

Deixar a interface mais moderna.

🔹 Responsividade em telas menores

Otimização visual.

🔹 Relatórios PDF de pedidos

Exportação automática.

🧑‍💻 8. Conclusão

O projeto FeiFood representa um sistema completo, profissional e estruturado, que cobre:

modelagem de dados

interface gráfica

comunicação com banco

CRUD completo

pesquisa dinâmica

controle de pedidos

Além de funcionar como entrega acadêmica, o projeto é uma excelente base para sistemas de vendas, delivery, controle de estoque e muito mais.

📎 9. Contato e Contribuição

Sinta-se livre para clonar, testar, melhorar e enviar PRs.

Desenvolvedor: Higor Augusto
Curso: Ciência da Computação – 3º Semestre
