#language: pt
#Author: Gabriel Izidorio de Oliveira
#Version: 1.0
#Encoding: UTF-8

 @regressivo
Funcionalidade: Busca de personagens
  Eu como administrador do sistema, quero buscar informações sobre os personagens da Marvel

  @marvel
  Esquema do Cenário: Buscar um personagem específico na API Marvel
    Dado que tenho dados de acesso validos
    Quando busco um personagem existente pelo "<idDoPersonagem>"
    Então o "<nomeDoPersonagem>" deve ser retornado
    E esse personagem deve participar do evento "Civil War"
    E o status code da request deve ser 200

    Exemplos:
      | idDoPersonagem | nomeDoPersonagem |
      | 1009262        | Daredevil        |
      | 1009220        | Captain America  |
      | 1009187        | Black Panther    |