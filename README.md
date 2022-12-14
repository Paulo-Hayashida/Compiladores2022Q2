# Compiladores2022Q2
- TRABALHO FINAL da disciplina de Compiladores, ministrada pelo professor Francisco Isidro pela Universidade Federal do ABC.

| Nomes                         | RA        |
| ------------------------------| ----------|
| Paulo Henrique Eiji Hayashida | 11104714  |
| Eduardo Yukio Sugimoto        | 111076814 |
        
## Check List (Obrigatorio)       
- A) Possuir 2 tipos de dados (pelo menos 1 String)
  - Variaveis criadas: Tipos Double para para Numero e String para Texto.
  
- B) Possuir a instrução de decisão (if/else)
  - Estruturas de decisão criadas: If para Se e Else para Senao.
        
- C) Pelo menos 1 estrutura de repetição 
  - Estrutura de repetição criada: While para Enquanto.
  
- D) Verificar Atribuições com compatibilidade de tipos (semântica)
  -  Criadas funções de verificação de compatibilidade entre tipos para casos de Declaração de variáveis(1) e Utilização de operadores(2).
  -  exemplo 1: a = 4 + "banana", operação inválida, pois o operador '+' (Soma) espera 'numero' 'OP' 'numero'
  -  exemplo2: numero a = "banana", operação inválida, pois a variável 'a' foi declarada como tipo 'numero' e recebeu o tipo 'texto' (IsiLanguage).
  -  Várias funções que capturam os tokens anteriores, armazenam-as em variávies para serem analisadas seus tipos, sendo que se não havendo concordancia, gera-se um erro de compilação.
  
- E) Possuir operações de Entrada e Saída
  - Para a Entrada: Conversão da função Leia (IsiLanguage) para 'Scanner key' na linguagem Java e para 'scanf' na linguagem C.
  - Para Saída: Conversão da função Escreva (IsiLanguage) para 'System.out.println' na linguagem Java e para 'printf' na linguagem C.
 
- F) Aceitar números decimais
  - Números decimais são naturalmente tratados para o tipo Double.
   
- G) Verificar declaração de variávies (não usar variáveis que não foram declaradas)
  - Criada a função de verificação  de variáveis não declaradas utilizando Tabela de Simbolos.
  - Função verifica se o símbolo existe na table de variáveis, está função está no arquivo IsiLang.g4 abaixo das declarações das variáveis.
        
- H) Verificar se há variáveis declaradas e não-utilizadas (warning)
  - Criada a função de verificação de variáveis não declaradas e emissão de mensagens 'Warning' no console após compilação do arquivo.
  - Função abaixo do prog, que verifica o valor dos símbolos, caso "Não Usado", exibe um Warning ao final do programa.
  
- I) Geração de pelo menos 1 linguagem destino (C/Java/Python)
  - Geração de arquivos em Java e C.
  
## Anexos A (2 Itens)
- A1) Nova instrução para Switch/Case (escolha/caso)
  - NÃO UTILIZADO. 
  
- A2) Mais tipos de dados
  - NÃO UTILIZADO. 
  
- A3) Inclusão de novos operadores (exponenciação, raiz quadrada, logaritmos)
  - Criados os operadores de exponenciação '^', raiz quadrada 'sqrt'  e logaritmos 'log'.
  - O operador de exponenciação funcionou corretamente, gerando seus equivalentes em Java e em C.
  - Já os operadores de radiciação e logaritmo não apresentaram comportamento esperado.
 
- A4) Geração de código para mais de uma linguagem diferente
  - Geração de arquivos em Java e C. 

## Anexos B (2 Itens - NÃO UTILIZADO)

## Considerações
- Mais detalhe sobre as funções podem ser encontrados no código fonte.
- Os comportamentos não esperados das funções 'sqrt' e 'log' refletem diretamente na possibilidade da gramática estar incompleta, deixando margem para trabalhos futuros.
- O link para o vídeo no YouTube com os testes: https://www.youtube.com/watch?v=MgwfCNg5sJw
