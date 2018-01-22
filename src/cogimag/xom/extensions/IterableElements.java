/* * Copyright (C) 2017 Michal G.
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package cogimag.xom.extensions;
import java.util.Iterator;
import java.util.NoSuchElementException;
import nu.xom.Elements;
import nu.xom.Element;


/**
 *
 * @author Michal G. <Michal.G at cogitatummagnumtelae.com>
 */
public class IterableElements  {
    //xom Elements class is final. cannot extend it.
    private Elements eleColl;
    public IterableElements(Elements xom_element_collection) {
        eleColl = xom_element_collection;
    }
    
    private class ElementIterator implements Iterator {  
        int index;
        private ElementIterator() {
            index = 0;
        }
        @Override
        public boolean hasNext() {
            return ((eleColl.size() - index - 1) > 0);
        }
        @Override
        public Object next() throws NoSuchElementException {
            return eleColl.get(index++);        
        }
    }
    
    
    
    
}
