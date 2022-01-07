#language: pt
#Author: Gabriel Izidorio de Oliveira
#Version: 1.0
#Encoding: UTF-8

@placeholder @regressivo
Funcionalidade: Atualizar publicações da API JsonPlaceHolder
  Eu como administrador do sistema, quero alterar publicações

  Cenário: Atualizar dados de uma publicacao na API JSONPlaceHolder
    Dado que tenho dados de acesso validos na API JSONPlaceHolder
    Quando modifico dados em uma publicacao
    Então deve retornar a publicacao com dados alterados
    E o status code da request JSONPlaceHolder deve ser 200