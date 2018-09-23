//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4-2 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2013.07.04 at 12:26:43 PM CEST 
//


package de.w3l.anw.wetterstation.xml.wdaten;

import javax.xml.bind.annotation.XmlRegistry;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the de.w3l.anw.wetterstation.xml package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {


    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: de.w3l.anw.wetterstation.xml
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link Weather }
     * 
     */
    public Weather createResponse() {
        return new Weather();
    }

    /**
     * Create an instance of {@link Weather.CurrentObservation }
     * 
     */
    public Weather.CurrentObservation createResponseCurrentObservation() {
        return new Weather.CurrentObservation();
    }

    /**
     * Create an instance of {@link Weather.Features }
     * 
     */
    public Weather.Features createResponseFeatures() {
        return new Weather.Features();
    }

    /**
     * Create an instance of {@link Weather.CurrentObservation.Image }
     * 
     */
    public Weather.CurrentObservation.Image createResponseCurrentObservationImage() {
        return new Weather.CurrentObservation.Image();
    }

    /**
     * Create an instance of {@link Weather.CurrentObservation.DisplayLocation }
     * 
     */
    public Weather.CurrentObservation.DisplayLocation createResponseCurrentObservationDisplayLocation() {
        return new Weather.CurrentObservation.DisplayLocation();
    }

    /**
     * Create an instance of {@link Weather.CurrentObservation.ObservationLocation }
     * 
     */
    public Weather.CurrentObservation.ObservationLocation createResponseCurrentObservationObservationLocation() {
        return new Weather.CurrentObservation.ObservationLocation();
    }

}
