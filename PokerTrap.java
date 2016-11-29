import java.util.ArrayList;
import java.util.List;

public class PokerTrap {

    private char marca;
    private String bestHandSinColor;
    private String bestHandColorMayor;
    private String bestHandColorMenor;
    private String cartasEnManoSinColor;
    private String cartasEnManoColorMayor;
    private String cartasEnManoColorMenor;
    private int contadorSinColor;
    private int contadorColorMayor;
    private int contadorColorMenor;
    private String cartasInvolucradasSinColor;
    private char[] cartasBoardOrdenadas;

    public char[] getCartasOrdenadasBoard()
    {
        return cartasBoardOrdenadas;
    }

    public String getCartasInvolucradasSinColor() {
        return cartasInvolucradasSinColor;
    }

    public String getBestHandSinColor() {
        return bestHandSinColor;
    }

    public String getBestHandColorMayor() {
        return bestHandColorMayor;
    }

    public String getBestHandColorMenor() {
        return bestHandColorMenor;
    }

    public String getCartasEnManoSinColor() {

        String cartasMano = "";
        if(cartasEnManoSinColor.length() != 0)
        {
            char[] cartas = cartasEnManoSinColor.toCharArray();
            cartasMano += cartas[0];
            cartasMano += cartas[2];
            if(cartas[0] != cartas[2])
            {
                cartasMano += marca;
            }
        }

        return cartasMano;
    }

    public String getCartasEnManoColorMayor() {
        String cartasMano = "";
        if(cartasEnManoColorMayor.length() != 0)
        {
            char[] cartas = cartasEnManoColorMayor.toCharArray();
            cartasMano += cartas[0];
            cartasMano += cartas[1];
            cartasMano += cartas[2];
        }

        return cartasMano;
    }

    public String getCartasEnManoColorMenor() {
        String cartasMano = "";
        if(cartasEnManoColorMenor.length() != 0)
        {
            char[] cartas = cartasEnManoColorMenor.toCharArray();
            cartasMano += cartas[0];
            cartasMano += cartas[2];
            cartasMano += cartas[3];
        }

        return cartasMano;
    }

    public int getContadorSinColor() {
        return contadorSinColor;
    }

    public int getContadorColorMayor() {
        return contadorColorMayor;
    }

    public int getContadorColorMenor() {
        return contadorColorMenor;
    }

