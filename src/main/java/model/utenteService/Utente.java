package model.utenteService;


import java.util.List;

public class Utente {
  private String nomeUtente;
  private String codiceSicurezza;
  private String email;
  private String tipo;
  private List<String> telefoni;


  public String getCodiceSicurezza() {
    return codiceSicurezza;
  }

  public void setCodiceSicurezza(String codiceSicurezza) {
    this.codiceSicurezza = codiceSicurezza;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getNomeUtente() {
    return nomeUtente;
  }

  public void setNomeUtente(String nomeUtente) {
    this.nomeUtente = nomeUtente;
  }

  public String getTipo() {
    return tipo;
  }

  public void setTipo(String tipo) {
    this.tipo = tipo;
  }

  public List<String> getTelefoni() {
    return telefoni;
  }

  public void setTelefoni(List<String> telefoni) {
    this.telefoni = telefoni;
  }
}
