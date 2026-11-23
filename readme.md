# Um menino bonito

O menino tem olhos brilhantes e um sorriso que ilumina o rosto. Seus cabelos
são arrumados, seu jeito é gentil e sua curiosidade faz dele alguém especial.

Quando ele fala, transmite alegria. Quando brinca, espalha imaginação e
descobertas. E, com sua bondade, consegue deixar o dia de todas as pessoas
ao seu redor um pouco mais feliz.

test

## Exemplo em Java

```java
public class Menino {
    private String nome;
    private String sorriso;

    public Menino(String nome, String sorriso) {
        this.nome = nome;
        this.sorriso = sorriso;y
    }

    public void apresentar() {
        System.out.println(nome + " sorri " + sorriso + ".");
    }

    public static void main(String[] args) {
        Menino menino = new Menino("Lucas", "gentilmente");
        menino.apresentar();
    }
}
```

## Exemplo em Python

```python
class Menino:
    def __init__(self, nome, sorriso):
        self.nome = nome
        self.sorriso = sorriso

    def apresentar(self):
        print(f"{self.nome} sorri {self.sorriso}.")


menino = Menino("Lucas", "gentilmente")
menino.apresentar()
```

## Programas separados

- [Programa Java](java/Menino.java)
- [Programa Python](python/menino.py)

# Mod simples de Minecraft

Este projeto cria um mod Fabric para Minecraft 1.21.4.

O mod adiciona:

- Um item chamado **Amuleto do Menino**.
- O comando `/oi`, que envia uma mensagem ao jogador.

## Requisitos

- Java 21
- Gradle (necessário para gerar o wrapper; o projeto ainda não inclui `gradlew.bat`)
- Minecraft 1.21.4 com Fabric Loader
- Fabric API 1.21.4

## Como compilar no Windows

1. Instale o JDK 21 e o Gradle. Se o instalador do Java oferecer essa opção,
   adicione o JDK ao `PATH`.
2. Abra o PowerShell e confirme que os comandos estão disponíveis:

```powershell
java -version
javac -version
gradle -version
```

3. Entre na pasta do projeto:

```powershell
cd "C:\Users\Meu computador\Repositorios\mods\mdm\minecraft-mod"
```

4. Gere o wrapper do Gradle. Isso cria `gradlew.bat` para compilar o projeto
   usando uma versão definida do Gradle:

```powershell
gradle wrapper --gradle-version 8.11
```

5. Compile o mod:

```powershell
.\gradlew.bat build
```

Na primeira compilação, o Gradle pode demorar enquanto baixa as dependências.
Quando terminar, o arquivo `.jar` do mod estará em
`minecraft-mod\build\libs`.

## Como instalar e usar no Minecraft

1. Instale o Fabric Loader para Minecraft 1.21.4.
2. Abra o launcher uma vez com o perfil Fabric e depois feche o jogo.
3. Baixe a Fabric API compatível com Minecraft 1.21.4.
4. Abra a pasta `.minecraft`: pressione `Win + R`, digite
   `%appdata%\.minecraft` e pressione Enter.
5. Abra ou crie a pasta `mods` dentro de `.minecraft`.
6. Copie para `mods` o arquivo da Fabric API e o `.jar` do mod gerado em
   `minecraft-mod\build\libs`.
7. No launcher, inicie o perfil Fabric para Minecraft 1.21.4.
8. Em um mundo, digite `/oi` no chat para testar o comando. Se comandos
   estiverem desativados no mundo, habilite-os ou abra o mundo para LAN com
   comandos permitidos.
9. No modo Criativo, procure **Amuleto do Menino** na aba **Ferramentas**.

Os exemplos em `java/` e `python/` são programas independentes e não são mods;
não os copie para a pasta `mods`. O mod do Minecraft é o projeto dentro de
`minecraft-mod/`.

**Aparência do item:** o mod registra o item, mas ainda não inclui um modelo nem
uma textura personalizados. Por isso, a imagem do item pode aparecer como uma
textura ausente no jogo.

## Explicação para iniciantes

Um mod é um programa Java executado pelo Minecraft. Ele não modifica os
arquivos originais do jogo; em vez disso, registra novos conteúdos enquanto
o Fabric inicia o jogo.