    public void start(String cartasEnMano, String cartasBoard){

        if(cartasEnMano.length() == 3)
            this.marca = cartasEnMano.charAt(2);
        else
            this.marca = ' ';
        this.bestHandSinColor = "";
        this.bestHandColorMayor = "";
        this.bestHandColorMenor = "";
        this.cartasEnManoSinColor = "";
        this.cartasEnManoColorMayor = "";
        this.cartasEnManoColorMenor = "";
        this.contadorSinColor = 0;
        this.contadorColorMayor = 0;
        this.contadorColorMenor = 0;

        String bestHand = "", combination = " ", cartas, bestHand2, combination2, cartasEnMano1 = " ", cartasEnMano2 = " ", cartasEnMano3 = " ";

        boolean colorMenorUsado = false;
        String coloresUsados1 = "";
        String coloresUsados2 = "";
        int cartaManoEnBoard1 = 0;
        int cartaManoEnBoard2 = 0;
        MejorMano3 mejorMano = new MejorMano3();
        //String cartasEnMano = "AKo"; // AA A7s
        String cartasEnManoOperativas = "";
        //String cartasBoard = "8s3s4s8s8d";

        // VEAMOS SI HAY COLOR ANTES DE HACER DEMASIADAS PRUEBAS INEFICIENTES y ESCALERA DE COLOR

        char[] x = cartasBoard.toCharArray();
        char[] y = cartasEnMano.toCharArray();

        int contadorColor1=0;
        char color1 = 'h';
        int contadorColor2=0;
        char color2 = 'd';
        int contadorColor3=0;
        char color3 = 'c';
        int contadorColor4=0;
        char color4 = 's';

        for(int k=0; k < x.length; k=k+2) {
            //COLOR
            if (x[k + 1] == color1)
                contadorColor1++;
            else if (x[k + 1] == color2)
                contadorColor2++;
            else if (x[k + 1] == color3)
                contadorColor3++;
            else if (x[k + 1] == color4)
                contadorColor4++;
        }
        int maxContadorColor = contadorColor1;
        char maxColor = color1;

        if(maxContadorColor < contadorColor2)
        {
            maxContadorColor = contadorColor2;
            maxColor = color2;
        }
        if(maxContadorColor < contadorColor3)
        {
            maxContadorColor = contadorColor3;
            maxColor = color3;
        }
        if(maxContadorColor < contadorColor4)
        {
            maxContadorColor = contadorColor4;
            maxColor = color4;
        }
        boolean contadorColoresEnBoard1 = false;
        boolean contadorColoresEnBoard2 = false;

        boolean colorBoolean = false;
        // si 3 tienen el mismo color y es suit = 5 color

        for(int p = 0; p < cartasBoard.length(); p+=2)
        {
            if(x[p] == y[0])
            {
                cartaManoEnBoard1++;
                coloresUsados1 += x[p];
                coloresUsados1 += x[p+1];
            }
            if(x[p] == y[1])
            {
                cartaManoEnBoard2++;
                coloresUsados2 += x[p];
                coloresUsados2 += x[p+1];
            }
        }

        if(cartasEnMano.length() == 3 && maxContadorColor >= 3 && y[2] == 's')
        {
            colorBoolean = true;
            for(int p = 0; p < cartasBoard.length(); p+=2)
            {
                if(x[p] == y[0])
                {

                    if(cartasBoard.substring(p+1).toCharArray()[0] == maxColor)
                    {
                        colorBoolean = false;
                    }
                }
                if(x[p] == y[1])
                {

                    if(cartasBoard.substring(p+1).toCharArray()[0] == maxColor)
                    {
                        colorBoolean = false;
                    }
                }
            }
            if(!contadorColoresEnBoard1 && !contadorColoresEnBoard2)
            {
                cartasEnManoOperativas += y[0];
                cartasEnManoOperativas += maxColor;
                cartasEnManoOperativas += y[1];
                cartasEnManoOperativas += maxColor;
            }
        }
        else if(maxContadorColor >= 4) //si 4 tienen el mismo color y no es suit
        {
            contadorColoresEnBoard1 = false;
            contadorColoresEnBoard2 = false;
            colorBoolean = true;
            for(int p = 0; p < cartasBoard.length(); p+=2)
            {
                if(x[p] == y[0])
                {
                    //cartaManoEnBoard1++;
                    if(cartasBoard.substring(p+1).toCharArray()[0] == maxColor)
                    {
                        contadorColoresEnBoard1 = true;
                    }
                }
                if(x[p] == y[1])
                {
                    //cartaManoEnBoard2++;
                    if(cartasBoard.substring(p+1).toCharArray()[0] == maxColor)
                    {
                        contadorColoresEnBoard2 = true;
                    }
                }
            }
            if(!contadorColoresEnBoard1 && !contadorColoresEnBoard2)
            {
                cartasEnManoOperativas += y[0];
                cartasEnManoOperativas += maxColor;
                cartasEnManoOperativas += y[1];
                if(maxColor == color1)
                    cartasEnManoOperativas += color2;
                else
                    cartasEnManoOperativas += color1;
            }
            else if(contadorColoresEnBoard1 && !contadorColoresEnBoard2)
            {
                cartasEnManoOperativas += y[0];
                if(maxColor == color1)
                    cartasEnManoOperativas += color2;
                else
                    cartasEnManoOperativas += color1;
                cartasEnManoOperativas += y[1];
                cartasEnManoOperativas += maxColor;
                colorMenorUsado = true;
            }
            if(!contadorColoresEnBoard1 && contadorColoresEnBoard2)
            {
                cartasEnManoOperativas += y[0];
                cartasEnManoOperativas += maxColor;
                cartasEnManoOperativas += y[1];
                if(maxColor == color1)
                    cartasEnManoOperativas += color2;
                else
                    cartasEnManoOperativas += color1;
            }
            if(contadorColoresEnBoard1 && contadorColoresEnBoard2)
                colorBoolean = false;
        }

        if(!colorBoolean) {
            System.out.println("NO HAY COLOR CON CONTADOR >3");
        }
        else
        {
            cartas = cartasEnManoOperativas + cartasBoard;
            mejorMano.mejorMano3(cartas);
            bestHand = mejorMano.getBestHand();
            combination = mejorMano.getCombination();

            System.out.println("COMBINACION CON COLOR MAYOR\n");

            System.out.println(bestHand);
            System.out.println("Cartas involucradas: " + combination);
            System.out.println("Cartas en mano: " + cartasEnManoOperativas + "\n");
            cartasEnMano1 = cartasEnManoOperativas;
        }

        // AQUI TERMINA ESTE TROZO

        // AQUI EMPIEZA LA NORMAL SIN COLOR
        cartasEnManoOperativas = "";

        cartasEnManoOperativas += y[0];
        if(maxColor == color1)
            cartasEnManoOperativas += color2;
        else
            cartasEnManoOperativas += color1;
        cartasEnManoOperativas += y[1];
        if(maxColor == color3)
            cartasEnManoOperativas += color4;
        else
            cartasEnManoOperativas += color3;

        cartas = cartasEnManoOperativas + cartasBoard;
        mejorMano.mejorMano3(cartas);
        bestHand2 = mejorMano.getBestHand();
        combination2 = mejorMano.getCombination();

        System.out.println("COMBINACION SIN COLOR \n");
        System.out.println(bestHand2);
        System.out.println("Cartas involucradas: " + combination2);
        System.out.println("Cartas en mano: " + cartasEnManoOperativas + "\n");
        cartasEnMano2 = cartasEnManoOperativas;
        this.cartasInvolucradasSinColor = combination2;

        // AQUI ACABA LA NORMAL SIN COLOR

        // AQUI EMPIEZA LA POSIBILIDAD DE EL COLOR MALO
        String bestHand3 = "Nada";
        String combination3 = "Nada";
        if(cartasEnMano.length() == 3 && maxContadorColor >= 3 && y[2] == 's' || cartasEnMano.length() == 2)
        {

        }
        else if(colorMenorUsado)
        {

        }
        else if(colorBoolean && !contadorColoresEnBoard2)
        {
            cartasEnManoOperativas = "";

            cartasEnManoOperativas += y[0];
            if(maxColor == color1)
                cartasEnManoOperativas += color2;
            else
                cartasEnManoOperativas += color1;
            cartasEnManoOperativas += y[1];
            cartasEnManoOperativas += maxColor;

            cartas = cartasEnManoOperativas + cartasBoard;
            mejorMano.mejorMano3(cartas);
            bestHand3 = mejorMano.getBestHand();
            combination3 = mejorMano.getCombination();

            System.out.println("COMBINACION CON COLOR MENOR \n");
            System.out.println(bestHand3);
            System.out.println("Cartas involucradas: " + combination3);
            System.out.println("Cartas en mano: " + cartasEnManoOperativas + "\n");
            cartasEnMano3 =  cartasEnManoOperativas;
        }
        else
        {
            System.out.println("NO HAY COLOR CON EL NUMERO MENOR");
        }

        // AQUI TERMINA LA DEL COLOR MENOR

        // AQUI EMPEZAMOS A CONTABILIZAR LAS COMBINACIONES PARA CADA COSA

        int i = 0;
        boolean encontrado = false;
        boolean encontrado2 = false;
        boolean encontrado3 = false;
        char[] combinationChars = combination.toCharArray();
        char[] combinationsChars2 = combination2.toCharArray();
        char[] combinationsChars3 = combination3.toCharArray();

        while(i < combination.length() && !encontrado)
        {
            if(combinationChars[i] == y[0])
            {
                encontrado = true;
            }
            if(combinationChars[i] == y[1])
            {
                encontrado = true;
            }
            i=i+2;
        }
        i = 0;
        while(i < combination2.length() && !encontrado2)
        {
            if(combinationsChars2[i] == y[0])
            {
                encontrado2 = true;
            }
            if(combinationsChars2[i] == y[1])
            {
                encontrado2 = true;
            }
            i=i+2;
        }
        i = 0;
        while(i < combination3.length() && !encontrado3)
        {
            if(combinationsChars3[i] == y[0])
            {
                encontrado3 = true;
            }
            if(combinationsChars3[i] == y[1])
            {
                encontrado3 = true;
            }
            i=i+2;
        }

        if(!encontrado)
            bestHand = "No made Hand";
        if(!encontrado2)
            bestHand2 = "No made Hand";
        if(!encontrado3)
            bestHand3 = "No made Hand";

        this.cartasBoardOrdenadas = mejorMano.getCartasBoardOrdenadas();

        // LO SIENTO YO DE MAÑANA PERO HAY QUE MIRAR QUE TENGAN QUE cartasEnManoOperativas ESTEN EN combination(CARTAS INVOLUCRADAS)
        // POR DESGRACIA TAMBIEN HAY QUE RESTAR COMBOS POR CARTAS EN BOARD
        // PARA ELLO USA EL INT CARTAMANOENBOARD
        if(cartasEnMano.length() == 3 && y[2] == 's')// AKs AQs Q7s
        {
            marca = 's';
            char[] coloresUsados1Char = coloresUsados1.toCharArray();
            char[] coloresUsados2Char = coloresUsados2.toCharArray();

            for(int d = 0; d < coloresUsados1.length(); d=d+2)
            {
                for(int f = 0; f < coloresUsados2.length(); f=f+2)
                {
                    if(coloresUsados1Char[d+1] == coloresUsados2Char[f+1])
                    {
                        cartaManoEnBoard1--;
                    }
                }
            }
            if(colorBoolean)
            {
                System.out.println(cartasEnMano1 + "(" + (1)+ ")");
                this.bestHandColorMayor = bestHand;
                this.cartasEnManoColorMayor = cartasEnMano1;
                this.contadorColorMayor = 1;
                System.out.println(cartasEnMano2 + "(" + (3 - cartaManoEnBoard1 - cartaManoEnBoard2) + ")");
                this.bestHandSinColor = bestHand2;
                this.cartasEnManoSinColor = cartasEnMano2;
                this.contadorSinColor = 3 - cartaManoEnBoard1 - cartaManoEnBoard2;
            }
            else
            {
                System.out.println(cartasEnMano2 + "(" + (4 - cartaManoEnBoard1 - cartaManoEnBoard2) + ")");
                this.bestHandSinColor = bestHand2;
                this.cartasEnManoSinColor = cartasEnMano2;
                this.contadorSinColor = 4 - cartaManoEnBoard1 - cartaManoEnBoard2;
            }
        }
        else if(cartasEnMano.length() == 3 && y[2] == 'o') // AKo AQo AJo ATo...
        {
            boolean coloresUsadosBool1 = false;
            boolean coloresUsadosBool2 = false;
            char[] coloresUsados1Char = coloresUsados1.toCharArray();
            char[] coloresUsados2Char = coloresUsados2.toCharArray();
            char[] posibilidadesColores = {'d', 'h', 's', 'c'};
            String cartaCombo = "";
            int contadorSinColor = 0;
            int contadorColorMayor = 0;
            int contadorColorMenor = 0;
            int d = 0;
            for(int b = 0; b < 4; b++)
            {
                for(int k = 0; k < 4; k++)
                {
                    if(posibilidadesColores[b] == posibilidadesColores[k])
                    {
                        // AL SER SUIT ESTO NO NOS INTERESA
                    }
                    else
                    {
                        coloresUsadosBool1 = false;
                        for(int p = 0; p < coloresUsados1.length(); p=p+2)
                        {
                            if(coloresUsados1Char[p+1] == posibilidadesColores[b])
                            {
                                coloresUsadosBool1 = true;
                            }

                        }
                        coloresUsadosBool2 = false;
                        for(int p = 0; p < coloresUsados2.length(); p=p+2)
                        {
                            if(coloresUsados2Char[p+1] == posibilidadesColores[k])
                            {
                                coloresUsadosBool2 = true;
                            }

                        }

                        /*cartaCombo += y[0];
                        cartaCombo += posibilidadesColores[i];
                        cartaCombo += y[1];
                        cartaCombo += posibilidadesColores[k];*/
                        if(coloresUsadosBool1 || coloresUsadosBool2)
                        {

                        }
                        else
                        {
                            if(posibilidadesColores[b] == maxColor)
                            {
                                if(colorBoolean) {
                                    contadorColorMayor++;
                                }
                                else
                                    contadorSinColor++;
                            }
                            else if(posibilidadesColores[k] == maxColor)
                            {
                                if(colorBoolean && !contadorColoresEnBoard2)
                                {
                                    contadorColorMenor++;
                                }
                                else
                                    contadorSinColor++;
                            }
                            else
                                contadorSinColor++;
                        }

                    }
                }
            }

            marca = 'o';
            System.out.println(bestHand2 + " " + cartasEnMano2 + " " + contadorSinColor);
            this.bestHandSinColor = bestHand2;
            this.contadorSinColor = contadorSinColor;
            this.cartasEnManoSinColor = cartasEnMano2;
            if(contadorColorMayor != 0)
            {
                System.out.println(bestHand +  " " + cartasEnMano1 + " " + contadorColorMayor);
                this.bestHandColorMayor = bestHand;
                this.contadorColorMayor = contadorColorMayor;
                this.cartasEnManoColorMayor = cartasEnMano1;
            }

            if(contadorColorMenor != 0)
            {
                System.out.println(bestHand3 + " " + cartasEnMano3 + " " + contadorColorMenor);
                this.bestHandColorMenor = bestHand3;
                this.contadorColorMenor = contadorColorMenor;
                this.cartasEnManoColorMenor = cartasEnMano3;
            }


            //char[] cartasEnManoOperativasChar = cartasEnManoOperativas.toCharArray();

            // LAS DEL BOARD SE LLAMAN X
            /*int distinto = 0;
            for(int e = 0; e < x.length; e=e+2)
            {
                if(x[e] == cartasEnManoOperativasChar[0] && x[e + 1] == cartasEnManoOperativasChar[3])
                {

                }
                else
                    distinto++;
                if(x[e] == cartasEnManoOperativasChar[2] && x[e + 1] == cartasEnManoOperativasChar[1])
                {

                }
                else
                    distinto++;
            }

            if(colorBoolean)
            {
                if(cartaManoEnBoard2 == 1)
                {
                    System.out.println(cartasEnMano1 + "(" + (3) + ")");
                    if(combination3 != "Nada")
                    {
                        System.out.println(cartasEnMano3 + "(" + (3) + ")");
                        System.out.println(cartasEnMano2 + "(" + (6) + ")");
                    }
                    else
                    {
                        System.out.println(cartasEnMano2 + "(" + (9) + ")");
                    }
                }
                else if(cartaManoEnBoard2 == 2)
                {
                    System.out.println(cartasEnMano1 + "(" + (3) + ")");
                    if(combination3 != "Nada")
                    {
                        System.out.println(cartasEnMano3 + "(" + (3) + ")");
                        System.out.println(cartasEnMano2 + "(" + (6) + ")");
                    }
                    else
                    {
                        System.out.println(cartasEnMano2 + "(" + (9) + ")");
                    }
                }
                else if(cartaManoEnBoard2 == 0)
                {
                    System.out.println(cartasEnMano1 + "(" + (3) + ")");
                    if(combination3 != "Nada")
                    {
                        System.out.println(cartasEnMano3 + "(" + (3) + ")");
                        System.out.println(cartasEnMano2 + "(" + (6) + ")");
                    }
                    else
                    {
                        System.out.println(cartasEnMano2 + "(" + (9) + ")");
                    }
                }
            }
            else
            {
                if((cartaManoEnBoard1 == 0 && cartaManoEnBoard2 == 1) || (cartaManoEnBoard1 == 1 && cartaManoEnBoard2 == 0))
                {
                    System.out.println(cartasEnMano2 + "(" + (9) + ")");
                }
                else if((cartaManoEnBoard1 == 0 && cartaManoEnBoard2 == 2) || (cartaManoEnBoard1 == 2 && cartaManoEnBoard2 == 0))
                {
                    System.out.println(cartasEnMano2 + "(" + (6) + ")");
                }
                else if((cartaManoEnBoard1 == 1 && cartaManoEnBoard2 == 1))
                {
                    System.out.println(cartasEnMano2 + "(" + (12) + ")");
                }
                else if((cartaManoEnBoard1 == 1 && cartaManoEnBoard2 == 1))
                {
                    System.out.println(cartasEnMano2 + "(" + (12) + ")");
                }
                else if(cartaManoEnBoard2 == 0 && cartaManoEnBoard1 == 0)
                {
                    System.out.println(cartasEnMano2 + "(" + (12) + ")");
                }
            }*/
        }
        else // AA KK QQ ...
        {
            if(colorBoolean)
            {
                if(cartaManoEnBoard2 == 1)
                {
                    System.out.println(cartasEnMano1 + "(" + (3 - 1) + ")");
                    this.bestHandColorMayor = bestHand;
                    this.cartasEnManoColorMayor = cartasEnMano1;
                    this.contadorColorMayor = 2;
                    System.out.println(cartasEnMano2 + "(" + (3 - 2) + ")");
                    this.bestHandSinColor = bestHand2;
                    this.cartasEnManoSinColor = cartasEnMano2;
                    this.contadorSinColor = 1;
                }
                else if(cartaManoEnBoard2 == 2)
                {
                    System.out.println(cartasEnMano1 + "(" + (3 - 2) + ")");
                    this.bestHandColorMayor = bestHand;
                    this.cartasEnManoColorMayor = cartasEnMano1;
                    this.contadorColorMayor = 1;
                }
                else if(cartaManoEnBoard2 == 0)
                {
                    System.out.println(cartasEnMano1 + "(" + (3) + ")");
                    this.bestHandColorMayor = bestHand;
                    this.cartasEnManoColorMayor = cartasEnMano1;
                    this.contadorColorMayor = 3;
                    System.out.println(cartasEnMano2 + "(" + (3) + ")");
                    this.bestHandSinColor = bestHand2;
                    this.cartasEnManoSinColor = cartasEnMano2;
                    this.contadorSinColor = 3;
                }
            }
            else
            {
                if(cartaManoEnBoard2 == 1)
                {
                    System.out.println(cartasEnMano2 + "(" + (6 - 3) + ")");
                    this.bestHandSinColor = bestHand2;
                    this.cartasEnManoSinColor = cartasEnMano2;
                    this.contadorSinColor = 3;
                }
                else if(cartaManoEnBoard2 == 2)
                {
                    System.out.println(cartasEnMano2 + "(" + (6 - 5) + ")");
                    this.bestHandSinColor = bestHand2;
                    this.cartasEnManoSinColor = cartasEnMano2;
                    this.contadorSinColor = 1;
                }
                else if(cartaManoEnBoard2 == 0)
                {
                    System.out.println(cartasEnMano2 + "(" + (6) + ")");
                    this.bestHandSinColor = bestHand2;
                    this.cartasEnManoSinColor = cartasEnMano2;
                    this.contadorSinColor = 6;
                }

            }
        }

    }

}
