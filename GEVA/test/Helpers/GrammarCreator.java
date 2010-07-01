package Helpers;

import Mapper.*;
import java.io.*;
import java.util.Properties;
import Individuals.*;

public class GrammarCreator {

    private GrammarCreator() {
    }

    final public static String getTestFile(String file_name) {
        return GrammarCreator.getGrammarFile(file_name);
    }

    public static String getGrammarFile(String file_name) {
        try {
            File f = new File(System.getProperty("user.dir") + File.separator + "test");
            if (f.exists()) {
                file_name = f.getAbsolutePath() + File.separator + file_name;
            } else {
                f = new File(System.getProperty("user.dir") + File.separator + "GEVA" + File.separator + "test");
                if (f.exists()) {
                    file_name = f.getAbsolutePath() + File.separator + file_name;
                } else {
                    throw new FileNotFoundException(file_name);
                }
            }
        } catch (IOException e) {
            System.err.println(e);
        }
        return file_name;
    }

    public static String getGrammarFile() {
        return GrammarCreator.getGrammarFile("test_grammar.bnf");
    }

    public static GEGrammar getGEGrammar(Properties properties_file) {
        GEGrammar geg = new GEGrammar(properties_file);
        return geg;
    }

    /**
     * Create size 10 with only 0 as codon value
     * @return gechromosome of size 10 with 0 as codon value
     */
    public static GEChromosome getGEChromosome() {
        int[] ia = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
        GEChromosome chrom = new GEChromosome(10, ia);
        chrom.setMaxChromosomeLength(10);
        chrom.setMaxCodonValue(Integer.MAX_VALUE);
        return chrom;
    }

    public static ContextualDerivationTree getContextualDerivationTree(GEGrammar geg, GEChromosome gec) {
        return new ContextualDerivationTree(geg, gec);
    }

    public static DerivationTree getDerivationTree(GEGrammar geg, GEChromosome gec) {
        return new DerivationTree(geg, gec);
    }

    /**
     * FIXME proper file search
     * @param properties_file
     * @return
     */
    public static Properties getProperties(String properties_file) {
        Properties p = new Properties();
        String file_name = "";
        boolean found = false;
        try {
            File f;
            f = new File(properties_file);
            if(f.exists()) {
                found = true;
                p.load(new FileInputStream(f));
            } else {
                f = new File(System.getProperty("user.dir") + File.separator + "test");
                if (f.exists()) {
                    file_name = f.getAbsolutePath() + File.separator + properties_file;
                    f = new File(file_name);
                    if (f.exists()) {
                        p.load(new FileInputStream(f));
                        found = true;
                    }
                }
            }
            if(!found) {
                f = new File(System.getProperty("user.dir") + File.separator + "GEVA" + File.separator + "test");
                if (f.exists()) {
                    file_name = f.getAbsolutePath() + File.separator + properties_file;
                    f = new File(file_name);
                    p.load(new FileInputStream(f));
                    String grammar_path = p.getProperty("grammar_file");
                    grammar_path = grammar_path.replaceFirst("..", ".");
                    p.setProperty("grammar_file", grammar_path);
                } else {
                    throw new FileNotFoundException(properties_file);
                }
            }
        } catch (IOException e) {
            System.err.println("Error loading properties:" + e);
        }
        return p;
    }

    public static Properties getProperties() {
        return GrammarCreator.getProperties("test.properties");
    }
}