package io.shike.nlp;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Properties;

import edu.stanford.nlp.pipeline.Annotation;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;

/**
 * @author Ranger Tsao(https://github.com/boliza)
 */
public class StanfordExample {

    public static void main(String[] args) throws IOException {
        Annotation document = new Annotation("Vert.x created by Tim Fox, maintain by Julien Viet");
        Properties props = new Properties();
        props.setProperty("annotators", "tokenize,ssplit,pos,lemma,depparse,natlog,openie");
        StanfordCoreNLP corenlp = new StanfordCoreNLP(props);
        corenlp.annotate(document);
        corenlp.prettyPrint(document, System.out);

        corenlp.jsonPrint(document, new PrintWriter(System.out));

    }

}
