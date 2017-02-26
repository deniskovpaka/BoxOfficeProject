package net.proselyte.boxoffice.model.helper;

import java.util.Arrays;
import java.util.function.Function;
import java.util.function.IntFunction;

/**
 * The Utils class containing some static utility methods.
 *
 * @author deniskovpaka
 */
public class Utils {
    public static boolean[] booleansClassArrayToPrimitiveArray(final Boolean[] booleansArray) {
        final boolean[] primitives = new boolean[booleansArray.length];
        int index = 0;
        for (boolean primitive : booleansArray) {
            primitives[index++] = primitive;
        }
        return primitives;
    }
    /**
     * The SIZE Must!!! be synchronized
     * with {seats.seats_list && ticket.ticket_number}.
     */
    private static final int SIZE = 10;
    /**
     * Specific method for checking value.
     * The *number* value must not exceed
     * SIZE and be positive number.
     * @param sizeValue
     * @return TRUE if the value isn't correct, FALSE otherwise.
     * The SIZE must be synchronized with {seats.seats_list && ticket.ticket_number}.
     */
    public static boolean isSizeValueIncorrect(int sizeValue) {
        return sizeValue >= SIZE || sizeValue < 0;
    }

    public static <T, U> U[] convertArray(T[] from,
                                          Function<T, U> func,
                                          IntFunction<U[]> generator) {
        return Arrays.stream(from).map(func).toArray(generator);
    }
}