### Estrutura do projeto

```text
minecraft-mod/
├── build.gradle
├── gradle.properties
├── settings.gradle
└── src/
    └── main/
        ├── java/
        │   └── com/example/meninomod/MeninoMod.java
        └── resources/
            ├── assets/meninomod/lang/
            │   ├── en_us.json
            │   └── pt_br.json
            └── fabric.mod.json
```

- `MeninoMod.java` contém a lógica do mod.
- `fabric.mod.json` apresenta o mod ao Fabric Loader.
- Os arquivos `lang/*.json` definem os nomes exibidos em cada idioma.
- `build.gradle` informa como compilar o projeto.
- `gradle.properties` guarda as versões usadas pelo projeto.
- `settings.gradle` define o nome do projeto e os repositórios Gradle.

### Como o código funciona

O código principal é `com.example.meninomod.MeninoMod`.

```java
public static final String MOD_ID = "meninomod";
```

`MOD_ID` é o identificador único do mod. Ele identifica recursos como blocos
e itens sem confundi-los com recursos de outros mods.

```java
public static final Item AMULETO_DO_MENINO = new Item(
        new Item.Settings()
);
```

Essa parte cria um item básico chamado `AMULETO_DO_MENINO`. A classe `Item`
representa um item do Minecraft e `Item.Settings()` contém suas configurações.

```java
Registry.register(
        Registries.ITEM,
        Identifier.of(MOD_ID, "amuleto_do_menino"),
        AMULETO_DO_MENINO
);
```

Essa parte registra o item no Minecraft. O identificador completo do item é:

```text
meninomod:amuleto_do_menino
```

Sem esse registro, o Minecraft não reconheceria o objeto criado no Java.

```java
ItemGroupEvents.modifyEntriesEvent(ItemGroups.TOOLS)
        .register(entries -> entries.add(AMULETO_DO_MENINO));
```

Esse código coloca o item na aba **Ferramentas** do inventário criativo.

### Como o comando funciona

```java
CommandManager.literal("oi")
```

Essa parte cria o nome do comando `/oi`.

```java
.executes(context -> {
    context.getSource().sendFeedback(
            () -> Text.literal("Olá! Este é o Menino Mod."),
            false
    );
    return 1;
})
```

Quando o jogador digita `/oi`, o método `executes` é chamado. Ele envia uma
mensagem ao jogador, e `return 1` informa ao Minecraft que o comando terminou
com sucesso.

### O arquivo `fabric.mod.json`

O arquivo `fabric.mod.json` identifica o projeto para o Fabric. Entre outras
informações, ele declara:

- O nome e o identificador do mod.
- A versão.
- A versão do Minecraft.
- O Java mínimo necessário.
- A classe Java que inicia o mod.

O campo `main` informa ao Fabric que a classe inicial é:

```text
com.example.meninomod.MeninoMod
```

O Fabric executa o método `onInitialize()` dessa classe.

### Como o Java inicia o mod

O método mais importante é:

```java
@Override
public void onInitialize()
```

Ele é executado quando o Fabric inicia o mod. Dentro dele, o código:

1. Cria o identificador do item.
2. Registra o item.
3. Adiciona o item ao inventário criativo.
4. Registra o comando `/oi`.

### Como os textos são traduzidos

Os nomes em português e inglês ficam em:

- `assets/meninomod/lang/pt_br.json`
- `assets/meninomod/lang/en_us.json`

Por exemplo:

```json
{
  "item.meninomod.amuleto_do_menino": "Amuleto do Menino"
}
```

A chave à esquerda identifica o item no código. O texto à direita é exibido no
jogo. Assim, o mesmo item pode ter nomes diferentes em idiomas diferentes.

### Ferramentas usadas na compilação

- **Java 21:** permite executar o compilador Java e é usado pelo Minecraft 1.21.4.
- **Gradle:** baixa bibliotecas, compila o Java e cria o arquivo `.jar`.
- **Fabric Loader:** carrega o mod dentro do Minecraft.
- **Fabric API:** fornece ferramentas para itens, comandos, eventos e outros recursos.

O projeto ainda não possui o wrapper do Gradle (`gradlew.bat`). Para compilar,
é necessário um JDK 21 e o wrapper gerado ou uma instalação local do Gradle.

