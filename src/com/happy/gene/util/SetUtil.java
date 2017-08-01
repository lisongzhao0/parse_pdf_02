package com.happy.gene.util;

import com.happy.gene.pdf.generate.ICloneable;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by zhaolisong on 26/07/2017.
 */
public class SetUtil {

    public static final SetUtil newInstance() { return new SetUtil(); }

    public SetUtil() {}

    public boolean isListEmpty(List list) { return (null==list || list.isEmpty()); }
    public boolean isMapEmpty(Map map) { return (null==map || map.isEmpty()); }
    public boolean isSetEmpty(Set set) { return (null==set || set.isEmpty()); }

    public List<ICloneable> copyList(List<ICloneable> src, List<ICloneable> dest) {
        if (null==src || src.isEmpty()) { return dest; }
        if (null==dest) { dest = new ArrayList<>(); }

        for (ICloneable tmp : src) {
            if (null==tmp) { continue; }
            dest.add(tmp.clone(tmp.createBlank()));
        }
        return dest;
    }
}
