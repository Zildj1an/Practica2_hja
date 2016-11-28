import java.util.ArrayList;

public class CalcularCombos {

    public void calcular(ArrayList<String> conjuntoCartas, String cartasBoard) {

        String cartasEnMano;

        PokerTrap poker = new PokerTrap();

        for(int i = 0; i < conjuntoCartas.size(); i++)
        {
            cartasEnMano = conjuntoCartas.get(i);
            poker.start(cartasEnMano, cartasBoard);
        }



    }
}