## Guia completo: entenda tudo passo a passo

Esta seção foi escrita para alguém que nunca criou um programa Java. Ela não
supõe conhecimento prévio. Leia na ordem indicada e não pule os conceitos.
Mesmo que pareça longo, cada parte explica uma decisão usada no projeto.

### 1. Java, Fabric e Minecraft são coisas diferentes

O Minecraft é o programa que o jogador abre.

O Java é a linguagem usada para escrever partes do Minecraft e deste mod.
Você escreve texto em um arquivo `.java`, mas o computador não executa esse
texto diretamente. Primeiro, o compilador Java transforma o código em arquivos
executáveis chamados de bytecode.

O Fabric Loader é um programa que sabe carregar mods junto ao Minecraft.
Sem o Fabric Loader, o Minecraft não sabe que este projeto existe.

A Fabric API é um conjunto de classes prontas usado pelos modders. Ela fornece
exemplos e funções para tarefas como:

- Criar um item.
- Registrar o item.
- Criar um comando.
- Detectar um evento.
- Colocar itens em categorias do inventário criativo.

O nosso projeto usa Fabric 1.21.4. Uma versão não compatível pode causar um
erro na inicialização.

### 2. O que é uma pasta e um arquivo

Uma pasta organiza outros arquivos. Use o Explorador de Arquivos para abrir:

```text
C:\Users\Meu computador\Repositorios\mods\mdm\minecraft-mod
```

`src` significa source, ou código-fonte.

`main` significa que estes arquivos serão usados na versão principal do mod.

Dentro de `src/main`, existem duas categorias:

- `java`: código escrito em Java.
- `resources`: arquivos de texto usados pelo Minecraft e pelo Fabric.

Não renomeie estas pastas sem atualizar também o código e a configuração.

### 3. O que é um arquivo Java

Um arquivo Java começa, normalmente, por um pacote:

```java
package com.example.meninomod;
```

Um pacote organiza arquivos semelhantes. Imagine um pacote como o endereço
completo de uma sala em um prédio.

`com.example.meninomod.MeninoMod` significa:

```text
com
└── example
    └── meninomod
        └── MeninoMod
```

Consequentemente, o arquivo precisa estar em:

```text
src/main/java/com/example/meninomod/MeninoMod.java
```

O nome da pasta não precisa ser igual em todos os projetos, mas o caminho
deve corresponder ao pacote declarado no arquivo.

### 4. O que são imports

No início de `MeninoMod.java`, existem linhas como:

```java
import net.minecraft.item.Item;
```

`import` informa ao Java que você quer usar uma classe existente em outra
parte do projeto.

Uma classe é um modelo usado para criar objetos. Pense assim:

- `Item` é o modelo.
- `AMULETO_DO_MENINO` é o objeto criado com esse modelo.

Você não precisa escrever o sistema de itens do Minecraft. Ele já existe no
Minecraft, e a Fabric API permite que o seu código use essas classes.

Erros como `cannot find symbol` frequentemente significam que o import está
ausente, escrito incorretamente ou disponível apenas em outra versão do
Minecraft.

### 5. O que é uma classe

Esta é a declaração da classe principal:

```java
public class MeninoMod implements ModInitializer
```

Ela pode ser separada em partes:

- `public`: o Java permite que outras classes enxerguem esta classe.
- `class`: declara que isso é uma classe.
- `MeninoMod`: é o nome escolhido pelo projeto.
- `implements ModInitializer`: informa que esta classe segue o contrato exigido pelo Fabric.
- `ModInitializer`: é uma interface que exige o método `onInitialize`.

Uma interface funciona como uma lista de obrigações. Ao dizer
`implements ModInitializer`, a classe assume que implementará o método exigido
por essa interface.

### 6. O que é um objeto

O item é criado assim:

```java
public static final Item AMULETO_DO_MENINO =
        new Item(new Item.Settings());
```

As palavras podem ser entendidas assim:

- `public`: outras classes podem acessar o item.
- `static`: o item pertence à classe e existe sem criar `new MeninoMod()`.
- `final`: o conteúdo da variável não será substituído por outro item.
- `Item`: o tipo da variável.
- `AMULETO_DO_MENINO`: o nome usado no nosso código.
- `new`: cria um novo objeto.
- `Item.Settings()`: configurações iniciais do objeto.

