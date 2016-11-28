import java.util.ArrayList;
import java.lang.StringBuilder;

public class CalcularCombos {

    private ArrayList<String> escaleraColor;
    private int escaleraColorContador;
    private ArrayList<String> poker;
    private int pokerContador;
    private ArrayList<String> fullHouse;
    private int fullHouseContador;
    private ArrayList<String> color;
    private int colorContador;
    private ArrayList<String> escalera;
    private int escaleraContador;
    private ArrayList<String> trio;
    private int trioContador;
    private ArrayList<String> doblePareja;
    private int dobleParejaContador;
    private ArrayList<String> pareja;
    private int parejaContador;
    private ArrayList<String> cartaAlta;
    private int cartaAltaContador;
    private ArrayList<String> noMadeHand;
    private int noMadeHandContador;

    public ArrayList<String> getEscaleraColor() {
        return escaleraColor;
    }

    public int getEscaleraColorContador() {
        return escaleraColorContador;
    }

    public ArrayList<String> getPoker() {
        return poker;
    }

    public int getPokerContador() {
        return pokerContador;
    }

    public ArrayList<String> getFullHouse() {
        return fullHouse;
    }

    public int getFullHouseContador() {
        return fullHouseContador;
    }

    public ArrayList<String> getColor() {
        return color;
    }

    public int getColorContador() {
        return colorContador;
    }

    public ArrayList<String> getEscalera() {
        return escalera;
    }

    public int getEscaleraContador() {
        return escaleraContador;
    }

    public ArrayList<String> getTrio() {
        return trio;
    }

    public int getTrioContador() {
        return trioContador;
    }

    public ArrayList<String> getDoblePareja() {
        return doblePareja;
    }

    public int getDobleParejaContador() {
        return dobleParejaContador;
    }

    public ArrayList<String> getPareja() {
        return pareja;
    }

    public int getParejaContador() {
        return parejaContador;
    }

    public ArrayList<String> getCartaAlta() {
        return cartaAlta;
    }

    public int getCartaAltaContador() {
        return cartaAltaContador;
    }

    public ArrayList<String> getNoMadeHand() {
        return noMadeHand;
    }

    public int getNoMadeHandContador() {
        return noMadeHandContador;
    }

    public void calcular(ArrayList<String> conjuntoCartas, String cartasBoard) {

        noMadeHand = new ArrayList<>();
        cartaAlta = new ArrayList<>();
        pareja = new ArrayList<>();
        doblePareja = new ArrayList<>();
        trio = new ArrayList<>();
        escalera = new ArrayList<>();
        color = new ArrayList<>();
        fullHouse = new ArrayList<>();
        poker = new ArrayList<>();
        escaleraColor = new ArrayList<>();

        noMadeHandContador = 0;
        cartaAltaContador = 0;
        parejaContador = 0;
        dobleParejaContador = 0;
        trioContador = 0;
        escaleraContador = 0;
        colorContador = 0;
        fullHouseContador = 0;
        pokerContador = 0;
        escaleraColorContador = 0;

        String cartasEnMano;

        PokerTrap poker = new PokerTrap();

        for(int i = 0; i < conjuntoCartas.size(); i++)
        {
            cartasEnMano = conjuntoCartas.get(i);
            poker.start(cartasEnMano, cartasBoard);

            // PARA COLOR MAYOR
            if(poker.getBestHandColorMayor().equals("Flush"))
            {
                int contador = poker.getContadorColorMayor();
                this.color.add(poker.getCartasEnManoColorMayor() + " (" + contador + ")");
                this.colorContador += contador;
            }
            else if(poker.getBestHandColorMayor().equals("Straight Flush"))
            {
                int contador = poker.getContadorColorMayor();
                this.escaleraColor.add(poker.getCartasEnManoColorMayor() + " (" + contador + ")");
                this.escaleraColorContador += contador;
            }
            else if(poker.getBestHandColorMayor().equals("No made Hand"))
            {
                int contador = poker.getContadorColorMayor();
                this.noMadeHand.add(poker.getCartasEnManoColorMayor() + " (" + contador + ")");
                this.noMadeHandContador += contador;
            }

            // PARA COLOR MENOR
            if(poker.getBestHandColorMenor().equals("Flush"))
            {
                int contador = poker.getContadorColorMenor();
                this.color.add(poker.getCartasEnManoColorMenor() + " (" + contador + ")");
                this.colorContador += contador;
            }
            else if(poker.getBestHandColorMenor().equals("Straight Flush"))
            {
                int contador = poker.getContadorColorMenor();
                this.escaleraColor.add(poker.getCartasEnManoColorMenor() + " (" + contador + ")");
                this.escaleraColorContador += contador;
            }
            else if(poker.getBestHandColorMenor().equals("No made Hand"))
            {
                int contador = poker.getContadorColorMenor();
                this.noMadeHand.add(poker.getCartasEnManoColorMenor() + " (" + contador + ")");
                this.noMadeHandContador += contador;
            }

            // PARA SIN COLOR
            String mejorManoSinColor = "";
            mejorManoSinColor += poker.getBestHandSinColor().charAt(0);
            mejorManoSinColor += poker.getBestHandSinColor().charAt(1);

            if(mejorManoSinColor.equals("St"))
            {
                int contador = poker.getContadorSinColor();
                this.escalera.add(poker.getCartasEnManoSinColor() + " (" + contador + ")");
                this.escaleraContador += contador;
            }
            else if(mejorManoSinColor.equals("Fo"))
            {
                int contador = poker.getContadorSinColor();
                this.poker.add(poker.getCartasEnManoSinColor() + " (" + contador + ")");
                this.pokerContador += contador;
            }
            else if(mejorManoSinColor.equals("Fu"))
            {
                int contador = poker.getContadorSinColor();
                this.fullHouse.add(poker.getCartasEnManoSinColor() + " (" + contador + ")");
                this.fullHouseContador += contador;
            }
            else if(mejorManoSinColor.equals("Th"))
            {
                int contador = poker.getContadorSinColor();
                this.trio.add(poker.getCartasEnManoSinColor() + " (" + contador + ")");
                this.trioContador += contador;
            }
            else if(mejorManoSinColor.equals("Tw"))
            {
                int contador = poker.getContadorSinColor();
                this.doblePareja.add(poker.getCartasEnManoSinColor() + " (" + contador + ")");
                this.dobleParejaContador += contador;
            }
            else if(mejorManoSinColor.equals("Pa"))
            {
                int contador = poker.getContadorSinColor();
                this.pareja.add(poker.getCartasEnManoSinColor() + " (" + contador + ")");
                this.parejaContador += contador;
            }
            else if(mejorManoSinColor.equals("Hi"))
            {
                int contador = poker.getContadorSinColor();
                this.cartaAlta.add(poker.getCartasEnManoSinColor() + " (" + contador + ")");
                this.cartaAltaContador += contador;
            }
            else if(mejorManoSinColor.equals("No"))
            {
                int contador = poker.getContadorSinColor();
                this.noMadeHand.add(poker.getCartasEnManoSinColor() + " (" + contador + ")");
                this.noMadeHandContador += contador;
            }



        }



    }
}
