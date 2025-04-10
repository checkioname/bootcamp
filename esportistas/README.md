### Exercício Esportistas
Praticar esportes é muito importante para sua saúde. Consideramos que uma pessoa que pratica algum tipo de atividade física regularmente é um esportista.

Para validar se uma pessoa é um atleta, é necessário um aplicativo para vincular duas entidades:


Pessoa, cujos atributos devem ser:
Nome
Sobrenome
Idade
Esporte, cujos atributos devem ser:
Nome
Nível

Nosso aplicativo deve ter a seguinte funcionalidade:


Visualize todos os esportes que carregamos.
- PATH: /findSports

Verifique se existe um esporte digitando seu nome. Se existir, o nível do esporte deverá ser exibido. Use a classe ResponseEntity para retornar a resposta.
- PATH: /findSport/{name}
 
Mostrar os esportistas. Queremos ver uma lista com o nome e o sobrenome da pessoa e o nome do esporte que ela pratica (não é necessário ver a idade ou o nível do esporte que ela pratica). Para isso, é importante que você use uma DTO.
- PATH: /findSportsPersons

Observação: No momento, nenhum banco de dados será usado, portanto, os objetos em listas são manipulados.

