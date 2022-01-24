#language: pt
#Author: Gabriel Izidorio de Oliveira
#Version: 1.0
#Encoding: UTF-8

@pets @regressivo
Funcionalidade: Criar, modificar e deletar pets
  Eu como administrador do sistema, quero gerenciar pets

@petsPost
  Cenário: Criar pets na API Petstore
    Dado que tenho dados de acesso validos na API Petstore
    Quando crio um pet
    Então deve retornar as informacoes do pet criado
    E o status code da request Petstore deve ser 200

@petsPut
  Cenário: Modificar pet API Petstore
    Dado que tenho dados de acesso validos na API Petstore
    E crio um pet
    Quando modifico esse pet
    Então deve retornar as informacoes do pet modificado
    E o status code da request Petstore deve ser 200

@petsDelete
  Cenário: Deletar pet API Petstore
    Dado que tenho dados de acesso validos na API Petstore
    E crio um pet
    Quando deleto esse pet
    Então deve retornar o id do pet deletado
    E o status code da request Petstore deve ser 200


@negativoPetsPost
  Cenário: Criação de pet inválida API Petstore
    Dado que tenho dados de acesso validos na API Petstore
    Quando crio um pet com dados invalidos
    Então deve retornar a mensagem "invalid input"
    E o status code da request Petstore deve ser 405

@negativoPetsPut
  Cenário: Modificação pet inválida API Petstore
    Dado que tenho dados de acesso validos na API Petstore
    E crio um pet
    Quando modifico esse pet com dados invalidos
    Então deve retornar a mensagem "invalid ID supplied"
    E o status code da request Petstore deve ser 400

@negativoPetsDelete
  Cenário: Deletar pet com id inválida API Petstore
    Dado que tenho dados de acesso validos na API Petstore
    Quando deleto um pet com id invalido
    Então deve retornar a mensagem "Invalid ID supplied"
    E o status code da request Petstore deve ser 400