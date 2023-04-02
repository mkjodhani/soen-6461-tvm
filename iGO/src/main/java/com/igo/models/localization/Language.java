package com.igo.models.localization;

import java.util.HashMap;

/**
 * @author mkjodhani
 * @version 1.0
 * @project SDM TVM Project
 * @since 01/03/23
 */
public class Language {
    static public String ENGLISH = "ENGLISH";
    static public String FRENCH = "FRENCH";
    private HashMap<String,HashMap<String,String>> languages;

    public Language() {
        this.languages = new HashMap<>();
        this.languages.put(FRENCH,new HashMap<>());
        this.languages.put(ENGLISH,new HashMap<>());
        registerLabels();
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
        HashMap<String,String> english = languages.getOrDefault(ENGLISH,null);
        HashMap<String,String> french = languages.getOrDefault(FRENCH,null);
        english.put("mainScreenTitle","Ticket vending machine(TVM)");
        french.put("mainScreenTitle","Distributeur automatique de billets(DAB)");
        // TODO put all the words for both language in hashmap
    }
}
