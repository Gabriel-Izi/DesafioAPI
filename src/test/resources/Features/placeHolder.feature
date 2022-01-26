#language: pt
#Author: Gabriel Izidorio de Oliveira
#Version: 1.0
#Encoding: UTF-8

@placeHolder @regressivo
Funcionalidade: Criar, modificar e deletar publicações da API JsonPlaceHolder
  Eu como administrador do sistema, quero gerenciar publicações

@patchPlaceHolder
  Cenário: Atualizar um dado especifico de uma publicacao na API JSONPlaceHolder
    Dado que tenho dados de acesso validos na API JSONPlaceHolder
    Quando modifico um dado de uma publicacao
    Então deve retornar a publicacao com dados alterados
    E o status code da request JSONPlaceHolder deve ser 200

@postPlaceHolder
  Cenário: Criar uma publicacao na API JSONPlaceHolder
    Dado que tenho dados de acesso validos na API JSONPlaceHolder
    Quando crio uma publicacao
    Então deve retornar a publicacao com dados criados
    E o status code da request JSONPlaceHolder deve ser 201

@putPlaceHolder
  Cenário: Modificar uma publicacao na API JSONPlaceHolder
    Dado que tenho dados de acesso validos na API JSONPlaceHolder
    Quando modifico uma publicacao
    Então deve retornar a publicacao com dados modificados
    E o status code da request JSONPlaceHolder deve ser 200

@getPlaceHolder
  Cenário: Buscar uma publicacao na API JSONPlaceHolder
    Dado que tenho dados de acesso validos na API JSONPlaceHolder
    Quando busco uma publicacao pelo id
    Então deve retornar a publicacao do id buscado
    E o status code da request JSONPlaceHolder deve ser 200

@deletePlaceHolder
  Cenário: Deletar uma publicacao na API JSONPlaceHolder
    Dado que tenho dados de acesso validos na API JSONPlaceHolder
    Quando deleto uma publicacao pelo id
    Então deve retornar uma resposta vazia
    E o status code da request JSONPlaceHolder deve ser 200


#@negativoPatchPlaceHolder
#  Cenário: Atualizar com um dado invalido especifico em uma publicacao na API JSONPlaceHolder
#    Dado que tenho dados de acesso validos na API JSONPlaceHolder
#    Quando modifico um dado de uma publicacao com um dado invalido
#    Então o status code da request JSONPlaceHolder deve ser 400
#
#@negativoPostPlaceHolder
#  Cenário: Criar uma publicacao invalida na API JSONPlaceHolder
#    Dado que tenho dados de acesso validos na API JSONPlaceHolder
#    Quando crio uma publicacao invalida
#    Então o status code da request JSONPlaceHolder deve ser 400
#
#@negativoPutPlaceHolder
#  Cenário: Modificar uma publicacao com dados invalidos na API JSONPlaceHolder
#    Dado que tenho dados de acesso validos na API JSONPlaceHolder
#    Quando modifico uma publicacao com dados invalidos
#    Então o status code da request JSONPlaceHolder deve ser 400
#
#@negativoGetPlaceHolder
#  Cenário: Buscar uma publicacao invalida na API JSONPlaceHolder
#    Dado que tenho dados de acesso validos na API JSONPlaceHolder
#    Quando busco uma publicacao por um id invalido
#    Então deve retornar uma publicacao vazia
#    E o status code da request JSONPlaceHolder deve ser 404
#
#@negativoDeletePlaceHolder
#Cenário: Deletar uma publicacao inexistente na API JSONPlaceHolder
#  Dado que tenho dados de acesso validos na API JSONPlaceHolder
#  Quando tento deletar uma publicacao com id inexistente
#  Então deve retornar uma resposta vazia
#  E o status code da request JSONPlaceHolder deve ser 404