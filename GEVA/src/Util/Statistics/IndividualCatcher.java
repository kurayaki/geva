/*
 * StatCatcher.java
 *
 * Created on November 2, 2006, 1:35 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package Util.Statistics;

import Exceptions.BadParameterException;
import FitnessEvaluation.MultiSquares.PictureCopy;
import Individuals.FitnessPackage.Fitness;
import Individuals.Individual;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

import Parameter.ParameterI;
import Util.Constants;

/**
 * Stores Individuals
 * @author Blip
 */
public class IndividualCatcher implements ParameterI {
    
    private StringBuffer sb;
    private int catchInterval;

    /** Creates a new instance of StatCatcher */
    public IndividualCatcher() {
        this.sb = new StringBuffer();
        this.catchInterval = 10;
    }

    /**
     * New instance
     * @param p properties
     */
    public IndividualCatcher(Properties p) {
        this.sb = new StringBuffer();
        this.setProperties(p);
    }

    public void setProperties(Properties p) {
        int valueI;
        try {
            String key = Constants.INDIVIDUAL_CATCH_INTERVAL;
            valueI = Integer.parseInt(p.getProperty(key));
            if(valueI < 1) {
                if(valueI!=-1) {
                    throw new BadParameterException(key);
                } else {
                    valueI = Integer.MAX_VALUE;
                }
            }
        } catch(Exception e) {
            valueI = Integer.MAX_VALUE;
            System.out.println("Catch interval default: best individual");
        }
        this.catchInterval = valueI;
    }

    /**
     * Add the population to a StringBuffer. Individuals are taken according to catchInterval
     * @param population Population
     */
    
    public void addPop(List<Individual> population) {
        Fitness[] fA = new Fitness[population.size()];
        for(int i=0;i<fA.length;i++) {
            fA[i] = population.get(i).getFitness();
        }
        //Sort ascending
        Arrays.sort(fA);
        int cnt = 0;
        while(cnt<fA.length) {
            sb.append("Rank:").append(cnt).append(" Fit:").append(fA[cnt].getDouble());
            sb.append(" Phenotype:").append(fA[cnt].getIndividual().toString());
            sb.append(System.getProperty("line.separator"));
            cnt += this.catchInterval;
        }  
    }

    /**
     * Add a string to the string buffer
     * @param s string
     */
    public void addString(String s) {
        this.sb.append(s);
    }

    /**
     * Clear the stringbuffer
     */
    public void clear() {
        this.sb = new StringBuffer();
    }

    /**
     * Get the capacity of the stringbuffer
     * @return capacity of stringbuffer
     */
    public int getCapacity() {
        return this.sb.capacity();
    }
			      
    /**
     * Individuals in the population are stored according to this interval.
     * E.g A value of 10 stores individual 1, 100, 200, ...
     * @param catchInterval Interval for catching
     */
    public void setCatchInterval(int catchInterval) {
        this.catchInterval = catchInterval;
    }

    /**
     * Get catch intervall
     * @return catch interval
     */
    public int getCatchInterval() {
        return this.catchInterval;
    }

    @Override
    public String toString() {
        return this.sb.toString();
    }
    
}
