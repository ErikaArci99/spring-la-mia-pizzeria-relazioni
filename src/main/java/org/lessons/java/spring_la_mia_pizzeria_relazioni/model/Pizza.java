package org.lessons.java.spring_la_mia_pizzeria_relazioni.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import java.util.List;

@Entity
@Table(name = "pizza")
public class Pizza {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "il campo nome non può essere vuoto")
    private String nome;

    @NotBlank(message = "aggiungere la descrizione della pizza")
    private String descrizione;

    @NotBlank(message = "inserisci url immagine")
    private String foto;

    @Positive(message = "il prezzo non può essere negativo")
    private Double prezzo;

    // relazione con offerta
    @OneToMany(mappedBy = "pizza", cascade = CascadeType.ALL)
    private List<OffertaSpeciale> offerteSpeciali;

    // getter e setter
    public List<OffertaSpeciale> getOfferteSpeciali() {
        return offerteSpeciali;
    }

    public void setOfferteSpeciali(List<OffertaSpeciale> offerteSpeciali) {
        this.offerteSpeciali = offerteSpeciali;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public Double getPrezzo() {
        return prezzo;
    }

    public void setPrezzo(Double prezzo) {
        this.prezzo = prezzo;
    }

    @Override
    public String toString() {
        return "Pizza{" + "id=" + id + ", nome='" + nome + '\'' + ", descrizione='" + descrizione + '\'' + ", foto='"
                + foto + '\'' + ", prezzo=" + prezzo + '}';
    }
}
