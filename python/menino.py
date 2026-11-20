class Menino:
    def __init__(self, nome, sorriso):
        self.nome = nome
        self.sorriso = sorriso

    def apresentar(self):
        print(f"{self.nome} sorri {self.sorriso}.")


menino = Menino("Lucas", "gentilmente")
menino.apresentar()
