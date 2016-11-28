import java.io.BufferedWriter;
import java.io.IOException;


public class MejorMano3 {
    private int apartado;
    private String combination;
    private String bestHand;
    boolean tisdraw1, tisdraw2;
    private String draw1;
    private String draw2;
    private int rank;
    private char desempate;
    private char desempate2;
    private char[] cartasBoardOrdenadas;

    public char[] getCartasBoardOrdenadas() {
        return cartasBoardOrdenadas;
    }

    public int getDesempate(){
        return (valorCarta(desempate));
    }

    public int getDesempate2(){
        return (valorCarta(desempate2));
    }

    public void setApartado(int apartado){
        this.apartado = apartado;
    }

    public String getCombination(){
        return combination;
    }

    public int getRank(){
        return rank;
    }

    public String getBestHand(){
        return bestHand;
    }

    public void setBestHand(javax.swing.JLabel lblBestHand, BufferedWriter file_out)
            throws IOException
    {
        lblBestHand.setText(bestHand);

        if (apartado == 1)
            file_out.write(" - Best hand: " + bestHand + " (" + combination + ")");
        else if (apartado == 2)
            file_out.write(" - Best hand: " + bestHand + " with " + combination);
        else
            file_out.write(" - Best hand: " + bestHand);

        file_out.newLine();
    }

    public void setDraws(javax.swing.JLabel lblDraw1, javax.swing.JLabel lblDraw2, BufferedWriter file_out)
            throws IOException
    {
        if (tisdraw1) {
            lblDraw1.setText(draw1);
            file_out.write(" - Draw: ");
            file_out.write(draw1);
            file_out.newLine();
        } else
            lblDraw1.setText("");

        if (tisdraw2) {
            lblDraw2.setText(draw2);
            file_out.write(" - Draw: ");
            file_out.write(draw2);
            file_out.newLine();
        } else
            lblDraw2.setText("");
    }

