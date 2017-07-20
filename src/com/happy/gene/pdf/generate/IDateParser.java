package com.happy.gene.pdf.generate;

import java.util.HashMap;

/**
 * Created by zhaolisong on 14/07/2017.
 */
public interface IDateParser {
    String getName();
    HashMap<String, Object> parse(HashMap<String, Object> params);
}
