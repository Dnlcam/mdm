# Um menino bonito

O menino tem olhos brilhantes e um sorriso que ilumina o rosto. Seus cabelos
são arrumados, seu jeito é gentil e sua curiosidade faz dele alguém especial.

Quando ele fala, transmite alegria. Quando brinca, espalha imaginação e
descobertas. E, com sua bondade, consegue deixar o dia de todas as pessoas
ao seu redor um pouco mais feliz.

## Exemplo em Java

```java
public class Menino {
    private String nome;
    private String sorriso;

    public Menino(String nome, String sorriso) {
        this.nome = nome;
        this.sorriso = sorriso;
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