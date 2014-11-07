
package com.ECS.client.jax;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java pour anonymous complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="IsValid" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="BrowseNodeLookupRequest" type="{http://webservices.amazon.com/AWSECommerceService/2011-08-01}BrowseNodeLookupRequest" minOccurs="0"/>
 *         &lt;element name="ItemSearchRequest" type="{http://webservices.amazon.com/AWSECommerceService/2011-08-01}ItemSearchRequest" minOccurs="0"/>
 *         &lt;element name="ItemLookupRequest" type="{http://webservices.amazon.com/AWSECommerceService/2011-08-01}ItemLookupRequest" minOccurs="0"/>
 *         &lt;element name="SimilarityLookupRequest" type="{http://webservices.amazon.com/AWSECommerceService/2011-08-01}SimilarityLookupRequest" minOccurs="0"/>
 *         &lt;element name="CartGetRequest" type="{http://webservices.amazon.com/AWSECommerceService/2011-08-01}CartGetRequest" minOccurs="0"/>
 *         &lt;element name="CartAddRequest" type="{http://webservices.amazon.com/AWSECommerceService/2011-08-01}CartAddRequest" minOccurs="0"/>
 *         &lt;element name="CartCreateRequest" type="{http://webservices.amazon.com/AWSECommerceService/2011-08-01}CartCreateRequest" minOccurs="0"/>
 *         &lt;element name="CartModifyRequest" type="{http://webservices.amazon.com/AWSECommerceService/2011-08-01}CartModifyRequest" minOccurs="0"/>
 *         &lt;element name="CartClearRequest" type="{http://webservices.amazon.com/AWSECommerceService/2011-08-01}CartClearRequest" minOccurs="0"/>
 *         &lt;element ref="{http://webservices.amazon.com/AWSECommerceService/2011-08-01}Errors" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "isValid",
    "browseNodeLookupRequest",
    "itemSearchRequest",
    "itemLookupRequest",
    "similarityLookupRequest",
    "cartGetRequest",
    "cartAddRequest",
    "cartCreateRequest",
    "cartModifyRequest",
    "cartClearRequest",
    "errors"
})
@XmlRootElement(name = "Request")
public class Request {

    @XmlElement(name = "IsValid")
    protected String isValid;
    @XmlElement(name = "BrowseNodeLookupRequest")
    protected BrowseNodeLookupRequest browseNodeLookupRequest;
    @XmlElement(name = "ItemSearchRequest")
    protected ItemSearchRequest itemSearchRequest;
    @XmlElement(name = "ItemLookupRequest")
    protected ItemLookupRequest itemLookupRequest;
    @XmlElement(name = "SimilarityLookupRequest")
    protected SimilarityLookupRequest similarityLookupRequest;
    @XmlElement(name = "CartGetRequest")
    protected CartGetRequest cartGetRequest;
    @XmlElement(name = "CartAddRequest")
    protected CartAddRequest cartAddRequest;
    @XmlElement(name = "CartCreateRequest")
    protected CartCreateRequest cartCreateRequest;
    @XmlElement(name = "CartModifyRequest")
    protected CartModifyRequest cartModifyRequest;
    @XmlElement(name = "CartClearRequest")
    protected CartClearRequest cartClearRequest;
    @XmlElement(name = "Errors")
    protected Errors errors;

    /**
     * Obtient la valeur de la propriété isValid.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIsValid() {
        return isValid;
    }

    /**
     * Définit la valeur de la propriété isValid.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIsValid(String value) {
        this.isValid = value;
    }

    /**
     * Obtient la valeur de la propriété browseNodeLookupRequest.
     * 
     * @return
     *     possible object is
     *     {@link BrowseNodeLookupRequest }
     *     
     */
    public BrowseNodeLookupRequest getBrowseNodeLookupRequest() {
        return browseNodeLookupRequest;
    }

    /**
     * Définit la valeur de la propriété browseNodeLookupRequest.
     * 
     * @param value
     *     allowed object is
     *     {@link BrowseNodeLookupRequest }
     *     
     */
    public void setBrowseNodeLookupRequest(BrowseNodeLookupRequest value) {
        this.browseNodeLookupRequest = value;
    }

    /**
     * Obtient la valeur de la propriété itemSearchRequest.
     * 
     * @return
     *     possible object is
     *     {@link ItemSearchRequest }
     *     
     */
    public ItemSearchRequest getItemSearchRequest() {
        return itemSearchRequest;
    }

