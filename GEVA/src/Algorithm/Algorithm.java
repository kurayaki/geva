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
 * Algorithm.java
 *
 * Created on March 27, 2007, 12:57 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package Algorithm;

/**
 * Interface for Algorithms
 * This simple interface defines three methods that an algorithm must implement
 * init() - runs the initialisation pipline(s) of the algorithm.
 * run(int iterations) steps the algorithms loop pipeline(s) a through specified number of iterations
 * step() steps the algorithms loop pipeline through a single iteration
 * @author Blip
 */
public interface Algorithm {
    /**
     * Initializing the algorithm
     */
    public void init();
    /**
     * Run for the specified number of steps
     * @param steps run for steps
     */
    public void run(int steps);
    /**
     * Step the algorithm once
     */
    public void step();
    
}
