/**
 * Created by Suhel on 23/09/17.
 */

public enum RANK {
    R1, R2, R3, R4, R5, R6, R7, R8, R9, R10, JACK, QUEEN, KING;

    @Override
    public String toString() {
        return ordinal() < 10 ?
                String.valueOf(ordinal() + 1) :
                new String[]{"J", "Q", "K"}[ordinal() - 10];
    }
}