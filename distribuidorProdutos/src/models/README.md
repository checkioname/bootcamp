## Exercício 2


Temos que entregar um trabalho para um distribuidor de produtos, portanto, vamos gerar um programa que executa diferentes operações. Teremos duas categorias de produtos diferentes: Perecível e Não Perecível.


1º - Crie uma classe Product com os seguintes atributos: 
- String name e double price; 
- ela deve definir os métodos getters e setters para seus atributos, 
- um construtor que receba todos os seus atributos como parâmetro e o método toString().
- Crie o método calculate() para o qual passaremos um parâmetro int chamado quantityOfProducts; esse método deve multiplicar o preço pela quantidade de produtos passada.

Crie a classe Perishable, que terá um atributo chamado daysPerCaducar do tipo int, como no caso do produto, defina setters, getters, um construtor que receba todos os atributos como parâmetro e o método toString(). Essa classe deve herdar de Product e sobrescrever o método calculate(), que deve fazer o mesmo que a classe Product (multiplicar o preço pelo número de produtos) e, adicionalmente, reduzir o preço de acordo com o método daysForString():

- Se faltar um dia (1) para a expiração, o preço final será reduzido em 4 vezes. 
- Se faltarem dois dias (2) para a expiração, o preço final será reduzido em 3 vezes. 
- Se faltarem três dias (3) para a expiração, o preço final será reduzido pela metade. 
- 
Crie a classe Nãoperecível, ela terá um atributo chamado type, que será uma String, crie setters, getters, construtor e método toString(); nessa classe, o método calculate() fará exatamente o mesmo que na classe Product. 

Crie uma classe executável chamada Distribuidora na qual você criará uma matriz de produtos e imprimirá o preço total ao vender 5 produtos de cada tipo. Crie os elementos da matriz com os dados que você deseja.