Não confunda o nome da variável com o nome exibido no jogo. A variável
`AMULETO_DO_MENINO` é usada apenas pelo Java. O nome exibido pelo Minecraft
vem do arquivo de idioma.

### 7. Como o item é registrado

Criar um objeto não é suficiente. É preciso registrá-lo no jogo:

```java
Registry.register(
        Registries.ITEM,
        Identifier.of(MOD_ID, "amuleto_do_menino"),
        AMULETO_DO_MENINO
);
```

O registro acontece em três etapas:

1. `Registries.ITEM` informa que o elemento registrado é um item.
2. `Identifier.of(...)` cria o endereço completo do item.
3. `AMULETO_DO_MENINO` informa qual objeto será associado ao endereço.

O endereço completo é formado por:

```text
meninomod:amuleto_do_menino
```

`meninomod` é o namespace e `amuleto_do_menino` é o nome do item.

O namespace evita que dois mods usem, acidentalmente, o mesmo nome.

### 8. Como o item entra no criativo

Esta parte modifica uma categoria existente:

```java
ItemGroupEvents.modifyEntriesEvent(ItemGroups.TOOLS)
        .register(entries -> entries.add(AMULETO_DO_MENINO));
```

O processo é:

1. `ItemGroups.TOOLS` seleciona a categoria **Tools**.
2. `modifyEntriesEvent` cria um evento que permite modificar essa categoria.
3. `.register(...)` informa o que deve acontecer.
4. `entries ->` significa que o código recebe uma lista chamada `entries`.
5. `entries.add(...)` adiciona o nosso item à lista.

`entries ->` é uma lambda. Ela é uma forma curta de escrever uma função sem
declarar um método separado.

### 9. Como o comando `/oi` é criado

O registro de comandos acontece em `onInitialize()`:

```java
CommandRegistrationCallback.EVENT.register(
        (dispatcher, registryAccess, environment) -> {
```

Essa lambda recebe três informações:

- `dispatcher`: responsável por registrar comandos.
- `registryAccess`: permite acessar registros do servidor.
- `environment`: informa em qual ambiente o código está rodando.

Neste mod, apenas `dispatcher` é usado.

Depois:

```java
dispatcher.register(
        CommandManager.literal("oi")
);
```

`literal("oi")` cria um comando cujo texto inicial deve ser exatamente `oi`.
Por isso, o jogador usa `/oi`.

### 10. O que acontece ao executar o comando

```java
.executes(context -> {
    context.getSource().sendFeedback(
            () -> Text.literal("Olá! Este é o Menino Mod."),
            false
    );
    return 1;
})
```

Cada parte tem uma função:

- `.executes(...)`: executa o comando.
- `context`: contém informações sobre a execução.
- `context.getSource()`: obtém quem executou o comando.
- `sendFeedback(...)`: envia uma mensagem.
- `Text.literal(...)`: cria uma mensagem de texto.
- `false`: mantém o feedback restrito ao jogador ou ao contexto adequado.
- `return 1`: declara que o comando foi executado com sucesso.

A expressão dentro de `sendFeedback`:

```java
() -> Text.literal("Olá! Este é o Menino Mod.")
```

é outra lambda. Ela cria o texto somente no momento em que o Minecraft
precisar enviá-lo.

### 11. O ciclo de vida do mod

Ao iniciar o jogo com o Fabric, a sequência é semelhante a esta:

1. O Fabric Loader procura arquivos `.jar` na pasta `mods`.
2. Ele lê `fabric.mod.json`.
3. Ele encontra a classe informada pelo campo `main`.
4. Ele cria a classe `MeninoMod`.
5. Ele executa `onInitialize()`.
6. O item é registrado.
7. O item é colocado no inventário criativo.
8. O comando `/oi` é registrado.
9. O mundo pode ser criado ou carregado.

O método `onInitialize()` funciona como o primeiro passo do nosso mod.

### 12. Entenda `fabric.mod.json`

Este arquivo não contém lógica Java. Ele é uma ficha técnica do mod:

```json
{
  "id": "meninomod",
  "version": "${version}",
  "name": "Menino Mod"
}
```

