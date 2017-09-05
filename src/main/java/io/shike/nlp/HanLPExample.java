package io.shike.nlp;

import com.hankcs.hanlp.HanLP;
import com.hankcs.hanlp.seg.Segment;
import com.hankcs.hanlp.seg.common.Term;

import java.util.List;

/**
 * @author Ranger Tsao(https://github.com/boliza)
 */
public class HanLPExample {

    public static void main(String[] args) {
        Segment segment = HanLP.newSegment().enableNameRecognize(true);
        List<Term> termList = segment.seg("赵亮于2015年认识周理，他们两个在2016年的时候，强奸了雷玉玲");
        System.out.println(termList);
    }

}
