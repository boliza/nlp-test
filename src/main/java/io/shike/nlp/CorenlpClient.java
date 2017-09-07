package io.shike.nlp;

import java.util.Collection;
import java.util.Properties;

import edu.stanford.nlp.ie.util.RelationTriple;
import edu.stanford.nlp.ling.CoreAnnotations;
import edu.stanford.nlp.naturalli.NaturalLogicAnnotations;
import edu.stanford.nlp.pipeline.Annotation;
import edu.stanford.nlp.pipeline.StanfordCoreNLPClient;
import edu.stanford.nlp.util.CoreMap;

/**
 * @author Ranger Tsao(https://github.com/boliza)
 */
public class CorenlpClient {

    public static void main(String[] args) {
        Properties props = new Properties();
        props.setProperty("annotators", "tokenize,ssplit,pos,lemma,depparse,natlog,openie");
        StanfordCoreNLPClient pipeline = new StanfordCoreNLPClient(props, "http://192.168.199.209", 8080, 2);

        // Annotate an example document.
        Annotation doc = new Annotation("Vert.x is created by Tim Fox");
        pipeline.annotate(doc);

        // Loop over sentences in the document
        for (CoreMap sentence : doc.get(CoreAnnotations.SentencesAnnotation.class)) {
            // Get the OpenIE triples for the sentence
            Collection<RelationTriple> triples = sentence.get(NaturalLogicAnnotations.RelationTriplesAnnotation.class);
            // Print the triples
            for (RelationTriple triple : triples) {
                System.out.println(triple.confidence + "\t" +
                                       triple.subjectLemmaGloss() + "\t" +
                                       triple.relationLemmaGloss() + "\t" +
                                       triple.objectLemmaGloss());
            }
        }
    }

}
