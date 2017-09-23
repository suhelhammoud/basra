import java.util.*;

/**
 * Created by Suhel on 23/09/17.
 */
public class PowerSetIterator<E> implements Iterator<List<E>> {

    final private E[] values;
    final private long maxSize;
    private long index;

    public PowerSetIterator(Collection<E> collection) {
        values = (E[]) collection.toArray();
        maxSize = (1 << values.length);
        index = 0;
    }

    public PowerSetIterator(E[] arr) {
        values = Arrays.copyOf(arr, arr.length);
        maxSize = (1 << values.length);
        index = 0;
    }


    public long size() {
        return maxSize;
    }

    @Override
    public boolean hasNext() {
        return index < maxSize;
    }

    @Override
    public List<E> next() {
        int length = Long.bitCount(index);
        List<E> result = new ArrayList<>(length);

        for (int i = 0; i < length; i++)
            if (((index >> i) & 1) > 0)
                result.add(values[i]);

        index++;
        return result;
    }


}
