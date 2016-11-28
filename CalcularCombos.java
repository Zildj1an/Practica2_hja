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
    private ArrayList<String> overPair;
    private int overPairContador;
    private ArrayList<String> topPair;
    private int topPairContador;
    private ArrayList<String> pocketPair;
    private int pocketPairContador;
    private ArrayList<String> middlePair;
    private int middlePairContador;
    private ArrayList<String> weakPair;
    private int weakPairContador;
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

    public ArrayList<String> getOverPair() {
        return overPair;
    }

    public int getOverPairContador() {
        return overPairContador;
    }

    public ArrayList<String> getTopPair() {
        return topPair;
    }

    public int getTopPairContador() {
        return topPairContador;
    }

    public ArrayList<String> getPocketPair() {
        return pocketPair;
    }

    public int getPocketPairContador() {
        return pocketPairContador;
    }

    public ArrayList<String> getMiddlePair() {
        return middlePair;
    }

    public int getMiddlePairContador() {
        return middlePairContador;
    }

    public ArrayList<String> getWeakPair() {
        return weakPair;
    }

    public int getWeakPairContador() {
        return weakPairContador;
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
        overPair = new ArrayList<>();
        topPair = new ArrayList<>();
        pocketPair = new ArrayList<>();
        middlePair = new ArrayList<>();
        weakPair = new ArrayList<>();
        doblePareja = new ArrayList<>();
        trio = new ArrayList<>();
        escalera = new ArrayList<>();
        color = new ArrayList<>();
        fullHouse = new ArrayList<>();
        poker = new ArrayList<>();
        escaleraColor = new ArrayList<>();

        noMadeHandContador = 0;
        cartaAltaContador = 0;
        overPairContador = 0;
        topPairContador = 0;
        pocketPairContador = 0;
        middlePairContador = 0;
        weakPairContador = 0;
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
            else if(mejorManoSinColor.equals("Pa")) // AQUI TODOS LOS PAIRS
            {
                char[] cartasOrdenadasBoard = poker.getCartasOrdenadasBoard();
                String carta1 = cartasEnMano.substring(0,1);
                String carta2 = cartasEnMano.substring(1,2);
                if(carta1.equals(carta2))
                {
                    if(valorCarta(carta1.charAt(0)) > cartasOrdenadasBoard[0])
                    {
                        int contador = poker.getContadorSinColor();
                        this.overPair.add(poker.getCartasEnManoSinColor() + " (" + contador + ")");
                        this.overPairContador += contador;
                    }
                    else
                    {
                        int contador = poker.getContadorSinColor();
                        this.pocketPair.add(poker.getCartasEnManoSinColor() + " (" + contador + ")");
                        this.pocketPairContador += contador;
                    }
                }
                else if(carta1.equals(cartasOrdenadasBoard[0]) || carta2.equals(cartasOrdenadasBoard[0]))
                {
                    int contador = poker.getContadorSinColor();
                    this.topPair.add(poker.getCartasEnManoSinColor() + " (" + contador + ")");
                    this.topPairContador += contador;
                }
                else if(carta1.equals(cartasOrdenadasBoard[2]) || carta2.equals(cartasOrdenadasBoard[2]))
                {
                    int contador = poker.getContadorSinColor();
                    this.middlePair.add(poker.getCartasEnManoSinColor() + " (" + contador + ")");
                    this.middlePairContador += contador;
                }
                else
                {
                    int contador = poker.getContadorSinColor();
                    this.weakPair.add(poker.getCartasEnManoSinColor() + " (" + contador + ")");
                    this.weakPairContador += contador;
                }
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

    private int valorCarta(char letra) {
        int numero;

        switch (letra) {
            case 'T':
                numero = 10;
                break;
            case 'J':
                numero = 11;
                break;
            case 'Q':
                numero = 12;
                break;
            case 'K':
                numero = 13;
                break;
            case 'A':
                numero = 14;
                break;
            default:
                numero = Character.getNumericValue(letra);
                break;
        }

        return numero;
    }

}
