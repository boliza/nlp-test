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
        Annotation document = new Annotation("15日，备受关注的电影《黄金时代》在北京举行了电影发布会，导演许鞍华和编剧李樯及汤唯、冯绍峰等众星悉数亮相。据悉，电影确定将于10月1日公映。本片讲述了“民国四大才女”之一的萧红短暂而传奇的一生，通过她与萧军、汪恩甲、端木蕻良、洛宾基四人的情感纠葛，与鲁迅、丁玲等人一起再现上世纪30年代的独特风貌。电影原名《穿过爱情的漫长旅程》，后更名《黄金时代》，这源自萧红写给萧军信中的一句话：“这不正是我的黄金时代吗？”");
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
