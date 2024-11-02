import React, { createContext, useContext, useState } from 'react';
import en from '../translations/en.json';
import es from '../translations/es.json';

const translations = {
  en,
  es,
  fn
};

const LanguageContext = createContext();

export const LanguageProvider = ({ children }) => {
  const [language, setLanguage] = useState('en');

  const changeLanguage = (lang) => setLanguage(lang);

  return (
    <LanguageContext.Provider value={{ language, changeLanguage, translations }}>
      {children}
    </LanguageContext.Provider>
  );
};

export const useLanguage = () => useContext(LanguageContext);

const Translate = ({ id }) => {
    const { language, translations } = useLanguage();
    return translations[language][id] || id;
  };
  
  export default Translate;