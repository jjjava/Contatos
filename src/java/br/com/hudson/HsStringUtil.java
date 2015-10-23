package br.com.hudson;

/**
 *
 * @author hudson.sales
 * @verison 1.0.0
 */
public class HsStringUtil {

    private static final String START = "<b>";
    private static final String END = "</b>";

    public static String setBoldMatchChar(String original, String param) {
        StringBuilder aux = new StringBuilder();
        char originalArray[] = original.toCharArray();
        char paramArray[] = param.toCharArray();

        for (int k = 0; k < paramArray.length; k++) {
            char charAuxOrig[] = {originalArray[k]};
            String auxOrig = new String(charAuxOrig);

            char charAuxParam[] = {paramArray[k]};
            String auxParam = new String(charAuxParam);

            if (auxParam.equalsIgnoreCase(auxOrig)) {
                aux.append(START);
                aux.append(originalArray[k]);
                aux.append(END);
            } else {
                aux.append(originalArray[k]);
            }
        }
        for (int k = param.length(); k < originalArray.length; k++) {
            aux.append(originalArray[k]);
        }
        return aux.toString();
    }

    public static int getInt(String s) {
        return Integer.getInteger(s);
    }

    public static String getStatus(int page) {
        int end = page * 10;
        int start = end - 9;
        return "" + start + "-" + end;
    }
}
