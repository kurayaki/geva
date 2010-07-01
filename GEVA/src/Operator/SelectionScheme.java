package Operator;

import Individuals.*;
import Individuals.FitnessPackage.BasicFitness;
import Individuals.Populations.Population;
import Individuals.Populations.SimplePopulation;
import Mapper.GEGrammar;
import Operator.Operations.Operation;
import Operator.Operations.SelectionOperation;
import Operator.Operations.TournamentSelect;
import Util.Random.MersenneTwisterFast;
import Util.Random.RandomNumberGenerator;

import java.util.Iterator;
import java.util.Properties;

/**
 * SelectionScheme has a SelectionOperation
 * This opertor is used for selecting from the population
 * @author Blip
 */
public class SelectionScheme extends SplitOperator{
    
    /** Creates a new instance of SelectionScheme
     * @param rng random number generator
     * @param size size
     * @param op operation
     */
    @SuppressWarnings({"SameParameterValue"})
    public SelectionScheme(RandomNumberGenerator rng, int size, Operation op){
        super(rng, size, op);
        SelectionOperation sOp = (SelectionOperation)op;
        this.destinationPopulation = sOp.getSelectedPopulation();
    }
    
    /** Creates a new instance of SelectionScheme
     * @param rng random number generator
     * @param op operation
     */
    public SelectionScheme(RandomNumberGenerator rng, Operation op){
        super(rng, ((SelectionOperation)op).getSize(), op);
        SelectionOperation sOp = (SelectionOperation)op;
        this.destinationPopulation = sOp.getSelectedPopulation();
    }

    /**
     * Set properties
     *
     * @param p object containing properties
     */
    public void setProperties(Properties p) {
        
    }

    public void perform() {
        super.operation.doOperation(super.population.getAll());
        //System.out.println("ops:"+this.population.size()+" "+this.population);
        //System.out.println("sps:"+this.destinationPopulation.size()+" "+this.destinationPopulation);
    }
    
    public void setOperation(Operation op) {
        this.operation = op;
    }
    
    public Operation getOperation() {
        return this.operation;
    }
    
    /**
     * Returns the selected population.
     * @return Selected population
     **/
    public Population getPopulation() {
        return this.destinationPopulation;
    }
    
    public static void main(String[] Args) {
        Population p = new SimplePopulation();
        Population selected;
        
        for(int i=0;i<100;i++) {
        GEIndividual ind = new GEIndividual();
        Chromosome c = new GEChromosome(100);
        Genotype g = new Genotype();
        g.add(c);
        ind.setGenotype(g);
        ind.setPhenotype(new Phenotype());
        ind.setMapper(new GEGrammar());
        
        BasicFitness f = new BasicFitness((double)i, ind );
        //f.setSortDirection(true);
        ind.setFitness(f);
        p.add(ind);
        }
        
        MersenneTwisterFast m = new MersenneTwisterFast();
        TournamentSelect t = new TournamentSelect(50,3,m);
        SelectionScheme selecta = new SelectionScheme(m,100,t);
        
        
        selecta.setPopulation(p);
        int tests = 100;
        double totalavg = 0;
        //long tstart = System.currentTimeMillis();
        for(int i = 0;i<tests;i++){
        selecta.perform();
        selected = selecta.getPopulation();
        Iterator<Individual> pItr = selected.iterator();
        double fitsofar = 0;
        while(pItr.hasNext())
        {
            
        fitsofar += pItr.next().getFitness().getDouble();
        
        }
        double avgFitness = fitsofar / (double)p.size();
        totalavg += avgFitness;
        }
        //ttotal = System.currentTimeMillis() - tstart;
        //System.out.println("time taken for " + tests + " selection operations on a population size of 2000");
        double x = totalavg/(double)tests;
        System.out.println(x);
     }
    
}
