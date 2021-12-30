#language: pt
#Author: Gabriel Izidorio de Oliveira
#Version: 1.0
#Encoding: UTF-8

@userspetstore @regressivo
Funcionalidade: Criar, modificar e deletar usuários
  Eu como administrador do sistema, quero gerenciar usuários

@usersPost
  Cenário: Criar usuário API Petstore
    Dado que tenho dados de acesso validos de usuario na API Petstore
    Quando crio um usuario
    Então deve retornar o id do usuario criado
    E o status code da request userPetstore deve ser 200

@usersPut
  Cenário: Modificar usuário API Petstore
    Dado que tenho dados de acesso validos de usuario na API Petstore
    E crio um usuario
    Quando modifico esse usuario
    Então deve retornar o id do usuario modificado
    E o status code da request userPetstore deve ser 200

@usersDelete
  Cenário: Deletar usuário API Petstore
    Dado que tenho dados de acesso validos de usuario na API Petstore
    E crio um usuario
    Quando deleto esse usuario
    Então deve retornar o username do usuario deletado
    E o status code da request userPetstore deve ser 200