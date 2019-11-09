import java.util.*;

public class Crypto {
    public static void main(String[]args){
        Scanner input = new Scanner(System.in);
        System.out.println("Digite a frase que deseja codificar: ");
        String text = input.nextLine();
        System.out.println();
        System.out.print("Quantas posicoes do alfabeto voce deseja deslocar cada letra de sua frase? ");
        int shiftValue = input.nextInt();
        System.out.print("Em blocos de quantas letras voce deseja agrupar o texto codificado? ");
        int lettersPerGroup = input.nextInt();

        String encryptText = encryptString(text, shiftValue, lettersPerGroup);
        /* codifica o texto passado pelo usuário
        valores passados para o método 'encryptString', serão:
        1) texto do usuário;
        2) valor a ser deslocado por cada letra;
        3) quantidade de letras por bloco que a String será agrupada. */
        System.out.println("--------------------------------------------------------");
        System.out.println("Aqui esta seu texto codificado: ");
        System.out.println(encryptText);
        System.out.println("--------------------------------------------------------");


        String decryptText = decryptString(encryptText, shiftValue, lettersPerGroup);
        /* decodifica o texto codificado no metodo 'encryptString'
        valores passados para o método 'decryptString', serão:
        1) texto codificado no método 'encryptString';
        2) o valor utilizado para deslocar as letras no método 'encryptString'. */
        System.out.println();
        System.out.println("Aqui esta o texto decodificado: ");
        System.out.println(decryptText);
        System.out.println("--------------------------------------------------------");

    }

    public static String encryptString(String inText, int shiftValue, int lettersPerGroup) {
        inText = normalizeText(inText);
        inText = obify(inText);
        inText = caesarify(inText, shiftValue);
        inText = groupify(inText, lettersPerGroup);
        return inText;
    }

    public static String decryptString(String inText, int shiftValue, int lettersPerGroup) {
        inText = ungroupify(inText);
        inText = uncaesarify(inText, shiftValue);
        inText = unobify(inText);
        return inText;
    }

    public static String ungroupify(String inText) {
        return inText.replaceAll("[^A-Z]", "");
    }

    public static String normalizeText(String inText) {
        return inText.toUpperCase().replaceAll("[^A-Z]", ""); // elimina todo caracter que não estiver no intervalo A - Z (inclusive)
    }

    public static String obify(String inText) {
        return inText.replaceAll("([AEIOUY])", "OB$1"); // coloca o prefixo "OB" em toda vogal e na letra 'Y'
    }

    public static String unobify(String inText) {
        return inText.replaceAll("(OB)([AEIOUY])", "$2"); // retira o prefixo "OB" de toda vogal e da letra 'Y'
    }

    public static String caesarify(String inText, int shift){ // desloca cada letra da String em 'shift' posições
        char[] charArray = inText.toCharArray(); // passa String crua para um array tipo 'char'
        int temp = 0;
        for (int i = 0; i < charArray.length; i++) {
                temp = (int) charArray[i] + shift; // passa o codigo ascii com shift para uma var 'temp'
                while (temp > 90 || temp < 65) { //ajusta a variavel temp
                    if (temp > 90) {
                        temp = temp - 26; // ajusta temp quando resultado for maior que a letra 'Z'
                    } else if (temp < 65) {
                        temp = temp + 26; // ajusta temp quando resultado for menor que a letra 'A'
                    }
                }
                charArray[i] = (char) temp; // cria array com caracteres já 'shiftados'
        }
        String result = new String(charArray); // passa resultado do array 'shiftado' para uma String
        return result;
    }

    public static String groupify (String inText, int lettersPerGroup) { // agrupa String em blocos de 'lettersPerGroup'
        if (lettersPerGroup == 0) { // retorna a própria String se 'lettersPerGroup' for zero
            return inText;
        } else {

            //Dimensionando o novo array "charArrayModified"
            char[] charArray = inText.toCharArray(); // transforma String em array de caracteres
            int newLength = 0;
            if (charArray.length <= lettersPerGroup) {
                newLength = lettersPerGroup;
            } else if (charArray.length % lettersPerGroup != 0) {
                newLength = lettersPerGroup + (charArray.length / lettersPerGroup) * (lettersPerGroup + 1);
            } else {
                newLength = lettersPerGroup + (charArray.length / lettersPerGroup - 1) * (lettersPerGroup + 1);
            }
            char[] charArrayModified = new char[newLength];

            // agrupa String em blocos de 'lettersPerGroup'
            int count = 0;
            int space = 0;
            for (int i = 0; i < charArrayModified.length; i++) {
                if (space == lettersPerGroup) {
                    charArrayModified[i] = ' ';
                    space = 0;
                } else if (count >= charArray.length) {
                    charArrayModified[i] = 'x';
                } else {
                    charArrayModified[i] = charArray[count];
                    space++;
                    count++;
                }
            }
            String result = new String(charArrayModified); // transforma array de caracteres em String
            return result;
        }
    }

    public static String uncaesarify(String inText, int shift){ // coloca cada letra da String na posição correta
        char[] charArray = inText.toCharArray(); // passa String crua para um array tipo 'char'
        int temp = 0;
        for (int i = 0; i < charArray.length; i++) {
            temp = (int) charArray[i] - shift; // passa o codigo ascii com shift para uma var 'temp'
            while (temp > 90  || temp < 65) { //ajusta a variavel temp
                if (temp > 90) {
                    temp = temp - 26; // ajusta temp quando resultado for maior que a letra 'Z'
                } else if (temp < 65) {
                    temp = temp + 26; // ajusta temp quando resultado for menor que a letra 'A'
                }
            }
            charArray[i] = (char) temp; // cria array com caracteres já corrigidos
        }
        String result = new String(charArray); // passa resultado do array corrigido para uma String
        return result;
    }

}
