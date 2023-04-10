package com.igo.models.localization;

import java.util.HashMap;
import java.util.Observable;

/**
 * @author mkjodhani
 * @version 1.0
 * @project SDM TVM Project
 * @since 01/03/23
 */
public class Language extends Observable {
    public enum TYPES{
        ENGLISH,
        FRENCH
    }
    private static Language selectedLanguage = null;
    private HashMap<TYPES,HashMap<String,String>> languages;
    private TYPES selectedOption = TYPES.ENGLISH;
    private Language() {
        this.languages = new HashMap<>();
        this.languages.put(TYPES.FRENCH,new HashMap<>());
        this.languages.put(TYPES.ENGLISH,new HashMap<>());
        registerLabels();
    }

    public static Language getReference(){
        if (selectedLanguage == null){
            selectedLanguage = new Language();
        }
        return selectedLanguage;
    }
    public String getLabelByLanguage(String language, String word){
        HashMap<String,String> selectedLanguage = languages.getOrDefault(language,null);
        if(selectedLanguage == null){
            throw new Error("Language not found.");
        }
        else{
            return selectedLanguage.getOrDefault(word,"");
        }
    }

    private void registerLabels(){
        HashMap<String,String> english = languages.getOrDefault(TYPES.ENGLISH,null);
        HashMap<String,String> french = languages.getOrDefault(TYPES.FRENCH,null);
        // ADMIN VIEW
        english.put("customerTab","Customers");
        english.put("opusCardTab","Opus cards");
        english.put("ticketsTab","Tickets");
        english.put("customerMenu","Customer");
        english.put("priceMenu","Prices");
        english.put("helpMenu","Help");
        english.put("registerCustomerMenu","Register Customer");
        english.put("opusPriceMenu","Opus Price");
        english.put("ticketPriceMenu","Ticket Price");
        english.put("aboutMenu","About");
        english.put("customerIdColumn","Customer ID");
        english.put("firstName","First Name");
        english.put("lastName","Last Name");
        english.put("customerType","Customer Type");
        english.put("birthDate","Date of Birth");
        english.put("ticketId", "Ticket ID");
        english.put("areaCode", "Area Code");
        english.put("price", "Price");
        english.put("ticketType", "Ticket Type");
        english.put("cashPayment", "Cash Payment");
        english.put("opusId", "OPUS ID");
        english.put("currentPlan", "Current Plan");
        english.put("noCustomerFoundMessage","No customers found!");
        english.put("noOpusMessage","No opuses found!");
        english.put("noTicketsFoundMessage","No tickets found!");
        english.put("admin","Admin");
        english.put("opusPlanDetails","OPUS Plan Details:");
        english.put("opusRechargeTypeLabel","OPUS Recharge Type");
        english.put("update","Update");
        english.put("paymentDetailsLabel","Payment Details");
        english.put("cardHolderName","Cardholder Name:");
        english.put("cardNumber","Card Number:");
        english.put("expirationDate","Expiration Date (MM/YY):");
        english.put("cvv","CVV:");
        english.put("cardType","Card Type");
        english.put("makePayment","Make Payment");
        english.put("customerDetails","Customer Details");
        english.put("ticketPriceDetailsLabel","Ticket Price Details:");
        english.put("getNewOpusCard","Get New OPUS Card");
        english.put("rechargeOpusCard","Recharge OPUS Car");
        english.put("purchaseTicket","Purchase Ticket");
        english.put("scanTicket","Scan Ticket");
        english.put("ticketDetails","Ticket Details");
        english.put("registerOpus","Register OPUS");
        english.put("fetch","Fetch");

        french.put("customerTab","Clientes");
        french.put("opusCardTab","Cartes Opus");
        french.put("ticketsTab","Des billets");
        french.put("customerMenu","Cliente");
        french.put("priceMenu","Des prix");
        french.put("helpMenu","Aider");
        french.put("registerCustomerMenu","Enregistrer le client");
        french.put("opusPriceMenu","Prix ​​Opus");
        french.put("ticketPriceMenu","Le prix du ticket");
        french.put("aboutMenu","À propos de nous");
        french.put("customerIdColumn","N ° de client");
        french.put("firstName","Prénom");
        french.put("lastName","Nom de famille");
        french.put("customerType","Type de client");
        french.put("birthDate","Date de naissance");
        french.put("ticketId", "ID de billets");
        french.put("areaCode", "Indicatif régional");
        french.put("price", "Prix");
        french.put("ticketType", "Type de billet");
        french.put("cashPayment", "Paiement en espèces");
        french.put("opusId", "ID OPUS");
        french.put("currentPlan", "Plan actuel");
        french.put("noCustomerFoundMessage","Aucun client trouvé !");
        french.put("noOpusMessage","Aucun opus trouvé !");
        french.put("noTicketsFoundMessage","Aucun billet trouvé!");
        french.put("admin","Administratrice");
        french.put("opusPlanDetails","Détails du forfait OPUS :");
        french.put("opusRechargeTypeLabel","Type de recharge OPUS");
        french.put("update","Mise à jour");
        french.put("paymentDetailsLabel","Détails de paiement");
        french.put("cardHolderName","Nom du titulaire:");
        french.put("cardNumber","Numéro de carte:");
        french.put("expirationDate","Date d'expiration (MM/AA):");
        french.put("cardType","Type de carte");
        french.put("makePayment","Effectuer le paiement");
        french.put("customerDetails","Détails du client");
        french.put("ticketPriceDetailsLabel","Détails du prix du billet:");
        french.put("getNewOpusCard","Obtenir une nouvelle carte OPUS");
        french.put("rechargeOpusCard","Recharger la carte OPUS");
        french.put("purchaseTicket","Acheter un billet");
        french.put("scanTicket","Scanner le ticket");
        french.put("ticketDetails","Détails du billet");
        french.put("registerOpus","Inscrivez-OPUS");
        french.put("fetch","aller chercher");

        english.put("mainScreenTitle","Ticket vending machine(TVM)");
        french.put("mainScreenTitle","Distributeur automatique de billets(DAB)");
        // TODO put all the words for both language in hashmap
    }

    public TYPES getSelectedOption() {
        return selectedOption;
    }
    public String getLabel(String key){
        return languages.getOrDefault(this.selectedOption,null).get(key);
    }

    public void setSelectedOption(TYPES selectedOption) {
        this.selectedOption = selectedOption;
        setChanged();
        notifyObservers();
    }
}
