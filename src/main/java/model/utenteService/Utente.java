package model.utenteService;


import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
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

  public void setCodiceSicurezza(String codiceSicurezza) {// password è inserita dall’utente
    //this.codiceSicurezza=codiceSicurezza;
     try {
        MessageDigest digest =
                MessageDigest.getInstance("SHA-1");
        digest.reset();
        digest.update(codiceSicurezza.getBytes(StandardCharsets.UTF_8));
        this.codiceSicurezza = String.format("%040x", new
                BigInteger(1, digest.digest()));
      } catch (NoSuchAlgorithmException e) {
        throw new RuntimeException(e);

      }
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
