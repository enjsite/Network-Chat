import java.util.Arrays;

public class Homework6 {

    public int[] arrayFrom4(int[] arr) {
        for (int i = arr.length-1; i >= 0; i--){
            if (arr[i] == 4){
                return Arrays.copyOfRange(arr, i+1, arr.length);
            }
        }
        throw new RuntimeException("No 4");
    }

    public boolean arrHave1and4(int[] arr){
        boolean have1 = false;
        boolean have4 = false;

        for(int i = 0; i < arr.length; i++){
            switch (arr[i]){
                case 1:
                    have1 = true;
                    break;
                case 4:
                    have4 = true;
                    break;
                default:
                    return false;
            }
        }
        return (have1 && have4);
    }
}