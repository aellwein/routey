/**
 * Routey servlet dispatcher can be extended by adding SPI implementations to the interfaces in this package. 
 * One of the example base extensions is the {@link net.ellwein.routey.core.extensions.RouteyPackageScanner} - 
 * it is the user app's pointer to the packages which are containing annotations processed by Routey. 
 * 
 * @author Alexander Ellwein
 * @since 1.0.0
 */
package net.ellwein.routey.core.extensions;