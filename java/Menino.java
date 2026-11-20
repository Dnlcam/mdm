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
