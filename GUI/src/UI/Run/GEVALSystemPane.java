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

package UI.Run;

import Fractal.LSystem2Control;
import UI.Run.GEVALSystemStreamParser.Event;
import Util.I18N;
import java.awt.BorderLayout;

/**
 * Pane that shows final LSystem
 * @author eliott bartley
 */
public class GEVALSystemPane
     extends GEVAPane
  implements GEVALSystemStreamParser.Listener
             <GEVALSystemStreamParser.Event>

{

    public GEVALSystemPane(GEVAPaneManager paneManager)
    {   super(paneManager, I18N.get("ui.run.lsys.name"));
        super.setLayout(new BorderLayout());
    }

    public void lineParsed(Event event)
    {   LSystem2Control guiLSystem;
        super.viewMe("result");
        guiLSystem = new LSystem2Control
        (   event.getGrammar(),
            event.getDepth(),
            event.getAngle(),
            null
        );
        super.add(guiLSystem);

    }
    public void streamParsed() { }

}
