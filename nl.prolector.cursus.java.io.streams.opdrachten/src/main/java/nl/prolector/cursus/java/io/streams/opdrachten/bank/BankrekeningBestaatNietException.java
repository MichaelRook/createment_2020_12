package nl.prolector.cursus.java.io.streams.opdrachten.bank;

public class BankrekeningBestaatNietException extends Exception {

   public BankrekeningBestaatNietException(int rekeningNr) {
      super("** Rekening met nummer " + rekeningNr + " bestaat niet.");
   }
}
