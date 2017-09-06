package io.shike.nlp;

import java.io.IOException;
import java.util.List;
import java.util.Properties;

import edu.stanford.nlp.ie.machinereading.structure.MachineReadingAnnotations;
import edu.stanford.nlp.ie.machinereading.structure.RelationMention;
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
        Annotation document = new Annotation("张三与李四在2015年结婚，次年生了个儿子，取名王五，再过2年，生了个女儿叫龙六");
// Setup Chinese Properties by loading them from classpath resources
        Properties props = new Properties();
        props.load(IOUtils.readerFromString("chinese.properties"));
// Or this way of doing it also works
// Properties props = StringUtils.argsToProperties(new String[]{"-props", "StanfordCoreNLP-chinese.properties"});
        StanfordCoreNLP corenlp = new StanfordCoreNLP(props);
        corenlp.annotate(document);
        corenlp.prettyPrint(document, System.out);
        List<CoreMap> sentences = document.get(CoreAnnotations.SentencesAnnotation.class);
        List<RelationMention> sentences2 = document.get(MachineReadingAnnotations.RelationMentionsAnnotation.class);

        sentences.forEach(c -> {
            List<CoreLabel> tokens = c.get(CoreAnnotations.TokensAnnotation.class);
            tokens.forEach(token -> {
                String word = token.getString(CoreAnnotations.TextAnnotation.class);
                String pos = token.getString(CoreAnnotations.PartOfSpeechAnnotation.class);
                String ner = token.getString(CoreAnnotations.NamedEntityTagAnnotation.class);
                System.out.println(word + "---->" + pos + "---->" + ner);
            });
        });

        sentences2.forEach(r -> {
            System.out.println(r.toString());
        });
    }

}
