import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

class RandomizedSet {
    HashMap<Integer, Integer> hm;
    ArrayList<Integer> available;

    public RandomizedSet() {
        hm = new HashMap<Integer, Integer>();
        available = new ArrayList<Integer>();
    }

    public boolean insert(int val) {
        boolean hasValue = hm.containsKey(val);
        if (hasValue) return false;
        available.add(val);
        hm.put(val, available.size() - 1);
        return true;
    }

    public boolean remove(int val) {
        if (!hm.containsKey(val)) {
            return false;
        }
        Integer availableVal = available.get(available.size() - 1);
        int currIndex = hm.get(val);
        available.set(currIndex, availableVal);
        hm.put(availableVal, currIndex);
        available.remove(available.size() - 1);
        hm.remove(val);
        return true;
    }

    public int getRandom() {
        int currInd = ThreadLocalRandom.current().nextInt(0, available.size());
        return available.get(currInd);
    }
}

/**
 * Your RandomizedSet object will be instantiated and called as such:
 * RandomizedSet obj = new RandomizedSet();
 * boolean param_1 = obj.insert(val);
 * boolean param_2 = obj.remove(val);
 * int param_3 = obj.getRandom();
 */