    private static int valorCarta(char letra) {
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

    public void mejorMano3(String cartas) {
        this.combination = new String();
        desempate = 0;
        desempate2 = 0;
        char[] x = new char[cartas.length()];
        x = cartas.toCharArray();
        char aux, aux2;

        boolean inter=true;

        for(int j=0; j < x.length-1 && inter; j+=2) {
            inter=false;

            for(int i=2;i<x.length-j;i=i+2) {
                if(valorCarta(x[i-2])<valorCarta(x[i])) {
                    inter=true;

                    aux=x[i-2];
                    aux2=x[i-1];
                    x[i-2]=x[i];
                    x[i-1]=x[i+1];
                    x[i]=aux;
                    x[i+1]=aux2;
                }
            }
        }
        this.cartasBoardOrdenadas = x;
        //En 7 cartas puede haber hasta 3 parejas(temporal)
        int contadorHuecoEscalera=0;
        int contadorEscalera=0;
        int contadorDobleParPoker = 1;
        int contadorTrio = 2;
        int contadorPar = 3;
        boolean escaleraRealizada = false;
        boolean escaleraOpenEnded = false;
        int contadorEscaleraMax = 0;
        int contadorIguales1=0;
        char iguales1 = 'N';
        int contadorIguales2=0;
        char iguales2 = 'N';
        int contadorIguales3=0;
        char iguales3 = 'N';
        int contadorColor1=0;
        char color1 = 'h';
        int contadorColor2=0;
        char color2 = 'd';
        int contadorColor3=0;
        char color3 = 'c';
        int contadorColor4=0;
        char color4 = 's';
        if(x[1] == color1)
            contadorColor1++;
        else if(x[1] == color2)
            contadorColor2++;
        else if(x[1] == color3)
            contadorColor3++;
        else if(x[1] == color4)
            contadorColor4++;
        contadorEscalera++;

        for(int k=2; k < x.length; k=k+2) {
            //COLOR
            if(x[k+1] == color1)
                contadorColor1++;
            else if(x[k+1] == color2)
                contadorColor2++;
            else if(x[k+1] == color3)
                contadorColor3++;
            else if(x[k+1] == color4)
                contadorColor4++;
            //IGUALES
            if(x[k] == x[k - 2])
            {
                if(contadorIguales3 == 0)
                {
                    if(contadorIguales2 == 0)
                    {
                        if((contadorIguales1 != 0) && (x[k] != x[k-((contadorIguales1+1)*2)])) {
                            contadorIguales2++;
                            iguales2=x[k];
                        } else {
                            contadorIguales1++;
                            iguales1=x[k];
                        }
                    }
                    else if(x[k] != x[k-((contadorIguales2+1)*2)]) {
                        contadorIguales3++;
                        iguales3=x[k];
                    }
                    else
                        contadorIguales2++;
                }
                else
                    contadorIguales3++;
            }
            //ESCALERA
            if (valorCarta(x[k-2]) - valorCarta(x[k]) < 3)
            {
                if ((valorCarta(x[k-2]) - valorCarta(x[k])) == 1)
                    contadorEscalera++;
                else if(valorCarta(x[k-2]) - valorCarta(x[k]) == 0)
                { /* no hace nada */ }
                else {
                    contadorHuecoEscalera++;
                    //si tiene dos huecos ya no es ni proyecto
                    if(contadorHuecoEscalera > 0) {
                        if(contadorEscaleraMax < contadorEscalera)
                            contadorEscaleraMax = contadorEscalera;
                        else
                            contadorEscalera = 1;
                    }
                    else
                        contadorEscalera++;
                }

                if( (contadorEscalera == 5 && contadorHuecoEscalera == 0))
                    escaleraRealizada = true;
                if(contadorEscalera == 4 && contadorHuecoEscalera == 0)
                    escaleraOpenEnded = true;
            }
            else {
                if(contadorEscaleraMax < contadorEscalera)
                    contadorEscaleraMax = contadorEscalera;
                contadorEscalera = 1;
            }
            if(contadorEscaleraMax < contadorEscalera)
                contadorEscaleraMax = contadorEscalera;
            if(contadorEscalera == 1)
                contadorHuecoEscalera = 0;
        }

        // Pequeño parche para arreglar la escalera con el A 2 3 4 5 y sus draws

        if(x[0] == 'A' && x[x.length-2] == '2')
        {
            contadorEscalera++;

            if((contadorEscalera == 5 && contadorHuecoEscalera == 0))
                escaleraRealizada = true;
            if(contadorEscalera == 4 && contadorHuecoEscalera == 0)
                escaleraOpenEnded = true;
        }
        else if(x[0] == 'A' && x[x.length-2] == '3')
        {
            contadorHuecoEscalera++;
            //si tiene dos huecos ya no es ni proyecto
            if(contadorHuecoEscalera > 0) {
                if(contadorEscaleraMax < contadorEscalera)
                    contadorEscaleraMax = contadorEscalera;
                else
                    contadorEscalera = 1;
            }
            else
                contadorEscalera++;
        }
        if(contadorEscaleraMax < contadorEscalera)
            contadorEscaleraMax = contadorEscalera;
        if(contadorEscalera == 1)
            contadorHuecoEscalera = 0;

        //COLOR?
        if(contadorColor1 >= 5 || contadorColor2 >= 5 || contadorColor3 >= 5 || contadorColor4 >= 5)
        {
            char icolor;
            int colorcuant = 0;
            boolean escaleraDeColor = false;

            if(escaleraRealizada)
            {
                if(contadorColor1 >= 5){
                    colorcuant = contadorColor1;
                    icolor = color1;
                }else if (contadorColor2 >= 5){
                    colorcuant = contadorColor2;
                    icolor = color2;
                }else if (contadorColor3 >= 5){
                    colorcuant = contadorColor3;
                    icolor = color3;
                }else{
                    colorcuant = contadorColor4;
                    icolor = color4;
                }

                char[] escaleracolor = new char[2*colorcuant];
                int j = 1;
                for (int i = 1; i < x.length; i += 2){
                    if (x[i] == icolor){
                        escaleracolor[j-1] =  x[i-1];
                        escaleracolor[j] = x[i];
                        j+=2;
                    }
                }
                for (int k = 2; k < escaleracolor.length; k += 2)
                {
                    if (valorCarta(escaleracolor[k-2]) - valorCarta(escaleracolor[k]) < 3)
                    {
                        if ((valorCarta(escaleracolor[k-2]) - valorCarta(escaleracolor[k])) == 1)
                            contadorEscalera++;
                        else if(valorCarta(escaleracolor[k-2]) - valorCarta(escaleracolor[k]) == 0)
                        { /* no hace nada */ }
                        else {
                            contadorHuecoEscalera++;
                            //si tiene dos huecos ya no es ni proyecto
                            if(contadorHuecoEscalera > 0) {
                                if(contadorEscaleraMax < contadorEscalera)
                                    contadorEscaleraMax = contadorEscalera;
                                else
                                    contadorEscalera = 1;
                            }
                            else
                                contadorEscalera++;
                        }

                        if( (contadorEscalera == 5 && contadorHuecoEscalera == 0))
                            escaleraDeColor = true;
                        if(contadorEscalera == 4 && contadorHuecoEscalera == 0)
                            escaleraOpenEnded = true;
                    }
                    else {
                        if(contadorEscaleraMax < contadorEscalera)
                            contadorEscaleraMax = contadorEscalera;
                        contadorEscalera = 1;
                    }
                    if(contadorEscaleraMax < contadorEscalera)
                        contadorEscaleraMax = contadorEscalera;
                    if(contadorEscalera == 1)
                        contadorHuecoEscalera = 0;
                }

                // Pequeño parche para arreglar la escalera con el A 2 3 4 5 y sus draws

                if(escaleracolor[0] == 'A' && escaleracolor[escaleracolor.length-2] == '2')
                {
                    contadorEscalera++;

                    if((contadorEscalera == 5 && contadorHuecoEscalera == 0))
                        escaleraDeColor = true;
                    if(contadorEscalera == 4 && contadorHuecoEscalera == 0)
                        escaleraOpenEnded = true;
                }
                else if(escaleracolor[0] == 'A' && escaleracolor[escaleracolor.length-2] == '3')
                {
                    contadorHuecoEscalera++;
                    //si tiene dos huecos ya no es ni proyecto
                    if(contadorHuecoEscalera > 0) {
                        if(contadorEscaleraMax < contadorEscalera)
                            contadorEscaleraMax = contadorEscalera;
                        else
                            contadorEscalera = 1;
                    }
                    else
                        contadorEscalera++;
                }
                if(contadorEscaleraMax < contadorEscalera)
                    contadorEscaleraMax = contadorEscalera;
                if(contadorEscalera == 1)
                    contadorHuecoEscalera = 0;

            }
            //ESCALERA DE COLOR?
            if(escaleraRealizada && escaleraDeColor){

                bestHand = "Straight Flush";
                rank = 9;
                colorcuant = 0;

                if(contadorColor1 >= 5){
                    colorcuant = contadorColor1;
                    icolor = color1;
                }else if (contadorColor2 >= 5){
                    colorcuant = contadorColor2;
                    icolor = color2;
                }else if (contadorColor3 >= 5){
                    colorcuant = contadorColor3;
                    icolor = color3;
                }else{
                    colorcuant = contadorColor4;
                    icolor = color4;
                }
                char[] escaleracolor = new char[2*colorcuant];

                if(colorcuant == 5){
                    for (int i = 1; i < x.length; i += 2){
                        if (x[i] == icolor){
                            combination += x[i-1];
                            combination += x[i];
                        }
                    }
                }else{
                    int j = 1;
                    for (int i = 1; i < x.length; i += 2){
                        if (x[i] == icolor){
                            escaleracolor[j-1] =  x[i-1];
                            escaleracolor[j] = x[i];
                            j+=2;
                        }
                    }
                    for (int i = 0; i < escaleracolor.length-2; i += 2){
                        if ((valorCarta(escaleracolor[i]) - valorCarta(escaleracolor[i+2]) == 1) && (combination.length() < 10)){
                            combination += escaleracolor[i];
                            combination += escaleracolor[i+1];
                            if (combination.length() == 6 && escaleracolor[0] == 'A' && combination.charAt(0) == '5' && escaleracolor[escaleracolor.length-2] == '2'){
                                combination += escaleracolor[i+2];
                                combination += escaleracolor[i+3];
                                combination += escaleracolor[0];
                                combination += escaleracolor[1];
                            }else
                            if(combination.length() == 8){
                                combination += escaleracolor[i+2];
                                combination += escaleracolor[i+3];
                            }
                        }else
                        if ((valorCarta(escaleracolor[i]) - valorCarta(escaleracolor[i+2]) > 1) && (combination.length() < 10)){
                            combination = new String();
                        }
                    }
                }
            }
            else{
                bestHand = "Flush";
                rank = 6;
                if (contadorColor1 >= 5){
                    for (int i = 1; i < x.length; i += 2){
                        if (x[i] == color1){
                            combination += x[i-1];
                            combination += x[i];
                        }
                    }
                }
                else if (contadorColor2 >= 5){
                    for (int i = 1; i < x.length; i += 2){
                        if (x[i] == color2){
                            combination += x[i-1];
                            combination += x[i];
                        }
                    }
                }
                else if (contadorColor3 >= 5){
                    for (int i = 1; i < x.length; i += 2){
                        if (x[i] == color3){
                            combination += x[i-1];
                            combination += x[i];
                        }
                    }
                }
                else if (contadorColor4 >= 5){
                    for (int i = 1; i < x.length; i += 2){
                        if (x[i] == color4){
                            combination += x[i-1];
                            combination += x[i];
                        }
                    }
                }
            }
        }
        else {
            if(escaleraRealizada){
                bestHand = "Straight";
                rank = 5;
                for (int i = 0; i < x.length-2; i += 2){
                    if ((valorCarta(x[i]) - valorCarta(x[i+2]) == 1) && (combination.length() < 10)){
                        combination += x[i];
                        combination += x[i+1];
                        if (combination.length() == 6 && x[0] == 'A' && combination.charAt(0) == '5' && x[x.length - 2] == '2'){
                            combination += x[i+2];
                            combination += x[i+3];
                            combination += x[0];
                            combination += x[1];
                        }else
                        if(combination.length() == 8){
                            combination += x[i+2];
                            combination += x[i+3];
                        }
                    }else
                    if ((valorCarta(x[i]) - valorCarta(x[i+2]) > 1) && (combination.length() < 10)){
                        combination = new String();
                    }
                }
            }
            else
            {
                char mayor;
                if (contadorIguales1 >= contadorIguales2 && contadorIguales1 >= contadorIguales3)
                    mayor = iguales1;
                else {
                    if (contadorIguales2 >= contadorIguales3)
                        mayor = iguales2;
                    else
                        mayor = iguales3;
                }

                if(contadorIguales1 == 3 || contadorIguales2 == 3){
                    bestHand = "Four of a Kind (" + mayor + ")";
                    rank = 8;
                    desempate = mayor;
                    for (int i = 0; i < x.length; i += 2){
                        if (x[i] != mayor && apartado > 1 && contadorDobleParPoker != 0){
                            combination += x[i];
                            combination += x[i+1];
                            contadorDobleParPoker--;
                        }
                        if (x[i] == mayor){
                            combination += x[i];
                            combination += x[i+1];
                        }
                    }
                }
                else if(contadorIguales1 == 2 || contadorIguales2 == 2 || contadorIguales3 == 2)
                {
                    if(contadorIguales1 >= 1 && iguales1 != mayor || contadorIguales2 >= 1 && iguales2 != mayor|| contadorIguales3 >= 1 && iguales3 != mayor)
                    {
                        char medio;
                        if(contadorIguales1 >= 1 && iguales1 != mayor)
                            medio = iguales1;
                        else if(contadorIguales2 >= 1 && iguales2 != mayor)
                            medio = iguales2;
                        else
                            medio = iguales3;

                        bestHand = "Full House (" + mayor + " and " + medio + ")";
                        rank = 7;
                        desempate = mayor;
                        desempate2 = medio;
                        for (int i = 0; i < x.length; i += 2){
                            if (x[i] == mayor || x[i] == medio){
                                combination += x[i];
                                combination += x[i+1];
                            }
                        }
                    }
                    else{
                        bestHand = "Three of a Kind (" + mayor + ")";
                        rank = 4;
                        desempate = mayor;
                        for (int i = 0; i < x.length; i += 2){
                            if (x[i] != mayor && apartado > 1 && contadorTrio != 0){
                                combination += x[i];
                                combination += x[i+1];
                                contadorTrio--;
                            }
                            if (x[i] == mayor){
                                combination += x[i];
                                combination += x[i+1];
                            }
                        }
                    }
                }
                else if(contadorIguales1 == 1 || contadorIguales2 == 1 || contadorIguales3 == 1)
                {
                    int contadorDoblePareja = 0;

                    if (contadorIguales1 == 1)
                        contadorDoblePareja++;
                    if (contadorIguales2 == 1)
                        contadorDoblePareja++;
                    if(contadorIguales3 == 1)
                        contadorDoblePareja++;

                    char medio;

                    if(contadorIguales1 == 1 && iguales1 != mayor)
                        medio = iguales1;
                    else if(contadorIguales2 == 1 && iguales2 != mayor)
                        medio = iguales2;
                    else
                        medio = iguales3;

                    if(contadorDoblePareja > 1){
                        bestHand = "Two Pair (" + mayor + " and " + medio + ")";
                        rank = 3;
                        desempate = mayor;
                        desempate2 = medio;
                        for (int i = 0; i < x.length; i += 2){
                            if ((x[i] != mayor && x[i] != medio) && apartado > 1 && contadorDobleParPoker != 0){
                                combination += x[i];
                                combination += x[i+1];
                                contadorDobleParPoker--;
                            }
                            if (x[i] == mayor || x[i] == medio){
                                combination += x[i];
                                combination += x[i+1];
                            }
                        }
                    }
                    else {
                        bestHand = "Pair of " + mayor;
                        rank = 2;
                        desempate = mayor;
                        for (int i = 0; i < x.length; i += 2){
                            if (x[i] != mayor && apartado > 1 && contadorPar != 0){
                                combination += x[i];
                                combination += x[i+1];
                                contadorPar--;
                            }
                            if (x[i] == mayor){
                                combination += x[i];
                                combination += x[i+1];
                            }
                        }
                    }
                } else {
                    bestHand = "High Card " + x[0];
                    rank = 1;
                    for (int i = 0; i < 10; i++){
                        combination += x[i];
                    }
                }
            }
        }

        if(escaleraOpenEnded && !escaleraRealizada){
            draw1 = "Open-ended straight";
            tisdraw1 = true;
        }
        else if(contadorEscaleraMax >= 4 && !escaleraRealizada){
            draw1 = "Straight gutshot";
            tisdraw1 = true;
        } else
            tisdraw1 = false;
        if(contadorColor1 == 4 ||contadorColor2 == 4 || contadorColor3 == 4 || contadorColor4 == 4){
            draw2 = "Flush";
            tisdraw2 = true;
        } else
            tisdraw2 = false;

        if (apartado == 2 && x.length == 14){
            tisdraw1 = false;
            tisdraw2 = false;
        }
    }

}