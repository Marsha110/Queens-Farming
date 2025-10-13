package edu.kit.informatik;

import edu.kit.informatik.ui.QueensFarmingUI;

/**
 * The entry point of the program.
 *
 * @author uqeoc
 * @version 1.0
 */
public final class Application {
    private static final String ARGUMENT_ERROR = "Error:Expected 0 arguments, but got %d.";
    private static final String ERROR_UTILITY_CLASS_INSTANTIATION = "Utility class cannot be instantiated.";

    /**
     * Description: a constructor of Application
     */
    private Application() {
        throw new AssertionError(ERROR_UTILITY_CLASS_INSTANTIATION);
    }

    /**
     * The entry point of the program.
     *
     * @param args empty
     */
    public static void main(String[] args) {

        if(args.length==0){
            QueensFarmingUI QueensFarmingUI = new QueensFarmingUI();
            QueensFarmingUI.start();
        }else{
            System.err.println(String.format(ARGUMENT_ERROR, args.length));
        }
    }


}
