🛒 FeiFood — Sistema de Pedidos e Catálogo de Alimentos

Projeto acadêmico desenvolvido como estudante de Ciência da Computação, inspirado em plataformas como iFood.
O sistema foi construído em Java (Swing) utilizando Apache NetBeans, MVC, DAO, e banco de dados PostgreSQL.

O objetivo é criar um ambiente simples e funcional onde usuários podem se cadastrar, realizar login, buscar alimentos, visualizar detalhes e registrar pedidos.

<img width="927" height="681" alt="image" src="https://github.com/user-attachments/assets/6b03ffa9-bcc2-402d-85a0-7f4966ecabb9" />

📑 1. Objetivo do Projeto

O FeiFood é um sistema destinado a estudos de programação orientada a objetos, boas práticas, arquitetura MVC e manipulação de banco de dados.
O projeto simula um pequeno aplicativo de delivery com foco em:

Aprendizado de conexão com o banco (JDBC)

Organização em camadas: Model, View, Controller, DAO

CRUD básico de usuários

Sistema de busca e exibição de dados em JTable

Registro de pedidos vinculados ao usuário

O sistema foi desenvolvido para fins acadêmicos, servindo como base para estudos futuros sobre UX/UI, estrutura de software e desenvolvimento de backend.

🧱 2. Arquitetura do Sistema

O projeto utiliza uma estrutura MVC + DAO, dividida da seguinte forma:

✔ Model

Armazena classes responsáveis por representar os dados, como:

User

Alimento

Pedido

PedidoItem

✔ DAO (Data Access Object)

Responsável pela comunicação com o banco PostgreSQL.
Cada classe tem seu próprio DAO, por exemplo:

ClienteDAO

AlimentoDAO

PedidoDAO

✔ Controller

Controla o fluxo da aplicação e a lógica de negócio:

ControleLogin

ControleCadastro

ControlePedido

✔ View (Swing)

Telas criadas com JFrame Forms:

TelaInicial (Login)

TelaCadastro

TelaPrincipal

RealizarPedido

🔑 3. Funcionalidades Implementadas

O sistema implementa as seguintes funcionalidades completas:

🧍‍♂️ 3.1 Cadastrar novo usuário

O usuário pode criar uma conta fornecendo:

Nome

Sobrenome

Data de nascimento

Email

Senha

📌 A validação é feita no Controller antes de enviar os dados ao DAO.

O DAO insere o novo registro em:

tb_cliente


Com tratamento de exceções e mensagens via JOptionPane.

🔐 3.2 Login de usuário

A tela de login permite ao usuário acessar o sistema utilizando:

Email

Senha

O processo é:

Controller pega os dados dos campos

Envia para o ClienteDAO

DAO executa:

SELECT * FROM tb_cliente WHERE email_cliente = ? AND senha_cliente = ?


Se encontrado → abre a TelaPrincipal

Se não encontrado → mostra mensagem de erro

Também armazena os dados do usuário logado para uso posterior no pedido.

🔎 3.3 Buscar por alimento

O usuário pode digitar o nome (ou parte do nome) de um alimento.
A busca é feita no banco através de:

SELECT * FROM tb_alimentos WHERE nome_alimento ILIKE ?


O Controller recebe o texto pesquisado, chama o DAO e retorna um ResultSet.

📋 3.4 Listar informações de alimentos buscados

Os resultados da busca são exibidos em uma JTable contendo:

Nome	Tipo	Preço	Descrição	Porção

O preço é formatado com "R$"

A porção exibe "g"

Exemplo:

Hambúrguer Clássico | comida | R$22,90 | Pão e carne | 180g


A tabela é atualizada dinamicamente conforme o usuário pesquisa novos alimentos.

🛒 3.5 Cadastrar pedido

O usuário pode:

Selecionar um alimento da tabela

Adicionar ao pedido atual

Remover itens

Confirmar o pedido

Quando o pedido é salvo:

✔ Inserção na tabela tb_pedidos

Inclui:

ID do cliente

Data do pedido

Status inicial

Total calculado

✔ Inserção de cada item na tabela tb_pedido_itens

Inclui:

ID do pedido

ID do alimento

Quantidade

Preço total do item

Toda a lógica é abstraída no ControlePedido e nos respectivos DAOs.

🗄 4. Banco de Dados

O FeiFood utiliza PostgreSQL com as seguintes tabelas:

tb_cliente

tb_alimentos

tb_pedidos

tb_pedido_itens

As tabelas possuem:

chaves primárias incrementais (SERIAL)

relacionamentos via FK

campos validados para evitar inconsistências

suporte a “nota média do alimento”

🧪 5. Testes Realizados

Durante o desenvolvimento, foram feitos testes como:

Teste de conexão com banco

Teste de inserção de usuário

Teste de login válido e inválido

Teste de pesquisa com filtros parciais

Teste de formatação de tabelas

Teste de criação de pedidos com múltiplos itens

Teste de remoção e atualização do pedido

Todos os testes foram aprovados, garantindo o funcionamento essencial do sistema.

🧭 6. Conclusão

O FeiFood representa um projeto completo dentro do contexto acadêmico, demonstrando domínio de:

Java Swing e interfaces gráficas

Banco de dados relacional

Lógica de programação estruturada e orientada a objetos

Arquitetura MVC

Boas práticas com DAOs

Manipulação de JTable

Estrutura de CRUD real com diferentes entidades

O projeto abre caminho para futuras expansões, como:

Telas mais ricas em UX/UI

Sistema de avaliações dos alimentos

Histórico de pedidos do usuário

Integração com API de pagamentos fictícia

Versão mobile ou web

📘 7. Sobre o Desenvolvedor

Projeto desenvolvido por:

Higor Augusto
Estudante de Ciência da Computação – 3º Semestre
Focado em desenvolvimento de software, UX/UI e aplicações Java.