    /**
     * Définit la valeur de la propriété itemSearchRequest.
     * 
     * @param value
     *     allowed object is
     *     {@link ItemSearchRequest }
     *     
     */
    public void setItemSearchRequest(ItemSearchRequest value) {
        this.itemSearchRequest = value;
    }

    /**
     * Obtient la valeur de la propriété itemLookupRequest.
     * 
     * @return
     *     possible object is
     *     {@link ItemLookupRequest }
     *     
     */
    public ItemLookupRequest getItemLookupRequest() {
        return itemLookupRequest;
    }

    /**
     * Définit la valeur de la propriété itemLookupRequest.
     * 
     * @param value
     *     allowed object is
     *     {@link ItemLookupRequest }
     *     
     */
    public void setItemLookupRequest(ItemLookupRequest value) {
        this.itemLookupRequest = value;
    }

    /**
     * Obtient la valeur de la propriété similarityLookupRequest.
     * 
     * @return
     *     possible object is
     *     {@link SimilarityLookupRequest }
     *     
     */
    public SimilarityLookupRequest getSimilarityLookupRequest() {
        return similarityLookupRequest;
    }

    /**
     * Définit la valeur de la propriété similarityLookupRequest.
     * 
     * @param value
     *     allowed object is
     *     {@link SimilarityLookupRequest }
     *     
     */
    public void setSimilarityLookupRequest(SimilarityLookupRequest value) {
        this.similarityLookupRequest = value;
    }

    /**
     * Obtient la valeur de la propriété cartGetRequest.
     * 
     * @return
     *     possible object is
     *     {@link CartGetRequest }
     *     
     */
    public CartGetRequest getCartGetRequest() {
        return cartGetRequest;
    }

    /**
     * Définit la valeur de la propriété cartGetRequest.
     * 
     * @param value
     *     allowed object is
     *     {@link CartGetRequest }
     *     
     */
    public void setCartGetRequest(CartGetRequest value) {
        this.cartGetRequest = value;
    }

    /**
     * Obtient la valeur de la propriété cartAddRequest.
     * 
     * @return
     *     possible object is
     *     {@link CartAddRequest }
     *     
     */
    public CartAddRequest getCartAddRequest() {
        return cartAddRequest;
    }

    /**
     * Définit la valeur de la propriété cartAddRequest.
     * 
     * @param value
     *     allowed object is
     *     {@link CartAddRequest }
     *     
     */
    public void setCartAddRequest(CartAddRequest value) {
        this.cartAddRequest = value;
    }

    /**
     * Obtient la valeur de la propriété cartCreateRequest.
     * 
     * @return
     *     possible object is
     *     {@link CartCreateRequest }
     *     
     */
    public CartCreateRequest getCartCreateRequest() {
        return cartCreateRequest;
    }

    /**
     * Définit la valeur de la propriété cartCreateRequest.
     * 
     * @param value
     *     allowed object is
     *     {@link CartCreateRequest }
     *     
     */
    public void setCartCreateRequest(CartCreateRequest value) {
        this.cartCreateRequest = value;
    }

    /**
     * Obtient la valeur de la propriété cartModifyRequest.
     * 
     * @return
     *     possible object is
     *     {@link CartModifyRequest }
     *     
     */
    public CartModifyRequest getCartModifyRequest() {
        return cartModifyRequest;
    }

    /**
     * Définit la valeur de la propriété cartModifyRequest.
     * 
     * @param value
     *     allowed object is
     *     {@link CartModifyRequest }
     *     
     */
    public void setCartModifyRequest(CartModifyRequest value) {
        this.cartModifyRequest = value;
    }

    /**
     * Obtient la valeur de la propriété cartClearRequest.
     * 
     * @return
     *     possible object is
     *     {@link CartClearRequest }
     *     
     */
    public CartClearRequest getCartClearRequest() {
        return cartClearRequest;
    }

    /**
     * Définit la valeur de la propriété cartClearRequest.
     * 
     * @param value
     *     allowed object is
     *     {@link CartClearRequest }
     *     
     */
    public void setCartClearRequest(CartClearRequest value) {
        this.cartClearRequest = value;
    }

    /**
     * Obtient la valeur de la propriété errors.
     * 
     * @return
     *     possible object is
     *     {@link Errors }
     *     
     */
    public Errors getErrors() {
        return errors;
    }

    /**
     * Définit la valeur de la propriété errors.
     * 
     * @param value
     *     allowed object is
     *     {@link Errors }
     *     
     */
    public void setErrors(Errors value) {
        this.errors = value;
    }

}