`id` é o identificador usado pelo mod. Ele deve ser único e normalmente usa
somente letras minúsculas.

`version` recebe uma versão definida durante a compilação. O texto `${version}`
é substituído pelo Gradle.

`name` é o nome que pode ser mostrado em interfaces e configurações.

A dependência:

```json
"depends": {
  "fabricloader": ">=0.16.9",
  "fabric-api": "*",
  "minecraft": "~1.21.4",
  "java": ">=21"
}
```

informa quais componentes são necessários:

- Fabric Loader na versão mínima definida.
- Alguma versão compatível da Fabric API.
- Minecraft 1.21.4 compatível.
- Java 21 ou uma versão compatível mais recente.

### 13. Entenda as traduções

O Minecraft usa uma chave para encontrar o nome do item:

```text
item.meninomod.amuleto_do_menino
```

Ela começa com `item`, pois é um nome de item.

Depois vem `meninomod`, o identificador do mod.

Finalmente vem `amuleto_do_menino`, o nome interno do item.

No arquivo de português:

```json
"item.meninomod.amuleto_do_menino": "Amuleto do Menino"
```

a primeira parte é a chave e a segunda parte é o texto mostrado.

Não é necessário escrever acentos no nome interno em inglês ou Java. Somente
o texto exibido ao jogador usa a escrita correta do idioma.

### 14. Entenda o Gradle

O Gradle é uma ferramenta que automatiza tarefas.

Sem o Gradle, uma pessoa teria que baixar manualmente o Java, Fabric, Yarn,
Minecraft e todas as dependências. Depois, teria que passar manualmente cada
caminho para o compilador.

O Gradle faz isso automaticamente conforme os arquivos:

- `settings.gradle`
- `build.gradle`
- `gradle.properties`

`gradle.properties` guarda números que podem ser reutilizados:

```properties
minecraft_version=1.21.4
loader_version=0.16.9
fabric_version=0.114.0+1.21.4
```

`build.gradle` usa esses valores em configurações como:

```gradle
minecraft "com.mojang:minecraft:${project.minecraft_version}"
```

Isso significa "baixe o Minecraft na versão indicada em
`gradle.properties`".

O arquivo `build.gradle` também informa ao compilador Java:

```gradle
it.options.release = 21
```

Isso indica que o código foi feito para Java 21.

### 15. Entenda a pasta `build`

Antes da compilação, o projeto está principalmente em `src`.

Depois da compilação, o Gradle cria arquivos derivados na pasta `build`.

Normalmente existem:

```text
build/
├── classes/
├── libs/
└── resources/
```

`classes` contém bytecode Java traduzido.

`resources` contém `fabric.mod.json` e os arquivos de idioma.

`libs` contém o `.jar` final que pode ser colocado na pasta `mods`.

Não edite manualmente arquivos dentro de `build`. Eles são gerados
automaticamente e podem ser apagados durante uma nova compilação.

### 16. Instalação completa no Windows

Primeiro, instale um JDK 21. Uma distribuição comum é:

```text
Eclipse Temurin JDK 21
```

Durante a instalação, marque a opção de adicionar o Java ao `PATH`, se ela
estiver disponível.

Depois, abra o PowerShell e execute:

```powershell
java -version
javac -version
```

Ambos devem mostrar uma versão relacionada ao Java 21.

Se `javac` não existir, o JDK não está configurado corretamente. Reinstalar
somente o Java Runtime não é suficiente.

Depois, abra o terminal na pasta do projeto:

```powershell
cd "C:\Users\Meu computador\Repositorios\mods\mdm\minecraft-mod"
```

Como este projeto ainda não tem `gradlew.bat`, você precisa gerar o wrapper.
Com o Gradle instalado globalmente:

```powershell
gradle wrapper
```

Depois, use o wrapper criado:

```powershell
.\gradlew.bat build
```

O wrapper evita que cada pessoa precise instalar exatamente o mesmo Gradle
globalmente. Ele baixa a versão definida pelo projeto.

Na primeira compilação, o processo pode demorar porque é necessário baixar o
Minecraft, o Fabric, os mapeamentos, o Yarn e outras bibliotecas.

### 17. Erros comuns

