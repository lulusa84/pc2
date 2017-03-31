/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package documentazione;

import org.w3c.dom.DOMException;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 *
 * @author sarahtarantino
 */
public interface elements extends Node {
    NodeList getElementsByTagNameNS(String namespaceURI,
                              String localName)
                                throws DOMException;

}
