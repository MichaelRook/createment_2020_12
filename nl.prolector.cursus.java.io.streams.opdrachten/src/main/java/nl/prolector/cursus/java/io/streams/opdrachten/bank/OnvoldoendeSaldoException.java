package nl.prolector.cursus.java.io.streams.opdrachten.bank;

import nl.prolector.cursus.java.io.streams.opdrachten.bank.entity.Bankrekening;

public class OnvoldoendeSaldoException extends Exception {

   public OnvoldoendeSaldoException(Bankrekening b) {
      super(String.format("** Rekening met nummer %d van houder '%s' "
            + "had te weinig saldo (namelijk %.2f euro)",
            b.getRekeningnummer(), b.getHouder(), b.getSaldo()));
   }

   public OnvoldoendeSaldoException(Bankrekening b, Throwable t) {
      super(String.format("** Rekening met nummer %d van houder '%s' "
            + "had te weinig saldo (namelijk %.2f euro)",
            b.getRekeningnummer(), b.getHouder(), b.getSaldo()), t);
   }
}