#### `javac não é reconhecido`

O JDK não está instalado ou não está no `PATH`.

#### `java não é reconhecido`

O Java não está instalado ou não está no `PATH`.

#### `Unsupported class file major version`

A versão do Java provavelmente está incorreta. Use JDK 21.

#### `Cannot find symbol`

Pode haver:

- Um nome escrito incorretamente.
- Uma importação ausente.
- Uma versão de API incompatível.
- Uma classe que não existe nesta versão do Minecraft.

#### `Could not resolve`

Pode haver:

- Sem conexão com a internet.
- Uma URL ou versão incorreta.
- Fabric Maven temporariamente indisponível.
- Proxy corporativo bloqueando downloads.

#### O jogo não aparece na lista

Verifique:

- O `.jar` está dentro da pasta `mods`.
- A Fabric API está na pasta `mods`.
- O Fabric Loader é para Minecraft 1.21.4.
- Você iniciou o perfil Fabric correto.
- A versão do Java é compatível.

#### O item não aparece

Verifique:

- Não houve erros no arquivo Java.
- O mod iniciou sem mensagens de erro.
- Você abriu o grupo **Ferramentas** no inventário criativo.
- `fabric.mod.json` aponta para `MeninoMod`.

#### `/oi` não aparece

Verifique:

- Você está em um mundo com permissões de comando.
- O servidor iniciou o mod.
- Não houve erro durante o registro do comando.
- Você digitou exatamente `/oi`.

### 18. Como alterar o projeto passo a passo

#### Mudar o nome mostrado no jogo

Altere em `pt_br.json`:

```json
"item.meninomod.amuleto_do_menino": "Meu Novo Nome"
```

Não altere a chave à esquerda.

#### Mudar a mensagem do comando

Altere em `MeninoMod.java`:

```java
Text.literal("Minha nova mensagem")
```

#### Criar outro comando

Copie o bloco de registro e mude:

```java
CommandManager.literal("tchau")
```

Depois, mude a mensagem para algo como:

```java
Text.literal("Até logo!")
```

Faça uma alteração por vez e compile novamente.

#### Mudar o item de categoria

Troque:

```java
ItemGroups.TOOLS
```

por outra categoria disponível na versão usada, por exemplo uma categoria
válida desta versão.

Após alterar o Java, recompilar não significa que o jogo vai atualizar
automaticamente. O Minecraft precisa ser fechado e iniciado novamente com o
novo arquivo `.jar`.

### 19. Exercícios sugeridos

Comece com alterações simples:

1. Troque o nome mostrado para `Colar da Alegria`.
2. Troque a mensagem de `/oi`.
3. Mude o identificador do comando para `ola`.
4. Crie o comando `/tchau`.
5. Adicione uma segunda mensagem ao comando `/oi`.
6. Mude o item para aparecer na aba correta do criativo.

Não tente todas as alterações ao mesmo tempo. Teste uma alteração, compile,
copie o novo `.jar`, reinicie o Minecraft e confirme o resultado.

### 20. Vocabulário essencial

- **Classe:** modelo usado para representar um conceito.
- **Objeto:** elemento criado a partir de uma classe.
- **Método:** ação ou função pertencente a uma classe.
- **Variável:** nome associado a um valor ou objeto.
- **Pacote:** organização hierárquica de classes.
- **Import:** instrução que permite usar outra classe.
- **Registro:** processo de informar ao Minecraft que um conteúdo existe.
- **Identificador:** endereço único de um conteúdo.
- **Namespace:** parte inicial do identificador que pertence ao mod.
- **Evento:** ocorrência que permite executar código em um momento específico.
- **Lambda:** forma curta de escrever uma função.
- **Compilação:** processo que transforma Java em bytecode.
- **Dependência:** biblioteca ou projeto necessário ao código.
- **Jar:** arquivo compactado usado para distribuir classes e recursos.
- **Wrapper:** programa que usa uma versão definida do Gradle.
- **Fabric Loader:** responsável por carregar o mod no Minecraft.
- **Fabric API:** biblioteca que facilita a criação de recursos.
- **JDK:** ferramentas Java usadas por desenvolvedores.
- **Bytecode:** instrução intermediária produzida pelo compilador Java.