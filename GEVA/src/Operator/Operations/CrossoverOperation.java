/*
Grammatical Evolution in Java
Release: GEVA-v1.0.zip
Copyright (C) 2008 Michael O'Neill, Erik Hemberg, Anthony Brabazon, Conor Gilligan 
Contributors Patrick Middleburgh, Eliott Bartley, Jonathan Hugosson, Jeff Wrigh

Separate licences for asm, bsf, antlr, groovy, jscheme, commons-logging, jsci is included in the lib folder. 
Separate licence for rieps is included in src/com folder.

This licence refers to GEVA-v1.0.

This software is distributed under the terms of the GNU General Public License.


This program is free software: you can redistribute it and/or modify
it under the terms of the GNU General Public License as published by
the Free Software Foundation, either version 3 of the License, or
(at your option) any later version.

This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU General Public License for more details.

You should have received a copy of the GNU General Public License
along with this program.  If not, see <http://www.gnu.org/licenses/>.
*/

/*
 * CrossoverOperation.java
 *
 * Created on 14 March 2007, 22:45
 *
 */

package Operator.Operations;

import Exceptions.BadParameterException;
import Individuals.Individual;
import Util.Random.RandomNumberGenerator;
import Util.Random.Stochastic;
import Util.Constants;

import java.util.List;
import java.util.Properties;

/**
 * Abstract class for CrossoverOperation
 * @author Conor
 */
public abstract class CrossoverOperation implements Operation, Stochastic {
    
    double probability;
    RandomNumberGenerator rand;
    
    public abstract void doOperation(List<Individual> operands);
    
    /** Creates a new instance of CrossoverOperation
     * @param prob crossover probability
     * @param rng random number generator
     */
    CrossoverOperation(double prob, RandomNumberGenerator rng) {
        this.probability = prob;
        this.rand = rng;
    }

    /**
     * New instance
     * @param rng random number generator
     * @param p properties
     */
    CrossoverOperation(RandomNumberGenerator rng, Properties p) {
        this.setProperties(p);
        this.rand = rng;
    }

    /**
     * Set properties
     *
     * @param p object containing properties
     */
    public void setProperties(Properties p) {
        double value = 0.9;
        try {
            String key = Constants.CROSSOVER_PROBABILITY;
            if(p.getProperty(key)!=null) {
                value = Double.parseDouble(p.getProperty(key));
                if(value < 0.0 || value > 1.0) {
                    throw new BadParameterException(key);
                }
            }
        } catch(Exception e) {
            value = 0.9;
            System.out.println(e+" using default: "+value);
        }
        this.probability = value;
    }

    public void setRNG(RandomNumberGenerator m) {
        this.rand = m;
    }
    
    public RandomNumberGenerator getRNG() {
        return this.rand;
    }
    
}
