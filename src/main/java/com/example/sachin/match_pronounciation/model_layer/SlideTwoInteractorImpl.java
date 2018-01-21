package com.example.sachin.match_pronounciation.model_layer;

import com.example.sachin.match_pronounciation.Listeners.SlideTwoListener;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by home on 20-Jan-18.
 */

public class SlideTwoInteractorImpl implements SlideTwoListener.SlideTwoInteractor {

    private SlideTwoListener.SlideTwoPresenter slideTwoPresenter;

    @Override
    public void calculatePercentageOfMatch(SlideTwoListener.SlideTwoPresenter slideTwoPresenter, String s1, String s2) {

        this.slideTwoPresenter = slideTwoPresenter;
        percentageOfWordMatch(s1, s2);
    }

    public void percentageOfWordMatch(String pronounciation, String userPronounciation) {
        pronounciation = pronounciation.trim().replaceAll("[.?!]", " ");
        userPronounciation = userPronounciation.trim().replaceAll("[.?!]", " ");
        String[] pro = pronounciation.split(" ");
        String[] uPro = userPronounciation.split(" ");
        percentageOfMatch(pro, uPro);
    }

    public void percentageOfMatch(String[] as0, String[] as1) {
        int n = as0.length;
        Integer temp = null;

        // String frequency of as0
        HashMap<String, Integer> hm0 = new HashMap<String, Integer>();
        for (int i = 0; i < n; i++) {
            temp = hm0.get(as0[i]);
            if (temp == null) {
                hm0.put(as0[i], new Integer(1));
            } else {
                hm0.put(as0[i], new Integer(temp.intValue() + 1));
            }
        }

        // String frequency of as1
        n = as1.length;
        HashMap<String, Integer> hm1 = new HashMap<String, Integer>();
        for (int i = 0; i < n; i++) {
            temp = hm1.get(as1[i]);
            if (temp == null) {
                hm1.put(as1[i], new Integer(1));
            } else {
                hm1.put(as1[i], new Integer(temp.intValue() + 1));
            }
        }

        // Frequency difference between hm0 and hm1 to diff
        HashMap<String, Integer> diff = new HashMap<String, Integer>();
        String key;
        Integer value, value1, rval;
        Iterator it = hm0.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<String, Integer> pairs = (Map.Entry<String, Integer>) it
                    .next();
            key = pairs.getKey();
            value = pairs.getValue();
            value1 = hm1.get(key);
            it.remove();
            hm1.remove(key);
            if (value1 != null)
                rval = new Integer(Math.abs(value1.intValue()
                        - value.intValue()));
            else
                rval = value;
            diff.put(key, rval);
        }

        // Sum all remaining String frequencies in hm1
        int val = 0;
        it = hm1.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<String, Integer> pairs = (Map.Entry<String, Integer>) it
                    .next();
            val += pairs.getValue().intValue();
        }

        // Sum all frequencies in diff
        it = diff.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<String, Integer> pairs = (Map.Entry<String, Integer>) it
                    .next();
            val += pairs.getValue().intValue();
        }

        // Calculate word match percentage
        int per = (int) ((((float) val * 100)) / ((float) (as0.length + as1.length)));
        per = 100 - per;

       slideTwoPresenter.matchPercentage(per);
    }
}
