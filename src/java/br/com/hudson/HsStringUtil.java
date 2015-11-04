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

    public static String simpleBold(String original, String param) {

        String paramLow = param.toLowerCase();
        String paramHigh = param.toUpperCase();
        String paramUpperFrist = paramLow.substring(0, 1).toUpperCase() + paramLow.substring(1, param.length());
        
        System.out.println(paramLow);
        System.out.println(paramHigh);
        System.out.println(paramUpperFrist);

        original = original.replace(paramLow, "<b>" + paramLow + "</b>");
        System.out.println(original);
        original = original.replace(paramHigh, "<b>" + paramHigh + "</b>");
                System.out.println(original);
        original = original.replace(paramUpperFrist, "<b>" + paramUpperFrist + "</b>");
                System.out.println(original);
        return original;
    }
}
