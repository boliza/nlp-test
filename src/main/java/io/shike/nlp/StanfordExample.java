package io.shike.nlp;

import java.io.IOException;
import java.util.List;
import java.util.Properties;

import edu.stanford.nlp.io.IOUtils;
import edu.stanford.nlp.ling.CoreAnnotations;
import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.pipeline.Annotation;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import edu.stanford.nlp.util.CoreMap;

/**
 * @author Ranger Tsao(https://github.com/boliza)
 */
public class StanfordExample {

    public static void main(String[] args) throws IOException {
        Annotation document = new Annotation("赵亮于2015年认识周理，他们两个在2016年的时候，强奸了雷玉玲");
// Setup Chinese Properties by loading them from classpath resources
        Properties props = new Properties();
        props.load(IOUtils.readerFromString("t.properties"));
// Or this way of doing it also works
// Properties props = StringUtils.argsToProperties(new String[]{"-props", "StanfordCoreNLP-chinese.properties"});
        StanfordCoreNLP corenlp = new StanfordCoreNLP(props);
        corenlp.annotate(document);
        corenlp.prettyPrint(document, System.out);
        List<CoreMap> sentences = document.get(CoreAnnotations.SentencesAnnotation.class);
        sentences.forEach(c -> {
            List<CoreLabel> tokens = c.get(CoreAnnotations.TokensAnnotation.class);
            System.out.println(tokens);
            for (CoreLabel token : tokens) {
                String word = token.getString(CoreAnnotations.TextAnnotation.class);
                String pos = token.getString(CoreAnnotations.PartOfSpeechAnnotation.class);
                String ner = token.getString(CoreAnnotations.NamedEntityTagAnnotation.class);
                System.out.println(word + "\t " + pos + "\t " + ner);
            }
        });
    }

}
